<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <div id="login">
		<div id="login-top">
			<h2>登录</h2>
			<a href="register.jsp"> 还没有账号，立即注册</a>
		</div>
		<form action="login" method="post">
			<div class="login-item">
				<label>用户名<span>*</span></label>
				<input class="login-txt" name="username"  placeholder="用户名" value="${cookie.loginName.value}">
			</div>
			<div class="login-item">
                <label>密码<span>*</span></label>
                <input class="login-txt" type="password" name="password" placeholder="登录密码"value="${cookie.password.value}"/>
			</div>
			<div class="login-item">
                <label>验证码<span>*</span></label>
                <input class="login-txt login-code" name="code" placeholder="验证码" /> 
				<img src="${pageContext.request.contextPath }/tesCode" onclick="createCode();" alt="点击修改验证码" title="点击刷新验证码">
			</div>
			<div class="login-item">
                <input type="checkbox" name="saveUserName" value="1" ${cookie.loginName.value eq null ? '' : 'checked' }> 
                记住我&nbsp;&nbsp; <a href="#">忘记密码？</a>
			</div>
			<div class="login-item">
				<button type="submit" class="login-btn">登录</button>
			</div>
			<div class="login-item">
				<span style="color:red;">${data.errorMsg }</span>
			</div>
		</form>
    </div>
    <script>
        $(function (){
		});

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