package com.hl.p2p.utils;

import java.util.Date;

/**
 * 时间判断
 */
public class DateUtil {

  /**
   * 用来计算发送两次验证码之间的间隔 ()
   * @param d1
   * @param d2
   * @return
   */
  public static long getBetweenSecond(Date d1 , Date d2 ){
    return  Math.abs((d1.getTime() - d2.getTime())/1000) ;
  }
}
