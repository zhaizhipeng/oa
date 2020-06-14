
# 创建机构表
DROP TABLE IF EXISTS `org_info`;
CREATE TABLE `org_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `org_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '机构ID',
  `org_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '机构名称',
  `father_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '上级机构ID',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_user_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `update_user_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `org_info` VALUES (1, '00', '总公司', '-1', '2020-06-14 15:06:13', '管理员', '2020-06-14 15:06:22', '管理员');
INSERT INTO `org_info` VALUES (2, '0001', '上海子公司', '1', '2020-06-14 15:08:02', '管理员', '2020-06-14 15:08:10', '管理员');
INSERT INTO `org_info` VALUES (3, '0002', '北京子公司', '1', '2020-06-14 15:08:32', '管理员', '2020-06-14 15:08:38', '管理员');
