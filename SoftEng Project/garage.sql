-- MySQL dump 10.13  Distrib 5.5.34, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: garage
-- ------------------------------------------------------
-- Server version	5.5.34-0ubuntu0.13.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `garage`
--

DROP TABLE IF EXISTS `garage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `garage` (
  `VehicleID` int(3) NOT NULL AUTO_INCREMENT,
  `VehicleName` text NOT NULL,
  `VehicleType` varchar(10) NOT NULL,
  `Brand` varchar(30) NOT NULL,
  `CC` int(10) NOT NULL,
  `Rented` varchar(7) NOT NULL,
  PRIMARY KEY (`VehicleID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `garage`
--

LOCK TABLES `garage` WRITE;
/*!40000 ALTER TABLE `garage` DISABLE KEYS */;
INSERT INTO `garage` VALUES (1,'BMW 316i','Car','BMW',1600,''),(2,'BMW 320i','Car','BMW',1800,''),(3,'Audi R8','Car','Audi',2200,''),(4,'Audi A3','Car','Audi',1600,''),(5,'BMW M3 E46','Car','BMW',2200,''),(6,'BMW M3 E96','Car','BMW',2400,''),(7,'Aston Martin DB9','Car','Aston Martin',2800,''),(8,'Aston Martin DBS','Car','Aston Martin',3200,''),(9,'Fiat Punto','Car','Fiat',1000,''),(10,'Citroen C3','Car','Citroen',1100,''),(11,'Toyota Supra','Car','Toyota',1800,''),(12,'Mercedes CLK','Car','Mercedes',1900,''),(13,'Kawasaki S','Motorbike','Kawasaki',1500,''),(14,'Seat Leon','Car','Seat',1400,''),(15,'Ford Mondeo','Car','Ford',1200,'');
/*!40000 ALTER TABLE `garage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rent`
--

DROP TABLE IF EXISTS `rent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rent` (
  `user_id` int(10) unsigned DEFAULT NULL,
  `product_id` int(10) unsigned DEFAULT NULL,
  `type` enum('cycle','car') DEFAULT 'car',
  `name` varchar(40) DEFAULT NULL,
  `brand` varchar(40) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  KEY `u_id` (`user_id`),
  KEY `p_id` (`product_id`),
  CONSTRAINT `p_id` FOREIGN KEY (`product_id`) REFERENCES `storage` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `u_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rent`
--

LOCK TABLES `rent` WRITE;
/*!40000 ALTER TABLE `rent` DISABLE KEYS */;
/*!40000 ALTER TABLE `rent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storage`
--

DROP TABLE IF EXISTS `storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storage` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type` enum('cycle','car') NOT NULL DEFAULT 'car',
  `name` varchar(20) DEFAULT NULL,
  `brand` varchar(30) DEFAULT NULL,
  `CC` int(10) DEFAULT NULL,
  `rented` enum('yes','no') DEFAULT 'no',
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storage`
--

LOCK TABLES `storage` WRITE;
/*!40000 ALTER TABLE `storage` DISABLE KEYS */;
INSERT INTO `storage` VALUES (1,'car','316i','BMW',1600,'no',20000),(2,'car','320i','BMW',1800,'no',1000),(3,'car','R8','Audi',2200,'no',4000),(4,'car','A3','Audi',1600,'no',10000),(5,'car','M3 E46','BMW',2200,'no',23340),(6,'car','M3 E96','BMW',2400,'no',10000),(7,'car','DB9','Aston Martin',2800,'no',1234),(8,'car','DBS','Aston Martin',3200,'no',500),(9,'car','Punto','Fiat',1000,'no',100),(10,'car','C3','Citroen',1100,'no',200),(11,'car','Supra','Toyota',1800,'no',1000),(12,'car','CLK','Mercedes',1900,'no',2000),(13,'cycle','S','Kawasaki',1500,'no',9000),(14,'car','Leon','Seat',1400,'no',1000),(15,'car','Ford Mondeo','Ford',1200,'no',400),(17,'car','GT','Golf',2000,'no',2000),(18,'cycle','G 650','BMW',1500,'no',15000);
/*!40000 ALTER TABLE `storage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(40) DEFAULT NULL,
  `permissions` enum('1','2','3') DEFAULT '1',
  `password` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'alkis','1','alk123'),(2,'karaxalios','1','krx'),(3,'menios','1','1992'),(4,'admin','3','admin'),(11,'employee','2','emp');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-01-24 19:15:12
