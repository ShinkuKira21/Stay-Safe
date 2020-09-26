/*
 Navicat Premium Data Transfer

 Source Server         : Azure
 Source Server Type    : MySQL
 Source Server Version : 50642
 Source Host           : staysafe-23.database.windows.net:3306
 Source Schema         : staysafe

 Target Server Type    : MySQL
 Target Server Version : 50642
 File Encoding         : 65001

 Date: 25/09/2020 21:51:12
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of accounts
-- ----------------------------
INSERT INTO `accounts` VALUES (0, '1801492', '$2a$11$1P7jTHANKGwerhoA6rzQMOiGHbOf0AbvnBAxEUbVNUqAhgGpRf6Tm', 'Edward', 'Patch', 21, '1801492@student.uwtsd.ac.uk', '07533 897799', 'Student');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `productID` int(11) NULL DEFAULT NULL,
  `customerID` int(11) NULL DEFAULT NULL,
  `staffID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_productID`(`productID`) USING BTREE,
  INDEX `FK_customerID`(`customerID`) USING BTREE,
  INDEX `FK_staffID`(`staffID`) USING BTREE,
  CONSTRAINT `FK_customerID` FOREIGN KEY (`customerID`) REFERENCES `accounts` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_productID` FOREIGN KEY (`productID`) REFERENCES `products` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_staffID` FOREIGN KEY (`staffID`) REFERENCES `accounts` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

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
  PRIMARY KEY (`id`) USING BTREE
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

SET FOREIGN_KEY_CHECKS = 1;
