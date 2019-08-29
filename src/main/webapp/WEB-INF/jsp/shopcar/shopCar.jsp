<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2018/1/15
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>购物车</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/shopCar/shopCar.css"/>"/>
</head>
<body>
<div>
    <div class="my-body" style="width: 65%">
        <c:choose>
            <c:when test="${_SESSION_USER_INFO !=null}">
                <div class="body-title">
                    <div class="row" style="position: relative;">
                        <div class="col-md-4" >
                            <img  class="logo2-img" src="<c:url value="/resources/images/head/logo2.png"/>">
                            <span class="imgTitle">购物车</span>
                        </div>
                        <div class="delCar">
                            <a href="javascript:;" id="delChecked" style="color: white;">删除所选</a>
                        </div>
                    </div>
                </div>
                <div class="table-responsive">
                    <table id="carReport"></table>
                    <div>
                        <div class="carLi float-r bg-r"><a href="javascript:;" id="submitCar">去结算</a></div>
                        <div class="carLi" style="margin-right: 2%;float: right;">
                            <span>总价：</span>
                            <span class="totalPri">￥</span>
                            <span class="totalPri" id="totalPri" ></span>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div style="border-bottom: 1px solid #e6e6e6;">
                    <div class="cen-div">
                        <div><h3>您还未登录！请登录</h3></div>
                        <div>
                            <a href="<c:url value="/login/loginAction_service.action"/>">已有账号？登录</a>
                        </div>
                        <div>
                            <a href="javascript:void(0)" id="register">还未注册？立即注册</a>
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<script type="application/javascript" src="<c:url value="/resources/js/shopCar/shopCar.js"/>"></script>
</body>
</html>
