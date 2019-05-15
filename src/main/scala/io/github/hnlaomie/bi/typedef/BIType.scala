package io.github.hnlaomie.bi.typedef

/**
  * @author <a href="mailto:hnlaomie@163.com">laomie</a>
  * @date 2018-12-29
  */
object BIType {
    // db type for column and table
    val TABLE = "TABLE"
    val VIEW = "VIEW"
    val SQL = "SQL"

    // table partition with year, month, day
    val YEAR = "YEAR"
    val MONTH = "MONTH"
    val DAY = "DAY"

    // fact and dimension
    val FACT = "FACT"
    val DIM_LARGE = "DIM_LARGE"
    val DIM_SMALL = "DIM_SMALL"
}
