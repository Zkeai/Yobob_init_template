/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50738
Source Host           : localhost:3306
Source Database       : hupi_db

Target Server Type    : MYSQL
Target Server Version : 50738
File Encoding         : 65001

Date: 2023-02-04 18:45:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` bigint(200) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) CHARACTER SET utf8 NOT NULL,
  `sn` varchar(256) CHARACTER SET utf8 NOT NULL,
  `parentId` bigint(20) DEFAULT NULL COMMENT '父级id',
  `ancestors` varchar(256) CHARACTER SET utf8 DEFAULT NULL COMMENT '祖级列表',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态 0 正常 1 删除',
  `leader` varchar(256) CHARACTER SET utf8 DEFAULT NULL COMMENT '负责人',
  `phone` varchar(256) CHARACTER SET utf8 DEFAULT NULL COMMENT '手机号',
  `email` varchar(256) CHARACTER SET utf8 DEFAULT NULL COMMENT '邮箱',
  `create_by` varchar(256) DEFAULT NULL COMMENT '创建人',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateBy` varchar(256) DEFAULT NULL COMMENT '更新人',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0 正常 1 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1 COMMENT='部门表';

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '研发部门', 'yf', null, null, '0', null, null, null, null, '2023-02-03 13:29:07', null, '2023-02-03 13:29:08', '0');
INSERT INTO `department` VALUES ('2', '客服部门', 'kf', null, null, '0', null, null, null, null, '2023-02-03 13:29:07', null, '2023-02-03 13:29:08', '0');
INSERT INTO `department` VALUES ('3', '运营部门', 'yy', null, null, '0', null, null, null, null, '2023-02-03 13:29:07', null, '2023-02-03 13:29:08', '0');
INSERT INTO `department` VALUES ('4', '总裁办', 'zcb', null, null, '0', null, null, null, null, '2023-02-03 13:29:07', null, '2023-02-03 13:29:08', '0');
INSERT INTO `department` VALUES ('6', '安保部门', 'ab', '0', '', '0', '', '15257222873', '', '', '2023-02-03 13:38:09', '', '2023-02-03 14:20:47', '0');
INSERT INTO `department` VALUES ('12', '监理部门', 'jl', '0', '', '0', '', '15257222875', '', '', '2023-02-03 14:22:17', '', '2023-02-03 14:22:32', '0');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `expression` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `gender` tinyint(4) NOT NULL DEFAULT '0' COMMENT '性别（0-男, 1-女）',
  `education` varchar(512) DEFAULT NULL COMMENT '学历',
  `place` varchar(512) DEFAULT NULL COMMENT '地点',
  `job` varchar(512) DEFAULT NULL COMMENT '职业',
  `contact` varchar(512) DEFAULT NULL COMMENT '联系方式',
  `loveExp` varchar(512) DEFAULT NULL COMMENT '感情经历',
  `content` text COMMENT '内容（个人介绍）',
  `photo` varchar(1024) DEFAULT NULL COMMENT '照片地址',
  `reviewStatus` int(11) NOT NULL DEFAULT '0' COMMENT '状态（0-待审核, 1-通过, 2-拒绝）',
  `reviewMessage` varchar(512) DEFAULT NULL COMMENT '审核信息',
  `viewNum` int(11) NOT NULL DEFAULT '0' COMMENT '浏览数',
  `thumbNum` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `userId` bigint(20) NOT NULL COMMENT '创建用户 id',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='帖子';

-- ----------------------------
-- Records of post
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单主键ID',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID',
  `order_num` int(11) DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `perms` varchar(100) DEFAULT '' COMMENT '权限标识',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', 'system', '0', '1', '/sys', '', 'M', '', '2022-07-04 14:56:29', '2022-07-04 14:56:31', '系统管理目录');
