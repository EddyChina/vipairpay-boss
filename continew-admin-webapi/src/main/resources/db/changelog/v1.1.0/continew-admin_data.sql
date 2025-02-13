-- liquibase formatted sql

-- changeset Charles7c:1
-- 初始化默认菜单
INSERT IGNORE INTO `sys_menu`
(`id`, `title`, `parent_id`, `type`, `path`, `name`, `component`, `icon`, `is_external`, `is_cache`, `is_hidden`, `permission`, `sort`, `status`, `create_user`, `create_time`, `update_user`, `update_time`)
VALUES
(1050, '公告管理', 1000, 2, '/system/announcement', 'Announcement', 'system/announcement/index', 'advertising', b'0', b'0', b'0', 'system:announcement:list', 5, 1, 1, NOW(), NULL, NULL),
(1051, '公告新增', 1050, 3, NULL, NULL, NULL, NULL, b'0', b'0', b'0', 'system:announcement:add', 1, 1, 1, NOW(), NULL, NULL),
(1052, '公告修改', 1050, 3, NULL, NULL, NULL, NULL, b'0', b'0', b'0', 'system:announcement:update', 2, 1, 1, NOW(), NULL, NULL),
(1053, '公告删除', 1050, 3, NULL, NULL, NULL, NULL, b'0', b'0', b'0', 'system:announcement:delete', 3, 1, 1, NOW(), NULL, NULL),
(1054, '公告导出', 1050, 3, NULL, NULL, NULL, NULL, b'0', b'0', b'0', 'system:announcement:export', 4, 1, 1, NOW(), NULL, NULL),
(2000, '系统工具', 0, 1, '/tool', 'Tool', NULL, 'tool', b'0', b'0', b'0', NULL, 2, 1, 1, NOW(), NULL, NULL),
(2010, '代码生成', 2000, 2, '/tool/generator', 'Generator', 'tool/generator/index', 'code', b'0', b'0', b'0', 'tool:generator:list', 1, 1, 1, NOW(), NULL, NULL);