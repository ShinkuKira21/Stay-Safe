-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 29, 2020 at 01:35 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `foodtray-7261`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

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
  `picture` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`id`, `username`, `password`, `firstName`, `lastName`, `age`, `email`, `telephoneNumber`, `role`, `picture`) VALUES
(0, '1801492', '$2a$11$1P7jTHANKGwerhoA6rzQMOiGHbOf0AbvnBAxEUbVNUqAhgGpRf6Tm', 'Edward', 'Patch', 21, '1801492@student.uwtsd.ac.uk', '07533 897799', 'Student', 'anime1.jpg'),
(1, 'paul.payne', '$2a$11$QuzaKTayy55oj6hsiDq3HuYkZecnBID15uI4hoZ88kWH7K0xHZdES', 'Paul', 'Payne', 31, 'paul.payne@uwtsd.ac.uk', '01792 651423', 'Staff', 'anime2.png');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

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
  `tstamp` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Triggers `orders`
--
DROP TRIGGER `triActiveTimeStamp`
DELIMITER $$
CREATE TRIGGER `triActiveTimeStamp` BEFORE INSERT ON `orders` FOR EACH ROW BEGIN 
	IF NEW.tstamp IS NULL THEN
		SET NEW.tstamp = CURRENT_TIME;
	END IF;

	IF NEW.active IS NULL THEN
		SET NEW.active = 0;
	END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `category` varchar(20) NOT NULL,
  `price` decimal(13,2) NOT NULL,
  `calories` int(11) DEFAULT NULL,
  `allergies` text DEFAULT NULL,
  `availability` int(11) NOT NULL,
  `img` varchar(125) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `name`, `category`, `price`, `calories`, `allergies`, `availability`, `img`) VALUES
(0, 'Flat White (Primo)', 'Drinks', '2.40', 56, 'Vegetarian, Gluten Free (GF)', -1, 'N/A'),
(1, 'Cappuccino (Primo)', 'Drinks', '2.25', 61, 'Vegetarian, Gluten Free (GF)', -1, 'N/A'),
(2, 'Cappuccino (Medio)', 'Drinks', '2.55', 56, 'Vegetarian, Gluten Free (GF)', -1, 'N/A'),
(3, 'Cappuccino (Massimo)', 'Drinks', '2.65', 52, 'Vegetarian, Gluten Free (GF)', -1, 'N/A'),
(4, 'Caffe Latte (Primo)', 'Drinks', '2.15', 41, 'Vegetarian, Gluten Free (GF)', -1, 'N/A'),
(5, 'Caffe Latte (Massimo)', 'Drinks', '2.65', 40, 'Vegetarian, Gluten Free (GF)', -1, 'N/A'),
(6, 'Small Breakfast', 'Food', '1.50', 460, '', -1, 'N/A'),
(7, 'Standard Breakfast', 'Food', '3.50', 600, '', -1, 'N/A'),
(8, 'Walkers Crisps (Cheese and Onion)', 'Snack', '1.65', 75, 'Vegetarian', 38, 'N/A'),
(9, 'Cadbury Creme Eggs', 'Snack', '0.75', 56, 'Eggs, Dairy', 25, 'uploads/s-l640.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`,`firstName`,`lastName`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_productID` (`productID`,`productName`,`productCategory`),
  ADD KEY `FK_customerID` (`customerID`,`customerFName`,`customerLName`),
  ADD KEY `FK_staffID` (`staffID`,`staffFName`,`staffLName`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`,`name`,`category`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FK_customerID` FOREIGN KEY (`customerID`,`customerFName`,`customerLName`) REFERENCES `accounts` (`id`, `firstName`, `lastName`),
  ADD CONSTRAINT `FK_productID` FOREIGN KEY (`productID`,`productName`,`productCategory`) REFERENCES `products` (`id`, `name`, `category`),
  ADD CONSTRAINT `FK_staffID` FOREIGN KEY (`staffID`,`staffFName`,`staffLName`) REFERENCES `accounts` (`id`, `firstName`, `lastName`);

DELIMITER $$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
