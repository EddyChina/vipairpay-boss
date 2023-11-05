/*
 * Copyright (c) 2022-present Charles7c Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package top.charles7c.cnadmin.system.model.resp;

import java.io.Serial;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;

import top.charles7c.cnadmin.common.annotation.TreeField;
import top.charles7c.cnadmin.common.base.BaseResp;
import top.charles7c.cnadmin.common.config.easyexcel.ExcelBaseEnumConverter;
import top.charles7c.cnadmin.common.enums.DisEnableStatusEnum;
import top.charles7c.cnadmin.common.enums.MenuTypeEnum;

/**
 * 菜单信息
 *
 * @author Charles7c
 * @since 2023/2/15 20:23
 */
@Data
@TreeField(value = "id")
@ExcelIgnoreUnannotated
@Schema(description = "菜单信息")
public class MenuResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 菜单标题
     */
    @Schema(description = "菜单标题", example = "用户管理")
    @ExcelProperty(value = "菜单标题")
    private String title;

    /**
     * 菜单图标
     */
    @Schema(description = "菜单图标", example = "user")
    @ExcelProperty(value = "菜单图标")
    private String icon;

    /**
     * 菜单排序
     */
    @Schema(description = "菜单排序", example = "1")
    @ExcelProperty(value = "菜单排序")
    private Integer sort;

    /**
     * 权限标识
     */
    @Schema(description = "权限标识", example = "system:user:list")
    @ExcelProperty(value = "权限标识")
    private String permission;

    /**
     * 组件路径
     */
    @Schema(description = "组件路径", example = "/system/user/index")
    @ExcelProperty(value = "组件路径")
    private String component;

    /**
     * 状态
     */
    @Schema(description = "状态（1：启用；2：禁用）", type = "Integer", allowableValues = {"1", "2"}, example = "1")
    @ExcelProperty(value = "状态", converter = ExcelBaseEnumConverter.class)
    private DisEnableStatusEnum status;

    /**
     * 是否外链
     */
    @Schema(description = "是否外链", example = "false")
    @ExcelProperty(value = "是否外链")
    private Boolean isExternal;

    /**
     * 是否缓存
     */
    @Schema(description = "是否缓存", example = "false")
    @ExcelProperty(value = "是否缓存")
    private Boolean isCache;

    /**
     * 是否隐藏
     */
    @Schema(description = "是否隐藏", example = "false")
    @ExcelProperty(value = "是否隐藏")
    private Boolean isHidden;

    /**
     * 路由地址
     */
    @Schema(description = "路由地址", example = "/system/user")
    @ExcelProperty(value = "路由地址")
    private String path;

    /**
     * 组件名称
     */
    @Schema(description = "组件名称", example = "User")
    @ExcelProperty(value = "组件名称")
    private String name;

    /**
     * 菜单类型
     */
    @Schema(description = "菜单类型（1：目录；2：菜单；3：按钮）", type = "Integer", allowableValues = {"1", "2", "3"}, example = "2")
    @ExcelProperty(value = "菜单类型", converter = ExcelBaseEnumConverter.class)
    private MenuTypeEnum type;

    /**
     * 上级菜单 ID
     */
    @Schema(description = "上级菜单 ID", example = "1000")
    private Long parentId;
}