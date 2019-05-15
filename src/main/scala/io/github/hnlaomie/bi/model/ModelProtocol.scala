package io.github.hnlaomie.bi.model

import spray.json._
import DefaultJsonProtocol._
import JsonReader._
import io.github.hnlaomie.bi.typedef.BIType

/**
  * @author <a href="mailto:hnlaomie@163.com">laomie</a>
  * @date 2018-12-29
  */

case class DBColumn
(
    name: String,
    dbName: Option[String],
    // TABLE, VIEW, SQL
    dbType: Option[String],
    javaType: Option[String],
    sql: Option[String]
)

case class DBTable
(
    name: String,
    dbName: Option[String],
    // TABLE, VIEW, SQL
    dbType: Option[String],
    // FACT, DIM_LARGE, DIM_SMALL
    tableType: Option[String],
    sql: Option[String],
    columns: List[DBColumn]
)

object ModelProtocol extends DefaultJsonProtocol {
    implicit val dbColumnFormat = jsonFormat5(DBColumn)
    implicit val dbTableFormat = jsonFormat6(DBTable)
}
