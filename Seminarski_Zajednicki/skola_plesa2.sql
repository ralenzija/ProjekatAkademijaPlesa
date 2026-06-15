/*
SQLyog Community v13.3.0 (64 bit)
MySQL - 10.4.32-MariaDB : Database - skola_plesa
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`skola_plesa` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `skola_plesa`;

/*Table structure for table `instruktor` */

DROP TABLE IF EXISTS `instruktor`;

CREATE TABLE `instruktor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) DEFAULT NULL,
  `prezime` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `sifra` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `instruktor` */

insert  into `instruktor`(`id`,`ime`,`prezime`,`email`,`sifra`) values 
(1,'Luka','Simic','luka@gmail.com','luka'),
(2,'Ema','Pesic','ema@gmail.com','ema'),
(3,'Lazar','Milanovic','lazar@gmail.com','lazar'),
(4,'Kosta','Jankovic','kosta@gmail.com','kosta'),
(5,'Lana','Jovanovic','lana@gmail.com','lana'),
(6,'Sergej','Petrovic','sergej@gmail.com','sergej');

/*Table structure for table `instruktor_sertifikat` */

DROP TABLE IF EXISTS `instruktor_sertifikat`;

CREATE TABLE `instruktor_sertifikat` (
  `instruktor` bigint(20) NOT NULL,
  `sertifikat` bigint(20) NOT NULL,
  `datumSticanja` date DEFAULT NULL,
  PRIMARY KEY (`instruktor`,`sertifikat`),
  KEY `sertifikat` (`sertifikat`),
  CONSTRAINT `instruktor_sertifikat_ibfk_1` FOREIGN KEY (`instruktor`) REFERENCES `instruktor` (`id`),
  CONSTRAINT `instruktor_sertifikat_ibfk_2` FOREIGN KEY (`sertifikat`) REFERENCES `sertifikat` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `instruktor_sertifikat` */

insert  into `instruktor_sertifikat`(`instruktor`,`sertifikat`,`datumSticanja`) values 
(1,1,'2025-11-06'),
(2,7,'2025-08-02'),
(3,6,'2025-08-02'),
(4,4,'2025-08-14'),
(6,4,'2025-08-15');

/*Table structure for table `kurs_plesa` */

DROP TABLE IF EXISTS `kurs_plesa`;

CREATE TABLE `kurs_plesa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(50) DEFAULT NULL,
  `trajanje` int(11) DEFAULT NULL,
  `cena` double DEFAULT NULL,
  `vrsta` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `kurs_plesa` */

insert  into `kurs_plesa`(`id`,`naziv`,`trajanje`,`cena`,`vrsta`) values 
(1,'Upoznaj valcer',4,140,'valcer'),
(2,'Tango za parove',2,100,'tango'),
(3,'Osnove salse',3,110,'salsa'),
(4,'Balet za pocetnike',3,130,'balet'),
(5,'Street dance',2,90,'hiphop'),
(6,'Cha-cha advance',5,180,'chacha');

/*Table structure for table `nivo_vestine` */

DROP TABLE IF EXISTS `nivo_vestine`;

