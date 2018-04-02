<div class="el-header" >
		<div class="container" style="position: relative;">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/index">首页</a></li>
					<#if !logininfo??>
					<li><a href="/login">登录</a></li>
					<li><a href="/register">快速注册</a></li>
					<#else>
					<li>
						  <a class="el-current-user" href="/personal">${logininfo.username}</a>
					</li>
					<li><a  href="/recharge">账户充值  </a></li>
					<li><a id="esc" href="javascript:;">注销</a></li>
					</#if>
				<li><a href="#">帮助</a></li>
			</ul>
		</div>
</div>
<script type="text/javascript">
	$(function () {
		$("#esc").on("click",function () {
      $.messager.confirm("提示","是否退出登录",function(){
        $.ajax({
          type: 'POST',
          url: "/escLogin.json",
          success: function (res) {
            if(res.succeed){
              window.location.href="/login";
            }
          }
        });
      });
    })
  })
</script>
