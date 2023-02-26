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

package top.charles7c.cnadmin.system.model.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 用户和岗位实体
 *
 * @author Charles7c
 * @since 2023/2/25 22:28
 */
@Data
@NoArgsConstructor
@TableName("sys_user_post")
public class UserPostDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户 ID
     */
    private Long userId;

    /**
     * 岗位 ID
     */
    private Long postId;

    public UserPostDO(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }
}