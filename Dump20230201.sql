-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: quanlynhankhau
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `hoatdong`
--

DROP TABLE IF EXISTS `hoatdong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoatdong` (
  `idHoatDong` int NOT NULL AUTO_INCREMENT,
  `idNhanKhau` int NOT NULL,
  `ngayBatDau` datetime NOT NULL,
  `ngayKetThuc` datetime NOT NULL,
  `hoatDong` varchar(45) NOT NULL,
  `soLuongBan` int NOT NULL,
  `soLuongGhe` int NOT NULL,
  `soLuongLoa` int NOT NULL,
  `soLuongDai` int NOT NULL,
  `soLuongManHinh` int NOT NULL,
  `soLuongDen` int NOT NULL,
  `lePhi` varchar(45) NOT NULL,
  `ghiChu` varchar(45) DEFAULT NULL,
  `xacNhan` varchar(45) NOT NULL,
  PRIMARY KEY (`idHoatDong`),
  UNIQUE KEY `idHoatDong_UNIQUE` (`idHoatDong`),
  KEY `idNhanKhau_idx` (`idNhanKhau`),
  CONSTRAINT `idNhanKhau` FOREIGN KEY (`idNhanKhau`) REFERENCES `nhankhau` (`idNhanKhau`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoatdong`
--

LOCK TABLES `hoatdong` WRITE;
/*!40000 ALTER TABLE `hoatdong` DISABLE KEYS */;
INSERT INTO `hoatdong` VALUES (7,8,'2023-01-31 00:00:00','2023-02-01 00:00:00','Tổ chức tiệc',1,1,1,1,1,1,'6.000.000 VND',NULL,'Chờ xác nhận'),(8,14,'2023-01-31 00:00:00','2023-02-01 00:00:00','Tổ chức tiệc',1,1,1,1,1,1,'2.000.000 VND',NULL,'Chờ xác nhận');
/*!40000 ALTER TABLE `hoatdong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hokhau`
--

DROP TABLE IF EXISTS `hokhau`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hokhau` (
  `idHoKhau` int NOT NULL AUTO_INCREMENT,
  `idNhanKhau` int NOT NULL,
  `diaChi` varchar(200) NOT NULL,
  `soNha` varchar(45) DEFAULT NULL,
  `duongPho` varchar(45) DEFAULT NULL,
  `phuong` varchar(45) DEFAULT NULL,
  `quan` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idHoKhau`),
  UNIQUE KEY `idHoKhau_UNIQUE` (`idHoKhau`),
  UNIQUE KEY `idNhanKhau_UNIQUE` (`idNhanKhau`),
  KEY `fk-nhankhau_idx` (`idNhanKhau`),
  CONSTRAINT `fk-hokhau-nhankhau` FOREIGN KEY (`idNhanKhau`) REFERENCES `nhankhau` (`idNhanKhau`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hokhau`
--

LOCK TABLES `hokhau` WRITE;
/*!40000 ALTER TABLE `hokhau` DISABLE KEYS */;
INSERT INTO `hokhau` VALUES (2,13,'288 - Nguyễn Xiển - Thanh Xuân - Thường Tín','288','Nguyễn Xiển','Thanh Xuân','Thường Tín');
/*!40000 ALTER TABLE `hokhau` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hokhaunhankhau`
--

DROP TABLE IF EXISTS `hokhaunhankhau`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hokhaunhankhau` (
  `idHoKhauNhanKhau` int NOT NULL AUTO_INCREMENT,
  `idHoKhau` int NOT NULL,
  `idNhanKhau` int NOT NULL,
  `quanHe` varchar(45) NOT NULL,
  PRIMARY KEY (`idHoKhauNhanKhau`),
  UNIQUE KEY `idHoKhau-NhanKhau_UNIQUE` (`idHoKhauNhanKhau`),
  UNIQUE KEY `idNhanKhau_UNIQUE` (`idNhanKhau`),
  KEY `fk-hokhaunhankhau-hokhau_idx` (`idHoKhau`),
  KEY `fk-hokhaunhankhau-nhankhau_idx` (`idNhanKhau`),
  CONSTRAINT `fk-hokhaunhankhau-hokhau` FOREIGN KEY (`idHoKhau`) REFERENCES `hokhau` (`idHoKhau`),
  CONSTRAINT `fk-hokhaunhankhau-nhankhau` FOREIGN KEY (`idNhanKhau`) REFERENCES `nhankhau` (`idNhanKhau`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hokhaunhankhau`
--

LOCK TABLES `hokhaunhankhau` WRITE;
/*!40000 ALTER TABLE `hokhaunhankhau` DISABLE KEYS */;
INSERT INTO `hokhaunhankhau` VALUES (2,2,13,'Chủ Hộ'),(3,2,12,'Vợ'),(4,2,9,'con');
/*!40000 ALTER TABLE `hokhaunhankhau` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lephi`
--

DROP TABLE IF EXISTS `lephi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lephi` (
  `idLePhi` int NOT NULL AUTO_INCREMENT,
  `ban` int NOT NULL,
  `ghe` int NOT NULL,
  `loa` int NOT NULL,
  `manHinh` int NOT NULL,
  `den` int NOT NULL,
  `ngay` int NOT NULL,
  `dai` int NOT NULL,
  PRIMARY KEY (`idLePhi`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lephi`
--

LOCK TABLES `lephi` WRITE;
/*!40000 ALTER TABLE `lephi` DISABLE KEYS */;
INSERT INTO `lephi` VALUES (1,50,25,100,1000,50,1,100);
/*!40000 ALTER TABLE `lephi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lichsu`
--

DROP TABLE IF EXISTS `lichsu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lichsu` (
  `idLichSu` int NOT NULL AUTO_INCREMENT,
  `thaoTac` varchar(45) NOT NULL,
  `thoiGian` timestamp NOT NULL,
  `idUser` int NOT NULL,
  `idNhanKhau` int DEFAULT NULL,
  `idHoKhau` int DEFAULT NULL,
  `idHoKhauNhanKhau` int DEFAULT NULL,
  `idTamTru` int DEFAULT NULL,
  `idTamVang` int DEFAULT NULL,
  `idKiemTra` int DEFAULT NULL,
  `idHoatDong` int DEFAULT NULL,
  PRIMARY KEY (`idLichSu`),
  UNIQUE KEY `idLichSu_UNIQUE` (`idLichSu`),
  KEY `fk-nhankhau_idx` (`idNhanKhau`),
  KEY `fk-lichsu-hokhau_idx` (`idHoKhau`),
  KEY `fk-lichsu-hokhaunhankhau_idx` (`idHoKhauNhanKhau`),
  KEY `idTamTru_idx` (`idTamTru`),
  KEY `idTamVang_idx` (`idTamVang`),
  KEY `fk-lichsu-kiemtra_idx` (`idKiemTra`),
  KEY `fk-lichsu-hoatdong_idx` (`idHoatDong`),
  CONSTRAINT `fk-lichsu-hoatdong` FOREIGN KEY (`idHoatDong`) REFERENCES `hoatdong` (`idHoatDong`),
  CONSTRAINT `fk-lichsu-hokhau` FOREIGN KEY (`idHoKhau`) REFERENCES `hokhau` (`idHoKhau`),
  CONSTRAINT `fk-lichsu-hokhaunhankhau` FOREIGN KEY (`idHoKhauNhanKhau`) REFERENCES `hokhaunhankhau` (`idHoKhauNhanKhau`),
  CONSTRAINT `fk-lichsu-kiemtra` FOREIGN KEY (`idKiemTra`) REFERENCES `nhavanhoa` (`idKiemTra`),
  CONSTRAINT `fk-lichsu-nhankhau` FOREIGN KEY (`idNhanKhau`) REFERENCES `nhankhau` (`idNhanKhau`),
  CONSTRAINT `fk-lichsu-tamtru` FOREIGN KEY (`idTamTru`) REFERENCES `tamtru` (`idTamTru`),
  CONSTRAINT `fk-lichsu-tamvang` FOREIGN KEY (`idTamVang`) REFERENCES `tamvang` (`idTamVang`)
) ENGINE=InnoDB AUTO_INCREMENT=209 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lichsu`
--

LOCK TABLES `lichsu` WRITE;
/*!40000 ALTER TABLE `lichsu` DISABLE KEYS */;
INSERT INTO `lichsu` VALUES (1,'Đăng Ký','2023-01-27 13:56:34',2,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,'Đăng Ký','2023-01-27 13:57:18',3,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,'Kiểm tra hiện trạng nhà văn hoá','2023-01-27 13:58:16',3,NULL,NULL,NULL,NULL,NULL,1,NULL),(7,'Đăng nhập','2023-01-27 14:05:37',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,'Đăng nhập','2023-01-27 14:07:47',3,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,'Đăng nhập','2023-01-27 14:08:14',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,'Đăng nhập','2023-01-27 14:08:29',2,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,'Đăng nhập','2023-01-27 14:08:49',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(16,'Đăng nhập','2023-01-27 14:11:29',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(18,'Đăng nhập','2023-01-27 14:13:10',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(20,'Đăng Ký','2023-01-27 14:14:46',4,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(21,'Đăng nhập','2023-01-27 14:14:47',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(22,'Đăng nhập','2023-01-27 14:14:57',4,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(24,'Đăng nhập','2023-01-27 14:15:31',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(26,'Đăng nhập','2023-01-27 14:20:55',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(27,'Đăng xuất','2023-01-27 14:20:58',3,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(28,'Đăng nhập','2023-01-27 14:21:11',2,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(29,'Đăng xuất','2023-01-27 14:21:13',2,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(30,'Đăng nhập','2023-01-27 14:21:24',3,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(31,'Đăng xuất','2023-01-27 14:21:26',3,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(32,'Đăng nhập','2023-01-27 14:21:33',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(33,'Đăng xuất','2023-01-27 14:21:36',3,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(35,'Đăng nhập','2023-01-27 14:23:06',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(36,'Đăng xuất','2023-01-27 14:23:11',3,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(37,'Đăng nhập','2023-01-27 14:23:19',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(38,'Đăng xuất','2023-01-27 14:23:22',3,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(42,'Đăng nhập','2023-01-27 14:23:53',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(44,'Đăng nhập','2023-01-27 14:26:33',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(45,'Đăng xuất','2023-01-27 14:26:42',5,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(46,'Đăng nhập','2023-01-27 14:28:52',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(48,'Đăng nhập','2023-01-27 14:29:58',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(49,'Đăng nhập','2023-01-27 14:32:57',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(50,'Đăng xuất','2023-01-27 14:33:00',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(51,'Đăng nhập','2023-01-27 14:33:24',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(52,'Đăng nhập','2023-01-28 08:13:58',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(56,'Đăng nhập','2023-01-28 08:15:46',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(62,'Xoá hoạt động sử dụng nhà văn hoá','2023-01-28 08:17:04',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(65,'Xoá hoạt động sử dụng nhà văn hoá','2023-01-28 08:17:10',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(69,'Xoá hoạt động sử dụng nhà văn hoá','2023-01-28 08:17:42',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(72,'Xoá hoạt động sử dụng nhà văn hoá','2023-01-28 08:17:53',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(73,'Đăng nhập','2023-01-28 08:18:06',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(77,'Xoá hoạt động sử dụng nhà văn hoá','2023-01-28 08:18:48',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(78,'Đăng Ký','2023-01-28 08:25:18',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(79,'Đăng nhập','2023-01-28 08:25:20',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(80,'Đăng xuất','2023-01-28 08:26:26',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(81,'Đăng nhập','2023-01-28 08:28:24',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(82,'Đăng nhập','2023-01-31 08:54:54',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(83,'Đăng nhập','2023-01-31 08:55:56',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(84,'Đăng nhập','2023-01-31 08:56:16',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(85,'Đăng nhập','2023-01-31 08:57:14',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(88,'Đăng nhập','2023-01-31 09:16:31',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(91,'Đăng nhập','2023-01-31 09:22:06',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(92,'Đăng nhập','2023-01-31 09:28:31',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(93,'Đăng nhập','2023-01-31 09:28:58',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(94,'Đăng nhập','2023-01-31 11:16:24',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95,'Đăng nhập','2023-01-31 11:17:39',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(96,'Đăng nhập','2023-01-31 11:17:59',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(97,'Đăng nhập','2023-01-31 11:18:35',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(98,'Đăng nhập','2023-01-31 11:19:22',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99,'Đăng nhập','2023-01-31 11:20:40',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(100,'Đăng nhập','2023-01-31 11:23:18',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(101,'Đăng nhập','2023-01-31 11:25:45',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(102,'Đăng nhập','2023-01-31 11:26:16',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(103,'Đăng nhập','2023-01-31 11:27:20',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(104,'Đăng nhập','2023-01-31 11:29:33',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(106,'Xoá nhân khẩu','2023-01-31 11:30:11',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(108,'Xoá nhân khẩu','2023-01-31 11:30:17',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(110,'Xoá nhân khẩu','2023-01-31 11:30:36',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(112,'Xoá nhân khẩu','2023-01-31 11:30:39',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(114,'Xoá nhân khẩu','2023-01-31 11:30:42',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(116,'Xoá nhân khẩu','2023-01-31 11:30:45',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(119,'Xoá hộ khẩu','2023-01-31 11:30:48',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(120,'Xoá nhân khẩu','2023-01-31 11:30:48',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(121,'Thêm nhân khẩu','2023-01-31 11:33:14',1,8,NULL,NULL,NULL,NULL,NULL,NULL),(122,'Thêm nhân khẩu','2023-01-31 11:34:07',1,9,NULL,NULL,NULL,NULL,NULL,NULL),(123,'Thêm nhân khẩu','2023-01-31 11:34:58',1,10,NULL,NULL,NULL,NULL,NULL,NULL),(124,'Thêm nhân khẩu','2023-01-31 11:35:34',1,11,NULL,NULL,NULL,NULL,NULL,NULL),(125,'Đăng nhập','2023-01-31 11:38:52',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(126,'Thêm nhân khẩu','2023-01-31 11:40:47',1,12,NULL,NULL,NULL,NULL,NULL,NULL),(127,'Thêm nhân khẩu','2023-01-31 12:05:19',1,13,NULL,NULL,NULL,NULL,NULL,NULL),(128,'Thêm hộ khẩu','2023-01-31 12:06:27',1,NULL,2,NULL,NULL,NULL,NULL,NULL),(129,'Xem hộ khẩu','2023-01-31 12:06:49',1,NULL,2,NULL,NULL,NULL,NULL,NULL),(130,'Xem nhân khẩu','2023-01-31 12:06:49',1,13,NULL,NULL,NULL,NULL,NULL,NULL),(131,'Đăng nhập','2023-01-31 12:08:12',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(132,'Xem hộ khẩu','2023-01-31 12:08:41',1,NULL,2,NULL,NULL,NULL,NULL,NULL),(133,'Xem nhân khẩu','2023-01-31 12:08:41',1,13,NULL,NULL,NULL,NULL,NULL,NULL),(134,'Xem hộ khẩu','2023-01-31 12:08:42',1,NULL,2,NULL,NULL,NULL,NULL,NULL),(135,'Thêm hộ khẩu nhân khẩu','2023-01-31 12:08:47',1,12,2,NULL,NULL,NULL,NULL,NULL),(136,'Xem hộ khẩu','2023-01-31 12:08:47',1,NULL,2,NULL,NULL,NULL,NULL,NULL),(137,'Xem hộ khẩu','2023-01-31 12:08:47',1,NULL,2,NULL,NULL,NULL,NULL,NULL),(138,'Xem nhân khẩu','2023-01-31 12:08:47',1,13,NULL,NULL,NULL,NULL,NULL,NULL),(139,'Xem hộ khẩu','2023-01-31 12:08:51',1,NULL,2,NULL,NULL,NULL,NULL,NULL),(140,'Thêm hộ khẩu nhân khẩu','2023-01-31 12:08:58',1,9,2,NULL,NULL,NULL,NULL,NULL),(141,'Xem hộ khẩu','2023-01-31 12:08:58',1,NULL,2,NULL,NULL,NULL,NULL,NULL),(142,'Xem hộ khẩu','2023-01-31 12:08:58',1,NULL,2,NULL,NULL,NULL,NULL,NULL),(143,'Xem nhân khẩu','2023-01-31 12:08:58',1,13,NULL,NULL,NULL,NULL,NULL,NULL),(144,'Đăng nhập','2023-01-31 12:12:32',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(145,'Thêm nhân khẩu','2023-01-31 12:13:40',1,14,NULL,NULL,NULL,NULL,NULL,NULL),(146,'Đăng nhập','2023-01-31 13:28:27',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(147,'Thêm tạm trú','2023-01-31 13:29:20',1,NULL,NULL,NULL,1,NULL,NULL,NULL),(148,'Đăng nhập','2023-01-31 16:24:49',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(149,'Xem hộ khẩu','2023-01-31 16:24:55',1,NULL,2,NULL,NULL,NULL,NULL,NULL),(150,'Xem nhân khẩu','2023-01-31 16:24:55',1,13,NULL,NULL,NULL,NULL,NULL,NULL),(151,'Đăng xuất','2023-01-31 16:25:03',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(152,'Đăng nhập','2023-01-31 16:25:05',2,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(153,'Xem hộ khẩu','2023-01-31 16:25:08',2,NULL,2,NULL,NULL,NULL,NULL,NULL),(154,'Xem nhân khẩu','2023-01-31 16:25:08',2,13,NULL,NULL,NULL,NULL,NULL,NULL),(155,'Đăng xuất','2023-01-31 16:25:31',2,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(156,'Đăng nhập','2023-01-31 16:25:32',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(157,'Xem hộ khẩu','2023-01-31 16:25:35',1,NULL,2,NULL,NULL,NULL,NULL,NULL),(158,'Xem nhân khẩu','2023-01-31 16:25:35',1,13,NULL,NULL,NULL,NULL,NULL,NULL),(159,'Đăng nhập','2023-02-01 03:26:07',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(160,'Xem chi tiết hiện trạng nhà văn hoá','2023-02-01 03:26:19',1,NULL,NULL,NULL,NULL,NULL,1,NULL),(161,'Đăng nhập','2023-02-01 03:26:44',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(162,'Xem chi tiết hiện trạng nhà văn hoá','2023-02-01 03:26:50',1,NULL,NULL,NULL,NULL,NULL,1,NULL),(163,'Đăng nhập','2023-02-01 05:13:43',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(164,'Đăng nhập','2023-02-01 05:17:24',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(165,'Đăng nhập','2023-02-01 05:18:22',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(168,'Xem nhân khẩu','2023-02-01 05:42:04',1,8,NULL,NULL,NULL,NULL,NULL,NULL),(170,'Xem nhân khẩu','2023-02-01 05:42:38',1,8,NULL,NULL,NULL,NULL,NULL,NULL),(171,'Đăng nhập','2023-02-01 05:45:13',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(173,'Xem nhân khẩu','2023-02-01 05:45:21',1,8,NULL,NULL,NULL,NULL,NULL,NULL),(174,'Xoá hoạt động sử dụng nhà văn hoá','2023-02-01 05:45:23',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(175,'Đăng ký sử dụng nhà văn hoá','2023-02-01 05:45:42',1,NULL,NULL,NULL,NULL,NULL,NULL,7),(176,'Đăng ký sử dụng nhà văn hoá','2023-02-01 05:46:45',1,NULL,NULL,NULL,NULL,NULL,NULL,8),(177,'Đăng nhập','2023-02-01 06:08:01',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(178,'Đăng nhập','2023-02-01 06:10:08',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(179,'Đăng nhập','2023-02-01 06:10:45',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(180,'Đăng nhập','2023-02-01 06:11:23',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(181,'Đăng nhập','2023-02-01 06:12:46',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(182,'Đăng nhập','2023-02-01 06:13:38',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(183,'Đăng nhập','2023-02-01 06:14:44',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(184,'Đăng nhập','2023-02-01 06:15:53',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(185,'Đăng nhập','2023-02-01 06:17:27',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(186,'Đăng nhập','2023-02-01 06:21:06',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(187,'Đăng nhập','2023-02-01 06:22:53',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(188,'Đăng nhập','2023-02-01 06:23:56',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(189,'Đăng nhập','2023-02-01 06:26:43',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(190,'Xem nhân khẩu','2023-02-01 06:26:52',1,8,NULL,NULL,NULL,NULL,NULL,NULL),(191,'Xem chi tiết hiện trạng nhà văn hoá','2023-02-01 06:27:04',1,NULL,NULL,NULL,NULL,NULL,1,NULL),(192,'Đăng nhập','2023-02-01 06:28:19',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(193,'Xem chi tiết hoạt động sử dụng nhà văn hoá','2023-02-01 06:28:23',1,NULL,NULL,NULL,NULL,NULL,NULL,7),(194,'Xem nhân khẩu','2023-02-01 06:28:23',1,8,NULL,NULL,NULL,NULL,NULL,NULL),(195,'Xem chi tiết hoạt động sử dụng nhà văn hoá','2023-02-01 06:28:27',1,NULL,NULL,NULL,NULL,NULL,NULL,7),(196,'Xem nhân khẩu','2023-02-01 06:28:27',1,8,NULL,NULL,NULL,NULL,NULL,NULL),(197,'Xem chi tiết hoạt động sử dụng nhà văn hoá','2023-02-01 06:28:32',1,NULL,NULL,NULL,NULL,NULL,NULL,7),(198,'Xem nhân khẩu','2023-02-01 06:28:32',1,8,NULL,NULL,NULL,NULL,NULL,NULL),(199,'Xem chi tiết hoạt động sử dụng nhà văn hoá','2023-02-01 06:28:37',1,NULL,NULL,NULL,NULL,NULL,NULL,7),(200,'Xem nhân khẩu','2023-02-01 06:28:37',1,8,NULL,NULL,NULL,NULL,NULL,NULL),(201,'Đăng xuất','2023-02-01 06:28:40',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(202,'Đăng nhập','2023-02-01 06:28:41',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(203,'Xem chi tiết hoạt động sử dụng nhà văn hoá','2023-02-01 06:28:47',1,NULL,NULL,NULL,NULL,NULL,NULL,8),(204,'Xem nhân khẩu','2023-02-01 06:28:47',1,14,NULL,NULL,NULL,NULL,NULL,NULL),(205,'Đăng nhập','2023-02-01 06:30:28',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(206,'Đăng nhập','2023-02-01 06:32:30',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(207,'Xem hộ khẩu','2023-02-01 06:32:36',1,NULL,2,NULL,NULL,NULL,NULL,NULL),(208,'Xem nhân khẩu','2023-02-01 06:32:36',1,13,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `lichsu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhankhau`
--

DROP TABLE IF EXISTS `nhankhau`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhankhau` (
  `idNhanKhau` int NOT NULL AUTO_INCREMENT,
  `hoTen` varchar(45) NOT NULL,
  `biDanh` varchar(45) DEFAULT NULL,
  `ngaySinh` date NOT NULL,
  `gioiTinh` varchar(45) NOT NULL,
  `cccd` varchar(45) DEFAULT NULL,
  `ngayCap` datetime DEFAULT NULL,
  `noiCap` varchar(45) DEFAULT NULL,
  `nguyenQuan` varchar(45) DEFAULT NULL,
  `danToc` varchar(45) DEFAULT NULL,
  `noiThuongTru` varchar(200) DEFAULT NULL,
  `ngayDangKyThuongTru` datetime DEFAULT NULL,
  `trinhDoHocVan` varchar(45) DEFAULT NULL,
  `trinhDoNgoaiNgu` varchar(45) DEFAULT NULL,
  `ngheNghiep` varchar(45) DEFAULT NULL,
  `noiLamViec` varchar(45) DEFAULT NULL,
  `tonGiao` varchar(45) DEFAULT NULL,
  `quocTich` varchar(45) DEFAULT NULL,
  `trinhDoChuyenMon` varchar(45) DEFAULT NULL,
  `ghiChu` varchar(45) DEFAULT NULL,
  `trangThai` varchar(45) NOT NULL,
  PRIMARY KEY (`idNhanKhau`),
  UNIQUE KEY `idNhanKhau_UNIQUE` (`idNhanKhau`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhankhau`
--

LOCK TABLES `nhankhau` WRITE;
/*!40000 ALTER TABLE `nhankhau` DISABLE KEYS */;
INSERT INTO `nhankhau` VALUES (8,'Vũ Hữu Nam Anh','Nam Anh','2002-02-12','NAM','034202002758','2017-03-02 00:00:00','Thái Bình','Thái Bình','Kinh','Thái Bình','2002-02-12 00:00:00','Không','Không','Không','Không','Không','Việt Nam','Không','Không','Vô gia cư'),(9,'Đào Đức Duy','Đậu Đậu','2020-12-15','NAM','',NULL,'','','','288 - Nguyễn Xiển - Thanh Xuân - Thường Tín','2023-01-31 19:08:58','','','','','','','','','Có Hộ Khẩu'),(10,'Bùi Minh Kiên','','2002-12-24','NAM','',NULL,'','','','Thái Bình','2002-12-24 00:00:00','','','','','','','','','Tạm Trú'),(11,'Nguyễn Thuỳ Dương','','2002-04-26','NỮ','',NULL,'','','','Phú Thọ','2002-04-26 00:00:00','','','','','','','','','Vô gia cư'),(12,'Vũ Thị Mai Hương','','1994-11-13','NỮ','',NULL,'','','','288 - Nguyễn Xiển - Thanh Xuân - Thường Tín','2023-01-31 19:08:47','','','','','','','','','Có Hộ Khẩu'),(13,'Đào Đức Huy','','1991-12-17','NAM','',NULL,'','','','288 - Nguyễn Xiển - Thanh Xuân - Thường Tín','2023-01-31 19:06:27','','','','','','','','','Có Hộ Khẩu'),(14,'Nguyễn Đức Minh','','2002-12-01','NAM','',NULL,'','','','Hải Dương','2002-12-01 00:00:00','','','','','','','','','Vô gia cư');
/*!40000 ALTER TABLE `nhankhau` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhavanhoa`
--

DROP TABLE IF EXISTS `nhavanhoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhavanhoa` (
  `idKiemTra` int NOT NULL AUTO_INCREMENT,
  `idUser` int NOT NULL,
  `ngayKiemTra` datetime NOT NULL,
  `soLuongBan` int DEFAULT NULL,
  `hienTrangBan` varchar(45) DEFAULT NULL,
  `soLuongGhe` int DEFAULT NULL,
  `hienTrangGhe` varchar(45) DEFAULT NULL,
  `soLuongLoa` int DEFAULT NULL,
  `hienTrangLoa` varchar(45) DEFAULT NULL,
  `soLuongDai` int DEFAULT NULL,
  `hienTrangDai` varchar(45) DEFAULT NULL,
  `soLuongManHinh` int DEFAULT NULL,
  `hienTrangManHinh` varchar(45) DEFAULT NULL,
  `soLuongDen` int DEFAULT NULL,
  `hienTrangDen` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idKiemTra`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhavanhoa`
--

LOCK TABLES `nhavanhoa` WRITE;
/*!40000 ALTER TABLE `nhavanhoa` DISABLE KEYS */;
INSERT INTO `nhavanhoa` VALUES (1,3,'2023-01-27 20:58:16',123,'123',123,'123',123,'123',123,'123',123,'123',123,'123'),(2,5,'2023-01-27 21:23:42',22,'22',22,'22',22,'22',22,'22',22,'22',22,'22');
/*!40000 ALTER TABLE `nhavanhoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tamtru`
--

DROP TABLE IF EXISTS `tamtru`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tamtru` (
  `idTamTru` int NOT NULL AUTO_INCREMENT,
  `idNhanKhau` int NOT NULL,
  `noiTamTru` varchar(45) NOT NULL,
  `lyDo` varchar(45) NOT NULL,
  `ngayHieuLuc` datetime NOT NULL,
  `ngayHetHieuLuc` datetime NOT NULL,
  PRIMARY KEY (`idTamTru`),
  UNIQUE KEY `idNhanKhau_UNIQUE` (`idNhanKhau`),
  UNIQUE KEY `idTamTru_UNIQUE` (`idTamTru`),
  KEY `idNhanKhau_TamVang_idx` (`idNhanKhau`),
  CONSTRAINT `idNhanKhau_TamTru` FOREIGN KEY (`idNhanKhau`) REFERENCES `nhankhau` (`idNhanKhau`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tamtru`
--

LOCK TABLES `tamtru` WRITE;
/*!40000 ALTER TABLE `tamtru` DISABLE KEYS */;
INSERT INTO `tamtru` VALUES (1,10,'Hà Nội','Đi học','2023-01-01 00:00:00','2023-01-10 00:00:00');
/*!40000 ALTER TABLE `tamtru` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tamvang`
--

DROP TABLE IF EXISTS `tamvang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tamvang` (
  `idTamVang` int NOT NULL AUTO_INCREMENT,
  `idNhanKhau` int NOT NULL,
  `noiTamTru` varchar(45) NOT NULL,
  `lyDo` varchar(45) NOT NULL,
  `ngayHieuLuc` datetime NOT NULL,
  `ngayHetHieuLuc` datetime NOT NULL,
  PRIMARY KEY (`idTamVang`),
  UNIQUE KEY `idNhanKhau_UNIQUE` (`idNhanKhau`),
  UNIQUE KEY `idTamVang_UNIQUE` (`idTamVang`),
  CONSTRAINT `idNhanKhau_TamVang` FOREIGN KEY (`idNhanKhau`) REFERENCES `nhankhau` (`idNhanKhau`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tamvang`
--

LOCK TABLES `tamvang` WRITE;
/*!40000 ALTER TABLE `tamvang` DISABLE KEYS */;
/*!40000 ALTER TABLE `tamvang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `idUser` int NOT NULL AUTO_INCREMENT,
  `taiKhoan` varchar(45) NOT NULL,
  `matKhau` varchar(45) NOT NULL,
  `tenNguoiDung` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `role` varchar(45) NOT NULL,
  `capQuyen` varchar(45) NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `idUser_UNIQUE` (`idUser`),
  UNIQUE KEY `taiKhoan_UNIQUE` (`taiKhoan`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'a','a','a','Tổ Trưởng','Đã cấp quyền'),(2,'b','b','b','Nhân Viên','Đã cấp quyền'),(3,'n','n','n','Cán Bộ Quản Lý','Đã cấp quyền'),(4,'d','d','d','Nhân Viên','Đã cấp quyền');
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

-- Dump completed on 2023-02-01 13:33:25
