/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : dormitory

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 07/07/2020 11:45:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dormitory
-- ----------------------------
DROP TABLE IF EXISTS `dormitory`;
CREATE TABLE `dormitory`  (
  `dormitoryId` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`dormitoryId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1101 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dormitory
-- ----------------------------
INSERT INTO `dormitory` VALUES (1001);
INSERT INTO `dormitory` VALUES (1100);

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager`  (
  `managerId` int(11) NOT NULL AUTO_INCREMENT,
  `managerName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `managerPassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `managerPhone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`managerId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10002 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES (10001, '再改回来', '000000', '000000');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `orderId` int(11) NOT NULL AUTO_INCREMENT,
  `orderDormitoryId` int(11) NOT NULL,
  `orderInfo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `orderStart` date NOT NULL,
  `orderFinish` date NULL DEFAULT NULL,
  `orderIsFinish` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`orderId`) USING BTREE,
  INDEX `ord_dor`(`orderDormitoryId`) USING BTREE,
  CONSTRAINT `ord_dor` FOREIGN KEY (`orderDormitoryId`) REFERENCES `dormitory` (`dormitoryId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 10005 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (10000, 1001, '修门', '2020-07-01', '0001-01-01', 0);
INSERT INTO `order` VALUES (10001, 1001, '修门', '2020-07-01', '0001-01-01', 0);
INSERT INTO `order` VALUES (10002, 1001, '修门', '2020-07-06', '0001-01-01', 0);
INSERT INTO `order` VALUES (10003, 1001, '修门', '2020-07-06', '0001-01-01', 0);
INSERT INTO `order` VALUES (10004, 1001, '修门', '2020-07-06', '0001-01-01', 0);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `studentId` int(11) NOT NULL AUTO_INCREMENT,
  `studentName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `studentPassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `studentDormitoryId` int(11) NOT NULL,
  `studentPhone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`studentId`) USING BTREE,
  INDEX `stud_dor`(`studentDormitoryId`) USING BTREE,
  CONSTRAINT `stud_dor` FOREIGN KEY (`studentDormitoryId`) REFERENCES `dormitory` (`dormitoryId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 10002 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (10001, '王五', '123', 1001, '23546125');

SET FOREIGN_KEY_CHECKS = 1;
