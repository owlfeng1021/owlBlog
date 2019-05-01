/*
 Navicat Premium Data Transfer

 Source Server         : docker_mysql_1
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : 192.168.233.128:3306
 Source Schema         : owlBlog

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 01/05/2019 11:12:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_attach
-- ----------------------------
DROP TABLE IF EXISTS `t_attach`;
CREATE TABLE `t_attach`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `author_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `created` int(11) NULL DEFAULT NULL,
  `fkey` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `fname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ftype` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_comments
-- ----------------------------
DROP TABLE IF EXISTS `t_comments`;
CREATE TABLE `t_comments`  (
  `coid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `agent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `author_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `created` int(11) NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `mail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `owner_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `parent` int(11) NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `cid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`coid`) USING BTREE,
  INDEX `FK5y2wfl144ichqpljcbds100h3`(`cid`) USING BTREE,
  CONSTRAINT `FK5y2wfl144ichqpljcbds100h3` FOREIGN KEY (`cid`) REFERENCES `t_contents` (`cid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_contents
-- ----------------------------
DROP TABLE IF EXISTS `t_contents`;
CREATE TABLE `t_contents`  (
  `cid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `allow_comment` bit(1) NULL DEFAULT NULL,
  `allow_feed` bit(1) NULL DEFAULT NULL,
  `allow_ping` bit(1) NULL DEFAULT NULL,
  `author_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `categories` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `comments_num` int(11) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `created` int(11) NULL DEFAULT NULL,
  `hits` int(11) NULL DEFAULT NULL,
  `modified` int(11) NULL DEFAULT NULL,
  `slug` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `tags` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_contents_metas
-- ----------------------------
DROP TABLE IF EXISTS `t_contents_metas`;
CREATE TABLE `t_contents_metas`  (
  `mid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `cid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  INDEX `FKgh9yxc49ryx810i3280in9ey0`(`cid`) USING BTREE,
  INDEX `FKc1qxy65px6gk43l6olaidbi41`(`mid`) USING BTREE,
  CONSTRAINT `FKc1qxy65px6gk43l6olaidbi41` FOREIGN KEY (`mid`) REFERENCES `t_meats` (`mid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKgh9yxc49ryx810i3280in9ey0` FOREIGN KEY (`cid`) REFERENCES `t_contents` (`cid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_logs
-- ----------------------------
DROP TABLE IF EXISTS `t_logs`;
CREATE TABLE `t_logs`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `action` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `author_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `created` int(11) NULL DEFAULT NULL,
  `data` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_logs
-- ----------------------------
INSERT INTO `t_logs` VALUES ('1123111635489214464', '登录后台', '1', 1556605654, NULL, '0:0:0:0:0:0:0:1');
INSERT INTO `t_logs` VALUES ('1123112129460731904', '登录后台', '1', 1556605772, NULL, '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for t_meats
-- ----------------------------
DROP TABLE IF EXISTS `t_meats`;
CREATE TABLE `t_meats`  (
  `mid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `parent` int(11) NULL DEFAULT NULL,
  `slug` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sort` int(11) NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`mid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_meats
-- ----------------------------
INSERT INTO `t_meats` VALUES ('', '', 'saflkjasldk', NULL, 'http://jsjianwang.cn', 0, 'link');

-- ----------------------------
-- Table structure for t_options
-- ----------------------------
DROP TABLE IF EXISTS `t_options`;
CREATE TABLE `t_options`  (
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `value` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_options
-- ----------------------------
INSERT INTO `t_options` VALUES ('allow_install', '', NULL);
INSERT INTO `t_options` VALUES ('site_description', '13 Blog', NULL);
INSERT INTO `t_options` VALUES ('site_keywords', '13 Blog', NULL);
INSERT INTO `t_options` VALUES ('site_record', 'asdklfjlkasd', '备案号');
INSERT INTO `t_options` VALUES ('site_theme', 'default', NULL);
INSERT INTO `t_options` VALUES ('site_title', 'My Blog', '');
INSERT INTO `t_options` VALUES ('social_github', '234', NULL);
INSERT INTO `t_options` VALUES ('social_twitter', '234', NULL);
INSERT INTO `t_options` VALUES ('social_weibo', '423', NULL);
INSERT INTO `t_options` VALUES ('social_zhihu', '324', NULL);

-- ----------------------------
-- Table structure for t_users
-- ----------------------------
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users`  (
  `uid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `activated` int(11) NULL DEFAULT NULL,
  `created` int(11) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `group_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `home_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `logged` int(11) NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `screen_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_users
-- ----------------------------
INSERT INTO `t_users` VALUES ('1', 0, 1490756162, '1034683568@qq.com', 'visitor', NULL, 0, 'a66abb5684c45962d887564f08346e8d', 'admin', 'admin');

SET FOREIGN_KEY_CHECKS = 1;
