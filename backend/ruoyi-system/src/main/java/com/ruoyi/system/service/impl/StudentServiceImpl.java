package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanValidators;
import com.ruoyi.system.mapper.SysDictDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StudentMapper;
import com.ruoyi.system.domain.Student;
import com.ruoyi.system.service.IStudentService;

import static com.ruoyi.common.utils.PageUtils.startPage;

/**
 * 学生管理Service业务层处理
 *
 * @author xzr
 * @date 2024-08-18
 */
@Service
public class StudentServiceImpl implements IStudentService
{
    @Autowired
    private StudentMapper studentMapper;
    @Autowired private JdbcTemplate jdbc;
    private void checkGrade(Long grade) {
        if (SecurityUtils.isAdmin(SecurityUtils.getUserId())) return;
        boolean allowed = SecurityUtils.getLoginUser().getUser().getRoles().stream().anyMatch(r -> jdbc.queryForObject("select count(*) from sys_role_child_menu rm join child_menu c on c.child_menu_id=rm.child_menu_id where rm.role_id=? and c.child_menu_class='Grades' and c.child_menu_name=?",Integer.class,r.getRoleId(),String.valueOf(grade)) > 0);
        if (!allowed) throw new ServiceException("无权访问该班级学生");
    }
    private void checkStudent(Long id) {
        Student item=studentMapper.selectStudentByStudentId(id);
        if(item==null) throw new ServiceException("学生不存在");
        checkGrade(item.getGrades());
    }
    @Autowired
    private SysDictDataMapper dictDataMapper;
    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    /**
     * 查询学生管理
     *
     * @param studentId 学生管理主键
     * @return 学生管理
     */
    @Override
    public Student selectStudentByStudentId(Long studentId)
    {

        checkStudent(studentId);
        return studentMapper.selectStudentByStudentId(studentId);
    }

    /**
     * 查询学生管理列表
     *
     * @param student 学生管理
     * @return 学生管理
     */
    @Override
    public List<Student> selectStudentList(Student student)
    {
        student.getParams().put("isAdmin", SecurityUtils.isAdmin(SecurityUtils.getUserId()));
        student.getParams().put("roleIds", SecurityUtils.getLoginUser().getUser().getRoles().stream().map(r -> r.getRoleId()).collect(java.util.stream.Collectors.toList()));
        String dictType="student_mark";
        List<SysDictData> sysDictData = dictDataMapper.selectDictDataByType(dictType);
        for (int i = 0; i < sysDictData.size(); i++) {
            if (sysDictData.get(i).getDictValue().equals(student.getIsmark())){
                student.setIsmark(sysDictData.get(i).getDictLabel());
            }
        }
        startPage();
        List<Student> students = studentMapper.selectStudentList(student);
        return students;
    }

    /**
     * 新增学生管理
     *
     * @param student 学生管理
     * @return 结果
     */
    @Override
    public int insertStudent(Student student)
    {
        checkGrade(student.getGrades());
        student.setCreateTime(DateUtils.getNowDate());
        return studentMapper.insertStudent(student);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String importUser(List<Student> studentList)
    {
        if (StringUtils.isNull(studentList) || studentList.size() == 0)
        {
            throw new ServiceException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (Student student : studentList)
        {
            try
            {
                    checkGrade(student.getGrades());
                    student.setCreateTime(DateUtils.getNowDate());
                    studentMapper.insertStudent(student);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、学生 " + student.getStudentName() + " 导入成功");

            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、学生 " + student.getStudentName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Override
    public List<Student> selectDormitory(Student student) {
        return studentMapper.selectDormitory(student);
    }

    /**
     * 修改学生管理
     *
     * @param student 学生管理
     * @return 结果
     */
    @Override
    public int updateStudent(Student student)
    {
        checkStudent(student.getStudentId());
        if(student.getGrades()!=null) checkGrade(student.getGrades());
        student.setUpdateTime(DateUtils.getNowDate());
        return studentMapper.updateStudent(student);
    }

    /**
     * 批量删除学生管理
     *
     * @param studentIds 需要删除的学生管理主键
     * @return 结果
     */
    @Override
    public int deleteStudentByStudentIds(Long[] studentIds)
    {
        for(Long id:studentIds) checkStudent(id);
        return studentMapper.deleteStudentByStudentIds(studentIds);
    }

    /**
     * 删除学生管理信息
     *
     * @param studentId 学生管理主键
     * @return 结果
     */
    @Override
    public int deleteStudentByStudentId(Long studentId)
    {
        checkStudent(studentId);
        return studentMapper.deleteStudentByStudentId(studentId);
    }
}
