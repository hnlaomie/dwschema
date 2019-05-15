package test

import io.github.hnlaomie.common.constant.MessageID
import io.github.hnlaomie.common.util.exception.BaseException
import io.github.hnlaomie.common.util.message.MessageFactory
import io.github.hnlaomie.common.util.sysparam.SystemParameters
import spray.json._
import io.github.hnlaomie.bi.model.ModelProtocol._
import JsonReader._
import io.github.hnlaomie.bi.model.DBTable

/**
  *
  *
  *
  * @author <a href="mailto:hnlaomie@163.com">laomie</a>
  * @date 2018-09-30
  */
object Test {
    def main(args: Array[String]) {
        val beginTime = System.currentTimeMillis()
        val exp = new BaseException(MessageID.MSG_000, "a", "b")
        println(exp)
        val endTime = System.currentTimeMillis()
        println("处理用时：" + (endTime - beginTime) + "毫秒")

        val strJson = s"""{"name" : "test", "columns" : [{"name": "col1", "dbType": "TABLE"}]}"""
        val jsonAst = strJson.parseJson
        val table = jsonAst.convertTo[DBTable]

        println(table)

    }
}
