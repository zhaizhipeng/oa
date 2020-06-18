
# 组织机构表
DROP TABLE IF EXISTS sys_org_info;
CREATE TABLE `sys_org_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `org_id` varchar(20) NOT NULL DEFAULT '' COMMENT '机构ID',
  `org_name` varchar(64) NOT NULL DEFAULT '' COMMENT '机构名称',
  `father_org_id` varchar(20) NOT NULL DEFAULT '' COMMENT '上级机构ID',
  `father_org_name` varchar(64) NOT NULL DEFAULT '' COMMENT '上级机构名称',
  `disabled` int(11) DEFAULT '1' COMMENT '禁用/启用 0：禁用、1：启用',
  `misc_desc` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '是否有效 0：无效、1：有效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_oper_id` bigint(20) NOT NULL COMMENT '创建人id',
  `create_oper_name` varchar(50) NOT NULL COMMENT '创建人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_oper_id` bigint(20) NOT NULL COMMENT '修改人id',
  `update_oper_name` varchar(50) NOT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

# 用户表
DROP TABLE IF EXISTS sys_user;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mobile_phone` varchar(11) NOT NULL COMMENT '手机号（登录名）',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `pwd_valid_date` date DEFAULT NULL COMMENT '密码有效截止日期',
  `mail` varchar(30) NOT NULL DEFAULT '' COMMENT '邮箱',
  `org_id` varchar(20) NOT NULL COMMENT '所属组织机构ID',
  `user_name` varchar(20) NOT NULL COMMENT '用户名',
  `disabled` int(11) DEFAULT '1' COMMENT '禁用/启用 0：禁用、1：启用',
  `last_login_date` datetime DEFAULT NULL COMMENT '上次登录时间',
  `misc_desc` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '是否有效 0：无效、1：有效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_oper_id` bigint(20) NOT NULL COMMENT '创建人id',
  `create_oper_name` varchar(50) NOT NULL COMMENT '创建人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_oper_id` bigint(20) NOT NULL COMMENT '修改人id',
  `update_oper_name` varchar(50) NOT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

# 角色表
DROP TABLE IF EXISTS sys_role;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_cn_name` varchar(30) DEFAULT NULL COMMENT '角色中文名称',
  `role_en_name` varchar(30) DEFAULT NULL COMMENT '角色英文名称',
  `disabled` int(11) NOT NULL DEFAULT '1' COMMENT '禁用/启用 0：禁用、1：启用',
  `misc_desc` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '是否有效 0：无效、1：有效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_oper_id` bigint(20) NOT NULL COMMENT '创建人id',
  `create_oper_name` varchar(50) NOT NULL COMMENT '创建人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_oper_id` bigint(20) NOT NULL COMMENT '修改人id',
  `update_oper_name` varchar(50) NOT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

# 用户角色映射表
DROP TABLE IF EXISTS sys_user_role;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `misc_desc` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '是否有效 0：无效、1：有效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_oper_id` bigint(20) NOT NULL COMMENT '创建人id',
  `create_oper_name` varchar(50) NOT NULL COMMENT '创建人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_oper_id` bigint(20) NOT NULL COMMENT '修改人id',
  `update_oper_name` varchar(50) NOT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`id`),
  KEY `index_user_id` (`user_id`),
  KEY `index_role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色映射表';

# 权限资源表
DROP TABLE IF EXISTS sys_resources;
CREATE TABLE `sys_resources` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `resources_name` varchar(20) NOT NULL COMMENT '资源名称',
  `resources_key` varchar(32) NOT NULL DEFAULT '' COMMENT '资源key',
  `resources_type` int(11) NOT NULL DEFAULT '0' COMMENT '资源类型 1：新增、2：删除 3：修改、4 查询',
  `url_type` int(11) NOT NULL DEFAULT '0' COMMENT 'url类型 1：静态资源、2：按钮',
  `father_id` bigint(20) NOT NULL COMMENT '父权限id',
  `misc_desc` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '是否有效 0：无效、1：有效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_oper_id` bigint(20) NOT NULL COMMENT '创建人id',
  `create_oper_name` varchar(50) NOT NULL COMMENT '创建人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_oper_id` bigint(20) NOT NULL COMMENT '修改人id',
  `update_oper_name` varchar(50) NOT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限资源表';

# 角色资源映射表
DROP TABLE IF EXISTS sys_role_resources;
CREATE TABLE `sys_role_resources` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `resources_id` bigint(20) NOT NULL COMMENT '资源id',
  `misc_desc` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '是否有效 0：无效、1：有效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_oper_id` bigint(20) NOT NULL COMMENT '创建人id',
  `create_oper_name` varchar(50) NOT NULL COMMENT '创建人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_oper_id` bigint(20) NOT NULL COMMENT '修改人id',
  `update_oper_name` varchar(50) NOT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`id`),
  KEY `index_role_id` (`role_id`),
  KEY `index_resources_id` (`resources_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源映射表';

# 角色资源日志表
DROP TABLE IF EXISTS sys_operate_log;
CREATE TABLE `sys_operate_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `operate_type` int(3) NOT NULL COMMENT '操作类型 1：新增角色、2：修改角色、3：启用角色、4：禁用角色、5：编辑用户角色',
  `old_role_ids` varchar(2048) NOT NULL COMMENT '旧角色ID（逗号隔开）',
  `role_ids` varchar(2048) NOT NULL COMMENT '角色ID（逗号隔开）',
  `old_role_names` varchar(2048) NOT NULL DEFAULT '' COMMENT '旧角色名称（逗号隔开）',
  `role_names` varchar(2048) NOT NULL DEFAULT '' COMMENT '角色名称（逗号隔开）',
  `old_resources_names` varchar(2048) NOT NULL DEFAULT '' COMMENT '旧权限名称（逗号隔开）',
  `resources_names` varchar(2048) NOT NULL DEFAULT '' COMMENT '权限名称（逗号隔开）',
  `user_name` varchar(200) NOT NULL DEFAULT '' COMMENT '用户名',
  `content` varchar(2048) NOT NULL DEFAULT '' COMMENT '操作内容描述',
  `misc_desc` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '是否有效 0：无效、1：有效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_oper_id` bigint(20) NOT NULL COMMENT '创建人id',
  `create_oper_name` varchar(50) NOT NULL COMMENT '创建人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_oper_id` bigint(20) NOT NULL COMMENT '修改人id',
  `update_oper_name` varchar(50) NOT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限操作日志表';
