package io.github.hnlaomie.common.util.sysparam

import java.util.{Locale, ResourceBundle}
import scala.util.{Try, Success, Failure}
import io.github.hnlaomie.common.constant.Constants

/**
  *
  * load messages from resource file which in resources directory
  *
  * @author <a href="mailto:hnlaomie@163.com">laomie</a>
  * @date 2018-09-30
  */
object SystemParameters {
    // resource bundle
    private lazy val bundle: ResourceBundle = initBundle();

    /**
      * load messages from resource file
      * @return resource bundle
      */
    private def initBundle(): ResourceBundle = {
        val locale = Locale.getDefault();
        val bundle = ResourceBundle.getBundle(Constants.MSG_CONFIG_FILE, locale)
        return bundle
    }

    /**
      * get string value by key
      * @param key message key
      * @return message value
      */
    def getStringParam(key: String) : String = {
        var value: String = null

        val result = Try(bundle.getObject(key))
        result match {
            case Success(v) => value = v.toString
            case Failure(exception) => value = null
        }

        return value
    }

    /**
      * get int value by key
      * @param key
      * @return
      */
    def getIntParam(key: String) : Int = {
        val strValue = getStringParam(key)
        val value = if (strValue == null) 0 else strValue.toInt
        return value
    }

}
