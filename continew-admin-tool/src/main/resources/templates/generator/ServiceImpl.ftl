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

package ${packageName}.${subPackageName};

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import top.charles7c.cnadmin.common.base.BaseServiceImpl;
import ${packageName}.mapper.${classNamePrefix}Mapper;
import ${packageName}.model.entity.${classNamePrefix}DO;
import ${packageName}.model.query.${classNamePrefix}Query;
import ${packageName}.model.request.${classNamePrefix}Request;
import ${packageName}.model.vo.${classNamePrefix}DetailVO;
import ${packageName}.model.vo.${classNamePrefix}VO;
import ${packageName}.service.${classNamePrefix}Service;

/**
 * ${businessName}业务实现
 *
 * @author ${author}
 * @since ${date}
 */
@Service
@RequiredArgsConstructor
public class ${className} extends BaseServiceImpl<${classNamePrefix}Mapper, ${classNamePrefix}DO, ${classNamePrefix}VO, ${classNamePrefix}DetailVO, ${classNamePrefix}Query, ${classNamePrefix}Request> implements ${classNamePrefix}Service {}