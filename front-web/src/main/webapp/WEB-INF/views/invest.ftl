<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>HL-P2P平台->我要借款</title>
<#include "base/links.ftl" />
  <script type="text/javascript" src="/js/plugins/jquery.twbsPagination.min.js"></script>
  <script type="text/javascript" src="/js/plugins/jquery.form.js"></script>
  <script type="text/javascript" src="/js/plugins-override.js"></script>
  <script type="text/javascript">
    $(function () {
      $(".btn").click(function () {
        var $v=$(this).attr('v')
        $("#bidRequestState").val($v)
        if($v>0){
          $("#quertState").val(1)
        }
        $("#searchForm").submit();
      });
    });
  </script>
</head>
<body>
<!-- 网页头信息 -->
	<#include "base/head-tpl.ftl" />
	<#assign currentNav="invest" />
<!-- 网页导航 -->
	<#include "base/navbar-tpl.ftl" />

<!-- 网页内容 -->
<div class="container" style="min-height: 500px;">
  <h4 class="page-title">投资列表</h4>
  <form action="/invest" id="searchForm" method="POST">
    <input type="hidden" name="currentPage" id="currentPage" value="1">
    <input type="hidden" name="quertState" id="quertState" value="0">
    <input type="hidden" name="bidRequestState" id="bidRequestState" value="-1">
    <div style="margin: 20px 0px;">
      <span class="text-muted">标的状态</span>
      <div style="margin-left: 30px" class="btn-group" data-toggle="buttons">
        <span class="btn btn-default" v="-1">全部</span>
        <span class="btn btn-default" v="1">招标中</span>
        <span class="btn btn-default" v="8">已完成</span>
      </div>
    </div>
  </form>
  <table class="table el-table table-hover">
    <thead id="gridHead">
    <tr>
      <th>借款人</th>
      <th width="180px">借款标题</th>
      <th>年利率</th>
      <th>金额</th>
      <th>还款方式</th>
      <th>进度</th>
      <th width="80px">操作</th>
    </tr>
    </thead>
    <tbody id="gridBody">
				<#if pageResult.data?size &gt; 0 >
					<#list pageResult.data as data>
		<tr>
      <td>${data.createuser.username }</td>
      <td>${data.title}</td>
      <td class="text-info">${data.currentrate}%</td>
      <td class="text-info">${data.bidrequestamount}</td>
      <td>${data.returnTypeDisplay}</td>
      <td>
        <div class="">
					${data.persent} %
        </div>
      </td>
      <td><a class="btn btn-danger btn-sm"
             href="/borrow_info?id=${data.id}">查看</a></td>
    </tr>
					</#list>
				<#else>
	<tr>
    <td colspan="7" align="center">
      <p class="text-danger">目前没有符合要求的标</p>
    </td>
  </tr>
				</#if>

    <script type="text/javascript">
      $(function () {
        //先将分页的内容先删了  之后再换成新的分页内容
        $("#page_container").empty().append($('<ul id="pagination" class="pagination"></ul>'));
        $("#pagination").twbsPagination({
          totalPages:${pageResult.totalCount},
          currentPage:${pageResult.currentPage},
          initiateStartPageClick: false,
          onPageClick: function (event, page) {
            $("#currentPage").val(page);
            $("#searchForm").submit();
          }
        });
      });
    </script>
    </tbody>
  </table>
  <div style="text-align: center;">
    <ul id="pagination" class="pagination"></ul>
  </div>
</div>

<!-- 网页版权 -->
	<#include "base/floor.ftl" />
</body>
</html>