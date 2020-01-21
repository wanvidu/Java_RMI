-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Generation Time: Dec 07, 2019 at 06:19 PM
-- Server version: 5.7.27
-- PHP Version: 7.2.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Table structure for table `Admins`
--

CREATE TABLE `Admins` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Admins`
--

INSERT INTO `Admins` (`id`, `username`, `password`) VALUES
(1, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `Questions`
--

CREATE TABLE `Questions` (
  `id` int(11) NOT NULL,
  `question` varchar(200) NOT NULL,
  `category` int(11) NOT NULL,
  `answer_1` int(11) NOT NULL,
  `answer_2` int(11) NOT NULL,
  `answer_3` int(11) NOT NULL,
  `answer_4` int(11) NOT NULL,
  `answer_5` int(11) NOT NULL,
  `answer_6` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Questions`
--

INSERT INTO `Questions` (`id`, `question`, `category`, `answer_1`, `answer_2`, `answer_3`, `answer_4`, `answer_5`, `answer_6`) VALUES
(1, 'Are you practical, athletic, straight forward and good with tools and machinery?', 1, 1, 2, 3, 4, 5, 6),
(2, 'Can you fix electrical things, solve mechanical problems and work on automobiles?', 1, 1, 2, 3, 4, 5, 6),
(3, 'Are you inquisitive, analytical, scientific and accurate?', 2, 1, 2, 3, 4, 5, 6),
(4, 'Can you think abstractly, solve math problems, understand physical theories and do complex calculations?', 2, 1, 2, 3, 4, 5, 6),
(5, 'Are you creative, imaginative, innovative and intuitive? ', 3, 1, 2, 3, 4, 5, 6);

-- --------------------------------------------------------

--
-- Table structure for table `Question_Answers`
--

CREATE TABLE `Question_Answers` (
  `id` int(11) NOT NULL,
  `answer` varchar(50) NOT NULL,
  `value` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Question_Answers`
--

INSERT INTO `Question_Answers` (`id`, `answer`, `value`) VALUES
(1, 'Strongly Disagree', 0),
(2, 'Moderately Disagree', 1),
(3, 'Slightly Disagree', 2),
(4, 'Slightly Agree', 4),
(5, 'Moderately Agree', 6),
(6, 'Strongly Agree', 10);

-- --------------------------------------------------------

--
-- Table structure for table `Question_Categories`
--

CREATE TABLE `Question_Categories` (
  `id` int(11) NOT NULL,
  `Category` varchar(100) NOT NULL,
  `description` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Question_Categories`
--

INSERT INTO `Question_Categories` (`id`, `Category`, `description`) VALUES
(1, 'Realistic', ''),
(2, 'Investigative', ''),
(3, 'Artistic', ''),
(4, 'Social', ''),
(5, 'Enterprising', ''),
(6, 'Conventional', '');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `userName` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `marks` int(11) DEFAULT NULL,
  `category1` int(11) DEFAULT NULL,
  `category2` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `userName`, `email`, `marks`, `category1`, `category2`) VALUES
(1, 'user', 'user', 15, 2, NULL),
(2, 'nimal', 'nimal@nimal.com', NULL, NULL, NULL),
(4, 'kamal', 'kamal@kamal.com', NULL, NULL, NULL),
(5, 'john', 'john', 25, 3, NULL),
(6, 'hashan', 'hashan@hashan.com', 21, 1, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Admins`
--
ALTER TABLE `Admins`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Questions`
--
ALTER TABLE `Questions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `answer_1` (`answer_1`),
  ADD KEY `answer_2` (`answer_2`),
  ADD KEY `answer_3` (`answer_3`),
  ADD KEY `answer_4` (`answer_4`),
  ADD KEY `answer_5` (`answer_5`),
  ADD KEY `answer_6` (`answer_6`),
  ADD KEY `category` (`category`);

--
-- Indexes for table `Question_Answers`
--
ALTER TABLE `Question_Answers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Question_Categories`
--
ALTER TABLE `Question_Categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category1` (`category1`),
  ADD KEY `category2` (`category2`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Admins`
--
ALTER TABLE `Admins`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `Questions`
--
ALTER TABLE `Questions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `Question_Answers`
--
ALTER TABLE `Question_Answers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `Question_Categories`
--
ALTER TABLE `Question_Categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Questions`
--
ALTER TABLE `Questions`
  ADD CONSTRAINT `Questions_ibfk_1` FOREIGN KEY (`answer_1`) REFERENCES `Question_Answers` (`id`),
  ADD CONSTRAINT `Questions_ibfk_2` FOREIGN KEY (`answer_2`) REFERENCES `Question_Answers` (`id`),
  ADD CONSTRAINT `Questions_ibfk_3` FOREIGN KEY (`answer_3`) REFERENCES `Question_Answers` (`id`),
  ADD CONSTRAINT `Questions_ibfk_4` FOREIGN KEY (`answer_4`) REFERENCES `Question_Answers` (`id`),
  ADD CONSTRAINT `Questions_ibfk_5` FOREIGN KEY (`answer_5`) REFERENCES `Question_Answers` (`id`),
  ADD CONSTRAINT `Questions_ibfk_6` FOREIGN KEY (`answer_6`) REFERENCES `Question_Answers` (`id`),
  ADD CONSTRAINT `Questions_ibfk_7` FOREIGN KEY (`category`) REFERENCES `Question_Categories` (`id`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`category1`) REFERENCES `Question_Categories` (`id`),
  ADD CONSTRAINT `user_ibfk_2` FOREIGN KEY (`category2`) REFERENCES `Question_Categories` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
