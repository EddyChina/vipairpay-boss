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
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import top.charles7c.continew.admin.common.util.helper.LoginHelper;
import top.charles7c.continew.admin.system.model.query.MessageQuery;
import top.charles7c.continew.admin.system.model.resp.MessageResp;
import top.charles7c.continew.admin.system.model.resp.MessageUnreadResp;
import top.charles7c.continew.admin.system.service.MessageService;
import top.charles7c.continew.admin.system.service.MessageUserService;
import top.charles7c.continew.starter.extension.crud.model.query.PageQuery;
import top.charles7c.continew.starter.extension.crud.model.resp.PageDataResp;
import top.charles7c.continew.starter.extension.crud.model.resp.R;
import top.charles7c.continew.starter.log.common.annotation.Log;

/**
 * 消息管理 API
 *
 * @author Bull-BCLS
 * @since 2023/10/15 19:05
 */
@Tag(name = "消息管理 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/message")
public class MessageController {

    private final MessageService baseService;
    private final MessageUserService messageUserService;

    @Operation(summary = "分页查询列表", description = "分页查询列表")
    @GetMapping
    public R<PageDataResp<MessageResp>> page(MessageQuery query, @Validated PageQuery pageQuery) {
        query.setUserId(LoginHelper.getUserId());
        PageDataResp<MessageResp> pageData = baseService.page(query, pageQuery);
        return R.ok(pageData);
    }

    @Operation(summary = "删除数据", description = "删除数据")
    @Parameter(name = "ids", description = "ID 列表", example = "1,2", in = ParameterIn.PATH)
    @DeleteMapping("/{ids}")
    public R delete(@PathVariable List<Long> ids) {
        baseService.delete(ids);
        return R.ok("删除成功");
    }

    @Operation(summary = "标记已读", description = "将消息标记为已读状态")
    @Parameter(name = "ids", description = "消息ID列表", example = "1,2", in = ParameterIn.QUERY)
    @PatchMapping("/read")
    public R readMessage(@RequestParam(required = false) List<Long> ids) {
        messageUserService.readMessage(ids);
        return R.ok();
    }

    @Log(ignore = true)
    @Operation(summary = "查询未读消息数量", description = "查询当前用户的未读消息数量")
    @Parameter(name = "isDetail", description = "是否查询详情", example = "true", in = ParameterIn.QUERY)
    @GetMapping("/unread")
    public R<MessageUnreadResp> countUnreadMessage(@RequestParam(required = false) Boolean detail) {
        return R.ok(messageUserService.countUnreadMessageByUserId(LoginHelper.getUserId(), detail));
    }
}