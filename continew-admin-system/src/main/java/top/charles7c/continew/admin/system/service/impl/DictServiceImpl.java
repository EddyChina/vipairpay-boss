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

package top.charles7c.continew.admin.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.charles7c.continew.admin.system.mapper.DictMapper;
import top.charles7c.continew.admin.system.model.entity.DictDO;
import top.charles7c.continew.admin.system.model.query.DictQuery;
import top.charles7c.continew.admin.system.model.req.DictReq;
import top.charles7c.continew.admin.system.model.resp.DictDetailResp;
import top.charles7c.continew.admin.system.model.resp.DictItemDetailResp;
import top.charles7c.continew.admin.system.model.resp.DictResp;
import top.charles7c.continew.admin.system.service.DictItemService;
import top.charles7c.continew.admin.system.service.DictService;
import top.charles7c.continew.starter.core.util.validate.CheckUtils;
import top.charles7c.continew.starter.extension.crud.base.BaseServiceImpl;
import top.charles7c.continew.starter.extension.crud.model.query.SortQuery;
import top.charles7c.continew.starter.file.excel.util.ExcelUtils;

/**
 * 字典业务实现
 *
 * @author Charles7c
 * @since 2023/9/11 21:29
 */
@Service
@RequiredArgsConstructor
public class DictServiceImpl extends BaseServiceImpl<DictMapper, DictDO, DictResp, DictDetailResp, DictQuery, DictReq>
    implements DictService {

    private final DictItemService dictItemService;

    @Override
    public Long add(DictReq req) {
        String name = req.getName();
        CheckUtils.throwIf(this.isNameExists(name, null), "新增失败，[{}] 已存在", name);
        String code = req.getCode();
        CheckUtils.throwIf(this.isCodeExists(code, null), "新增失败，[{}] 已存在", code);
        return super.add(req);
    }

    @Override
    public void update(DictReq req, Long id) {
        String name = req.getName();
        CheckUtils.throwIf(this.isNameExists(name, id), "修改失败，[{}] 已存在", name);
        String code = req.getCode();
        CheckUtils.throwIf(this.isCodeExists(code, id), "修改失败，[{}] 已存在", code);
        DictDO oldDict = super.getById(id);
        if (oldDict.getIsSystem()) {
            CheckUtils.throwIfNotEqual(req.getCode(), oldDict.getCode(), "[{}] 是系统内置字典，不允许修改字典编码", oldDict.getName());
        }
        super.update(req, id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> ids) {
        List<DictDO> list =
            baseMapper.lambdaQuery().select(DictDO::getName, DictDO::getIsSystem).in(DictDO::getId, ids).list();
        Optional<DictDO> isSystemData = list.stream().filter(DictDO::getIsSystem).findFirst();
        CheckUtils.throwIf(isSystemData::isPresent, "所选字典 [{}] 是系统内置字典，不允许删除",
            isSystemData.orElseGet(DictDO::new).getName());
        dictItemService.deleteByDictIds(ids);
        super.delete(ids);
    }

    @Override
    public void export(DictQuery query, SortQuery sortQuery, HttpServletResponse response) {
        List<DictResp> dictList = this.list(query, sortQuery);
        List<DictItemDetailResp> dictItemList = new ArrayList<>();
        for (DictResp dict : dictList) {
            List<DictItemDetailResp> tempDictItemList = dictItemService.listByDictId(dict.getId());
            for (DictItemDetailResp dictItem : tempDictItemList) {
                dictItem.setDictName(dict.getName());
                dictItem.setDictCode(dict.getCode());
                dictItemList.add(dictItem);
            }
        }
        ExcelUtils.export(dictItemList, "导出数据", DictItemDetailResp.class, response);
    }

    /**
     * 名称是否存在
     *
     * @param name
     *            名称
     * @param id
     *            ID
     * @return 是否存在
     */
    private boolean isNameExists(String name, Long id) {
        return baseMapper.lambdaQuery().eq(DictDO::getName, name).ne(null != id, DictDO::getId, id).exists();
    }

    /**
     * 编码是否存在
     *
     * @param code
     *            编码
     * @param id
     *            ID
     * @return 是否存在
     */
    private boolean isCodeExists(String code, Long id) {
        return baseMapper.lambdaQuery().eq(DictDO::getCode, code).ne(null != id, DictDO::getId, id).exists();
    }
}