<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2018/1/11
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<nav class="navbar navbar-default navbar-static-top" style="margin-bottom: 15px;">
    <div class="container-fluid">
        <div class="navbar-header w-20">
            <a class="navbar-brand" style="padding: 0 15px;" href="javascript:void(0)">
                <img class="h-50" src="<c:url value="/resources/images/head/logo.png"/>">
            </a>
        </div>
        <div class="collapse navbar-collapse">
            <ul id="nav-bottom" class="nav navbar-nav common-nav">
                <li class="active">
                    <a class="text-center" href="<c:url value="/servlet/home/homeAction_home.action"/>">首页</a>
                </li>
                <li>
                    <a class="text-center" href="<c:url value="/servlet/classification/classificationAction_service.action"/>">分类</a>
                </li>
                <%--<li>
                    <a class="text-center" href="<c:url value="/sreach/sreachAction_service.action"/>">搜索</a>
                </li>--%>
                <li>
                    <a class="text-center" href="<c:url value="/servlet/shopCar/shopCarAction_service.action"/>">购物车</a>
                </li>
                <li>
                    <a class="text-center" href="<c:url value="/servlet/userMessage/userMessageAction_service.action"/>">我的</a>
                </li>
                <c:if test="${_SESSION_USER_INFO.user_access eq 777}">
                    <li>
                        <a class="text-center" href="<c:url value="/servlet/systemAction/home.action"/>">系统管理</a>
                    </li>
                </c:if>
            </ul>
            <ul class="nav navbar-nav navbar-right w-20">
                <c:if test="${_SESSION_USER_INFO != null}">
                    <li><a id="userInfo" href="javascript:;">欢迎您，${_SESSION_USER_INFO.user_name}</a></li>
                </c:if>
                <c:if test="${_SESSION_USER_INFO == null}">
                    <li><a href="<c:url value="/login/loginAction_service.action"/>">亲，请登录!</a></li>
                </c:if>
                <li><a href="<c:url value="/login/loginAction_logout.action"/>">退出</a></li>
                <li><a href="javascript:;" id="register">免费注册</a></li>
            </ul>
        </div>
    </div>
</nav>