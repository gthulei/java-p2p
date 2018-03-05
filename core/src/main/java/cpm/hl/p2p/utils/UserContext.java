package cpm.hl.p2p.utils;

import com.hl.p2p.pojo.Logininfo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public class UserContext {

  public static final String LOGININFO_IN_SESSION = "logininfo";


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
}
