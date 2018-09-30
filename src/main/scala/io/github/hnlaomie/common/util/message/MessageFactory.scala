package io.github.hnlaomie.common.util.message

import io.github.hnlaomie.common.util.sysparam.SystemParameters

/**
  *
  * message creator factory
  *
  * @author <a href="mailto:hnlaomie@163.com">laomie</a>
  * @date 2018-09-30
  */
object MessageFactory {

    /**
      * get value which replaced with real parameters
      * @param value defined in property file
      * @param params replace parameters defined in property file
      * @return
      */
    private def getValue(value: String, params: Object*): String = {
        var result = value
        if (params.length > 0) {
            for (i <- params.indices) {
                val key = "{" + i.toString + "}"
                val param = if (params(i) == null) "" else params(i).toString
                result = value.replaceAll(key, param)
            }
        }

        return result
    }

    /**
      * create message with parameters
      * @param key message key
      * @return message with key and value
      */
    def createMessage(key: String, params: Object*): Message = {
        val result = new Message

        val value = SystemParameters.getStringParam(key)
        val realValue = if (params.length == 0) value else getValue(value, params)

        result.setKey(key)
        result.setValue(value)
        return result
    }
}