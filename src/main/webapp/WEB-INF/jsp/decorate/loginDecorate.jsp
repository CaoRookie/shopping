<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2018/1/11
  Time: 9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html lang="zh_CN">
<head>
    <title>买多多-<sitemesh:write property='title'/></title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <meta name="format-detection" content="telephone=yes"/>
    <link rel="shortcut icon" href="<c:url value="/resources/images/head/logo1.png"/>" type="image/x-icon">
    <link rel="stylesheet" href="<c:url value="/resources/framework/bootstrap-3.3.7/dist/css/bootstrap.min.css"/>" />
    <link rel="stylesheet" href="<c:url value="/resources/framework/bootstrap-3.3.7/dist/css/bootstrap-theme.min.css"/>" />
    <link rel="stylesheet" href="<c:url value="/resources/framework/bootstrap-table-master/dist/bootstrap-table.min.css"/>" />
    <link rel="stylesheet" href="<c:url value="/resources/framework/layer-v3.1.1/layer/mobile/need/layer.css"/>" />
    <link rel="stylesheet" href="<c:url value="/resources/framework/jquery-easyui-1.5.2/themes/bootstrap/easyui.css"/>" />
    <link rel="stylesheet" href="<c:url value="/resources/framework/iconfont/iconfont.css"/>">
    <!--[if lt IE 9]>
    <script src="<c:url value="/resources/js/other/respond.min.js"/>"></script>
    <![endif]-->
    <!--[if lt IE 9]>
    　　<script src="<c:url value="/resources/js/other/html5shiv.min.js"/>"></script>
    <![endif]-->
    <script src="<c:url value="/resources/js/other/jquery-3.2.1.min.js"/>"></script>
    <script src="<c:url value="/resources/js/other/respond.min.js"/>"></script>
    <script src="<c:url value="/resources/js/other/html5shiv.min.js"/>"></script>
    <script src="<c:url value="/resources/js/other/jquery.validate.min.js"/>"></script>
    <script src="<c:url value="/resources/framework/bootstrap-3.3.7/dist/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/framework/bootstrap-table-master/dist/bootstrap-table.min.js"/>"></script>
    <script src="<c:url value="/resources/framework/bootstrap-table-master/dist/extensions/export/libs/FileSaver/FileSaver.min.js"/>"></script>
    <script src="<c:url value="/resources/framework/bootstrap-table-master/dist/extensions/export/libs/js-xlsx/xlsx.core.min.js"/>"></script>
    <script src="<c:url value="/resources/framework/bootstrap-table-master/dist/extensions/export/libs/tableExport.min.js"/>"></script>
    <script src="<c:url value="/resources/framework/bootstrap-table-master/dist/extensions/export/bootstrap-table-export.js"/>"></script>
    <script src="<c:url value="/resources/js/other/bootstrap-editable.js"/>"></script>
    <script src="<c:url value="/resources/framework/bootstrap-table-master/dist/extensions/editable/bootstrap-table-editable.js"/>"></script>
    <script src="<c:url value="/resources/framework/bootstrap-table-master/dist/locale/bootstrap-table-zh-CN.min.js"/>"></script>
    <script src="<c:url value="/resources/framework/jquery-easyui-1.5.2/jquery.easyui.min.js"/>"></script>
    <script src="<c:url value="/resources/framework/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js"/>"></script>
    <script src="<c:url value="/resources/framework/layer-v3.1.1/layer/layer.js"/>"></script>
    <script src="<c:url value="/resources/js/common/common_method.js"/>"></script>
    <script src="<c:url value="/resources/js/other/jquery.form.js"/>"></script>
    <script src="<c:url value="/resources/js/other/jquery.md5.js"/>"></script>
    <script src="<c:url value="/resources/js/other/jquery.base64.js"/>"></script>

<%-- 定义全局变量 --%>
    <script type="application/javascript">
        var SPRINGMVC_MAPPING = "";
        //根目录，/shoppingServer
        var ROOT_PATH = "${pageContext.request.contextPath}" + SPRINGMVC_MAPPING;
    </script>
    <sitemesh:write property='head'/>
</head>


<body id="decorateBody">
<sitemesh:write property='body'/>
</body>
</html>
