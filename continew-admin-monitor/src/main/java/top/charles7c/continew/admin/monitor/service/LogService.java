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

package top.charles7c.continew.admin.monitor.service;

import java.util.List;
import java.util.Map;

import top.charles7c.continew.admin.monitor.model.query.LoginLogQuery;
import top.charles7c.continew.admin.monitor.model.query.OperationLogQuery;
import top.charles7c.continew.admin.monitor.model.query.SystemLogQuery;
import top.charles7c.continew.admin.monitor.model.resp.*;
import top.charles7c.continew.starter.extension.crud.model.query.PageQuery;
import top.charles7c.continew.starter.extension.crud.model.resp.PageDataResp;

/**
 * 系统日志业务接口
 *
 * @author Charles7c
 * @since 2022/12/23 20:12
 */
public interface LogService {

    /**
     * 分页查询操作日志列表
     *
     * @param query
     *            查询条件
     * @param pageQuery
     *            分页查询条件
     * @return 操作日志分页信息
     */
    PageDataResp<OperationLogResp> page(OperationLogQuery query, PageQuery pageQuery);

    /**
     * 分页查询登录日志列表
     *
     * @param query
     *            查询条件
     * @param pageQuery
     *            分页查询条件
     * @return 登录日志分页信息
     */
    PageDataResp<LoginLogResp> page(LoginLogQuery query, PageQuery pageQuery);

    /**
     * 分页查询系统日志列表
     *
     * @param query
     *            查询条件
     * @param pageQuery
     *            分页查询条件
     * @return 系统日志分页信息
     */
    PageDataResp<SystemLogResp> page(SystemLogQuery query, PageQuery pageQuery);

    /**
     * 查看系统日志详情
     *
     * @param logId
     *            日志 ID
     * @return 系统日志详情
     */
    SystemLogDetailResp get(Long logId);

    /**
     * 查询仪表盘总计信息
     * 
     * @return 仪表盘总计信息
     */
    DashboardTotalResp getDashboardTotal();

    /**
     * 查询仪表盘访问趋势信息
     *
     * @return 仪表盘访问趋势信息
     */
    List<DashboardAccessTrendResp> listDashboardAccessTrend(Integer days);

    /**
     * 查询仪表盘热门模块列表
     *
     * @return 仪表盘热门模块列表
     */
    List<DashboardPopularModuleResp> listDashboardPopularModule();

    /**
     * 查询仪表盘访客地域分布信息
     *
     * @return 仪表盘访客地域分布信息
     */
    List<Map<String, Object>> listDashboardGeoDistribution();
}
