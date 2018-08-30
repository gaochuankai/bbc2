<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 布局中部 -->
<div id="middle">
    <!-- 布局中部左边 -->
    <div id="index_left">
        <table height="100%" >
            <tr>
                <td vAlign=top align=middle>
                    <table>
                        <tr>
                            <td><a href="index">论坛首页</a></td>
                        </tr>

                    </table>
                    <table>
						<c:forEach items="${nodeTypeList }" var="nodeTypeEntity">
							<tr>
	                            <td>
	                            	<a href="index?id=1&context=${context}">${nodeTypeEntity.typeName }</a>
	                            </td>
	                        </tr>
						</c:forEach>
                    </table>
                </td>
            </tr>
        </table>
    </div>
    <!-- 布局中部右边 -->
    <div id="index_center">
        <table class="text_list" id="index_center">
            <tr>
                <th>标题</th>
                <th>作者</th>
                <th>回复</th>
                <th>类型</th>
                <th>发帖时间</th>
            </tr>
            <c:forEach items="${nodeInfoList }" var="nodeInfoEntity">
            	<tr>
	                <td>
	                	<a href="textDetails?id=${text.textid}" target="_blank">${nodeInfoEntity.title }</a>
	                </td>
	                <td>
	                	<a href="#" target="_blank">${nodeInfoEntity.userEntity.name }</a>
	                </td>
	                <td>${nodeInfoEntity.replyCount }</td>
	                <td>${nodeInfoEntity.nodeTypeEntity.typeName }</td>
	                <td>
	                	<fmt:formatDate value="${nodeInfoEntity.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
	                </td>
	            </tr>
            </c:forEach>
        </table>
        <div class="page">
            <ul id="pagination-flickr">
                <li class="pre-off"><a href="index?page=${page.pageNow-1}&context=${context}&id=${id}">上一页</a></li>
                <li><a href="">[1]</a></li>
                <li><a href="">[2]</a></li>
                <li><a href="">[3]</a></li>
                <li><a href="">[4]</a></li>
                <li><a href="">[5]</a></li>
                <li><a href="">[6]</a></li>
                <li class="next"><a href="index?page=${page.pageNow+1}&context=${context}&id=${id}">下一页</a></li>
            </ul>
        </div>
    </div>
</div>