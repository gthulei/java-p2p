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
					currentPage : ${pageResult.currentPage},
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
					<#assign currentMenu="bid" />
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
							<span class="pull-left" style="line-height: 35px;">投资明细</span>
							<div class="clearfix"></div>
						</div>
						<table class="table">
							<thead>
								<tr>
									<th>借款标题</th>
									<th>年化利率</th>
									<th>这次投标金额</th>
									<th>投标时间</th>
                  <th>查询标地信息</th>
								</tr>
							</thead>
							<tbody>
								<#list pageResult.data as data>
									<tr>
										<td>${data.bidrequesttitle}</td>
										<td>${data.actualrate*100}%</td>
										<td>${data.availableamount}</td>
										<td>${data.bidtime?string("yyyy-MM-dd")}</td>
                    <td><a href="/borrowDes?id=${data.bidrequestId}">查看</a></td>
									</tr>
								</#list>
							</tbody>
						</table>
						<div style="text-align: center;">
							<ul id="pagination" class="pagination"></ul>
						</div>
					</div>
				</div>
			</div>
		</div>		
						
		<#include "base/floor.ftl" />
	</body>
</html>