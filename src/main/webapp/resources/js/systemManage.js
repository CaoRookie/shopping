var pageType="product";
$(function () {
    initReport.initReport(initReport.columns.productListReport,pageType);
    $('.myList>li').click(function () {
        var temp=$(this).val();
        if(temp === 3){
            $('.addRow').hide();
        }else{
            if(temp === 1 || temp === 2) {/*如果是商品和商品分类则显示添加按钮*/
                $('.addRow').show();
            }else{
                $('#addTableRow').hide();
            }
        }
        initReport.initTable(temp);
    });
});

//焦点切换
$(document).ready(function () {
    $('.nav>li>a').click(function () {
        $('.nav>li>a').removeClass('a-focus');
        $(this).addClass('a-focus');
    });
});
var initReport={
    url:'/systemAction_getReport.action',
    columns:{
        productListReport:[
            {title: '全选',field: "", align: "center",valign:"middle",checkbox:'true'},
            {field: "", title: "序号",align:"center",valign:"middle",sortable:true,
                formatter:function (value,row,index) {
                    return ++index;
                }
            },
            {field: "id",title: "商品id"},
            {field: "name", title: "商品名称",align:"center",valign:"middle",editable:true},
            {field: "image_path", title: "商品图",align:"center",valign:"middle",editable:false,
                formatter:function (value,row,index) {
                    return ['<img class="productImg" src="'+ROOT_PATH+value+'" >'].join('');
                }
            },
            {field: "detail", title: "商品详情",align:"center",valign:"middle",editable:true},
            {field: "price", title: "商品价格",align:"center",valign:"middle",editable:true,sortable:true},
            {field: "vip_price", title:"VIP价格",align:"center",valign:"middle",editable:true,sortable:true},
            {field: "lave_quantity", title: "库存量",align:"center",valign:"middle",editable:true,sortable:true},
            {field: "sale_quantity", title: "销售量",align:"center",valign:"middle",editable:false,sortable:true},
            {field: "click_rate", title: "商品点击率",align:"center",valign:"middle",editable:false,sortable:true},
            {field: "promotions_price", title: "促销价",align:"center",valign:"middle",editable:true,sortable:true},
            {field: "product_type", title: "商品类型",align:"center",valign:"middle",editable:true},
            {field: "operating", title: "操作",align:"center",valign:"middle",editable:false,
                formatter:function (value,row,index) {
                    return [/*'<a class="addRow" href="javascript:;">增加</a> |',*/
                            ' <a class="deletePro" href="javascript:;">删除</a>',
                            ].join('');
                },
                events:'operateEvents'
            }
        ],
        productClassifReport:[
            {title: '全选',field: "", align: "center",valign:"middle",checkbox:'true'},
            {field: "", title: "序号",align:"center",valign:"middle",
                formatter:function (value,row,index) {
                    return ++index;
                }
            },
            {field: "product_type_id", title: "商品id",align:"center",valign:"middle"},
            {field: "product_type_name", title: "类型",align:"center",valign:"middle",editable:true},
            {field: "icon_name", title: "标记图片",align:"center",valign:"middle",editable:true},
            {field: "operating", title: "操作",align:"center",valign:"middle",editable:false,
                formatter:function (value,row,index) {
                    return [
                        ' <a class="deleteProType" href="javascript:;">删除</a>',
                    ].join('');
                },
                events:'operateEvents'
            }
        ],
        userListReport:[
            {title: '全选',field: "", align: "center",valign:"middle",checkbox:'true'},
            {field: "", title: "序号",align:"center",valign:"middle",
                formatter:function (value,row,index) {
                    return ++index;
                }
            },
            {field: "user_id", title: "用户编号",align:"center"},
            {field: "user_name", title: "姓名",align:"center"},
            {field: "user_phone", title: "手机号码",align:"center"},
            {field: "user_email", title: "邮箱地址",align:"center"},
            {field: "user_password", title: "密码",align:"center"},
            {field: "user_access", title: "权限",align:"center",valign:"middle",
                formatter:function (value,row,index) {
                   if (value === 777)
                       return "管理员";
                   return "普通用户";
                }
            },
            {field: "operating", title: "操作",align:"center",valign:"middle",editable:false,
                formatter:function (value,row,index) {
                    var a='<a class="updateAccess" href="javascript:;">设为管理员</a>';
                    var b='<a class="updateAccess" href="javascript:;">设为普通用户</a>';
                    var c='<a class="deleteUser" href="javascript:;">删除</a>';
                    if (row['user_access'] === 777)
                        return b+' | '+c;
                    return a+' | '+c;
                },
                events:'operateEvents'
            }
        ],
        orderListReport:[
            {title: '全选',field: "", align: "center",valign:"middle",checkbox:'true'},
            {field: "id", title: "",align:"center"},
            {field: "", title: "序号",align:"center",valign:"middle",
                formatter:function (value,row,index) {
                    return ++index;
                }
            },
            {field: "orderNumber", title: "订单ID",align:"center"},
            {field: "post", title: "配送方式",align:"center"},
            {field: "pay", title: "支付方式",align:"center"},
            {field: "totalPrice", title: "单价",align:"center"},
            {field: "date", title: "提交时间",align:"center"},
            {field: "state", title: "状态",align:"center",
                formatter:function (value,row,index) {
                    if(value ===-1)
                        return "待支付";
                    if(value ===0)
                        return "待发货";
                    if(value ===1)
                        return "待收货";
                    if(value ===2)
                        return "待评价";
                    if(value ===3)
                        return "已取消";
                }
            },
            {field: "oprating", title: "操作",align:"center",
                formatter:function (value,row,index) {
                    return '<a href="javascript:;" class="openDetail">查看详情</a>'
                },
                events:'operateEvents'
            },
            {field: "ordercl", title: "订单处理",align:"center",
                formatter:function (value,row,index) {
                    if(row['state'] === 0){
                        return '<a href="javascript:;" class="ship">发货</a>'
                    }
                    return '-';
                },
                events:'operateEvents'
            }
        ],
    },
    initReport:function (column,type) {
        $('#myTable').bootstrapTable('destroy');
        $('#myTable').bootstrapTable({
            method: 'get',
            url : ROOT_PATH + initReport.url,
            dataType:"json",                      //请求方式（*）
            editable: true,                          //开启编辑模式
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
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            //queryParamsType : '',
            queryParams: {type:type},                       //传递参数（*）
            columns: column,
            onEditableSave: function (field, row, oldValue, $el) {
                var param = {row:JSON.stringify(row),type:type};
                method_ajax.updateDataBoolean(oprateTable.url.update,param,"编辑成功!","编辑失败!");
                oprateTable.refeshTable(initReport.url);
            }
        });
        $('#myTable').bootstrapTable('hideColumn', 'id');
        $('#myTable').bootstrapTable('hideColumn', 'product_type_id');
    },
    initTable:function (selectedValue) {
        setTimeout(function () {
            if (selectedValue === 1) {
                pageType="product";
                $("#tablesTitle").html("商品列表");
                initReport.initReport(initReport.columns.productListReport,pageType);
            } else if (selectedValue === 2) {
                pageType="productType";
                $("#tablesTitle").html("商品分类");
                initReport.initReport(initReport.columns.productClassifReport,pageType);
            } else if (selectedValue === 3) {
                $("#tablesTitle").html("新增商品");
            }else if (selectedValue === 4) {
                pageType="user";
                $("#tablesTitle").html("用户列表");
                initReport.initReport(initReport.columns.userListReport,pageType);
            }else if (selectedValue === 5) {
                pageType="order";
                $("#tablesTitle").html("全部订单");
                initReport.initReport(initReport.columns.orderListReport,pageType);
            }else if (selectedValue === 6) {
                pageType="orderCL";
                $("#tablesTitle").html("已处理订单");
                initReport.initReport(initReport.columns.orderListReport,pageType);
            }else if (selectedValue === 7) {
                pageType="orderDCL";
                $("#tablesTitle").html("待处理订单");
                initReport.initReport(initReport.columns.orderListReport,pageType);
            }else if (selectedValue === 8) {
                pageType="orderCG";
                $("#tablesTitle").html("成功交易订单");
                initReport.initReport(initReport.columns.orderListReport,pageType);
            }
        },200);
    }
};
var oprateTable={
    url:{
        fileUpLoad:'/systemAction_fileUpLoad.action',
        add:'/systemAction_addItem.action',
        del:'/systemAction_delete.action',
        update:'/systemAction_update.action',
        getOrderInfo:'/userMessageAction_getOrderInfo.action',
    },
    refeshTable:function (url) {
        setTimeout(function () {
            $('#myTable').bootstrapTable('refresh', {url: ROOT_PATH+url});
        },200);
    }
};

