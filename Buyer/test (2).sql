-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2021 at 03:36 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `buyers`
--

CREATE TABLE `buyers` (
  `buyerID` int(255) NOT NULL,
  `buyerName` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `buyerGender` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `buyerContactNo` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `buyerEmail` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `buyerAddress` varchar(255) CHARACTER SET utf8mb4 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `buyers`
--

INSERT INTO `buyers` (`buyerID`, `buyerName`, `buyerGender`, `buyerContactNo`, `buyerEmail`, `buyerAddress`) VALUES
(1, 'Supun Ranaweera', 'Male', '0772533502', 'SupunR@gmail.com', 'No 31/4, Flower road, Kandy'),
(2, 'Hasaruni Ediriweera', 'Female', '0716692511', 'HasaruniE@gmail.com', '\'hasaruni\', wiharamawatha, godagama, matara');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `buyers`
--
ALTER TABLE `buyers`
  ADD PRIMARY KEY (`buyerID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `buyers`
--
ALTER TABLE `buyers`
  MODIFY `buyerID` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
