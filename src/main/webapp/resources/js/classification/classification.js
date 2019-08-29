$(function () {
    //初始化加载家电
    initReport.initReport(1,initReport.url);
});
$(document).ready(function () {
    $('.nav>li>a').click(function () {
        $('.nav>li>a').removeClass('a_checked');
        $('.nav>li>a').addClass('color-white');
        $(this).removeClass('color-white');
        $(this).addClass('a_checked');
    });
    $('.myList>li').click(function () {
        initReport.initReport($(this).val(),initReport.url);
        $("#tablesTitle").html($(this).find('a').text());

    });
});
var initReport={
    url:'/classificationAction_getProductByType.action',
    url2:'/classificationAction_getProductBySreach.action',
    columns:{
        productListReport:[
            {field: "", title: "序号",align:"center",valign:"middle",
                formatter:function (value,row,index) {
                    return ++index;
                }
            },
            {field: "id", title: "商品ID",align:"center",valign:"middle"},
            {field: "name", title: "商品名称",align:"center",valign:"middle"},
            {field: "image_path", title: "商品图",align:"center",valign:"middle",
                formatter:function (value,row,index) {
                    return ['<img class="productImg" src="'+ROOT_PATH+value+'" >'].join('');
                }
            },
            {field: "detail", title: "商品详情",align:"center",valign:"middle",sortable:true},
            {field: "price", title: "商品价格",align:"center",valign:"middle",sortable:true},
            {field: "vip_price", title:"VIP价格",align:"center",valign:"middle",sortable:true},
            {field: "lave_quantity", title: "库存量",align:"center",valign:"middle",sortable:true},
            {field: "sale_quantity", title: "销售量",align:"center",valign:"middle",sortable:true},
            {field: "click_rate", title: "商品点击率",align:"center",valign:"middle",sortable:true},
            {field: "promotions_price", title: "促销价",align:"center",valign:"middle",sortable:true},
            {field: "operating", title: "操作",align:"center",valign:"middle",width:160,
                formatter:function (value,row,index) {
                    return [
                        ' <a class="buyById" href="javascript:;">一键购买</a> |',
                        ' <a class="addCar" href="javascript:;">加入购物车</a>'
                    ].join('');
                },
                events:'operateEvents'
            }
        ]
    },
    initReport:function (type,url) {
        $('#myTable').bootstrapTable('destroy');
        setTimeout(function () {
            $('#myTable').bootstrapTable({
                method: 'get',
                url : ROOT_PATH + url,
                dataType:"json",                      //请求方式（*）
                //editable: true,                          //开启编辑模式
                //toolbar: '#toolbar',                //工具按钮用哪个容器
                striped: false,                      //是否显示行间隔色
                cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: true,                   //是否显示分页（*）
                sortable: true,                     //是否启用排序
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
                lickToSelect: false,                //是否启用点击选中行
                //height: 370,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
                width:'100%',
                uniqueId: "id",                     //每一行的唯一标识，一般为主键列
                showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
                cardView: false,                    //是否显示详细视图
                detailView: false,                   //是否显示父子表
                queryParamsType : '',
                queryParams: {product_type:type},                       //传递参数（*）
                columns: initReport.columns.productListReport
            });
            $('#myTable').bootstrapTable('hideColumn',"id");
        },200);
    }
};
var oprateTable={
    url:{
        buy:'/classificationAction_buyProductById.action',
        addToCar:'/homeAction_addProductToCar.action',
    },
    addRow:function (insertIndex, rowObj){
        var insertRow = rowObj;
        $.each(insertRow, function(name, value){
            insertRow[name] = '';
        });

        var params = {index:insertIndex + 1, row:insertRow};
        $('#myTable').bootstrapTable('insertRow', params);
    },
    refeshTable:function () {
        setTimeout(function () {
            $('#myTable').bootstrapTable('refresh', {url: ROOT_PATH+url});
        },200);
    }
};
$(document).ready(function () {
    window.operateEvents={
        'click .buyById': function (e, value, row, index) {
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
                window.location.href=ROOT_PATH+'/buyOrder/orderAction/orderAction_myOrder.action?param='+$.base64.encode(row['id']);
            }
        },
        'click .addCar': function (e, value, row, index) {
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
                var param={pid:row['id']};
                method_ajax.updateDataBoolean(oprateTable.url.addToCar,param,"添加成功!","添加失败!")
            }
        }
    }
});
$(document).on('click','#sreachBtn',function () {
    var text=$('#sreachPro').val();
    initReport .initReport(text,initReport.url2);
});
