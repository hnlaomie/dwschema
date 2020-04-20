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
package io.github.hnlaomie.bi.constant;

/**
 * 字段的数据类型
 */
enum class FieldDataType {
    // 整数
    INTEGER,
    // 长整数
    LONG,
    // 小数
    DOUBLE,
    // 大数
    BIGDECIMAL,
    // 字符窜
    STRING,
    // 日期，只有年月日，格式"yyyy-MM-dd"
    DATE,
    // 整数形式的日期，格式"yyyyMMdd"，数据库存为char(8), 查询时按"yyyy-MM-dd"格式传入
    INT_DATE,
    // 日期和时分秒，格式"yyyy-MM-dd hh:mm:ss"
    DATETIME,
    // 整数形式的日期和时分秒，格式"yyyyMMddhhmmss"，数据库存为char(14)，查询时按"yyyy-MM-dd hh:mm:ss"格式传入
    INT_DATETIME,
    // 时间（无日期）
    TIME,
    // 时间戳
    TIMESTAMP
}

