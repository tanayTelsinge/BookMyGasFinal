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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userid` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phonenumber` varchar(15) DEFAULT NULL,
  `profilepicture` varchar(255) DEFAULT NULL,
  `usertype` varchar(20) NOT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,NULL,'test@gmail.com','testuser','$2a$10$p8sx5R9fdmf4XiFq4s9jn.Un1jxkeexmeG.qHX2QwNnQFOl4TTTzq',NULL,NULL,'admin'),(6,'pune','tanay@gmail.com','newtest','$2a$10$0jAPAVrbT1m0tIrrMijHwui.bKbm6H7j9OI.YKE0ziSq5.0TGqDg2','13123',NULL,'admin'),(7,'elm street, pune','john.doe@gmail.com','John Doe','$2a$10$t1SliDm7PR8FzkMxRAuVi.MHwxm9ns9VvISPvj1JhfY02.WhIwY.u','987654321',NULL,'admin'),(8,'pune','janesmith@gmail.com','Jane Smith','$2a$10$z.rfD7UcJCyHcTjaQyJknur3dtFzHrMRGUzFJsevwqSWMmrWVxh2e','123123123',NULL,'customer'),(9,'pune','testnew@gmail.com','test','$2a$10$Tuvgo44GsSUXqFiQpgsb/.MwfG5PxeAwLBVUcHjSwteqSguK7ZcbW','1414',NULL,'customer'),(10,'pune','aniket@gmail.com','aniket','$2a$10$B3lHsp.RlaVc5KQNgdTn7.RjBGyZm28rbs4fgLbs6O6Td/oBfwxMy','123123123',NULL,'customer');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
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
