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

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RestController;

import top.charles7c.continew.admin.system.model.query.RoleQuery;
import top.charles7c.continew.admin.system.model.req.RoleReq;
import top.charles7c.continew.admin.system.model.resp.RoleDetailResp;
import top.charles7c.continew.admin.system.model.resp.RoleResp;
import top.charles7c.continew.admin.system.service.RoleService;
import top.charles7c.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.charles7c.continew.starter.extension.crud.base.BaseController;

/**
 * 角色管理 API
 *
 * @author Charles7c
 * @since 2023/2/8 23:11
 */
@Tag(name = "角色管理 API")
@RestController
@CrudRequestMapping("/system/role")
public class RoleController extends BaseController<RoleService, RoleResp, RoleDetailResp, RoleQuery, RoleReq> {}
