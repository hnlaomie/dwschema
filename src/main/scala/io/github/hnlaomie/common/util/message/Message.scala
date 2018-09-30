package io.github.hnlaomie.common.util.message

import scala.beans.BeanProperty
import org.apache.commons.lang3.builder.ToStringBuilder

/**
  *
  * message bean class
  *
  * @author <a href="mailto:hnlaomie@163.com">laomie</a>
  * @date 2018-09-30
  */
class Message {
    // message key
    @BeanProperty
    var key: String = _

    // message value
    @BeanProperty
    var value: String = _

    // parameters
    @BeanProperty
    var params: Seq[Object] = _

    // exception
    @BeanProperty
    var exception: Throwable = _

    override def toString: String = ToStringBuilder.reflectionToString(this)
}
