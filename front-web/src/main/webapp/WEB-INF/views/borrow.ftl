<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>HL-P2P平台->我要借款</title>
<#include "base/links.ftl" />
</head>
<body>
<!-- 网页头信息 -->
	<#include "base/head-tpl.ftl" />

<!-- 网页导航 -->
	<#assign currentNav="borrow" />
	<#include "base/navbar-tpl.ftl" />

<!-- 网页内容 -->
<div class="container el-borrow">
  <div class="row">
    <div class="el-borrow-item col-sm-4">
      <div class="el-borrow-item-title" style="background-color: #40d47e;">
        信用贷
      </div>
      <div class="el-borrow-item-content">
        <p>
          认证后可借金额 <i>¥5000
        </p>
        <a href="#" class="text-primary">申请条件</a>
        <p class="help-block">仅限广州地区</p>
        <ul>
          <li>
            <#if user??>
              <#if userinfo.isBasicInfo>
              <a href="#">填写基本资料</a>
               <span class="glyphicon glyphicon-ok" style="color:#00aa00;"></span>
              <#else >
              <a href="/basicInfo">填写基本资料</a>
              <span class="glyphicon glyphicon-remove" style="color:red;"></span>
              </#if>
            <#else >
              <span>填写基本资料</span>
            </#if>
          </li>
          <li>
            <#if user??>
              <#if userinfo.isRealAuth>
                <a href="#">身份认证</a>
               <span class="glyphicon glyphicon-ok" style="color:#00aa00;"></span>
              <#else >
              <a href="/realAuth">身份认证</a>
              <span class="glyphicon glyphicon-remove" style="color:red;"></span>
              </#if>
            <#else >
              <a href="/realAuth">身份认证</a>
              <span class="glyphicon glyphicon-remove" style="color:red;"></span>
            </#if>

          </li>
          <li>
            <#if userinfo.authscore??>
              <#if (userinfo.authscore>20)>
                <a href="#">材料认证分数达到20分</a>
               <span class="glyphicon glyphicon-ok" style="color:#00aa00;"></span>
                <#else >
                <a href="/userFile">材料认证分数达到20分</a>
                <span class="glyphicon glyphicon-remove" style="color:red;"></span>
              </#if>
              <#else >
                <a href="/userFile">材料认证分数达到20分</a>
                <span class="glyphicon glyphicon-remove" style="color:red;"></span>
            </#if>

          </li>
          <li>
            <#if user??>
             <#if userinfo.isVedioAuth>
              <a href="#">视频认证</a>
             <span class="glyphicon glyphicon-ok" style="color:#00aa00;"></span>
             <#else >
              <a href="#">视频认证</a>
              <span class="glyphicon glyphicon-remove" style="color:red;"></span>
             </#if>
            <#else >
              <span>视频认证</span>
            </#if>
          </li>
        </ul>
        <#if user??>
          <a href="/borrowInfo" class="el-borrow-apply">
            申请贷款
          </a>
        <#else >
         <a href="/login" class="el-borrow-apply">
           请先登录
         </a>
        </#if>

      </div>
    </div>
    <div class="el-borrow-item col-sm-4">
      <div class="el-borrow-item-title">车易贷</div>
      <div class="el-borrow-item-content">
        <p>
          认证后可借金额 <i>¥ 10,000.00</i>
        </p>
        <a href="#" class="text-primary">申请条件</a>
        <p class="help-block">仅限广州地区</p>
        <ul>
          <li>填写基本资料</li>
          <li>身份认证</li>
          <li>材料认证分数达到30分</li>
          <li>提交车辆抵押相关资料</li>
          <li>视频认证</li>
        </ul>
        <#if user??>
          <a href="#" class="el-borrow-apply">
            申请贷款
          </a>
        <#else >
         <a href="/login" class="el-borrow-apply">
           请先登录
         </a>
        </#if>
      </div>
    </div>
    <div class="el-borrow-item col-sm-4">
      <div class="el-borrow-item-title" style="background-color: #2ca2ee;">
        房易贷
      </div>
      <div class="el-borrow-item-content">
        <p>
          可借金额 <i>¥ 10,0000.00</i>
        </p>
        <a href="#" class="text-primary">申请条件</a>
        <p class="help-block">仅限广州地区</p>
        <ul>
          <li>填写基本资料</li>
          <li>身份认证</li>
          <li>材料认证分数达到50分</li>
          <li>提交房屋抵押相关资料</li>
          <li>视频认证</li>
        </ul>
        <#if user??>
          <a href="#" class="el-borrow-apply">
            申请贷款
          </a>
        <#else >
         <a href="/login" class="el-borrow-apply">
           请先登录
         </a>
        </#if>
      </div>
    </div>
  </div>
</div>

<!-- 网页版权 -->
<#include "base/floor.ftl" />
</body>
</html>