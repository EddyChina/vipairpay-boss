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

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.util.ObjectUtil;

import top.charles7c.continew.admin.system.model.query.MenuQuery;
import top.charles7c.continew.admin.system.model.req.MenuReq;
import top.charles7c.continew.admin.system.model.resp.MenuResp;
import top.charles7c.continew.admin.system.service.MenuService;
import top.charles7c.continew.starter.core.util.URLUtils;
import top.charles7c.continew.starter.core.util.validate.ValidationUtils;
import top.charles7c.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.charles7c.continew.starter.extension.crud.base.BaseController;
import top.charles7c.continew.starter.extension.crud.base.ValidateGroup;
import top.charles7c.continew.starter.extension.crud.enums.Api;
import top.charles7c.continew.starter.extension.crud.model.resp.R;

/**
 * 菜单管理 API
 *
 * @author Charles7c
 * @since 2023/2/15 20:35
 */
@Tag(name = "菜单管理 API")
@RestController
@CrudRequestMapping(value = "/system/menu", api = {Api.TREE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class MenuController extends BaseController<MenuService, MenuResp, MenuResp, MenuQuery, MenuReq> {

    @Override
    @SaCheckPermission("system:menu:add")
    public R<Long> add(@Validated(ValidateGroup.Crud.Add.class) @RequestBody MenuReq req) {
        this.checkPath(req);
        return super.add(req);
    }

    @Override
    @SaCheckPermission("system:menu:update")
    public R update(@Validated(ValidateGroup.Crud.Update.class) @RequestBody MenuReq req, @PathVariable Long id) {
        this.checkPath(req);
        return super.update(req, id);
    }

    /**
     * 检查路由地址格式
     * 
     * @param req
     *            创建或修改信息
     */
    private void checkPath(MenuReq req) {
        Boolean isExternal = ObjectUtil.defaultIfNull(req.getIsExternal(), false);
        String path = req.getPath();
        ValidationUtils.throwIf(isExternal && !URLUtils.isHttpUrl(path), "路由地址格式错误，请以 http:// 或 https:// 开头");
        ValidationUtils.throwIf(!isExternal && URLUtils.isHttpUrl(path), "路由地址格式错误");
    }
}
