/*
Navicat MySQL Data Transfer

Source Server         : 10.0.0.131
Source Server Version : 50717
Source Host           : 10.0.0.131:3306
Source Database       : code-sundy

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-04-04 16:25:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(255) NOT NULL COMMENT '用户名',
  `user_pwd` varchar(255) NOT NULL COMMENT '密码',
  `user_sex` varchar(255) DEFAULT NULL COMMENT '性别',
  `user_address` varchar(255) DEFAULT NULL COMMENT '地址',
  `user_phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `user_state` varchar(255) DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'admin', '123456', '女', '广东深圳市宝安区', '15959596555', '1');
INSERT INTO `tb_user` VALUES ('2', 'sundy', '654321', '男', '广东深圳市南山区', '15845218210', '1');
INSERT INTO `tb_user` VALUES ('3', 'Andy', '123654', '女', '广东深圳市福田区', '15956542165', '0');
INSERT INTO `tb_user` VALUES ('4', 'Tom', '321456', '女', '广东广州天河区', '15745412159', '0');
INSERT INTO `tb_user` VALUES ('5', 'Meky', '112233', '女', '广东广州天河区', '15745150197', '0');
INSERT INTO `tb_user` VALUES ('6', 'Heny', '112222', '女', '广西南宁市万秀区', '15745150128', '1');
INSERT INTO `tb_user` VALUES ('7', 'Lony', '332211', '男', '北京市朝阳区', '13155140927', '1');
INSERT INTO `tb_user` VALUES ('8', 'Lucy', '654321', '女', '广东深圳龙华', '15905695558', '');
