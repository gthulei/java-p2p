/*
 Navicat Premium Data Transfer

 Source Server         : db_hl_p2p
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost
 Source Database       : db_p2p

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : utf-8

 Date: 03/21/2018 17:42:12 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL COMMENT '用户ID',
  `tradePassword` varchar(255) DEFAULT NULL COMMENT '交易密码',
  `usableAmount` decimal(18,4) NOT NULL COMMENT '账户可用金额',
  `freezedAmount` decimal(18,4) NOT NULL COMMENT '账户冻结金额',
  `borrowLimitAmount` decimal(18,4) NOT NULL COMMENT '授信额度',
  `version` int(11) NOT NULL COMMENT '版本',
  `unReceiveInterest` decimal(18,4) NOT NULL COMMENT '账户待收利息',
  `unReceivePrincipal` decimal(18,4) NOT NULL COMMENT '账户待收本金',
  `unReturnAmount` decimal(18,4) NOT NULL COMMENT '账户待还金额',
  `remainBorrowLimit` decimal(18,4) NOT NULL COMMENT '账户剩余授信额度',
  PRIMARY KEY (`id`) COMMENT '主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `accountflow`
-- ----------------------------

DROP TABLE IF EXISTS `accountflow`;
CREATE TABLE `accountflow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `accountActionType` tinyint(4) NOT NULL COMMENT '资金变化的类型',
  `amount` decimal(18,4) NOT NULL '总金额',
  `note` varchar(255) NOT NULL,
  `balance` decimal(18,4) NOT NULL COMMENT '可用金额',
  `freezed` decimal(18,4) NOT NULL COMMENT '冻结金额',
  `actionTime` datetime NOT NULL COMMENT '资金变化时间',
  `account_id` bigint(20) NOT NULL COMMENT '账户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `bid`
-- ----------------------------

DROP TABLE IF EXISTS `bid`;
CREATE TABLE `bid` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `actualRate` decimal(8,4) NOT NULL COMMENT '年化利率',
  `availableAmount` decimal(18,4) NOT NULL COMMENT '这次投标金额',
  `bidrequest_id` bigint(20) NOT NULL COMMENT '关联标ID',
  `bidUser_id` bigint(20) NOT NULL  COMMENT '投标人',
  `bidTime` datetime NOT NULL  COMMENT '投标时间',
  `bidRequestTitle` varchar(255) DEFAULT NULL COMMENT '借款标题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `bidrequest`
-- ----------------------------
DROP TABLE IF EXISTS `bidrequest`;
CREATE TABLE `bidrequest` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `version` int(11) NOT NULL COMMENT '版本号',
  `bidRequestType` tinyint(4) NOT NULL COMMENT '借款类型',
  `bidRequestState` tinyint(4) NOT NULL COMMENT '借款状态',
  `bidRequestAmount` decimal(18,4) NOT NULL COMMENT '借款总金额',
  `currentRate` decimal(8,4) NOT NULL COMMENT '年化利率',
  `monthes2Return` tinyint(4) NOT NULL COMMENT '还款月数',
  `bidCount` int(11) NOT NULL COMMENT '已投标次数',
  `totalRewardAmount` decimal(18,4) NOT NULL COMMENT '总回报金额(总利息)',
  `currentSum` decimal(18,4) NOT NULL COMMENT '当前已投标总金额',
  `title` varchar(255) NOT NULL COMMENT '借款标题',
  `description` varchar(255) NOT NULL COMMENT '借款描述',
  `note` varchar(255) NOT NULL COMMENT '风控意见',
  `disableDate` datetime DEFAULT NULL COMMENT '招标截止日期',
  `createuser_id` bigint(20) NOT NULL COMMENT '借款人id',
  `disableDays` tinyint(4) NOT NULL COMMENT '招标天数',
  `minBidAmount` decimal(18,4) NOT NULL COMMENT '最小借款金额',
  `applyTime` datetime DEFAULT NULL COMMENT '申请时间',
  `publishTime` datetime DEFAULT NULL COMMENT '发标时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `bidrequestaudithistory`
-- ----------------------------

DROP TABLE IF EXISTS `bidrequestaudithistory`;
CREATE TABLE `bidrequestaudithistory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `state` tinyint(4) NOT NULL COMMENT '审核的状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `auditTime` datetime DEFAULT NULL COMMENT '审核时间',
  `applyTime` datetime NOT NULL COMMENT '借款时间',
  `auditor_id` bigint(20) DEFAULT NULL COMMENT '审核人ID',
  `applier_id` bigint(20) NOT NULL COMMENT '借款人ID',
  `bidRequestId` bigint(20) NOT NULL COMMENT '关联标ID',
  `auditType` tinyint(4) NOT NULL COMMENT '审核的状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `companybankinfo`
-- ----------------------------

DROP TABLE IF EXISTS `companybankinfo`;
CREATE TABLE `companybankinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bankname` varchar(255) DEFAULT NULL COMMENT '银行名称',
  `accountname` varchar(255) DEFAULT NULL COMMENT '开户人姓名',
  `banknumber` varchar(255) DEFAULT NULL COMMENT '开户人银行账号',
  `bankforkname` varchar(255) DEFAULT NULL COMMENT '开户支行',
  `logininfo_id` bigint(20) NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `emailactive`
-- ----------------------------
DROP TABLE IF EXISTS `emailactive`;
CREATE TABLE `emailactive` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `logininfo_id` bigint(20) NOT NULL COMMENT '用户ID',
  `email` varchar(255) NOT NULL COMMENT '用户邮箱',
  `sendDate` datetime NOT NULL COMMENT '发送时间',
  `uuidcode` varchar(255) NOT NULL COMMENT '邮箱验证码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `iplog`
-- ----------------------------
DROP TABLE IF EXISTS `iplog`;
CREATE TABLE `iplog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ip` varchar(50) NOT NULL COMMENT 'ip',
  `loginState` tinyint(4) NOT NULL COMMENT '登录状态',
  `username` varchar(50) DEFAULT NULL COMMENT '主键',
  `loginInfoId` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `loginType` tinyint(4) NOT NULL COMMENT '用户类型',
  `loginTime` datetime NOT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=435 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `logininfo`
-- ----------------------------
DROP TABLE IF EXISTS `logininfo`;
CREATE TABLE `logininfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(200) DEFAULT NULL COMMENT '密码',
  `state` tinyint(4) DEFAULT NULL COMMENT '用户状态 0 正常 1 锁定',
  `usertype` tinyint(4) DEFAULT NULL COMMENT '用户类型',
  `admin` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `paymentschedule`
-- ----------------------------
DROP TABLE IF EXISTS `paymentschedule`;
CREATE TABLE `paymentschedule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `deadLine` datetime NOT NULL COMMENT '本期还款截止期限',
  `payDate` datetime DEFAULT NULL COMMENT '还款时间',
  `totalAmount` decimal(18,4) NOT NULL COMMENT '本期还款总金额',
  `principal` decimal(18,4) NOT NULL COMMENT '本期还款本金',
  `interest` decimal(18,4) NOT NULL COMMENT '本期还款总利息',
  `monthIndex` tinyint(4) NOT NULL COMMENT '第几期',
  `state` tinyint(4) NOT NULL COMMENT '本期还款状态（默认正常待还）',
  `bidRequestType` tinyint(4) NOT NULL COMMENT '借款类型',
  `returnType` tinyint(4) NOT NULL COMMENT '还款方式',
  `bidrequest_id` bigint(20) NOT NULL COMMENT '标的ID',
  `biduser_id` bigint(20) NOT NULL COMMENT '还款人ID',
  `bidRequestTitle` varchar(255) DEFAULT NULL COMMENT '借款名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `paymentscheduledetail`
-- ----------------------------
DROP TABLE IF EXISTS `paymentscheduledetail`;
CREATE TABLE `paymentscheduledetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT, COMMENT '主键'
  `bidamount` decimal(18,4) NOT NULL COMMENT '该投标人总共投标金额',
  `bid_id` bigint(20) NOT NULL COMMENT '对应的投标ID',
  `totalamount` decimal(18,4) NOT NULL COMMENT '本期还款总金额',
  `principal` decimal(18,4) NOT NULL COMMENT '本期应还款本金',
  `interest` decimal(18,4) NOT NULL COMMENT '本期应还款利息',
  `monthindex` tinyint(4) NOT NULL COMMENT '第几期（即第几个月）',
  `deadline` datetime NOT NULL COMMENT '本期还款截止时间',
  `bidrequest_id` bigint(20) NOT NULL COMMENT '所属哪个借款',
  `paydate` datetime DEFAULT NULL COMMENT '还款时间',
  `returntype` tinyint(4) NOT NULL COMMENT '还款方式',
  `paymentschedule_id` bigint(20) NOT NULL COMMENT '所属还款计划'
  `fromlogininfo_id` bigint(20) DEFAULT NULL COMMENT '还款人',
  `tologininfo_id` bigint(20) DEFAULT NULL COMMENT '收款人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `realauth`
-- ----------------------------
DROP TABLE IF EXISTS `realauth`;
CREATE TABLE `realauth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `realname` varchar(50) NOT NULL COMMENT '用户名',
  `sex` tinyint(4) NOT NULL COMMENT '性别',
  `birthDate` varchar(50) DEFAULT NULL COMMENT '生日',
  `idNumber` varchar(50) NOT NULL COMMENT '身份证号',
  `address` varchar(255) NOT NULL COMMENT '住宅地址',
  `state` tinyint(4) NOT NULL COMMENT '审核状态',
  `image1` varchar(255) NOT NULL COMMENT '身份证正面',
  `image2` varchar(255) NOT NULL COMMENT '身份证反面',
  `remark` varchar(255) DEFAULT NULL COMMENT '审核备注',
  `auditTime` datetime DEFAULT NULL COMMENT '审核时间',
  `applyTime` datetime NOT NULL COMMENT '审请时间',
  `auditor_id` bigint(20) DEFAULT NULL COMMENT '审核人ID',
  `applier_id` bigint(20) NOT NULL COMMENT '审请人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `rechargeoffline`
-- ----------------------------
DROP TABLE IF EXISTS `rechargeoffline`;
CREATE TABLE `rechargeoffline` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `state` tinyint(4) NOT NULL COMMENT '交易状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `auditTime` datetime DEFAULT NULL COMMENT '审核时间',
  `applyTime` datetime NOT NULL COMMENT '审请时间',
  `auditor_id` bigint(20) DEFAULT NULL COMMENT '审核人ID',
  `applier_id` bigint(20) NOT NULL COMMENT '审请人ID',
  `tradeCode` varchar(255) NOT NULL COMMENT '交易号',
  `tradeTime` datetime NOT NULL COMMENT '交易时间',
  `amount` decimal(18,4) NOT NULL COMMENT '交易金额',
  `note` varchar(255) DEFAULT NULL COMMENT '交易说明',
  `bankinfo_id` bigint(20) NOT NULL COMMENT '银行信息ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `systemaccount`
-- ----------------------------

DROP TABLE IF EXISTS `systemaccount`;
CREATE TABLE `systemaccount` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `version` int(11) NOT NULL COMMENT '版本号',
  `beginDate` datetime DEFAULT NULL COMMENT '开始时间',
  `endDate` datetime DEFAULT NULL COMMENT '结束时间',
  `createdate` datetime NOT NULL COMMENT '创建时间',
  `totalbalance` decimal(18,4) NOT NULL COMMENT '平台账户的可用金额',
  `freezedamount` decimal(18,4) NOT NULL COMMENT '平台账户的冻结金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `systemaccountflow`
-- ----------------------------
DROP TABLE IF EXISTS `systemaccountflow`;
CREATE TABLE `systemaccountflow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `createdDate` datetime NOT NULL COMMENT '创建时间',
  `accountactiontype` tinyint(4) NOT NULL COMMENT '资金变化的类型',
  `amount` decimal(18,4) NOT NULL COMMENT '流水金额',
  `note` varchar(255) DEFAULT NULL,
  `balance` decimal(18,4) NOT NULL COMMENT '可用金额',
  `freezedAmount` decimal(18,4) NOT NULL COMMENT '流水之后账户的 冻结金额',
  `systemAccount_id` bigint(20) NOT NULL COMMENT '系统账户对应ID',
  `targetuser_id` bigint(20) NOT NULL COMMENT '还款目标ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `systemdictionary`
-- ----------------------------
DROP TABLE IF EXISTS `systemdictionary`;
CREATE TABLE `systemdictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sn` varchar(50) NOT NULL COMMENT '字典码',
  `title` varchar(50) NOT NULL COMMENT '字典分类',
  `intro` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `systemdictionary`
-- ----------------------------
BEGIN;
INSERT INTO `systemdictionary` VALUES ('1', 'incomeGrade', '月收入', null), ('2', 'educationBackground', '学历', null), ('3', 'marriage', '婚姻', null), ('4', 'kidCount', '子女', null), ('5', 'houseCondition', '住房条件', null), ('6', 'userFileType', '风控资料类型', null);
COMMIT;

-- ----------------------------
--  Table structure for `systemdictionaryitem`
-- ----------------------------
DROP TABLE IF EXISTS `systemdictionaryitem`;
CREATE TABLE `systemdictionaryitem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parentId` bigint(20) NOT NULL COMMENT '字典父ID',
  `title` varchar(50) NOT NULL COMMENT '字典标题',
  `tvalue` varchar(50) DEFAULT NULL,
  `sequence` tinyint(4) NOT NULL COMMENT '字典排序',
  `intro` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `systemdictionaryitem`
-- ----------------------------
BEGIN;
INSERT INTO `systemdictionaryitem` VALUES ('1', '1', '3000以下', '1', '1', null), ('2', '1', '3000~5000', '2', '1', null), ('3', '2', '大专以下', '1', '1', null), ('4', '2', '大专', '2', '1', null), ('5', '3', '已婚', '1', '1', null), ('6', '3', '未婚', '2', '1', null), ('7', '4', '有子女', '1', '1', null), ('8', '4', '无子女', '2', '1', null), ('9', '5', '有车有房', '1', '1', null), ('10', '5', '无车有房', '2', '1', null), ('11', '5', '租房', '3', '1', null), ('12', '6', '房产证正面', '1', '1', null), ('13', '6', '房产证反面', '2', '1', null), ('14', '6', '户口本', '3', '1', null), ('15', '6', '结婚证', '4', '1', null), ('16', '6', '银行流水证明', '5', '1', null), ('17', '6', '学位证', '6', '1', null), ('18', '6', '毕业证', '7', '1', null), ('19', '6', '电话记录', '7', '1', null);
COMMIT;

-- ----------------------------
--  Table structure for `userfile`
-- ----------------------------
DROP TABLE IF EXISTS `userfile`;
CREATE TABLE `userfile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `state` tinyint(4) NOT NULL COMMENT '审核状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '审核备注',
  `auditTime` datetime DEFAULT NULL COMMENT '审核时间',
  `applyTime` datetime NOT NULL COMMENT '提交时间',
  `auditor_id` bigint(20) DEFAULT NULL COMMENT '审核人ID',
  `applier_id` bigint(20) NOT NULL COMMENT '申请人ID',
  `score` tinyint(4) NULL COMMENT '风控分数',
  `file` varchar(255) NOT NULL COMMENT '风控图片',
  `filetype_id` bigint(20) DEFAULT NULL COMMENT '风控类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `id` bigint(20) NOT NULL COMMENT '用户ID',
  `version` int(11) NOT NULL COMMENT '版本',
  `bitState` bigint(20) NOT NULL COMMENT '认证状态',
  `realName` varchar(30) DEFAULT NULL COMMENT '用户名',
  `idNumber` varchar(30) DEFAULT NULL COMMENT '身份证号',
  `phoneNumber` varchar(30) DEFAULT NULL COMMENT '手机号',
  `incomeGrade_id` bigint(20) DEFAULT NULL COMMENT '收入ID',
  `marriage_id` bigint(20) DEFAULT NULL COMMENT '婚姻ID',
  `kidCount_id` bigint(20) DEFAULT NULL COMMENT '子女ID',
  `educationBackground_id` bigint(20) DEFAULT NULL COMMENT '学历ID',
  `authScore` int(11) DEFAULT NULL COMMENT '风控分数',
  `houseCondition_id` bigint(20) DEFAULT NULL COMMENT '住宅ID',
  `realauthid` bigint(20) DEFAULT NULL COMMENT '实名认证ID',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `vedioauth`
-- ----------------------------
DROP TABLE IF EXISTS `vedioauth`;
CREATE TABLE `vedioauth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `state` tinyint(4) NOT NULL COMMENT '审核状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '审核备注',
  `auditTime` datetime DEFAULT NULL COMMENT '审核时间',
  `applyTime` datetime NOT NULL COMMENT '申请时间',
  `auditor_id` bigint(20) DEFAULT NULL COMMENT '审核人ID',
  `applier_id` bigint(20) NOT NULL COMMENT '申请人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
