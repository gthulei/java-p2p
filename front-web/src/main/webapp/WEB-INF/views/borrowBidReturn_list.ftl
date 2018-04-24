<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>HL-P2P平台</title>
		<#include "base/links.ftl" />

		<link rel="stylesheet" href="/css/bank.css">
		<script type="text/javascript" src="/js/bank.js"></script>
		<script type="text/javascript" src="/js/plugins/jquery.twbsPagination.min.js"></script>
		<script type="text/javascript" src="/js/plugins-override.js"></script>
		<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
    <link type="text/css" rel="stylesheet" href="/css/account.css" />
		<script type="text/javascript">
			$(function(){
				$("#query").click(function(){
					$("#searchForm").submit();
				});
				
				$(".beginDate,.endDate").click(function(){
					WdatePicker();
				});

      });
	 </script>
	</head>
	<body>
	
		<!-- 网页顶部导航 -->
		<#include "base/head-tpl.ftl" />
		<#assign currentNav="account" />
		<!-- 网页导航 -->
		<#include "base/navbar-tpl.ftl" />
		
		<div class="container">
			<div class="row">
				<!--导航菜单-->
				<div class="col-sm-3">
					<#assign currentMenu="borrowBidReturn" />
					<#include "base/leftmenu-tpl.ftl" />
				</div>
				<!-- 功能页面 -->
				<div class="col-sm-9">
					<form action="/borrowBidReturn_list" name="searchForm" id="searchForm" class="form-inline" method="post">
						
					</form>
					<div class="panel panel-default">
						<div class="panel-heading">
							<span class="pull-left" style="line-height: 35px;">还款列表</span>
							<div class="clearfix"></div>
						</div>
						<table class="table">
							<thead>
								<tr>
									<th>本期还款截止期限</th>
                  <th>还款时间</th>
                  <th>本期还款总金额</th>
                  <th>本期还款本金</th>
                  <th>本期还款总利息</th>
                  <th>第几期</th>
                  <th>本期还款状态</th>
                  <th>借款类型</th>
								</tr>
							</thead>
							<tbody>
							<#if (list?size > 0)>
								<#list list as data>
									<tr>
                    <td>${data.deadline?string("yyyy-MM-dd HH:mm:ss")}</td>
                    <td>${(data.paydate?string("yyyy-MM-dd HH:mm:ss"))!'-'}</td>
                    <td>${data.totalamount}</td>
                    <td>${data.principal}</td>
                    <td>${data.interest}</td>
                    <td>${data.monthindex}</td>
                    <td>${data.stateDisplay}</td>
                    <td><span class="label label-primary">信</span></td>
                  </tr>
								</#list>
							</#if>
							</tbody>
						</table>
						<div style="text-align: center;">
							<#if !(list?size > 0)>
                暂未数据
							</#if >
						</div>
					</div>
				</div>
			</div>
		</div>		
						
		<#include "base/floor.ftl" />
	</body>
</html>