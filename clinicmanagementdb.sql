-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: clinicmanagement
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime NOT NULL,
  `status` varchar(50) DEFAULT NULL,
  `patientId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_appointment_patient_idx` (`patientId`),
  CONSTRAINT `fk_appointment_patientId` FOREIGN KEY (`patientId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (32,'2023-08-20 23:37:16','Completed',28),(33,'2023-08-20 23:39:54','Completed',45),(34,'2023-08-21 00:54:29','Completed',42),(35,'2023-08-21 00:54:32','Completed',42),(37,'2023-08-22 12:23:00','Completed',42),(39,'2023-08-31 12:23:00','Completed',42),(40,'2023-08-23 17:26:11','Completed',42),(41,'2023-08-22 10:26:00','Completed',42),(42,'2023-08-21 10:26:00','Completed',42),(44,'2023-08-23 22:31:48','Completed',42),(45,'2023-08-23 22:33:07','Completed',42),(46,'2023-08-23 22:38:28','Completed',42),(48,'2023-08-23 23:41:55','Completed',42),(61,'2023-08-24 16:48:46','Completed',42),(63,'2023-08-24 19:02:41','Completed',42),(69,'2023-08-24 19:22:16','Pending payment',42),(70,'2023-08-26 23:27:43','Confirmed',73),(71,'2023-09-09 00:39:33','Confirmed',42),(72,'2023-09-16 00:29:00','Confirmed',42),(73,'2023-09-09 16:32:18','Confirmed',42),(75,'2023-09-15 10:09:00','Confirmed',81);
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicine`
--

DROP TABLE IF EXISTS `medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicine` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `quantity` int NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `unit_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_medicine_unit_idx` (`unit_id`),
  CONSTRAINT `fk_medicine_unit` FOREIGN KEY (`unit_id`) REFERENCES `unit` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine`
--

LOCK TABLES `medicine` WRITE;
/*!40000 ALTER TABLE `medicine` DISABLE KEYS */;
INSERT INTO `medicine` VALUES (4,'yui',1.00,0,'2023-08-23 18:40:37','2023-08-23 18:40:37',2),(5,'tyu',6.00,22216,'2023-08-23 18:40:33','2023-08-23 18:40:33',2),(6,'rty',2.00,180,'2023-08-23 18:40:30','2023-08-23 18:40:30',2),(7,'ert',1.00,1291,'2023-08-23 18:40:28','2023-08-23 18:40:28',1),(12,'qwe',654.00,2522,'2023-08-22 00:19:33','2023-08-22 00:19:33',1);
/*!40000 ALTER TABLE `medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount_received` decimal(10,2) NOT NULL,
  `created_date` datetime NOT NULL,
  `payment_method_id` int NOT NULL,
  `patient_id` int NOT NULL,
  `appointment_id` int NOT NULL,
  `fee` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_payment_patient_idx` (`patient_id`),
  KEY `fk_payment_appointment_idx` (`appointment_id`),
  KEY `fk_payment_method_idx` (`payment_method_id`),
  CONSTRAINT `fk_payment_appointment` FOREIGN KEY (`appointment_id`) REFERENCES `appointment` (`id`),
  CONSTRAINT `fk_payment_method` FOREIGN KEY (`payment_method_id`) REFERENCES `payment_method` (`id`),
  CONSTRAINT `fk_payment_patient` FOREIGN KEY (`patient_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (2,999.00,'2023-08-23 15:58:49',1,42,37,200.00),(3,999.00,'2023-08-23 15:59:59',2,28,32,200.00),(4,2.00,'2023-08-23 17:56:35',1,42,34,200.00),(5,200.00,'2023-08-23 17:57:13',1,42,35,200.00),(6,22222.00,'2023-08-23 22:32:48',1,42,39,200.00),(7,2222.00,'2023-08-23 22:32:54',1,42,44,200.00),(8,222222.00,'2023-08-23 22:40:33',1,45,33,200.00),(9,222.00,'2023-08-23 22:40:47',2,42,40,200.00),(10,311.00,'2023-08-23 22:40:53',2,42,45,200.00),(11,2222.00,'2023-08-23 22:41:00',1,42,42,200.00),(12,4441.00,'2023-08-23 22:41:09',1,42,41,200.00),(13,322.00,'2023-08-24 00:12:13',1,42,46,200.00),(14,233.00,'2023-08-24 19:03:23',1,42,61,200.00),(15,322.00,'2023-08-26 23:28:18',1,42,48,200.00),(16,2222.00,'2023-09-09 17:11:14',1,42,63,200.00);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_method`
--

DROP TABLE IF EXISTS `payment_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_method` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_method`
--

LOCK TABLES `payment_method` WRITE;
/*!40000 ALTER TABLE `payment_method` DISABLE KEYS */;
INSERT INTO `payment_method` VALUES (1,'cash'),(2,'banking');
/*!40000 ALTER TABLE `payment_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescription`
--

DROP TABLE IF EXISTS `prescription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prescription` (
  `id` int NOT NULL AUTO_INCREMENT,
  `symptom` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `diagnose` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `created_date` datetime NOT NULL,
  `appointment_id` int NOT NULL,
  `doctor_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_prescription_appointment_idx` (`appointment_id`),
  KEY `fk_prescription_doctor_idx` (`doctor_id`),
  CONSTRAINT `fk_prescription_appointment` FOREIGN KEY (`appointment_id`) REFERENCES `appointment` (`id`),
  CONSTRAINT `fk_prescription_doctor` FOREIGN KEY (`doctor_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescription`
--

LOCK TABLES `prescription` WRITE;
/*!40000 ALTER TABLE `prescription` DISABLE KEYS */;
INSERT INTO `prescription` VALUES (5,'hehe','hehe','2023-08-22 17:08:39',32,45),(6,'aaa','aaa','2023-08-22 17:08:56',39,45),(7,'ewq','eqw','2023-08-22 17:22:18',34,45),(8,'dsa','sda','2023-08-22 22:44:00',37,45),(9,'weq','eqw','2023-08-23 17:53:15',35,45),(10,'ewqeq','eqweqw','2023-08-23 22:32:39',44,45),(11,'dasdas','dasdsadas','2023-08-23 22:37:37',33,45),(12,'bvcbcbc','dvcxv','2023-08-23 22:37:45',40,45),(13,'bdfbdb','fdbdfbdf','2023-08-23 22:37:56',42,45),(14,'eqwewq','eqwewqewq','2023-08-23 22:38:11',41,45),(15,'wqeqeq','weqeqe','2023-08-23 22:38:24',45,45),(16,'eqwewq','ewqewq','2023-08-24 00:10:21',46,45),(17,'2','2','2023-08-24 00:12:33',48,45),(18,'hgfjmmmm','mmmmmm','2023-08-24 19:03:09',61,45),(19,'eqw','ewq','2023-08-26 23:28:07',63,45),(20,'eqeq','ewqewqe','2023-09-09 17:10:49',69,45);
/*!40000 ALTER TABLE `prescription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescription_medicine`
--

DROP TABLE IF EXISTS `prescription_medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prescription_medicine` (
  `id` int NOT NULL AUTO_INCREMENT,
  `prescription_id` int NOT NULL,
  `medicine_id` int DEFAULT NULL,
  `quantity` int NOT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_preme_pre_idx` (`prescription_id`),
  KEY `fk_preme_me_idx` (`medicine_id`),
  CONSTRAINT `fk_preme_me` FOREIGN KEY (`medicine_id`) REFERENCES `medicine` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_preme_pre` FOREIGN KEY (`prescription_id`) REFERENCES `prescription` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescription_medicine`
--

LOCK TABLES `prescription_medicine` WRITE;
/*!40000 ALTER TABLE `prescription_medicine` DISABLE KEYS */;
INSERT INTO `prescription_medicine` VALUES (17,5,12,6,2.00),(18,5,6,7,2.00),(19,6,4,1,3.00),(20,7,6,12,9.00),(21,8,12,1,654.00),(22,10,7,2,1.00),(23,10,6,1,2.00),(24,11,12,7,654.00),(25,12,6,5,2.00),(26,12,7,2,1.00),(27,13,12,2,654.00),(28,13,7,2,1.00),(29,13,6,3,2.00),(30,13,5,3,6.00),(31,13,4,3,1.00),(32,14,7,6,1.00),(33,14,12,6,654.00),(34,14,6,3,2.00),(35,14,5,3,6.00),(36,14,4,9,1.00),(37,15,7,8,1.00),(38,15,6,12,2.00),(39,16,7,8,1.00),(40,17,7,2,1.00),(41,18,NULL,4,3.00),(42,18,6,6,2.00),(43,19,12,1,654.00),(44,19,7,1,1.00),(45,20,12,4,654.00);
/*!40000 ALTER TABLE `prescription_medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_DOCTOR'),(3,'ROLE_NURSE'),(4,'ROLE_PATIENT');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rule`
--

DROP TABLE IF EXISTS `rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rule` (
  `id` int NOT NULL AUTO_INCREMENT,
  `appointment_limit` int NOT NULL,
  `fee` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rule`
--

LOCK TABLES `rule` WRITE;
/*!40000 ALTER TABLE `rule` DISABLE KEYS */;
INSERT INTO `rule` VALUES (1,20,200.00);
/*!40000 ALTER TABLE `rule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shift`
--

DROP TABLE IF EXISTS `shift`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shift` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shift`
--

LOCK TABLES `shift` WRITE;
/*!40000 ALTER TABLE `shift` DISABLE KEYS */;
INSERT INTO `shift` VALUES (12,'shift test','2023-09-03 17:04:00','2023-09-16 17:04:00','eqeq');
/*!40000 ALTER TABLE `shift` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit`
--

DROP TABLE IF EXISTS `unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unit` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit`
--

LOCK TABLES `unit` WRITE;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
INSERT INTO `unit` VALUES (1,'Tablet'),(2,'Bottle'),(3,'Vial');
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `date_of_birth` datetime NOT NULL,
  `gender` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `address` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `avatar` varchar(1000) DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `role_id` int DEFAULT '4',
  PRIMARY KEY (`id`),
  KEY `fk_user_role_idx` (`role_id`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'pho','dao minh','2002-02-08 00:00:00','Male','minhpho82021@gmail.com','0966216173','cu chi','admin','$2a$10$mk41T/cCP5AZTs7//I2/m.IRdWE3pC9.x5LrjHZvRnSScJfLlbgA.','https://res.cloudinary.com/duvzl7dla/image/upload/v1693998416/x09fri8bidgt3oukmfub.png','2023-08-21 00:44:50',1),(28,'ewq','ewq','2222-02-22 00:00:00','Male','ewq@gmail.com','3213213211','ewq','ewq','$2a$10$OugVBi8vqujG5LOGdKNVo.P8vMyi4IU3SjmCxTv.MV0O5RuI86XAa','https://res.cloudinary.com/duvzl7dla/image/upload/v1693998416/x09fri8bidgt3oukmfub.png','2023-08-21 00:44:44',3),(40,'eq','eq','2023-08-13 00:00:00','Male','minhpho82022@gmail.com','3213213211','a','b','$2a$10$Q4feqMGo8Hwe4qtEvqQbq.HoIZ./t6.9oTQZOs2Kss4Urw.mAAQ62','https://res.cloudinary.com/duvzl7dla/image/upload/v1693998416/x09fri8bidgt3oukmfub.png','2023-08-21 01:09:15',3),(41,'updated','updated','2222-02-22 00:00:00','Male','minhpho82023@gmail.com','3213213211','a','updated','$2a$10$Keh88WdMqWIMie9SfPhbOedDxMcM9d5j09ajeWUcMme4yAR5LGg4m','https://res.cloudinary.com/duvzl7dla/image/upload/v1693998416/x09fri8bidgt3oukmfub.png','2023-08-21 00:44:34',4),(42,'haaaaaaaaaa','qew','2222-02-22 00:00:00','Female','daominh8202@gmail.com','3213213211','a','a','$2a$10$drNK88hjvV0QnGrzj3Biz.23JrBUUVvb7uGkfRF3AY0ZQCLLt.NE6','https://res.cloudinary.com/duvzl7dla/image/upload/v1693998416/x09fri8bidgt3oukmfub.png','2023-08-21 00:54:19',4),(43,'aaaa','Phố','2023-08-30 00:00:00','Male','2051052099pho1@ou.edu.vn','0966216173','079202010461','ewqqqqq','$2a$10$F498WhCq3hQfkwZXrkWo5eRnbtPKSe4/iAPV5Dn4pW4u7lcBIT09G','https://res.cloudinary.com/duvzl7dla/image/upload/v1693998416/x09fri8bidgt3oukmfub.png','2023-08-21 00:44:25',2),(45,'das','dda','2311-01-31 00:00:00','Female','2051052099pho2@ou.edu.vn','0966216173','củ chi','c','$2a$10$2Mu6zgP/YWAiQKahK/ZiM.nD86bpktrf6RUdt3MRKbmirK2d7KESW','https://res.cloudinary.com/duvzl7dla/image/upload/v1693998416/x09fri8bidgt3oukmfub.png','2023-08-26 00:18:12',2),(72,'dwqdqw','dwqdwq','2023-08-26 00:00:00','Male','daominewqeqh8202@gmail.com','3213213211','2èe2èe','d','$2a$10$mtfyyxgZoqRKnFvJ3kXkJOmxaBHfWNR1vRlJkJk0RLdGsuUEO2AJ2','https://res.cloudinary.com/duvzl7dla/image/upload/v1693998416/x09fri8bidgt3oukmfub.png','2023-08-26 18:54:58',4),(73,'dadw','dda','2023-08-26 00:00:00','Female','2051052099pho@ou.edu.vn','3213213121','ewq222','aaaaaa','$2a$10$ZqiDFJ0LpEdgLPYZTt.xcu1gjnbwGWK5whp0WIfrvAzv34LcayhCC','https://res.cloudinary.com/duvzl7dla/image/upload/v1693998416/x09fri8bidgt3oukmfub.png','2023-08-26 23:27:03',4),(74,'e','e','2023-08-26 00:00:00','Male','ewq@gmail.com','3213213182','qe','weq','$2a$10$4qzRGOTTz5QiJHp6NXYDve.MzaED46LU1FF/cHDhaYgVvb69P5b/C','https://res.cloudinary.com/duvzl7dla/image/upload/v1693998416/x09fri8bidgt3oukmfub.png','2023-08-26 23:34:14',4),(75,'mnb','mnbmnb','2023-09-06 00:00:00','Male','mnbmnb@gmail.com','3213213211','21321dwqdd','dddaaa','$2a$10$gkFwtbUQ1D461BVnxo6s.etZa2i.pXN34uT.lq0FEh7q.Gi9ojLJa','https://res.cloudinary.com/duvzl7dla/image/upload/v1693998416/x09fri8bidgt3oukmfub.png','2023-09-06 17:25:03',4),(76,'lklk','lklk','2023-09-06 00:00:00','Male','minhpho8202lklklk@gmail.com','6546546545','Slklklk','aewqewqewq','$2a$10$AswkfvkFGIBR0vmvwi6rJO5/H9kKEgEty0vBXsKrbNg5kkScs37Dy','https://res.cloudinary.com/duvzl7dla/image/upload/v1693998416/x09fri8bidgt3oukmfub.png','2023-09-06 18:49:08',4),(78,'eqeq','eqeq','2023-09-19 00:00:00','Male','minhpho8202222@gmail.com','3213213212','Số 21 Đường 58 Ấp Chợ Xã Tân Phú Trung Huyện Củ Chi Tphcm','213','$2a$10$YK4vR2mTJmdc0vk18kISKulOmlhRVcVtijuesRaRR7Dnm5g7RdRvm','https://res.cloudinary.com/duvzl7dla/image/upload/v1694023173/brptggodk7gsdqubc7xz.png','2023-09-07 01:00:30',2),(79,'da','da','2023-09-09 00:00:00','Male','minhpho8202221@gmail.com','3213213209','Số 21 Đường 58 Ấp Chợ Xã Tân Phú Trung Huyện Củ Chi Tphcm','abc','$2a$10$XfdBkuX.rdtrJ.g2Ol/ELOGAJNFmlpCTCsXsy1XQCAfODF1pxEKmS','https://res.cloudinary.com/duvzl7dla/image/upload/v1694248084/josjkgu0e87xb417yfeh.png','2023-09-09 15:28:01',4),(81,'ewq','ewq','2023-09-09 00:00:00','Male','minhpho82022eq@gmail.com','3213213211','Số 21 Đường 58 Ấp Chợ Xã Tân Phú Trung Huyện Củ Chi Tphcm','a2','$2a$10$U86SZLUElC4MabOOjslHE.JPYj78FDn7twJReweGTIcL3Tb.OkdG2','https://res.cloudinary.com/duvzl7dla/image/upload/v1694254144/fwx5jzuntorywlp70c5v.png','2023-09-09 17:09:03',4);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_shift`
--

DROP TABLE IF EXISTS `user_shift`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_shift` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `shift_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_us_user_idx` (`user_id`),
  KEY `fk_us_shift_idx` (`shift_id`),
  CONSTRAINT `fk_us_shift` FOREIGN KEY (`shift_id`) REFERENCES `shift` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_us_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_shift`
--

LOCK TABLES `user_shift` WRITE;
/*!40000 ALTER TABLE `user_shift` DISABLE KEYS */;
INSERT INTO `user_shift` VALUES (58,43,12),(60,78,12);
/*!40000 ALTER TABLE `user_shift` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-09 17:21:32
