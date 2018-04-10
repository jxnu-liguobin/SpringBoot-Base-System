/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : managesystemdb

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2018-04-10 11:17:52
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `tb_books`
-- ----------------------------
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

-- ----------------------------
-- Records of tb_books
-- ----------------------------
INSERT INTO `tb_books` VALUES ('441f54dbd7604f41bc575b75ad1ff640', '??', '????', '666', '???', '94');
INSERT INTO `tb_books` VALUES ('4a2592ca1ac44c7da96af42338553b73', '??????', '????', '50', '???', '50');
INSERT INTO `tb_books` VALUES ('75928c502c034cc1a76fb0f0e6cac5e1', '???', '????', '80', '???', '80');
INSERT INTO `tb_books` VALUES ('8a3c31d8b80f4083b8da2840d55bda76', 'ssss', 'sss', '1', 'sss', '1');
INSERT INTO `tb_books` VALUES ('e008401787cc4d39a979abe2c23cc318', '??????', '????', '90', '???', '90');
INSERT INTO `tb_books` VALUES ('e3af1116623c4a55aa01018b6b043bcb', 'dddd', 'dddd', '55', 'dwdwde', '53');
INSERT INTO `tb_books` VALUES ('ff51aab870894e59b8f70e008508a615', 'dddd', 'dddd', '11', 'dddd', '11');

