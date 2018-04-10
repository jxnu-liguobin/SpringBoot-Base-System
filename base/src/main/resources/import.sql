# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.16)
# Database: ManagementSystemDB
# Generation Time: 2017-07-07 12:00:53 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table tb_books
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tb_books`;

CREATE TABLE `tb_books` (
  `book_id` varchar(50) NOT NULL DEFAULT '',
  `book_name` varchar(50) DEFAULT NULL,
  `book_press` varchar(50) DEFAULT NULL,
  `book_inventory` int(255) NOT NULL,
  `book_author` varchar(50) DEFAULT NULL,
  `current_inventory` int(255) DEFAULT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `tb_books` WRITE;
/*!40000 ALTER TABLE `tb_books` DISABLE KEYS */;

INSERT INTO `tb_books` (`book_id`, `book_name`, `book_press`, `book_inventory`, `book_author`, `current_inventory`)
VALUES
	('441f54dbd7604f41bc575b75ad1ff640','目送','三联书店',100,'龙应台',100),
	('4a2592ca1ac44c7da96af42338553b73','孩子你慢慢来','三联书店',50,'龙应台',50),
	('75928c502c034cc1a76fb0f0e6cac5e1','野火集','三联书店',80,'龙应台',80),
	('e008401787cc4d39a979abe2c23cc318','亲爱的安德烈','三联书店',90,'龙应台',90);

/*!40000 ALTER TABLE `tb_books` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tb_borrow_books
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tb_borrow_books`;

