CREATE DATABASE  IF NOT EXISTS `twitch` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='more info at: https://dev.twitch.tv/docs/api/reference#get-users\nUser’s broadcaster type: "partner", "affiliate", or "".';
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `followers`
--

DROP TABLE IF EXISTS `followers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `followers` (
  `followers_id` int(11) NOT NULL AUTO_INCREMENT,
  `followers` int(11) NOT NULL,
  `fetched` datetime NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`followers_id`),
  KEY `fk_followers_user_idx` (`user_id`),
  CONSTRAINT `fk_followers_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=504431 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `language` (
  `language_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`language_id`),
  UNIQUE KEY `language_id_UNIQUE` (`language_id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `latest_followers_update_view`
--

DROP TABLE IF EXISTS `latest_followers_update_view`;
/*!50001 DROP VIEW IF EXISTS `latest_followers_update_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `latest_followers_update_view` AS SELECT 
 1 AS `followers_id`,
 1 AS `followers`,
 1 AS `fetched`,
 1 AS `user_id`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `live_longest_time_since_follower_update_view`
--

DROP TABLE IF EXISTS `live_longest_time_since_follower_update_view`;
/*!50001 DROP VIEW IF EXISTS `live_longest_time_since_follower_update_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `live_longest_time_since_follower_update_view` AS SELECT 
 1 AS `stream_id`,
 1 AS `title`,
 1 AS `started`,
 1 AS `thumbnail`,
 1 AS `view_count`,
 1 AS `user_id`,
 1 AS `game_id`,
 1 AS `followers`,
 1 AS `fetched`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `live_streams_view`
--

DROP TABLE IF EXISTS `live_streams_view`;
/*!50001 DROP VIEW IF EXISTS `live_streams_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `live_streams_view` AS SELECT 
 1 AS `stream_id`,
 1 AS `title`,
 1 AS `started`,
 1 AS `ended`,
 1 AS `thumbnail`,
 1 AS `view_count`,
 1 AS `user_id`,
 1 AS `game_id`,
 1 AS `stream_type_id`,
 1 AS `language_id`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `new_view`
--

DROP TABLE IF EXISTS `new_view`;
/*!50001 DROP VIEW IF EXISTS `new_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `new_view` AS SELECT 
 1 AS `max-fetched`,
 1 AS `user_id`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `stream`
--

DROP TABLE IF EXISTS `stream`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stream` (
  `stream_id` bigint(20) NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `started` datetime NOT NULL,
  `ended` datetime DEFAULT NULL,
  `thumbnail` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `view_count` int(11) NOT NULL COMMENT 'latest read view count',
  `user_id` bigint(20) NOT NULL,
  `game_id` int(11) DEFAULT NULL,
  `stream_type_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  PRIMARY KEY (`stream_id`),
  UNIQUE KEY `stream_id_UNIQUE` (`stream_id`),
  KEY `stream_type_id_fk_idx` (`stream_type_id`),
  KEY `language_id_fk_idx` (`language_id`),
  KEY `game_id_fk_idx` (`game_id`),
  KEY `fk_stream_user_idx` (`user_id`),
  CONSTRAINT `fk_stream_game` FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_stream_language` FOREIGN KEY (`language_id`) REFERENCES `language` (`language_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_stream_stream_type` FOREIGN KEY (`stream_type_id`) REFERENCES `stream_type` (`stream_type_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_stream_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stream_community`
--

DROP TABLE IF EXISTS `stream_community`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stream_community` (
  `stream_id` double NOT NULL,
  `community_id` int(11) NOT NULL,
  KEY `community_id_fk_idx` (`community_id`),
  CONSTRAINT `fk_stream_community_community` FOREIGN KEY (`community_id`) REFERENCES `community` (`community_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL,
  `login` varchar(255) CHARACTER SET utf8 NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_type_id` int(11) NOT NULL,
  `broadcaster_type_id` int(11) NOT NULL,
  `offline_image` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `profile_image` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `total_views` int(11) NOT NULL,
  `updated` datetime NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `id_UNIQUE` (`user_id`),
  KEY `id_idx` (`user_type_id`),
  KEY `broadcastertype_id_idx` (`broadcaster_type_id`),
  CONSTRAINT `fk_user_broadcaster_type` FOREIGN KEY (`broadcaster_type_id`) REFERENCES `broadcaster_type` (`broadcaster_type_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_user_user_type` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`user_type_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  KEY `user_id_fk_idx` (`user_id`),
  KEY `fk_vote_client_idx` (`client_id`),
  CONSTRAINT `fk_vote_client` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'twitch'
--

--
-- Final view structure for view `latest_followers_update_view`
--

/*!50001 DROP VIEW IF EXISTS `latest_followers_update_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `latest_followers_update_view` AS select `s`.`followers_id` AS `followers_id`,`s`.`followers` AS `followers`,`s`.`fetched` AS `fetched`,`s`.`user_id` AS `user_id` from `followers` `s` where (`s`.`fetched` = (select max(`followers`.`fetched`) from `followers` where (`followers`.`user_id` = `s`.`user_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `live_longest_time_since_follower_update_view`
--

/*!50001 DROP VIEW IF EXISTS `live_longest_time_since_follower_update_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `live_longest_time_since_follower_update_view` AS select `live_streams_view`.`stream_id` AS `stream_id`,`live_streams_view`.`title` AS `title`,`live_streams_view`.`started` AS `started`,`live_streams_view`.`thumbnail` AS `thumbnail`,`live_streams_view`.`view_count` AS `view_count`,`live_streams_view`.`user_id` AS `user_id`,`live_streams_view`.`game_id` AS `game_id`,`latest_followers_update_view`.`followers` AS `followers`,`latest_followers_update_view`.`fetched` AS `fetched` from (`live_streams_view` left join `latest_followers_update_view` on((`live_streams_view`.`user_id` = `latest_followers_update_view`.`user_id`))) order by `latest_followers_update_view`.`fetched`,`live_streams_view`.`view_count` desc limit 240 */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `live_streams_view`
--

/*!50001 DROP VIEW IF EXISTS `live_streams_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `live_streams_view` AS select `stream`.`stream_id` AS `stream_id`,`stream`.`title` AS `title`,`stream`.`started` AS `started`,`stream`.`ended` AS `ended`,`stream`.`thumbnail` AS `thumbnail`,`stream`.`view_count` AS `view_count`,`stream`.`user_id` AS `user_id`,`stream`.`game_id` AS `game_id`,`stream`.`stream_type_id` AS `stream_type_id`,`stream`.`language_id` AS `language_id` from `stream` where isnull(`stream`.`ended`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `new_view`
--

/*!50001 DROP VIEW IF EXISTS `new_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `new_view` AS select max(`followers`.`fetched`) AS `max-fetched`,`followers`.`user_id` AS `user_id` from `followers` group by `followers`.`user_id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-08 21:03:14