CREATE TABLE `nivo_vestine` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nivo` varchar(50) DEFAULT NULL,
  `vrsta` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `nivo_vestine` */

insert  into `nivo_vestine`(`id`,`nivo`,`vrsta`) values 
(1,'pocetni','salsa'),
(2,'srednji','salsa'),
(3,'napredni','salsa'),
(4,'pocetni','tango'),
(6,'srednji','tango'),
(7,'napredni','tango'),
(8,'pocetni','valcer'),
(9,'srednji','valcer'),
(10,'napredni','valcer'),
(11,'pocetni','hiphop'),
(12,'srednji','hiphop'),
(13,'napredni','hiphop'),
(14,'pocetni','rumba'),
(15,'srednji','rumba'),
(16,'napredni','rumba'),
(17,'pocetni','chacha'),
(18,'srednji','chacha'),
(19,'napredni','chacha'),
(20,'pocetni','balet'),
(21,'srednji','balet'),
(22,'napredni','balet');

/*Table structure for table `polaznik` */

DROP TABLE IF EXISTS `polaznik`;

CREATE TABLE `polaznik` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) DEFAULT NULL,
  `prezime` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `nivo` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `nivo` (`nivo`),
  CONSTRAINT `polaznik_ibfk_1` FOREIGN KEY (`nivo`) REFERENCES `nivo_vestine` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `polaznik` */

insert  into `polaznik`(`id`,`ime`,`prezime`,`email`,`nivo`) values 
(1,'Novi','Neki','novi@gmail.com',4),
(2,'Ana','Petrovic','ana@gmail.com',9),
(4,'Beba','Bob','beba@gmail.com',21),
(12,'Sergej','Campara','sergej@gmail.com',6),
(13,'Marija','Simovic','marija@gmail.com',15);

/*Table structure for table `prijava_na_kurs` */

DROP TABLE IF EXISTS `prijava_na_kurs`;

CREATE TABLE `prijava_na_kurs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `datum` date DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `ukupanIznos` double DEFAULT NULL,
  `instruktor` bigint(20) DEFAULT NULL,
  `polaznik` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `instruktor` (`instruktor`),
  KEY `polaznik` (`polaznik`),
  CONSTRAINT `prijava_na_kurs_ibfk_1` FOREIGN KEY (`instruktor`) REFERENCES `instruktor` (`id`),
  CONSTRAINT `prijava_na_kurs_ibfk_2` FOREIGN KEY (`polaznik`) REFERENCES `polaznik` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `prijava_na_kurs` */

insert  into `prijava_na_kurs`(`id`,`datum`,`status`,`ukupanIznos`,`instruktor`,`polaznik`) values 
(1,'2025-09-11','PLACENA',200,3,12),
(2,'2025-09-27','PLACENA',300,5,1),
(3,'2025-09-18','PLACENA',400,2,4),
(4,'2025-09-09','PLACENA',500,1,13),
(5,'2025-09-06','PLACENA',600,4,2),
(12,'2025-09-06','POTVRDJENA',1850,2,4),
(13,'2027-10-06','POTVRDJENA',1850,1,13),
(14,'2025-09-06','POTVRDJENA',740,1,1),
(15,'2025-09-06','ZAVRSENA',570,5,4),
(16,'2025-09-06','ZAVRSENA',740,4,13);

/*Table structure for table `sertifikat` */

DROP TABLE IF EXISTS `sertifikat`;

CREATE TABLE `sertifikat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ples` varchar(50) DEFAULT NULL,
  `organizacija` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sertifikat` */

insert  into `sertifikat`(`id`,`ples`,`organizacija`) values 
(1,'salsa','DancePro Academy'),
(2,'tango','Global Dance Institute'),
(3,'valcer','StepUp Dance School'),
(4,'hiphop','Elite Dance Federation'),
(5,'rumba','Rhythm & Motion Academy'),
(6,'balet','Global Dance Institute'),
(7,'chacha','Elite Dance Federation'),
(8,'salsa','aaaaa'),
(9,'hiphop','Nova'),
(10,'balet','Neka organizacija'),
(11,'tango','AA Dance');

/*Table structure for table `stavka_prijave` */

DROP TABLE IF EXISTS `stavka_prijave`;

CREATE TABLE `stavka_prijave` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `prijava` bigint(20) NOT NULL,
  `iznos` double DEFAULT NULL,
  `kurs` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`prijava`),
  KEY `prijava` (`prijava`),
  KEY `kurs` (`kurs`),
  CONSTRAINT `stavka_prijave_ibfk_1` FOREIGN KEY (`prijava`) REFERENCES `prijava_na_kurs` (`id`),
  CONSTRAINT `stavka_prijave_ibfk_2` FOREIGN KEY (`kurs`) REFERENCES `kurs_plesa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `stavka_prijave` */

insert  into `stavka_prijave`(`id`,`prijava`,`iznos`,`kurs`) values 
(4,14,560,1),
(5,14,330,3),
(6,14,180,5),
(7,15,390,4),
(8,15,180,5),
(11,16,560,1),
(12,16,180,5);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
