-- Reconstructed demo schema. Original business DDL was absent. No real student records.
SET NAMES utf8mb4;
create table sys_dept (
  dept_id           bigint(20)      not null auto_increment    comment '部门id',
  parent_id         bigint(20)      default 0                  comment '父部门id',
  ancestors         varchar(50)     default ''                 comment '祖级列表',
  dept_name         varchar(30)     default ''                 comment '部门名称',
  order_num         int(4)          default 0                  comment '显示顺序',
  leader            varchar(20)     default null               comment '负责人',
  phone             varchar(11)     default null               comment '联系电话',
  email             varchar(50)     default null               comment '邮箱',
  status            char(1)         default '0'                comment '部门状态（0正常 1停用）',
  del_flag          char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (dept_id)
) engine=innodb auto_increment=200 comment = '部门表';
create table sys_user (
  user_id           bigint(20)      not null auto_increment    comment '用户ID',
  dept_id           bigint(20)      default null               comment '部门ID',
  user_name         varchar(30)     not null                   comment '用户账号',
  nick_name         varchar(30)     not null                   comment '用户昵称',
  user_type         varchar(2)      default '00'               comment '用户类型（00系统用户）',
  email             varchar(50)     default ''                 comment '用户邮箱',
  phonenumber       varchar(11)     default ''                 comment '手机号码',
  sex               char(1)         default '0'                comment '用户性别（0男 1女 2未知）',
  avatar            varchar(100)    default ''                 comment '头像地址',
  password          varchar(100)    default ''                 comment '密码',
  status            char(1)         default '0'                comment '帐号状态（0正常 1停用）',
  del_flag          char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
  login_ip          varchar(128)    default ''                 comment '最后登录IP',
  login_date        datetime                                   comment '最后登录时间',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (user_id)
) engine=innodb auto_increment=100 comment = '用户信息表';
create table sys_post
(
  post_id       bigint(20)      not null auto_increment    comment '岗位ID',
  post_code     varchar(64)     not null                   comment '岗位编码',
  post_name     varchar(50)     not null                   comment '岗位名称',
  post_sort     int(4)          not null                   comment '显示顺序',
  status        char(1)         not null                   comment '状态（0正常 1停用）',
  create_by     varchar(64)     default ''                 comment '创建者',
  create_time   datetime                                   comment '创建时间',
  update_by     varchar(64)     default ''			       comment '更新者',
  update_time   datetime                                   comment '更新时间',
  remark        varchar(500)    default null               comment '备注',
  primary key (post_id)
) engine=innodb comment = '岗位信息表';
create table sys_role (
  role_id              bigint(20)      not null auto_increment    comment '角色ID',
  role_name            varchar(30)     not null                   comment '角色名称',
  role_key             varchar(100)    not null                   comment '角色权限字符串',
  role_sort            int(4)          not null                   comment '显示顺序',
  data_scope           char(1)         default '1'                comment '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  menu_check_strictly  tinyint(1)      default 1                  comment '菜单树选择项是否关联显示',
  dept_check_strictly  tinyint(1)      default 1                  comment '部门树选择项是否关联显示',
  status               char(1)         not null                   comment '角色状态（0正常 1停用）',
  del_flag             char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
  create_by            varchar(64)     default ''                 comment '创建者',
  create_time          datetime                                   comment '创建时间',
  update_by            varchar(64)     default ''                 comment '更新者',
  update_time          datetime                                   comment '更新时间',
  remark               varchar(500)    default null               comment '备注',
  primary key (role_id)
) engine=innodb auto_increment=100 comment = '角色信息表';
create table sys_menu (
  menu_id           bigint(20)      not null auto_increment    comment '菜单ID',
  menu_name         varchar(50)     not null                   comment '菜单名称',
  parent_id         bigint(20)      default 0                  comment '父菜单ID',
  order_num         int(4)          default 0                  comment '显示顺序',
  path              varchar(200)    default ''                 comment '路由地址',
  component         varchar(255)    default null               comment '组件路径',
  query             varchar(255)    default null               comment '路由参数',
  route_name        varchar(50)     default ''                 comment '路由名称',
  is_frame          int(1)          default 1                  comment '是否为外链（0是 1否）',
  is_cache          int(1)          default 0                  comment '是否缓存（0缓存 1不缓存）',
  menu_type         char(1)         default ''                 comment '菜单类型（M目录 C菜单 F按钮）',
  visible           char(1)         default 0                  comment '菜单状态（0显示 1隐藏）',
  status            char(1)         default 0                  comment '菜单状态（0正常 1停用）',
  perms             varchar(100)    default null               comment '权限标识',
  icon              varchar(100)    default '#'                comment '菜单图标',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default ''                 comment '备注',
  primary key (menu_id)
) engine=innodb auto_increment=2000 comment = '菜单权限表';
create table sys_user_role (
  user_id   bigint(20) not null comment '用户ID',
  role_id   bigint(20) not null comment '角色ID',
  primary key(user_id, role_id)
) engine=innodb comment = '用户和角色关联表';
create table sys_role_menu (
  role_id   bigint(20) not null comment '角色ID',
  menu_id   bigint(20) not null comment '菜单ID',
  primary key(role_id, menu_id)
) engine=innodb comment = '角色和菜单关联表';
create table sys_role_dept (
  role_id   bigint(20) not null comment '角色ID',
  dept_id   bigint(20) not null comment '部门ID',
  primary key(role_id, dept_id)
) engine=innodb comment = '角色和部门关联表';
create table sys_user_post
(
  user_id   bigint(20) not null comment '用户ID',
  post_id   bigint(20) not null comment '岗位ID',
  primary key (user_id, post_id)
) engine=innodb comment = '用户与岗位关联表';
create table sys_oper_log (
  oper_id           bigint(20)      not null auto_increment    comment '日志主键',
  title             varchar(50)     default ''                 comment '模块标题',
  business_type     int(2)          default 0                  comment '业务类型（0其它 1新增 2修改 3删除）',
  method            varchar(200)    default ''                 comment '方法名称',
  request_method    varchar(10)     default ''                 comment '请求方式',
  operator_type     int(1)          default 0                  comment '操作类别（0其它 1后台用户 2手机端用户）',
  oper_name         varchar(50)     default ''                 comment '操作人员',
  dept_name         varchar(50)     default ''                 comment '部门名称',
  oper_url          varchar(255)    default ''                 comment '请求URL',
  oper_ip           varchar(128)    default ''                 comment '主机地址',
  oper_location     varchar(255)    default ''                 comment '操作地点',
  oper_param        varchar(2000)   default ''                 comment '请求参数',
  json_result       varchar(2000)   default ''                 comment '返回参数',
  status            int(1)          default 0                  comment '操作状态（0正常 1异常）',
  error_msg         varchar(2000)   default ''                 comment '错误消息',
  oper_time         datetime                                   comment '操作时间',
  cost_time         bigint(20)      default 0                  comment '消耗时间',
  primary key (oper_id),
  key idx_sys_oper_log_bt (business_type),
  key idx_sys_oper_log_s  (status),
  key idx_sys_oper_log_ot (oper_time)
) engine=innodb auto_increment=100 comment = '操作日志记录';
create table sys_dict_type
(
  dict_id          bigint(20)      not null auto_increment    comment '字典主键',
  dict_name        varchar(100)    default ''                 comment '字典名称',
  dict_type        varchar(100)    default ''                 comment '字典类型',
  status           char(1)         default '0'                comment '状态（0正常 1停用）',
  create_by        varchar(64)     default ''                 comment '创建者',
  create_time      datetime                                   comment '创建时间',
  update_by        varchar(64)     default ''                 comment '更新者',
  update_time      datetime                                   comment '更新时间',
  remark           varchar(500)    default null               comment '备注',
  primary key (dict_id),
  unique (dict_type)
) engine=innodb auto_increment=100 comment = '字典类型表';
create table sys_dict_data
(
  dict_code        bigint(20)      not null auto_increment    comment '字典编码',
  dict_sort        int(4)          default 0                  comment '字典排序',
  dict_label       varchar(100)    default ''                 comment '字典标签',
  dict_value       varchar(100)    default ''                 comment '字典键值',
  dict_type        varchar(100)    default ''                 comment '字典类型',
  css_class        varchar(100)    default null               comment '样式属性（其他样式扩展）',
  list_class       varchar(100)    default null               comment '表格回显样式',
  is_default       char(1)         default 'N'                comment '是否默认（Y是 N否）',
  status           char(1)         default '0'                comment '状态（0正常 1停用）',
  create_by        varchar(64)     default ''                 comment '创建者',
  create_time      datetime                                   comment '创建时间',
  update_by        varchar(64)     default ''                 comment '更新者',
  update_time      datetime                                   comment '更新时间',
  remark           varchar(500)    default null               comment '备注',
  primary key (dict_code)
) engine=innodb auto_increment=100 comment = '字典数据表';
create table sys_config (
  config_id         int(5)          not null auto_increment    comment '参数主键',
  config_name       varchar(100)    default ''                 comment '参数名称',
  config_key        varchar(100)    default ''                 comment '参数键名',
  config_value      varchar(500)    default ''                 comment '参数键值',
  config_type       char(1)         default 'N'                comment '系统内置（Y是 N否）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (config_id)
) engine=innodb auto_increment=100 comment = '参数配置表';
create table sys_logininfor (
  info_id        bigint(20)     not null auto_increment   comment '访问ID',
  user_name      varchar(50)    default ''                comment '用户账号',
  ipaddr         varchar(128)   default ''                comment '登录IP地址',
  login_location varchar(255)   default ''                comment '登录地点',
  browser        varchar(50)    default ''                comment '浏览器类型',
  os             varchar(50)    default ''                comment '操作系统',
  status         char(1)        default '0'               comment '登录状态（0成功 1失败）',
  msg            varchar(255)   default ''                comment '提示消息',
  login_time     datetime                                 comment '访问时间',
  primary key (info_id),
  key idx_sys_logininfor_s  (status),
  key idx_sys_logininfor_lt (login_time)
) engine=innodb auto_increment=100 comment = '系统访问记录';
create table sys_job (
  job_id              bigint(20)    not null auto_increment    comment '任务ID',
  job_name            varchar(64)   default ''                 comment '任务名称',
  job_group           varchar(64)   default 'DEFAULT'          comment '任务组名',
  invoke_target       varchar(500)  not null                   comment '调用目标字符串',
  cron_expression     varchar(255)  default ''                 comment 'cron执行表达式',
  misfire_policy      varchar(20)   default '3'                comment '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  concurrent          char(1)       default '1'                comment '是否并发执行（0允许 1禁止）',
  status              char(1)       default '0'                comment '状态（0正常 1暂停）',
  create_by           varchar(64)   default ''                 comment '创建者',
  create_time         datetime                                 comment '创建时间',
  update_by           varchar(64)   default ''                 comment '更新者',
  update_time         datetime                                 comment '更新时间',
  remark              varchar(500)  default ''                 comment '备注信息',
  primary key (job_id, job_name, job_group)
) engine=innodb auto_increment=100 comment = '定时任务调度表';
create table sys_job_log (
  job_log_id          bigint(20)     not null auto_increment    comment '任务日志ID',
  job_name            varchar(64)    not null                   comment '任务名称',
  job_group           varchar(64)    not null                   comment '任务组名',
  invoke_target       varchar(500)   not null                   comment '调用目标字符串',
  job_message         varchar(500)                              comment '日志信息',
  status              char(1)        default '0'                comment '执行状态（0正常 1失败）',
  exception_info      varchar(2000)  default ''                 comment '异常信息',
  create_time         datetime                                  comment '创建时间',
  primary key (job_log_id)
) engine=innodb comment = '定时任务调度日志表';
create table sys_notice (
  notice_id         int(4)          not null auto_increment    comment '公告ID',
  notice_title      varchar(50)     not null                   comment '公告标题',
  notice_type       char(1)         not null                   comment '公告类型（1通知 2公告）',
  notice_content    longblob        default null               comment '公告内容',
  status            char(1)         default '0'                comment '公告状态（0正常 1关闭）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(255)    default null               comment '备注',
  primary key (notice_id)
) engine=innodb auto_increment=10 comment = '通知公告表';
create table gen_table (
  table_id          bigint(20)      not null auto_increment    comment '编号',
  table_name        varchar(200)    default ''                 comment '表名称',
  table_comment     varchar(500)    default ''                 comment '表描述',
  sub_table_name    varchar(64)     default null               comment '关联子表的表名',
  sub_table_fk_name varchar(64)     default null               comment '子表关联的外键名',
  class_name        varchar(100)    default ''                 comment '实体类名称',
  tpl_category      varchar(200)    default 'crud'             comment '使用的模板（crud单表操作 tree树表操作）',
  tpl_web_type      varchar(30)     default ''                 comment '前端模板类型（element-ui模版 element-plus模版）',
  package_name      varchar(100)                               comment '生成包路径',
  module_name       varchar(30)                                comment '生成模块名',
  business_name     varchar(30)                                comment '生成业务名',
  function_name     varchar(50)                                comment '生成功能名',
  function_author   varchar(50)                                comment '生成功能作者',
  gen_type          char(1)         default '0'                comment '生成代码方式（0zip压缩包 1自定义路径）',
  gen_path          varchar(200)    default '/'                comment '生成路径（不填默认项目路径）',
  options           varchar(1000)                              comment '其它生成选项',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (table_id)
) engine=innodb auto_increment=1 comment = '代码生成业务表';
create table gen_table_column (
  column_id         bigint(20)      not null auto_increment    comment '编号',
  table_id          bigint(20)                                 comment '归属表编号',
  column_name       varchar(200)                               comment '列名称',
  column_comment    varchar(500)                               comment '列描述',
  column_type       varchar(100)                               comment '列类型',
  java_type         varchar(500)                               comment 'JAVA类型',
  java_field        varchar(200)                               comment 'JAVA字段名',
  is_pk             char(1)                                    comment '是否主键（1是）',
  is_increment      char(1)                                    comment '是否自增（1是）',
  is_required       char(1)                                    comment '是否必填（1是）',
  is_insert         char(1)                                    comment '是否为插入字段（1是）',
  is_edit           char(1)                                    comment '是否编辑字段（1是）',
  is_list           char(1)                                    comment '是否列表字段（1是）',
  is_query          char(1)                                    comment '是否查询字段（1是）',
  query_type        varchar(200)    default 'EQ'               comment '查询方式（等于、不等于、大于、小于、范围）',
  html_type         varchar(200)                               comment '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  dict_type         varchar(200)    default ''                 comment '字典类型',
  sort              int                                        comment '排序',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (column_id)
) engine=innodb auto_increment=1 comment = '代码生成业务表字段';
insert into sys_menu values('1', '系统管理', '0', '1', 'system',           null, '', '', 1, 0, 'M', '0', '0', '', 'system',   'admin', sysdate(), '', null, '系统管理目录');
insert into sys_menu values('2', '系统监控', '0', '2', 'monitor',          null, '', '', 1, 0, 'M', '0', '0', '', 'monitor',  'admin', sysdate(), '', null, '系统监控目录');
insert into sys_menu values('3', '系统工具', '0', '3', 'tool',             null, '', '', 1, 0, 'M', '0', '0', '', 'tool',     'admin', sysdate(), '', null, '系统工具目录');
insert into sys_menu values('4', '若依官网', '0', '4', 'http://ruoyi.vip', null, '', '', 0, 0, 'M', '0', '0', '', 'guide',    'admin', sysdate(), '', null, '若依官网地址');
insert into sys_menu values('100',  '用户管理', '1',   '1', 'user',       'system/user/index',        '', '', 1, 0, 'C', '0', '0', 'system:user:list',        'user',          'admin', sysdate(), '', null, '用户管理菜单');
insert into sys_menu values('101',  '角色管理', '1',   '2', 'role',       'system/role/index',        '', '', 1, 0, 'C', '0', '0', 'system:role:list',        'peoples',       'admin', sysdate(), '', null, '角色管理菜单');
insert into sys_menu values('102',  '菜单管理', '1',   '3', 'menu',       'system/menu/index',        '', '', 1, 0, 'C', '0', '0', 'system:menu:list',        'tree-table',    'admin', sysdate(), '', null, '菜单管理菜单');
insert into sys_menu values('103',  '部门管理', '1',   '4', 'dept',       'system/dept/index',        '', '', 1, 0, 'C', '0', '0', 'system:dept:list',        'tree',          'admin', sysdate(), '', null, '部门管理菜单');
insert into sys_menu values('104',  '岗位管理', '1',   '5', 'post',       'system/post/index',        '', '', 1, 0, 'C', '0', '0', 'system:post:list',        'post',          'admin', sysdate(), '', null, '岗位管理菜单');
insert into sys_menu values('105',  '字典管理', '1',   '6', 'dict',       'system/dict/index',        '', '', 1, 0, 'C', '0', '0', 'system:dict:list',        'dict',          'admin', sysdate(), '', null, '字典管理菜单');
insert into sys_menu values('106',  '参数设置', '1',   '7', 'config',     'system/config/index',      '', '', 1, 0, 'C', '0', '0', 'system:config:list',      'edit',          'admin', sysdate(), '', null, '参数设置菜单');
insert into sys_menu values('107',  '通知公告', '1',   '8', 'notice',     'system/notice/index',      '', '', 1, 0, 'C', '0', '0', 'system:notice:list',      'message',       'admin', sysdate(), '', null, '通知公告菜单');
insert into sys_menu values('108',  '日志管理', '1',   '9', 'log',        '',                         '', '', 1, 0, 'M', '0', '0', '',                        'log',           'admin', sysdate(), '', null, '日志管理菜单');
insert into sys_menu values('109',  '在线用户', '2',   '1', 'online',     'monitor/online/index',     '', '', 1, 0, 'C', '0', '0', 'monitor:online:list',     'online',        'admin', sysdate(), '', null, '在线用户菜单');
insert into sys_menu values('110',  '定时任务', '2',   '2', 'job',        'monitor/job/index',        '', '', 1, 0, 'C', '0', '0', 'monitor:job:list',        'job',           'admin', sysdate(), '', null, '定时任务菜单');
insert into sys_menu values('111',  '数据监控', '2',   '3', 'druid',      'monitor/druid/index',      '', '', 1, 0, 'C', '0', '0', 'monitor:druid:list',      'druid',         'admin', sysdate(), '', null, '数据监控菜单');
insert into sys_menu values('112',  '服务监控', '2',   '4', 'server',     'monitor/server/index',     '', '', 1, 0, 'C', '0', '0', 'monitor:server:list',     'server',        'admin', sysdate(), '', null, '服务监控菜单');
insert into sys_menu values('113',  '缓存监控', '2',   '5', 'cache',      'monitor/cache/index',      '', '', 1, 0, 'C', '0', '0', 'monitor:cache:list',      'redis',         'admin', sysdate(), '', null, '缓存监控菜单');
insert into sys_menu values('114',  '缓存列表', '2',   '6', 'cacheList',  'monitor/cache/list',       '', '', 1, 0, 'C', '0', '0', 'monitor:cache:list',      'redis-list',    'admin', sysdate(), '', null, '缓存列表菜单');
insert into sys_menu values('115',  '表单构建', '3',   '1', 'build',      'tool/build/index',         '', '', 1, 0, 'C', '0', '0', 'tool:build:list',         'build',         'admin', sysdate(), '', null, '表单构建菜单');
insert into sys_menu values('116',  '代码生成', '3',   '2', 'gen',        'tool/gen/index',           '', '', 1, 0, 'C', '0', '0', 'tool:gen:list',           'code',          'admin', sysdate(), '', null, '代码生成菜单');
insert into sys_menu values('117',  '系统接口', '3',   '3', 'swagger',    'tool/swagger/index',       '', '', 1, 0, 'C', '0', '0', 'tool:swagger:list',       'swagger',       'admin', sysdate(), '', null, '系统接口菜单');
insert into sys_menu values('500',  '操作日志', '108', '1', 'operlog',    'monitor/operlog/index',    '', '', 1, 0, 'C', '0', '0', 'monitor:operlog:list',    'form',          'admin', sysdate(), '', null, '操作日志菜单');
insert into sys_menu values('501',  '登录日志', '108', '2', 'logininfor', 'monitor/logininfor/index', '', '', 1, 0, 'C', '0', '0', 'monitor:logininfor:list', 'logininfor',    'admin', sysdate(), '', null, '登录日志菜单');
insert into sys_menu values('1000', '用户查询', '100', '1',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:query',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1001', '用户新增', '100', '2',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:add',            '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1002', '用户修改', '100', '3',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:edit',           '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1003', '用户删除', '100', '4',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:remove',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1004', '用户导出', '100', '5',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:export',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1005', '用户导入', '100', '6',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:import',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1006', '重置密码', '100', '7',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:resetPwd',       '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1007', '角色查询', '101', '1',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:query',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1008', '角色新增', '101', '2',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:add',            '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1009', '角色修改', '101', '3',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:edit',           '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1010', '角色删除', '101', '4',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:remove',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1011', '角色导出', '101', '5',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:export',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1012', '菜单查询', '102', '1',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:query',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1013', '菜单新增', '102', '2',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:add',            '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1014', '菜单修改', '102', '3',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:edit',           '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1015', '菜单删除', '102', '4',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:remove',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1016', '部门查询', '103', '1',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:dept:query',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1017', '部门新增', '103', '2',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:dept:add',            '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1018', '部门修改', '103', '3',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:dept:edit',           '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1019', '部门删除', '103', '4',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:dept:remove',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1020', '岗位查询', '104', '1',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:post:query',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1021', '岗位新增', '104', '2',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:post:add',            '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1022', '岗位修改', '104', '3',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:post:edit',           '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1023', '岗位删除', '104', '4',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:post:remove',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1024', '岗位导出', '104', '5',  '', '', '', '', 1, 0, 'F', '0', '0', 'system:post:export',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1025', '字典查询', '105', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:dict:query',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1026', '字典新增', '105', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:dict:add',            '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1027', '字典修改', '105', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:dict:edit',           '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1028', '字典删除', '105', '4', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:dict:remove',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1029', '字典导出', '105', '5', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:dict:export',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1030', '参数查询', '106', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:config:query',        '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1031', '参数新增', '106', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:config:add',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1032', '参数修改', '106', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:config:edit',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1033', '参数删除', '106', '4', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:config:remove',       '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1034', '参数导出', '106', '5', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:config:export',       '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1035', '公告查询', '107', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:notice:query',        '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1036', '公告新增', '107', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:notice:add',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1037', '公告修改', '107', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:notice:edit',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1038', '公告删除', '107', '4', '#', '', '', '', 1, 0, 'F', '0', '0', 'system:notice:remove',       '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1039', '操作查询', '500', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:query',      '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1040', '操作删除', '500', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:remove',     '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1041', '日志导出', '500', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:export',     '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1042', '登录查询', '501', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:query',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1043', '登录删除', '501', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:remove',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1044', '日志导出', '501', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:export',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1045', '账户解锁', '501', '4', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:unlock',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1046', '在线查询', '109', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:online:query',       '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1047', '批量强退', '109', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:online:batchLogout', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1048', '单条强退', '109', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:online:forceLogout', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1049', '任务查询', '110', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:query',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1050', '任务新增', '110', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:add',            '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1051', '任务修改', '110', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:edit',           '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1052', '任务删除', '110', '4', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:remove',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1053', '状态修改', '110', '5', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:changeStatus',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1054', '任务导出', '110', '6', '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:export',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1055', '生成查询', '116', '1', '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:query',             '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1056', '生成修改', '116', '2', '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:edit',              '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1057', '生成删除', '116', '3', '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:remove',            '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1058', '导入代码', '116', '4', '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:import',            '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1059', '预览代码', '116', '5', '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:preview',           '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1060', '生成代码', '116', '6', '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:code',              '#', 'admin', sysdate(), '', null, '');
insert into sys_dict_type values(1,  '用户性别', 'sys_user_sex',        '0', 'admin', sysdate(), '', null, '用户性别列表');
insert into sys_dict_type values(2,  '菜单状态', 'sys_show_hide',       '0', 'admin', sysdate(), '', null, '菜单状态列表');
insert into sys_dict_type values(3,  '系统开关', 'sys_normal_disable',  '0', 'admin', sysdate(), '', null, '系统开关列表');
insert into sys_dict_type values(4,  '任务状态', 'sys_job_status',      '0', 'admin', sysdate(), '', null, '任务状态列表');
insert into sys_dict_type values(5,  '任务分组', 'sys_job_group',       '0', 'admin', sysdate(), '', null, '任务分组列表');
insert into sys_dict_type values(6,  '系统是否', 'sys_yes_no',          '0', 'admin', sysdate(), '', null, '系统是否列表');
insert into sys_dict_type values(7,  '通知类型', 'sys_notice_type',     '0', 'admin', sysdate(), '', null, '通知类型列表');
insert into sys_dict_type values(8,  '通知状态', 'sys_notice_status',   '0', 'admin', sysdate(), '', null, '通知状态列表');
insert into sys_dict_type values(9,  '操作类型', 'sys_oper_type',       '0', 'admin', sysdate(), '', null, '操作类型列表');
insert into sys_dict_type values(10, '系统状态', 'sys_common_status',   '0', 'admin', sysdate(), '', null, '登录状态列表');
insert into sys_dict_data values(1,  1,  '男',       '0',       'sys_user_sex',        '',   '',        'Y', '0', 'admin', sysdate(), '', null, '性别男');
insert into sys_dict_data values(2,  2,  '女',       '1',       'sys_user_sex',        '',   '',        'N', '0', 'admin', sysdate(), '', null, '性别女');
insert into sys_dict_data values(3,  3,  '未知',     '2',       'sys_user_sex',        '',   '',        'N', '0', 'admin', sysdate(), '', null, '性别未知');
insert into sys_dict_data values(4,  1,  '显示',     '0',       'sys_show_hide',       '',   'primary', 'Y', '0', 'admin', sysdate(), '', null, '显示菜单');
insert into sys_dict_data values(5,  2,  '隐藏',     '1',       'sys_show_hide',       '',   'danger',  'N', '0', 'admin', sysdate(), '', null, '隐藏菜单');
insert into sys_dict_data values(6,  1,  '正常',     '0',       'sys_normal_disable',  '',   'primary', 'Y', '0', 'admin', sysdate(), '', null, '正常状态');
insert into sys_dict_data values(7,  2,  '停用',     '1',       'sys_normal_disable',  '',   'danger',  'N', '0', 'admin', sysdate(), '', null, '停用状态');
insert into sys_dict_data values(8,  1,  '正常',     '0',       'sys_job_status',      '',   'primary', 'Y', '0', 'admin', sysdate(), '', null, '正常状态');
insert into sys_dict_data values(9,  2,  '暂停',     '1',       'sys_job_status',      '',   'danger',  'N', '0', 'admin', sysdate(), '', null, '停用状态');
insert into sys_dict_data values(10, 1,  '默认',     'DEFAULT', 'sys_job_group',       '',   '',        'Y', '0', 'admin', sysdate(), '', null, '默认分组');
insert into sys_dict_data values(11, 2,  '系统',     'SYSTEM',  'sys_job_group',       '',   '',        'N', '0', 'admin', sysdate(), '', null, '系统分组');
insert into sys_dict_data values(12, 1,  '是',       'Y',       'sys_yes_no',          '',   'primary', 'Y', '0', 'admin', sysdate(), '', null, '系统默认是');
insert into sys_dict_data values(13, 2,  '否',       'N',       'sys_yes_no',          '',   'danger',  'N', '0', 'admin', sysdate(), '', null, '系统默认否');
insert into sys_dict_data values(14, 1,  '通知',     '1',       'sys_notice_type',     '',   'warning', 'Y', '0', 'admin', sysdate(), '', null, '通知');
insert into sys_dict_data values(15, 2,  '公告',     '2',       'sys_notice_type',     '',   'success', 'N', '0', 'admin', sysdate(), '', null, '公告');
insert into sys_dict_data values(16, 1,  '正常',     '0',       'sys_notice_status',   '',   'primary', 'Y', '0', 'admin', sysdate(), '', null, '正常状态');
insert into sys_dict_data values(17, 2,  '关闭',     '1',       'sys_notice_status',   '',   'danger',  'N', '0', 'admin', sysdate(), '', null, '关闭状态');
insert into sys_dict_data values(18, 99, '其他',     '0',       'sys_oper_type',       '',   'info',    'N', '0', 'admin', sysdate(), '', null, '其他操作');
insert into sys_dict_data values(19, 1,  '新增',     '1',       'sys_oper_type',       '',   'info',    'N', '0', 'admin', sysdate(), '', null, '新增操作');
insert into sys_dict_data values(20, 2,  '修改',     '2',       'sys_oper_type',       '',   'info',    'N', '0', 'admin', sysdate(), '', null, '修改操作');
insert into sys_dict_data values(21, 3,  '删除',     '3',       'sys_oper_type',       '',   'danger',  'N', '0', 'admin', sysdate(), '', null, '删除操作');
insert into sys_dict_data values(22, 4,  '授权',     '4',       'sys_oper_type',       '',   'primary', 'N', '0', 'admin', sysdate(), '', null, '授权操作');
insert into sys_dict_data values(23, 5,  '导出',     '5',       'sys_oper_type',       '',   'warning', 'N', '0', 'admin', sysdate(), '', null, '导出操作');
insert into sys_dict_data values(24, 6,  '导入',     '6',       'sys_oper_type',       '',   'warning', 'N', '0', 'admin', sysdate(), '', null, '导入操作');
insert into sys_dict_data values(25, 7,  '强退',     '7',       'sys_oper_type',       '',   'danger',  'N', '0', 'admin', sysdate(), '', null, '强退操作');
insert into sys_dict_data values(26, 8,  '生成代码', '8',       'sys_oper_type',       '',   'warning', 'N', '0', 'admin', sysdate(), '', null, '生成操作');
insert into sys_dict_data values(27, 9,  '清空数据', '9',       'sys_oper_type',       '',   'danger',  'N', '0', 'admin', sysdate(), '', null, '清空操作');
insert into sys_dict_data values(28, 1,  '成功',     '0',       'sys_common_status',   '',   'primary', 'N', '0', 'admin', sysdate(), '', null, '正常状态');
insert into sys_dict_data values(29, 2,  '失败',     '1',       'sys_common_status',   '',   'danger',  'N', '0', 'admin', sysdate(), '', null, '停用状态');
insert into sys_config values(1, '主框架页-默认皮肤样式名称',     'sys.index.skinName',            'skin-blue',     'Y', 'admin', sysdate(), '', null, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow' );
insert into sys_config values(2, '用户管理-账号初始密码',         'sys.user.initPassword',         '123456',        'Y', 'admin', sysdate(), '', null, '初始化密码 123456' );
insert into sys_config values(3, '主框架页-侧边栏主题',           'sys.index.sideTheme',           'theme-dark',    'Y', 'admin', sysdate(), '', null, '深色主题theme-dark，浅色主题theme-light' );
insert into sys_config values(4, '账号自助-验证码开关',           'sys.account.captchaEnabled',    'true',          'Y', 'admin', sysdate(), '', null, '是否开启验证码功能（true开启，false关闭）');
insert into sys_config values(5, '账号自助-是否开启用户注册功能', 'sys.account.registerUser',      'false',         'Y', 'admin', sysdate(), '', null, '是否开启注册用户功能（true开启，false关闭）');
insert into sys_config values(6, '用户登录-黑名单列表',           'sys.login.blackIPList',         '',              'Y', 'admin', sysdate(), '', null, '设置登录IP黑名单限制，多个匹配项以;分隔，支持匹配（*通配、网段）');
CREATE TABLE child_menu (child_menu_id BIGINT PRIMARY KEY AUTO_INCREMENT,child_menu_class VARCHAR(64),child_menu_name VARCHAR(64),order_num INT DEFAULT 0,child_menu_type BIGINT,child_menu_label VARCHAR(128));
CREATE TABLE sys_role_child_menu (role_id BIGINT,child_menu_id BIGINT,PRIMARY KEY(role_id,child_menu_id));
CREATE TABLE student (student_id BIGINT PRIMARY KEY AUTO_INCREMENT,student_name VARCHAR(255),student_num VARCHAR(64),grades BIGINT,duties BIGINT,sex BIGINT,birthday DATETIME,id_card VARCHAR(255),native_place VARCHAR(255),nation VARCHAR(255),dormitory VARCHAR(255),face BIGINT,phone VARCHAR(255),oicq VARCHAR(255),we_chat VARCHAR(255),address VARCHAR(255),emergency_name1 VARCHAR(255),emergency_relation1 VARCHAR(255),emergency_work1 VARCHAR(255),emergency_phone1 VARCHAR(255),emergency_name2 VARCHAR(255),emergency_relation2 VARCHAR(255),emergency_work2 VARCHAR(255),emergency_phone2 VARCHAR(255),photo VARCHAR(255),life_photo VARCHAR(255),ismark VARCHAR(255),follow BIGINT,religion VARCHAR(255),create_time DATETIME,update_time DATETIME,UNIQUE KEY uk_student_num(student_num)) ENGINE=InnoDB;
CREATE TABLE student_mark(mark_id BIGINT PRIMARY KEY AUTO_INCREMENT,mark_content BIGINT,student_id BIGINT,create_time DATETIME,update_time DATETIME,UNIQUE KEY uk_student_mark(student_id,mark_content),FOREIGN KEY(student_id) REFERENCES student(student_id) ON DELETE CASCADE);
CREATE TABLE student_follow_up(follow_id BIGINT PRIMARY KEY AUTO_INCREMENT,student_id BIGINT,follow_image VARCHAR(255),follow_content TEXT,create_time DATETIME,update_time DATETIME,FOREIGN KEY(student_id) REFERENCES student(student_id) ON DELETE CASCADE);
INSERT INTO sys_dept(dept_id,parent_id,ancestors,dept_name,status,del_flag) VALUES(100,0,'0','演示学院','0','0');
INSERT INTO sys_role(role_id,role_name,role_key,role_sort,status) VALUES(1,'演示管理员','admin',1,'0'),(100,'一班辅导员','counselor',2,'0');
INSERT INTO sys_user(user_id,dept_id,user_name,nick_name,password,status,del_flag) VALUES(1,100,'admin','演示管理员','$2b$12$6a2zvDsny65SgJ1bDwq1ve07KaENZEkwMnlhaf9qjXCoeRLcDJ3uq','0','0'),(100,100,'counselor','演示辅导员','$2b$12$6a2zvDsny65SgJ1bDwq1ve07KaENZEkwMnlhaf9qjXCoeRLcDJ3uq','0','0');
INSERT INTO sys_user_role VALUES(1,1),(100,100);
INSERT INTO sys_menu(menu_id,menu_name,parent_id,order_num,path,component,menu_type,perms,icon) VALUES(3000,'新生管理',0,0,'freshman',NULL,'M','','peoples'),(3001,'学生档案',3000,1,'student','system/student/index','C','system:student:list','user'),(3002,'跟进记录',3000,2,'follow-up','system/follow_up/index','C','system:follow_up:list','edit'),(3003,'标记管理',3000,3,'mark','system/mark/index','C','system:mark:list','tag'),(3004,'字段与班级配置',3000,4,'child-menu','system/child_menu/index','C','system:child_menu:list','list');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3100,'student-list',3001,'','F','system:student:list');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3101,'student-query',3001,'','F','system:student:query');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3102,'student-add',3001,'','F','system:student:add');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3103,'student-edit',3001,'','F','system:student:edit');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3104,'student-remove',3001,'','F','system:student:remove');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3105,'student-export',3001,'','F','system:student:export');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3106,'student-import',3001,'','F','system:student:import');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3107,'follow_up-list',3001,'','F','system:follow_up:list');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3108,'follow_up-query',3001,'','F','system:follow_up:query');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3109,'follow_up-add',3001,'','F','system:follow_up:add');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3110,'follow_up-edit',3001,'','F','system:follow_up:edit');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3111,'follow_up-remove',3001,'','F','system:follow_up:remove');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3112,'follow_up-export',3001,'','F','system:follow_up:export');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3113,'follow_up-import',3001,'','F','system:follow_up:import');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3114,'mark-list',3001,'','F','system:mark:list');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3115,'mark-query',3001,'','F','system:mark:query');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3116,'mark-add',3001,'','F','system:mark:add');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3117,'mark-edit',3001,'','F','system:mark:edit');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3118,'mark-remove',3001,'','F','system:mark:remove');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3119,'mark-export',3001,'','F','system:mark:export');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3120,'mark-import',3001,'','F','system:mark:import');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3121,'child_menu-list',3001,'','F','system:child_menu:list');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3122,'child_menu-query',3001,'','F','system:child_menu:query');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3123,'child_menu-add',3001,'','F','system:child_menu:add');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3124,'child_menu-edit',3001,'','F','system:child_menu:edit');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3125,'child_menu-remove',3001,'','F','system:child_menu:remove');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3126,'child_menu-export',3001,'','F','system:child_menu:export');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3127,'child_menu-import',3001,'','F','system:child_menu:import');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3128,'sys_role_child_menu-list',3001,'','F','system:sys_role_child_menu:list');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3129,'sys_role_child_menu-query',3001,'','F','system:sys_role_child_menu:query');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3130,'sys_role_child_menu-add',3001,'','F','system:sys_role_child_menu:add');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3131,'sys_role_child_menu-edit',3001,'','F','system:sys_role_child_menu:edit');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3132,'sys_role_child_menu-remove',3001,'','F','system:sys_role_child_menu:remove');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3133,'sys_role_child_menu-export',3001,'','F','system:sys_role_child_menu:export');
INSERT INTO sys_menu(menu_id,menu_name,parent_id,path,menu_type,perms) VALUES(3134,'sys_role_child_menu-import',3001,'','F','system:sys_role_child_menu:import');
INSERT INTO sys_role_menu SELECT 100,menu_id FROM sys_menu WHERE menu_id IN(3000,3001) OR (menu_id>=3100 AND menu_id<3107);
INSERT INTO child_menu VALUES(1,'Grades','1',1,3,'软件工程1班（演示）');
INSERT INTO child_menu VALUES(2,'Grades','2',2,3,'软件工程2班（演示）');
INSERT INTO child_menu VALUES(3,'Fields','studentName',3,1,'studentName');
INSERT INTO child_menu VALUES(4,'Fields','studentName',4,2,'studentName');
INSERT INTO child_menu VALUES(5,'Fields','studentNum',5,1,'studentNum');
INSERT INTO child_menu VALUES(6,'Fields','studentNum',6,2,'studentNum');
INSERT INTO child_menu VALUES(7,'Fields','grades',7,1,'grades');
INSERT INTO child_menu VALUES(8,'Fields','grades',8,2,'grades');
INSERT INTO child_menu VALUES(9,'Fields','duties',9,1,'duties');
INSERT INTO child_menu VALUES(10,'Fields','duties',10,2,'duties');
INSERT INTO child_menu VALUES(11,'Fields','sex',11,1,'sex');
INSERT INTO child_menu VALUES(12,'Fields','sex',12,2,'sex');
INSERT INTO child_menu VALUES(13,'Fields','birthday',13,1,'birthday');
INSERT INTO child_menu VALUES(14,'Fields','birthday',14,2,'birthday');
INSERT INTO child_menu VALUES(15,'Fields','idCard',15,1,'idCard');
INSERT INTO child_menu VALUES(16,'Fields','idCard',16,2,'idCard');
INSERT INTO child_menu VALUES(17,'Fields','nativePlace',17,1,'nativePlace');
INSERT INTO child_menu VALUES(18,'Fields','nativePlace',18,2,'nativePlace');
INSERT INTO child_menu VALUES(19,'Fields','nation',19,1,'nation');
INSERT INTO child_menu VALUES(20,'Fields','nation',20,2,'nation');
INSERT INTO child_menu VALUES(21,'Fields','dormitory',21,1,'dormitory');
INSERT INTO child_menu VALUES(22,'Fields','dormitory',22,2,'dormitory');
INSERT INTO child_menu VALUES(23,'Fields','face',23,1,'face');
INSERT INTO child_menu VALUES(24,'Fields','face',24,2,'face');
INSERT INTO child_menu VALUES(25,'Fields','phone',25,1,'phone');
INSERT INTO child_menu VALUES(26,'Fields','phone',26,2,'phone');
INSERT INTO child_menu VALUES(27,'Fields','oicq',27,1,'oicq');
INSERT INTO child_menu VALUES(28,'Fields','oicq',28,2,'oicq');
INSERT INTO child_menu VALUES(29,'Fields','weChat',29,1,'weChat');
INSERT INTO child_menu VALUES(30,'Fields','weChat',30,2,'weChat');
INSERT INTO child_menu VALUES(31,'Fields','address',31,1,'address');
INSERT INTO child_menu VALUES(32,'Fields','address',32,2,'address');
INSERT INTO sys_role_child_menu SELECT 1,child_menu_id FROM child_menu;
INSERT INTO sys_role_child_menu SELECT 100,child_menu_id FROM child_menu WHERE child_menu_class<>'Grades' OR child_menu_name='1';
INSERT INTO sys_dict_type(dict_name,dict_type,status) VALUES('student_grades','student_grades','0');
INSERT INTO sys_dict_data(dict_sort,dict_label,dict_value,dict_type,status) VALUES(1,'软件工程1班（演示）','1','student_grades','0');
INSERT INTO sys_dict_data(dict_sort,dict_label,dict_value,dict_type,status) VALUES(2,'软件工程2班（演示）','2','student_grades','0');
INSERT INTO sys_dict_type(dict_name,dict_type,status) VALUES('student_duties','student_duties','0');
INSERT INTO sys_dict_data(dict_sort,dict_label,dict_value,dict_type,status) VALUES(1,'普通学生','1','student_duties','0');
INSERT INTO sys_dict_data(dict_sort,dict_label,dict_value,dict_type,status) VALUES(2,'班长','2','student_duties','0');
INSERT INTO sys_dict_type(dict_name,dict_type,status) VALUES('student_mark','student_mark','0');
INSERT INTO sys_dict_data(dict_sort,dict_label,dict_value,dict_type,status) VALUES(1,'需持续关注','1','student_mark','0');
INSERT INTO sys_dict_data(dict_sort,dict_label,dict_value,dict_type,status) VALUES(2,'资助咨询','2','student_mark','0');
INSERT INTO sys_dict_data(dict_sort,dict_label,dict_value,dict_type,status) VALUES(3,'学业支持','3','student_mark','0');
INSERT INTO sys_dict_type(dict_name,dict_type,status) VALUES('student_face','student_face','0');
INSERT INTO sys_dict_data(dict_sort,dict_label,dict_value,dict_type,status) VALUES(1,'群众','1','student_face','0');
INSERT INTO sys_dict_data(dict_sort,dict_label,dict_value,dict_type,status) VALUES(2,'共青团员','2','student_face','0');
INSERT INTO student(student_id,student_name,student_num,grades,duties,sex,id_card,dormitory,phone,nation,native_place,address,follow,ismark,religion,create_time) VALUES(1,'演示同学甲','DEMO2024001',1,1,1,'DEMO-ID-001','演示宿舍A-101','00000000001','演示民族','演示籍贯','虚构地址1',2,'','无',NOW());
INSERT INTO student(student_id,student_name,student_num,grades,duties,sex,id_card,dormitory,phone,nation,native_place,address,follow,ismark,religion,create_time) VALUES(2,'演示同学乙','DEMO2024002',1,1,0,'DEMO-ID-002','演示宿舍A-102','00000000002','演示民族','演示籍贯','虚构地址2',2,'','无',NOW());
INSERT INTO student(student_id,student_name,student_num,grades,duties,sex,id_card,dormitory,phone,nation,native_place,address,follow,ismark,religion,create_time) VALUES(3,'演示同学丙','DEMO2024003',1,1,1,'DEMO-ID-003','演示宿舍A-103','00000000003','演示民族','演示籍贯','虚构地址3',2,'','无',NOW());
INSERT INTO student(student_id,student_name,student_num,grades,duties,sex,id_card,dormitory,phone,nation,native_place,address,follow,ismark,religion,create_time) VALUES(4,'演示同学丁','DEMO2024004',1,1,0,'DEMO-ID-004','演示宿舍A-104','00000000004','演示民族','演示籍贯','虚构地址4',2,'','无',NOW());
INSERT INTO student(student_id,student_name,student_num,grades,duties,sex,id_card,dormitory,phone,nation,native_place,address,follow,ismark,religion,create_time) VALUES(5,'演示同学戊','DEMO2024005',2,1,1,'DEMO-ID-005','演示宿舍A-105','00000000005','演示民族','演示籍贯','虚构地址5',2,'','无',NOW());
INSERT INTO student(student_id,student_name,student_num,grades,duties,sex,id_card,dormitory,phone,nation,native_place,address,follow,ismark,religion,create_time) VALUES(6,'演示同学己','DEMO2024006',2,1,0,'DEMO-ID-006','演示宿舍A-106','00000000006','演示民族','演示籍贯','虚构地址6',2,'','无',NOW());
INSERT INTO student_mark(student_id,mark_content,create_time) VALUES(1,1,NOW()),(2,2,NOW());
UPDATE student SET ismark='需持续关注' WHERE student_id=1;
UPDATE student SET ismark='资助咨询' WHERE student_id=2;
INSERT INTO student_follow_up(student_id,follow_content,create_time) VALUES(1,'已完成入学适应沟通，约定下周再次跟进。此记录为虚构演示。',NOW());
UPDATE student SET follow=1 WHERE student_id=1;


