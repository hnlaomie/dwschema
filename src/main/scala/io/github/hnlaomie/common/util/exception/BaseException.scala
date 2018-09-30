package io.github.hnlaomie.common.util.exception

import org.apache.commons.lang3.StringUtils
import io.github.hnlaomie.common.util.message.{Message, MessageFactory}

/**
  *
  * base exception extends from RuntimeException
  *
  * @author <a href="mailto:hnlaomie@163.com">laomie</a>
  * @date 2018-09-30
  */
class BaseException (
    val errCode: Option[String],
    val msgParams: Option[Seq[String]],
    val cause: Option[Throwable]
) extends RuntimeException (
    errCode match {
        case Some(string) => string
        case None => null
    },
    cause match {
        case Some(throwable) => throwable
        case None => null
    }
) {
    // message object which saved exception message
    var errMessage: Message = initMessage()

    /**
      * init error message from error code, parameters and exception
      * @return
      */
    private def initMessage(): Message = {
        val message = if (msgParams == None) MessageFactory.createMessage(errCode.get)
            else MessageFactory.createMessage(errCode.get, msgParams.get)

        if (cause != None)
            message.setException(cause.get)

        return message
    }

    def this(code: String) = this(Some(code), None, None)

    def this(code: String, exp: Throwable) = this(Some(code), None, Some(exp))

    def this(code: String, params: String*) = this(Some(code), Some(params), None)

    override def toString: String = {
        val lineSeparator = System.lineSeparator
        val sb = new StringBuilder

        sb.append("err_code: " + errMessage.getKey())
        sb.append(" err_message: ")
        sb.append(errMessage.getValue() + lineSeparator)
        if (errMessage.getException() != null) {
            sb.append(errMessage.getException().getMessage() + lineSeparator)
            val causeMsg = StringUtils.join(errMessage.getException().getStackTrace(), lineSeparator)
            sb.append(causeMsg)
        }

        return sb.toString();
    }

}
