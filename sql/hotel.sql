/*
 Navicat Premium Data Transfer

 Source Server         : hotel
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : hotel

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 04/07/2020 21:29:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `userId` int(0) NOT NULL,
  `hotelId` int(0) NOT NULL,
  `comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `userAvatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `userName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `rate` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 1, 1, '这家酒店不错', NULL, '2020-06-25', '客户一号', 4);
INSERT INTO `comment` VALUES (2, 1, 1, '牛逼', NULL, '2020-06-25', '客户一号', 3);
INSERT INTO `comment` VALUES (3, 2, 2, '这家酒店住得不是很舒服', '', '2020-06-25', '客户二号', 3);
INSERT INTO `comment` VALUES (4, 2, 2, '不会吧不会吧不会有人觉得这家酒店不好吧', 'https://i.loli.net/2020/06/25/IAt8nojc9T6fEZh.jpg', '2020-06-26', '客户二号', 4);

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hotelId` int(0) NOT NULL DEFAULT -1,
  `couponType` int(0) NOT NULL,
  `couponName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `target_money` int(0) NULL DEFAULT NULL,
  `discount` double NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `start_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `end_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `discount_money` int(0) NULL DEFAULT NULL,
  `target_room` int(0) NULL DEFAULT NULL,
  `coopEnterprise` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `target_vip` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `hotelId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of coupon
-- ----------------------------
INSERT INTO `coupon` VALUES (2, '满500-100优惠', 2, 3, '满减优惠券', 500, -1, 1, NULL, NULL, 100, NULL, NULL, NULL);
INSERT INTO `coupon` VALUES (5, '这是一个多间优惠', 1, 2, '多间优惠券', 0, -1, 1, NULL, NULL, 150, 5, NULL, NULL);
INSERT INTO `coupon` VALUES (6, '这是一个满减优惠', 2, 3, '满减优惠券', 1000, -1, 1, NULL, NULL, 300, NULL, NULL, NULL);
INSERT INTO `coupon` VALUES (7, '限时优惠券', 2, 4, '限时优惠券', 0, -1, 1, '2020-05-20', '2020-06-06', NULL, NULL, NULL, NULL);
INSERT INTO `coupon` VALUES (12, '优惠', 1, 1, '618生日优惠', 0, -1, 1, NULL, NULL, 200, NULL, NULL, NULL);
INSERT INTO `coupon` VALUES (15, '这是一个vip优惠券', 3, 6, 'vip优惠券', 0, 0.2, 1, NULL, NULL, -1, NULL, NULL, 1);
INSERT INTO `coupon` VALUES (16, '这是一个vip', 1, 6, 'vip优惠券', 0, 0.1, 1, NULL, NULL, -1, NULL, NULL, 1);
INSERT INTO `coupon` VALUES (17, '这是一个限时优惠', 1, 4, '限时优惠券', 0, 0.1, 1, '2020-06-17', '2020-06-23', -1, NULL, NULL, NULL);
INSERT INTO `coupon` VALUES (18, '这是一个网站限时优惠', 0, 4, '网站限时优惠', 0, -1, 1, '2020-06-18', '2020-06-25', 150, NULL, NULL, NULL);
INSERT INTO `coupon` VALUES (28, '这是一个多间优惠券', 10, 2, '多间优惠券', NULL, 0.1, 1, NULL, NULL, -1, 5, NULL, NULL);
INSERT INTO `coupon` VALUES (29, '这是一个秘密', 11, 6, 'vip优惠券', NULL, 0.1, 1, NULL, NULL, -1, NULL, NULL, 2);
INSERT INTO `coupon` VALUES (30, '这是一个满减优惠券', 0, 3, '满减优惠券', 1000, 0.1, 1, NULL, NULL, -1, NULL, NULL, NULL);
INSERT INTO `coupon` VALUES (32, '这是一个vip优惠券', 0, 6, 'vip优惠券', NULL, -1, 1, NULL, NULL, 200, NULL, NULL, 3);

-- ----------------------------
-- Table structure for hotel
-- ----------------------------
DROP TABLE IF EXISTS `hotel`;
CREATE TABLE `hotel`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `hotelName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `hotelDescription` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bizRegion` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hotelStar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phoneNum` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rate` double NULL DEFAULT NULL,
  `manager_id` int(0) NULL DEFAULT NULL,
  `VIPLevel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `VIPLevelDiscount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hotel
-- ----------------------------
INSERT INTO `hotel` VALUES (1, '桂圆酒店', '这是一个秘密已做好决定', '南京市栖霞区', 'XiDan', 'Five', '13592862039', 3.5, 2, '1', NULL);
INSERT INTO `hotel` VALUES (2, '如家酒店', '欢迎您入住', '南京市栖霞区', 'XiDan', 'Four', '13592862039', 4.5, 2, '1', NULL);
INSERT INTO `hotel` VALUES (3, '东方大酒店', '欢迎您入住', '南京市栖霞区', 'XiDan', 'Three', '74125896321', 5, 5, '1', NULL);
INSERT INTO `hotel` VALUES (4, '斯亚花园大酒店', '欢迎您入住', '南京市栖霞区', 'XiDan', 'Three', '12345678963', 3, 5, '1', NULL);
INSERT INTO `hotel` VALUES (5, '江苏大酒店', '欢迎您入住', '南京市栖霞区', 'XiDan', 'Four', '25896314789', 4, 7, '1', NULL);
INSERT INTO `hotel` VALUES (6, '南京金桥饭店', '欢迎您入住', '南京市栖霞区', 'XiDan', 'Five', '96325874123', 5, 7, '1', NULL);
INSERT INTO `hotel` VALUES (7, '江苏新世纪大酒店', '欢迎您入住', '南京市栖霞区', 'XinJieKou', 'Four', '15935745621', 4, 5, '1', NULL);
INSERT INTO `hotel` VALUES (8, '杭州百合花酒店', '欢迎您入住', '杭州市西湖区', 'XiCheng', 'Three', '14796325803', 3, 9, '1', NULL);
INSERT INTO `hotel` VALUES (9, '凯悦酒店', '欢迎您入住', '杭州市西湖区', 'XiCheng', 'Four', '15476932580', 3.5, 9, '1', NULL);
INSERT INTO `hotel` VALUES (10, '流水苑宾馆', '欢迎您入住', '杭州市西湖区', 'XiCheng', 'Five', '12396547852', 4.5, 9, '1', NULL);

-- ----------------------------
-- Table structure for orderlist
-- ----------------------------
DROP TABLE IF EXISTS `orderlist`;
CREATE TABLE `orderlist`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `userId` int(0) NULL DEFAULT NULL,
  `hotelId` int(0) NULL DEFAULT NULL,
  `hotelName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `checkInDate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `checkOutDate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roomType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roomNum` int(0) NULL DEFAULT NULL,
  `peopleNum` int(0) NULL DEFAULT NULL,
  `haveChild` tinytext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `createDate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(65, 0) NULL DEFAULT NULL,
  `clientName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phoneNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orderState` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderlist
-- ----------------------------
INSERT INTO `orderlist` VALUES (36, 4, 1, '桂圆酒店', '2020-06-30', '2020-07-01', 'Family', 2, 4, '0', '2020-06-30', 1600, '小小车', '12345678919', '已执行');
INSERT INTO `orderlist` VALUES (37, 4, 1, '桂圆酒店', '2020-06-30', '2020-07-02', 'Family', 1, 1, '0', '2020-06-30', 1620, '小小车', '12345678919', '已执行');
INSERT INTO `orderlist` VALUES (38, 4, 2, '如家酒店', '2020-06-30', '2020-07-01', 'DoubleBed', 2, 4, '0', '2020-06-30', 810, '小小车', '12345678919', '已预订');

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `price` double NULL DEFAULT NULL,
  `curNum` int(0) NULL DEFAULT NULL,
  `total` int(0) NULL DEFAULT NULL,
  `hotelId` int(0) NULL DEFAULT NULL,
  `roomType` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bedType` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `breakfast` int(0) NULL DEFAULT NULL,
  `peopleNum` int(0) NULL DEFAULT NULL,
  `wifi` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (1, 200, 10, 10, 1, 'Family', 'DoubleBed', 1, 4, 1);
INSERT INTO `room` VALUES (2, 199, 6, 6, 3, 'BigBed', 'BigBed', 1, 2, 1);
INSERT INTO `room` VALUES (3, 299, 3, 3, 4, 'DoubleBed', 'DoubleBed', 1, 3, 1);
INSERT INTO `room` VALUES (4, 399, 10, 10, 5, 'Family', 'SingleBed', 0, 4, 1);
INSERT INTO `room` VALUES (5, 122, 2, 2, 1, 'BigBed', 'BigBed', 1, 2, 0);
INSERT INTO `room` VALUES (6, 399, 10, 10, 2, 'Family', 'SingleBed', 1, 4, 0);
INSERT INTO `room` VALUES (7, 500, 10, 10, 6, 'BigBed', NULL, 0, 2, 1);
INSERT INTO `room` VALUES (8, 500, 5, 5, 7, 'Family', NULL, 1, 4, 1);
INSERT INTO `room` VALUES (9, 1000, 8, 8, 8, 'DoubleBed', NULL, 1, 3, 1);
INSERT INTO `room` VALUES (10, 299, 5, 5, 9, 'Family', NULL, 0, 4, 1);
INSERT INTO `room` VALUES (11, 300, 0, 2, 2, 'DoubleBed', 'DoubleBed', 1, 3, 0);
INSERT INTO `room` VALUES (12, 400, 2, 2, 2, 'BigBed', 'BigBed', 1, 2, 0);
INSERT INTO `room` VALUES (13, 600, 3, 3, 1, 'DoubleBed', 'DoubleBed', 0, 3, 1);
INSERT INTO `room` VALUES (14, 500, 2, 2, 1, 'Family', 'BigBed', 1, 4, 0);
INSERT INTO `room` VALUES (15, 600, 2, 2, 1, 'DoubleBed', 'DoubleBed', 1, 3, 1);
INSERT INTO `room` VALUES (16, 500, 3, 3, 10, 'DoubleBed', NULL, 1, 3, 1);
INSERT INTO `room` VALUES (17, 500, 3, 3, 1, 'DoubleBed', 'DoubleBed', 0, 3, 1);
INSERT INTO `room` VALUES (18, 1000, 5, 5, 1, 'Theme', 'BigBed', 1, 5, 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phoneNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `credit` double(255, 0) NULL DEFAULT NULL,
  `userType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `vipLevel` int(0) NULL DEFAULT NULL,
  `birthday` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enterprise` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hotelId` int(0) NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '111@qq.com', '123456', '客户一号', '12345678919', 3370, 'Client', 4, '2000-07-14', '', NULL, 'https://i.loli.net/2020/06/25/wCg9PHeFyqckYUL.png');
INSERT INTO `user` VALUES (2, '222@qq.com', '123456', '客户二号', '12345678911', 100, 'Client', 1, '1997-06-07', '', NULL, 'https://i.loli.net/2020/06/25/wCg9PHeFyqckYUL.png');
INSERT INTO `user` VALUES (3, '333@qq.com', '123456', '酒店工作人员一号', '1593574567', 100, 'HotelManager', 1, '1990-07-24', '如家酒店', 1, 'https://i.loli.net/2020/06/25/IAt8nojc9T6fEZh.jpg');
INSERT INTO `user` VALUES (4, '444@qq.com', '123456', '网站管理人员', '12345678910', 100, 'Admin', 1, '1990-08-19', '南京大学', NULL, 'https://i.loli.net/2020/06/25/IAt8nojc9T6fEZh.jpg');
INSERT INTO `user` VALUES (5, '555@qq.com', '123456', '网站营销人员', '1234567890', 100, 'Marketer', 1, '1985-09-10', '南京大学', NULL, 'https://i.loli.net/2020/06/25/IAt8nojc9T6fEZh.jpg');
INSERT INTO `user` VALUES (6, '666@qq.com', '123456', '酒店工作人员二号', '1234567890', 150, 'HotelManager', 1, '1982-02-03', '格林豪泰酒店', 2, 'https://i.loli.net/2020/06/25/IAt8nojc9T6fEZh.jpg');

-- ----------------------------
-- Table structure for vipinfo
-- ----------------------------
DROP TABLE IF EXISTS `vipinfo`;
CREATE TABLE `vipinfo`  (
  `id` int(0) NOT NULL,
  `vip1` int(0) NOT NULL,
  `vip2` int(0) NOT NULL,
  `vip3` int(0) NOT NULL,
  `vip4` int(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vipinfo
-- ----------------------------
INSERT INTO `vipinfo` VALUES (1, 100, 400, 800, 1200);

SET FOREIGN_KEY_CHECKS = 1;
