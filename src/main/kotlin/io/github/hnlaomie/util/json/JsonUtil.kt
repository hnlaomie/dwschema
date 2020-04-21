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
 *
 * Source copy from "https://github.com/taskbase/arson"
 */
package io.github.hnlaomie.util.json

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.io.Reader
import java.lang.reflect.Type
import java.util.logging.Logger

/**
 * Wrapper for the Json serialization library Gson that performs additional fixes and checks on the deserialized objects.
 */
class JsonUtil(
        private val gson: Gson,
        private val enhancers: List<(o: Any?) -> Any?> = listOf(KotlinReflectionUtils::addDefaultValues),
        private val checks: List<(T: Any?) -> Boolean> = listOf(KotlinReflectionUtils::containsIllegalNullValues),
        private val log: Logger = Logger.getLogger(JsonUtil::class.java.name)
) {
    fun <T> fromJson(json: String, classOfT: Class<T>): T = check(enhance(gson.fromJson<T>(json, classOfT)))
    fun <T> fromJson(json: String, typeOfT: Type): T = check(enhance(gson.fromJson(json, typeOfT)))
    fun <T> fromJson(json: Reader, classOfT: Class<T>): T = check(enhance(gson.fromJson(json, classOfT)))
    fun <T> fromJson(json: Reader, typeOfT: Type): T = check(enhance(gson.fromJson(json, typeOfT)))
    fun <T> fromJson(reader: JsonReader, typeOfT: Type): T = check(enhance(gson.fromJson(reader, typeOfT)))
    fun <T> fromJson(json: JsonElement, classOfT: Class<T>): T = check(enhance(gson.fromJson(json, classOfT)))
    fun <T> fromJson(json: JsonElement, typeOfT: Type): T = check(enhance(gson.fromJson(json, typeOfT)))

    fun toJson(src: Any?): String = gson.toJson(src)
    fun toJson(src: Any?, typeOfSrc: Type): String = gson.toJson(src, typeOfSrc)
    fun toJson(src: Any?, writer: Appendable) = gson.toJson(src, writer)
    fun toJson(src: Any?, typeOfSrc: Type, writer: Appendable) = gson.toJson(src, typeOfSrc, writer)
    fun toJson(src: Any?, typeOfSrc: Type, writer: JsonWriter) = gson.toJson(src, typeOfSrc, writer)
    fun toJson(jsonElement: JsonElement): String = gson.toJson(jsonElement)
    fun toJson(jsonElement: JsonElement, writer: Appendable) = gson.toJson(jsonElement, writer)
    fun toJson(jsonElement: JsonElement, writer: JsonWriter) = gson.toJson(jsonElement, writer)

    private fun <T> enhance(anObject: T): T {
        var o = anObject
        enhancers.forEach { enhancer ->
            @Suppress("UNCHECKED_CAST")
            o = enhancer.invoke(o) as T
        }
        return o
    }

    private fun <T> check(anObject: T): T {
        return if (checks.any { check -> check.invoke(anObject) }) {
            log.warning("Object:\n${gson.toJson(anObject)}\nContains illegal null values.")
            throw IllegalArgumentException("Object:\n${gson.toJson(anObject)}\nContains illegal null values.")
        } else {
            anObject
        }
    }
}

