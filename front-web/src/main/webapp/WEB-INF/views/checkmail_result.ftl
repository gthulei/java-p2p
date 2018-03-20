<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>HL-P2P平台->邮件激活</title>
	<#include "base/links.ftl">
  <script type="text/javascript" src="/js/plugins/jquery-validation/jquery.validate.js"></script>
  <script type="text/javascript" src="/js/plugins/jquery.form.js"></script>

  <style type="text/css">
    .el-login-form {
      width: 600px;
      margin-left: auto;
      margin-right: auto;
      margin-top: 20px;
    }

    .el-login-form .form-control {
      width: 220px;
      display: inline;
    }
  </style>

</head>
<body>
<!-- 网页头信息 -->
<div class="el-header">
  <div class="container" style="position: relative;">
    <ul class="nav navbar-nav navbar-right">
      <li><a href="/">首页</a></li>
      <li><a href="/login">登录</a></li>
      <li><a href="#">帮助</a></li>
    </ul>
  </div>
</div>

<!-- 网页导航 -->
<div class="navbar navbar-default el-navbar">
  <div class="container">
    <div class="navbar-header">
      <a href=""><img alt="Brand" src="/images/logo.png"></a>
      <span class="el-page-title">邮件激活结果</span>
    </div>
  </div>
</div>

<!-- 网页内容 -->
<div class="container" style="min-height: 343px;">
  <h1>
			<#if succeed>
        您的邮件已经成功激活,请登录系统查看!
			<#else>
				您的邮件激活失败,失败原因是:${message}!
			</#if>
  </h1>
</div>


<!-- 网页版权 -->
	<#include "base/floor.ftl">
</body>
</html>