package com.hl.p2p.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {

  /**
   * 获取异常的堆栈信息
   *
   * @param e
   * @return
   */
  public static String getStackTrace(Throwable e) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);

    try {
      //将异常信息输出在控制台
      e.printStackTrace(pw);
      //将异常信息返回
      return sw.toString();
    } finally {
      pw.close();
    }
  }
}
