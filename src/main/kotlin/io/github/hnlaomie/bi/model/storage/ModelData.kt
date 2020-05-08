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
package io.github.hnlaomie.bi.model.storage

import io.github.hnlaomie.bi.model.bean.*

interface ModelData {
    /**
     * 获取父表[parentTable]和子表[childTable]的表连接
     */
    fun getRelation(parentTable: String, childTable: String): JoinTable?

    /**
     * 根据表名[tableName]获取表对象
     */
    fun getTable(tableName: String): DBTable?

    /**
     * 根据表名[tableName]和列名[columnName]获取列对象
     */
    fun getColumn(tableName: String, columnName: String): DBColumn?

    /**
     * 从字段名[fieldName]获取字段对象
     */
    fun getField(fieldName: String): Field?

    /**
     * 释放资源
     */
    fun release()
}