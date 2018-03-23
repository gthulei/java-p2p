<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>HL-P2P平台</title>
		<#include "base/links.ftl" />
		<link type="text/css" rel="stylesheet" href="/css/account.css" />
		<script type="text/javascript" src="/js/plugins/jquery.form.js"></script>
		<script type="text/javascript">
			$(function(){
				$("#editForm").ajaxForm(function(){
					window.location.reload();
				});
				
				$("#submitFileType").click(function(){
					$("#editForm").submit();
				});
			})
		</script>
	</head>
	<body>
		<!-- 网页顶部导航 -->
		<#include "base/head-tpl.ftl"/>
		<#assign currentNav="personal" />
		<#include "base/navbar-tpl.ftl" />

		<div class="container">
			<div class="row">
				<!--导航菜单-->
				<div class="col-sm-3">
					<#assign currentMenu="userFile"/>
					<#include "base/leftmenu-tpl.ftl" />
				</div>
				<!-- 功能页面 -->
				<div class="col-sm-9">
					<div class="panel panel-default">
						<div class="panel-heading">
							用户认证文件信息
						</div>
					</div>
					<div class="row">
					  <form method="POST" action="/userFileSelectType.json" id="editForm">
					  <#list userFiles as file>
					  <div class="col-sm-6 col-md-4">
					    <div class="thumbnail">
					      <img src="http://127.0.0.1:8080${file.file}" />
					      <div class="caption">
					        <p>
					        	<input type="hidden" name="id" value="${file.id}" />
					        	<select class="form-control" name="filetypeId" style="width: 180px" autocomplate="off">
									<#list fileTypes as type>
										<option value="${type.id}">${type.title}</option>
									</#list>
								</select>
					        </p>
					      </div>
					    </div>
					  </div>
					  </#list>
					  </form>
					</div>
					<div class="row" style="text-align: center;">
						<a href="javascript:;" id="submitFileType" class="btn btn-success">确定提交</a>
					</div>
				</div>
			</div>
		</div>		
		<#include "base/floor.ftl" />
	</body>
</html>