//添加行
$(document).on('click','#addRow',function () {
    $.ajax({
        type: "post",
        data: {type:pageType},
        dataType: '',
        url: ROOT_PATH + oprateTable.url.add,
        success: function (data) {
            if(data === "true"){
                layer.msg("添加成功！",{icon:1});
                oprateTable.refeshTable(initReport.url);
            }
            if(data === "false")
                layer.msg("添加失败！",{icon:2});
        },
        error:function (data) {
            layer.msg("添加失败！",{icon:2});
        }
    });

});

//删除所选
$(document).on('click','#delRow',function () {
    var options=$('#myTable').bootstrapTable('getSelections');
    $.each(options,function (index,element) {
        method_ajax.updateDataCallback(""
            ,function () {

            },function () {
                layer.msg("删除失败!");
            });
    });
});

//行内删除和设置
window.operateEvents= {
    'click .deletePro': function (e, value, row, index) {
        var param = {id: row['id'], type: "product"};
        method_ajax.updateDataBoolean(oprateTable.url.del, param, "删除成功!", "删除失败!");
        oprateTable.refeshTable(initReport.url);
    },
    'click .deleteProType': function (e, value, row, index) {
        var param = {id: row['product_type_id'], type: "productType"};
        method_ajax.updateDataBoolean(oprateTable.url.del, param, "删除成功!", "删除失败!");
        oprateTable.refeshTable(initReport.url);
    },
    'click .deleteUser': function (e, value, row, index) {
        var param = {id: row['user_id'], type: "user"};
        method_ajax.updateDataBoolean(oprateTable.url.del, param, "删除成功!", "删除失败!");
        oprateTable.refeshTable(initReport.url);
    },
    'click .updateAccess': function (e, value, row, index) {
        var param = {row: JSON.stringify(row), type: "user"};
        method_ajax.updateDataBoolean(oprateTable.url.update, param, "修改成功!", "修改失败!");
        oprateTable.refeshTable(initReport.url);
    },
    'click .openDetail': function (e, value, row, index) {
        getProductList(row);
    },
    'click .ship': function (e, value, row, index) {
        var param = {row: JSON.stringify(row), type: "order"};
        method_ajax.updateDataBoolean(oprateTable.url.update, param, "已发货!", "发货失败!");
        oprateTable.refeshTable(initReport.url);
    }
};

