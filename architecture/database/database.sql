-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.21-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2015-04-08 05:29:25
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for ticket_operator_database
DROP DATABASE IF EXISTS `ticket_operator_database`;
CREATE DATABASE IF NOT EXISTS `ticket_operator_database` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ticket_operator_database`;


-- Dumping structure for table ticket_operator_database.movie
DROP TABLE IF EXISTS `movie`;
CREATE TABLE IF NOT EXISTS `movie` (
  `movieId` int(10) NOT NULL AUTO_INCREMENT,
  `movieName` varchar(50) DEFAULT NULL,
  `description` longtext,
  `country` varchar(50) DEFAULT NULL,
  `actors` varchar(50) DEFAULT NULL,
  `releaseDate` date DEFAULT NULL,
  PRIMARY KEY (`movieId`),
  UNIQUE KEY `movieName` (`movieName`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- Dumping data for table ticket_operator_database.movie: ~7 rows (approximately)
DELETE FROM `movie`;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` (`movieId`, `movieName`, `description`, `country`, `actors`, `releaseDate`) VALUES
	(1, 'Проект А', 'Гонконг начала 20 века. На китайском море свирепствуют пираты под предводительством зловещего пирата Ло. Все свои надежды правительство возлагает на специально разработанный план борьбы с пиратами под кодовым названием Проект А', 'Китай', 'Джеки Чан, Саммо Хунг', '2014-12-02'),
	(2, 'Час расплаты', 'Талантливый ученый Майкл Дженнингс принимает участие в разработке специальных научных проектов крупных мировых корпораций. Последняя его работа наиболее сложна и секретна. По итогам работы над ней он должен получить 92 миллиона долларов, после чего пройти процедуру очистки памяти от момента начала работ до их завершения. Завершив работу и потеряв память, Майкл получает не чек, а лишь пакет с набором казалось бы случайных предметов. Ему говорят, что он нарушил контракт и оштрафован на сумму гонорара. С помощью полученных предметов Майкл начинает восстанавливать картину прошлого, при этом защищаясь от людей, нанявших его — ведь им совсем не выгодно, чтобы он вспомнил…', 'США', 'Бен Аффлек', '2014-12-03'),
	(7, 'gtgfd', '        \r\n    ', 'dfgd', '', NULL),
	(9, 'Роб би Гуд', 'Главные герои этого комедийного боевика - три профессиональных вор, которые спланировали весьма опасное и сложное дело. И они готовы рискнуть, ведь если все получится -  они богачи. Но никто из них подумать не  мог, что в их деле появится малолетний ребенок, который и ходить то не умеет. И непонятно почему он все время орет, все время куда- то ползет. И как отделаться от него??? Возможности такой не предоставляется. Выход один -  брать его с собой на дело...', 'Гонконг', 'Джеки Чан, Луис Ку', '2015-01-15'),
	(10, 'Разборки в Бронксе', 'Кеунг, полицейский из Гонконга, приезжает к своему дяде в Америку на свадьбу. Дядя как раз продал свой супермаркет красивой китаянке, ни слова не сказав новой хозяйке о том, что на магазин имеют виды бандиты, и уехал. Кеунг, конечно же, заступается за симпатичную бизнес-леди и устраивает рэкетирам разборку. Но на этом подвиги азиатского полицейского в нью-йоркских кварталах не заканчиваются! Кеунг начинает жесткое противостояние гангстерам в деле о пропаже бриллиантов        \r\n    ', 'Китай', 'Джеки Чан', '2010-12-11'),
	(11, 'Клинок дракона', '', 'Китай', 'Джеки Чан', '2015-02-19'),
	(12, 'gfdg', '', 'dfddfd', '', '2015-04-08');
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;


