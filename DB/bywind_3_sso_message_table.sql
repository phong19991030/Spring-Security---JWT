-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bywind_3_sso
-- ------------------------------------------------------
-- Server version	5.5.5-10.11.2-MariaDB

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
-- Table structure for table `message_table`
--

DROP TABLE IF EXISTS `message_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message_table` (
  `MESSAGE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CONTENT` varchar(50) DEFAULT NULL,
  `GROUP_ID` int(11) DEFAULT NULL,
  `INS_ID` varchar(50) DEFAULT NULL,
  `INS_DT` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`MESSAGE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_table`
--

LOCK TABLES `message_table` WRITE;
/*!40000 ALTER TABLE `message_table` DISABLE KEYS */;
INSERT INTO `message_table` VALUES (107,' ,m. ',1,'2','2023-06-19 16:41:29'),(109,' vxcvx ',2,'3','2023-06-19 17:01:38'),(110,' cvbcb ',1,'1','2023-06-19 17:07:08'),(111,' vxcvx ',2,'3','2023-06-19 17:07:16'),(112,' ,m. ',1,'2','2023-06-19 17:07:18'),(113,' zxczxcz ',2,'3','2023-06-19 17:12:23'),(114,' 1231231 ',2,'3','2023-06-19 17:12:36'),(115,' xcvxcv ',2,'3','2023-06-19 17:12:47'),(116,' 213123 ',2,'3','2023-06-19 17:12:49'),(117,' cvbcvb ',1,'2','2023-06-19 17:13:08'),(118,' cvbcvb ',1,'2','2023-06-19 17:13:14'),(119,' 123123 ',1,'2','2023-06-19 17:13:21');
/*!40000 ALTER TABLE `message_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-27 16:44:39