CREATE TABLE `tb_borrow_books` (
  `user_id` int(11) NOT NULL,
  `book_id` varchar(50) NOT NULL DEFAULT '',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`,`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table tb_resource
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tb_resource`;

CREATE TABLE `tb_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `is_hide` int(11) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `source_key` varchar(255) DEFAULT NULL,
  `source_url` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf5ra2gn0xedeida2op8097sr5` (`parent_id`),
  CONSTRAINT `FKf5ra2gn0xedeida2op8097sr5` FOREIGN KEY (`parent_id`) REFERENCES `tb_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `tb_resource` WRITE;
/*!40000 ALTER TABLE `tb_resource` DISABLE KEYS */;

INSERT INTO `tb_resource` (`id`, `create_time`, `description`, `icon`, `is_hide`, `level`, `name`, `sort`, `source_key`, `source_url`, `type`, `update_time`, `parent_id`)
VALUES
	(1,'2017-01-10 13:56:51','用户管理',NULL,0,2,'用户管理',1,'system:user:index','/admin/user/index',1,'2017-01-10 13:59:01',NULL),
	(2,'2017-01-10 13:56:51','用户编辑',NULL,0,3,'用户编辑',1,'system:user:edit','/admin/user/edit*',2,'2017-01-10 16:26:42',1),
	(3,'2017-01-11 16:48:48','用户添加',NULL,0,3,'用户添加',2,'system:user:add','/admin/user/add',2,'2017-01-11 16:49:26',1),
	(4,'2017-01-11 16:48:48','用户删除',NULL,0,3,'用户删除',3,'system:user:deleteBatch','/admin/user/deleteBatch',2,'2017-01-18 14:11:41',1),
	(5,'2017-01-11 16:48:48','角色分配',NULL,0,3,'角色分配',4,'system:user:grant','/admin/user/grant/**',2,'2017-01-18 14:11:51',1),
	(6,'2017-01-12 16:45:10','角色管理',NULL,0,2,'角色管理',2,'system:role:index','/admin/role/index',1,'2017-01-12 16:46:52',NULL),
	(7,'2017-01-12 16:47:02','角色编辑',NULL,0,3,'角色编辑',1,'system:role:edit','/admin/role/edit*',2,'2017-01-18 10:24:06',1),
	(8,'2017-01-12 16:47:23','角色添加',NULL,0,3,'角色添加',2,'system:role:add','/admin/role/add',2,'2017-01-12 16:49:16',6),
	(9,'2017-01-12 16:47:23','角色删除',NULL,0,3,'角色删除',3,'system:role:deleteBatch','/admin/role/deleteBatch',2,'2017-01-18 14:12:03',6),
	(10,'2017-01-12 16:47:23','资源分配',NULL,0,3,'资源分配',4,'system:role:grant','/admin/role/grant/**',2,'2017-01-18 14:12:11',6),
	(11,'2017-01-17 11:21:12','资源管理',NULL,0,2,'资源管理',3,'system:resource:index','/admin/resource/index',1,'2017-01-17 11:21:42',NULL),
	(12,'2017-01-17 11:21:52','资源编辑',NULL,0,3,'资源编辑',1,'system:resource:edit','/admin/resource/edit*',2,'2017-01-17 11:22:36',11),
	(13,'2017-01-17 11:21:54','资源添加',NULL,0,3,'资源添加',2,'system:resource:add','/admin/resource/add',2,'2017-01-17 11:22:39',11),
	(14,'2017-01-17 11:21:54','资源删除',NULL,0,3,'资源删除',3,'system:resource:deleteBatch','/admin/resource/deleteBatch',2,'2017-01-18 14:12:31',11),
	(15,'2017-01-17 11:21:54','图书管理',NULL,0,2,'图书管理',4,'system:books:book_management','/admin/books/book_management',1,'2017-01-18 14:12:31',NULL);

/*!40000 ALTER TABLE `tb_resource` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tb_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tb_role`;

CREATE TABLE `tb_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `role_key` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `tb_role` WRITE;
/*!40000 ALTER TABLE `tb_role` DISABLE KEYS */;

INSERT INTO `tb_role` (`id`, `create_time`, `description`, `name`, `role_key`, `status`, `update_time`)
VALUES
	(1,'2017-01-09 17:25:30','超级管理员','administrator','administrator',0,'2017-01-09 17:26:25'),
	(2,'2017-04-20 22:50:05','学生用户','student','student',0,'2017-04-20 22:50:05');

/*!40000 ALTER TABLE `tb_role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tb_role_resource
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tb_role_resource`;

CREATE TABLE `tb_role_resource` (
  `role_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`resource_id`),
  KEY `FK868kc8iic48ilv5npa80ut6qo` (`resource_id`),
  CONSTRAINT `FK7ffc7h6obqxflhj1aq1mk20jk` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`),
  CONSTRAINT `FK868kc8iic48ilv5npa80ut6qo` FOREIGN KEY (`resource_id`) REFERENCES `tb_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `tb_role_resource` WRITE;
/*!40000 ALTER TABLE `tb_role_resource` DISABLE KEYS */;

INSERT INTO `tb_role_resource` (`role_id`, `resource_id`)
VALUES
	(1,1),
	(1,2),
	(1,3),
	(1,4),
	(1,5),
	(1,6),
	(1,7),
	(1,8),
	(1,9),
	(1,10),
	(1,11),
	(1,12),
	(1,13),
	(1,14),
	(1,15);

/*!40000 ALTER TABLE `tb_role_resource` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tb_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `delete_status` int(11) DEFAULT NULL,
  `locked` int(11) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;

INSERT INTO `tb_user` (`id`, `user_code`, `user_name`, `create_time`, `delete_status`, `locked`, `password`, `telephone`, `update_time`)
VALUES
	(1,'admin','admin','2017-01-09 17:26:41',0,0,'3931MUEQD1939MQMLM4AISPVNE','18888888888','2017-04-20 22:52:12'),
	(2,'1418140303','James','2017-04-20 22:48:34',0,0,'22IVQ4M4SLA8QI8MP4ISSPQUKJ','183222222','2017-06-29 02:29:10'),
	(12,'1418140304','Jon','2017-05-04 19:31:10',0,0,'22IVQ4M4SLA8QI8MP4ISSPQUKJ','1832203000','2017-06-29 02:29:31');

/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tb_user_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tb_user_role`;

CREATE TABLE `tb_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKea2ootw6b6bb0xt3ptl28bymv` (`role_id`),
  CONSTRAINT `FK7vn3h53d0tqdimm8cp45gc0kl` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `FKea2ootw6b6bb0xt3ptl28bymv` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `tb_user_role` WRITE;
/*!40000 ALTER TABLE `tb_user_role` DISABLE KEYS */;

INSERT INTO `tb_user_role` (`user_id`, `role_id`)
VALUES
	(1,1),
	(2,2),
	(12,2);

/*!40000 ALTER TABLE `tb_user_role` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
