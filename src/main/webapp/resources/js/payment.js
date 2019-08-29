$(function () {
    $("#subFromBtn").click(function () {
        var val = $('input[name="pay"]:checked').val();
        layer.open({
            type: 1,
            title: false,
            closeBtn: 0,
            shadeClose: true,
            skin: 'layui-layer-demo',
            content: '<img class="myPay" src="'+ROOT_PATH+'/resources/images/common/'+val+'.png">'
        });
    });
});

