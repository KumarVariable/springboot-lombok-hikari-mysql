/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.35-log : Database - employees
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`employees` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `employees`;

/*Table structure for table `departments` */

DROP TABLE IF EXISTS `departments`;

CREATE TABLE `departments` (
  `dept_no` char(4) NOT NULL,
  `dept_name` varchar(40) NOT NULL,
  PRIMARY KEY (`dept_no`),
  UNIQUE KEY `dept_name` (`dept_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `dept_emp` */

DROP TABLE IF EXISTS `dept_emp`;

CREATE TABLE `dept_emp` (
  `emp_no` int(11) NOT NULL,
  `dept_no` char(4) NOT NULL,
  `from_date` date NOT NULL,
  `to_date` date NOT NULL,
  PRIMARY KEY (`emp_no`,`dept_no`),
  KEY `dept_no` (`dept_no`),
  CONSTRAINT `dept_emp_ibfk_1` FOREIGN KEY (`emp_no`) REFERENCES `employees` (`emp_no`) ON DELETE CASCADE,
  CONSTRAINT `dept_emp_ibfk_2` FOREIGN KEY (`dept_no`) REFERENCES `departments` (`dept_no`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `dept_manager` */

DROP TABLE IF EXISTS `dept_manager`;

CREATE TABLE `dept_manager` (
  `emp_no` int(11) NOT NULL,
  `dept_no` char(4) NOT NULL,
  `from_date` date NOT NULL,
  `to_date` date NOT NULL,
  PRIMARY KEY (`emp_no`,`dept_no`),
  KEY `dept_no` (`dept_no`),
  CONSTRAINT `dept_manager_ibfk_1` FOREIGN KEY (`emp_no`) REFERENCES `employees` (`emp_no`) ON DELETE CASCADE,
  CONSTRAINT `dept_manager_ibfk_2` FOREIGN KEY (`dept_no`) REFERENCES `departments` (`dept_no`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `employees` */

DROP TABLE IF EXISTS `employees`;

CREATE TABLE `employees` (
  `emp_no` int(11) NOT NULL,
  `birth_date` date NOT NULL,
  `first_name` varchar(14) NOT NULL,
  `last_name` varchar(16) NOT NULL,
  `gender` enum('M','F') NOT NULL,
  `hire_date` date NOT NULL,
  PRIMARY KEY (`emp_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `salaries` */

DROP TABLE IF EXISTS `salaries`;

CREATE TABLE `salaries` (
  `emp_no` int(11) NOT NULL,
  `salary` int(11) NOT NULL,
  `from_date` date NOT NULL,
  `to_date` date NOT NULL,
  PRIMARY KEY (`emp_no`,`from_date`),
  KEY `salaries_idx_to_date` (`to_date`),
  CONSTRAINT `salaries_ibfk_1` FOREIGN KEY (`emp_no`) REFERENCES `employees` (`emp_no`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `titles` */

DROP TABLE IF EXISTS `titles`;

CREATE TABLE `titles` (
  `emp_no` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `from_date` date NOT NULL,
  `to_date` date DEFAULT NULL,
  PRIMARY KEY (`emp_no`,`title`,`from_date`),
  KEY `titles_idx_to_date` (`to_date`),
  CONSTRAINT `titles_ibfk_1` FOREIGN KEY (`emp_no`) REFERENCES `employees` (`emp_no`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `current_dept_emp` */

DROP TABLE IF EXISTS `current_dept_emp`;

/*!50001 DROP VIEW IF EXISTS `current_dept_emp` */;
/*!50001 DROP TABLE IF EXISTS `current_dept_emp` */;

/*!50001 CREATE TABLE  `current_dept_emp`(
 `emp_no` int(11) ,
 `dept_no` char(4) ,
 `from_date` date ,
 `to_date` date 
)*/;

/*Table structure for table `dept_emp_latest_date` */

DROP TABLE IF EXISTS `dept_emp_latest_date`;

/*!50001 DROP VIEW IF EXISTS `dept_emp_latest_date` */;
/*!50001 DROP TABLE IF EXISTS `dept_emp_latest_date` */;

/*!50001 CREATE TABLE  `dept_emp_latest_date`(
 `emp_no` int(11) ,
 `from_date` date ,
 `to_date` date 
)*/;

/*Table structure for table `view_current_salary_title` */

DROP TABLE IF EXISTS `view_current_salary_title`;

/*!50001 DROP VIEW IF EXISTS `view_current_salary_title` */;
/*!50001 DROP TABLE IF EXISTS `view_current_salary_title` */;

/*!50001 CREATE TABLE  `view_current_salary_title`(
 `employee_id` int(11) ,
 `current_salary` int(11) ,
 `salary_from_date` date ,
 `salary_to_date` date ,
 `job_title` varchar(50) ,
 `title_from_date` date ,
 `title_to_date` date 
)*/;

/*Table structure for table `view_emp_current_job_title` */

DROP TABLE IF EXISTS `view_emp_current_job_title`;

/*!50001 DROP VIEW IF EXISTS `view_emp_current_job_title` */;
/*!50001 DROP TABLE IF EXISTS `view_emp_current_job_title` */;

/*!50001 CREATE TABLE  `view_emp_current_job_title`(
 `employee_id` int(11) ,
 `job_title` varchar(50) ,
 `title_from_date` date ,
 `title_to_date` date 
)*/;

/*Table structure for table `view_emp_deptt_manager` */

DROP TABLE IF EXISTS `view_emp_deptt_manager`;

/*!50001 DROP VIEW IF EXISTS `view_emp_deptt_manager` */;
/*!50001 DROP TABLE IF EXISTS `view_emp_deptt_manager` */;

/*!50001 CREATE TABLE  `view_emp_deptt_manager`(
 `manager_id` int(11) ,
 `department_id` char(4) ,
 `manager_from_date` date ,
 `manager_to_date` date ,
 `manager_name` varchar(31) 
)*/;

/*View structure for view current_dept_emp */

/*!50001 DROP TABLE IF EXISTS `current_dept_emp` */;
/*!50001 DROP VIEW IF EXISTS `current_dept_emp` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `current_dept_emp` AS select `l`.`emp_no` AS `emp_no`,`d`.`dept_no` AS `dept_no`,`l`.`from_date` AS `from_date`,`l`.`to_date` AS `to_date` from (`dept_emp` `d` join `dept_emp_latest_date` `l` on(((`d`.`emp_no` = `l`.`emp_no`) and (`d`.`from_date` = `l`.`from_date`) and (`l`.`to_date` = `d`.`to_date`)))) */;

/*View structure for view dept_emp_latest_date */

/*!50001 DROP TABLE IF EXISTS `dept_emp_latest_date` */;
/*!50001 DROP VIEW IF EXISTS `dept_emp_latest_date` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dept_emp_latest_date` AS select `dept_emp`.`emp_no` AS `emp_no`,max(`dept_emp`.`from_date`) AS `from_date`,max(`dept_emp`.`to_date`) AS `to_date` from `dept_emp` group by `dept_emp`.`emp_no` */;

/*View structure for view view_current_salary_title */

/*!50001 DROP TABLE IF EXISTS `view_current_salary_title` */;
/*!50001 DROP VIEW IF EXISTS `view_current_salary_title` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_current_salary_title` AS (select `salary`.`emp_no` AS `employee_id`,`salary`.`salary` AS `current_salary`,`salary`.`from_date` AS `salary_from_date`,`salary`.`to_date` AS `salary_to_date`,`jobtitle`.`job_title` AS `job_title`,`jobtitle`.`title_from_date` AS `title_from_date`,`jobtitle`.`title_to_date` AS `title_to_date` from (`employees`.`salaries` `salary` join (select `view_emp_current_job_title`.`employee_id` AS `employee_id`,`view_emp_current_job_title`.`job_title` AS `job_title`,`view_emp_current_job_title`.`title_from_date` AS `title_from_date`,`view_emp_current_job_title`.`title_to_date` AS `title_to_date` from `employees`.`view_emp_current_job_title`) `jobtitle` on((`jobtitle`.`employee_id` = `salary`.`emp_no`))) where (`salary`.`to_date` = (select max(`employees`.`salaries`.`to_date`) from `employees`.`salaries`))) */;

/*View structure for view view_emp_current_job_title */

/*!50001 DROP TABLE IF EXISTS `view_emp_current_job_title` */;
/*!50001 DROP VIEW IF EXISTS `view_emp_current_job_title` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_emp_current_job_title` AS (select `titles`.`emp_no` AS `employee_id`,`titles`.`title` AS `job_title`,`titles`.`from_date` AS `title_from_date`,`titles`.`to_date` AS `title_to_date` from `titles` where (`titles`.`to_date` = (select max(`titles`.`to_date`) from `titles`))) */;

/*View structure for view view_emp_deptt_manager */

/*!50001 DROP TABLE IF EXISTS `view_emp_deptt_manager` */;
/*!50001 DROP VIEW IF EXISTS `view_emp_deptt_manager` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_emp_deptt_manager` AS (select `deptmgr`.`emp_no` AS `manager_id`,`deptmgr`.`dept_no` AS `department_id`,`deptmgr`.`from_date` AS `manager_from_date`,`deptmgr`.`to_date` AS `manager_to_date`,concat(`emp`.`first_name`,' ',`emp`.`last_name`) AS `manager_name` from (`dept_manager` `deptmgr` join `employees` `emp` on((`emp`.`emp_no` = `deptmgr`.`emp_no`))) where (`deptmgr`.`to_date` = (select max(`dept_manager`.`to_date`) from `dept_manager`))) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
