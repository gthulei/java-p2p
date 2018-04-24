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
				
				$('#pagination').twbsPagination({
					totalPages : ${pageResult.totalCount},
          startPage: ${pageResult.currentPage},
					visiblePages : 5,
					first:"首页",
				    prev:"上一页",
				    next:"下一页",
				    last:"最后一页",
					onPageClick : function(event, page) {
						$("#currentPage").val(page);
						$("#searchForm").submit();
					}
				});
				
				$("#query").click(function(){
					$("#currentPage").val(1);
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
					<#assign currentMenu="accountFlow_list" />
					<#include "base/leftmenu-tpl.ftl" />
				</div>
				<!-- 功能页面 -->
				<div class="col-sm-9">
					<form action="/accountFlowList" name="searchForm" id="searchForm" class="form-inline">
						<input type="hidden" id="currentPage" name="currentPage" value="" />
						<div class="form-group">
							<label>时间范围</label>
							<input type="text" class="form-control beginDate" name="beginDate" value="${(qo.beginDate?string('yyyy-MM-dd'))!''}"/>
						</div>
						<div class="form-group">
							<label></label>
							<input type="text" class="form-control endDate" name="endDate" value="${(qo.endDate?string('yyyy-MM-dd'))!''}"/>
						</div>
						<div class="form-group">
							<button id="query" class="btn btn-success"><i class="icon-search"></i> 查询</button>
						</div>
					</form>
					<div class="panel panel-default">
						<div class="panel-heading">
							<span class="pull-left" style="line-height: 35px;">账户流水</span>
							<div class="clearfix"></div>
						</div>
						<table class="table">
							<thead>
								<tr>
									<th>可用金额</th>
									<th>冻结金额</th>
									<th>资金变化时间</th>
									<th>备注</th>
								</tr>
							</thead>
							<tbody>
								<#if (pageResult.data?size > 0)>
								<#list pageResult.data as data>
									<tr>
										<td>${data.balance}元</td>
										<td>${data.freezed}元</td>
										<td>${data.actiontime?string("yyyy-MM-dd")}</td>
										<td>${data.note}</td>
									</tr>
								</#list>
								</#if>
							</tbody>
						</table>
						<div style="text-align: center;">
					 <#if (pageResult.data?size > 0)>
							<ul id="pagination" class="pagination"></ul>
							<#else >
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