function getProductList(row) {
    $('#productList').bootstrapTable('destroy');
    $('#productList').bootstrapTable({
        method: 'get',
        url :ROOT_PATH+oprateTable.url.getOrderInfo,
        dataType:"json",                      //请求方式（*）
        //toolbar: '#toolbar',                //工具按钮用哪个容器
        striped: false,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: false,                   //是否显示分页（*）
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
        lickToSelect: true,                //是否启用点击选中行
        //height: 370,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        width:'100%',                    //宽度
        uniqueId: "orderNumber",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        queryParamsType : '',
        queryParams: {number:row['orderNumber']},
        columns: [
            {title: "商品",field: "image_path", align: "center",valign:"middle",
                formatter:function (value,row,index) {
                    return '<div class="p-img"><img src="'+ROOT_PATH+value+'"></div>' +
                        '<div class="p-info">'+row['name']+'&nbsp;'+row['detail']+'</div>'
                }
            },
            {title: "数量",field: "pnum", align: "center",valign:"middle",sortable:true},
            {title: "单价",field: "price", align: "center",valign:"middle",sortable:true,
                formatter:function (value,row,index) {
                    return value+'￥';
                }
            },
            {title: "",field: "name", align: "center",valign:"middle"},
            {title: "",field: "detail", align: "center",valign:"middle"},
        ],
        onLoadSuccess: function (data) {
            console.log(data);
            $('#valName').html(data.bottomMap.receiptUserName);
            $('#valPhone').html(data.bottomMap.receiptPhone);
            $('#valAddr').html(data.bottomMap.address);
            $('#valPay').html(data.bottomMap.pay);
            $('#valPost').html(data.bottomMap.post);
            $('#valTotalPrice').html(data.bottomMap.totalPrice+' ￥');
            $('#orderDetailModal').modal('show');
        },
        onLoadError: function (data) {

        }
    });
    $('#productList').bootstrapTable('hideColumn', 'name');
    $('#productList').bootstrapTable('hideColumn', 'detail');
}
//文件上传
$("#input-id").fileinput({
    language:'zh',
    uploadUrl: ROOT_PATH +oprateTable.url.fileUpLoad, //上传的地址
    allowedFileExtensions : ['xlsx', 'xls'],//接收的文件后缀,
    maxFileCount: 1,
    enctype: 'multipart/form-data',
    showUpload: true, //是否显示上传按钮
    showCaption: false,//是否显示标题
    browseClass: "btn btn-primary", //按钮样式
    dropZoneEnabled: false,
    overwriteInitial: false,
    initialPreviewAsData: true
});

//上传成功回调函数
$('#input-id').on('fileuploaded', function(event, data, previewId, index) {
    var result_msg = JSON.parse(data.response).result_msg;
    layer.alert(result_msg, {
        skin: 'layui-layer-lan', //样式类名
        closeBtn: 0
    }, function () {
        window.location.reload();
    });
});
