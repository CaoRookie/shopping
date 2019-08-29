<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2018/1/15
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <title>分类</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/classification/calssification.css"/>">
</head>
<body>
<div>
    <div class="my-body">
        <div class="body-title">
            <div class="row" style="position: relative;">
                <div class="col-md-4" >
                    <img class="logo2-img" src="<c:url value="/resources/images/head/logo2.png"/>">
                    <span class="imgTitle">商品分类</span>
                </div>
                <div class="col-md-8 class-sreach">
                    <form class="form-horizontal" method="post">
                        <div class="form-group">
                            <div class="col-sm-7">
                                <input type="text" class="form-control input-lg" id="sreachPro" name="sreachPro" placeholder="商品名/商品类型">
                            </div>
                            <div class="col-sm-5 sreachBtn">
                                <a id="sreachBtn" href="javascript:;" style="color: #ffffff;"><i class="glyphicon glyphicon-search"></i></a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                <div>
                    <div class="listTitle">
                        <span class="glyphicon glyphicon-list"></span>
                        <span>商品分类</span>
                    </div>
                </div>
                <ul class="nav nav-stacked myList">
                    <c:forEach items="${proTypeList}" var="proType" varStatus="index">
                        <li value="${proType.product_type_id}">
                            <a href="#myReport" data-toggle="tab"
                                    <c:if test="${proType.product_type_id eq 1}">
                                        class="a_checked">
                                    </c:if>
                                    <c:if test="${proType.product_type_id != 1}">
                                        class="color-white">
                                    </c:if>
                            <i class="iconfont ${proType.icon_name}"></i>
                                &nbsp;${proType.product_type_name}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div id="listContent" class="col-md-10">
                <div><span class="tablesTitle" id="tablesTitle">大小家电</span></div>
                <div  id="tb" class="tab-content">
                    <div id="myReport" class="tab-pane fade in active">
                        <table id="myTable"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/resources/js/classification/classification.js"/>"></script>
</body>
</html>
