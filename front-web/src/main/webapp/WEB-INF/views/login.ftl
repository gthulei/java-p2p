<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<#include "base/links.ftl">
  <script type="text/javascript" src="/js/plugins/jquery-validation/jquery.validate.js"></script>
  <script type="text/javascript" src="/js/plugins/jquery.form.js"></script>
  <title>HL-P2P平台->用户登录</title>
</head>
<style type="text/css">
	.el-login-form{
		width:600px; 
		margin-left:auto;
		margin-right:auto;
		margin-top: 20px;
	}
	.el-login-form .form-control{
		width: 220px;
		display: inline;
	}
	.container{
		margin-bottom: 50px;
	}
</style>

<script type="text/javascript">
	$(function(){
		$("#loginForm").ajaxForm({
			success:function(data){
				if(data.succeed){
					$.messager.confirm("提示","登陆成功,点击确定进入个人中心",function(){
						window.location.href="/personal";
					});
				}else{
					$.messager.popup(data.errorMessage)
				}
			}
		});
		$("#loginForm").validate({
			rules:{
				username:"required",
				password:"required"
			},
			messages:{
				username:"用户名不能为空!",
				password:"密码不能为空!"
			},
			errorClass:"text-danger",
			highlight:function(element){
				$(element).closest("div.form-group").addClass("has-error");
			},
			unhighlight:function(element){
				$(element).closest("div.form-group").removeClass("has-error");
			}
			
		});
		
	});
	
</script>
<body>
	<!-- 网页头信息 -->
	<div class="el-header" >
		<div class="container" style="position: relative;">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/index">首页</a></li>
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
				<span class="el-page-title">用户登录</span>
			</div>
		</div>
	</div>
	
	<!-- 网页内容 --> 
	<div class="container">  
		<form id="loginForm" class="form-horizontal el-login-form" action="/login.json" method="post" >
			<p class="h4" style="margin: 10px 10px 20px 110px;color:#999;">请输入用户名和密码</p> 
			<div class="form-group">
				<label class="control-label col-sm-2">用户名</label>
				<div class="col-sm-10">
					<input type="text" autocomplete="off" name="username" class="form-control" value=""/>
				</div> 
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">密&emsp;码</label>
				<div class="col-sm-10">
					<input type="password" autocomplete="off" name="password" class="form-control" value=""/>
				</div>
			</div>
			<div class="form-gorup">
				<div class="col-sm-offset-3">
					<button type="submit" class="btn btn-success" style="width: 100px;">
						登录
					</button>
					&emsp;&emsp;
					<a href="/register">新用户，马上注册</a>
				</div>
			</div>
		</form>
	</div>
	<#include "base/floor.ftl">
</body>
</html>