-- Dumping structure for table ticket_operator_database.orderstatus
DROP TABLE IF EXISTS `orderstatus`;
CREATE TABLE IF NOT EXISTS `orderstatus` (
  `orderStatusId` int(10) NOT NULL DEFAULT '0',
  `orderStatus` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`orderStatusId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table ticket_operator_database.orderstatus: ~4 rows (approximately)
DELETE FROM `orderstatus`;
/*!40000 ALTER TABLE `orderstatus` DISABLE KEYS */;
INSERT INTO `orderstatus` (`orderStatusId`, `orderStatus`) VALUES
	(0, 'Забронирован'),
	(1, 'Получен'),
	(2, 'Отменен'),
	(3, 'Аннулирован');
/*!40000 ALTER TABLE `orderstatus` ENABLE KEYS */;


-- Dumping structure for table ticket_operator_database.place
DROP TABLE IF EXISTS `place`;
CREATE TABLE IF NOT EXISTS `place` (
  `placeId` int(10) NOT NULL AUTO_INCREMENT,
  `row` int(10) DEFAULT NULL,
  `place` int(10) DEFAULT NULL,
  PRIMARY KEY (`placeId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Dumping data for table ticket_operator_database.place: ~8 rows (approximately)
DELETE FROM `place`;
/*!40000 ALTER TABLE `place` DISABLE KEYS */;
INSERT INTO `place` (`placeId`, `row`, `place`) VALUES
	(1, 1, 1),
	(2, 1, 2),
	(3, 1, 3),
	(4, 1, 4),
	(5, 1, 5),
	(6, 2, 1),
	(7, 2, 2),
	(8, 2, 3),
	(9, 2, 4),
	(10, 2, 5);
/*!40000 ALTER TABLE `place` ENABLE KEYS */;


-- Dumping structure for table ticket_operator_database.role
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `roleId` int(10) NOT NULL,
  `role` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table ticket_operator_database.role: ~2 rows (approximately)
DELETE FROM `role`;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`roleId`, `role`) VALUES
	(0, 'admin'),
	(1, 'user');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;


