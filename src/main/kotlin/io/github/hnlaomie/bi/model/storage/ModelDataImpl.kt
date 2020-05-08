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

class ModelDataImpl(val model: Model): ModelData {
    // 表树根结点
    private val root = initTree(model)
    // 关系相关索引<父表名-子表名，关系对象>
    private val relationIndex = initRelation(model)
    // 表索引<表名, 表对象>
    private val tableIndex = initTable(model)
    // 列相关索引<表名-列名, 列对象>
    private val columnIndex = initColumn(model)
    // 字段相关索引<字段名, 字段对象>
    private val fieldIndex = initField(model)

    /**
     * 将模型[model]的表关系初始化
     */
    private fun initRelation(model: Model): Map<String, JoinTable> {
        val index = mutableMapOf<String, JoinTable>()
        for (relation in model.joins) {
            val parentTable = relation.parent
            val childTable = relation.child
            val key = "$parentTable-$childTable"
            index[key] = relation
        }
        return index
    }

    /**
     * 为模型[model]的表建索引
     */
    private fun initTable(model: Model): Map<String, DBTable> {
        val index = mutableMapOf<String, DBTable>()
        for (table in model.tables) {
            val tableName = table.name
            index[tableName] = table
        }
        return index
    }

    /**
     * 为模型[model]的列建索引
     */
    private fun initColumn(model: Model): Map<String, DBColumn> {
        val index = mutableMapOf<String, DBColumn>()
        for (table in model.tables) {
            val tableName = table.name
            for (column in table.columns) {
                val columnName = column.name
                val key = "$tableName-$columnName"
                index[key] = column
            }
        }
        return index
    }

    /**
     * 为模型[model]的字段建索引
     */
    private fun initField(model: Model): Map<String, Field> {
        val index = mutableMapOf<String, Field>()
        for (field in model.fields) {
            val fieldName = field.name
            index[fieldName] = field
        }
        return index
    }

    /**
     * 取得表结点树[nodeMap]的根结点
     */
    private fun getRoot(nodeMap: Map<String, TreeNode<DBTable>>): TreeNode<DBTable>? {
        var root: TreeNode<DBTable>? = null

        val parentSet = mutableSetOf<String>()
        val childSet = mutableSetOf<String>()

        // 生成父子结点集合
        for (key in nodeMap.keys) {
            val node = nodeMap[key]
            if (node != null) {
                val parentName = node.element.name
                parentSet.add(parentName)

                for (childNode in node.children) {
                    val childName = childNode.element.name
                    childSet.add(childName)
                }
            }
        }

        // 从父结点集合中移去在子结点集合中存在的表
        for (childTable in childSet) {
            if (childTable in parentSet) {
                parentSet.remove(childTable)
            }
        }

        /*
         * TODO:
         *   1. 父结点集合为空则抛出“无事实表”异常
         *   2. 父结点集合有多个元素则抛出“有多个事实表”异常
         */
        if (parentSet.size == 1) {
            for (tableName in parentSet) {
                root = nodeMap[tableName] ?: error("")
            }
        }

        return root
    }

    /**
     * 将模型[model]的表初始化为树结构
     */
    private fun initTree(model: Model): TreeNode<DBTable>? {
        var root: TreeNode<DBTable>? = null

        if (model != null) {
            // 树结点的索引<表名,树结点>
            val nodeMap = mutableMapOf<String, TreeNode<DBTable>>()
            for(table in model.tables) {
                val tableName = table.name
                val node = TreeNode(table)
                nodeMap[tableName] = node
            }

            for(joinTable in model.joins) {
                val parentTable = joinTable.parent
                val childTable = joinTable.child
                val parent = nodeMap[parentTable]
                val child = nodeMap[childTable]
                if (parent != null && child != null) {
                    val pNode = child.parent
                    if (pNode != null) {
                        // 多个父表，抛异常
                        if (parent != pNode) {
                            //TODO: throw exception
                        }
                        continue
                    } else {
                        // 设置父子关系
                        parent.addChild(child)
                    }
                }
            }

            root = getRoot(nodeMap)
        }

        return root

    }

    override fun release() {
        if (root != null) {
            // 遍历树，释放结点
        }
    }

    override fun getRelation(parentTable: String, childTable: String): JoinTable? {
        val key = "$parentTable-$childTable"
        return this.relationIndex.get(key)
    }

    override fun getTable(tableName: String): DBTable? {
        return this.tableIndex.get(tableName)
    }

    override fun getColumn(tableName: String, columnName: String): DBColumn? {
        val key = "$tableName-$columnName"
        return this.columnIndex.get(key)
    }

    override fun getField(fieldName: String): Field? {
        return this.fieldIndex.get(fieldName)
    }

}