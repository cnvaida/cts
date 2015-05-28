-- MySQL dump 10.13  Distrib 5.6.16, for Win32 (x86)
--
-- Host: localhost    Database: netstudent
-- ------------------------------------------------------
-- Server version	5.6.16

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
-- Table structure for table `admins`
--

DROP TABLE IF EXISTS `admins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admins` (
  `id_admin` int(2) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` text NOT NULL,
  `email` varchar(20) NOT NULL,
  `privilegiu` int(1) NOT NULL,
  PRIMARY KEY (`id_admin`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admins`
--

LOCK TABLES `admins` WRITE;
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
INSERT INTO `admins` VALUES (1,'admin','test','cn.vaida92@gmail.com',2);
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `an_studiu`
--

DROP TABLE IF EXISTS `an_studiu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `an_studiu` (
  `id_an_studiu` int(1) NOT NULL AUTO_INCREMENT,
  `nume` varchar(10) NOT NULL,
  PRIMARY KEY (`id_an_studiu`),
  UNIQUE KEY `nume` (`nume`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `an_studiu`
--

LOCK TABLES `an_studiu` WRITE;
/*!40000 ALTER TABLE `an_studiu` DISABLE KEYS */;
INSERT INTO `an_studiu` VALUES (17,'1'),(18,'2'),(19,'3'),(20,'4'),(21,'5');
/*!40000 ALTER TABLE `an_studiu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `anunturi`
--

DROP TABLE IF EXISTS `anunturi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `anunturi` (
  `id_anunt` int(5) NOT NULL AUTO_INCREMENT,
  `anunt` text NOT NULL,
  PRIMARY KEY (`id_anunt`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anunturi`
--

LOCK TABLES `anunturi` WRITE;
/*!40000 ALTER TABLE `anunturi` DISABLE KEYS */;
INSERT INTO `anunturi` VALUES (10,'Perioada de practica incepe pe 21.05.2014.'),(11,'Sesiunea din vara se va prelungi pana pe 15 iulie.');
/*!40000 ALTER TABLE `anunturi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forma_finantare`
--

DROP TABLE IF EXISTS `forma_finantare`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forma_finantare` (
  `id_forma_finantare` int(1) NOT NULL AUTO_INCREMENT,
  `denumire` varchar(50) NOT NULL,
  PRIMARY KEY (`id_forma_finantare`),
  UNIQUE KEY `denumire` (`denumire`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forma_finantare`
--

LOCK TABLES `forma_finantare` WRITE;
/*!40000 ALTER TABLE `forma_finantare` DISABLE KEYS */;
INSERT INTO `forma_finantare` VALUES (17,'Buget'),(16,'Taxa');
/*!40000 ALTER TABLE `forma_finantare` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupe`
--

DROP TABLE IF EXISTS `grupe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupe` (
  `id_grupa` int(3) NOT NULL AUTO_INCREMENT,
  `nume` varchar(10) NOT NULL,
  `id_serie` int(2) NOT NULL,
  PRIMARY KEY (`id_grupa`),
  KEY `id_serie` (`id_serie`),
  CONSTRAINT `grupe_ibfk_1` FOREIGN KEY (`id_serie`) REFERENCES `serii` (`id_serie`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupe`
--

LOCK TABLES `grupe` WRITE;
/*!40000 ALTER TABLE `grupe` DISABLE KEYS */;
INSERT INTO `grupe` VALUES (6,'1033',15),(7,'1032',15);
/*!40000 ALTER TABLE `grupe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materii`
--

DROP TABLE IF EXISTS `materii`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `materii` (
  `id_materie` int(3) NOT NULL AUTO_INCREMENT,
  `denumire` text NOT NULL,
  PRIMARY KEY (`id_materie`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materii`
--

LOCK TABLES `materii` WRITE;
/*!40000 ALTER TABLE `materii` DISABLE KEYS */;
INSERT INTO `materii` VALUES (15,'Matematica'),(16,'Fizica'),(17,'Geometrie'),(18,'Mecanica');
/*!40000 ALTER TABLE `materii` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `note`
--

DROP TABLE IF EXISTS `note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `note` (
  `id_nota` int(11) NOT NULL AUTO_INCREMENT,
  `nota` int(2) DEFAULT NULL,
  `calificativ` text,
  `id_materie` int(3) NOT NULL,
  `id_student` int(5) NOT NULL,
  PRIMARY KEY (`id_nota`),
  KEY `id_materie` (`id_materie`),
  KEY `id_student` (`id_student`),
  CONSTRAINT `note_ibfk_1` FOREIGN KEY (`id_materie`) REFERENCES `materii` (`id_materie`),
  CONSTRAINT `note_ibfk_2` FOREIGN KEY (`id_student`) REFERENCES `student` (`id_student`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `note`
--

LOCK TABLES `note` WRITE;
/*!40000 ALTER TABLE `note` DISABLE KEYS */;
INSERT INTO `note` VALUES (10,6,'Examen',15,11),(11,7,'Examen',16,11),(12,8,'Examen',17,11),(13,5,'RestanÈ?Ä?',18,11);
/*!40000 ALTER TABLE `note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `serii`
--

DROP TABLE IF EXISTS `serii`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `serii` (
  `id_serie` int(2) NOT NULL AUTO_INCREMENT,
  `nume` varchar(10) NOT NULL,
  `id_specializare` int(2) NOT NULL,
  `id_an_studiu` int(2) NOT NULL,
  PRIMARY KEY (`id_serie`),
  KEY `id_an_studiu` (`id_an_studiu`),
  KEY `id_specializare` (`id_specializare`),
  CONSTRAINT `serii_ibfk_1` FOREIGN KEY (`id_an_studiu`) REFERENCES `an_studiu` (`id_an_studiu`),
  CONSTRAINT `serii_ibfk_2` FOREIGN KEY (`id_specializare`) REFERENCES `specializari` (`id_specializare`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `serii`
--

LOCK TABLES `serii` WRITE;
/*!40000 ALTER TABLE `serii` DISABLE KEYS */;
INSERT INTO `serii` VALUES (14,'A',9,17),(15,'B',9,17),(16,'A',9,18),(17,'A',10,20);
/*!40000 ALTER TABLE `serii` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specializari`
--

DROP TABLE IF EXISTS `specializari`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `specializari` (
  `id_specializare` int(2) NOT NULL AUTO_INCREMENT,
  `denumire` text NOT NULL,
  PRIMARY KEY (`id_specializare`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specializari`
--

LOCK TABLES `specializari` WRITE;
/*!40000 ALTER TABLE `specializari` DISABLE KEYS */;
INSERT INTO `specializari` VALUES (9,'Matematica'),(10,'Fizica');
/*!40000 ALTER TABLE `specializari` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id_student` int(5) NOT NULL AUTO_INCREMENT,
  `nume` varchar(30) NOT NULL,
  `prenume` varchar(20) NOT NULL,
  `cod_student` varchar(15) NOT NULL,
  `id_specializare` int(2) NOT NULL,
  `id_grupa` int(3) NOT NULL,
  `id_serie` int(2) NOT NULL,
  `id_forma_finantare` int(1) NOT NULL,
  `id_an_studiu` int(1) NOT NULL,
  `email` varchar(30) NOT NULL,
  `parola` text NOT NULL,
  `privilegiu` int(1) DEFAULT '0',
  PRIMARY KEY (`id_student`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `cod_student` (`cod_student`),
  KEY `specializare` (`id_specializare`),
  KEY `grupa` (`id_grupa`),
  KEY `seria` (`id_serie`),
  KEY `forma_finantare` (`id_forma_finantare`),
  KEY `an_studiu` (`id_an_studiu`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`id_specializare`) REFERENCES `specializari` (`id_specializare`),
  CONSTRAINT `student_ibfk_2` FOREIGN KEY (`id_grupa`) REFERENCES `grupe` (`id_grupa`),
  CONSTRAINT `student_ibfk_3` FOREIGN KEY (`id_serie`) REFERENCES `serii` (`id_serie`),
  CONSTRAINT `student_ibfk_4` FOREIGN KEY (`id_forma_finantare`) REFERENCES `forma_finantare` (`id_forma_finantare`),
  CONSTRAINT `student_ibfk_5` FOREIGN KEY (`id_an_studiu`) REFERENCES `an_studiu` (`id_an_studiu`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (11,'Popescu','Ion','POPION1033',9,6,15,17,18,'a.3_76@yahoo.com','popescu',0);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-03-18 22:16:20
