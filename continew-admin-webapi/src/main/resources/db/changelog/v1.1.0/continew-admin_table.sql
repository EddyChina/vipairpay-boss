-- liquibase formatted sql

-- changeset Charles7c:1
CREATE TABLE IF NOT EXISTS `gen_config` (
    `table_name`    varchar(64)                        COMMENT '表名称',
    `module_name`   varchar(60)  NOT NULL              COMMENT '模块名称',
    `package_name`  varchar(60)  NOT NULL              COMMENT '包名称',
    `frontend_path` varchar(255) DEFAULT NULL          COMMENT '前端路径',
    `business_name` varchar(50)  NOT NULL              COMMENT '业务名称',
    `author`        varchar(100) NOT NULL              COMMENT '作者',
    `table_prefix`  varchar(20)  DEFAULT NULL          COMMENT '表前缀',
    `is_override`   bit(1)       NOT NULL DEFAULT b'0' COMMENT '是否覆盖',
    `create_time`   datetime     NOT NULL              COMMENT '创建时间',
    `update_time`   datetime     DEFAULT NULL          COMMENT '修改时间',
    PRIMARY KEY (`table_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='生成配置表';

CREATE TABLE IF NOT EXISTS `gen_field_config` (
    `table_name`    varchar(64)  NOT NULL              COMMENT '表名称',
    `column_name`   varchar(64)  NOT NULL              COMMENT '列名称',
    `column_type`   varchar(25)  NOT NULL              COMMENT '列类型',
    `field_name`    varchar(64)  NOT NULL              COMMENT '字段名称',
    `field_type`    varchar(25)  NOT NULL              COMMENT '字段类型',
    `comment`       varchar(512) DEFAULT NULL          COMMENT '注释',
    `is_required`   bit(1)       NOT NULL DEFAULT b'1' COMMENT '是否必填',
    `show_in_list`  bit(1)       NOT NULL DEFAULT b'1' COMMENT '是否在列表中显示',
    `show_in_form`  bit(1)       NOT NULL DEFAULT b'1' COMMENT '是否在表单中显示',
    `show_in_query` bit(1)       NOT NULL DEFAULT b'1' COMMENT '是否在查询中显示',
    `form_type`     tinyint(1)   UNSIGNED DEFAULT NULL COMMENT '表单类型',
    `query_type`    tinyint(1)   UNSIGNED DEFAULT NULL COMMENT '查询方式',
    `create_time`   datetime NOT NULL COMMENT '创建时间',
    INDEX `idx_table_name`(`table_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字段配置表';

CREATE TABLE IF NOT EXISTS `sys_announcement` (
    `id`             bigint(20)   AUTO_INCREMENT       COMMENT 'ID',
    `title`          varchar(150) NOT NULL             COMMENT '标题',
    `content`        mediumtext   NOT NULL             COMMENT '内容',
    `type`           varchar(30)  NOT NULL             COMMENT '类型',
    `effective_time` datetime     DEFAULT NULL         COMMENT '生效时间',
    `terminate_time` datetime     DEFAULT NULL         COMMENT '终止时间',
    `sort`           int          NOT NULL DEFAULT 999 COMMENT '排序',
    `create_user`    bigint(20)   NOT NULL             COMMENT '创建人',
    `create_time`    datetime     NOT NULL             COMMENT '创建时间',
    `update_user`    bigint(20)   DEFAULT NULL         COMMENT '修改人',
    `update_time`    datetime     DEFAULT NULL         COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_create_user`(`create_user`) USING BTREE,
    INDEX `idx_update_user`(`update_user`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公告表';