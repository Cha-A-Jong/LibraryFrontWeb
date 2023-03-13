-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.23 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for library_front
CREATE DATABASE IF NOT EXISTS `library_front` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `library_front`;

-- Dumping structure for table library_front.author
CREATE TABLE IF NOT EXISTS `author` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table library_front.author: ~0 rows (approximately)
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` (`id`, `firstName`, `lastName`) VALUES
	(1, 'Joanna', 'Rowling'),
	(2, 'Cynthia', 'McLeod'),
	(3, 'Thomas', 'Harris'),
	(4, 'Stephen', 'King'),
	(5, 'Cora', 'Reilly');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;

-- Dumping structure for table library_front.book
CREATE TABLE IF NOT EXISTS `book` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `isbn` varchar(255) DEFAULT NULL,
  `subtitle` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `author_id` bigint DEFAULT NULL,
  `borrowReceipt_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5gbo4o7yxefxivwuqjichc67t` (`author_id`),
  KEY `FKtkq7poo3jrx9xcnse40i70c2b` (`borrowReceipt_id`),
  CONSTRAINT `FK5gbo4o7yxefxivwuqjichc67t` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`),
  CONSTRAINT `FKtkq7poo3jrx9xcnse40i70c2b` FOREIGN KEY (`borrowReceipt_id`) REFERENCES `borrowreceipt` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table library_front.book: ~0 rows (approximately)
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` (`id`, `isbn`, `subtitle`, `title`, `author_id`, `borrowReceipt_id`) VALUES
	(1, '152663788X', ' ', 'Harry Potter and the Chamber of Secrets', 1, 1),
	(2, '9054294485', NULL, 'Hoe duur was de suiker?', 2, 2),
	(3, '0312022824', NULL, 'The Silence of the lambs', 3, 3),
	(4, '0670813028', NULL, 'IT', 4, 4),
	(5, '9798622644122', NULL, 'Sweet temptation', 5, 5);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;

-- Dumping structure for table library_front.book_member
CREATE TABLE IF NOT EXISTS `book_member` (
  `book_id` bigint NOT NULL,
  `member_id` bigint NOT NULL,
  PRIMARY KEY (`member_id`,`book_id`),
  KEY `FK8kv2q921hst2u8une6bfw3ia5` (`book_id`),
  CONSTRAINT `FK8kv2q921hst2u8une6bfw3ia5` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `FKarpbvvqe1frhjpbrfnjwdph7` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table library_front.book_member: ~0 rows (approximately)
/*!40000 ALTER TABLE `book_member` DISABLE KEYS */;
INSERT INTO `book_member` (`book_id`, `member_id`) VALUES
	(1, 1),
	(2, 2),
	(3, 3),
	(4, 4),
	(5, 5);
/*!40000 ALTER TABLE `book_member` ENABLE KEYS */;

-- Dumping structure for table library_front.borrowreceipt
CREATE TABLE IF NOT EXISTS `borrowreceipt` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `borrow_date` date DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `receipt_number` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table library_front.borrowreceipt: ~0 rows (approximately)
/*!40000 ALTER TABLE `borrowreceipt` DISABLE KEYS */;
INSERT INTO `borrowreceipt` (`id`, `borrow_date`, `due_date`, `receipt_number`, `remark`) VALUES
	(1, '2022-01-08', '2022-01-15', '2022-001', 'laat ingeleverd'),
	(2, '2022-01-08', '2022-01-15', '2022-002', 'laat ingeleverd'),
	(3, '2022-01-08', '2022-01-15', '2022-003', 'laat ingeleverd'),
	(4, '2022-04-12', '2022-04-19', '2022-004', 'boek gescheurd'),
	(5, '2022-04-12', '2022-04-19', '2022-005', 'boek gescheurd');
/*!40000 ALTER TABLE `borrowreceipt` ENABLE KEYS */;

-- Dumping structure for table library_front.genre
CREATE TABLE IF NOT EXISTS `genre` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `book_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKeurvaf3grxe6jojmpsl8co1pa` (`book_id`),
  CONSTRAINT `FKeurvaf3grxe6jojmpsl8co1pa` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table library_front.genre: ~0 rows (approximately)
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` (`id`, `name`, `book_id`) VALUES
	(1, 'Fantasy', 1),
	(2, 'Roman', 2),
	(3, 'Thriller', 3),
	(4, 'Horror', 4),
	(5, 'Fiction', 5);
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;

-- Dumping structure for table library_front.member
CREATE TABLE IF NOT EXISTS `member` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cbb_id_number` varchar(255) DEFAULT NULL,
  `date_of_birth` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `library_number` varchar(255) DEFAULT NULL,
  `borrowReceipt_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhd7hn5bka7vck3orbf2r3plkp` (`borrowReceipt_id`),
  CONSTRAINT `FKhd7hn5bka7vck3orbf2r3plkp` FOREIGN KEY (`borrowReceipt_id`) REFERENCES `borrowreceipt` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table library_front.member: ~0 rows (approximately)
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` (`id`, `cbb_id_number`, `date_of_birth`, `firstName`, `lastName`, `library_number`, `borrowReceipt_id`) VALUES
	(1, 'FI000794', '14-02-1990', 'Chanelle', 'Cha-A-Jong', '202212001', 1),
	(2, 'BA080211', '10-06-1980', 'Ashwin', 'Adhin', '202212002', 2),
	(3, 'DA000123', '20-01-1980', 'Bronto', 'Somohardjo', '202212003', 3),
	(4, 'AB000111', '03-02-1959', 'Chan', 'Santokhi', '202212004', 4),
	(5, 'BO004587', '07-03-1961', 'Ronnie', 'Brunswijk', '202212005', 5);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
