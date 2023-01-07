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
  KEY `fk-nhankhau_idx` (`idNhanKhau`),
  CONSTRAINT `fk-hokhau-nhankhau` FOREIGN KEY (`idNhanKhau`) REFERENCES `nhankhau` (`idNhanKhau`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hokhau`
--

LOCK TABLES `hokhau` WRITE;
/*!40000 ALTER TABLE `hokhau` DISABLE KEYS */;
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
  KEY `fk-hokhaunhankhau-hokhau_idx` (`idHoKhau`),
  KEY `fk-hokhaunhankhau-nhankhau_idx` (`idNhanKhau`),
  CONSTRAINT `fk-hokhaunhankhau-hokhau` FOREIGN KEY (`idHoKhau`) REFERENCES `hokhau` (`idHoKhau`),
  CONSTRAINT `fk-hokhaunhankhau-nhankhau` FOREIGN KEY (`idNhanKhau`) REFERENCES `nhankhau` (`idNhanKhau`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hokhaunhankhau`
--

LOCK TABLES `hokhaunhankhau` WRITE;
/*!40000 ALTER TABLE `hokhaunhankhau` DISABLE KEYS */;
/*!40000 ALTER TABLE `hokhaunhankhau` ENABLE KEYS */;
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
  PRIMARY KEY (`idLichSu`),
  UNIQUE KEY `idLichSu_UNIQUE` (`idLichSu`),
  KEY `fk-user_idx` (`idUser`),
  KEY `fk-nhankhau_idx` (`idNhanKhau`),
  KEY `fk-lichsu-hokhau_idx` (`idHoKhau`),
  KEY `fk-lichsu-hokhaunhankhau_idx` (`idHoKhauNhanKhau`),
  CONSTRAINT `fk-lichsu-hokhau` FOREIGN KEY (`idHoKhau`) REFERENCES `hokhau` (`idHoKhau`),
  CONSTRAINT `fk-lichsu-hokhaunhankhau` FOREIGN KEY (`idHoKhauNhanKhau`) REFERENCES `hokhaunhankhau` (`idHoKhauNhanKhau`),
  CONSTRAINT `fk-lichsu-nhankhau` FOREIGN KEY (`idNhanKhau`) REFERENCES `nhankhau` (`idNhanKhau`),
  CONSTRAINT `fk-lichsu-user` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lichsu`
--

LOCK TABLES `lichsu` WRITE;
/*!40000 ALTER TABLE `lichsu` DISABLE KEYS */;
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
  PRIMARY KEY (`idNhanKhau`),
  UNIQUE KEY `idNhanKhau_UNIQUE` (`idNhanKhau`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhankhau`
--

LOCK TABLES `nhankhau` WRITE;
/*!40000 ALTER TABLE `nhankhau` DISABLE KEYS */;
/*!40000 ALTER TABLE `nhankhau` ENABLE KEYS */;
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
  KEY `idNhanKhau_TamVang_idx` (`idNhanKhau`),
  CONSTRAINT `idNhanKhau_TamTru` FOREIGN KEY (`idNhanKhau`) REFERENCES `nhankhau` (`idNhanKhau`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tamtru`
--

LOCK TABLES `tamtru` WRITE;
/*!40000 ALTER TABLE `tamtru` DISABLE KEYS */;
/*!40000 ALTER TABLE `tamtru` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tamtrutamvang`
--

DROP TABLE IF EXISTS `tamtrutamvang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tamtrutamvang` (
  `idTamTruTamVang` int NOT NULL AUTO_INCREMENT,
  `idNhanKhau` int DEFAULT NULL,
  PRIMARY KEY (`idTamTruTamVang`),
  UNIQUE KEY `idNhanKhau_UNIQUE` (`idNhanKhau`),
  CONSTRAINT `idNhanKhau` FOREIGN KEY (`idNhanKhau`) REFERENCES `nhankhau` (`idNhanKhau`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tamtrutamvang`
--

LOCK TABLES `tamtrutamvang` WRITE;
/*!40000 ALTER TABLE `tamtrutamvang` DISABLE KEYS */;
/*!40000 ALTER TABLE `tamtrutamvang` ENABLE KEYS */;
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
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `idUser_UNIQUE` (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
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

-- Dump completed on 2023-01-05 21:35:34
