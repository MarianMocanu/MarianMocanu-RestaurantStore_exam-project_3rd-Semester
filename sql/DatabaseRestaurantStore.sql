-- MySQL dump 10.13  Distrib 8.0.17, for macos10.14 (x86_64)
--
-- ------------------------------------------------------
-- Server version	5.6.42-log

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
-- Table structure for table `business_details`
--

DROP TABLE IF EXISTS `business_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `business_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `cvr` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mobile_phone` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business_details`
--

LOCK TABLES `business_details` WRITE;
/*!40000 ALTER TABLE `business_details` DISABLE KEYS */;
INSERT INTO `business_details` VALUES (1,'Trianglen 1','Halifax Osterbro','23910232','Zack','Bryant','12345678','2100'),(2,'Trianglen 1','Halifax Osterbro','23910232','Zack','Bryant','12345678','2100'),(3,'Nyhavn 63B','Karma Sushi','26391392','Kiran','Morin','12345678','1128'),(4,'Frederiksborggade 12','Burger King','19033546','Hugo','Philip','12345678','1350'),(5,'Nørrebrogade 13','Grillen BurgerBar','32728198','Sierra','Felix','12345678','2200'),(6,'Blegdamsvej 22','Collusion','53627381','Markus','Barnett','12345678','2100'),(7,'Refshalevej 96','Noma','73921354','Mirza','Noble','12345678','1432'),(8,'Lygten 33','McDonalds','90132145','Shola','McDonald','12345678','2200'),(9,'Hausergade 38','Ricemarket','30312358','Tasha','Hooper','12345678','1128'),(10,'Oselsgade 112','B\'India','42316487','Honey','Cobb','12345678','2100');
/*!40000 ALTER TABLE `business_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Meat'),(11,'Beverages'),(21,'Spices'),(31,'Bakery and Dairy'),(41,'Fruits and Vegetables');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (12),(12);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) DEFAULT NULL,
  `category_name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (1,41,'Fruits and Vegetables',45,21,'Asparagus',1),(2,1,'Meat',125,141,'Deer Back',2),(3,41,'Fruits and Vegetables',10,51,'Garlic',1),(4,21,'Spices',30,177,'Dill',1),(5,31,'Bakery and Dairy',20,182,'Baguette Parisien - Mette Munk',1),(6,41,'Fruits and Vegetables',25,1,'Cherry',1),(7,41,'Fruits and Vegetables',25,61,'Potatoes',10),(8,41,'Fruits and Vegetables',14,81,'Lettuce',6),(9,41,'Fruits and Vegetables',12.5,31,'Avocado',1),(10,41,'Fruits and Vegetables',30,41,'Carrot',9),(11,41,'Fruits and Vegetables',45,21,'Asparagus',20),(12,11,'Beverages',850,188,'Tuborg Draft',1),(13,11,'Beverages',140,196,'Tanqueray Gin',1),(14,11,'Beverages',192,191,'Fanta',1),(15,11,'Beverages',175,197,'French Rose Lemonade',1),(16,11,'Beverages',150,193,'Still Water',1),(17,11,'Beverages',130,195,'Absolut Vodka',1),(18,11,'Beverages',125,194,'Captain Morgan Dark Rum',1),(19,11,'Beverages',192,190,'Coca Cola',1),(20,31,'Bakery and Dairy',30,184,'Outdoor Egg',1),(21,31,'Bakery and Dairy',20,187,'Cheddar Cheese',1),(22,1,'Meat',75,161,'Pork Bacon',1),(23,31,'Bakery and Dairy',20,182,'Baguette Parisien - Mette Munk',1),(24,1,'Meat',78,101,'Burger Patty',10),(25,21,'Spices',20,176,'Barbecue Mix',6),(26,31,'Bakery and Dairy',120,181,'Pancake Mix - Krusteaz',2),(27,21,'Spices',30,175,'Basil',7),(28,21,'Spices',40,179,'Garlic Powder',1),(29,21,'Spices',42,180,'Thyme',6),(30,21,'Spices',100,174,'Rosemary',1),(31,31,'Bakery and Dairy',22,186,'Butter',1),(32,31,'Bakery and Dairy',30,184,'Outdoor Egg',30),(33,31,'Bakery and Dairy',50,183,'Caffeine Milk',10),(34,1,'Meat',50,121,'Chicken Breast',10),(35,41,'Fruits and Vegetables',12.5,31,'Avocado',4),(36,41,'Fruits and Vegetables',30,41,'Carrot',1),(37,41,'Fruits and Vegetables',12.5,31,'Avocado',1),(38,41,'Fruits and Vegetables',45,21,'Asparagus',1),(39,41,'Fruits and Vegetables',25,1,'Cherry',1),(40,41,'Fruits and Vegetables',10,51,'Garlic',1),(41,41,'Fruits and Vegetables',25,61,'Potatoes',1),(42,41,'Fruits and Vegetables',20,71,'Tomatoes',1),(43,41,'Fruits and Vegetables',14,81,'Lettuce',1),(44,1,'Meat',78,101,'Burger Patty',1),(45,1,'Meat',75,91,'Ground Beef',1),(46,1,'Meat',63,111,'Chopped Pork',1),(47,41,'Fruits and Vegetables',25,61,'Potatoes',1),(48,41,'Fruits and Vegetables',45,21,'Asparagus',1),(49,41,'Fruits and Vegetables',10,51,'Garlic',1),(50,41,'Fruits and Vegetables',20,71,'Tomatoes',1),(51,41,'Fruits and Vegetables',30,41,'Carrot',1),(52,41,'Fruits and Vegetables',14,81,'Lettuce',1),(53,41,'Fruits and Vegetables',45,21,'Asparagus',1),(54,31,'Bakery and Dairy',120,181,'Pancake Mix - Krusteaz',10),(55,21,'Spices',40,178,'Chilli Flakes',1),(56,1,'Meat',78,101,'Burger Patty',10);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_table`
--

DROP TABLE IF EXISTS `order_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cvr` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `delivery_address` varchar(255) DEFAULT NULL,
  `delivery_timestamp` datetime DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `order_timestamp` datetime DEFAULT NULL,
  `phone_no` varchar(255) DEFAULT NULL,
  `processed_timestamp` datetime DEFAULT NULL,
  `recipient_name` varchar(255) DEFAULT NULL,
  `order_status` int(11) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  `user_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK89gns61nqhr2ie0880dwip77v` (`user_user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_table`
--

LOCK TABLES `order_table` WRITE;
/*!40000 ALTER TABLE `order_table` DISABLE KEYS */;
INSERT INTO `order_table` VALUES (1,'23910232','Halifax Osterbro','Trianglen 1','2019-12-31 11:00:00',0,'2019-12-13 08:16:26','12345678','2019-12-13 08:34:37','Zack Bryant',1,355,'2100',3),(2,'23910232','Halifax Osterbro','Trianglen 1','2019-12-24 07:30:00',0,'2019-12-13 08:18:24','12345678','2019-12-13 08:35:42','Zack Bryant',2,1541.5,'2100',3),(3,'26391392','Karma Sushi','Nyhavn 63B','2020-03-10 16:00:00',0,'2019-12-13 08:21:27','12345678','2019-12-13 08:35:25','Kiran Morin',1,2099,'1128',4),(4,'19033546','Burger King','Frederiksborggade 12','2020-01-01 09:00:00',0,'2019-12-13 08:22:54','12345678',NULL,'Hugo Philip',0,1764,'1350',5),(5,'32728198','Grillen BurgerBar','Nørrebrogade 13','2019-12-31 12:00:00',97.5,'2019-12-13 08:24:58','12345678','2019-12-13 08:34:52','Sierra Felix',1,1852.5,'2200',6),(6,'32728198','Grillen BurgerBar','Nørrebrogade 13','2019-12-14 09:00:00',10,'2019-12-13 08:25:22','12345678','2019-12-13 08:33:15','Sierra Felix',1,171.5,'2200',6),(7,'53627381','Collusion','Blegdamsvej 22','2019-12-15 08:00:00',0,'2019-12-13 08:26:17','12345678','2019-12-13 08:34:59','Markus Barnett',2,216,'2100',7),(8,'73921354','Noma','Refshalevej 96','2020-01-02 06:30:00',0,'2019-12-13 08:27:40','12345678','2019-12-13 08:35:33','Mirza Noble',2,144,'1432',8),(9,'42316487','B\'India','Oselsgade 112','2019-12-25 16:00:00',0,'2019-12-13 08:28:41','12345678',NULL,'Honey Cobb',0,1285,'2100',11),(10,'30312358','Ricemarket','Hausergade 38','2019-12-22 08:45:00',0,'2019-12-13 08:31:18','12345678','2019-12-13 08:31:52','Tasha Hooper',1,780,'1128',10);
/*!40000 ALTER TABLE `order_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_table_item_list`
--

DROP TABLE IF EXISTS `order_table_item_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_table_item_list` (
  `order_id` int(11) NOT NULL,
  `item_list_id` int(11) NOT NULL,
  UNIQUE KEY `UK_j9ygy521aq55258fo29x78c90` (`item_list_id`),
  KEY `FKg01kr3xwfgy3by2089kx0s5mk` (`order_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_table_item_list`
--

LOCK TABLES `order_table_item_list` WRITE;
/*!40000 ALTER TABLE `order_table_item_list` DISABLE KEYS */;
INSERT INTO `order_table_item_list` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(2,6),(2,7),(2,8),(2,9),(2,10),(2,11),(3,12),(3,13),(3,14),(3,15),(3,16),(3,17),(3,18),(3,19),(3,20),(3,21),(3,22),(3,23),(4,24),(4,25),(4,26),(4,27),(4,28),(4,29),(4,30),(4,31),(5,32),(5,33),(5,34),(5,35),(6,36),(6,37),(6,38),(6,39),(6,40),(6,41),(6,42),(6,43),(7,44),(7,45),(7,46),(8,47),(8,48),(8,49),(8,50),(8,51),(8,52),(9,53),(9,54),(9,55),(10,56);
/*!40000 ALTER TABLE `order_table_item_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_images`
--

DROP TABLE IF EXISTS `product_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=212 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_images`
--

LOCK TABLES `product_images` WRITE;
/*!40000 ALTER TABLE `product_images` DISABLE KEYS */;
INSERT INTO `product_images` VALUES (1,'https://i.ibb.co/y85JYHh/cherry.jpg'),(11,'https://i.ibb.co/Wftw43s/asparagus.jpg'),(21,'https://i.ibb.co/pzPJDZt/carrot.jpg'),(31,'https://i.ibb.co/M78ppgZ/garlic.jpg'),(41,'https://i.ibb.co/x1HT3c0/potatoes.jpg'),(51,'https://i.ibb.co/sjCgJhk/tomatoes.jpg'),(61,'https://i.ibb.co/xDKJ8Ym/groundbeef.jpg'),(71,'https://i.ibb.co/0yM4Fvp/burgerpatty.jpg'),(81,'https://i.ibb.co/hy4hbwv/choppedpork.jpg'),(91,'https://i.ibb.co/6Y4qdmG/chickenbreast.jpg'),(101,'https://i.ibb.co/dt9ZBw8/deerback.jpg'),(111,'https://i.ibb.co/xCXQgFs/duckbreast.jpg'),(121,'https://i.ibb.co/Q9Q1tsm/porkbacon.jpg'),(131,'https://i.ibb.co/P9r5gtC/milk.jpg'),(141,'https://i.ibb.co/yf36PqF/asparagus.jpg'),(151,'https://i.ibb.co/cFBXT8H/cherry.jpg'),(152,'https://i.ibb.co/m6csB7Y/chickenwings.jpg'),(153,'https://i.ibb.co/XVcm8gf/lettuce.png'),(154,'https://i.ibb.co/thMj1Rb/avocado.jpg'),(155,'https://i.ibb.co/8mCgrt4/salt.jpg'),(156,'https://i.ibb.co/G2x5yFJ/pepper.jpg'),(157,'https://i.ibb.co/F6Nb97b/rosemary.jpg'),(158,'https://i.ibb.co/N1QMB8v/basil.jpg'),(159,'https://i.ibb.co/0M5m9pQ/barbecuemix.jpg'),(160,'https://i.ibb.co/C7PxHVj/dill.jpg'),(161,'https://i.ibb.co/MVLvWCM/chilliflakes.jpg'),(162,'https://i.ibb.co/QcWmsCF/garlicpowder.jpg'),(163,'https://i.ibb.co/gvm5PV6/thyme.jpg'),(164,'https://i.ibb.co/PZ51gMb/pancakemix.jpg'),(165,'https://i.ibb.co/b5hfkjN/baguette.jpg'),(166,'https://i.ibb.co/HCnNNQw/caffeinemilk.jpg'),(167,'https://i.ibb.co/7JRc36g/egg.jpg'),(168,'https://i.ibb.co/B4hFRtz/butter.jpg'),(169,'https://i.ibb.co/QKVMMQZ/cheddarcheese.jpg'),(170,'https://i.ibb.co/tb4TbmC/tuborg.jpg'),(171,'https://i.ibb.co/MMv5L2y/carlsberg.jpg'),(172,'https://i.ibb.co/LQNnmTX/cocacola.png'),(173,'https://i.ibb.co/tCSkvjC/fanta.png'),(174,'https://i.ibb.co/VwQg1L2/sparklingwater.jpg'),(175,'https://i.ibb.co/TW67sDH/stillwater.jpg'),(176,'https://i.ibb.co/sR4gHHM/captainmorgan.jpg'),(177,'https://i.ibb.co/m9593fC/absolutvodka.png'),(178,'https://i.ibb.co/CPdfSfq/tanqueraygin.png'),(179,'https://i.ibb.co/wL31H31/frenchlemonade.jpg'),(181,'https://i.ibb.co/tsbHpT0/asparagus2.jpg'),(191,'https://i.ibb.co/0j9BfTt/avocado2.jpg'),(201,'https://i.ibb.co/8gr8LkB/carrot2.jpg'),(211,'https://i.ibb.co/TqTpHyN/cherry2.jpg');
/*!40000 ALTER TABLE `product_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_prices`
--

DROP TABLE IF EXISTS `product_prices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_prices` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=542 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_prices`
--

LOCK TABLES `product_prices` WRITE;
/*!40000 ALTER TABLE `product_prices` DISABLE KEYS */;
INSERT INTO `product_prices` VALUES (1,25,1),(11,30,1),(331,25,1),(321,78,1),(41,45,1),(51,12.5,1),(61,12.5,1),(71,10,10),(81,30,1),(91,10,1),(101,25,1),(111,20,1),(121,14,1),(131,14,1),(141,10,25),(151,75,1),(161,78,1),(171,63,1),(181,50,1),(191,56.5,1),(201,56.5,1),(211,51.5,10),(221,45,1),(231,11,10),(241,11,0),(291,45,1),(301,75,1),(271,125,1),(281,110,1),(311,12,1),(341,45,1),(351,30,1),(361,10,1),(371,25,1),(381,20,1),(391,75,1),(401,78,1),(411,63,1),(421,50,1),(431,125,1),(441,110,1),(451,75,1),(461,12,1),(471,45,1),(481,25,1),(482,20,1),(483,25,1),(484,100,1),(485,30,1),(486,20,1),(487,30,1),(488,40,1),(489,40,1),(490,42,1),(491,120,1),(492,20,1),(493,50,1),(494,30,1),(495,30,1),(496,22,1),(497,20,1),(498,850,1),(499,850,1),(500,192,1),(501,192,1),(502,150,1),(503,150,1),(504,125,1),(505,130,1),(506,140,1),(507,175,1),(511,42.5,10),(521,9,15),(531,28.5,5),(541,22.5,10);
/*!40000 ALTER TABLE `product_prices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=198 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Price per kg.','Cherry',41),(21,'1 kilogram','Asparagus',41),(31,'1 piece','Avocado',41),(41,'1 kilogram','Carrot',41),(51,'100g','Garlic',41),(61,'1 kilogram','Potatoes',41),(71,'1 kilogram','Tomatoes',41),(81,'1 kilogram','Lettuce',41),(91,'1 kilogram','Ground Beef',1),(101,'1 kilogram','Burger Patty',1),(111,'1 kilogram','Chopped Pork',1),(121,'1 kilogram','Chicken Breast',1),(131,'1 kilogram','Chicken Wings',1),(141,'1 kilogram','Deer Back',1),(151,'1 kilogram','Duck Breast',1),(161,'1 kilogram','Pork Bacon',1),(171,'1 liter','Milk - Arla',31),(172,'100g','Salt',21),(173,'100g','Pepper',21),(174,'100g','Rosemary',21),(175,'100g','Basil',21),(176,'100g','Barbecue Mix',21),(177,'100g','Dill',21),(178,'100g','Chilli Flakes',21),(179,'100g','Garlic Powder',21),(180,'100g','Thyme',21),(181,'4.53kg','Pancake Mix - Krusteaz',31),(182,'1 piece','Baguette Parisien - Mette Munk',31),(183,'1 liter','Caffeine Milk',31),(184,'10 pieces','Outdoor Egg',31),(185,'1 liter','Scrambled Eggs',31),(186,'100g','Butter',31),(187,'100g','Cheddar Cheese',31),(188,'1 keg x 50l','Tuborg Draft',11),(189,'1 keg x 50l','Carlsberg Draft',11),(190,'1 crate x 24 bottles','Coca Cola',11),(191,'1 crate x 24 bottles','Fanta',11),(192,'1 crate x 24 bottles','Sparkling Water',11),(193,'1 crate x 24 bottles','Still Water',11),(194,'1 bottle','Captain Morgan Dark Rum',11),(195,'1 bottle','Absolut Vodka',11),(196,'1 bottle','Tanqueray Gin',11),(197,'1 crate x 24 bottles','French Rose Lemonade',11);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products_images`
--

DROP TABLE IF EXISTS `products_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products_images` (
  `product_id` int(11) NOT NULL,
  `images_id` int(11) NOT NULL,
  UNIQUE KEY `UK_68u3rm4tfmsixwa8nyfjg36xa` (`images_id`),
  KEY `FKgt41wyswrex82sy6iwdup2eak` (`product_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products_images`
--

LOCK TABLES `products_images` WRITE;
/*!40000 ALTER TABLE `products_images` DISABLE KEYS */;
INSERT INTO `products_images` VALUES (1,151),(21,181),(41,21),(51,31),(61,41),(71,51),(91,61),(101,71),(111,81),(121,91),(141,101),(151,111),(161,121),(171,131),(131,152),(81,153),(31,154),(172,155),(173,156),(174,157),(175,158),(176,159),(177,160),(178,161),(179,162),(180,163),(181,164),(182,165),(183,166),(184,167),(186,168),(187,169),(188,170),(189,171),(190,172),(191,173),(192,174),(193,175),(194,176),(195,177),(196,178),(197,179),(21,141),(31,191),(41,201),(1,211);
/*!40000 ALTER TABLE `products_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products_prices`
--

DROP TABLE IF EXISTS `products_prices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products_prices` (
  `product_id` int(11) NOT NULL,
  `prices_id` int(11) NOT NULL,
  UNIQUE KEY `UK_ix8jyc7jwfx8lhpqoav86prk4` (`prices_id`),
  KEY `FKqcsrm422tb8o70fppto8nynfc` (`product_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products_prices`
--

LOCK TABLES `products_prices` WRITE;
/*!40000 ALTER TABLE `products_prices` DISABLE KEYS */;
INSERT INTO `products_prices` VALUES (1,481),(171,461),(31,71),(31,61),(41,351),(51,361),(61,371),(71,381),(81,141),(81,131),(91,391),(101,401),(111,411),(121,421),(131,211),(131,201),(161,451),(21,511),(141,431),(151,441),(172,482),(173,483),(174,484),(175,485),(176,486),(177,487),(178,488),(179,489),(180,490),(181,491),(182,492),(183,493),(184,494),(185,495),(186,496),(187,497),(188,498),(189,499),(190,500),(191,501),(192,502),(193,503),(194,504),(195,505),(196,506),(197,507),(21,471),(31,521),(41,531),(1,541);
/*!40000 ALTER TABLE `products_prices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `active` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `business_details_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FKkxq6545ha99pdhkasq9g7o3ly` (`business_details_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,1,'johndoe@gmail.com','$2a$10$ikXs/7o5bewcBvY6ExLxze.nj/382cRTSuPPgiCYCmKuYucbIOy5e',NULL),(2,1,'stevebob@gmail.com','$2a$10$Jjxtev6pAeIICkzXdb3jyenRvGAXS/ZXtsNH3MYyO9K21WDc.qI5u',NULL),(3,1,'zackbryant@mail.com','$2a$10$oQHk0j6sS94WlWfx9Zp44Oubw9fY/3p9NxVhPgvZKV80NypDZ.H4G',2),(4,1,'kiranmorin@gmail.com','$2a$10$t0XWZP4NtmAa8xme8Je.7OOG.QIPObs74aCAJ9iRJLdw4MLm7hfvi',3),(5,1,'hugophilip@yahoo.com','$2a$10$D4/MZaSQQ5QBA6bF/y17HOcSIUge.fJAs8RkzQdFGiwDiqH2ebJNe',4),(6,1,'sierrafelix@gmail.com','$2a$10$S07k21btsfrhPpUJudZAbOBejj6El1yta1M7zZuuVXs6tBWJ/sEyu',5),(7,1,'markusbarnett@mail.com','$2a$10$KiCfF0f/5IxGqsesGm0xu.5DXRpvJuv.i7oCng8oZDEDtj3ahXgWq',6),(8,1,'mirzanoble@gmail.com','$2a$10$P856g0RKBP9kutvrUq10luTssnjKjcxZa7lUCHP0osXdamySOL1u.',7),(9,1,'sholamcdonald@mail.com','$2a$10$ArmWC.IqkUN4uAYyW4i9YuGKydyob.DfkpRHqlkrPX1Pjo.WmSXw6',8),(10,1,'tashahooper@yahoo.com','$2a$10$ntm7FvhtCkjg8Ap0uDHZVOPtbiP/YiEvihkdDM.CiWPFu9BLh5fEG',9),(11,1,'honeycobb@yahoo.com','$2a$10$p15F.Wa5oh5L0uAsHUxm7ugDNFPuzzkL68VgBAuPZAON0n4NvFeX.',10);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(2,1),(3,2),(4,2),(5,2),(6,2),(7,2),(8,2),(9,2),(10,2),(11,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-13 12:49:15
