<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2018/1/10
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/login/login.css"/>"/>
</head>
<body>
<div class="header">
    <div id="logo">
        <a href="<c:url value="/servlet/home/homeAction_home.action"/>">
            <img style="height: 60px;" src="<c:url value="/resources/images/head/logo.png"/>" alt="买多多" >
        </a>
        <b>欢迎登录</b>
    </div>
    <a id="feedback" class="float-r" href="#">
        <img src="<c:url value="/resources/images/head/talk_icon.png"/>">
        登录页面，问题反馈
    </a>
</div>
<div class="content">
    <div class="tips-wrapper">
        <div class="tips-inner">
            <i class="icon-tips"></i>
            <p>依据《网络安全法》，为保障您的账户安全和正常使用，请尽快完成手机号验证！将更有利于保护您的个人隐私。</p>
        </div>
    </div>
    <div class="login-wrap" style="background: #e93854">
        <div class="w">
            <div class="login-form">
                <div class="tips-wrapper">
                    <div class="tips-inner">
                        <div class="cont-wrapper">
                            <i class="icon-tips"></i><p>买多多不会以任何理由要求您转账汇款，谨防诈骗。</p>
                        </div>
                    </div>
                </div>
                <div class="login-box">
                    <h3>账户登录</h3>
                    <div class="form">
                        <form id="login-form" method="post" action="<c:url value="/servlet/home/homeAction_home.action"/>">
                            <div class="form-group">
                                <label for="username" class="sr-only"></label>
                                <div class="input-group w-100">
                                    <div class="input-group-addon name-label" style="width: 38px;"></div>
                                    <input id="username" name="username" class="form-control" type="text"  placeholder="邮箱/用户名/已验证手机" style="height: 38px;"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="sr-only"></label>
                                <div class="input-group w-100">
                                    <div class="input-group-addon pwd-label" style="width: 38px;"></div>
                                    <input type="password" name="password" class="form-control" id="password" placeholder="密码" style="height: 38px;"/>
                                </div>
                            </div>
                            <div class="form-group" style="text-align: right;">
                                <a href="#">忘记密码</a>
                            </div>
                            <div class="form-group">
                                <div class="login-btn">
                                    <a href="javascript:;" id="login_btn">登录</a>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="coagent">
                        <ul>
                            <li>
                                <a href="javascript:;" onclick="/*window.location='//qq.jd.com/new/qq/login.aspx'+window.location.search;return false;*/">
                                    <b class="QQ-icon"></b>
                                    <span>QQ</span>
                                </a>
                                <span class="line">|</span>
                            </li>
                            <li>
                                <a href="javascript:;" onclick="/*window.location='//qq.jd.com/new/wx/login.action'+window.location.search;return false;*/">
                                    <b class="weixin-icon"></b>
                                    <span>微信</span>
                                </a>
                            </li>
                            <li class="extra-r">
                                <div>
                                    <div class="regist-link">
                                        <a href="javascript:;" id="register" target="_blank">
                                        <b></b>
                                            立即注册</a>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="login-banner">
            <div class="w">
                <div id="banner-bg"></div>
            </div>
        </div>
    </div>
</div>
<%-- 定义全局变量 --%>
<script type="application/javascript">
    var SPRINGMVC_MAPPING = "";
    var ROOT_PATH = "${pageContext.request.contextPath}" + SPRINGMVC_MAPPING;
</script>
<script src="<c:url value="/resources/js/login/login.js"/>"></script>

</body>
</html>
