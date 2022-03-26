-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: booking
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `camera`
--

DROP TABLE IF EXISTS `camera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `camera` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_tipo_camera` int NOT NULL,
  `id_hotel` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `camera_FK` (`id_hotel`),
  KEY `camera_FK_1` (`id_tipo_camera`),
  CONSTRAINT `camera_FK` FOREIGN KEY (`id_hotel`) REFERENCES `hotel` (`id`),
  CONSTRAINT `camera_FK_1` FOREIGN KEY (`id_tipo_camera`) REFERENCES `tipo_camera` (`id`),
  CONSTRAINT `FKs0imosagslruihur3sfhq4nx2` FOREIGN KEY (`id`) REFERENCES `tipo_camera` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `camera`
--

LOCK TABLES `camera` WRITE;
/*!40000 ALTER TABLE `camera` DISABLE KEYS */;
INSERT INTO `camera` VALUES (2,1,12);
/*!40000 ALTER TABLE `camera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disponibilita`
--

DROP TABLE IF EXISTS `disponibilita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `disponibilita` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_camera` int NOT NULL,
  `data_inizio_prenotazione` date NOT NULL,
  `data_fine_prenotazione` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `disponibilita_un` (`id_camera`,`data_inizio_prenotazione`,`data_fine_prenotazione`),
  CONSTRAINT `disponibilita_FK` FOREIGN KEY (`id_camera`) REFERENCES `camera` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci COMMENT='Disponibilita hotel';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disponibilita`
--

LOCK TABLES `disponibilita` WRITE;
/*!40000 ALTER TABLE `disponibilita` DISABLE KEYS */;
INSERT INTO `disponibilita` VALUES (13,2,'2022-03-17','2022-03-19'),(14,2,'2022-03-20','2022-03-21');
/*!40000 ALTER TABLE `disponibilita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotel` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_tipo_hotel` int NOT NULL,
  `nome` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `indirizzo` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `telefono` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `numero_camere` int NOT NULL,
  `numero_stelle` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `hotel_un` (`nome`),
  UNIQUE KEY `hotel_un_1` (`indirizzo`),
  UNIQUE KEY `hotel_un_2` (`email`),
  UNIQUE KEY `hotel_un_3` (`telefono`),
  KEY `hotel_FK` (`id_tipo_hotel`),
  CONSTRAINT `hotel_FK` FOREIGN KEY (`id_tipo_hotel`) REFERENCES `tipo_hotel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
INSERT INTO `hotel` VALUES (12,4,'hotel dhn','via fasulla 00','hotel@dhn.it','055123456',10,1),(13,7,'uffa','ma che ne so','test@email.com','099887651',99,5);
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sqcamera`
--

DROP TABLE IF EXISTS `sqcamera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sqcamera` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sqcamera`
--

LOCK TABLES `sqcamera` WRITE;
/*!40000 ALTER TABLE `sqcamera` DISABLE KEYS */;
INSERT INTO `sqcamera` VALUES (3);
/*!40000 ALTER TABLE `sqcamera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sqdisponibilita`
--

DROP TABLE IF EXISTS `sqdisponibilita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sqdisponibilita` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sqdisponibilita`
--

LOCK TABLES `sqdisponibilita` WRITE;
/*!40000 ALTER TABLE `sqdisponibilita` DISABLE KEYS */;
INSERT INTO `sqdisponibilita` VALUES (15);
/*!40000 ALTER TABLE `sqdisponibilita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sqhotel`
--

DROP TABLE IF EXISTS `sqhotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sqhotel` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sqhotel`
--

LOCK TABLES `sqhotel` WRITE;
/*!40000 ALTER TABLE `sqhotel` DISABLE KEYS */;
INSERT INTO `sqhotel` VALUES (14);
/*!40000 ALTER TABLE `sqhotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sqtipo_camera`
--

DROP TABLE IF EXISTS `sqtipo_camera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sqtipo_camera` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sqtipo_camera`
--

LOCK TABLES `sqtipo_camera` WRITE;
/*!40000 ALTER TABLE `sqtipo_camera` DISABLE KEYS */;
INSERT INTO `sqtipo_camera` VALUES (3);
/*!40000 ALTER TABLE `sqtipo_camera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sqtipo_hotel`
--

DROP TABLE IF EXISTS `sqtipo_hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sqtipo_hotel` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sqtipo_hotel`
--

LOCK TABLES `sqtipo_hotel` WRITE;
/*!40000 ALTER TABLE `sqtipo_hotel` DISABLE KEYS */;
INSERT INTO `sqtipo_hotel` VALUES (8);
/*!40000 ALTER TABLE `sqtipo_hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_camera`
--

DROP TABLE IF EXISTS `tipo_camera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_camera` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipologia` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `posti` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tipo_camera_un` (`tipologia`,`posti`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_camera`
--

LOCK TABLES `tipo_camera` WRITE;
/*!40000 ALTER TABLE `tipo_camera` DISABLE KEYS */;
INSERT INTO `tipo_camera` VALUES (1,'deluxe',2),(2,'singola',1);
/*!40000 ALTER TABLE `tipo_camera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_hotel`
--

DROP TABLE IF EXISTS `tipo_hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_hotel` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipologia` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tipo_hotel_un` (`tipologia`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_hotel`
--

LOCK TABLES `tipo_hotel` WRITE;
/*!40000 ALTER TABLE `tipo_hotel` DISABLE KEYS */;
INSERT INTO `tipo_hotel` VALUES (4,'albergo'),(5,'appartamento'),(6,'campeggio'),(7,'residence');
/*!40000 ALTER TABLE `tipo_hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'booking'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-17 23:51:06
