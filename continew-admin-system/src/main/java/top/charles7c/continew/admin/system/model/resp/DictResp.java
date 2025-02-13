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

package top.charles7c.continew.admin.system.model.resp;

import java.io.Serial;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.charles7c.continew.starter.extension.crud.base.BaseResp;

/**
 * 字典信息
 *
 * @author Charles7c
 * @since 2023/9/11 21:29
 */
@Data
@Schema(description = "字典信息")
public class DictResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 字典名称
     */
    @Schema(description = "字典名称", example = "公告类型")
    private String name;

    /**
     * 字典编码
     */
    @Schema(description = "字典编码", example = "announcement_type")
    private String code;

    /**
     * 描述
     */
    @Schema(description = "描述", example = "公告类型描述信息")
    private String description;

    /**
     * 是否为系统内置数据
     */
    @Schema(description = "是否为系统内置数据", example = "true")
    private Boolean isSystem;

    @Override
    public Boolean getDisabled() {
        return this.getIsSystem();
    }
}