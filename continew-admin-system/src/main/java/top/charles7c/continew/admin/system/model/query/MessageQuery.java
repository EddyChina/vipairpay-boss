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

package top.charles7c.continew.admin.system.model.query;

import java.io.Serializable;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.charles7c.continew.starter.data.mybatis.plus.query.Query;
import top.charles7c.continew.starter.data.mybatis.plus.query.QueryType;

/**
 * 消息查询条件
 *
 * @author Bull-BCLS
 * @since 2023/10/15 19:05
 */
@Data
@Schema(description = "消息查询条件")
public class MessageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Schema(description = "ID", example = "1")
    @Query
    private Long id;

    /**
     * 标题
     */
    @Schema(description = "标题", example = "欢迎注册 xxx")
    @Query(type = QueryType.INNER_LIKE)
    private String title;

    /**
     * 类型
     */
    @Schema(description = "类型（1：系统消息）", example = "1")
    @Query
    private Integer type;

    /**
     * 是否已读
     */
    @Schema(description = "是否已读", example = "true")
    private Boolean isRead;

    /**
     * 用户 ID
     */
    @Schema(hidden = true)
    private Long userId;
}