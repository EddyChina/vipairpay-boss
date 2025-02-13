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

package top.charles7c.continew.admin.system.model.req;

import java.io.Serial;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.validator.constraints.Length;

import top.charles7c.continew.starter.extension.crud.base.BaseReq;

/**
 * 修改参数信息
 *
 * @author Bull-BCLS
 * @since 2023/8/26 19:38
 */
@Data
@Schema(description = "修改参数信息")
public class OptionReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 参数键
     */
    @Schema(description = "参数键", example = "site_title")
    @NotBlank(message = "参数键不能为空")
    @Length(max = 100, message = "参数键长度不能超过 {max} 个字符")
    private String code;

    /**
     * 参数值
     */
    @Schema(description = "参数值", example = "ContiNew Admin")
    @NotBlank(message = "参数值不能为空")
    private String value;
}