/*
MySQL Backup
Database: staysafe
Backup Time: 2020-12-16 21:54:46
*/

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `staysafe`.`accounts`;
DROP TABLE IF EXISTS `staysafe`.`orders`;
DROP TABLE IF EXISTS `staysafe`.`products`;
DROP PROCEDURE IF EXISTS `staysafe`.`DeleteOrders`;
DROP EVENT IF EXISTS `staysafe`.`evtCheckOrders`;
CREATE TABLE `accounts` (
  `id` int(11) NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` char(60) CHARACTER SET latin1 COLLATE latin1_bin NOT NULL,
  `firstName` varchar(35) NOT NULL,
  `lastName` varchar(35) NOT NULL,
  `age` int(11) NOT NULL,
  `email` varchar(60) NOT NULL,
  `telephoneNumber` varchar(12) NOT NULL,
  `role` varchar(15) NOT NULL,
  `picture` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`,`firstName`,`lastName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `orders` (
  `id` varchar(15) NOT NULL,
  `productID` int(11) NOT NULL,
  `customerID` int(11) NOT NULL,
  `staffID` int(11) DEFAULT NULL,
  `customerFName` varchar(15) NOT NULL,
  `customerLName` varchar(15) NOT NULL,
  `productName` varchar(40) NOT NULL,
  `productCategory` varchar(20) NOT NULL,
  `staffFName` varchar(15) DEFAULT NULL,
  `staffLName` varchar(15) DEFAULT NULL,
  `orderPrice` varchar(5) NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `tstamp` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_productID` (`productID`,`productName`,`productCategory`),
  KEY `FK_customerID` (`customerID`,`customerFName`,`customerLName`),
  KEY `FK_staffID` (`staffID`,`staffFName`,`staffLName`),
  CONSTRAINT `FK_customerID` FOREIGN KEY (`customerID`, `customerFName`, `customerLName`) REFERENCES `accounts` (`id`, `firstName`, `lastName`),
  CONSTRAINT `FK_productID` FOREIGN KEY (`productID`, `productName`, `productCategory`) REFERENCES `products` (`id`, `name`, `category`),
  CONSTRAINT `FK_staffID` FOREIGN KEY (`staffID`, `staffFName`, `staffLName`) REFERENCES `accounts` (`id`, `firstName`, `lastName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `category` varchar(20) NOT NULL,
  `price` decimal(13,2) NOT NULL,
  `calories` int(11) DEFAULT NULL,
  `allergies` text DEFAULT NULL,
  `availability` int(11) NOT NULL,
  `img` varchar(125) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`,`name`,`category`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE DEFINER=`owner`@`%` PROCEDURE `DeleteOrders`(orderID varchar(15))
BEGIN
	DELETE FROM orders
	WHERE id = orderID;
END;
CREATE DEFINER=`owner`@`%` EVENT `evtCheckOrders` ON SCHEDULE EVERY 15 MINUTE STARTS '2020-10-06 23:34:34' ON COMPLETION NOT PRESERVE ENABLE DO DELETE FROM orders
		WHERE tstamp <= NOW() - INTERVAL 15 MINUTE;;
BEGIN;
LOCK TABLES `staysafe`.`accounts` WRITE;
DELETE FROM `staysafe`.`accounts`;
INSERT INTO `staysafe`.`accounts` (`id`,`username`,`password`,`firstName`,`lastName`,`age`,`email`,`telephoneNumber`,`role`,`picture`) VALUES (0, '1801492', '$2a$11$1P7jTHANKGwerhoA6rzQMOiGHbOf0AbvnBAxEUbVNUqAhgGpRf6Tm', 'Edward', 'Patch', 21, '1801492@student.uwtsd.ac.uk', '07533 897799', 'Student', 'anime1.jpg'),(1, 'paul.payne', '$2a$11$QuzaKTayy55oj6hsiDq3HuYkZecnBID15uI4hoZ88kWH7K0xHZdES', 'Paul', 'Payne', 31, 'paul.payne@uwtsd.ac.uk', '01792 651423', 'Staff', 'anime2.png');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `staysafe`.`orders` WRITE;
DELETE FROM `staysafe`.`orders`;
INSERT INTO `staysafe`.`orders` (`id`,`productID`,`customerID`,`staffID`,`customerFName`,`customerLName`,`productName`,`productCategory`,`staffFName`,`staffLName`,`orderPrice`,`active`,`tstamp`) VALUES ('ORD-1-0-0-0', 1, 0, NULL, 'Edward', 'Patch', 'Cappuccino (Primo)', 'Drinks', NULL, NULL, '£2.25', b'1', '2020-12-16 21:53:43'),('ORD-2-3-0-3', 2, 0, NULL, 'Edward', 'Patch', 'Cappuccino (Medio)', 'Drinks', NULL, NULL, '£2.55', b'0', '2020-12-16 21:53:49'),('ORD-7-1-0-1', 7, 0, NULL, 'Edward', 'Patch', 'Standard Breakfast', 'Food', NULL, NULL, '£3.50', b'1', '2020-12-16 21:53:45'),('ORD-7-4-0-4', 7, 0, NULL, 'Edward', 'Patch', 'Standard Breakfast', 'Food', NULL, NULL, '£3.50', b'0', '2020-12-16 21:53:54'),('ORD-8-2-0-2', 8, 0, NULL, 'Edward', 'Patch', 'Walkers Crisps (Cheese and Onion)', 'Snack', NULL, NULL, '£1.65', b'0', '2020-12-16 21:53:47'),('ORD-8-4-0-4', 8, 0, NULL, 'Edward', 'Patch', 'Walkers Crisps (Cheese and Onion)', 'Snack', NULL, NULL, '£1.65', b'0', '2020-12-16 21:53:56'),('ORD-9-3-0-3', 9, 0, NULL, 'Edward', 'Patch', 'Cadbury Creme Eggs', 'Snack', NULL, NULL, '£0.75', b'1', '2020-12-16 21:53:52');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `staysafe`.`products` WRITE;
DELETE FROM `staysafe`.`products`;
INSERT INTO `staysafe`.`products` (`id`,`name`,`category`,`price`,`calories`,`allergies`,`availability`,`img`) VALUES (0, 'Flat White (Primo)', 'Drinks', 2.40, 56, 'Vegetarian, Gluten Free (GF)', -1, 'N/A'),(1, 'Cappuccino (Primo)', 'Drinks', 2.25, 61, 'Vegetarian, Gluten Free (GF)', -1, 'N/A'),(2, 'Cappuccino (Medio)', 'Drinks', 2.55, 56, 'Vegetarian, Gluten Free (GF)', -1, 'N/A'),(3, 'Cappuccino (Massimo)', 'Drinks', 2.65, 52, 'Vegetarian, Gluten Free (GF)', -1, 'N/A'),(4, 'Caffe Latte (Primo)', 'Drinks', 2.15, 41, 'Vegetarian, Gluten Free (GF)', -1, 'N/A'),(5, 'Caffe Latte (Massimo)', 'Drinks', 2.65, 40, 'Vegetarian, Gluten Free (GF)', -1, 'N/A'),(6, 'Small Breakfast', 'Food', 1.50, 460, '', -1, 'N/A'),(7, 'Standard Breakfast', 'Food', 3.50, 600, '', -1, 'N/A'),(8, 'Walkers Crisps (Cheese and Onion)', 'Snack', 1.65, 75, 'Vegetarian', 38, 'N/A'),(9, 'Cadbury Creme Eggs', 'Snack', 0.75, 56, 'Eggs, Dairy', 25, 'uploads/s-l640.jpg');
UNLOCK TABLES;
COMMIT;
CREATE DEFINER = `owner`@`%` TRIGGER `triRecordTimestamp` BEFORE INSERT ON `orders` FOR EACH ROW BEGIN 
	IF NEW.tstamp IS NULL THEN
		SET NEW.tstamp = CURRENT_TIME;
	END IF;
END;;
CREATE DEFINER = `owner`@`%` TRIGGER `triActive` BEFORE INSERT ON `orders` FOR EACH ROW BEGIN 
	IF NEW.active IS NULL THEN
		SET NEW.active = 0;
	END IF;
END;;
