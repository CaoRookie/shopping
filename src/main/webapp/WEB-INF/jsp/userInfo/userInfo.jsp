<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2018/1/15
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>我的信息</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/userInfo/userInfo.css"/>">
</head>
<body>
<div>
    <div class="my-body">
        <div class="body-title">
            <div class="row" style="position: relative;">
                <div class="col-md-4" >
                    <img class="logo2-img" src="<c:url value="/resources/images/head/logo2.png"/>">
                    <span class="imgTitle">个人信息管理</span>
                </div>
            </div>
        </div>
        <div>
            <div class="border-b">
                <div class="mytitle border-b">
                    <span>收货地址：</span>
                    <div class="float-r h-30">
                        <a id="addAddr" href="javascript:;" style="line-height: 30px;padding: 0 10px;">添加地址</a>
                    </div>
                </div>
                <table id="myAddress" class="order-tb"></table>
            </div>
            <div>
                <div class="mytitle">用户信息：</div>
                <table id="userMessage" class="order-tb"></table>

            </div>
            <div>
                <div class="mytitle">我的订单：</div>
                <table id="myOrder" class="order-tb"></table>
                <%--<table id="myOrder" class="order-tb">
                    <thead>
                    <tr>
                        <th>订单详情</th>
                        <th>收货人</th>
                        <th>金额</th>
                        <th>订单状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <c:forEach items="${orderList}" var="order" varStatus="i">
                        <tbody>
                        <tr class="sep-row"><td colspan="5"></td></tr>
                        <tr class="tr-th"><td colspan="5">
                            <span class="gap"></span>
                            <span class="dealtime" title="${order.date}">${order.date}</span>
                            <span class="number">订单号：${order.orderNumber}</span>
                            <a href="#none" clstag="click|keycount|orderlist|dingdanshanchu" class="order-del" _orderid="73046079298" _passkey="9E13BE107E7EFACE595807069802D687" title="删除" style="display: none;"></a>
                        </td></tr>
                        <tr class="tr-bd"></tr>
                        </tbody>
                    </c:forEach>
                </table>--%>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="orderDetailModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="modalLabel">订单详情</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="dt">
                        <h4>商品清单</h4>
                    </div>
                    <table id="productList"></table>
                </div>
                <div class="row" style="margin-top: 10px;">
                    <div class="col-md-4 bo-r">
                        <div class="dt">
                            <h4>收货人信息</h4>
                        </div>
                        <div class="dd">
                            <div class="de-item">
                                <span class="lablex">收货人：</span>
                                <div class="info-rcol" id="valName"></div>
                            </div>
                            <div class="de-item">
                                <span class="lablex">地址：</span>
                                <div class="info-rcol" id="valAddr"></div>
                            </div>
                            <div class="de-item">
                                <span class="lablex">手机号码：</span>
                                <div class="info-rcol" id="valPhone"></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 bo-r">
                        <div class="dt">
                            <h4>付款信息</h4>
                        </div>
                        <div class="dd">
                            <div class="de-item">
                                <span class="lablex">付款方式：</span>
                                <div class="info-rcol" id="valPay"></div>
                            </div>
                            <div class="de-item">
                                <span class="lablex">总金额：</span>
                                <div class="info-rcol" id="valTotalPrice"></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="dt">
                            <h4>配送信息</h4>
                        </div>
                        <div class="dd">
                            <div class="de-item">
                                <span class="lablex">配送信息：</span>
                                <div class="info-rcol" id="valPost"></div>
                            </div>
                            <div class="de-item">
                                <span class="lablex">运费：0&nbsp;￥</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script type="application/javascript" src="<c:url value="/resources/js/userInfo/userInfo.js"/>"></script>
</body>
</html>
