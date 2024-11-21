-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: proyecto
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `administradores`
--

DROP TABLE IF EXISTS `administradores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administradores` (
  `id_administador` int NOT NULL AUTO_INCREMENT,
  `contrasena` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id_administador`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administradores`
--

LOCK TABLES `administradores` WRITE;
/*!40000 ALTER TABLE `administradores` DISABLE KEYS */;
INSERT INTO `administradores` VALUES (1,'contrasena','admi@parkingcontrol.com','Administrador');
/*!40000 ALTER TABLE `administradores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facturas`
--

DROP TABLE IF EXISTS `facturas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facturas` (
  `id_factura` int NOT NULL AUTO_INCREMENT,
  `tiempo_estacionamiento` int NOT NULL,
  `valor` decimal(38,2) NOT NULL,
  `placa` varchar(6) NOT NULL,
  `fecha_facturacion` datetime(6) NOT NULL,
  `fecha_ingreso` datetime(6) NOT NULL,
  `fecha_salida` datetime(6) DEFAULT NULL,
  `forma_pago` enum('EFECTIVO','TARJETA') DEFAULT NULL,
  `tipo_plan` enum('ANUAL','DIARIO','DURACION','FRACCION','MENSUAL') NOT NULL,
  `tipo_vehiculo` enum('CARRO','MOTO') NOT NULL,
  PRIMARY KEY (`id_factura`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facturas`
--

LOCK TABLES `facturas` WRITE;
/*!40000 ALTER TABLE `facturas` DISABLE KEYS */;
INSERT INTO `facturas` VALUES (1,180,25000.00,'XYZ789','2024-09-01 12:00:00.000000','2024-09-01 09:00:00.000000','2024-09-01 12:00:00.000000','EFECTIVO','DURACION','CARRO'),(2,120,20000.00,'ABC02B','2024-09-02 11:30:00.000000','2024-09-02 09:30:00.000000','2024-09-02 11:30:00.000000','TARJETA','DURACION','MOTO'),(3,240,35000.00,'HIJ890','2024-09-03 13:00:00.000000','2024-09-03 09:40:00.000000','2024-09-03 13:00:00.000000','EFECTIVO','DURACION','CARRO'),(4,150,28000.00,'ABC04B','2024-09-04 10:00:00.000000','2024-09-04 07:30:00.000000','2024-09-04 10:00:00.000000','TARJETA','DURACION','MOTO'),(5,90,18000.00,'ABC123','2024-09-05 09:00:00.000000','2024-09-05 07:30:00.000000','2024-09-05 09:00:00.000000','EFECTIVO','DURACION','CARRO'),(6,210,32000.00,'ABC02B','2024-09-06 14:00:00.000000','2024-09-06 10:30:00.000000','2024-09-06 14:00:00.000000','TARJETA','DURACION','MOTO'),(7,300,40000.00,'ABC007','2024-09-07 15:00:00.000000','2024-09-07 10:00:00.000000','2024-09-07 15:00:00.000000','EFECTIVO','DURACION','CARRO');
/*!40000 ALTER TABLE `facturas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parqueaderos`
--

DROP TABLE IF EXISTS `parqueaderos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parqueaderos` (
  `capacidad_carros` int NOT NULL,
  `capacidad_motos` int NOT NULL,
  `id_parqueadero` int NOT NULL AUTO_INCREMENT,
  `telefono` varchar(15) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  PRIMARY KEY (`id_parqueadero`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parqueaderos`
--

LOCK TABLES `parqueaderos` WRITE;
/*!40000 ALTER TABLE `parqueaderos` DISABLE KEYS */;
INSERT INTO `parqueaderos` VALUES (14,30,1,'320 2354322','ParkingControl','Carrera 21 número 05 - 12 Riofrío');
/*!40000 ALTER TABLE `parqueaderos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `planes_pago`
--

DROP TABLE IF EXISTS `planes_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `planes_pago` (
  `duracion_dias` int NOT NULL,
  `id_plan_pago` int NOT NULL AUTO_INCREMENT,
  `placa` varchar(6) NOT NULL,
  `fecha_fin` datetime(6) NOT NULL,
  `fecha_inicio` datetime(6) NOT NULL,
  `tipo_plan` enum('ANUAL','DIARIO','DURACION','FRACCION','MENSUAL') NOT NULL,
  `tipo_vehiculo` enum('CARRO','MOTO') NOT NULL,
  PRIMARY KEY (`id_plan_pago`),
  UNIQUE KEY `UKnc65w79g7pqhr9qy5mmd7x7pu` (`placa`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `planes_pago`
--

LOCK TABLES `planes_pago` WRITE;
/*!40000 ALTER TABLE `planes_pago` DISABLE KEYS */;
INSERT INTO `planes_pago` VALUES (30,1,'ABC123','2024-10-25 17:09:52.000000','2024-09-25 17:09:52.000000','MENSUAL','CARRO'),(30,2,'XYZ789','2024-10-25 17:09:52.000000','2024-09-25 17:09:52.000000','MENSUAL','MOTO'),(365,3,'LMN456','2025-09-25 17:09:52.000000','2024-09-25 17:09:52.000000','ANUAL','CARRO'),(30,4,'LOV04U','2024-11-04 11:14:58.776544','2024-10-05 11:14:58.776544','MENSUAL','MOTO'),(30,5,'GPT122','2024-11-04 11:53:44.817340','2024-10-05 11:53:44.817340','MENSUAL','CARRO'),(365,6,'TAT04I','2025-10-05 12:30:06.364535','2024-10-05 12:30:06.364535','ANUAL','MOTO');
/*!40000 ALTER TABLE `planes_pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `puestos`
--

DROP TABLE IF EXISTS `puestos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `puestos` (
  `esta_ocupado` bit(1) NOT NULL,
  `id_puesto` int NOT NULL AUTO_INCREMENT,
  `parqueadero_id` int NOT NULL,
  `numero_puesto` varchar(5) NOT NULL,
  `tipo_vehiculo` enum('CARRO','MOTO') NOT NULL,
  PRIMARY KEY (`id_puesto`),
  UNIQUE KEY `UKaagat4jywv59w97rjq86y4a91` (`numero_puesto`),
  KEY `FKc3otsfwcikuvkfvwdsw9bylpp` (`parqueadero_id`),
  CONSTRAINT `FKc3otsfwcikuvkfvwdsw9bylpp` FOREIGN KEY (`parqueadero_id`) REFERENCES `parqueaderos` (`id_parqueadero`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `puestos`
--

LOCK TABLES `puestos` WRITE;
/*!40000 ALTER TABLE `puestos` DISABLE KEYS */;
INSERT INTO `puestos` VALUES (_binary '',1,1,'C1','CARRO'),(_binary '',2,1,'C2','CARRO'),(_binary '\0',3,1,'C3','CARRO'),(_binary '\0',4,1,'C4','CARRO'),(_binary '\0',5,1,'C5','CARRO'),(_binary '\0',6,1,'C6','CARRO'),(_binary '',7,1,'C7','CARRO'),(_binary '\0',8,1,'C8','CARRO'),(_binary '\0',9,1,'C9','CARRO'),(_binary '\0',10,1,'C10','CARRO'),(_binary '\0',11,1,'C11','CARRO'),(_binary '\0',12,1,'C12','CARRO'),(_binary '\0',13,1,'C13','CARRO'),(_binary '\0',14,1,'C14','CARRO'),(_binary '',15,1,'M1','MOTO'),(_binary '\0',16,1,'M2','MOTO'),(_binary '\0',17,1,'M3','MOTO'),(_binary '\0',18,1,'M4','MOTO'),(_binary '\0',19,1,'M5','MOTO'),(_binary '\0',20,1,'M6','MOTO'),(_binary '\0',21,1,'M7','MOTO'),(_binary '\0',22,1,'M8','MOTO'),(_binary '\0',23,1,'M9','MOTO'),(_binary '\0',24,1,'M10','MOTO'),(_binary '\0',25,1,'M11','MOTO'),(_binary '\0',26,1,'M12','MOTO'),(_binary '\0',27,1,'M13','MOTO'),(_binary '\0',28,1,'M14','MOTO'),(_binary '\0',29,1,'M15','MOTO'),(_binary '\0',30,1,'M16','MOTO'),(_binary '\0',31,1,'M17','MOTO'),(_binary '\0',32,1,'M18','MOTO'),(_binary '\0',33,1,'M19','MOTO'),(_binary '\0',34,1,'M20','MOTO'),(_binary '\0',35,1,'M21','MOTO'),(_binary '\0',36,1,'M22','MOTO'),(_binary '\0',37,1,'M23','MOTO'),(_binary '\0',38,1,'M24','MOTO'),(_binary '\0',39,1,'M25','MOTO'),(_binary '\0',40,1,'M26','MOTO'),(_binary '\0',41,1,'M27','MOTO'),(_binary '\0',42,1,'M28','MOTO'),(_binary '\0',43,1,'M29','MOTO'),(_binary '\0',44,1,'M30','MOTO');
/*!40000 ALTER TABLE `puestos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarifas`
--

DROP TABLE IF EXISTS `tarifas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarifas` (
  `id_tarifa` int NOT NULL AUTO_INCREMENT,
  `tarifa_anual` decimal(38,2) NOT NULL,
  `tarifa_dia` decimal(38,2) NOT NULL,
  `tarifa_fraccion` decimal(38,2) NOT NULL,
  `tarifa_mes` decimal(38,2) NOT NULL,
  `tipo_vehiculo` enum('CARRO','MOTO') NOT NULL,
  PRIMARY KEY (`id_tarifa`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarifas`
--

LOCK TABLES `tarifas` WRITE;
/*!40000 ALTER TABLE `tarifas` DISABLE KEYS */;
INSERT INTO `tarifas` VALUES (1,2600000.00,3000.00,3000.00,350000.00,'CARRO'),(2,900000.00,1000.00,1000.00,100000.00,'MOTO');
/*!40000 ALTER TABLE `tarifas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehiculos`
--

DROP TABLE IF EXISTS `vehiculos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehiculos` (
  `id_vehiculo` int NOT NULL AUTO_INCREMENT,
  `placa` varchar(6) NOT NULL,
  `fecha_ingreso` datetime(6) NOT NULL,
  `color` varchar(10) NOT NULL,
  `marca_vehiculo` varchar(15) NOT NULL,
  `tipo_vehiculo` enum('CARRO','MOTO') NOT NULL,
  `numero_puesto` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id_vehiculo`),
  UNIQUE KEY `UKa8w6omovfa10q8eyjalqas391` (`placa`),
  UNIQUE KEY `UKf0i4wlaemy15mlt4wt4or6btv` (`numero_puesto`),
  CONSTRAINT `FK6mus08nn4senq6svl39wku55l` FOREIGN KEY (`numero_puesto`) REFERENCES `puestos` (`numero_puesto`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiculos`
--

LOCK TABLES `vehiculos` WRITE;
/*!40000 ALTER TABLE `vehiculos` DISABLE KEYS */;
INSERT INTO `vehiculos` VALUES (11,'IEM32B','2024-10-05 18:23:50.000000','azul','bajaj','MOTO','M1'),(12,'HGY068','2024-10-05 18:31:43.140000','negro','renault','CARRO','C1'),(13,'ABR456','2024-10-05 13:57:03.000000','beige','mazda','CARRO','C2'),(14,'ABC123','2024-10-07 14:17:50.000000','dorado','bmw','CARRO','C7');
/*!40000 ALTER TABLE `vehiculos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'proyecto'
--

--
-- Dumping routines for database 'proyecto'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-07 15:58:04
