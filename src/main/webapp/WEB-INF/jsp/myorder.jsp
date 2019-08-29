<%--
  Created by IntelliJ IDEA.
  User: 96433
  Date: 2018/3/27
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>我的订单</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/myOrder.css"/>">
</head>
<body>
<div>
    <div  class="my-body" style="width: 50%">
        <div class="body-title">
            <div class="row" style="position: relative;">
                <div class="col-md-4" >
                    <img class="logo2-img" src="<c:url value="/resources/images/head/logo2.png"/>">
                    <span class="imgTitle">商品结算</span>
                </div>
            </div>
        </div>
        <div style="margin: 10px 10px 0 10px;">
            <%--收货人信息 start--%>
            <div class="row myItem">
                <input type="hidden" id="selectAddrId" value="${addressList[0].addrId}">
                <div class="border-b min_h_80">
                    <div class="item-title">收货人信息</div>
                    <div>
                        <ul id="myAddress">
                            <c:forEach items="${addressList}" var="userAddress" varStatus="index">
                                <li class="<c:if test="${index.index != 0}">display_none</c:if> ovf-auto">
                                    <div class="<c:if test="${index.index eq 0}">item-selected</c:if>
                                     order_item addr_title col-md-2"  data-addrid="${userAddress.addrId}">
                                        <b></b>
                                        <a href="javacript:;">${userAddress.receiptUserName}</a>
                                    </div>
                                    <div class="col-md-4 col-md-offset-1">${userAddress.address}</div>
                                    <div class="col-md-2">${userAddress.receiptPhone}</div>
                                    <c:if test="${userAddress.isDefaultAddr != 0}">
                                        <div class="col-md-2 display_none">
                                            <a data-addrId="${userAddress.addrId}" class="setDefault" href="javascript:;">设为默认地址</a>
                                        </div>
                                    </c:if>
                                    <div class="col-md-1">
                                        <a data-addrId="${userAddress.addrId}" class="editAddr" <%--data-toggle="modal" --%>href="javascript:;">编辑</a>
                                    </div>
                                </li>
                            </c:forEach>
                            <div><a id="show_hide" href="javascript:;">更多地址</a></div>
                        </ul>
                    </div>
                </div>
            </div>
            <%--支付方式 start--%>
            <div class="row myItem">
                <input type="hidden" id="paySelect" value="在线支付">
                <div class="border-b">
                    <div class="item-title">支付方式</div>
                    <ul>
                        <li class="order_item pay-item float-l">
                            <a href="javascript:;">货到付款</a>
                            <b></b>
                        </li>
                        <li class="order_item pay-item float-l item-selected">
                            <a href="javascript:;">在线支付</a>
                            <b></b>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="row myItem">
                <input type="hidden" id="postSelect" value="买多多快递">
                <div class="border-b">
                    <div class="item-title">配送方式</div>
                    <ul>
                        <li class="order_item post-item float-l item-selected">
                            <a href="javascript:;">买多多快递</a>
                            <b></b>
                        </li>
                        <li class="order_item post-item float-l">
                            <a href="javascript:;">上门自取</a>
                            <b></b>
                        </li>
                    </ul>
                </div>
            </div>
            <%--送货清单 start--%>
            <div class="row myItem">
                <div class="border-b min_h_80">
                    <div class="ovf-auto item-title">
                        <div class="float-l">送货清单</div>
                        <div class="float-r"><a href="<c:url value="/servlet/shopCar/shopCarAction_service.action"/>">返回修改购物车</a></div>
                    </div>
                    <div class="ovf-auto">
                        <div class="sh_item col-md-12">
                            <div>货物清单</div>
                            <div class="table-responsive">
                                <table class="table" id="myTable">
                                    <tbody>
                                    <c:forEach items="${checkedList}" var="checkedCar" varStatus="i">
                                        <tr class="pIds" data-pid="${checkedCar.pid}">
                                            <td><img src="<c:url value="${checkedCar.image_path}"/>">${checkedCar.name}</td>
                                            <td>${checkedCar.price}&nbsp;￥</td>
                                            <td>x${checkedCar.pnum}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--底部 start--%>
        <div>
            <div>
                <div class="col-md-4 col-md-offset-8 padding_10_0">商品数量：<span class="bottom_text">${pnum}</span>&nbsp;件</div>
            </div>
            <div>
                <input type="hidden" id="totalPri" value="${totalPri}">
                <div class="col-md-4 col-md-offset-8 padding_10_0">总价：<span class="bottom_text">${totalPri}</span>&nbsp;￥</div>
            </div>
            <div>
                <div class="col-md-4 col-md-offset-8" id="submitOrder_parent"><a href="javacript:;" id="submitOrder">提交订单</a></div>
            </div>
        </div>
        <%--底部 end--%>
    </div>
</div>
<div class="modal fade" id="myAddrModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">编辑地址</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="getProname" class="col-sm-3 control-label">收货人：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="getProname" placeholder="姓名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="getProaddr" class="col-sm-3 control-label">收货地址：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="getProaddr" placeholder="地址">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="getProphone" class="col-sm-3 control-label">联系电话：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="getProphone" placeholder="电话">
                        </div>
                    </div>
                    <input type="hidden" id="addrId"/>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="editAddress">提交更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script src="<c:url value="/resources/js/order.js"/>"></script>
</body>
</html>
