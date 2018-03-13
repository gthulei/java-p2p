package com.hl.p2p.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 *  用来标记登陆的标签 (控制哪些页面需要登陆之后才能访问)
 *
 */

@Target(ElementType.METHOD) //用于描述方法
@Retention(RetentionPolicy.RUNTIME) //在运行时有效
public @interface RequireLogin {
}
