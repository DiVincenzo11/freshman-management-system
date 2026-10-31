"""Integration smoke test against a disposable local demo database.

Requires requests, openpyxl and redis-cli. Never point at a real student system.
"""
import base64,io,json,os,subprocess,uuid
import requests
from openpyxl import Workbook,load_workbook

BASE=os.getenv('BASE_URL','http://127.0.0.1:18800')
REDIS=os.getenv('REDIS_CLI','redis-cli')
checks=[]
def call(session,method,path,**kwargs):
 r=session.request(method,BASE+path,timeout=30,**kwargs);r.raise_for_status();r.encoding='utf-8';return r
def data(r):return r.json()
def ok(session,method,path,**kwargs):
 j=data(call(session,method,path,**kwargs));assert j.get('code')==200,j;return j
def login(username):
 s=requests.Session();j=ok(s,'GET','/captchaImage')
 value=subprocess.check_output([REDIS,'-h',os.getenv('REDIS_HOST','127.0.0.1'),'-p',os.getenv('REDIS_PORT','16380'),'--raw','GET','captcha_codes:'+j['uuid']],text=True).strip()
 try: value=json.loads(value)
 except ValueError: pass
 token=ok(s,'POST','/login',json={'username':username,'password':'Demo123456','code':str(value),'uuid':j['uuid']})['token']
 s.headers['Authorization']='Bearer '+token;return s
def check(label):checks.append(label);print('PASS',label)

admin=login('admin'); counselor=login('counselor');anon=requests.Session()
ok(admin,'GET','/getInfo');check('captcha login / JWT / account profile')
rows=ok(admin,'GET','/system/student/list',params={'pageSize':100})['rows'];assert len(rows)>=6;check('admin student list')
scoped=ok(counselor,'GET','/system/student/list',params={'params[roleId]':1,'pageSize':100})['rows']
assert scoped and all(x['grades']==1 for x in scoped);check('server-side class scope rejects forged roleId')
foreign=next(x for x in rows if x['grades']==2)
assert data(call(counselor,'GET',f"/system/student/{foreign['studentId']}"))['code']!=200
check('cross-class student detail rejected')
assert data(call(anon,'POST','/system/student/dormitory',json={}))['code']!=200
student=rows[0]
dorm=ok(anon,'POST','/system/student/dormitory',json={'studentName':student['studentName'],'idCard':student['idCard']})['data']
assert dorm['dormitory']==student['dormitory'] and not dorm.get('idCard')
check('dormitory lookup requires both keys and returns no identity data')
png=base64.b64decode('iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mP8/x8AAwMCAO+a4HsAAAAASUVORK5CYII=')
upload=ok(admin,'POST','/common/upload',files={'file':('demo.png',png,'image/png')})
assert upload['url'].startswith(BASE+'/profile/')
assert call(admin,'GET',upload['url'][len(BASE):]).content==png
check('image attachment upload / local resource retrieval')
created=[];marks=[];follows=[]
try:
 for grade in [1,2]:
  num='SMOKE-'+uuid.uuid4().hex[:12]
  ok(admin,'POST','/system/student',json={'studentName':'接口回归演示','studentNum':num,'grades':grade,'sex':0,'follow':2,'ismark':'','religion':'无'})
  item=ok(admin,'GET','/system/student/list',params={'studentNum':num})['rows'][0];created.append(item['studentId'])
 sid=created[0]
 ok(admin,'PUT','/system/student',json={'studentId':sid,'dormitory':'演示宿舍B-202'})
 assert ok(admin,'GET',f'/system/student/{sid}')['data']['dormitory']=='演示宿舍B-202'
 check('create student with optional fields / edit / read')
 for code in [1,2]:
  mark=ok(admin,'POST','/system/mark',json={'studentId':sid,'markContent':code})['data'];marks.append(mark['markId'])
 assert data(call(admin,'POST','/system/mark',json={'studentId':sid,'markContent':1}))['code']!=200
 assert 'null' not in ok(admin,'GET',f'/system/student/{sid}')['data']['ismark']
 ok(admin,'DELETE','/system/mark/'+','.join(map(str,marks)));marks=[]
 assert ok(admin,'GET',f'/system/student/{sid}')['data']['ismark']==''
 check('mark add / duplicate rejection / multi-delete label consistency')
 for sid in created:
  ok(admin,'POST','/system/follow_up',json={'studentId':sid,'followContent':'虚构自动回归测试记录'})
  follows.append(ok(admin,'GET','/system/follow_up/list',params={'studentId':sid})['rows'][0]['followId'])
  assert ok(admin,'GET',f'/system/student/{sid}')['data']['follow']==1
 ok(admin,'DELETE','/system/follow_up/'+','.join(map(str,follows)));follows=[]
 assert all(ok(admin,'GET',f'/system/student/{sid}')['data']['follow']==2 for sid in created)
 check('follow-up add / cross-student batch delete / status consistency')
 raw=call(admin,'POST','/system/student/export').content
 book=load_workbook(io.BytesIO(raw));assert book.active.max_row>=7;check('Excel export produces readable workbook')
 template=load_workbook(io.BytesIO(call(admin,'POST','/system/student/importTemplate').content))
 ws=template.active;headers={c.value:c.column for row in ws.iter_rows() for c in row if c.value in ['姓名','学号','专业班级']}
 assert len(headers)==3,headers
 num='IMPORT-'+uuid.uuid4().hex[:10];row=max(c.row for row in ws.iter_rows() for c in row if c.value=='姓名')+1
 for title,value in [('姓名','导入演示学生'),('学号',num),('专业班级','软件工程1班（演示）')]:ws.cell(row,headers[title],value)
 buf=io.BytesIO();template.save(buf)
 ok(admin,'POST','/system/student/importData',files={'file':('demo.xlsx',buf.getvalue(),'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet')})
 imported=ok(admin,'GET','/system/student/list',params={'studentNum':num})['rows'];assert len(imported)==1;created.append(imported[0]['studentId']);check('Excel template / upload / dictionary conversion / import')
finally:
 if created:ok(admin,'DELETE','/system/student/'+','.join(map(str,created)))
check('cleanup / delete student with cascading demo records')
print(json.dumps({'passed':len(checks),'checks':checks},ensure_ascii=False,indent=2))
