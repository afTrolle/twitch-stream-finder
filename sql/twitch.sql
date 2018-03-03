CREATE DATABASE  IF NOT EXISTS `twitch` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `twitch`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: twitch
-- ------------------------------------------------------
-- Server version	5.7.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `broadcaster_type`
--

DROP TABLE IF EXISTS `broadcaster_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `broadcaster_type` (
  `broadcaster_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`broadcaster_type_id`),
  UNIQUE KEY `type_UNIQUE` (`type`),
  UNIQUE KEY `id_UNIQUE` (`broadcaster_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='more info at: https://dev.twitch.tv/docs/api/reference#get-users\nUser’s broadcaster type: "partner", "affiliate", or "".';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `broadcaster_type`
--

LOCK TABLES `broadcaster_type` WRITE;
/*!40000 ALTER TABLE `broadcaster_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `broadcaster_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `client_id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(46) NOT NULL,
  `cookie` text NOT NULL,
  `issued` datetime NOT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE KEY `id_UNIQUE` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `community`
--

DROP TABLE IF EXISTS `community`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `community` (
  `community_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`community_id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `community`
--

LOCK TABLES `community` WRITE;
/*!40000 ALTER TABLE `community` DISABLE KEYS */;
/*!40000 ALTER TABLE `community` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game`
--

DROP TABLE IF EXISTS `game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game` (
  `game_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `art_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`game_id`),
  UNIQUE KEY `id_UNIQUE` (`game_id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `art_url_UNIQUE` (`art_url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game`
--

LOCK TABLES `game` WRITE;
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
/*!40000 ALTER TABLE `game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `language` (
  `language_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`language_id`),
  UNIQUE KEY `language_id_UNIQUE` (`language_id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stream`
--

DROP TABLE IF EXISTS `stream`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stream` (
  `stream_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `started` datetime NOT NULL,
  `ended` datetime DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `view_count` varchar(45) NOT NULL COMMENT 'latest read view count',
  `user_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  `stream_type_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  PRIMARY KEY (`stream_id`),
  UNIQUE KEY `stream_id_UNIQUE` (`stream_id`),
  KEY `user_id_fk§_idx` (`user_id`),
  KEY `stream_type_id_fk_idx` (`stream_type_id`),
  KEY `language_id_fk_idx` (`language_id`),
  KEY `game_id_fk_idx` (`game_id`),
  CONSTRAINT `fk_stream_game` FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_stream_language` FOREIGN KEY (`language_id`) REFERENCES `language` (`language_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_stream_stream_type` FOREIGN KEY (`stream_type_id`) REFERENCES `stream_type` (`stream_type_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_stream_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stream`
--

LOCK TABLES `stream` WRITE;
/*!40000 ALTER TABLE `stream` DISABLE KEYS */;
/*!40000 ALTER TABLE `stream` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stream_community`
--

DROP TABLE IF EXISTS `stream_community`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stream_community` (
  `stream_id` int(11) NOT NULL,
  `community_id` int(11) NOT NULL,
  UNIQUE KEY `stream_community_primary` (`community_id`,`stream_id`),
  KEY `community_id_fk_idx` (`community_id`),
  KEY `stream_id_fk_idx` (`stream_id`),
  CONSTRAINT `fk_stream_community_community` FOREIGN KEY (`community_id`) REFERENCES `community` (`community_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_strem_community_stream` FOREIGN KEY (`stream_id`) REFERENCES `stream` (`stream_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stream_community`
--

LOCK TABLES `stream_community` WRITE;
/*!40000 ALTER TABLE `stream_community` DISABLE KEYS */;
/*!40000 ALTER TABLE `stream_community` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stream_type`
--

DROP TABLE IF EXISTS `stream_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stream_type` (
  `stream_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`stream_type_id`),
  UNIQUE KEY `stream_type_id_UNIQUE` (`stream_type_id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stream_type`
--

LOCK TABLES `stream_type` WRITE;
/*!40000 ALTER TABLE `stream_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `stream_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `user_type_id` int(11) NOT NULL,
  `broadcaster_type_id` int(11) NOT NULL,
  `offline_image` varchar(255) DEFAULT NULL,
  `profile_image` varchar(255) DEFAULT NULL,
  `total_views` int(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `id_UNIQUE` (`user_id`),
  KEY `id_idx` (`user_type_id`),
  KEY `broadcastertype_id_idx` (`broadcaster_type_id`),
  CONSTRAINT `fk_user_broadcaster_type` FOREIGN KEY (`broadcaster_type_id`) REFERENCES `broadcaster_type` (`broadcaster_type_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_user_user_type` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`user_type_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_type`
--

DROP TABLE IF EXISTS `user_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_type` (
  `user_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL COMMENT 'Current known users are User’s type: "staff", "admin", "global_mod", or "".\nMore info at https://dev.twitch.tv/docs/api/reference#get-users\n',
  PRIMARY KEY (`user_type_id`),
  UNIQUE KEY `id_UNIQUE` (`user_type_id`),
  UNIQUE KEY `type_UNIQUE` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_type`
--

LOCK TABLES `user_type` WRITE;
/*!40000 ALTER TABLE `user_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vote`
--

DROP TABLE IF EXISTS `vote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vote` (
  `client_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `state` enum('positive','negative') NOT NULL,
  UNIQUE KEY `vote_primary_key` (`user_id`,`client_id`),
  KEY `user_id_fk_idx` (`user_id`),
  KEY `fk_vote_client_idx` (`client_id`),
  CONSTRAINT `fk_vote_client` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_vote_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vote`
--

LOCK TABLES `vote` WRITE;
/*!40000 ALTER TABLE `vote` DISABLE KEYS */;
/*!40000 ALTER TABLE `vote` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-03  0:52:50
