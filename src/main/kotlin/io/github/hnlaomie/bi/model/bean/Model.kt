/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package io.github.hnlaomie.bi.model.bean

/**
 * 模型定义
 */
data class Model (
    // 模型名
    val name: String,
    // 模型描述
    val description: String = "",
    // 数据源
    val dataSource: String,
    // 事实维度表定义
    val tables: List<DBTable>,
    // 事实维度表关联
    val joins: List<JoinTable> = listOf(),
    // 字段定义
    val fields: List<Field>
)