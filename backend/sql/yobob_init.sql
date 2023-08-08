/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 80100
Source Host           : localhost:3306
Source Database       : hupi_db

Target Server Type    : MYSQL
Target Server Version : 80100
File Encoding         : 65001

Date: 2023-08-08 17:18:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `sn` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `parentId` bigint DEFAULT NULL COMMENT '父级id',
  `ancestors` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '祖级列表',
  `isBan` tinyint NOT NULL DEFAULT '0' COMMENT '状态 0 正常 1 删除',
  `leader` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '负责人',
  `phone` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '手机号',
  `email` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '邮箱',
  `create_by` varchar(256) DEFAULT NULL COMMENT '创建人',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateBy` varchar(256) DEFAULT NULL COMMENT '更新人',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '0 正常 1 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1 COMMENT='部门表';

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', 'Yobob科技', 'hupi', '0', '0', '0', 'admin', '15257222873', '95736614@qq.com', null, '2023-02-13 22:00:06', null, '2023-02-15 00:36:44', '0');
INSERT INTO `department` VALUES ('2', '浙江总公司', null, '1', '0,1', '0', null, null, null, null, '2023-02-13 22:01:32', null, '2023-02-21 11:14:02', '1');
INSERT INTO `department` VALUES ('3', '上海分公司', null, '1', '0,1', '0', null, null, null, null, '2023-02-13 22:01:59', null, '2023-02-15 00:44:42', '0');
INSERT INTO `department` VALUES ('4', '研发部门', null, '2', '0,1,2', '0', null, null, null, null, '2023-02-13 22:03:35', null, '2023-02-13 22:03:35', '0');
INSERT INTO `department` VALUES ('5', '市场部门', null, '2', '0,1,2', '0', null, null, null, null, '2023-02-13 22:03:50', null, '2023-02-17 14:06:23', '0');
INSERT INTO `department` VALUES ('6', '财务部门', null, '2', '0,1,2', '0', null, null, null, null, '2023-02-13 22:04:10', null, '2023-02-13 22:04:10', '0');
INSERT INTO `department` VALUES ('7', '运维部门', null, '2', '0,1,2', '0', null, null, null, null, '2023-02-13 22:04:20', null, '2023-02-13 22:04:20', '0');
INSERT INTO `department` VALUES ('8', '市场部门', null, '3', '0,1,3', '0', null, null, null, null, '2023-02-13 22:04:39', null, '2023-02-13 22:04:39', '0');
INSERT INTO `department` VALUES ('9', '财务部门', null, '3', '0,1,3', '0', null, null, null, null, '2023-02-13 22:04:53', '', '2023-02-20 00:38:49', '0');

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `md5` varchar(32) DEFAULT NULL,
  `path` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `upload_time` datetime(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of file
-- ----------------------------

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `isBan` tinyint(1) DEFAULT '0',
  `code` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `isDelete` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('1', '董事长', '0', 'ceo', null, null, '0');
INSERT INTO `post` VALUES ('2', '项目经理', '0', 'se', null, null, '1');
INSERT INTO `post` VALUES ('3', '人力资源', '0', 'hr', null, null, '1');
INSERT INTO `post` VALUES ('4', '首席技术官', '0', 'cto', null, null, '1');
INSERT INTO `post` VALUES ('5', '普通员工', '0', 'user', null, null, '0');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单主键ID',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `parent_id` bigint DEFAULT NULL COMMENT '父菜单ID',
  `order_num` int DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `code` varchar(100) DEFAULT '' COMMENT '权限标识',
  `isBan` tinyint(1) DEFAULT '0',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', 'Setting', '0', '1', '/main/sys', '', 'M', '', '0', '2022-07-04 14:56:29', '2022-07-04 14:56:31', '系统管理目录');
INSERT INTO `sys_menu` VALUES ('2', '集成工具', 'Briefcase', '0', '2', '/main/utils', '', 'M', '', '0', '2022-07-04 14:59:43', '2022-07-04 14:59:45', '业务管理目录');
INSERT INTO `sys_menu` VALUES ('3', '用户管理', 'user', '1', '1', '/main/sys/user', 'sys/user/index', 'C', 'system:user:list', '0', '2022-07-04 15:20:51', '2022-07-04 15:20:53', '用户管理菜单');
INSERT INTO `sys_menu` VALUES ('4', '角色管理', 'peoples', '1', '2', '/main/sys/role', 'sys/role/index', 'C', 'system:role:list', '0', '2022-07-04 15:23:35', '2022-07-04 15:23:39', '角色管理菜单');
INSERT INTO `sys_menu` VALUES ('5', '菜单管理', 'tree-\r\n        table', '1', '3', '/main/sys/menu', 'sys/menu/index', 'C', 'system:menu:list', '0', '2022-07-04 15:23:41', '2022-07-04 15:23:43', '菜单管理菜单');
INSERT INTO `sys_menu` VALUES ('6', '部门管理', 'tree', '1', '1', '/main/sys/department', 'sys/Department', 'C', '', '0', '2022-07-04 15:24:40', '2022-07-04 15:24:44', '部门管理菜单');
INSERT INTO `sys_menu` VALUES ('7', '岗位管理', 'post', '1', '2', '/main/sys/post', 'sys/Post', 'C', '', '0', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '岗位管理菜单');
INSERT INTO `sys_menu` VALUES ('8', '用户新增', '#', '3', '2', '', '', 'F', 'system:user:add', '0', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '添加用户按钮');
INSERT INTO `sys_menu` VALUES ('9', '用户修改', '#', '3', '3', '', '', 'F', 'system:user:edit', '0', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '修改用户按钮');
INSERT INTO `sys_menu` VALUES ('10', '用户删除', '#', '3', '4', '', '', 'F', 'system:user:delete', '0', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '删除用户按钮');
INSERT INTO `sys_menu` VALUES ('11', '分配角色', '#', '3', '5', '', '', 'F', 'system:user:role', '0', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '分配角色按钮');
INSERT INTO `sys_menu` VALUES ('12', '重置密码', '#', '3', '6', '', '', 'F', 'system:user:resetPwd', '0', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '重置密码按钮');
INSERT INTO `sys_menu` VALUES ('13', '角色新增', '#', '4', '2', '', '', 'F', 'system:role:add', '0', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '添加用户按钮');
INSERT INTO `sys_menu` VALUES ('14', '角色修改', '#', '4', '3', '', '', 'F', 'system:role:edit', '0', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '修改用户按钮');
INSERT INTO `sys_menu` VALUES ('15', '角色删除', '#', '4', '4', '', null, 'F', 'system:role:delete', '0', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '删除用户按钮');
INSERT INTO `sys_menu` VALUES ('16', '分配权限', '#', '4', '5', '', '', 'F', 'system:role:menu', '0', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '分配权限按钮');
INSERT INTO `sys_menu` VALUES ('17', '菜单新增', '#', '5', '2', '', null, 'F', 'system:menu:add', '0', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '添加菜单按钮');
INSERT INTO `sys_menu` VALUES ('18', '菜单修改', '#', '5', '3', '', null, 'F', 'system:menu:edit', '0', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '修改菜单按钮');
INSERT INTO `sys_menu` VALUES ('19', '菜单删除', '#', '5', '4', '', null, 'F', 'system:menu:delete', '0', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '删除菜单按钮');
INSERT INTO `sys_menu` VALUES ('20', '用户查询', '#', '3', '1', '', null, 'F', 'system:user:query', '0', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '用户查询按钮');
INSERT INTO `sys_menu` VALUES ('21', '角色查询', '#', '4', '1', '', null, 'F', 'system:role:query', '0', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '角色查询按钮');
INSERT INTO `sys_menu` VALUES ('22', '菜单查询', '#', '5', '1', '', null, 'F', 'system:menu:query', '0', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '菜单查询按钮');
INSERT INTO `sys_menu` VALUES ('23', '部门查询', '#', '6', '1', '', null, 'F', 'system:department:query', '0', null, null, '部门查询按钮');
INSERT INTO `sys_menu` VALUES ('24', '部门新增', '#', '6', '2', '', null, 'F', 'system:department:add', '0', null, null, '部门新增按钮');
INSERT INTO `sys_menu` VALUES ('25', '部门修改', '#', '6', '3', '', null, 'F', 'system:department:edit', '0', null, null, '部门修改按钮');
INSERT INTO `sys_menu` VALUES ('26', '部门删除', '#', '6', '4', '', null, 'F', 'system:department:delete', '0', null, null, '部门删除按钮');
INSERT INTO `sys_menu` VALUES ('27', '岗位查询', '#', '7', '1', '', null, 'F', 'system:post:query', '0', null, null, '岗位查询按钮');
INSERT INTO `sys_menu` VALUES ('28', '岗位增加', '#', '7', '2', '', null, 'F', 'system:post:add', '0', null, null, '岗位新增按钮');
INSERT INTO `sys_menu` VALUES ('29', '岗位修改', '#', '7', '3', '', null, 'F', 'system:post:edit', '0', null, null, '岗位修改按钮');
INSERT INTO `sys_menu` VALUES ('30', '岗位删除', '#', '7', '4', '', null, 'F', 'system:post:delete', '0', null, null, '岗位删除按钮');
INSERT INTO `sys_menu` VALUES ('38', '文件上传', '#', '2', '0', '/main/utils/upload', 'utils/cos', 'C', 'other:utils:upload', '0', '2023-03-06 14:15:17', '2023-03-06 14:15:18', '文件上传菜单');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色主键ID',
  `name` varchar(30) DEFAULT NULL COMMENT '角色名称',
  `code` varchar(100) DEFAULT NULL COMMENT '角色权限字符串',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `isBan` tinyint(1) DEFAULT '0',
  `isDelete` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', 'admin', '2022-07-04 14:40:44', '2022-07-04 14:40:47', '拥有系统最高权限', '0', '0');
INSERT INTO `sys_role` VALUES ('2', '普通角色', 'common', '2022-07-04 14:41:56', '2022-07-04 14:41:58', '普通角色', '0', '0');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `dept_id` bigint NOT NULL COMMENT '部门ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色和部门关联表';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint DEFAULT NULL COMMENT '菜单ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1');
INSERT INTO `sys_role_menu` VALUES ('1', '3');
INSERT INTO `sys_role_menu` VALUES ('1', '8');
INSERT INTO `sys_role_menu` VALUES ('1', '9');
INSERT INTO `sys_role_menu` VALUES ('1', '10');
INSERT INTO `sys_role_menu` VALUES ('1', '11');
INSERT INTO `sys_role_menu` VALUES ('1', '12');
INSERT INTO `sys_role_menu` VALUES ('1', '20');
INSERT INTO `sys_role_menu` VALUES ('1', '4');
INSERT INTO `sys_role_menu` VALUES ('1', '13');
INSERT INTO `sys_role_menu` VALUES ('1', '14');
INSERT INTO `sys_role_menu` VALUES ('1', '15');
INSERT INTO `sys_role_menu` VALUES ('1', '16');
INSERT INTO `sys_role_menu` VALUES ('1', '21');
INSERT INTO `sys_role_menu` VALUES ('1', '5');
INSERT INTO `sys_role_menu` VALUES ('1', '17');
INSERT INTO `sys_role_menu` VALUES ('1', '18');
INSERT INTO `sys_role_menu` VALUES ('1', '19');
INSERT INTO `sys_role_menu` VALUES ('1', '22');
INSERT INTO `sys_role_menu` VALUES ('1', '6');
INSERT INTO `sys_role_menu` VALUES ('1', '23');
INSERT INTO `sys_role_menu` VALUES ('1', '24');
INSERT INTO `sys_role_menu` VALUES ('1', '25');
INSERT INTO `sys_role_menu` VALUES ('1', '26');
INSERT INTO `sys_role_menu` VALUES ('1', '7');
INSERT INTO `sys_role_menu` VALUES ('1', '27');
INSERT INTO `sys_role_menu` VALUES ('1', '28');
INSERT INTO `sys_role_menu` VALUES ('1', '29');
INSERT INTO `sys_role_menu` VALUES ('1', '30');
INSERT INTO `sys_role_menu` VALUES ('1', '2');
INSERT INTO `sys_role_menu` VALUES ('1', '36');
INSERT INTO `sys_role_menu` VALUES ('1', '37');
INSERT INTO `sys_role_menu` VALUES ('1', '38');
INSERT INTO `sys_role_menu` VALUES ('2', '8');
INSERT INTO `sys_role_menu` VALUES ('2', '9');
INSERT INTO `sys_role_menu` VALUES ('2', '11');
INSERT INTO `sys_role_menu` VALUES ('2', '20');
INSERT INTO `sys_role_menu` VALUES ('2', '2');
INSERT INTO `sys_role_menu` VALUES ('2', '36');
INSERT INTO `sys_role_menu` VALUES ('2', '1');
INSERT INTO `sys_role_menu` VALUES ('2', '3');

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
  `user_id` bigint DEFAULT NULL,
  `post_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES ('1', '1');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint DEFAULT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userName` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户昵称',
  `deptId` int DEFAULT NULL COMMENT '部门id',
  `userAccount` varchar(256) NOT NULL COMMENT '账号',
  `userPassword` varchar(512) NOT NULL COMMENT '密码',
  `userAvatar` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '用户头像',
  `email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `age` int DEFAULT NULL COMMENT '年龄',
  `gender` tinyint DEFAULT NULL COMMENT '性别',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 普通 1超级管理员',
  `isBan` int DEFAULT '0' COMMENT '0 正常 1封禁',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_userAccount` (`userAccount`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COMMENT='用户';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'lemon', '3', 'lemon', '$2a$10$knpqU6xYF5Ekr910MhEK.OSIXUnrEpYI1TCUw5Jsm74lMxBEbJLQy', '', 'l.wsbkzcigwk@qq.com', '19817725154', '41', '0', '0', '0', '2023-02-05 12:20:01', '2023-02-25 13:19:22', '0');
