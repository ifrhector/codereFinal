-- MySQL dump 10.13  Distrib 8.0.34, for macos13 (x86_64)
--
-- Host: localhost    Database: sales
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
-- Table structure for table `cat_movement_type`
--

DROP TABLE IF EXISTS `cat_movement_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cat_movement_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cat_movement_type`
--

LOCK TABLES `cat_movement_type` WRITE;
/*!40000 ALTER TABLE `cat_movement_type` DISABLE KEYS */;
INSERT INTO `cat_movement_type` VALUES (1,'Agregar productos'),(2,'Modificar productos'),(3,'Cancelar productos'),(4,'Cancelar orden');
/*!40000 ALTER TABLE `cat_movement_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cat_orders_status`
--

DROP TABLE IF EXISTS `cat_orders_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cat_orders_status` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cat_orders_status`
--

LOCK TABLES `cat_orders_status` WRITE;
/*!40000 ALTER TABLE `cat_orders_status` DISABLE KEYS */;
INSERT INTO `cat_orders_status` VALUES (1,'Abierto'),(2,'Cancelado'),(3,'Cerrado'),(4,'En espera'),(5,'Entregado / Cobrado'),(6,'Entregado / No cobrado');
/*!40000 ALTER TABLE `cat_orders_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_orders`
--

DROP TABLE IF EXISTS `client_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client_orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_order` datetime(6) DEFAULT NULL,
  `order_identifier` varchar(255) DEFAULT NULL,
  `total` double NOT NULL,
  `client_id` bigint DEFAULT NULL,
  `order_status_id` bigint DEFAULT NULL,
  `date_canceled` datetime(6) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `date_updated` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsvtx3xcntbrypx5rib5qq0ed6` (`client_id`),
  KEY `FKrvhq90lbraqv1i8ovegk3wi8v` (`order_status_id`),
  CONSTRAINT `FKrvhq90lbraqv1i8ovegk3wi8v` FOREIGN KEY (`order_status_id`) REFERENCES `cat_orders_status` (`id`),
  CONSTRAINT `FKsvtx3xcntbrypx5rib5qq0ed6` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_orders`
--

LOCK TABLES `client_orders` WRITE;
/*!40000 ALTER TABLE `client_orders` DISABLE KEYS */;
INSERT INTO `client_orders` VALUES (1,'2024-04-17 23:31:00.000000','3eDumpln81',2287.8,1,1,NULL,NULL,'2024-04-18 03:54:40.000000'),(2,'2024-04-17 23:40:15.000000','wbTTVtNxyG',0,3,2,'2024-04-18 00:27:15.000000','Se cancela por pruebas','2024-04-18 19:05:04.000000'),(3,'2024-04-18 00:29:48.000000','DenXYvXE6L',14131.96,3,1,NULL,'Pedido para entregar el sábado 20 de abril de 2024','2024-05-06 20:39:49.000000'),(4,'2024-05-06 18:00:20.000000','DIwPB2P9ra',0,2,2,'2024-05-06 18:15:36.000000','Swagger prueba','2024-05-06 18:15:36.000000');
/*!40000 ALTER TABLE `client_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `client_name` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,'Gerardo Fuentes Miranda','MX',_binary ''),(2,'Vinateria las flores','MX',_binary ''),(3,'Guadalupe Vizcaina','MX',_binary '');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_products`
--

DROP TABLE IF EXISTS `orders_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `movement_date` datetime(6) DEFAULT NULL,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  `total` double NOT NULL,
  `order_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKksi03fs4f4imwcsm1vipner2c` (`order_id`),
  KEY `FK43vke5jd6eyasd92t3k24kdxq` (`product_id`),
  CONSTRAINT `FK43vke5jd6eyasd92t3k24kdxq` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKksi03fs4f4imwcsm1vipner2c` FOREIGN KEY (`order_id`) REFERENCES `client_orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_products`
--

LOCK TABLES `orders_products` WRITE;
/*!40000 ALTER TABLE `orders_products` DISABLE KEYS */;
INSERT INTO `orders_products` VALUES (1,'2024-04-18 03:54:40.000000',457.56,5,2287.8,3,5),(2,'2024-04-18 04:04:45.000000',680.89,4,2723.56,3,6);
/*!40000 ALTER TABLE `orders_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `stock` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Vino Blanco Ruanda',135.6,70),(2,'Vino Tinto Ruanda',180.55,90),(3,'Vino Rosado Ruanda',125.5,100),(4,'Tequila Dobel',890.56,20),(5,'Tequila Don Julio reposado',457.56,1),(6,'Tequila Don Julio Cristalino',680.89,4),(7,'Tequila Gran reserva reposado',1245.56,6),(8,'Mezcal Montelobos espadin',960.56,5),(9,'Mezcal de gusano reposado',760.56,14),(10,'Mezcal Danzantes cristalino',1457.9,20),(11,'Vino de la ruanda',5000,100),(12,'Vino de interés social',11,5);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-06 20:47:27
