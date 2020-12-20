/*
 Navicat Premium Data Transfer

 Source Server         : Stay-Safe
 Source Server Type    : MySQL
 Source Server Version : 100414
 Source Host           : localhost:3306
 Source Schema         : staysafe

 Target Server Type    : MySQL
 Target Server Version : 100414
 File Encoding         : 65001

 Date: 16/12/2020 21:54:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for accounts
-- ----------------------------
DROP TABLE IF EXISTS `accounts`;
CREATE TABLE `accounts`  (
  `id` int(11) NOT NULL,
  `username` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `password` char(60) CHARACTER SET latin1 COLLATE latin1_bin NOT NULL,
  `firstName` varchar(35) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `lastName` varchar(35) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `age` int(11) NOT NULL,
  `email` varchar(60) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `telephoneNumber` varchar(12) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `role` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `picture` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`id`, `firstName`, `lastName`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of accounts
-- ----------------------------
INSERT INTO `accounts` VALUES (0, '1801492', '$2a$11$1P7jTHANKGwerhoA6rzQMOiGHbOf0AbvnBAxEUbVNUqAhgGpRf6Tm', 'Edward', 'Patch', 21, '1801492@student.uwtsd.ac.uk', '07533 897799', 'Student', 'anime1.jpg');
INSERT INTO `accounts` VALUES (1, 'paul.payne', '$2a$11$QuzaKTayy55oj6hsiDq3HuYkZecnBID15uI4hoZ88kWH7K0xHZdES', 'Paul', 'Payne', 31, 'paul.payne@uwtsd.ac.uk', '01792 651423', 'Staff', 'anime2.png');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `productID` int(11) NOT NULL,
  `customerID` int(11) NOT NULL,
  `staffID` int(11) NULL DEFAULT NULL,
  `customerFName` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `customerLName` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `productName` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `productCategory` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `staffFName` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `staffLName` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `orderPrice` varchar(5) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `active` bit(1) NULL DEFAULT NULL,
  `tstamp` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_productID`(`productID`, `productName`, `productCategory`) USING BTREE,
  INDEX `FK_customerID`(`customerID`, `customerFName`, `customerLName`) USING BTREE,
  INDEX `FK_staffID`(`staffID`, `staffFName`, `staffLName`) USING BTREE,
  CONSTRAINT `FK_customerID` FOREIGN KEY (`customerID`, `customerFName`, `customerLName`) REFERENCES `accounts` (`id`, `firstName`, `lastName`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_productID` FOREIGN KEY (`productID`, `productName`, `productCategory`) REFERENCES `products` (`id`, `name`, `category`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_staffID` FOREIGN KEY (`staffID`, `staffFName`, `staffLName`) REFERENCES `accounts` (`id`, `firstName`, `lastName`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('ORD-1-0-0-0', 1, 0, NULL, 'Edward', 'Patch', 'Cappuccino (Primo)', 'Drinks', NULL, NULL, '£2.25', b'1', '2020-12-16 21:53:43');
INSERT INTO `orders` VALUES ('ORD-2-3-0-3', 2, 0, NULL, 'Edward', 'Patch', 'Cappuccino (Medio)', 'Drinks', NULL, NULL, '£2.55', b'0', '2020-12-16 21:53:49');
INSERT INTO `orders` VALUES ('ORD-7-1-0-1', 7, 0, NULL, 'Edward', 'Patch', 'Standard Breakfast', 'Food', NULL, NULL, '£3.50', b'1', '2020-12-16 21:53:45');
INSERT INTO `orders` VALUES ('ORD-7-4-0-4', 7, 0, NULL, 'Edward', 'Patch', 'Standard Breakfast', 'Food', NULL, NULL, '£3.50', b'0', '2020-12-16 21:53:54');
INSERT INTO `orders` VALUES ('ORD-8-2-0-2', 8, 0, NULL, 'Edward', 'Patch', 'Walkers Crisps (Cheese and Onion)', 'Snack', NULL, NULL, '£1.65', b'0', '2020-12-16 21:53:47');
INSERT INTO `orders` VALUES ('ORD-8-4-0-4', 8, 0, NULL, 'Edward', 'Patch', 'Walkers Crisps (Cheese and Onion)', 'Snack', NULL, NULL, '£1.65', b'0', '2020-12-16 21:53:56');
INSERT INTO `orders` VALUES ('ORD-9-3-0-3', 9, 0, NULL, 'Edward', 'Patch', 'Cadbury Creme Eggs', 'Snack', NULL, NULL, '£0.75', b'1', '2020-12-16 21:53:52');

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`  (
  `id` int(11) NOT NULL,
  `name` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `category` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `price` decimal(13, 2) NOT NULL,
  `calories` int(11) NULL DEFAULT NULL,
  `allergies` text CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL,
  `availability` int(11) NOT NULL,
  `img` varchar(125) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`id`, `name`, `category`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES (0, 'Flat White (Primo)', 'Drinks', 2.40, 56, 'Vegetarian, Gluten Free (GF)', -1, 'N/A');
INSERT INTO `products` VALUES (1, 'Cappuccino (Primo)', 'Drinks', 2.25, 61, 'Vegetarian, Gluten Free (GF)', -1, 'N/A');
INSERT INTO `products` VALUES (2, 'Cappuccino (Medio)', 'Drinks', 2.55, 56, 'Vegetarian, Gluten Free (GF)', -1, 'N/A');
INSERT INTO `products` VALUES (3, 'Cappuccino (Massimo)', 'Drinks', 2.65, 52, 'Vegetarian, Gluten Free (GF)', -1, 'N/A');
INSERT INTO `products` VALUES (4, 'Caffe Latte (Primo)', 'Drinks', 2.15, 41, 'Vegetarian, Gluten Free (GF)', -1, 'N/A');
INSERT INTO `products` VALUES (5, 'Caffe Latte (Massimo)', 'Drinks', 2.65, 40, 'Vegetarian, Gluten Free (GF)', -1, 'N/A');
INSERT INTO `products` VALUES (6, 'Small Breakfast', 'Food', 1.50, 460, '', -1, 'N/A');
INSERT INTO `products` VALUES (7, 'Standard Breakfast', 'Food', 3.50, 600, '', -1, 'N/A');
INSERT INTO `products` VALUES (8, 'Walkers Crisps (Cheese and Onion)', 'Snack', 1.65, 75, 'Vegetarian', 38, 'N/A');
INSERT INTO `products` VALUES (9, 'Cadbury Creme Eggs', 'Snack', 0.75, 56, 'Eggs, Dairy', 25, 'uploads/s-l640.jpg');

-- ----------------------------
-- Procedure structure for DeleteOrders
-- ----------------------------
DROP PROCEDURE IF EXISTS `DeleteOrders`;
delimiter ;;
CREATE PROCEDURE `DeleteOrders`(orderID varchar(15))
BEGIN
	DELETE FROM orders
	WHERE id = orderID;
END
;;
delimiter ;

-- ----------------------------
-- Event structure for evtCheckOrders
-- ----------------------------
DROP EVENT IF EXISTS `evtCheckOrders`;
delimiter ;;
CREATE EVENT `evtCheckOrders`
ON SCHEDULE
EVERY '15' MINUTE STARTS '2020-10-06 23:34:34'
DO DELETE FROM orders
		WHERE tstamp <= NOW() - INTERVAL 15 MINUTE;
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table orders
-- ----------------------------
DROP TRIGGER IF EXISTS `triRecordTimestamp`;
delimiter ;;
CREATE TRIGGER `triRecordTimestamp` BEFORE INSERT ON `orders` FOR EACH ROW BEGIN 
	IF NEW.tstamp IS NULL THEN
		SET NEW.tstamp = CURRENT_TIME;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table orders
-- ----------------------------
DROP TRIGGER IF EXISTS `triActive`;
delimiter ;;
CREATE TRIGGER `triActive` BEFORE INSERT ON `orders` FOR EACH ROW BEGIN 
	IF NEW.active IS NULL THEN
		SET NEW.active = 0;
	END IF;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
