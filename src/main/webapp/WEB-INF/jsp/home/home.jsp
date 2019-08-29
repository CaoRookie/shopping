<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2018/1/10
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <meta name="description" content="商品列表">
    <meta name="author" content="caoyuan">
    <title>首页</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/home/home.css"/>"/>
</head>
<body>
<script>
    <%--var user=${_SESSION_USER_INFO};--%>
    var user_name='${_SESSION_USER_INFO.user_name}';
    var user_phone='${_SESSION_USER_INFO.user_phone}';
    var imgPath=$('#path').val();
</script>

<div class="container-fluid" style="margin-bottom: 65px;">
    <div class="row row1">
        <div class="col-md-3">
            <div class="module-title">
                <div class="classif-title">
                    <span>新闻信息</span>
                </div>
            </div>
            <div id="news">
                <ul class="nav nav-tabs">
                    <c:forEach items="${newsList}" var="news" varStatus="index">
                        <li class="col-md-4 <c:if test="${index.index eq 0}">active</c:if>">
                            <a href="#${news.title}" data-toggle="tab">${news.title}</a>
                        </li>
                    </c:forEach>
                </ul>
                <div class="tab-content">
                    <c:forEach items="${newsList}" var="news" varStatus="index">
                        <div id="${news.title}" class="tab-pane fade in <c:if test="${index.index eq 0}">active</c:if>">
                            <p class="new-content">${news.content}</p>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="module-title h-400">
                <div id="myCarousel" class="carousel slide h-100" data-ride="carousel">
                    <!-- 轮播（Carousel）指标 -->
                    <ol class="carousel-indicators">
                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        <li data-target="#myCarousel" data-slide-to="1"></li>
                        <li data-target="#myCarousel" data-slide-to="2"></li>
                        <li data-target="#myCarousel" data-slide-to="3"></li>
                        <li data-target="#myCarousel" data-slide-to="4"></li>
                    </ol>
                    <!-- 轮播（Carousel）项目 -->
                    <div class="carousel-inner h-100"  role="listbox">
                        <div class="item active">
                            <a href="<c:url value="/servlet/classification/classificationAction_classifi.action"/>">
                                <img src="<c:url value="/resources/images/home/lunbo1.jpg"/>">
                            </a>
                        </div>
                        <div class="item">
                            <a href="<c:url value="/servlet/classification/classificationAction_classifi.action"/>">
                                <img src="<c:url value="/resources/images/home/lunbo2.jpg"/>">
                            </a>
                        </div>
                        <div class="item">
                            <a href="<c:url value="/servlet/classification/classificationAction_classifi.action"/>">
                                <img src="<c:url value="/resources/images/home/lunbo3.jpg"/>">
                            </a>
                        </div>
                        <div class="item">
                            <a href="<c:url value="/servlet/classification/classificationAction_classifi.action"/>">
                                <img src="<c:url value="/resources/images/home/lunbo4.jpg"/>">
                            </a>
                        </div>
                        <div class="item">
                            <a href="<c:url value="/servlet/classification/classificationAction_classifi.action"/>">
                                <img src="<c:url value="/resources/images/home/lunbo5.jpg"/>">
                            </a>
                        </div>
                    </div>
                    <!-- 轮播（Carousel）导航 -->
                    <a class="carousel-control left" href="#myCarousel" role="button" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control right" href="#myCarousel" role="button" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="module-title">
                <div class="classif-title">
                    <span>网站公告</span>
                </div>
            </div>
            <div class="announcements">
                <ol>
                    <c:forEach items="${announcements}" var="ann" varStatus="i">
                        <li>
                            <a class="showAnno" data-content="${ann.content}" href="javascript:;">${ann.name}：${ann.content}</a>
                        </li>
                    </c:forEach>
                </ol>
            </div>
        </div>
    </div>
    <div class="row1">
        <div class="col-md-12 module">
            <span class="module-title">热门商品</span>
            <span class="module-title-des">用户关注较高的商品</span>
            <%--<a class="more float-r" href="#">详情<b></b></a>--%>
        </div>
        <table class="table table-bordered">
            <tbody>
                <tr>
                    <s:iterator value="clickList" status="i" var="product">
                        <td class="w-25">
                            <a href="javascript:;" data-item="${product}" class="product-img">
                                <img src="<c:url value="${image_path}"/>"><br/>
                            </a>
                            <div class="float-l">
                                <span><s:property value="name"/></span><br/>
                                <span><s:property value="price"/></span><br/>
                            </div>
                            <a href="javascript:;" class="float-r shopcar" data-pid="<s:property value="id"/>">
                                <img src="<c:url value="/resources/images/common/addshopcar.png"/>">
                            </a>
                        </td>
                    </s:iterator>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="row1">
        <div class="col-md-12 module">
            <span class="module-title">销量最高</span>
            <span class="module-title-des">用心服务，用户的最爱</span>
            <%--<a class="more float-r" href="#">详情<b></b></a>--%>
        </div>
        <table class="table table-bordered">
            <tbody>
            <tr>
                <s:iterator value="saleHightProduct" status="i" var="product">
                    <td class="w-25">
                        <a href="javascript:;" data-item="${product}" class="product-img">
                            <img src="<c:url value="${image_path}"/>"><br/>
                        </a>
                        <div class="float-l">
                            <span><s:property value="name"/></span><br/>
                            <span><s:property value="price"/></span><br/>
                        </div>
                        <a href="javascript:;" class="float-r shopcar" data-pid="<s:property value="id"/>">
                            <img src="<c:url value="/resources/images/common/addshopcar.png"/>">
                        </a>
                    </td>
                </s:iterator>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="row1">
        <div class="col-md-12 module">
            <span class="module-title">新品上架</span>
            <span class="module-title-des">又是新的潮流</span>
            <%--<a class="more float-r" href="#">详情<b></b></a>--%>
        </div>
        <table class="table table-bordered">
            <tbody>
            <tr>
                <s:iterator value="newProduct" status="i" var="product">
                    <td class="w-25">
                        <a href="javascript:;" data-item="${product}" class="product-img">
                            <img src="<c:url value="${image_path}"/>"><br/>
                        </a>
                        <div class="float-l">
                            <span><s:property value="name"/></span><br/>
                            <span><s:property value="price"/></span><br/>
                        </div>
                        <a href="javascript:;" class="float-r shopcar" data-pid="<s:property value="id"/>">
                            <img src="<c:url value="/resources/images/common/addshopcar.png"/>">
                        </a>
                    </td>
                </s:iterator>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="row1">
        <div class="col-md-12 module">
            <span class="module-title">促销商品</span>
            <span class="module-title-des">超级大减价哦</span>
            <%--<a class="more float-r" href="#">详情<b></b></a>--%>
        </div>
        <table class="table table-bordered">
            <tbody>
            <tr>
                <s:iterator value="promotionsProduct" status="i" var="product">
                    <td class="w-25">
                        <a href="javascript:;" data-item="${product}" class="product-img">
                            <img src="<c:url value="${image_path}"/>"><br/>
                        </a>
                        <div class="float-l">
                            <span><s:property value="name"/></span><br/>
                            <span><s:property value="price"/></span><br/>
                        </div>
                        <a href="javascript:;" class="float-r shopcar" data-pid="<s:property value="id"/>">
                            <img src="<c:url value="/resources/images/common/addshopcar.png"/>">
                        </a>
                    </td>
                </s:iterator>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<c:import url="../common/productModel.jsp"/>
<input id="path" value="" type="hidden"/>
<script src="<c:url value="/resources/js/home/home.js"/>"></script>
</body>
</html>
