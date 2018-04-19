<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>HL-P2P平台</title>
		<#include "base/links.ftl" />
		<link rel="stylesheet" href="/css/bank.css">
		<script type="text/javascript" src="/js/bank.js"></script>
		<link type="text/css" rel="stylesheet" href="/css/account.css" />
		<script type="text/javascript" src="/js/plugins/uploadify/jquery.uploadify.min.js"></script>
		<script type="text/javascript">
		
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
					<#assign currentMenu="bankInfo"/>
					<#include "base/leftmenu-tpl.ftl" />
				</div>
				<!-- 功能页面 -->
				<div class="col-sm-9">
					<div class="panel panel-default">
						<div class="panel-heading">
							绑定银行卡
						</div>
						<#if userinfo.isRealAuth>
						<form class="form-horizontal" id="bankForm" novalidate="novalidate">
							<fieldset>
								<div class="form-group">
									<p class="text-center text-danger">为保护您账户安全，绑定银行卡之后，不能自己修改，请认真填写！</p>
								</div>
								<div class="form-group">
						        	<label class="col-sm-4 control-label ">开户名</label>
					        		<div class="col-sm-8">
						        		<p class="form-control-static">${userinfo.realname}</p>
						        	</div>
						        </div>
						        <div class="form-group">
						        	<label class="col-sm-4  control-label" for="bankName">开户行</label>
					        		<div class="col-sm-8">
						        		<select class="form-control" name="bankname" size="1" id="bankname">
											<script>
											for(var k in SITE_BANK_TYPE_NAME_MAP){
												var v = SITE_BANK_TYPE_NAME_MAP[k];
												var html = '<option value="'+ k +'">' + v + '</option>' 
												document.write(html);
											}
											</script>
										</select>
						        	</div>
						        </div>
						        <div class="form-group">
						        	<label class="col-sm-4  control-label" for="bankforkname">支行名称</label>
					        		<div class="col-sm-8">
						        		<input id="idNumber" class="form-control" name="bankforkname"  type="text" value="">
						        	</div>
						        </div>
						        <div class="form-group">
						        	<label class="col-sm-4  control-label" for="accountNumber">银行账号</label>
					        		<div class="col-sm-8">
						        		<input id="idNumber" class="form-control" name="banknumber"  type="text" value="">
						        	</div>
						        </div>
						        <div class="form-group">
						        	<div id="asubmit" class="btn btn-primary col-sm-offset-4" data-loading-text="正在提交"><i class="icon-ok"></i> 绑定银行卡</div>
						        </div>
							</fieldset>
						</form>
						<#else>
							<div class="el-tip-info">
								<p class="text-info">请先完成实名认证之后，才能进行银行卡绑定；</p>
							</div>
						</#if>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			$("#asubmit").click(function () {
        $.ajax({
          type: 'POST',
          url: "/bankInfoSave.json",
	        data:{
            bankname:$("#bankname").val(),
            bankforkname:$("input[name='bankforkname']").val(),
            banknumber:$("input[name='banknumber']").val()
	        },
          success: function (res) {
            if(res.succeed){
              $.messager.popup(res.errorMessage);
            }else {
              $.messager.popup(res.errorMessage);
            }
          }
        });
      })
		</script>
		<#include "base/floor.ftl" />
	</body>
</html>