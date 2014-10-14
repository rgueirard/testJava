CREATE DATABASE  IF NOT EXISTS `pnom`;
USE `pnom`;
DROP TABLE IF EXISTS `operations`;
CREATE TABLE `operations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message` text NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;


#-----------------------------------
#USER RIGHTS MANAGEMENT
#-----------------------------------
CREATE USER 'pnom'@'localhost' IDENTIFIED BY 'toor';

GRANT ALL PRIVILEGES ON `pnom`.* TO 'pnom'@'localhost' WITH GRANT OPTION;

FLUSH PRIVILEGES;
