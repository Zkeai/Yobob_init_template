-- 创建库
create database if not exists hupi_db;

-- 切换库
use hupi_db;

-- 用户表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    userName     varchar(256)                           null comment '用户昵称',
    userAccount  varchar(256)                           not null comment '账号',
    userAvatar   varchar(1024)                          null comment '用户头像',
    gender       tinyint                                null comment '性别',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user / admin',
    userPassword varchar(512)                           not null comment '密码',
    jwtToken     varchar(512)                           not null comment 'jwt',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除',
    constraint uni_userAccount
        unique (userAccount)
) comment '用户';

-- 帖子表
create table if not exists post
(
    id            bigint auto_increment comment 'id' primary key,
    age           int comment '年龄',
    gender        tinyint  default 0                 not null comment '性别（0-男, 1-女）',
    education     varchar(512)                       null comment '学历',
    place         varchar(512)                       null comment '地点',
    job           varchar(512)                       null comment '职业',
    contact       varchar(512)                       null comment '联系方式',
    loveExp       varchar(512)                       null comment '感情经历',
    content       text                               null comment '内容（个人介绍）',
    photo         varchar(1024)                      null comment '照片地址',
    reviewStatus  int      default 0                 not null comment '状态（0-待审核, 1-通过, 2-拒绝）',
    reviewMessage varchar(512)                       null comment '审核信息',
    viewNum       int                                not null default 0 comment '浏览数',
    thumbNum      int                                not null default 0 comment '点赞数',
    userId        bigint                             not null comment '创建用户 id',
    createTime    datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime    datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete      tinyint  default 0                 not null comment '是否删除'
) comment '帖子';

-- 角色表sys_role
CREATE TABLE `sys_role` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色主键ID',
    `name` varchar(30) DEFAULT NULL COMMENT '角色名称',
    `code` varchar(100) DEFAULT NULL COMMENT '角色权限字符串',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*Data for the table `sys_role` */
insert into `sys_role`(`id`,`name`,`code`,`create_time`,`update_time`,`remark`)
values (1,'超级管理员','admin','2022-07-04 14:40:44','2022-07-04 14:40:47','拥有系统最高权限')
       ,(2,'普通角色','common','2022-07-04 14:41:56','2022-07-04 14:41:58','普通角色')
       ,(3,'测试角色','test','2022-07-04 14:42:24','2022-07-04 14:42:27','测试角色');


