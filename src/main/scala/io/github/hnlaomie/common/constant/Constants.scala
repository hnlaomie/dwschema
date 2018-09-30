package io.github.hnlaomie.common.constant;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;

/**
  * 常量定义
  *
  * @author <a href="mailto:hnlaomie@163.com">laomie</a>
  * @date 2018-09-30
  */
object Constants {
    // 文件编码
    val CHARSET : Charset = StandardCharsets.UTF_8;
    val ENCODING = "UTF-8";

    val MSG_CONFIG_FILE = "config.log_messages";

    // 日期显示格式
    val DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

}