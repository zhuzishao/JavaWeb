<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function (){

			$("#username").blur(function () {
				//1 获取用户名
				var username = this.value;
				$.getJSON("http://localhost:8080/JavaWeb/userServlet","action=ajaxExistsUsername&username=" + username,function (data) {
					if (data.existsUsername) {
						$("span.errorMsg").text("用户名已存在！");
					} else {
						$("span.errorMsg").text("用户名可用！");
					}
				});
			});

			$("#sub_btn").click(function (){
				// 验证用户名：必须由字母，数字下划线组成，并且长度为 5 到 12 位
				 //1.获取输入框中的内容
				 var usernameText = $("#username").val();
				 //2.验证用户名合法的正则表达式
				 var usernamePatt = /^\w{5,12}$/;
				 //3.验证用户名是否合法
				if(!usernamePatt.test(usernameText)){
					$("span.errorMsg").text("用户名不合法");
					return false;
				}

				// 验证密码：必须由字母，数字下划线组成，并且长度为 5 到 12 位

				var passwordText = $("#password").val();
				//2.验证用户名合法的正则表达式
				var passwordPatt = /^\w{5,12}$/;
				//3.验证用户名是否合法
				if(!passwordPatt.test(passwordText)){
					$("span.errorMsg").text("密码不合法");
					return false;
				}
				// 验证确认密码：和密码相同
				var passwordConfirm =$("#repwd").val();
				if( passwordConfirm != passwordText ){
					$("span.errorMsg").text("确认密码与原密码不相同");
					return false;
				}
				// 邮箱验证：xxxxx@xxx.com
				var emailText = $("#email").val();
				//2.验证用户名合法的正则表达式
				var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
				//3.验证用户名是否合法
				if(!emailPatt.test(emailText)){
					$("span.errorMsg").text("邮箱不合法");
					return false;
				}

				// 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
				// var codeText = $("#code").val();
				// $("span.errorMsg").text("");


			});
			//给谷歌验证码实现单击事件，使其点击切换
			$("#code_img").click(function (){
				this.src = "${basePath}kaptcha.jpg?d=" + new Date();
			});
		});


	</script>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
									<span class="errorMsg">
										<%=request.getAttribute("message")==null?"":request.getAttribute("message")%>
									</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   autocomplete="off" tabindex="1" name="username" id="username"
										   value="<%=request.getAttribute("username")==null?"":request.getAttribute("username")%>" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   autocomplete="off" tabindex="1" name="email" id="email"
										   value="<%=request.getAttribute("email")==null?"":request.getAttribute("email")%>" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 100px;" name="code" id="code"
										   value="<%=request.getAttribute("code")==null?"":request.getAttribute("code")%>"/>
									<img  id="code_img" alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px; height: 40px;width: 100px">
									<br />

									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%@include file="/pages/common/foot.jsp"%>
</body>
</html>