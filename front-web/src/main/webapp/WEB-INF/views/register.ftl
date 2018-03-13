<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<#include "base/links.ftl">
  <script type="text/javascript" src="/js/plugins/jquery-validation/jquery.validate.js"></script>
  <script type="text/javascript" src="/js/plugins/jquery.form.js"></script>
  <title>蓝源Eloan-P2P平台->用户注册</title>
</head>
<style type="text/css">
	.el-register-form{
		width:600px; 
		margin-left:auto;
		margin-right:auto;
		margin-top: 20px;
	}
	.el-register-form .form-control{
		width: 220px;
		display: inline;
	}
</style>
<script type="text/javascript">
	$(function(){
		$("#registerForm").validate({
			rules:{
				username:{
					required:true,
					rangelength:[4,16]
				},
				password:{
					required:true,
					rangelength:[4,20]
				},
				confirmPwd:{
					required:true,
					equalTo:"#password"
				}
			},
			messages:{
				username:{
					required:"请填写用户名!",
					rangelength:"用户名长度必须在{0}-{1}之间!",
					remote:"该用户名已经存在!"
				},
				password:{
					required:"请填写密码!",
					rangelength:"密码长度必须在{0}-{1}之间!"
				},
				confirmPwd:{
					required:"请再次确认密码",
					equalTo:"两次密码不一致"
				}
			},
			submitHandler:function(form){
				$("#registerForm").ajaxSubmit({
					dataType:"json",
					success:function(data){
						if(data.succeed){
							$.messager.confirm(data.errorMessage);
              window.location.href="/login";
						}else{
							$.messager.popup(data.errorMessage)
						}
					}
				});
			},
			errorClass:"text-danger",
			highlight:function(element){
				$(element).closest("div.form-group").addClass("has-error");
			},
			unhighlight:function(element){
				$(element).closest("div.form-group").removeClass("has-error");
			}
			
			
			
		})	
	})
</script>
<body>
	<!-- 网页头信息 -->
	<div class="el-header" >
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
				<span class="el-page-title">用户注册</span>
			</div>
		</div>
	</div>
	
	<!-- 网页内容 -->
	<div class="container">  
		<form id="registerForm" class="form-horizontal el-register-form"  action="/register.json" method="post" >
			<p class="h4" style="margin: 10px 10px 20px;color:#999;">请填写注册信息，点击“提交注册”即可完成注册！</p>
			<div class="form-group">
				<label class="control-label col-sm-2">用户名</label>
				<div class="col-sm-10">
					<input type="text" autocomplete="off" name="username" class="form-control" id="username"/>
					<p class="help-block">用户名为4~16位字母，数字，符号或中文</p>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">密&emsp;码</label>
				<div class="col-sm-10">
					<input type="password" autocomplete="off" name="password" id="password" class="form-control" />
					<p class="help-block">密码为4~16位字符组成,采用数字、字母、符号安全性更高</p>
				</div> 
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">确认密码</label>
				<div class="col-sm-10">
					<input type="password" autocomplete="off" name="confirmPwd" class="form-control" />
					<p class="help-block">请再次填写密码</p>	 
				</div>
			</div> 
			<div class="form-gorup">   
				<div class="col-sm-offset-2">  
					<button type="submit" class="btn btn-success">
						同意协议并注册
					</button>
					&emsp;&emsp;
					<a href="/login" class="text-primary">已有账号，马上登录</a>
					
					<p style="padding-left: 50px;margin-top: 15px;">
						<a href="#">《使用协议说明书》</a>
					</p>
				</div>
			</div>
		</form>
	</div>
	<!-- 网页版权 -->
	<#include "base/floor.ftl">
</body>
</html>