INSERT INTO `sys_menu` VALUES ('2', '业务管理', 'monitor', '0', '2', '/bsns', '', 'M', '', '2022-07-04 14:59:43', '2022-07-04 14:59:45', '业务管理目录');
INSERT INTO `sys_menu` VALUES ('3', '用户管理', 'user', '1', '1', '/sys/user', 'sys/user/index', 'C', 'system:user:list', '2022-07-04 15:20:51', '2022-07-04 15:20:53', '用户管理菜单');
INSERT INTO `sys_menu` VALUES ('4', '角色管理', 'peoples', '1', '2', '/sys/role', 'sys/role/index', 'C', 'system:role:list', '2022-07-04 15:23:35', '2022-07-04 15:23:39', '角色管理菜单');
INSERT INTO `sys_menu` VALUES ('5', '菜单管理', 'tree-\r\n        table', '1', '3', '/sys/menu', 'sys/menu/index', 'C', 'system:menu:list', '2022-07-04 15:23:41', '2022-07-04 15:23:43', '菜单管理菜单');
INSERT INTO `sys_menu` VALUES ('6', '部门管理', 'tree', '2', '1', '/bsns/department', 'bsns/Department', 'C', '', '2022-07-04 15:24:40', '2022-07-04 15:24:44', '部门管理菜单');
INSERT INTO `sys_menu` VALUES ('7', '岗位管理', 'post', '2', '2', '/bsns/post', 'bsns/Post', 'C', '', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '岗位管理菜单');
INSERT INTO `sys_menu` VALUES ('8', '用户新增', '#', '3', '2', '', '', 'F', 'system:user:add', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '添加用户按钮');
INSERT INTO `sys_menu` VALUES ('9', '用户修改', '#', '3', '3', '', '', 'F', 'system:user:edit', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '修改用户按钮');
INSERT INTO `sys_menu` VALUES ('10', '用户删除', '#', '3', '4', '', '', 'F', 'system:user:delete', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '删除用户按钮');
INSERT INTO `sys_menu` VALUES ('11', '分配角色', '#', '3', '5', '', '', 'F', 'system:user:role', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '分配角色按钮');
INSERT INTO `sys_menu` VALUES ('12', '重置密码', '#', '3', '6', '', '', 'F', 'system:user:resetPwd', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '重置密码按钮');
INSERT INTO `sys_menu` VALUES ('13', '角色新增', '#', '4', '2', '', '', 'F', 'system:role:add', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '添加用户按钮');
INSERT INTO `sys_menu` VALUES ('14', '角色修改', '#', '4', '3', '', '', 'F', 'system:role:edit', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '修改用户按钮');
INSERT INTO `sys_menu` VALUES ('15', '角色删除', '#', '4', '4', '', null, 'F', 'system:role:delete', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '删除用户按钮');
INSERT INTO `sys_menu` VALUES ('16', '分配权限', '#', '4', '5', '', '', 'F', 'system:role:menu', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '分配权限按钮');
INSERT INTO `sys_menu` VALUES ('17', '菜单新增', '#', '5', '2', '', null, 'F', 'system:menu:add', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '添加菜单按钮');
INSERT INTO `sys_menu` VALUES ('18', '菜单修改', '#', '5', '3', '', null, 'F', 'system:menu:edit', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '修改菜单按钮');
INSERT INTO `sys_menu` VALUES ('19', '菜单删除', '#', '5', '4', '', null, 'F', 'system:menu:delete', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '删除菜单按钮');
INSERT INTO `sys_menu` VALUES ('20', '用户查询', '#', '3', '1', '', null, 'F', 'system:user:query', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '用户查询按钮');
INSERT INTO `sys_menu` VALUES ('21', '角色查询', '#', '4', '1', '', null, 'F', 'system:role:query', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '角色查询按钮');
INSERT INTO `sys_menu` VALUES ('22', '菜单查询', '#', '5', '1', '', null, 'F', 'system:menu:query', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '菜单查询按钮');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色主键ID',
  `name` varchar(30) DEFAULT NULL COMMENT '角色名称',
  `code` varchar(100) DEFAULT NULL COMMENT '角色权限字符串',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', 'admin', '2022-07-04 14:40:44', '2022-07-04 14:40:47', '拥有系统最高权限');
INSERT INTO `sys_role` VALUES ('2', '普通角色', 'common', '2022-07-04 14:41:56', '2022-07-04 14:41:58', '普通角色');
INSERT INTO `sys_role` VALUES ('3', '测试角色', 'test', '2022-07-04 14:42:24', '2022-07-04 14:42:27', '测试角色');
INSERT INTO `sys_role` VALUES ('23', '测试员', 'ceshi', null, null, '测试');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色和部门关联表';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES ('2', '100');
INSERT INTO `sys_role_dept` VALUES ('2', '101');
INSERT INTO `sys_role_dept` VALUES ('2', '105');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `role_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '123');
INSERT INTO `sys_role_permission` VALUES ('1', '124');
INSERT INTO `sys_role_permission` VALUES ('1', '125');
INSERT INTO `sys_role_permission` VALUES ('23', '1234');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '2');
INSERT INTO `sys_user_role` VALUES ('1', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userName` varchar(256) DEFAULT NULL COMMENT '用户昵称',
  `deptId` int(11) DEFAULT NULL COMMENT '部门id',
  `userAccount` varchar(256) NOT NULL COMMENT '账号',
  `userPassword` varchar(512) NOT NULL COMMENT '密码',
  `userAvatar` varchar(1024) DEFAULT NULL COMMENT '用户头像',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `gender` tinyint(4) DEFAULT NULL COMMENT '性别',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '用户状态',
  `userRole` varchar(256) NOT NULL DEFAULT 'user' COMMENT '用户角色：user / admin',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_userAccount` (`userAccount`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COMMENT='用户';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', null, null, 'zkeai', 'fe655af804bd109499fdcf6d0a53cbbe', null, null, null, null, '0', '0', 'user', '2023-02-03 16:20:15', '2023-02-04 12:33:08', '0');
INSERT INTO `user` VALUES ('6', 'saoren', '0', 'saoren', 'df7de888952875c52b6f1b320a1c358a', '', '35873886@qq.com', '17336281859', '0', '1', '0', 'super', '2023-02-04 14:31:48', '2023-02-04 14:35:26', '0');
