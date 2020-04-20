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

import io.github.hnlaomie.bi.constant.FieldDataType
import io.github.hnlaomie.bi.constant.FieldType

/**
 * 模型字段定义
 */
data class Field (
    // 对应表名
    val tableName: String,
    // 对应列名
    val columnName: String,
    // 字段名
    val name: String = columnName,
    // 字段类型
    val type: FieldType = FieldType.GROUP,
    // 字段值类型
    val dataType: FieldDataType = FieldDataType.STRING,
    // 子字段列表
    val fields: List<Field>
)