-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 15, 2018 at 06:26 PM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 7.2.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `personal_finance_software`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `accountID` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `userID` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `type_accountID` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `accountName` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `balance` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `detail_type_expense`
--

CREATE TABLE `detail_type_expense` (
  `detail_type_expenseID` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `type_expenseID` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `detail_type_expenseName` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detail_type_expense`
--

INSERT INTO `detail_type_expense` (`detail_type_expenseID`, `type_expenseID`, `detail_type_expenseName`, `description`) VALUES
('DTE1', 'TE1', 'Đi chợ/siêu thị', 'Tiền mua thực phẩm ở chợ hoặc siêu thị'),
('DTE10', 'TE3', 'Xăng xe', 'Tiền xăng xe'),
('DTE11', 'TE3', 'Bảo hiểm xe', 'Tiền đống bảo hiểm đinh kỳ cho phương tiện đi lại'),
('DTE12', 'TE3', 'Sửa chữa, bảo dưỡng xe', 'Tiền chi cho sửa chữa và bảo dưỡng xe'),
('DTE13', 'TE3', 'Gửi xe', 'Tiền dành cho gửi xe'),
('DTE14', 'TE3', 'Thuê xe/Grab/Taxi/Xe ôm', 'Tiền đi Grab, đi xe ôm hoặc taxi'),
('DTE15', 'TE4', 'Học phí', 'Tiền đóng học phí đầu năm hoặc phí học thêm'),
('DTE16', 'TE4', 'Sách vở - Đồ dùng học tập', 'Tiền dành cho việc mua sách vở và đồ dùng học tập'),
('DTE17', 'TE4', 'Sữa', 'Tiền sữa nếu có con nhỏ'),
('DTE18', 'TE4', 'Đồ chơi', 'Tiền đồ chơi cho con cái'),
('DTE19', 'TE4', 'Tiền tiêu vặt', 'Tiền tiêu vặt hằng ngày cho con'),
('DTE2', 'TE1', 'Ăn tiệm/cơm quán', 'Chi phí cho ăn uống nếu không ăn tại nhà'),
('DTE20', 'TE5', 'Quần áo', 'Tiền mua quần áo'),
('DTE21', 'TE5', 'Giầy dép', 'Tiền mua giầy dép'),
('DTE22', 'TE5', 'Đồ điện tử', 'Tiền mua đồ điện tử như: tivi, tủ lạnh, điện thoại,...'),
('DTE23', 'TE5', 'Phụ kiện khác', 'Tiền dành mua các phụ kiện khác'),
('DTE24', 'TE6', 'Cưới xin', 'Tiền đi cưới xin'),
('DTE25', 'TE6', 'Ma chay', 'Tiền đi đám ma hoặc kỵ dỗ'),
('DTE26', 'TE6', 'Thăm hỏi', 'Tiền thăm bệnh'),
('DTE27', 'TE6', 'Biếu tặng', 'Tiền biếu tặng'),
('DTE28', 'TE7', 'Khám chữa bệnh - Bảo hiểm y tế', 'Tiền khám chữa bệnh hoặc tiền đóng bảo hiểm'),
('DTE29', 'TE7', 'Thuốc men', 'Tiền thuốc men'),
('DTE3', 'TE1', 'Cafe/Trà sữa', 'Chi phí cho đồ uống'),
('DTE30', 'TE8', 'Mua sắm đồ đạc', 'Tiền mua sắm các đồ đạc trong nhà'),
('DTE31', 'TE8', 'Sửa chữa nhà cửa', 'Tiền sửa chữa nhà cửa'),
('DTE32', 'TE8', 'Thuê nhà', 'Tiền thuê nhà nếu là nhà thuê'),
('DTE33', 'TE9', 'Vui chơi giải trí', 'Tiền dành cho vui chơi giải trí'),
('DTE34', 'TE9', 'Du lịch - Phượt', 'Tiền đi du lịch hoặc đi phượt'),
('DTE35', 'TE9', 'Làm đẹp', 'Tiền đi spa làm đẹp, tiền mua mỹ phẩm, các sản phẩm làm đẹp,...'),
('DTE36', 'TE9', 'Vật nuôi', 'Toàn bộ các chi phí cho vật nuôi (nếu có)'),
('DTE37', 'TE10', 'Học hành - Rèn luyện', 'Tiền dành cho học tập nâng cao kiến thức hoặc rèn luyện thể lực thể hình (GYM)'),
('DTE38', 'TE10', 'Giao lưu - Quan hệ', 'Tiền để giao lưu và mở rộng quan hệ'),
('DTE4', 'TE2', 'Điện', 'Tiền điện'),
('DTE5', 'TE2', 'Nước', 'Tiền nước'),
('DTE6', 'TE2', 'Mạng Internet', 'Tiền mạng Internet'),
('DTE7', 'TE2', 'Gas/Chất đốt', 'Tiền gas, chất đốt'),
('DTE8', 'TE2', 'Truyền hình', 'Tiền truyền hình như K+, MyTV, truyền hình Cab,...'),
('DTE9', 'TE2', 'Điện thoại', 'Tiền chi tiêu cho điện thoại di động hoặc điện thoại cố định');

-- --------------------------------------------------------

--
-- Table structure for table `detail_type_income`
--

CREATE TABLE `detail_type_income` (
  `detail_type_incomeID` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `type_incomeID` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `detail_type_incomeName` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `expense`
--

CREATE TABLE `expense` (
  `expenseID` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `detail_type_expenseID` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `accountID` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `createAt` timestamp NULL DEFAULT NULL,
  `updateAt` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `income`
--

CREATE TABLE `income` (
  `incomeID` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `detail_type_incomeID` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `accountID` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `createAt` timestamp NULL DEFAULT NULL,
  `updateAt` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `report`
--

CREATE TABLE `report` (
  `reportID` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `reportName` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `period` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `sum_income` bigint(20) DEFAULT NULL,
  `sum_expense` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `type_account`
--

CREATE TABLE `type_account` (
  `type_accountID` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `type_accountName` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `type_expense`
--

CREATE TABLE `type_expense` (
  `type_expenseID` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `type_expenseName` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `type_expense`
--

INSERT INTO `type_expense` (`type_expenseID`, `type_expenseName`, `description`) VALUES
('TE1', 'Ăn uống', 'Chi phí ăn uống hoặc mua thực phẩm'),
('TE10', 'Phát triển bản thân', 'Số tiền chi ra để học tập, giao lưu'),
('TE2', 'Dịch vụ sinh hoạt', 'Các khoản chi phí sinh hoạt như điện, nước, mạng internet,...'),
('TE3', 'Đi lại', 'Chi phí phải tốn cho việc đi lại, di chuyển'),
('TE4', 'Con cái', 'Tiền cho con cái (nếu có)'),
('TE5', 'Mua sắm', 'Chi phí mua sắm'),
('TE6', 'Hiếu hỉ', 'Chi phí các hoạt động ăn cưới, thăm hỏi, đám dỗ,...'),
('TE7', 'Sức khỏe', 'Chi phí thuốc men, khám chữa bệnh'),
('TE8', 'Nhà cửa', 'Tiền mua sắm đồ đạc hoặc tiền thuê nhà nếu là nhà thuê'),
('TE9', 'Hưởng thụ', 'Chi phí giải trí');

-- --------------------------------------------------------

--
-- Table structure for table `type_income`
--

CREATE TABLE `type_income` (
  `type_incomeID` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `type_incomeName` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `type_income`
--

INSERT INTO `type_income` (`type_incomeID`, `type_incomeName`, `description`) VALUES
('TI1', 'Lương', 'Tiền lương tháng hoặc lương theo hợp đồng'),
('TI2', 'Thưởng', 'Tiền thưởng tết hoặc hoàn thành dự án'),
('TI3', 'Được cho/tặng', 'Tiền được cho hoặc tặng, hoặc giá trị quy đổi từ quà được biếu'),
('TI4', 'Lãi tiết kiệm', 'Tiền lãi tiết kiệm'),
('TI5', 'Khác', 'Các nguồn thu khác');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userID` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `avatar` mediumblob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`accountID`),
  ADD KEY `FK_Account_User` (`userID`),
  ADD KEY `FK_Account_Type_Account` (`type_accountID`);

