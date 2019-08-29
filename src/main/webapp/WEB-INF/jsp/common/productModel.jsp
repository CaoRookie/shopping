<%--
  Created by IntelliJ IDEA.
  User: 96433
  Date: 2018/4/20
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 模态框（Modal）start -->
<style type="text/css">
    .proitem{
        margin-bottom: 17px;
        font-size: 15px;
        font-weight: 400;
    }
    #proPrice{
        color: red;
    }
</style>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    模态框（Modal）标题
                </h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-6">
                        <img src="" id="proImg" style="width: 200px;">
                    </div>
                    <div class="col-md-6">
                        <div class="proitem">商品描述：<span id="proDetail"></span></div>
                        <div class="proitem">商品价格：<span id="proPrice"></span>￥</div>
                    </div>
                </div>
                <input type="hidden" id="thisPid">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn " data-dismiss="modal">确定</button>
                <button type="button" class="btn" id="buyProduct">一键购买</button>
            </div>
        </div><!-- /.modal-content -->
    </div>
    <!-- 模态框（Modal）end -->
</div>
