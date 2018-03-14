package com.hl.p2p.utils;

import com.hl.p2p.pojo.Logininfo;
import com.hl.p2p.vo.VerifyCodeVo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public class UserContext {

  public static final String LOGININFO_IN_SESSION = "logininfo";

  public static final String VERIFYCODE = "verifyCode";


  private static HttpSession getSession() {
    return ((ServletRequestAttributes) RequestContextHolder
      .getRequestAttributes()).getRequest().getSession();
  }

  public static void putCurrent(Logininfo current) {
    getSession().setAttribute(LOGININFO_IN_SESSION, current);
  }


  public static Logininfo getCurrent() {

    return (Logininfo) getSession().getAttribute(LOGININFO_IN_SESSION);
  }

  public static void putVerifyCode(VerifyCodeVo v) {
    getSession().setAttribute(VERIFYCODE, v);
  }


  public static VerifyCodeVo getVerifyCode() {

    return (VerifyCodeVo) getSession().getAttribute(VERIFYCODE);
  }

  public static void removeCurrent(){
    getSession().setAttribute(LOGININFO_IN_SESSION, null);
  }
}
