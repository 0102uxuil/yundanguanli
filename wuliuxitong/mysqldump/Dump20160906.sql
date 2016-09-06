-- MySQL dump 10.13  Distrib 5.6.23, for Win32 (x86)
--
-- Host: localhost    Database: yundanguanli
-- ------------------------------------------------------
-- Server version	5.6.24-log

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
-- Table structure for table `cheliangxinxi`
--

DROP TABLE IF EXISTS `cheliangxinxi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cheliangxinxi` (
  `chepaihao` varchar(10) NOT NULL,
  `siji` varchar(10) NOT NULL,
  PRIMARY KEY (`chepaihao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `huowudan`
--

DROP TABLE IF EXISTS `huowudan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `huowudan` (
  `yundanbianhao` varchar(20) NOT NULL,
  `huowubianhao` varchar(10) NOT NULL,
  `chepaihao` varchar(10) NOT NULL,
  `riqi` date NOT NULL,
  `chufadi` varchar(10) NOT NULL,
  `mudidi` varchar(10) NOT NULL,
  `huozhu` varchar(30) NOT NULL,
  `huoming` varchar(10) NOT NULL,
  `zhongliang` float(10,2) NOT NULL,
  `zhongliang2` float(10,2) DEFAULT NULL,
  `jiage` float(10,2) DEFAULT NULL,
  `baodijia` float(10,2) DEFAULT NULL,
  `qitafeiyong` float(10,2) DEFAULT NULL,
  `beizhu` varchar(255) DEFAULT NULL,
  `shouxufei` float(10,2) DEFAULT '0.00',
  `yingfujine` float(10,2) NOT NULL,
  `shifujine` float(10,2) NOT NULL DEFAULT '0.00',
  `shifouqingsuan` enum('no','yes') NOT NULL DEFAULT 'no',
  `jiezhangbeizhu` varchar(255) DEFAULT NULL,
  `sijijiage` float(10,2) DEFAULT '0.00',
  `sijijiagejine` float(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`yundanbianhao`,`huowubianhao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `huozhubiao`
--

DROP TABLE IF EXISTS `huozhubiao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `huozhubiao` (
  `huozhu` varchar(10) NOT NULL DEFAULT '无',
  `huozhupinyin` varchar(45) NOT NULL DEFAULT 'wu',
  `huowu` varchar(10) NOT NULL DEFAULT '无',
  `riqi` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`huozhu`,`huowu`,`riqi`,`huozhupinyin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `huozhuxinxi`
--

DROP TABLE IF EXISTS `huozhuxinxi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `huozhuxinxi` (
  `huozhuming` varchar(30) NOT NULL,
  PRIMARY KEY (`huozhuming`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jiagebiao`
--

DROP TABLE IF EXISTS `jiagebiao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jiagebiao` (
  `huozhu` varchar(20) NOT NULL,
  `chufadi` varchar(20) NOT NULL,
  `mudidi` varchar(20) NOT NULL,
  `huoming` varchar(20) NOT NULL,
  `jiage` float(10,2) DEFAULT '0.00',
  `sijijiage` float(10,2) DEFAULT '0.00',
  PRIMARY KEY (`huozhu`,`chufadi`,`mudidi`,`huoming`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `kaixiaodan`
--

DROP TABLE IF EXISTS `kaixiaodan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kaixiaodan` (
  `yundanbianhao` varchar(20) NOT NULL,
  `chepaihao` varchar(10) DEFAULT NULL,
  `siji` varchar(10) DEFAULT NULL,
  `chucheriqi` date DEFAULT NULL,
  `chuchechufadi` varchar(10) DEFAULT NULL,
  `chuchemudidi` varchar(10) DEFAULT NULL,
  `huicheriqi` date DEFAULT NULL,
  `huichechufadi` varchar(10) DEFAULT NULL,
  `huichemudidi` varchar(10) DEFAULT NULL,
  `chuchekuan` float(10,2) DEFAULT NULL,
  `jiayouzhanjiayou` float(10,2) DEFAULT NULL,
  `tingchechangjiayou` float(10,2) DEFAULT NULL,
  `tingchechangyoujia` float(10,2) DEFAULT NULL,
  `guolufei` float(10,2) DEFAULT NULL,
  `yuetongka` float(10,2) DEFAULT NULL,
  `gongzi` float(10,2) DEFAULT NULL,
  `yifugongzi` float(10,2) DEFAULT NULL,
  `chifan` float(10,2) DEFAULT NULL,
  `zhusu` float(10,2) DEFAULT NULL,
  `jiashui` float(10,2) DEFAULT NULL,
  `zuochefei` float(10,2) DEFAULT NULL,
  `cailiaofei` float(10,2) DEFAULT NULL,
  `tingchefei` float(10,2) DEFAULT NULL,
  `guobangfei` float(10,2) DEFAULT NULL,
  `zhuangchefei` float(10,2) DEFAULT NULL,
  `xiechefei` float(10,2) DEFAULT NULL,
  `luntai` float(10,2) DEFAULT NULL,
  `fakuan` float(10,2) DEFAULT NULL,
  `xiaofei` float(10,2) DEFAULT NULL,
  `xiulifei` float(10,2) DEFAULT NULL,
  `qitafeiyong` float(10,2) DEFAULT NULL,
  `beizhu` varchar(255) DEFAULT NULL,
  `zongkaixiao` float(10,2) DEFAULT NULL,
  `gonglishu` float(10,2) DEFAULT '1.00',
  `youhao` float(10,2) DEFAULT '0.00',
  PRIMARY KEY (`yundanbianhao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `luxianbiao`
--

DROP TABLE IF EXISTS `luxianbiao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `luxianbiao` (
  `chufadi` varchar(10) NOT NULL DEFAULT '无',
  `mudidi` varchar(10) NOT NULL DEFAULT '无',
  `riqi` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`chufadi`,`mudidi`,`riqi`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `niankaixiaodan`
--

DROP TABLE IF EXISTS `niankaixiaodan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `niankaixiaodan` (
  `chepaihao` varchar(10) NOT NULL,
  `riqi` date NOT NULL,
  `shenche` float(10,2) DEFAULT NULL,
  `shenchebz` varchar(255) DEFAULT NULL,
  `baoxian` float(10,2) DEFAULT NULL,
  `baoxianbz` varchar(255) DEFAULT NULL,
  `gerenxian` float(10,2) DEFAULT NULL,
  `gerenxianbz` varchar(255) DEFAULT NULL,
  `shenyingyunzheng` float(10,2) DEFAULT NULL,
  `gprs` float(10,2) DEFAULT NULL,
  `qitafeiyong` float(10,2) DEFAULT NULL,
  `beizhu` varchar(255) DEFAULT NULL,
  `zongkaixiao` float(10,2) DEFAULT '0.00',
  PRIMARY KEY (`chepaihao`,`riqi`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `weixiudan`
--

DROP TABLE IF EXISTS `weixiudan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `weixiudan` (
  `chepaihao` varchar(10) NOT NULL,
  `riqi` date NOT NULL,
  `weixiudian` varchar(10) NOT NULL,
  `weixiuxiangmu` varchar(10) DEFAULT NULL,
  `weixiujine` float(10,2) DEFAULT NULL,
  `beizhu` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`chepaihao`,`riqi`,`weixiudian`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `yuekaixiaodan`
--

DROP TABLE IF EXISTS `yuekaixiaodan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yuekaixiaodan` (
  `chepaihao` varchar(255) NOT NULL,
  `riqi` date NOT NULL,
  `yuetongka` float(10,2) DEFAULT NULL,
  `dianhuafei` float(10,2) DEFAULT NULL,
  `qitafeiyong` float(10,2) DEFAULT NULL,
  `beizhu` varchar(255) DEFAULT NULL,
  `zongkaixiao` float(10,2) DEFAULT '0.00',
  PRIMARY KEY (`chepaihao`,`riqi`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-06 17:29:51
