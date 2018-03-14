package com.hl.p2p.intercept;

import com.hl.p2p.utils.RequireLogin;
import com.hl.p2p.utils.UserContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
public class LoginIntercept extends HandlerInterceptorAdapter {

  @Override
  public boolean preHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler) throws Exception {
    //判断登陆逻辑
    //判断当前请求的方法
    if (handler instanceof HandlerMethod) {  //HandlerMethod包装了我这次请求要访问的controller里的具体方法
      HandlerMethod hm = (HandlerMethod) handler ;
      RequireLogin rl = hm.getMethodAnnotation(RequireLogin.class);//获取到当前方法上是否有注解
      //rl != null  说明该方法需要登陆才能访问
      if (rl != null && UserContext.getCurrent() == null ) {
        response.sendRedirect("/login");
        return false ; //阻止继续执行
      }
    }
    return super.preHandle(request, response, handler);   //正常的放行
  }
}
