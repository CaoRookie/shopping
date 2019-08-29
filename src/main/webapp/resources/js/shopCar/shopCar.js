$(function () {

    initCarReport();

});
var carTableTool={
    url:{
        //获取数据
        getCar: '/shopCarAction_getCarReportData.action',
        //加号操作
        addProduct: '/shopCarAction_addCarReportData.action',
        //减号操作
        reduceProduct: '/shopCarAction_reduceCarReportData.action',
        //移除操作和删除所选操作
        removeProduct: '/shopCarAction_removeCarReportData.action',
        //点击结算
        jieSuan: '/buyOrder/orderAction/orderAction_myOrder.action',
    },
    refreshCarTable:function () {
        setTimeout(function () {
            $('#carReport').bootstrapTable('refresh', {url: ROOT_PATH+carTableTool.url.getCar});
        },200);
    }
};
function initCarReport() {
    $('#carReport').bootstrapTable('destroy');
    $('#carReport').bootstrapTable({
        method: 'get',
        url : ROOT_PATH+carTableTool.url.getCar,
        dataType:"json",                      //请求方式（*）
        //toolbar: '#toolbar',                //工具按钮用哪个容器
        striped: false,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: false,                   //是否显示分页（*）
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50,100],        //可供选择的每页的行数（*）
        search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,
        showColumns: false,                  //是否显示所有的列
        showRefresh: false,                  //是否显示刷新按钮
        minimumCountColumns: 5,             //最少允许的列数
        lickToSelect: true,                //是否启用点击选中行
        //height: 370,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        width:'100%',                    //宽度
        uniqueId: "PID",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        queryParamsType : '',
        queryParams: "",
        columns: [
            {title: '全选',field: "", align: "center",valign:"middle",checkbox:'true'},
            {title: '购物车id',field: "carId", align: "center",valign:"middle"},
            {title: "序号",field: "id", align: "center",valign:"middle",
                formatter:function (row,value,index) {
                    return index+=1;
                }
            },
            {title: "商品",field: "PNAME", align: "center",valign:"middle",
                formatter:function (value,row,index) {
                    return ['<img class="productImg" src="'+ROOT_PATH+row.PIMAGE+'" >'+value+''].join('');
                }
            },
            {title: "单价(元)",field: "PRICE", align: "center",valign:"middle",clickToSelect:true},
            {title: "数量",field: "PNUM", align: "center",valign:"middle",
                formatter:function (value,row,index) {
                    var pLower='<a class="pLower" href="javascript:void(0);"><i class="glyphicon glyphicon-minus"></i></a>';
                    if(value <= 1){
                        pLower='<a class="pLower" href="javascript:;"><i></i></a>';
                    }
                    return [pLower,
                        '<a class="pNum" href="javascript:;">'+value+'</a>',
                        '<a class="pAdd" href="javascript:void(0);"><i class="glyphicon glyphicon-plus"></i></a>'].join('');

                },
                events:'operateEvents'
            },
            {title: "小计(元)",field: "totalPrice", align: "center",valign:"middle",
                formatter:function (value,row,index) {
                    return row['PRICE'] * row['PNUM'];
                }
            },
            {title: "操作",field: "operting", align: "center",valign:"middle",
                formatter:function (value,row,index) {
                    return '<a href="javascript:;" class="remove">移除</a>';
                },
                events:'operateEvents'
            }
        ],
        onLoadSuccess: function (data) {
            $('#carReport').bootstrapTable('checkAll');
            var totalPri=0;
            for(var i=0;i<data.total;i++){
                totalPri +=data.data[i].PNUM * data.data[i].PRICE;
            }
            //console.log(totalPri);
            $('#totalPri').html(totalPri.toFixed(2));
        },
        onLoadError: function (data) {

        }
    });
    $('#carReport').bootstrapTable('hideColumn', 'PIMAGE');
    $('#carReport').bootstrapTable('hideColumn', 'PID');
    $('#carReport').bootstrapTable('hideColumn', 'carId');
}
//点击删除所选
$(document).on('click','#delChecked',function () {
    var options=$('#carReport').bootstrapTable('getSelections');
    $.each(options,function (index,element) {
       method_ajax.updateDataCallback(carTableTool.url.removeProduct,{pId:options[index]['PID']}
       ,function () {

       },function () {
           layer.msg("删除失败!");
       });
    });
    carTableTool.refreshCarTable();
});

//操作购物车
$(document).ready(function () {
    /**
     * 之前点击没反应是因为我的formatter中不是a标签
     */
    window.operateEvents = {
        //点击移除
        'click .remove': function (e, value, row, index) {
            var param={pId:row.PID};
            method_ajax.updateDataCallback(carTableTool.url.removeProduct,param,
                carTableTool.refreshCarTable(),function () {
                    layer.msg("请求失败!");
            });
        },
        'click .pAdd': function (e, value, row, index) {
            //当前数量加1
            /*var param={row:JSON.stringify(row)};*/
            var param={pId:row['PID']};
            method_ajax.updateDataCallback(carTableTool.url.addProduct,param,
                carTableTool.refreshCarTable(),function () {
                    layer.msg("请求失败!");
                });
        },
        'click .pLower': function (e, value, row, index) {
            //当前数量减1
            var param={pId:row.PID};
            method_ajax.updateDataCallback(carTableTool.url.reduceProduct,param,
                carTableTool.refreshCarTable(),function () {
                    layer.msg("请求失败!");
                });
        }
    };
});

//订单填写与审核
$(document).ready(function () {
    $('#submitCar').click(function () {
        var options=$('#carReport').bootstrapTable('getSelections');
        if(options.length <= 0){
            layer.msg("请选择需要购买的商品!");
            return false;
        }
        var carIds="";
        var pnum=0;
        var totalPri=$('#totalPri').text();
        $.each(options,function (index,element) {
            carIds=carIds+options[index]['carId']+",";
            pnum=pnum+options[index]['PNUM'];
        });
        carIds=carIds.substring(0,carIds.length-1);
        /*var pIdPnum=[];
        $.each(options,function (index,element) {
            pIdPnum.push(options[index]['PID']+":"+options[index]['PNUM']);
            /!*pIdPnum=pIdPnum+options[index]['PID']+":"+options[index]['PNUM']+","
            pnum=pnum+options[index]['PNUM'];*!/
        });
        pIdPnum.push("totalPri:"+totalPri);*/
        var code="carIds="+carIds+"&pnum="+pnum+"&totalPri="+totalPri;
        window.location.href=ROOT_PATH+carTableTool.url.jieSuan+'?code='+$.base64.encode(code);
    });
});

//根据选择不同，总价随之而变
$(document).on('change','input:checkbox',function () {
    var options=$('#carReport').bootstrapTable('getSelections');
    var totalPri=0;
    $.each(options,function (index,e) {
       totalPri=totalPri+(options[index]['PRICE']*options[index]['PNUM']);
    });
    $('#totalPri').html(totalPri.toFixed(2));
});
$(document).on('click','#register',function () {
   window.open(ROOT_PATH+'/login/loginAction_registerUser.action');
});