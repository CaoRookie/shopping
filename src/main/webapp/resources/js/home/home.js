
$(function () {
    //添加商品到购物车
    $(".shopcar").on('click',function () {
        if(validateMethod.isNull(user_name)){
            layer.confirm('请您登录!', {
                icon:7,
                time:0,
                title: '登录提示',
                btn: ['立即前往','再看看'] //按钮
            }, function(){
                //立即前往
                window.location.href=ROOT_PATH+"/login/loginAction_service.action";
            }, function(){

            });
        }else{
            var pid=$(this).data('pid');
            var param={pid:pid};
            var url="/homeAction_addProductToCar.action";
            method_ajax.updateDataBoolean(url,param,"添加成功!","添加失败!");
        }
    });
    //刷新新闻
    //setInterval('refreshNews()',10000);
    //鼠标移上显示公告信息
    $('.showAnno').hover(function () {
        // console.log($(this));
        layer.tips('<div>'+$(this).data('content')+'</div>', this, {
            tips: [4, '#6382A1']
        });
    },function () {
        layer.closeAll();
    });
});
//刷新新闻
function refreshNews() {
    $('#news').innerHTML="ccccc";
}
//点击图片显示图片详情
$(document).on('click','.product-img',function () {
    var  item= $(this).data("item");
    var jsonStr=item.substring(item.indexOf("{"),item.length+1);
    var toJSONObject=eval('(' + jsonStr.replace(/=/g,":") + ')');
    // console.log(toJSONObject);
    $('#myModalLabel').html(toJSONObject.name);
    $('#proDetail').html(toJSONObject.detail);
    $('#proPrice').html(toJSONObject.price);
    $('#thisPid').val(toJSONObject.id);
    $('#proImg').attr("src",ROOT_PATH+toJSONObject.image_path);
    $("#myModal").modal("show");
});
//点击一键购买
$(document).on('click','#buyProduct',function () {
    window.location.href=ROOT_PATH+'/buyOrder/orderAction/orderAction_myOrder.action?param='+$.base64.encode($('#thisPid').val());
});
