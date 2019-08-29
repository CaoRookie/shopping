
$(document).ready(function () {
    $('#show_hide').click(function () {
        var items=$('.addr_title');
        for(var i=0;i<items.length;i++){
            if(!$(items[i]).hasClass('item-selected')){
                $(items[i]).parent().toggleClass("display_none");
                if($(items[i]).parent().hasClass("display_none")){
                    $('#show_hide').html("更多地址");
                }else{
                    $('#show_hide').html("收起地址");
                }
            }
        }
    });
});
$(document).ready(function () {
    $('.addr_title').click(function () {
        $('.addr_title').removeClass('item-selected');
        $(this).addClass('item-selected');
        $('#selectAddrId').val($(this).data("addrid"));
    });
    $('.pay-item').click(function () {
        $('.pay-item').removeClass('item-selected');
        $(this).addClass('item-selected');
        $('#paySelect').val($(this).children('a').html());
    });
    $('.post-item').click(function () {
        $('.post-item').removeClass('item-selected');
        $(this).addClass('item-selected');
        $('#postSelect').val($(this).children('a').html());
    });
});
/**
 * 点击提交订单
 */

$(document).on('click','#submitOrder',function () {
    var pIds=$('.pIds');
    var pidTemp="";
    for(var i=0;i<pIds.length;i++){
        pidTemp=pidTemp+pIds[i].dataset['pid']+",";
    }
    var param={
        addrid:$('#selectAddrId').val(),
        pay:$('#paySelect').val(),
        post:$('#postSelect').val(),
        totalPri:$('#totalPri').val(),
        pIds:pidTemp.substring(0,pidTemp.length-1)
    };
    method_ajax.updateDataCallback('/orderAction_submitOrder.action',param,function (data) {
        window.open(ROOT_PATH+'/payment/buyProduct.action?code='+$.base64.encode(data));
        // window.location.href=;
    },function (data) {
        
    });
});

$(document).ready(function () {
    $('.setDefault').parent().parent().mouseover(function () {
        $(this).find('.setDefault').parent().show();
    });
    $('.setDefault').parent().parent().mouseout(function () {
        $(this).find('.setDefault').parent().hide();
    });
});
//设置为默认地址
$(document).on('click','.setDefault',function () {
    var param={addrId:$(this).data("addrid")};
    var url='/orderAction_setDefaultAddr.action';
    method_ajax.updateDataCallback(url,param,function () {
        location.reload();
    },function () {
        layer.msg("设置失败!");
    });
});
//点击编辑--回显地址数据
$(document).on('click','.editAddr',function () {
    var param={addrId:$(this).data("addrid")};
    var url='/orderAction_getEditAddrd.action';
    method_ajax.updateDataCallback(url,param,function (data) {
        //将字符串转换成json对象
        var dataObject=JSON.parse(data);
        $('#getProname').val(dataObject['receiptUserName']);
        $('#getProaddr').val(dataObject['address']);
        $('#getProphone').val(dataObject['receiptPhone']);
        $('#addrId').val(dataObject['addrId']);
        //将原来未修改的内容赋给oldData,用于比较是否修改
        oldData=dataObject;
        setTimeout(function () {
            $('#myAddrModal').modal('show');
        },200);
    },function () {
        layer.msg("数据获取失败!请联系管理员!");
    });
});
//模态框点击提交更改
var oldData;
$(document).on('click','#editAddress',function () {
    var param={
        getProname:$('#getProname').val()
        , getProaddr:$('#getProaddr').val()
        ,getProphone:$('#getProphone').val()
        , addrId:$('#addrId').val()
    };
    if(oldData['receiptUserName'] ===param.getProname
        && oldData['address']===param.getProaddr
        && oldData['receiptPhone']===param.getProphone ){
        layer.msg("您未修改数据!");
    }else{
        var url='/orderAction_updateAddress.action';
        method_ajax.updateDataCallback(url,param,function (data) {
            if(data === "true"){
                layer.msg("修改成功!");
                $('#myAddrModal').modal('hide');
                location.reload();
            }else{
                layer.msg("修改失败!");
            }
        },function () {
            layer.msg("数据获取失败!请联系管理员!");
        });
    }
});