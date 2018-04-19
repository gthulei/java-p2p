<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>HL-P2P平台</title>
		<#include "base/links.ftl" />
		<link type="text/css" rel="stylesheet" href="/css/account.css" />
		<script type="text/javascript" src="/js/bank.js"></script>
	</head>
	<body>
		<!-- 网页顶部导航 -->
		<#include "base/head-tpl.ftl" />
		
		<#assign currentNav="personal" />
		<!-- 网页导航 -->
		<#include "base/navbar-tpl.ftl" />
		
		<div class="container">
			<div class="row">
				<!--导航菜单-->
				<div class="col-sm-3">
					<#assign currentMenu = "bankInfo" />
					<#include "base/leftmenu-tpl.ftl" />
				</div>
				<!-- 功能页面 -->
				<div class="col-sm-9">
					<div class="panel panel-default">
						<div class="panel-heading">
							绑定银行卡
						</div>
						<div class="panel-body">
							<h4 class="text-success ">你已经绑定了银行卡</h4>
							<hr />
							<table style="width: 100%;height: 100px;">
								<tr>
									<td><span>开户名： ${bankInfo.accountname}</span></td>
									<td><span>开户行：<script>document.write(SITE_BANK_TYPE_NAME_MAP[${bankInfo.bankname}])</script></span></td>
								</tr>
								<tr>
									<td><span>支行名称： ${bankInfo.bankforkname}</span></td>
									<td><span>银行账号：${bankInfo.banknumber}</span></td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<#include "base/floor.ftl" />
	</body>
</html>