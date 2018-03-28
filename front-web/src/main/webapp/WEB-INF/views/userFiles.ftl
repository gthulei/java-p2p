<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>HL-P2P平台</title>
		<#include "base/links.ftl" />
  <link type="text/css" rel="stylesheet" href="/css/account.css"/>
  <script type="text/javascript" src="/js/plugins/jquery.form.js"></script>
  <script type="text/javascript" src="/js/plugins/uploadify/jquery.uploadify.min.js"></script>

  <style type="text/css">
    .uploadify {
      height: 20px;
      font-size: 13px;
      line-height: 20px;
      text-align: center;
      position: relative;
      margin-left: auto;
      margin-right: auto;
      cursor: pointer;
      background-color: #337ab7;
      border-color: #2e6da4;
      color: #fff;
    }
  </style>
  <script type="text/javascript">
    $(function () {
      $("#fileForm").ajaxForm(function(){
        window.location.reload();
      });

      $("#btn_uploadUserFiles").click(function(){
        if(!$("#file").val()){
          $.messager.popup("请选择图片");
          return;
        }
        $("#fileForm").submit();
      });
    });
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
			<#list userFiles as file>
			<div class="col-sm-6 col-md-4">
			<div class="thumbnail">
			<img src="http://127.0.0.1:8080${file.file}" />
			<div class="caption">
			<h4>${file.filetype.title}</h4>
			<p>得分：${file.score!'-'} &nbsp;&nbsp;状态：${file.audit}</p>
			</div>
			</div>
			</div>
			</#list>
      </div>
      <form action="/userFileApply.json" id="fileForm" class="form-horizontal" method="post" enctype="multipart/form-data">
        <input type="file" name="file" multiple="multiple" id="file"/>
      </form>
      <div class="row" style="margin-top: 30px">
        <button type="button" class="btn btn-success" id="btn_uploadUserFiles" style="width: 100px;">
          提交
        </button>
      </div>
    </div>
  </div>
</div>
		<#include "base/floor.ftl" />
</body>
</html>