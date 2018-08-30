<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<!-- 布局中部 -->
	<div id="login">
		<div id="login-top">
			<h2>注册</h2>
			<a href="login.jsp">已有账号，立即登陆</a>
		</div>
		<form action="register" method="post" id="myform" onsubmit="return checkRemember();">
			<input type="hidden" name="refer" value="">
			<div class="login-item">
				<label>昵称<span class="required">*</span></label> <input
					class="login-txt" type="text" id="usernick" autofocus="autofocus"
					autocomplete="off" placeholder="昵称" name="name"
					class="login-con ">

			</div>
			<span id="nick" style="font-size: 12px; float: right"></span>
			<div class="login-item">
				<label>用户名<span class="required">*</span></label> <input
					class="login-txt" type="text" id="username" autofocus="autofocus"
					autocomplete="off" placeholder="学号/手机/邮箱" name="loginName"
					class="login-con " onblur=testUsername(this)>
			</div>
			<span id="usernameinfo" style="font-size: 12px; float: right"></span>
			<div class="login-item">
				<label>密码<span class="required">*</span></label> <input
					class="login-txt" type="password" id="password" autocomplete="off"
					placeholder="登录密码" name="password" class="login-con ">
			</div>
			<span id="pwd" style="font-size: 12px; float: right"></span>
			<div class="login-item">
				<label class="login-label">确认密码<span class="required">*</span></label>
				<input class="login-txt" type="password" id="repasswordFromInput"
					autocomplete="off" placeholder="登录密码" name="repassword"
					class="login-con ">
			</div>
			<span id="repasswordFromSpan" style="font-size: 12px; float: right"></span>
			<div class="login-item">
				<label class="login-label">验证码<span class="required">*</span></label>
				<input class="login-txt login-code" id="scode" name="imgcode"
					class="login-con login-code" placeholder="验证码" /> 
				<img src="${request.servlet.contextPath }/tesCode" onclick="createCode();"
					alt="点击获取验证码" title="点击刷新验证码" onclick="" id="img_scode"
					class="code_box">
			</div>

			<div class="login-item">
				<input name="remember" value="1" checked="3" type="checkbox" id="remember" onclick="checkRemember()">
				我已阅读并同意&nbsp;&nbsp; <a href="#">《用户协议》</a>
			</div>
			<div class="login-item">
				<button type="submit" class="login-btn" onclick="">注册</button>
			</div>
			<div class="login-item">
				<span style="color:red;">${errorMsg }</span>
			</div>
		</form>
	</div>

	<script type="text/javascript">
	
		var nameFlag = false;
		var passwordFlag = false;
		var rePasswordFlag = false;
	
		$(function (){
			
		});

		function checkRemember(){
			if(!checkName()){
				alert("对不起，昵称只能中英文，数字，下划线，减号!");
				return flag;
			}
			if(nameFlag && passwordFlag && rePasswordFlag){
				
			}
			return document.getElementById("remember").checked;
		}
		
		//检查昵称
		//只能中英文，数字，下划线，减号
		function checkName(){
			reg = /^[\u4e00-\u9fa5A-Za-z0-9-_]*$/;
			str = $('#name').val();
			if(!reg.test(str)){
				alert("对不起，只能中英文，数字，下划线，减号!");
			}
			nameFlag = true;
		}
		
		// 校验邮箱
		function isEmail(){
			var str = document.getElementById('str').value.trim();
			if(str.length!=0){
				reg=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
				if(!reg.test(str)){
					alert("对不起，您输入的字符串类型格式不正确!");//请将“字符串类型”要换成你要验证的那个属性名称！
				}
			}
		}
		
		//校验密码
		function checkPassWordHeader(){
			var value = $('#password').val();
			// 0： 表示第一个级别 1：表示第二个级别 2：表示第三个级别
			// 3： 表示第四个级别 4：表示第五个级别
			var modes = 0;
			if(value.length < 8){//最初级别
				return modes;
			}
			if(/\d/.test(value)){//如果用户输入的密码 包含了数字
				modes++;
			}
			if(/[a-zA-Z]/.test(value)){//如果用户输入的密码 包含了小写的a到z
				modes++;
			}
			if(/\W/.test(value)){//如果是非数字 字母 ---W下划线
				modes++;
			}
			if(modes<2){
				alert("密码不能纯数字！");
				return ;
			}
			passwordFlag = true;
		}
		
		function checkRePassword(){
			
		}
		
		//获取验证码
		function createCode(){
			var imgSrc = $("#code");
		    var src = imgSrc.attr("src");
		    
		    imgSrc.attr("src",chgUrl(src));
		}
		
		//时间戳    
		//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳    
		function chgUrl(url){
		    var timestamp = (new Date()).valueOf();
		    urlurl = url.substring(0,35);
		    if((url.indexOf("&")>=0)){
		        urlurl = url + "×tamp=" + timestamp;
		    }else{
		        urlurl = url + "?timestamp=" + timestamp;
		    }
		    return urlurl;    
		}
		
	</script>
