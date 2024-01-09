--
-- Table structure for table `order`
--
DROP TABLE IF EXISTS `order`;

CREATE TABLE `recharge_order_01` (
        `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
        `merchno` varchar(50) NULL DEFAULT NULL COMMENT '商戶号',
        `orderId` varchar(50) NULL DEFAULT NULL COMMENT '訂單編号',
        `payType` varchar(50) NULL DEFAULT NULL COMMENT '支付类型',
        `amount` double(20,3) DEFAULT '0.000' COMMENT '充值金额',
        `asyncUrl` varchar(50) NULL DEFAULT NULL COMMENT '异步回调地址',
        `syncUrl` varchar(50) NULL DEFAULT NULL COMMENT '同步回调地址',
        `requestTime` timestamp NULL DEFAULT NULL COMMENT '添加时间',
        `attach` varchar(50) NULL DEFAULT NULL COMMENT '',
        `requestCurrency` varchar(50) NULL DEFAULT NULL COMMENT '请求货币（1：人民币；2：USDT；3：越南盾；4：印度卢比）',
        `apiVersion` varchar(50) NULL DEFAULT NULL COMMENT '版本号',
        PRIMARY KEY (`id`)
        ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='充值订单表';
