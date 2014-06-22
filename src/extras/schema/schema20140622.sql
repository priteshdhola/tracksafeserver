CREATE DATABASE  IF NOT EXISTS `tracksafe` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `tracksafe`;
-- MySQL dump 10.13  Distrib 5.6.13, for osx10.6 (i386)
--
-- Host: 127.0.0.1    Database: tracksafe
-- ------------------------------------------------------
-- Server version	5.6.19

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
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `modified_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (25,'Morning Run','2013-09-28 12:02:13',NULL,NULL,0,NULL,1,NULL),(26,'Morning Run','2013-09-28 12:06:26',NULL,NULL,0,NULL,1,NULL),(27,'Morning Run','2013-09-28 17:16:16',NULL,NULL,0,NULL,2,NULL),(28,'Weekend Run','2013-09-28 17:16:23',NULL,NULL,0,NULL,2,NULL),(29,'Group Run','2013-09-28 17:16:29',NULL,NULL,0,NULL,2,NULL),(30,'test','2013-10-01 23:10:05',NULL,NULL,0,NULL,1,NULL),(31,'test','2013-10-04 23:58:27',NULL,NULL,0,NULL,1,NULL),(32,'test','2014-01-05 18:27:28',NULL,NULL,0,NULL,1,NULL),(33,'magag','2014-01-05 18:30:39',NULL,NULL,0,NULL,1,NULL),(34,'','2014-01-05 20:43:57',NULL,NULL,0,NULL,1,NULL),(35,'testactivity','2014-02-22 21:31:17',NULL,NULL,0,NULL,1,NULL);
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `age` smallint(6) DEFAULT NULL,
  `weight` smallint(6) DEFAULT NULL,
  `height` smallint(6) DEFAULT NULL,
  `sex` tinyint(4) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'nir','nir','test',30,170,6,0,NULL,NULL),(2,'pritesh','pritesh','test',30,100,6,0,NULL,'2014-02-06 00:21:54'),(22,NULL,'pritesh2@test.com','test',NULL,NULL,NULL,NULL,'2013-12-23 22:05:22','2013-12-23 22:05:22'),(23,NULL,'pritesh3@test.com','test',NULL,NULL,NULL,NULL,'2013-12-29 22:30:43','2013-12-29 22:30:43'),(25,NULL,'pritesh@test.com','Test123456',NULL,NULL,NULL,NULL,'2014-02-27 21:21:52','2014-02-27 21:21:52'),(26,NULL,'pritesh@test.com','Test123456',NULL,NULL,NULL,NULL,'2014-02-27 21:22:18','2014-02-27 21:22:18'),(27,NULL,'pritesh@test.com','',NULL,NULL,NULL,NULL,'2014-02-27 21:22:56','2014-02-27 21:22:56'),(31,NULL,'email','',NULL,NULL,NULL,NULL,'2014-03-01 22:12:21','2014-03-01 22:12:21');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_activity`
--

DROP TABLE IF EXISTS `user_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `distance` double DEFAULT NULL,
  `pace` double DEFAULT NULL,
  `time` bigint(20) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_activity`
--

LOCK TABLES `user_activity` WRITE;
/*!40000 ALTER TABLE `user_activity` DISABLE KEYS */;
INSERT INTO `user_activity` VALUES (23,25,1,'2013-09-28 12:02:13','2013-09-28 12:02:13',NULL,NULL,NULL,NULL),(24,26,1,'2013-09-28 12:06:26','2013-09-28 12:06:26',NULL,NULL,NULL,NULL),(25,27,2,'2013-09-28 17:16:16','2013-09-28 17:16:16',NULL,NULL,NULL,NULL),(26,28,2,'2013-09-28 17:16:23','2013-09-28 17:16:23',NULL,NULL,NULL,NULL),(27,29,2,'2013-09-28 17:16:29','2013-09-28 17:16:29',NULL,NULL,NULL,NULL),(28,30,1,'2013-10-01 23:10:05','2013-10-01 23:10:05',NULL,NULL,NULL,NULL),(29,31,1,'2013-10-04 23:58:27','2013-10-04 23:58:27',NULL,NULL,NULL,NULL),(30,32,1,'2014-01-05 18:27:28','2014-01-05 18:27:28',NULL,NULL,NULL,NULL),(31,33,1,'2014-01-05 18:30:39','2014-01-05 18:30:39',NULL,NULL,NULL,NULL),(32,34,1,'2014-01-05 20:43:57','2014-01-05 20:43:57',NULL,NULL,NULL,NULL),(33,35,1,'2014-02-22 21:31:17','2014-02-22 21:31:17',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_relations`
--

DROP TABLE IF EXISTS `user_relations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_relations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `related_user_id` int(11) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_relations`
--

LOCK TABLES `user_relations` WRITE;
/*!40000 ALTER TABLE `user_relations` DISABLE KEYS */;
INSERT INTO `user_relations` VALUES (1,22,2,'2013-12-26 22:54:04','2013-12-26 22:54:04',NULL);
/*!40000 ALTER TABLE `user_relations` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-06-22 14:12:59
