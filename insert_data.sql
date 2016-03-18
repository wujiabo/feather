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
-- Dumping data for table feather.t_group: ~0 rows (approximately)
/*!40000 ALTER TABLE `t_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_group` ENABLE KEYS */;

-- Dumping data for table feather.t_group_role: ~0 rows (approximately)
/*!40000 ALTER TABLE `t_group_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_group_role` ENABLE KEYS */;

-- Dumping data for table feather.t_menu: ~6 rows (approximately)
/*!40000 ALTER TABLE `t_menu` DISABLE KEYS */;
INSERT INTO `t_menu` (`menu_id`, `menu_pid`, `menu_url`, `menu_name`, `seq`, `state`) VALUES
	(1, NULL, '', '系统管理', 1, '0'),
	(2, 1, '/userMgmt/view', '用户管理', 1, '0'),
	(3, 1, '/groupMgmt/view', '用户组管理', 2, '0'),
	(4, 1, '/roleMgmt/view', '角色管理', 3, '0'),
	(5, 1, '/permissionMgmt/view', '权限管理', 4, '0'),
	(6, 1, '/menuMgmt/view', '菜单管理', 5, '0');
/*!40000 ALTER TABLE `t_menu` ENABLE KEYS */;

-- Dumping data for table feather.t_permission: ~1 rows (approximately)
/*!40000 ALTER TABLE `t_permission` DISABLE KEYS */;
INSERT INTO `t_permission` (`permission_id`, `permission_pid`, `permission_code`, `permission_name`, `state`) VALUES
	(1, NULL, '*', '所有权限', '0');
/*!40000 ALTER TABLE `t_permission` ENABLE KEYS */;

-- Dumping data for table feather.t_role: ~1 rows (approximately)
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` (`role_id`, `role_code`, `role_name`, `state`) VALUES
	(1, 'ADMIN', '超级管理员', '0');
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;

-- Dumping data for table feather.t_role_menu: ~6 rows (approximately)
/*!40000 ALTER TABLE `t_role_menu` DISABLE KEYS */;
INSERT INTO `t_role_menu` (`role_id`, `menu_id`) VALUES
	(1, 1),
	(1, 2),
	(1, 3),
	(1, 4),
	(1, 5),
	(1, 6);
/*!40000 ALTER TABLE `t_role_menu` ENABLE KEYS */;

-- Dumping data for table feather.t_role_permission: ~1 rows (approximately)
/*!40000 ALTER TABLE `t_role_permission` DISABLE KEYS */;
INSERT INTO `t_role_permission` (`role_id`, `permission_id`) VALUES
	(1, 1);
/*!40000 ALTER TABLE `t_role_permission` ENABLE KEYS */;

-- Dumping data for table feather.t_user: ~1 rows (approximately)
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` (`user_id`, `user_name`, `screen_name`, `password`, `salt`, `state`, `create_time`, `update_time`) VALUES
	(1, 'admin', 'Admin', 'fdd2abb12ef67e9e7848709948b5f8bb', 'cb00dea8c6d81cb8422cb19383c0ec79', '0', '2016-03-18 10:19:02', '2016-03-18 10:19:03');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;

-- Dumping data for table feather.t_user_group: ~0 rows (approximately)
/*!40000 ALTER TABLE `t_user_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user_group` ENABLE KEYS */;

-- Dumping data for table feather.t_user_role: ~1 rows (approximately)
/*!40000 ALTER TABLE `t_user_role` DISABLE KEYS */;
INSERT INTO `t_user_role` (`user_id`, `role_id`) VALUES
	(1, 1);
/*!40000 ALTER TABLE `t_user_role` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
