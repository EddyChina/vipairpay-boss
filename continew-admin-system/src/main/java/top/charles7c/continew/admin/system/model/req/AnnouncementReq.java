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
import java.time.LocalDateTime;

import jakarta.validation.constraints.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.validator.constraints.Length;

import top.charles7c.continew.starter.extension.crud.base.BaseReq;

/**
 * 创建或修改公告信息
 *
 * @author Charles7c
 * @since 2023/8/20 10:55
 */
@Data
@Schema(description = "创建或修改公告信息")
public class AnnouncementReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    @Schema(description = "标题", example = "这是公告标题")
    @NotBlank(message = "标题不能为空")
    @Length(max = 150, message = "标题长度不能超过 {max} 个字符")
    private String title;

    /**
     * 内容
     */
    @Schema(description = "内容", example = "这是公告内容")
    @NotBlank(message = "内容不能为空")
    private String content;

    /**
     * 类型（取值于字典 announcement_type）
     */
    @Schema(description = "类型（取值于字典 announcement_type）", example = "1")
    @NotBlank(message = "类型不能为空")
    @Length(max = 30, message = "类型长度不能超过 {max} 个字符")
    private String type;

    /**
     * 生效时间
     */
    @Schema(description = "生效时间", example = "2023-08-08 00:00:00", type = "string")
    private LocalDateTime effectiveTime;

    /**
     * 终止时间
     */
    @Schema(description = "终止时间", example = "2023-08-08 23:59:59", type = "string")
    @Future(message = "终止时间必须是未来时间")
    private LocalDateTime terminateTime;
}