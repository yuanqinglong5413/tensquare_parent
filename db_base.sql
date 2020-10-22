/*
SQLyog Ultimate v12.3.1 (64 bit)
MySQL - 5.5.49 : Database - tensquare_base
*********************************************************************
*/

CREATE DATABASE /*!32312 IF NOT EXISTS*/`tensquare_base`;

USE `tensquare_base`;



DROP TABLE IF EXISTS `tb_city`;

CREATE TABLE `tb_city` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `name` varchar(20) DEFAULT NULL COMMENT '城市名称',
  `ishot` varchar(1) DEFAULT NULL COMMENT '是否热门',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='城市';



insert  into `tb_city`(`id`,`name`,`ishot`) values ('1','北京','1'),('2','上海','1'),('3','广州','1'),('4','深圳','1'),('5','天津','0'),('6','重庆','0'),('7','西安','0');



DROP TABLE IF EXISTS `tb_label`;

CREATE TABLE `tb_label` (
  `id` varchar(20) NOT NULL COMMENT '标签ID',
  `labelname` varchar(100) DEFAULT NULL COMMENT '标签名称',
  `state` varchar(1) DEFAULT NULL COMMENT '状态',
  `count` bigint(20) DEFAULT NULL COMMENT '使用数量',
  `recommend` varchar(1) DEFAULT NULL COMMENT '是否推荐',
  `fans` bigint(20) DEFAULT NULL COMMENT '粉丝数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签';



insert  into `tb_label`(`id`,`labelname`,`state`,`count`,`recommend`,`fans`) values ('1','java','1',NULL,NULL,NULL),('2','PHP','1',NULL,NULL,NULL),('3','C++','1',NULL,NULL,NULL),('4','python','1',NULL,NULL,NULL);


DROP TABLE IF EXISTS `tb_ul`;

CREATE TABLE `tb_ul` (
  `userid` varchar(20) NOT NULL,
  `labelid` varchar(20) NOT NULL,
  PRIMARY KEY (`userid`,`labelid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert  into `tb_ul`(`userid`,`labelid`) values ('1','1'),('1','2'),('1','3');

