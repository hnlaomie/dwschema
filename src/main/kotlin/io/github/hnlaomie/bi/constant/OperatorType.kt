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
package io.github.hnlaomie.bi.constant

/**
 * 操作符的枚举、构造方法的参数第一个为 实际操作符、第二个为给操作符类别，分别代表一元、二元、多元等。
 */
enum class OperatorType(value: Int) {
    LT(2),
    LE(2),
    GT(2),
    GE(2),
    EQ(2),
    NE(2),
    LIKE(2),
    NOT_LIKE(2),
    IS_NULL(1),
    IS_NOT_NULL(1),
    AND(0),
    OR(0),
    BETWEEN(3),
    NOT_BETWEEN(3),
    IN(4),
    NOT_IN(4)
}