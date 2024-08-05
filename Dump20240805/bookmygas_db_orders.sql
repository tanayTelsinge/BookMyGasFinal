-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: bookmygas_db
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `orderid` int NOT NULL AUTO_INCREMENT,
  `deliverydate` datetime(6) DEFAULT NULL,
  `orderdate` datetime(6) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `totalprice` double DEFAULT NULL,
  `agencyid` int NOT NULL,
  `userid` int NOT NULL,
  PRIMARY KEY (`orderid`),
  KEY `FK8s17nyj1imm49avt8okp0fa8e` (`agencyid`),
  KEY `FKdxew8n76x1bnoxjas0qxrlbq6` (`userid`),
  CONSTRAINT `FK8s17nyj1imm49avt8okp0fa8e` FOREIGN KEY (`agencyid`) REFERENCES `gasagency` (`agencyid`),
  CONSTRAINT `FKdxew8n76x1bnoxjas0qxrlbq6` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'2024-07-31 14:00:00.000000','2024-07-30 10:00:00.000000','Completed',50,1,7),(2,'2024-08-01 15:00:00.000000','2024-07-31 11:00:00.000000','Pending',80,2,8),(5,'2024-08-06 18:38:46.963000','2024-08-04 18:38:46.963000','PENDING',802,1,7),(6,'2024-08-06 19:07:10.062000','2024-08-04 19:07:10.062000','PENDING',802,1,7),(8,'2024-08-06 19:44:59.328000','2024-08-04 19:44:59.328000','PENDING',802,1,7),(9,'2024-08-06 19:48:33.409000','2024-08-04 19:48:33.409000','PENDING',802,1,7),(10,'2024-08-07 11:16:01.455000','2024-08-05 11:16:01.455000','PENDING',802,1,10);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-05 17:06:55
