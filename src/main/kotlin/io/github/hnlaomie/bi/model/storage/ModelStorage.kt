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

import io.github.hnlaomie.bi.model.bean.Model

object ModelStorage {
    // 模型缓存
    val modelMap = mutableMapOf<String, ModelData>()

    /**
     * 根据模型名[modelName]，从缓存中获取模型
     */
    fun get(modelName: String): ModelData? {
        return modelMap[modelName]
    }

    /**
     * 缓存模型
     */
    fun add(modelName: String, model: Model) {
        if (model != null) {
            val modelData = ModelDataImpl(model)
            modelMap[modelName] = modelData
        }
    }

    /**
     * 从缓存中删除模型
     */
    fun remove(modelName: String) {
        if (modelMap.containsKey(modelName)) {
            val modelData = modelMap[modelName]
            modelData?.release()
            modelMap.remove(modelName)
        }
    }
}