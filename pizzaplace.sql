-- MySQL dump 10.13  Distrib 8.0.29, for Linux (x86_64)
--
-- Host: localhost    Database: pizzaplace
-- ------------------------------------------------------
-- Server version	8.0.29-0ubuntu0.20.04.3

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
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders`
(
    `id`               int         NOT NULL AUTO_INCREMENT,
    `customer_id`      int          DEFAULT NULL,
    `status`           varchar(55) NOT NULL,
    `adress`           varchar(45) NOT NULL,
    `phone_number`     varchar(45) NOT NULL,
    `order_date`       datetime    NOT NULL,
    `shipping_date`    datetime     DEFAULT NULL,
    `customer_comment` varchar(220) DEFAULT NULL,
    `first_name`       varchar(45) NOT NULL,
    `last_name`        varchar(45) NOT NULL,
    PRIMARY KEY (`id`),
    KEY                `fk_user_id_idx` (`customer_id`),
    CONSTRAINT `fk_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK
TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders`
VALUES (32, 10007, 'Delivered', 'Customer Street 56', '66999111', '2022-07-14 21:40:54', NULL,
        'Add ketchup to pizza, and a cold beer please! Thank you! ', 'Customer', 'Customerovich'),
       (33, 9999, 'Canceled', 'Guest Adress', '999999', '2022-07-14 21:46:42', NULL, 'Add ketchup on two pizzas!',
        'Guest', 'Guestovich');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `orders_product`
--

DROP TABLE IF EXISTS `orders_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_product`
(
    `order_id`   int NOT NULL,
    `product_id` int NOT NULL,
    `quantity`   int NOT NULL,
    PRIMARY KEY (`order_id`, `product_id`),
    KEY          `fk_product_id_idx` (`product_id`),
    CONSTRAINT `fk_order_Id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_product`
--

LOCK
TABLES `orders_product` WRITE;
/*!40000 ALTER TABLE `orders_product` DISABLE KEYS */;
INSERT INTO `orders_product`
VALUES (32, 14, 8),
       (32, 15, 5),
       (32, 16, 2),
       (33, 15, 10);
/*!40000 ALTER TABLE `orders_product` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product`
(
    `id`                  int          NOT NULL AUTO_INCREMENT,
    `name`                varchar(55)  NOT NULL,
    `size`                varchar(45)  NOT NULL,
    `price`               int          NOT NULL,
    `image_url`           varchar(200) NOT NULL,
    `product_category_id` int          NOT NULL,
    PRIMARY KEY (`id`),
    KEY                   `fk_product_category_id_idx` (`product_category_id`),
    CONSTRAINT `fk_product_category_id` FOREIGN KEY (`product_category_id`) REFERENCES `product_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK
TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product`
VALUES (14, 'Pasta', 'Medium', 500,
        'https://firebasestorage.googleapis.com/v0/b/pizza-place-cd744.appspot.com/o/images%2Fpasta.jpg1657818428310?alt=media&token=2d59efae-6f84-4b0b-839b-5bfd3d98fbef',
        2),
       (15, 'Pizza', 'Large', 900,
        'https://firebasestorage.googleapis.com/v0/b/pizza-place-cd744.appspot.com/o/images%2Fexternal-content.duckduckgo.com.jpeg1657827432974?alt=media&token=1927259e-5a4a-4662-a224-f306b0c6a390',
        1),
       (16, 'Beer', 'Large', 150,
        'https://firebasestorage.googleapis.com/v0/b/pizza-place-cd744.appspot.com/o/images%2Fbeer.jpeg1657827454521?alt=media&token=d8a98178-e556-4fcd-8dd4-4177b295fb32',
        6);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_category`
(
    `id`   int         NOT NULL AUTO_INCREMENT,
    `name` varchar(55) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`
--

LOCK
TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category`
VALUES (1, 'Pizza'),
       (2, 'Pasta'),
       (6, 'Alcohol');
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review`
(
    `id`          int         NOT NULL AUTO_INCREMENT,
    `reviewer_id` int          DEFAULT NULL,
    `rating`      varchar(55) NOT NULL,
    `comment`     varchar(500) DEFAULT NULL,
    `order_id`    int          DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY           `fk_reviewer_id_idx` (`reviewer_id`),
    KEY           `fk_orders_id` (`order_id`),
    CONSTRAINT `fk_orders_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
    CONSTRAINT `fk_reviewer_id` FOREIGN KEY (`reviewer_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK
TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review`
VALUES (12, 10007, 'Excellent', 'Everything was excellent!', 32);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user`
(
    `id`           int          NOT NULL AUTO_INCREMENT,
    `username`     varchar(45)  NOT NULL,
    `email`        varchar(45)  NOT NULL,
    `password`     varchar(255) NOT NULL,
    `adress`       varchar(45)  NOT NULL,
    `first_name`   varchar(45)  NOT NULL,
    `last_name`    varchar(55)  NOT NULL,
    `phone_number` varchar(100) NOT NULL,
    `user_type_id` int          NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `username_UNIQUE` (`username`),
    UNIQUE KEY `email_UNIQUE` (`email`),
    KEY            `fk_user_type_id_idx` (`user_type_id`),
    CONSTRAINT `fk_user_type_id` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10008 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK
TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user`
VALUES (3, 'admin', 'admin@mail.com', '$2a$10$DdXGx35XjzqVw4E7/AzKReMHpVncrAqaCf6K/d645mVfDkB.e31hy',
        'Admin\'s place 77','Admin','Adminovich','000555333',1),(9999,'Guest','this is a guest',' $2a$12$GWd0noNnhENbPNHf23XbreFLpyhbF3WTNLPqcmNCC5xbSduodiD5i','GuestAdress','Guest','Guest','Guest',3),(10006,'employee','employee@mail.com','$2a$10$3SxiItJ2C6ZWBHM4flhjsehTlYoc6F1Nrrw/k7FsgrmbLAAMn7I9W','Employee Avenue 156','Employee','Employeevich','64333555',2),(10007,'customer','customer@mail.com','$2a$10$oEGhPl/avhaor390cEmwsut64e1gFLCaLluu3cwf/p/sXerKd/hRu','Customer Street 56','Customer','Customerovich','66999111',3);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_type`
--

DROP TABLE IF EXISTS `user_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_type`
--

LOCK TABLES `user_type` WRITE;
/*!40000 ALTER TABLE `user_type` DISABLE KEYS */;
INSERT INTO `user_type` VALUES (1,'Admin'),(3,'Customer'),(2,'Employee');
/*!40000 ALTER TABLE `user_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-14 21:49:25
