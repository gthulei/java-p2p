<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>HL-P2P平台</title>
		<#include "base/links.ftl" />
  <link type="text/css" rel="stylesheet" href="/css/account.css"/>
  <script type="text/javascript" src="/js/plugins/jquery.form.js"></script>
  <script>
    $(function () {
      $('#educationBackground').find("option[value=${userinfo.educationbackgroundId!'off'}]").attr("selected", true);
      $('#incomeGrade').find("option[value=${userinfo.incomegradeId!'off'}]").attr("selected", true);
      $('#marriage').find("option[value=${userinfo.marriageId!'off'}]").attr("selected", true);
      $('#houseCondition').find("option[value=${userinfo.houseconditionId!'off'}]").attr("selected", true);
      $('#kidCount').find("option[value=${userinfo.kidcountId!'off'}]").attr("selected", true);

      //提交表单
      $("#submitBtn").click(function () {
        if($("#educationBackground").val() == 'off'){
          $.messager.popup('学历不能为空');
          return;
        }
        if($("#incomeGrade").val() == 'off'){
          $.messager.popup('月收入不能为空');
          return;
        }
        if($("#marriage").val() == 'off'){
          $.messager.popup('婚姻情况不能为空');
          return;
        }
        if($("#houseCondition").val() == 'off'){
          $.messager.popup('住房不能为空')
          return;
        }
        if($("#kidCount").val() == 'off'){
          $.messager.popup('子女不能为空')
          return;
        }
        $("#userInfoForm").ajaxSubmit({
          success: function (data) {
            if (data.succeed) {
              $.messager.confirm("提示", "资料保存成功", function () {
                window.location.reload();
              });
            } else {
              $.messager.popup(data.errorMessage);
            }
          }
        });
      });
    });
  </script>
</head>
<body>
<!-- 网页顶部导航 -->
		<#include "base/head-tpl.ftl" />

<!-- 网页导航 -->
		<#assign currentNav="personal"/>
		<#include "base/navbar-tpl.ftl" />

<div class="container">
  <div class="row">
    <!--导航菜单-->
    <div class="col-sm-3">
					<#assign currentMenu="userInfo" />
					<#include "base/leftmenu-tpl.ftl" />
    </div>
    <!-- 功能页面 -->
    <div class="col-sm-9">
      <div class="panel panel-default">
        <div class="panel-heading">
          个人资料
        </div>
        <form id="userInfoForm" class="form-horizontal" action="/basicInfoSave.json" method="post" style="width: 700px;">
          <div class="form-group">
            <label class="col-sm-4 control-label">
              用户名
            </label>
            <div class="col-sm-8">
              <p class="form-control-static">${logininfo.username}</p>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-4 control-label">
              真实姓名
            </label>
            <div class="col-sm-8">
              <p class="form-control-static">
										<#if (userinfo.isRealAuth)>
											${userinfo.realname}
										<#else>
											未认证
											<a href="/realAuth">[马上认证]</a>
										</#if>
              </p>
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-4 control-label">
              身份证号码
            </label>
            <div class="col-sm-8">
              <p class="form-control-static">
										<#if (userinfo.isRealAuth)>
											${userinfo.idnumber}
										<#else>
											未认证
											<a href="/realAuth">[马上认证]</a>
										</#if>
              </p>
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-4 control-label">
              手机号码
            </label>
            <div class="col-sm-8">
              <label style="width: 250px;" class="form-control">${(userinfo.phonenumber)!''}</label>
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-4 control-label">
              个人学历
            </label>
            <div class="col-sm-8">
              <select class="form-control" id="educationBackground" name="educationbackgroundId" style="width: 180px" autocomplate="off">
                <option value="off">请选择学历</option>
										<#list educationBackgrounds as item>
										  <option value="${item.id}">${item.title}</option>
										</#list>
              </select>
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-4 control-label">
              月收入
            </label>
            <div class="col-sm-8">
              <select class="form-control" id="incomeGrade" name="incomegradeId" style="width: 180px" autocomplate="off">
                <option value="off">请选择月收入</option>
									<#list incomeGrades as item>
										  <option value="${item.id}">${item.title}</option>
									</#list>
              </select>
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-4 control-label">
              婚姻情况
            </label>
            <div class="col-sm-8">
              <select class="form-control" id="marriage" name="marriageId" style="width: 180px" autocomplate="off">
                <option value="off">请选择婚姻情况</option>
                <#list marriages as item>
                  <option value="${item.id}">${item.title}</option>
                </#list>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-4 control-label">
              住房条件
            </label>
            <div class="col-sm-8">
              <select class="form-control" id="houseCondition" name="houseconditionId" style="width: 180px" autocomplate="off">
                <option value="off">请选择住房条件</option>
										<#list houseConditions as item>
                      <option value="${item.id}">${item.title}</option>
										</#list>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-4 control-label">
              子女情况
            </label>
            <div class="col-sm-8">
              <select class="form-control" id="kidCount" name="kidcountId" style="width: 180px" autocomplate="off">
                <option value="off">请选择子女情况</option>
              <#list kidCounts as item>
                      <option value="${item.id}">${item.title}</option>
              </#list>
              </select>
            </div>
          </div>

          <div class="form-group">
            <div id="submitBtn" class="btn btn-primary col-sm-offset-5" data-loading-text="数据保存中" autocomplate="off">
              保存数据
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
		
		<#include "base/floor.ftl" />
</body>
</html>