--
-- Indexes for table `detail_type_expense`
--
ALTER TABLE `detail_type_expense`
  ADD PRIMARY KEY (`detail_type_expenseID`),
  ADD KEY `FK_Type_Detail_Type_Expense` (`type_expenseID`);

--
-- Indexes for table `detail_type_income`
--
ALTER TABLE `detail_type_income`
  ADD PRIMARY KEY (`detail_type_incomeID`),
  ADD KEY `FK_Type_Detail_Type_Income` (`type_incomeID`);

--
-- Indexes for table `expense`
--
ALTER TABLE `expense`
  ADD PRIMARY KEY (`expenseID`),
  ADD KEY `FK_Expense_Detail_Type` (`detail_type_expenseID`),
  ADD KEY `FK_Expense_Account` (`accountID`);

--
-- Indexes for table `income`
--
ALTER TABLE `income`
  ADD PRIMARY KEY (`incomeID`),
  ADD KEY `FK_Income_Detail_Type` (`detail_type_incomeID`),
  ADD KEY `FK_Income_Account` (`accountID`);

--
-- Indexes for table `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`reportID`);

--
-- Indexes for table `type_account`
--
ALTER TABLE `type_account`
  ADD PRIMARY KEY (`type_accountID`);

--
-- Indexes for table `type_expense`
--
ALTER TABLE `type_expense`
  ADD PRIMARY KEY (`type_expenseID`);

--
-- Indexes for table `type_income`
--
ALTER TABLE `type_income`
  ADD PRIMARY KEY (`type_incomeID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `FK_Account_Type_Account` FOREIGN KEY (`type_accountID`) REFERENCES `type_account` (`type_accountID`),
  ADD CONSTRAINT `FK_Account_User` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`);

--
-- Constraints for table `detail_type_expense`
--
ALTER TABLE `detail_type_expense`
  ADD CONSTRAINT `FK_Type_Detail_Type_Expense` FOREIGN KEY (`type_expenseID`) REFERENCES `type_expense` (`type_expenseID`);

--
-- Constraints for table `detail_type_income`
--
ALTER TABLE `detail_type_income`
  ADD CONSTRAINT `FK_Type_Detail_Type_Income` FOREIGN KEY (`type_incomeID`) REFERENCES `type_income` (`type_incomeID`);

--
-- Constraints for table `expense`
--
ALTER TABLE `expense`
  ADD CONSTRAINT `FK_Expense_Account` FOREIGN KEY (`accountID`) REFERENCES `account` (`accountID`),
  ADD CONSTRAINT `FK_Expense_Detail_Type` FOREIGN KEY (`detail_type_expenseID`) REFERENCES `detail_type_expense` (`detail_type_expenseID`);

--
-- Constraints for table `income`
--
ALTER TABLE `income`
  ADD CONSTRAINT `FK_Income_Account` FOREIGN KEY (`accountID`) REFERENCES `account` (`accountID`),
  ADD CONSTRAINT `FK_Income_Detail_Type` FOREIGN KEY (`detail_type_incomeID`) REFERENCES `detail_type_income` (`detail_type_incomeID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