-- ----------------------------
-- Table structure for `tb_borrow_books`
-- ----------------------------
DROP TABLE IF EXISTS `tb_borrow_books`;
CREATE TABLE `tb_borrow_books` (
  `user_id` int(11) NOT NULL,
  `book_id` varchar(50) NOT NULL DEFAULT '',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `book_name` varchar(255) NOT NULL,
  `book_author` varchar(255) DEFAULT NULL,
  `book_press` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_borrow_books
-- ----------------------------
INSERT INTO `tb_borrow_books` VALUES ('1', '441f54dbd7604f41bc575b75ad1ff640', '2018-03-01 12:09:46', '??', '???', '????');
INSERT INTO `tb_borrow_books` VALUES ('1', '75928c502c034cc1a76fb0f0e6cac5e1', '2018-04-10 11:17:15', '???', '???', '????');
INSERT INTO `tb_borrow_books` VALUES ('1', 'e3af1116623c4a55aa01018b6b043bcb', '2018-04-10 10:20:22', 'dddd', 'dwdwde', 'dddd');

-- ----------------------------
-- Table structure for `tb_resource`
-- ----------------------------
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_resource
-- ----------------------------
INSERT INTO `tb_resource` VALUES ('1', '2018-03-01 13:56:51', '????', null, '0', '2', '????', '1', 'system:user:index', '/admin/user/index', '1', '2018-04-01 13:59:01', null);
INSERT INTO `tb_resource` VALUES ('2', '2018-03-01 13:56:51', '????', null, '0', '3', '????', '1', 'system:user:edit', '/admin/user/edit*', '2', '2018-04-01 16:26:42', '1');
INSERT INTO `tb_resource` VALUES ('3', '2018-03-01 16:48:48', '????', null, '0', '3', '????', '2', 'system:user:add', '/admin/user/add', '2', '2018-04-01 16:49:26', '1');
INSERT INTO `tb_resource` VALUES ('4', '2018-03-01 16:48:48', '????', null, '0', '3', '????', '3', 'system:user:deleteBatch', '/admin/user/deleteBatch', '2', '2018-04-01 14:11:41', '1');
INSERT INTO `tb_resource` VALUES ('5', '2018-03-01 16:48:48', '????', null, '0', '3', '????', '4', 'system:user:grant', '/admin/user/grant/**', '2', '2018-04-01 14:11:51', '1');
INSERT INTO `tb_resource` VALUES ('6', '2018-03-01 16:45:10', '????', null, '0', '2', '????', '2', 'system:role:index', '/admin/role/index', '1', '2018-04-01 16:46:52', null);
INSERT INTO `tb_resource` VALUES ('7', '2018-03-01 16:47:02', '????', null, '0', '3', '????', '1', 'system:role:edit', '/admin/role/edit*', '2', '2018-04-01 10:24:06', '1');
INSERT INTO `tb_resource` VALUES ('8', '2018-03-01 16:47:23', '????', null, '0', '3', '????', '2', 'system:role:add', '/admin/role/add', '2', '2018-04-01 16:49:16', '6');
INSERT INTO `tb_resource` VALUES ('9', '2018-03-01 16:47:23', '????', null, '0', '3', '????', '3', 'system:role:deleteBatch', '/admin/role/deleteBatch', '2', '2018-04-01 14:12:03', '6');
INSERT INTO `tb_resource` VALUES ('10', '2018-03-01 16:47:23', '????', null, '0', '3', '????', '4', 'system:role:grant', '/admin/role/grant/**', '2', '2018-04-01 14:12:11', '6');
INSERT INTO `tb_resource` VALUES ('11', '2018-03-01 11:21:12', '????', null, '0', '2', '????', '3', 'system:resource:index', '/admin/resource/index', '1', '2018-04-01 11:21:42', null);
INSERT INTO `tb_resource` VALUES ('12', '2018-03-01 11:21:52', '????', null, '0', '3', '????', '1', 'system:resource:edit', '/admin/resource/edit*', '2', '2018-04-01 11:22:36', '11');
INSERT INTO `tb_resource` VALUES ('13', '2018-03-01 11:21:54', '????', null, '0', '3', '????', '2', 'system:resource:add', '/admin/resource/add', '2', '2018-04-01 11:22:39', '11');
INSERT INTO `tb_resource` VALUES ('14', '2018-03-01 11:21:54', '????', null, '0', '3', '????', '3', 'system:resource:deleteBatch', '/admin/resource/deleteBatch', '2', '2018-04-01 14:12:31', '11');
INSERT INTO `tb_resource` VALUES ('15', '2018-03-01 11:21:54', '????', null, '0', '2', '????', '4', 'system:books:book_management', '/admin/books/book_management', '1', '2018-04-01 14:12:31', null);
INSERT INTO `tb_resource` VALUES ('16', '2018-03-01 09:45:58', 'SQL????', null, '0', '2', 'SQL??', '2', 'system:resource:druid', '/druid', '1', '2018-04-01 09:45:58', '11');
INSERT INTO `tb_resource` VALUES ('17', '2018-04-02 11:37:23', '???????????', null, '0', '3', '????', '2', 'system:user:resume', '/admin/user/resume', '2', '2018-04-02 11:37:23', '1');

-- ----------------------------
-- Table structure for `tb_role`
-- ----------------------------
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', '2017-01-09 17:25:30', '?????', 'administrator', 'administrator', '0', '2017-01-09 17:26:25');
INSERT INTO `tb_role` VALUES ('2', '2017-04-20 22:50:05', '????', 'student', 'student', '0', '2017-04-20 22:50:05');
INSERT INTO `tb_role` VALUES ('3', '2018-04-10 10:25:21', 'sssssss', 'ssssss', 'ssssss', '1', '2018-04-10 10:25:47');

-- ----------------------------
-- Table structure for `tb_role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_resource`;
CREATE TABLE `tb_role_resource` (
  `role_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`resource_id`),
  KEY `FK868kc8iic48ilv5npa80ut6qo` (`resource_id`),
  CONSTRAINT `FK7ffc7h6obqxflhj1aq1mk20jk` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`),
  CONSTRAINT `FK868kc8iic48ilv5npa80ut6qo` FOREIGN KEY (`resource_id`) REFERENCES `tb_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role_resource
-- ----------------------------
INSERT INTO `tb_role_resource` VALUES ('1', '1');
INSERT INTO `tb_role_resource` VALUES ('1', '2');
INSERT INTO `tb_role_resource` VALUES ('1', '3');
INSERT INTO `tb_role_resource` VALUES ('1', '4');
INSERT INTO `tb_role_resource` VALUES ('1', '5');
INSERT INTO `tb_role_resource` VALUES ('1', '6');
INSERT INTO `tb_role_resource` VALUES ('1', '7');
INSERT INTO `tb_role_resource` VALUES ('1', '8');
INSERT INTO `tb_role_resource` VALUES ('1', '9');
INSERT INTO `tb_role_resource` VALUES ('1', '10');
INSERT INTO `tb_role_resource` VALUES ('1', '11');
INSERT INTO `tb_role_resource` VALUES ('1', '12');
INSERT INTO `tb_role_resource` VALUES ('1', '13');
INSERT INTO `tb_role_resource` VALUES ('1', '14');
INSERT INTO `tb_role_resource` VALUES ('1', '15');
INSERT INTO `tb_role_resource` VALUES ('1', '16');
INSERT INTO `tb_role_resource` VALUES ('1', '17');

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'admin', 'admin', '2017-01-09 17:26:41', '0', '0', 'S6557FPFLS0IL5VMMIA5AMUBJ', '13065120502', '2018-04-10 11:16:45');
INSERT INTO `tb_user` VALUES ('2', '1418140303', 'James', '2017-04-20 22:48:34', '0', '0', '37UKUUGEAAS613NRTV84V1409O', '15820582502', '2018-04-10 11:13:01');
INSERT INTO `tb_user` VALUES ('12', '1418140304', 'Jon', '2017-05-04 19:31:10', '0', '0', '23QPTHDNUT5MB5UPK1RI1GA1IQ', '15825624789', '2018-04-04 10:37:09');
INSERT INTO `tb_user` VALUES ('14', '1526705079', 'LGB', '2018-04-03 11:59:19', '0', '0', '3931MUEQD1939MQMLM4AISPVNE', '13065120502', '2018-04-03 11:59:19');
INSERT INTO `tb_user` VALUES ('15', '1526705078', 'LGG', '2018-04-03 11:59:38', '0', '0', '3IH0UBLIE3JLE6UUF9OJPS16UM', '13065120502', '2018-04-10 10:25:00');
INSERT INTO `tb_user` VALUES ('16', '1526705077', '???', '2018-04-04 10:28:26', '1', '0', '1N94J5RLMN4R1R1RG1JO9SUOGA', '13065120502', '2018-04-04 10:28:26');
INSERT INTO `tb_user` VALUES ('19', '1526705055', '??', '2018-04-09 10:36:15', '0', '0', '3931MUEQD1939MQMLM4AISPVNE', '13065120502', '2018-04-10 11:13:51');
INSERT INTO `tb_user` VALUES ('20', '1526705072', 'liguobin', '2018-04-10 10:44:40', '0', '0', '1N94J5RLMN4R1R1RG1JO9SUOGA', '13065120502', '2018-04-10 10:44:40');
INSERT INTO `tb_user` VALUES ('21', 'dddddd', 'SB??', '2018-04-10 11:13:34', '0', '0', 'Q206IO3DB372463JTEDR2MD13', '15202526589', '2018-04-10 11:13:34');

-- ----------------------------
-- Table structure for `tb_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKea2ootw6b6bb0xt3ptl28bymv` (`role_id`),
  CONSTRAINT `FK7vn3h53d0tqdimm8cp45gc0kl` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `FKea2ootw6b6bb0xt3ptl28bymv` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES ('1', '1');
INSERT INTO `tb_user_role` VALUES ('12', '1');
INSERT INTO `tb_user_role` VALUES ('2', '2');
INSERT INTO `tb_user_role` VALUES ('12', '2');
INSERT INTO `tb_user_role` VALUES ('14', '2');
INSERT INTO `tb_user_role` VALUES ('15', '2');
INSERT INTO `tb_user_role` VALUES ('16', '2');
INSERT INTO `tb_user_role` VALUES ('19', '2');
INSERT INTO `tb_user_role` VALUES ('20', '2');
INSERT INTO `tb_user_role` VALUES ('21', '2');
