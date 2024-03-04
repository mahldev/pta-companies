-- MySQL dump 10.13  Distrib 5.7.44, for Linux (x86_64)
--
-- Host: localhost    Database: companies
-- ------------------------------------------------------
-- Server version	5.7.44

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
-- Table structure for table `companies`
--

DROP TABLE IF EXISTS `companies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `companies` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number_of_employees` int(11) DEFAULT NULL,
  `revenues` bigint(20) DEFAULT NULL,
  `sector_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbrq6vnyuvxvh8vfgo1osewwtd` (`sector_id`),
  CONSTRAINT `FKbrq6vnyuvxvh8vfgo1osewwtd` FOREIGN KEY (`sector_id`) REFERENCES `company_sectors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companies`
--

LOCK TABLES `companies` WRITE;
/*!40000 ALTER TABLE `companies` DISABLE KEYS */;
INSERT INTO `companies` VALUES
(1, 'Empresa multinacional de consultoría, servicios tecnológicos y outsourcing.', 'Accenture', 4000, 5000, 1),
(2, 'Empresa multinacional de servicios informáticos con presencia en más de 70 países.', 'Atos', 11000, 12000, 2),
(3, 'Empresa multinacional de inspección, certificación y ensayos.', 'Dekra', 3300, 35000, 3),
(4, 'Empresa multinacional de telecomunicaciones que fabrica equipos de red y ofrece servicios de telecomunicaciones.', 'Ericsson', 27000, 100000, 4),
(5, 'Empresa multinacional de tecnología de la información que ofrece una amplia gama de productos y servicios.', 'Fujitsu', 36000, 137000, 5),
(6, 'Operador de telecomunicaciones español que ofrece servicios de telefonía fija, móvil e internet.', 'GrupoMásMóvil', 2300, 2500, 4),
(7, 'Empresa multinacional de tecnología de la información que ofrece una amplia gama de productos y servicios.', 'Huawei', 136000, 195000, 5),
(8, 'Empresa multinacional de tecnología de la información que ofrece una amplia gama de productos y servicios.', 'IBM', 73600, 350000, 5),
(9, 'Empresa española de tecnología de la información y defensa que ofrece una amplia gama de productos y servicios.', 'Indra', 3100, 49000, 6),
(10, 'Empresa multinacional de servicios informáticos con presencia en más de 50 países.', 'NTTDATA', 16000, 130000, 2),
(11, 'Empresa multinacional de software que desarrolla y comercializa una amplia gama de productos de software.', 'Oracle', 39000, 39000, 7),
(12, 'Operador de telecomunicaciones español que ofrece servicios de telefonía fija, móvil e internet.', 'Telefónica', 48000, 132000, 4);

/*!40000 ALTER TABLE `companies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_sectors`
--

DROP TABLE IF EXISTS `company_sectors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company_sectors` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_sectors`
--

LOCK TABLES `company_sectors` WRITE;
/*!40000 ALTER TABLE `company_sectors` DISABLE KEYS */;
INSERT INTO `company_sectors` VALUES 
(1, 'Consultoría tecnológica'),
(2, 'Servicios informáticos'),
(3, 'Inspección y certificación'),
(4, 'Telecomunicaciones'),
(5, 'Tecnología de la información'),
(6, 'Tecnología de la información y defensa'),
(7, 'Software');
/*!40000 ALTER TABLE `company_sectors` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-02 15:47:05
