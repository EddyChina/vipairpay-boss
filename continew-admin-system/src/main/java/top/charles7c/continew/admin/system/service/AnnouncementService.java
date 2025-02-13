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

package top.charles7c.continew.admin.system.service;

import java.util.List;

import top.charles7c.continew.admin.system.model.query.AnnouncementQuery;
import top.charles7c.continew.admin.system.model.req.AnnouncementReq;
import top.charles7c.continew.admin.system.model.resp.AnnouncementDetailResp;
import top.charles7c.continew.admin.system.model.resp.AnnouncementResp;
import top.charles7c.continew.admin.system.model.resp.DashboardAnnouncementResp;
import top.charles7c.continew.starter.extension.crud.base.BaseService;

/**
 * 公告业务接口
 *
 * @author Charles7c
 * @since 2023/8/20 10:55
 */
public interface AnnouncementService
    extends BaseService<AnnouncementResp, AnnouncementDetailResp, AnnouncementQuery, AnnouncementReq> {

    /**
     * 查询仪表盘公告列表
     *
     * @return 仪表盘公告列表
     */
    List<DashboardAnnouncementResp> listDashboard();
}