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
    <title>收银台</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/login/login.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/payment.css" />">
</head>
<body>
<div class="header">
    <div id="logo">
        <a href="<c:url value="/servlet/home/homeAction_home.action"/>">
            <img style="height: 60px;" src="<c:url value="/resources/images/head/logo.png"/>" alt="买多多" >
        </a>
        <b>收银台</b>
    </div>
</div>
<div class="content">
    <div style="width: 40%; margin: auto;">
        <form id="payFrom" class="form-horizontal">
            <div class="item"><span>订单号：</span><span>${orderNumber}</span></div>
            <div class="item"><span>总金额：</span><span class="c-red">${totalPrice}&nbsp;￥</span> </div>
            <div class="item">
                <label class="radio-inline">
                    <input type="radio" name="pay" id="weixin" value="weixin" checked>微信
                </label>
                <label class="radio-inline">
                    <input type="radio" name="pay" id="zhifubao" value="zhifubao">支付宝
                </label>
            </div>
            <div class="item">
                <a href="javascript:;" id="subFromBtn" class="btn btn-default">立即支付</a>
            </div>
        </form>
    </div>
</div>
<script src="<c:url value="/resources/js/payment.js"/>"></script>
</body>
</html>