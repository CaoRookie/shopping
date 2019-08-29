<%--
  Created by IntelliJ IDEA.
  User: pq
  Date: 2017/7/14
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>错误页面</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/errorPage/errorPage.css"/> ">
</head>
<body>
<div id="content" class="cf">
    <div id="duobei_wrap">
        <div class="logo_wrap cf">
            <a href="<c:url value="/servlet/home/homeAction_home.action"/>" id="logo"></a>
            <div id="title"><i>买</i><i>多</i><i>多</i></div>
        </div>
        <div class="reason">
            <p class="not_found_tip">Error  :( 很抱歉，亲，您访问的页面出现错误!</p>
            <p class="not_found_tip">请联系管理员，或稍后重试</p>
            <p class="not_found_tip"><span id="second"></span>秒后将自动跳转到首页</p>
            <p class="possible">错误详情：</p>
            <ul>
                <li><%=exception.getMessage()%></li>
            </ul>
        </div>
    </div>
    <!-- #duobei_wrap -->
    <div class="line"></div>

    <div class="not_found">
        <i class="ribbon"></i>
        <div class="not_found_404 cf">
            <span>4</span>
            <span>0</span>
            <span>4</span>
        </div>
        <div class="btn">
            <a href="<c:url value="/servlet/home/homeAction_home.action"/>" class="button button-rounded">看看首页</a>
            <a href="#" class="button button-rounded cancle">返回上页</a>
        </div>
    </div>
</div>

</body>
<%-- 定义全局变量 --%>
<script type="application/javascript">
    var ROOT_PATH = "${pageContext.request.contextPath}";
</script>
<script src="<c:url value="/resources/js/other/jquery-3.2.1.min.js"/>"></script>
<script src="<c:url value="/resources/js/other/error-page.js"/>"></script>
</html>
