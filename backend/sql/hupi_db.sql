/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50738
Source Host           : localhost:3306
Source Database       : hupi_db

Target Server Type    : MYSQL
Target Server Version : 50738
File Encoding         : 65001

Date: 2023-02-16 17:38:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` bigint(200) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) CHARACTER SET utf8 NOT NULL,
  `sn` varchar(256) CHARACTER SET utf8 DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1 COMMENT='部门表';

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', 'Hupi科技', 'hupi', '0', '0', '0', 'admin', '15257222873', '95736614@qq.com', null, '2023-02-13 22:00:06', null, '2023-02-15 00:36:44', '0');
INSERT INTO `department` VALUES ('2', '浙江总公司', null, '1', '0,1', '0', null, null, null, null, '2023-02-13 22:01:32', null, '2023-02-15 22:59:33', '0');
INSERT INTO `department` VALUES ('3', '上海分公司', null, '1', '0,1', '0', null, null, null, null, '2023-02-13 22:01:59', null, '2023-02-15 00:44:42', '0');
INSERT INTO `department` VALUES ('4', '研发部门', null, '2', '0,1,2', '0', null, null, null, null, '2023-02-13 22:03:35', null, '2023-02-13 22:03:35', '0');
INSERT INTO `department` VALUES ('5', '市场部门', null, '2', '0,1,2', '0', null, null, null, null, '2023-02-13 22:03:50', null, '2023-02-13 22:03:50', '0');
INSERT INTO `department` VALUES ('6', '财务部门', null, '2', '0,1,2', '0', null, null, null, null, '2023-02-13 22:04:10', null, '2023-02-13 22:04:10', '0');
INSERT INTO `department` VALUES ('7', '运维部门', null, '2', '0,1,2', '0', null, null, null, null, '2023-02-13 22:04:20', null, '2023-02-13 22:04:20', '0');
INSERT INTO `department` VALUES ('8', '市场部门', null, '3', '0,1,3', '0', null, null, null, null, '2023-02-13 22:04:39', null, '2023-02-13 22:04:39', '0');
INSERT INTO `department` VALUES ('9', '财务部门', null, '3', '0,1,3', '0', null, null, null, null, '2023-02-13 22:04:53', '', '2023-02-16 15:06:53', '0');

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `expression` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('1', '董事长', 'ceo');
INSERT INTO `post` VALUES ('2', '项目经理', 'se');
INSERT INTO `post` VALUES ('3', '人力资源', 'hr');
INSERT INTO `post` VALUES ('4', '首席技术官', 'cto');
INSERT INTO `post` VALUES ('5', '普通员工', 'user');

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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', 'Setting', '0', '1', '/main/sys', '', 'M', '', '2022-07-04 14:56:29', '2022-07-04 14:56:31', '系统管理目录');
INSERT INTO `sys_menu` VALUES ('2', '业务管理', 'monitor', '0', '2', '/main/bsns', '', 'M', '', '2022-07-04 14:59:43', '2022-07-04 14:59:45', '业务管理目录');
INSERT INTO `sys_menu` VALUES ('3', '用户管理', 'user', '1', '1', '/main/sys/user', 'sys/user/index', 'C', 'system:user:list', '2022-07-04 15:20:51', '2022-07-04 15:20:53', '用户管理菜单');
INSERT INTO `sys_menu` VALUES ('4', '角色管理', 'peoples', '1', '2', '/main/sys/role', 'sys/role/index', 'C', 'system:role:list', '2022-07-04 15:23:35', '2022-07-04 15:23:39', '角色管理菜单');
INSERT INTO `sys_menu` VALUES ('5', '菜单管理', 'tree-\r\n        table', '1', '3', '/main/sys/menu', 'sys/menu/index', 'C', 'system:menu:list', '2022-07-04 15:23:41', '2022-07-04 15:23:43', '菜单管理菜单');
INSERT INTO `sys_menu` VALUES ('6', '部门管理', 'tree', '1', '1', '/main/sys/department', 'sys/Department', 'C', '', '2022-07-04 15:24:40', '2022-07-04 15:24:44', '部门管理菜单');
INSERT INTO `sys_menu` VALUES ('7', '岗位管理', 'post', '1', '2', '/main/sys/post', 'sys/Post', 'C', '', '2022-07-04 15:24:42', '2022-07-04 15:24:46', '岗位管理菜单');
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
INSERT INTO `sys_role_menu` VALUES ('1', '1');
INSERT INTO `sys_role_menu` VALUES ('1', '2');
INSERT INTO `sys_role_menu` VALUES ('1', '3');
INSERT INTO `sys_role_menu` VALUES ('1', '4');
INSERT INTO `sys_role_menu` VALUES ('1', '5');
INSERT INTO `sys_role_menu` VALUES ('1', '6');
INSERT INTO `sys_role_menu` VALUES ('1', '7');
INSERT INTO `sys_role_menu` VALUES ('2', '1');
INSERT INTO `sys_role_menu` VALUES ('2', '2');
INSERT INTO `sys_role_menu` VALUES ('2', '3');
INSERT INTO `sys_role_menu` VALUES ('2', '7');

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
  `user_id` bigint(20) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES ('15', '5');
INSERT INTO `sys_user_post` VALUES ('16', '2');
INSERT INTO `sys_user_post` VALUES ('16', '3');
INSERT INTO `sys_user_post` VALUES ('17', '2');
INSERT INTO `sys_user_post` VALUES ('17', '3');
INSERT INTO `sys_user_post` VALUES ('17', '5');
INSERT INTO `sys_user_post` VALUES ('18', '3');
INSERT INTO `sys_user_post` VALUES ('18', '2');

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
INSERT INTO `sys_user_role` VALUES ('15', '2');
INSERT INTO `sys_user_role` VALUES ('15', '3');
INSERT INTO `sys_user_role` VALUES ('16', '2');
INSERT INTO `sys_user_role` VALUES ('17', '3');
INSERT INTO `sys_user_role` VALUES ('17', '1');
INSERT INTO `sys_user_role` VALUES ('17', '2');
INSERT INTO `sys_user_role` VALUES ('18', '2');
INSERT INTO `sys_user_role` VALUES ('1', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userName` varchar(256) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '用户昵称',
  `deptId` int(11) DEFAULT NULL COMMENT '部门id',
  `userAccount` varchar(256) NOT NULL COMMENT '账号',
  `userPassword` varchar(512) NOT NULL COMMENT '密码',
  `userAvatar` varchar(1024) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户头像',
  `email` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `gender` tinyint(4) DEFAULT NULL COMMENT '性别',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 普通 1超级管理员',
  `isBan` tinyint(4) DEFAULT '0' COMMENT '0 正常 1封禁',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_userAccount` (`userAccount`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1 COMMENT='用户';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'lemon', '52', 'lemon', '$2a$10$knpqU6xYF5Ekr910MhEK.OSIXUnrEpYI1TCUw5Jsm74lMxBEbJLQy', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fblog%2F202106%2F09%2F20210609081952_51ef5.thumb.1000_0.jpg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1678420681&t=09eaa1014aafb4526c0fd37ebb49d893', 'l.wsbkzcigwk@qq.com', '19817725154', '41', '0', '0', '0', '2023-02-05 12:20:01', '2023-02-14 17:11:16', '0');
INSERT INTO `user` VALUES ('5', '世界很美好', '52', 'saoren', '$2a$10$knpqU6xYF5Ekr910MhEK.OSIXUnrEpYI1TCUw5Jsm74lMxBEbJLQy', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fblog%2F202106%2F09%2F20210609081952_51ef5.thumb.1000_0.jpg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1678420681&t=09eaa1014aafb4526c0fd37ebb49d893', 'l.wsbkzcigwk@qq.com', '18695241448', '42', '0', '0', '0', '2023-02-06 23:51:12', '2023-02-15 23:38:05', '0');
INSERT INTO `user` VALUES ('6', null, null, 'kfjhfh', '$2a$10$rDgr4IEcSrKyOfjZAwpwAu726yBFzHT6mmjeeXAsLiR2sU/mEnkU.', null, null, '18695241449', null, '1', '0', '0', '2023-02-11 15:42:06', '2023-02-15 00:40:54', '0');
INSERT INTO `user` VALUES ('7', null, null, 'kfjhfhh', '$2a$10$78VWX1SasYI04kAJdSpTAef5iQt4XHJIjSiQHswxmPWWbRUoT0skS', null, null, '18695241449', null, '0', '0', '0', '2023-02-11 15:42:11', '2023-02-12 15:45:42', '0');
INSERT INTO `user` VALUES ('8', null, null, 'kfjhfhhk', '$2a$10$KP2hPA4SL1qskIpJomWbEu3MsOkjy.HqayaOgm2Hld3fA3J12o0cq', null, null, null, null, '1', '0', '0', '2023-02-11 15:42:15', '2023-02-12 15:45:30', '0');
INSERT INTO `user` VALUES ('9', null, null, 'kfjhfhhkl', '$2a$10$dlONddD7VRR8SupoQpn7kOPNZNcIojN61qy2DDre1ToamcTihNhFi', null, null, null, null, '1', '0', '0', '2023-02-11 15:42:19', '2023-02-12 15:45:30', '0');
INSERT INTO `user` VALUES ('10', null, null, 'fgfdh', '$2a$10$HZX4blpIJowbzil0/CyRN.AUdYUHjsbUF3l7XJV.X3FZpbfpRyJ7G', null, null, null, null, '1', '1', '1', '2023-02-11 15:42:24', '2023-02-12 15:45:31', '0');
INSERT INTO `user` VALUES ('11', null, null, 'fgfdhn', '$2a$10$vASRvqhUNbgQdSRlM783OePqNSh9fSbmSX/Sb9.XTeljviBkba3r.', null, null, null, null, '0', '0', '0', '2023-02-11 15:42:28', '2023-02-12 15:45:32', '0');
INSERT INTO `user` VALUES ('12', null, null, 'fgfdhnm', '$2a$10$3LkzzvSPVaewTUq7wjmse..YuhsxHvYLv95/AdGCBL7lCu99g8bMW', null, null, null, null, '0', '1', '0', '2023-02-11 15:42:32', '2023-02-12 15:45:32', '0');
INSERT INTO `user` VALUES ('13', null, null, 'ferewr435', '$2a$10$c/WdTTnRq.LVD5nNIvumD.Q6EyhETiUW1G5LgaqEoMj4SrkpBd67a', null, null, null, null, '0', '0', '1', '2023-02-11 15:42:36', '2023-02-13 15:40:21', '0');
INSERT INTO `user` VALUES ('14', null, null, 'fdsg435', '$2a$10$GbK9JDg5saevk5p8PADUkeCgP5xDPNGJnkBfd18M4.BFbR0k4QB4.', null, null, null, null, '1', '1', '0', '2023-02-11 16:06:44', '2023-02-13 15:22:35', '0');
INSERT INTO `user` VALUES ('15', 'aaaa', '4', 'admin123', '$2a$10$o/092iQ4k59mtL5aA3L7zelVSx0YEEDD.HIHvGhtI0q9ihmjNFkj.', null, '95736614@qq.com', '15257222873', null, null, '0', '0', '2023-02-14 12:43:01', '2023-02-14 12:43:01', '0');
INSERT INTO `user` VALUES ('16', 'bbb', '5', 'admin1234', '$2a$10$g1gnXIRNWe/zZ/fPmTtUHedCBpLKXd1j5Z2rxarbxs2tJl5XuOxUi', null, '95736614@qq.com', '19817725154', null, '0', '0', '0', '2023-02-14 12:46:54', '2023-02-14 12:46:54', '0');
INSERT INTO `user` VALUES ('17', 'ccccc', '6', 'admin12345', '$2a$10$Qrki5So5Wmr/bhawirugQ.8OvIpmCuY9AzAZDH3Gi0r798UnLffOC', null, '95736614@qq.com', '15257222873', null, '1', '0', '0', '2023-02-14 12:49:33', '2023-02-14 12:49:33', '0');
INSERT INTO `user` VALUES ('18', 'qqqq', '5', 'qqqqqq', '$2a$10$iN0.iiIoZQEtgpD4oMKzFurg4deYHXx7V8aqnPJ1IwkUMQPw0y.0u', null, '', '', null, '1', '0', '0', '2023-02-14 12:53:17', '2023-02-14 12:53:17', '0');
INSERT INTO `user` VALUES ('19', 'wwww', '4', 'admin111', '$2a$10$1MGNVA1KjdMpB4Nqr4yCb.zIXHUzUuQtcaFeGZbNOIApMl7IkMahe', null, '95736614@qq.com', '15257222873', null, '1', '0', '0', '2023-02-14 13:54:34', '2023-02-14 14:56:43', '1');
