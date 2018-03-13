<div class="navbar cm-navbar">
	<img class="logo" alt="Brand" src="/images/logo.png">
	<span class="pageTitle">&nbsp;</span>
	<ul class="nav navbar-nav navbar-right cm-navbar-nav">
		<li>
			<p class="navbar-text text-info">${logininfo.username}</p>
		</li>
    <li><a id="esc" href="javascript:;">安全退出</a></li>
		<li><a href="">个人设置</a></li>
	</ul>
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
