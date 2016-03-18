-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.23 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table feather.t_group
DROP TABLE IF EXISTS `t_group`;
CREATE TABLE IF NOT EXISTS `t_group` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_pid` int(11) DEFAULT NULL,
  `group_code` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `group_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `state` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Data exporting was unselected.


-- Dumping structure for table feather.t_group_role
DROP TABLE IF EXISTS `t_group_role`;
CREATE TABLE IF NOT EXISTS `t_group_role` (
  `group_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`group_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Data exporting was unselected.


-- Dumping structure for table feather.t_menu
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE IF NOT EXISTS `t_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_pid` int(11) DEFAULT NULL,
  `menu_url` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `menu_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `seq` int(5) DEFAULT NULL,
  `state` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Data exporting was unselected.


-- Dumping structure for table feather.t_permission
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE IF NOT EXISTS `t_permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_pid` int(11) DEFAULT NULL,
  `permission_code` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `permission_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `state` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Data exporting was unselected.


-- Dumping structure for table feather.t_role
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE IF NOT EXISTS `t_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `role_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `state` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Data exporting was unselected.


-- Dumping structure for table feather.t_role_menu
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE IF NOT EXISTS `t_role_menu` (
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Data exporting was unselected.


-- Dumping structure for table feather.t_role_permission
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE IF NOT EXISTS `t_role_permission` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Data exporting was unselected.


-- Dumping structure for table feather.t_user
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE IF NOT EXISTS `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `screen_name` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `salt` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `state` char(1) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Data exporting was unselected.


-- Dumping structure for table feather.t_user_group
DROP TABLE IF EXISTS `t_user_group`;
CREATE TABLE IF NOT EXISTS `t_user_group` (
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Data exporting was unselected.


-- Dumping structure for table feather.t_user_role
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE IF NOT EXISTS `t_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
