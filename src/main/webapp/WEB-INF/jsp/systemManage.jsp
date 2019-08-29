<%--
  Created by IntelliJ IDEA.
  User: cy
  Date: 2018/4/16
  Time: 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>系统管理</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/systemManage.css"/>">
</head>
<body>
<div>
    <div class="my-body">
        <div class="body-title">
            <div class="row" style="position: relative;">
                <div class="col-md-4" >
                    <img class="logo2-img" src="<c:url value="/resources/images/head/logo2.png"/>">
                    <span class="imgTitle">系统管理</span>
                </div>
            </div>
        </div>
        <div class="col-md-2">
            <ul class="nav nav-stacked myList">
                <li><div class="listTitle">商品管理</div></li>
                <li class="active" value="1"><a href="#myReport" data-toggle="tab" class="a-focus">商品列表</a></li>
                <li value="2"><a href="#myReport" data-toggle="tab">商品分类</a></li>
                <li value="3"><a href="#addProduct" data-toggle="tab">批量新增</a></li>
                <li><div class="listTitle">用户管理</div></li>
                <li value="4"><a href="#myReport" data-toggle="tab">用户列表</a></li>
                <li> <div class="listTitle">订单管理</div></li>
                <li value="5"><a href="#myReport" data-toggle="tab">全部订单</a></li>
                <li value="6"><a href="#myReport" data-toggle="tab">已处理订单</a></li>
                <li value="7"><a href="#myReport" data-toggle="tab">待处理订单</a></li>
                <li value="8"><a href="#myReport" data-toggle="tab">成功交易订单</a></li>
            </ul>
        </div>
        <div id="listContent" class="col-md-10">
            <div>
                <div><span class="tablesTitle" id="tablesTitle">商品列表</span></div>
                <div>
                    <div id="addTableRow" class="addRow">
                        <a id="addRow" href="javascript:;">
                            <span>添加</span>
                            <i class="glyphicon glyphicon-plus"></i>
                        </a>
                    </div>
                    <div id="removeTableRow" class="addRow">
                        <a id="delRow" href="javascript:;">
                            <span>删除所选</span>
                            <i class="glyphicon glyphicon-minus"></i>
                        </a>
                    </div>
                </div>
            </div>
            <div  id="tb" class="tab-content">
                <div id="myReport" class="tab-pane fade in active">
                    <table id="myTable"></table>
                </div>
                <div id="addProduct" class="tab-pane fade">
                    <div class="container background-white mt set-radius">
                        <div class="row">
                            <h3>数据导入</h3>
                        </div>
                        <div>
                            <div>
                                <img class="custom-img-sm" src="<c:url value="/resources/images/system/import/step1.png" />" />
                                <span class="temp-font-set">下载模板:</span>
                                <a href="<c:url value="/servlet/systemAction/excelTemplateDownload?type=product"/> "
                                   class="m-15">
                                    <img class="sm-size" src="<c:url value="/resources/images/system/import/xlsx.png"/> " />
                                    &nbsp;&nbsp;商品新增模板下载
                                </a>
                            </div>
                            <div>
                                <div class="form-group">
                                    <div class="mtb-10">
                                        <img class="custom-img-sm" src="<c:url value="/resources/images/system/import/step2.png" />" />
                                        <span class="temp-font-set">请选择文件上传:</span>
                                    </div>
                                    <div class="ml-5">
                                        <input id="input-id" type="file" name="excelFile" class="file" data-preview-file-type="text" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
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
<script src="<c:url value="/resources/js/systemManage.js"/>"></script>
</body>
</html>
