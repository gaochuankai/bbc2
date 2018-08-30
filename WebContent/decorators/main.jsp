<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp" %>
<%@ include file="/common/style_script.jsp"%>
<title>意坚教育-论坛</title>
<decorator:head/>
</head>
<body 
<decorator:getProperty property="body.id" writeEntireProperty="true"/> 
<decorator:getProperty property="body.class" writeEntireProperty="true"/>>
<!-- <div id='Loading' style="position: absolute; z-index: 1000; top: 0px; left: 0px;width: 100%; height: 100%;position:fixed; background:#FFF; text-align: center;"> 
        <div style="top:45%;margin:0 auto; position: relative;background:url('${staticURL }/content/images/loadingPic.gif');width:32px;height:32px"></div>
</div>  -->
<%@include file="../common/header.jsp" %>
<div id="main_content_wrap" class="container_12">
<decorator:body/>
</div>
<%@include file="../common/footer.jsp" %>
</body>
</html>

