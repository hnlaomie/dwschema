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
package io.github.hnlaomie.bi.query.bean

import io.github.hnlaomie.bi.constant.QueryType

/**
 * 模型查询块
 */
data class Query (
    // 查询类型
    val queryType: QueryType = QueryType.DSL,
    // sql语句
    val sql: String,
    // 查询项
    val selections: List<Item>,
    // 过滤项
    val filters: List<Filter>,
    // 分组过滤项
    val groupFilters: List<Filter>,
    // 排序项
    val orders: List<Order>,
    // 分页
    val limit: Limit
)