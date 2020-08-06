/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 127.0.0.1:3306
 Source Schema         : authority

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 06/08/2020 23:30:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户权限id',
  `authority` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '权限id',
  `member_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for purview
-- ----------------------------
DROP TABLE IF EXISTS `purview`;
CREATE TABLE `purview`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '权限id',
  `authority` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '权限名称',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '账号',
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户头像',
  `isEnable` bit(1) NULL DEFAULT NULL COMMENT '是否启用1、启用 0、禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
