/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.6.45-log : Database - ordertest
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ordertest` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `ordertest`;

/*Table structure for table `user2` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` text,
  `mobile` text,
  `address` text,
  `addressDetail` text,
  `role` int(10),
  `sdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `user2` */

insert  into `user`(`id`,`username`,`password`,`mobile`,`role`,`sdate`) values (1,'张三','111111','18084725150',0,'2018-01-25 18:54:02'),(2,'李四','111111','18084725151',0,'2018-01-25 18:54:02');

DROP TABLE IF EXISTS `good`;
CREATE TABLE `good` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `goodName` varchar(255) DEFAULT NULL,
  `goodDetail` text,
  `goodDiscount` text,
  `goodPrice` text,
  `goodPic` text,
  `categoryName` text,
  `categoryId` int,
  `gdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(10) NOT NULL AUTO_INCREMENT, 
  `categoryName` text, 
  `cdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
  `id` int(10) NOT NULL AUTO_INCREMENT, 
  `bType` int, 
  `categoryId` int,
  `goodId` int,
  `name` text,
  `picStr` text,
  `bdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(10) NOT NULL AUTO_INCREMENT,  
  `categoryName` text,
  `cdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

insert  into `category`(`id`,`categoryName`,`cdate`) values (1,'甜点','2018-01-25 18:54:02'),(2,'面','2018-01-25 18:54:02'),(3,'肉','2018-01-25 18:54:02'),(4,'卤味','2018-01-25 18:54:02');

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(10) NOT NULL AUTO_INCREMENT,  
  `userId` int,
  `goodId` int,
  `categoryId` int,
  `goodState` int,
  `getTypeInt` int,
  `shopAddress` text,
  `buyerphone` text, 
  `buyerName` text,
  `buyerAddress` text,
  `goodName` text,
  `goodDetail` text,
  `goodDiscount` text,
  `goodPrice` text,
  `goodPic` text, 
  `odate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` int(10) NOT NULL AUTO_INCREMENT, 
  `name` text,  
  `phone` text,
  `userId` int,
  `address` text,
  `addressDetail` text, 
  `state` int,  
  `addressDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
