## 简介

星型，雪花数据模型查询工具。将数据库物理模型做语义转换为基于度量维度的业务模型，并实现基于度量维度查询业务模型的方式。

### 特性

- 星型雪花模型转为业务模型。
- 基于度量维度查询业务模型。
- 支持基于表达式的自定义度量。
- 支持常用的数据源。

## 使用说明

### 编译打包

```
git clone https://github.com/hnlaomie/dwschema.git
cd dwschema
gradle shadowJar
```

## 参考
- cubes[http://cubes.databrewery.org]
