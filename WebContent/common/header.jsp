<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="security" uri="/tld/security.tld" %> --%>
<!DOCTYPE html>
<!-- 布局顶部登录状态 -->
<div id="main">
    <table cellSpacing=0 cellPadding=0 width="100%" border=0>
        <!--头部图片-->
        <tr height=80>
            <td width=260><IMG height=80 src="images/logo.gif" width=260></td>
            <td>
                <div class="search d1">
                    <form action="index" method="post">
                        <input type="text" placeholder="搜索文章" name="context" >
                        <button type="submit"></button>
                    </form>
                </div>
            </td>

            <c:if test="${sessionScope.user!=null}">
                <td
                    style="FONT-SIZE: 12px; FONT-WEIGHT: bold; COLOR: #000; PADDING-TOP: 20px; PADDING-RIGHT: 20px"
                    align=right>欢迎 <font color="red">张三</font>
                    登录!&nbsp;| <a class="one" href="personal">个人中心</a>|
                    <a href="logout">退出</a>|
                </td>
            </c:if>
            <c:if test="${null==user}">
                <td
                    style="FONT-SIZE: 12px; FONT-WEIGHT: bold; COLOR: #000; PADDING-TOP: 20px; PADDING-RIGHT: 20px"
                    align=right>您还未登录!&nbsp;| <a class="one" href="login.jsp">登录</a>|
                    <a href="register.jsp">注册</a>|
                </td>
            </c:if>



        </tr>
    </table>
    <table cellSpacing=0 cellPadding=0 width="100%" border=0>
        <tr bgColor=#1c5db6 height=4>
            <td></td>
        </tr>
    </table>
    <br />
</div>