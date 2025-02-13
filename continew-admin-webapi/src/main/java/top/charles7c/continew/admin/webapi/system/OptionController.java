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

package top.charles7c.continew.admin.webapi.system;

import java.util.List;

import lombok.RequiredArgsConstructor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cn.dev33.satoken.annotation.SaCheckPermission;

import top.charles7c.continew.admin.system.model.query.OptionQuery;
import top.charles7c.continew.admin.system.model.req.OptionReq;
import top.charles7c.continew.admin.system.model.req.OptionResetValueReq;
import top.charles7c.continew.admin.system.model.resp.OptionResp;
import top.charles7c.continew.admin.system.service.OptionService;
import top.charles7c.continew.starter.extension.crud.model.resp.R;

/**
 * 参数管理 API
 *
 * @author Bull-BCLS
 * @since 2023/8/26 19:38
 */
@Tag(name = "参数管理 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/option")
public class OptionController {

    private final OptionService optionService;

    @Operation(summary = "查询参数列表", description = "查询参数列表")
    @SaCheckPermission("system:config:list")
    @GetMapping
    public R<List<OptionResp>> list(@Validated OptionQuery query) {
        return R.ok(optionService.list(query));
    }

    @Operation(summary = "修改参数", description = "修改参数")
    @SaCheckPermission("system:config:update")
    @PatchMapping
    public R update(@Validated @RequestBody List<OptionReq> req) {
        optionService.update(req);
        return R.ok();
    }

    @Operation(summary = "重置参数", description = "重置参数")
    @SaCheckPermission("system:config:reset")
    @PatchMapping("/value")
    public R resetValue(@Validated @RequestBody OptionResetValueReq req) {
        optionService.resetValue(req);
        return R.ok();
    }
}