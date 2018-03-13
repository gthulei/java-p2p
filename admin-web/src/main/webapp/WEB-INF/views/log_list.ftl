<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>蓝源Eloan-P2P平台(系统管理平台)</title>
<#include "base/header.ftl"/>

  <script type="text/javascript" src="/js/jquery/jquery-2.1.3.js"></script>
  <script type="text/javascript" src="/js/bootstrap-3.3.2-dist/js/bootstrap.js"></script>
  <script type="text/javascript" src="/js/jquery.bootstrap.min.js"></script>

  <script type="text/javascript" src="/js/plugins/jquery.form.js"></script>
  <script type="text/javascript" src="/js/plugins/jquery.twbsPagination.min.js"></script>
  <script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>

  <script type="text/javascript">
    $(function(){
      $(".beginDate,.endDate").click(function(){
        WdatePicker();
      })
      $("#query").click(function(){
        $("#currentPage").val(1); //重新查询的时候设置当前页为1
        $("#searchForm").submit() ;
      })

      $("#pagination").twbsPagination({
        totalPages: ${pageResult.totalCount} ,
        visiblePages : 10 , //表示页面上面最多可以显示几页
        startPage: ${pageResult.currentPage},
        onPageClick:function(eventm,page){
          $("#currentPage").val(page);
          $("#searchForm").submit() ;
        }
      })
    });
  </script>
</head>
<body>
<div class="container">
		<#include "base/top.ftl"/>
  <div class="row">
    <div class="col-sm-3">
				<#assign currentMenu="ipLog" />
				<#include "base/menu.ftl" />
    </div>
    <div class="col-sm-9">
      <div class="page-header">
        <h3>登录日志查询</h3>
      </div>
      <form id="searchForm" class="form-inline" method="post" action="/ipLog">
        <input type="hidden" id="currentPage" name="currentPage" value="1"/>
        <div class="form-group">
          <label>状态</label>
          <select class="form-control" name="state" id="state">
            <option value="-1">全部</option>
            <option value="0">登录失败</option>
            <option value="1">登录成功</option>
          </select>
          <script type="text/javascript">
            $("#state option[value=${qo.state}]").attr("selected", true);
          </script>
        </div>
        <div class="form-group">
          <label>登陆时间</label>
          <input class="form-control beginDate" type="text" name="beginDate" value='${(qo.beginDate?string("yyyy-MM-dd"))!""}'/>到
          <input class="form-control endDate" type="text" name="endDate" value='${(qo.endDate?string("yyyy-MM-dd"))!""}'/>
        </div>
        <div class="form-group">
          <label>用户类型</label>
          <select class="form-control" name="userType" id="userType">
            <option value="-1">全部</option>
            <option value="0">后台管理员</option>
            <option value="1">前台用户</option>
          </select>
          <script type="text/javascript">
            //高级查询下拉框的选中
            $("#userType option[value=${qo.userType}]").attr("selected", true);
          </script>
        </div>
        <div class="form-group">
          <label>用户名</label>
          <input class="form-control" type="text" name="username" value='${(qo.username)!""}'/>
        </div>
        <div class="form-group">
          <button id="query" type="button" class="btn btn-success"><i class="icon-search"></i> 查询</button>
        </div>
      </form>
      <div class="panel panel-default">
        <table class="table">
          <thead>
          <tr>
            <th>用户</th>
            <th>登录时间</th>
            <th>登录ip</th>
            <th>登录状态</th>
            <th>用户类型</th>
          </tr>
          </thead>
          <tbody id="tbody">
							<#list pageResult.data as item >
              <tr>
                <td>${item.username}</td>
                <td>${item.logintime?string("yyyy-MM-dd HH:mm:ss")}</td>
                <td>${item.ip}</td>
                <td>
                  <#if item.loginstate == 0>
                    <div style="color: red">登录失败</div>
                  <#else >
                    登录成功
                  </#if>
                </td>
              </tr>
							</#list>
          </tbody>
        </table>
        <div style="text-align: center;" id="page_container">
          <ul id="pagination" class="pagination"></ul>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>