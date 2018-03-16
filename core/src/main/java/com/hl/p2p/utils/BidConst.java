package com.hl.p2p.utils;

import java.math.BigDecimal;

/**
 * 系统中的常量
 */
public class BidConst {

  //定义存储精度
  public static final int STORE_SCALE = 4;

  //定义运算精度
  public static final int CAL_SCALE = 8;

  //定义显示精度
  public static final int DISPLAY_SCALE = 2;

  //60秒种只能发送一次短信
  public static final int SEND_TIME = 60;

  //180秒短信过期
  public static final int SEND_TIME_EX = 180;

  // 1天时间
  public static final int DAY_TIME = 86400;

  // 邮件过期时间5天
  public static final int SEND_MAIL_TIME = DAY_TIME * 5;

  // 邮件5天过期
  public static final int EMAIL_VALID_DAY = 5;

  //定义一个系统级别的0
  public static final BigDecimal ZERO = new BigDecimal("0.0000");

  //定义初始的授信额度
  public static final BigDecimal INIT_BORROW_LIMIT = new BigDecimal("5000.0000");

}
