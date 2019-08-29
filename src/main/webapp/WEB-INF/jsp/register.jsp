<%--
  Created by IntelliJ IDEA.
  User: 96433
  Date: 2018/4/12
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>用户注册</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/login/login.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/login/register.css" />">
</head>
<body>
<div class="header">
    <div id="logo">
        <a href="<c:url value="/servlet/home/homeAction_home.action"/>">
            <img style="height: 60px;" src="<c:url value="/resources/images/head/logo.png"/>" alt="买多多" >
        </a>
        <b>欢迎注册</b>
    </div>
    <a id="feedback" class="float-r" href="<c:url value="/login/loginAction_service.action"/>">
        已有账号？登录
    </a>
</div>
<div class="content">
    <div style="width: 40%; margin: auto;">
        <form id="registerFrom" class="form-horizontal">
            <div class="form-group">
                <label for="username" class="col-sm-2 control-label">用户名</label>
                <div class="col-sm-10">
                    <input type="text" name="username" class="form-control" id="username" placeholder="用户名">
                </div>
            </div>
            <div class="form-group">
                <label for="sPassword" class="col-sm-2 control-label">设置密码</label>
                <div class="col-sm-10">
                    <input type="password" name="sPassword" class="form-control" id="sPassword" placeholder="设置密码">
                </div>
            </div>
            <div class="form-group">
                <label for="qPassword" class="col-sm-2 control-label">确认密码</label>
                <div class="col-sm-10">
                    <input type="password" name="qPassword" class="form-control" id="qPassword" placeholder="确认密码">
                </div>
            </div>
            <div class="form-group">
                <label for="phone" class="col-sm-2 control-label">手机号码</label>
                <div class="col-sm-10">
                    <input type="text" name="phone" class="form-control" id="phone" placeholder="手机号码">
                </div>
            </div>
            <div class="form-group">
                <label for="myemail" class="col-sm-2 control-label">邮箱</label>
                <div class="col-sm-10">
                    <input type="Email" name="myemail" class="form-control" id="myemail" placeholder="邮箱">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <%--<a href="javascript:;" id="subFromBtn" class="btn btn-default">立即注册</a>--%>
                    <button type="submit" id="subFromBtn" class="btn btn-default">立即注册</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="<c:url value="/resources/js/login/register.js"/>"></script>
</body>
</html>