-- 菜单权限表sys_menu
CREATE TABLE`sys_menu` (
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
/*Data for the table `sys_menu` */
insert into
    `sys_menu`(`id`,`name`,`icon`,`parent_id`,`order_num`,`path`,`component`,`menu_type`,`perms`,`create_time`,`update_time`,`remark`)
        values (
                1,'系统管理','system',0,1,'/sys','','M','','2022-07-04 14:56:29','2022-07-04 14:56:31','系统管理目录'),
            (2,'业务管理','monitor',0,2,'/bsns','','M','','2022-07-04 14:59:43','2022-07-04 14:59:45','业务管理目录'),
            (3,'用户管理','user',1,1,'/sys/user','sys/user/index','C','system:user:list','2022-07-04 15:20:51','2022-07-04 15:20:53','用户管理菜单'),
            (4,'角色管理','peoples',1,2,'/sys/role','sys/role/index','C','system:role:list','2022-07-04 15:23:35','2022-07-04 15:23:39','角色管理菜单'),
            (5,'菜单管理','tree-table',1,3,'/sys/menu','sys/menu/index','C','system:menu:list','2022-07-0415:23:41','2022-07-04 15:23:43','菜单管理菜单'),
            (6,'部门管理','tree',2,1,'/bsns/department','bsns/Department','C','','2022-07-04 15:24:40','2022-07-04 15:24:44','部门管理菜单'),
            (7,'岗位管理','post',2,2,'/bsns/post','bsns/Post','C','','2022-07-04 15:24:42','2022-07-04 15:24:46','岗位管理菜单'),
            (8,'用户新增','#',3,2,'','','F','system:user:add','2022-07-04 15:24:42','2022-07-04 15:24:46','添加用户按钮'),
            (9,'用户修改','#',3,3,'','','F','system:user:edit','2022-07-04 15:24:42','2022-07-04 15:24:46','修改用户按钮'),
            (10,'用户删除','#',3,4,'','','F','system:user:delete','2022-07-04 15:24:42','2022-07-04 15:24:46','删除用户按钮'),
            (11,'分配角色','#',3,5,'','','F','system:user:role','2022-07-04 15:24:42','2022-07-04 15:24:46','分配角色按钮'),
            (12,'重置密码','#',3,6,'','','F','system:user:resetPwd','2022-07-04 15:24:42','2022-07-04 15:24:46','重置密码按钮'),
            (13,'角色新增','#',4,2,'','','F','system:role:add','2022-07-04 15:24:42','2022-07-04 15:24:46','添加用户按钮'),
            (14,'角色修改','#',4,3,'','','F','system:role:edit','2022-07-04 15:24:42','2022-07-04 15:24:46','修改用户按钮'),
            (15,'角色删除','#',4,4,'',NULL,'F','system:role:delete','2022-07-04 15:24:42','2022-07-04 15:24:46','删除用户按钮'),
            (16,'分配权限','#',4,5,'','','F','system:role:menu','2022-07-04 15:24:42','2022-07-04 15:24:46','分配权限按钮'),
            (17,'菜单新增','#',5,2,'',NULL,'F','system:menu:add','2022-07-04 15:24:42','2022-07-04 15:24:46','添加菜单按钮'),
            (18,'菜单修改','#',5,3,'',NULL,'F','system:menu:edit','2022-07-04 15:24:42','2022-07-04 15:24:46','修改菜单按钮'),
            (19,'菜单删除','#',5,4,'',NULL,'F','system:menu:delete','2022-07-04 15:24:42','2022-07-04 15:24:46','删除菜单按钮'),
            (20,'用户查询','#',3,1,'',NULL,'F','system:user:query','2022-07-04 15:24:42','2022-07-04 15:24:46','用户查询按钮'),
            (21,'角色查询','#',4,1,'',NULL,'F','system:role:query','2022-07-04 15:24:42','2022-07-04 15:24:46','角色查询按钮'),
            (22,'菜单查询','#',5,1,'',NULL,'F','system:menu:query','2022-07-04 15:24:42','2022-07-04 15:24:46','菜单查询按钮');

-- 用户角色关联表sys_user_role
CREATE TABLE `sys_user_role` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户角色主键ID',
    `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
    `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*Data for the table `sys_user_role` */
insert into `sys_user_role`(`id`,`user_id`,`role_id`) values (1,1,1),(2,2,2),
(4,1,2),(6,3,3),(7,3,2),(9,4,3),(10,5,3),(11,15,3),(16,28,2),(17,28,3),
(20,29,20),(21,30,17),(22,30,21);

-- 角色菜单权限关联表sys_role_menu
CREATE TABLE `sys_role_menu` (
     `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色菜单主键ID',
     `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
     `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
     PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=239 DEFAULT CHARSET=utf8;
/*Data for the table `sys_role_menu` */
insert into `sys_role_menu`(`id`,`role_id`,`menu_id`) values
     (8,2,1),(9,2,2),(10,2,3),(11,2,4),(12,2,5),(13,2,6),(14,2,7),(15,3,2),(16,3,6),(17,3,7),
     (21,7,1),(22,7,2),(23,7,6),(24,7,7),(25,6,1),(26,6,3),(27,6,9),(28,6,10),
     (29,19,1),(30,19,3),(31,19,2),(32,19,6),(33,1,1),(34,1,3),(35,1,20),(36,1,8),
     (37,1,9),(38,1,10),(39,1,11),(40,1,12),(41,1,4),(42,1,21),(43,1,13),(44,1,14),
     (45,1,15),(46,1,16),(47,1,23),(48,1,5),(49,1,22),(50,1,17),(51,1,18),(52,1,19),
     (53,1,2),(54,1,6),(55,1,7),(208,20,1),(209,20,3),(210,20,20),(211,20,8),
     (212,20,9),(213,20,33),(214,20,10),(215,20,11),(216,20,4),(217,20,21),
     (218,20,13),(219,20,5),(220,20,22),(221,20,17),(222,20,18),(223,20,2),
     (224,20,6),(225,20,7),(232,21,1),(233,21,9),(234,21,4),(235,21,21),(236,21,2),
     (237,21,6),(238,21,7);