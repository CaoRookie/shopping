$(function () {
    getUserMessage();
    getMyAddress();
    getMyOrder();
});

function getUserMessage() {
    $('#userMessage').bootstrapTable('destroy');
    $('#userMessage').bootstrapTable({
        method: 'get',
        url :thisTool.getData,
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
        queryParams: {type:thisTool.param.userMessage},
        columns: [
            /*{title: "头像",field: "user_image", align: "center",valign:"middle",
                formatter:function (value,row,index) {
                    if(value === null|| value ==="undefined" || value === "null"){
                        return "无";
                    }
                    return ['<img class="userImg" src="'+ROOT_PATH+value+'" >'].join('');
                }
            },*/
            {title: "用户名",field: "user_name", align: "center",valign:"middle"},
            {title: "电话号码",field: "user_phone", align: "center",valign:"middle"},
            {title: "Email",field: "user_email", align: "center",valign:"middle"},
            {title: "操作",field: "", align: "center",valign:"middle",
                formatter:function (value,row,index) {
                return '<a href="javascript:;" class="updateUser">编辑</a>';
                },
                events:'operateEvents'
            }
        ],
        onEditableSave: function (field, row, oldValue, $el) {

        },
        onLoadSuccess: function (data) {
        },
        onLoadError: function (data) {

        }
    });
}
function getMyAddress() {
    $('#myAddress').bootstrapTable('destroy');
    $('#myAddress').bootstrapTable({
        method: 'get',
        url :thisTool.getData,
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
        uniqueId: "addrId",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        queryParamsType : '',
        queryParams: {type:thisTool.param.addressMessage},
        columns: [
            {title: "序号",field: "id", align: "center",valign:"middle",width:'5%',
                formatter:function (value,row,index) {
                    return index +=1;
                }
            },
            {title: "addrId",field: "addrId", align: "center",valign:"middle"},
            {title: "收货人",field: "receiptUserName", align: "center",valign:"middle",width:'15%'},
            {title: "收货号码",field: "receiptPhone", align: "center",valign:"middle",width:'15%'},
            {title: "收货地址",field: "address", align: "center",valign:"middle",width:'40%'},
            {title: "地址类型",field: "isDefaultAddr", align: "center",valign:"middle",width:'10%',
                formatter:function (value,row,index) {
                    if(value === 0)
                        return '<span class="color-red">默认收货地址</span>';
                    if(value === 1)
                        return '普通收货地址';
                }
            },
            {title: "操作",field: "operating", align: "center",valign:"middle",width:'15%',
                formatter:function (value,row,index) {
                    var a='<a href="javascript:;" class="opDefault">设为默认地址</a>';
                    var b='<a href="javascript:;" class="opNotDefault">设为普通地址</a>';
                    var c='<a href="javascript:;" class="remove">移除</a>';
                    if(row['isDefaultAddr'] === 0)
                        return b+"\t"+"|"+"\t"+c;
                    if (row['isDefaultAddr'] === 1){
                        return a+"\t"+"|"+"\t"+c;
                    }
                },
                events:'operateEvents'
            }
        ],
        onLoadSuccess: function (data) {

        },
        onLoadError: function (data) {

        }
    });
    $('#myAddress').bootstrapTable('hideColumn', 'addrId');
}
function getMyOrder() {
    $('#myOrder').bootstrapTable('destroy');
    $('#myOrder').bootstrapTable({
        method: 'get',
        url :thisTool.getData,
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
        queryParams: {type:thisTool.param.orderMessage},
        columns: [
            {title: "序号",field: "id", align: "center",valign:"middle",
                formatter:function (value,row,index) {
                    return index +=1;
                }
            },
            {title: "订单编号",field: "orderNumber", align: "center",valign:"middle"},
            {title: "订单时间",field: "date", align: "center",valign:"middle",sortable:true},
            {title: "总金额",field: "totalPrice", align: "center",valign:"middle",sortable:true,
                formatter:function (value,row,index) {
                    return value+"￥";
                }
            },
            {title: "订单状态",field: "state", align: "center",valign:"middle",
                formatter:function (value,row,index) {
                    if(value ===-1){
                        return ['<a href="javascript:void(0)" class="orderState">待支付</a>'].join("");
                    }
                    if(value ===0){
                        return "待发货"
                    }
                    if(value ===1){
                        return "待收货";
                    }
                    if(value ===2){
                        // return ['<a href="javascript:void(0)" class="orderState">待评价</a>'].join("");
                        return "去评价";
                    }
                    if(value ===3){
                        // return ['<a href="javascript:void(0)" class="orderState">待评价</a>'].join("");
                        return "已取消";
                    }
                },
                events:'operateEvents'
            },
            {title: "操作",field: "operating", align: "center",valign:"middle",
                formatter:function (value,row,index) {
                var a='<a class="orderDetail" href="javascript:void(0)">查看详情</a>';
                var b='<a class="delOrder" href="javascript:void(0)">删除</a>';
                var c='<a class="cancel" href="javascript:void(0)">取消</a>';
                    if(row['state'] === 3){
                        return a+' | '+b;
                    }else{
                        return a+' | '+c;
                    }
                },
                events:'operateEvents'
            }
        ],
        onLoadSuccess: function (data) {

        },
        onLoadError: function (data) {

        }
    });
    $('#myAddress').bootstrapTable('hideColumn', 'addrId');
}
function getProductList(row) {
    $('#productList').bootstrapTable('destroy');
    $('#productList').bootstrapTable({
        method: 'get',
        url :thisTool.getOrderInfo,
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

var thisTool={
    param:{
        userMessage:"user",
        addressMessage:"address",
        orderMessage:"order"
    },
    op_url:{
        remove:'/userMessageAction_deleteMyAddress.action',
        OrAndDeAddr:'/userMessageAction_setOrAndDeAddr.action',
        uploadUserImg:'/userMessageAction_uploadUserImg.action',
        updateUser:'/userMessageAction_updateUser.action',
        delOrder:'/userMessageAction_delOrder.action',
        cancelOrder:'/userMessageAction_cancelOrder.action'
    },
    getData:ROOT_PATH + '/userMessageAction_getMessage.action',
    getOrderInfo:ROOT_PATH + '/userMessageAction_getOrderInfo.action',
    refreshReport:function (id) {
      setTimeout(function () {
          $('#'+id).bootstrapTable('refresh', {url:thisTool.getData});
      },200);
    }
};
//获取省市县
var addAddr={
    selectProvince:function () {
        $('#province').combobox({
            url:ROOT_PATH + '/userMessageAction_getProvinceInfo.action',
            valueField:'id',
            textField:'text',
            width:'171px',
            height:'34px',
            onLoadSuccess:function (data) {
            },
            onLoadError:function (data) {
            },
            onSelect:function (node) {
                addAddr.selectCity(node.id);
            }
        });
    },
    selectCity:function (provinceId) {
      $('#cities').combobox({
          url:ROOT_PATH+'/userMessageAction_getProvinceInfo.action?parentId='+provinceId,
          valueField:'id',
          textField:'text',
          width:'171px',
          height:'34px',
          onLoadSuccess:function (data) {

          },
          onLoadError:function (data) {

          },
          onSelect:function (node) {
              addAddr.selectCountry(node.id);
          }
      });
    },
    selectCountry:function (cityId) {
      $('#areas').combobox({
          url:ROOT_PATH+'/userMessageAction_getProvinceInfo.action?parentId='+cityId,
          valueField:'id',
          textField:'text',
          width:'171px',
          height:'34px'
      });
    },
    saveReceiptAddr:function (map) {
        var url='/userMessageAction_saveReceiptAddr.action';
        var param={receiptUser:map.receiptUser, receiptAddr:map.receiptAddr,receiptPhone:map.receiptPhone};
        method_ajax.updateDataBoolean(url,param,"新增成功!","新增失败!");
    },
};
window.operateEvents = {
    //点击移除
    'click .remove': function (e, value, row, index) {
        var param={addrId:row.addrId};
        method_ajax.updateDataBoolean(thisTool.op_url.remove,param,"移除成功!","移除失败!");
        thisTool.refreshReport('myAddress');
    },
    //点击设为默认地址
    'click .opDefault': function (e, value, row, index) {
        var param={addrId:row.addrId};
        method_ajax.updateDataBoolean(thisTool.op_url.OrAndDeAddr,param,"设置成功!","设置失败!");
        thisTool.refreshReport('myAddress');
    },
    'click .opNotDefault': function (e, value, row, index) {
        var param={addrId:row.addrId};
        method_ajax.updateDataBoolean(thisTool.op_url.OrAndDeAddr,param,"设置成功!","设置失败!");
        thisTool.refreshReport('myAddress');
    },
    'click .updateUser': function (e, value, row, index) {
        initUpdateUser(e, value, row, index);
    },
    'click .orderState': function (e,value,row,index) {
        var code=row['orderNumber']+","+row['totalPrice'];
        window.open(ROOT_PATH+'/payment/buyProduct.action?code='+$.base64.encode(code));
    },
    'click .orderDetail': function (e,value,row,index) {
        getProductList(row);
    },
    'click .delOrder': function (e,value,row,index) {
        method_ajax.updateDataCallback(thisTool.op_url.delOrder,{orderNumber:row['orderNumber']}
        ,thisTool.refreshReport('myOrder')
        ,function () {
            layer.msg("删除失败！",{icon:2});
        })
    },
    'click .cancel': function (e,value,row,index) {
        method_ajax.updateDataCallback(thisTool.op_url.cancelOrder,{orderNumber:row['orderNumber']}
        ,thisTool.refreshReport('myOrder')
        ,function () {
            layer.msg("取消失败！",{icon:2});
        })
    }
};
function initUpdateUser(e, value, row, index) {
    var myImg='<img class="userImg" id="myImg" src="'+ROOT_PATH+row['user_image']+'" >';
    if(row['user_image'] === null|| row['user_image'] ==="undefined" || row['user_image'] === "null"){
       myImg= '<input id="input-uImg" type="file" name="imageFile"/>';
        $("#input-uImg").fileinput({
            language:'zh',
            uploadUrl: ROOT_PATH +thisTool.op_url.uploadUserImg, //上传的地址
            allowedFileExtensions : ['png', 'jpg'],//接收的文件后缀,
            maxFileCount: 1,
            enctype: 'multipart/form-data',
            showUpload: true, //是否显示上传按钮
            showCaption: false,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式
            showPreview :true, //是否显示预览
            autoReplace:true, //是否自动替换
            maxImageWidth: 200,//图片的最大宽度
            maxImageHeight: 200,//图片的最大高度
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            dropZoneEnabled: false,
            overwriteInitial: false,
            initialPreviewAsData: true,
            // 这个配置就是解决办法了,初始化时限制图片大小
            previewSettings: {
            }
        });
    }
    var thisIndex=layer.open({
        type: 1 //Page层类型
        ,area: ['500px', '300px']
        ,title: '我的信息编辑'
        ,zIndex:100000
        ,shade: 0.6 //遮罩透明度
        ,maxmin: true //允许全屏最小化
        ,anim: 1 //0-6的动画形式，-1不开启
        ,content: '<div style="padding:15px;"><form class="form-horizontal">' +
        '  <div class="form-group">' +
        '    <label for="myName" class="col-sm-3 control-label">用户姓名：</label>' +
        '    <div class="col-sm-9">' +
        '      <input type="text"  autocomplete="off" class="form-control" id="myName" value="'+row['user_name']+'">' +
        '    </div>' +
        '  </div>' +
        '  <div class="form-group">' +
        '    <label for="myPhone" class="col-sm-3 control-label">手机号码：</label>' +
        '    <div class="col-sm-9">' +
        '      <input type="text"  autocomplete="off" class="form-control" id="myPhone" value="'+row['user_phone']+'">' +
        '    </div>' +
        '  </div>' +
        '  <div class="form-group">' +
        '    <label for="myEmail" class="col-sm-3 control-label">电子邮箱：</label>' +
        '    <div class="col-sm-9">' +
        '      <input type="text"  autocomplete="off" class="form-control" id="myEmail" value="'+row['user_email']+'">' +
        '    </div>' +
        '  </div>' +
        '</form></div>'
        ,btn: ['保存', '重置', '取消']
        ,yes: function(index, layero){
            var param = {user_name: $("#myName").val()
                ,user_phone: $("#myPhone").val()
                ,user_email: $("#myEmail").val()
            };
            method_ajax.updateDataCallback(thisTool.op_url.updateUser,param,"编辑成功!","编辑失败!");
            layer.close(thisIndex);//关闭名为index的layer
            thisTool.refreshReport('userMessage');
        }
        ,btn2: function(index, layero){
            $('#myName').val(row['user_name']);
            $('#myPhone').val(row['user_phone']);
            $('#myEmail').val(row['user_email']);
            return false//开启该代码可禁止点击该按钮关闭
        }
    });
}
$(document).ready(function () {
    $('#addAddr').click(function () {
        var index=layer.open({
            type: 1 //Page层类型
            ,area: ['767px', '350px']
            ,title: '我的收货地址'
            ,zIndex:100000
            ,shade: 0.6 //遮罩透明度
            ,maxmin: true //允许全屏最小化
            ,anim: 1 //0-6的动画形式，-1不开启
            ,content: '<div style="padding:15px;"><form class="form-horizontal">' +
            '  <div class="form-group">' +
            '    <label class="col-sm-2 control-label"><span class="color-red">*&nbsp;</span>所在地区</label>' +
            '    <div class="col-sm-3">' +
            '      <input type="text" class="form-control" id="province" placeholder="省份">' +
            '    </div>' +
            '    <div class="col-sm-3">' +
            '      <input type="text" class="form-control" id="cities" placeholder="地市">' +
            '    </div>' +
            '    <div class="col-sm-3">' +
            '      <input type="text" class="form-control" id="areas" placeholder="区县">' +
            '    </div>' +
            '  </div>' +
            '  <div class="form-group">' +
            '    <label for="receiptUser" class="col-sm-2 control-label"><span class="color-red">*&nbsp;</span>收货人</label>' +
            '    <div class="col-sm-5">' +
            '      <input type="text"  autocomplete="off" class="form-control" id="receiptUser" placeholder="请输入收货人姓名">' +
            '    </div>' +
            '  </div>' +
            '  <div class="form-group">' +
            '    <label for="receiptAddr" class="col-sm-2 control-label"><span class="color-red">*&nbsp;</span>详细地址</label>' +
            '    <div class="col-sm-10">' +
            '      <input type="text"  autocomplete="off" class="form-control" id="receiptAddr" placeholder="请输入详细地址(镇、街道)">' +
            '    </div>' +
            '  </div>' +
            '  <div class="form-group">' +
            '    <label for="receiptPhone" class="col-sm-2 control-label"><span class="color-red">*&nbsp;</span>手机号码</label>' +
            '    <div class="col-sm-5">' +
            '      <input type="text"  autocomplete="off" class="form-control" id="receiptPhone" placeholder="请输入手机号码">' +
            '    </div>' +
            '  </div>' +
            '</form></div>'
            ,btn: ['保存', '重置', '取消']
            ,yes: function(index, layero){
                //按钮【确定】的回调
                var province=$('#province').combobox('getText');
                var city=$('#cities').combobox('getText');
                var area=$('#areas').combobox('getText');
                var map={
                    receiptUser:$('#receiptUser').val(),
                    receiptAddr:province+city+area+$('#receiptAddr').val(),
                    receiptPhone:$('#receiptPhone').val()
                };
                if(validateMethod.isNull(map.receiptUser)
                    || validateMethod.isNull(map.receiptAddr)
                    || !validateMethod.valiPhone(map.receiptPhone)){
                    layer.msg("带红色*号为必填项!");
                }else{
                    addAddr.saveReceiptAddr(map);
                    layer.close(index);//关闭名为index的layer
                    thisTool.refreshReport('myAddress');
                }
            }
            ,btn2: function(index, layero){
                //按钮【重置】的回调
                $('#province').combobox('clear');
                $('#cities').combobox('clear');
                $('#areas').combobox('clear');
                $('#receiptUser').val("");
                $('#receiptAddr').val("");
                $('#receiptPhone').val("");
                return false//开启该代码可禁止点击该按钮关闭
            }
            ,btn3: function(index, layero){

            }
        });
        addAddr.selectProvince();
    });
});