-- Dumping structure for table ticket_operator_database.seance
DROP TABLE IF EXISTS `seance`;
CREATE TABLE IF NOT EXISTS `seance` (
  `seanceId` int(10) NOT NULL AUTO_INCREMENT,
  `movieId` int(10) DEFAULT NULL,
  `seanceDate` date DEFAULT NULL,
  `seanceTime` time DEFAULT NULL,
  `price` int(10) DEFAULT NULL,
  PRIMARY KEY (`seanceId`),
  UNIQUE KEY `seanceDate_seanceTime` (`seanceDate`,`seanceTime`),
  KEY `FK_seance_movie` (`movieId`),
  CONSTRAINT `FK_seance_movie` FOREIGN KEY (`movieId`) REFERENCES `movie` (`movieId`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- Dumping data for table ticket_operator_database.seance: ~22 rows (approximately)
DELETE FROM `seance`;
/*!40000 ALTER TABLE `seance` DISABLE KEYS */;
INSERT INTO `seance` (`seanceId`, `movieId`, `seanceDate`, `seanceTime`, `price`) VALUES
	(1, 1, '2014-12-09', '03:08:27', 232324),
	(2, 2, '2014-12-04', '03:08:27', 500000),
	(5, 1, '2014-12-05', '23:00:00', 200000),
	(6, 1, '2014-12-06', '03:02:00', 20000),
	(9, 1, '2014-12-11', '10:00:00', 10000),
	(10, 2, '2014-12-19', '11:00:00', 50000),
	(11, 1, '2014-12-10', '10:00:00', 433),
	(13, 7, '2014-11-04', '10:00:00', 43243),
	(14, 7, '2014-12-21', '10:00:00', 5),
	(15, 7, '2014-12-25', '10:00:00', 434),
	(16, 9, '2015-01-22', '18:00:00', 20000),
	(17, 9, '2015-01-22', '20:00:00', 20000),
	(19, 9, '2015-02-12', '10:00:00', 20000),
	(20, 9, '2015-03-13', '20:00:00', 20000),
	(23, 1, '2015-03-23', '10:00:00', 12000),
	(24, 1, '2015-03-20', '10:00:00', 12000),
	(26, 9, '2015-03-17', '13:00:00', 13000),
	(27, 1, '2015-03-25', '13:00:00', 12000),
	(29, 11, '2015-04-22', '22:00:00', 20000),
	(31, 11, '2015-04-18', '11:00:00', 20000),
	(35, 9, '2015-04-11', '13:00:00', 20000),
	(38, 11, '2015-04-07', '10:00:00', 10000);
/*!40000 ALTER TABLE `seance` ENABLE KEYS */;


-- Dumping structure for table ticket_operator_database.ticket
DROP TABLE IF EXISTS `ticket`;
CREATE TABLE IF NOT EXISTS `ticket` (
  `ticketId` int(10) NOT NULL AUTO_INCREMENT,
  `seanceId` int(10),
  `placeId` int(10) DEFAULT NULL,
  PRIMARY KEY (`ticketId`),
  KEY `FK_ticket_seance` (`seanceId`),
  CONSTRAINT `FK_ticket_seance` FOREIGN KEY (`seanceId`) REFERENCES `seance` (`seanceId`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=351 DEFAULT CHARSET=utf8;

-- Dumping data for table ticket_operator_database.ticket: ~221 rows (approximately)
DELETE FROM `ticket`;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` (`ticketId`, `seanceId`, `placeId`) VALUES
	(1, 1, 1),
	(2, 1, 2),
	(3, 1, 3),
	(4, 1, 4),
	(5, 1, 5),
	(6, 1, 6),
	(7, 1, 7),
	(8, 1, 8),
	(9, 1, 9),
	(10, 1, 10),
	(11, 2, 1),
	(12, 2, 2),
	(13, 2, 3),
	(14, 2, 4),
	(15, 2, 5),
	(16, 2, 6),
	(17, 2, 7),
	(18, 2, 8),
	(19, 2, 9),
	(20, 2, 10),
	(41, 5, 1),
	(42, 5, 2),
	(43, 5, 3),
	(44, 5, 4),
	(45, 5, 5),
	(46, 5, 6),
	(47, 5, 7),
	(48, 5, 8),
	(49, 5, 9),
	(50, 5, 10),
	(51, 6, 1),
	(52, 6, 2),
	(53, 6, 3),
	(54, 6, 4),
	(55, 6, 5),
	(56, 6, 6),
	(57, 6, 7),
	(58, 6, 8),
	(59, 6, 9),
	(60, 6, 10),
	(81, 9, 1),
	(82, 9, 2),
	(83, 9, 3),
	(84, 9, 4),
	(85, 9, 5),
	(86, 9, 6),
	(87, 9, 7),
	(88, 9, 8),
	(89, 9, 9),
	(90, 9, 10),
	(91, 10, 1),
	(92, 10, 2),
	(93, 10, 3),
	(94, 10, 4),
	(95, 10, 5),
	(96, 10, 6),
	(97, 10, 7),
	(98, 10, 8),
	(99, 10, 9),
	(100, 10, 10),
	(101, 11, 1),
	(102, 11, 2),
	(103, 11, 3),
	(104, 11, 4),
	(105, 11, 5),
	(106, 11, 6),
	(107, 11, 7),
	(108, 11, 8),
	(109, 11, 9),
	(110, 11, 10),
	(111, 13, 1),
	(112, 13, 2),
	(113, 13, 3),
	(114, 13, 4),
	(115, 13, 5),
	(116, 13, 6),
	(117, 13, 7),
	(118, 13, 8),
	(119, 13, 9),
	(120, 13, 10),
	(121, 14, 1),
	(122, 14, 2),
	(123, 14, 3),
	(124, 14, 4),
	(125, 14, 5),
	(126, 14, 6),
	(127, 14, 7),
	(128, 14, 8),
	(129, 14, 9),
	(130, 14, 10),
	(131, 15, 1),
	(132, 15, 2),
	(133, 15, 3),
	(134, 15, 4),
	(135, 15, 5),
	(136, 15, 6),
	(137, 15, 7),
	(138, 15, 8),
	(139, 15, 9),
	(140, 15, 10),
	(141, 16, 1),
	(142, 16, 2),
	(143, 16, 3),
	(144, 16, 4),
	(145, 16, 5),
	(146, 16, 6),
	(147, 16, 7),
	(148, 16, 8),
	(149, 16, 9),
	(150, 16, 10),
	(151, 17, 1),
	(152, 17, 2),
	(153, 17, 3),
	(154, 17, 4),
	(155, 17, 5),
	(156, 17, 6),
	(157, 17, 7),
	(158, 17, 8),
	(159, 17, 9),
	(160, 17, 10),
	(171, 19, 1),
	(172, 19, 2),
	(173, 19, 3),
	(174, 19, 4),
	(175, 19, 5),
	(176, 19, 6),
	(177, 19, 7),
	(178, 19, 8),
	(179, 19, 9),
	(180, 19, 10),
	(181, 20, 1),
	(182, 20, 2),
	(183, 20, 3),
	(184, 20, 4),
	(185, 20, 5),
	(186, 20, 6),
	(187, 20, 7),
	(188, 20, 8),
	(189, 20, 9),
	(190, 20, 10),
	(211, 23, 1),
	(212, 23, 2),
	(213, 23, 3),
	(214, 23, 4),
	(215, 23, 5),
	(216, 23, 6),
	(217, 23, 7),
	(218, 23, 8),
	(219, 23, 9),
	(220, 23, 10),
	(221, 24, 1),
	(222, 24, 2),
	(223, 24, 3),
	(224, 24, 4),
	(225, 24, 5),
	(226, 24, 6),
	(227, 24, 7),
	(228, 24, 8),
	(229, 24, 9),
	(230, 24, 10),
	(231, 26, 1),
	(232, 26, 2),
	(233, 26, 3),
	(234, 26, 4),
	(235, 26, 5),
	(236, 26, 6),
	(237, 26, 7),
	(238, 26, 8),
	(239, 26, 9),
	(240, 26, 10),
	(241, 27, 1),
	(242, 27, 2),
	(243, 27, 3),
	(244, 27, 4),
	(245, 27, 5),
	(246, 27, 6),
	(247, 27, 7),
	(248, 27, 8),
	(249, 27, 9),
	(250, 27, 10),
	(261, 29, 1),
	(262, 29, 2),
	(263, 29, 3),
	(264, 29, 4),
	(265, 29, 5),
	(266, 29, 6),
	(267, 29, 7),
	(268, 29, 8),
	(269, 29, 9),
	(270, 29, 10),
	(281, 31, 1),
	(282, 31, 2),
	(283, 31, 3),
	(284, 31, 4),
	(285, 31, 5),
	(286, 31, 6),
	(287, 31, 7),
	(288, 31, 8),
	(289, 31, 9),
	(290, 31, 10),
	(311, 35, 1),
	(312, 35, 2),
	(313, 35, 3),
	(314, 35, 4),
	(315, 35, 5),
	(316, 35, 6),
	(317, 35, 7),
	(318, 35, 8),
	(319, 35, 9),
	(320, 35, 10),
	(341, 38, 1),
	(342, 38, 2),
	(343, 38, 3),
	(344, 38, 4),
	(345, 38, 5),
	(346, 38, 6),
	(347, 38, 7),
	(348, 38, 8),
	(349, 38, 9),
	(350, 38, 10);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;


-- Dumping structure for table ticket_operator_database.ticketorder
DROP TABLE IF EXISTS `ticketorder`;
CREATE TABLE IF NOT EXISTS `ticketorder` (
  `orderId` int(10) NOT NULL AUTO_INCREMENT,
  `ticketId` int(10) DEFAULT NULL,
  `userId` int(10) DEFAULT NULL,
  `orderDate` date DEFAULT NULL,
  `receivingDate` date DEFAULT NULL,
  `orderStatusId` int(10) DEFAULT '0',
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- Dumping data for table ticket_operator_database.ticketorder: ~17 rows (approximately)
DELETE FROM `ticketorder`;
/*!40000 ALTER TABLE `ticketorder` DISABLE KEYS */;
INSERT INTO `ticketorder` (`orderId`, `ticketId`, `userId`, `orderDate`, `receivingDate`, `orderStatusId`) VALUES
	(1, 2, 1, '2014-12-05', '2014-12-07', 1),
	(3, 7, 1, '2014-12-06', NULL, 3),
	(4, 1, 2, '2014-12-07', NULL, 2),
	(5, 81, 2, '2014-12-10', '2015-01-14', 1),
	(6, 82, 1, '2014-12-13', '2014-12-20', 1),
	(7, 91, 2, '2014-12-13', '2015-01-21', 1),
	(8, 92, 2, '2014-12-13', '2015-01-25', 1),
	(9, 131, 1, '2014-12-23', NULL, 3),
	(10, 132, 1, '2014-12-23', NULL, 0),
	(11, 141, 2, '2015-01-21', NULL, 0),
	(12, 171, 1, '2015-01-25', '2015-01-27', 1),
	(13, 181, 1, '2015-03-01', NULL, 3),
	(14, 181, 2, '2015-03-01', NULL, 0),
	(15, 241, 1, '2015-03-02', '2015-04-03', 1),
	(16, 261, 3, '2015-04-06', NULL, 2),
	(17, 263, 3, '2015-04-06', '2015-04-06', 1),
	(18, 261, 2, '2015-04-06', NULL, 0),
	(19, 312, 2, '2015-04-06', NULL, 0),
	(20, 281, 2, '2015-04-06', NULL, 0);
/*!40000 ALTER TABLE `ticketorder` ENABLE KEYS */;


-- Dumping structure for table ticket_operator_database.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `userId` int(10) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table ticket_operator_database.user: ~2 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`userId`, `login`, `password`, `roleId`, `email`, `lastname`, `firstname`) VALUES
	(1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 0, '', 'Харитонова', 'Вероника'),
	(2, 'ver', '81dc9bdb52d04dc20036dbd8313ed055', 1, 'veronica48@mail.ru', 'Вайраух', 'Вероника'),
	(3, 'arsty', '81dc9bdb52d04dc20036dbd8313ed055', 1, 'arst@gmail.com', 'Иванов', 'Арсений');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
