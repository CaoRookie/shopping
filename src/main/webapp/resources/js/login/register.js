$({

});
$(document).on('click','#subFrom',function () {
    var username=$('#username').val();
    var sPassword=$('#sPassword').val();
    var qPassword=$('#qPassword').val();
    var phone=$('#phone').val();
    var myemail=$('#myemail').val();
});
$("#subFromBtn").click(function() {

    $("#registerFrom").validate({
        submitHandler: function(form){
            //debug:true,
            $.ajax({
                cache : true,
                type : "POST",
                url : ROOT_PATH+'/loginAction_register.action',
                data : $('#registerFrom').serialize(),
                async : false,
                error : function(data) {
                    layer.alert('系统错误', {icon: 0});
                },
                success : function(data) {
                    layer.alert('保存成功', {icon: 1});
                    $('#registerFrom')[0].reset();
                }
            });
        },
        rules: {
            username: {
                required: true,
                minlength: 2,
                maxlength:10
            },
            sPassword:{
                required: true,
                minlength: 6,
                maxlength: 20,
                isPassword:true
            },
            qPassword:{
                required: true,
                minlength: 6,
                maxlength: 20,
                equalTo: "#sPassword"
            },
            phone:{
                required: true,
                minlength: 11,
                maxlength:11,
                digits:true,
                number:true,
                isMobile : true
            },
            myemail:{
                required: true,
                maxlength:50,
                isEmail:true
            }
        },
        messages: {
            username: {
                required: "不能为空",
                minlength: "不能少于2个字符",
                maxlength: "长度不能超过10"
            },
            sPassword:{
                required: "不能为空",
                minlength: "不能少于6个字符",
                maxlength: "不能超过20个字符",
                isPassword:"密码必须包含小写字母,下划线,数字",
            },
            qPassword:{
                required: "不能为空",
                minlength: "不能少于6个字符",
                equalTo: "两次密码不一致"
            },
            phone:{
                required: "不能为空",
                minlength: "必须11位数字",
                maxlength:"必须11位数字",
                digits:"必须是数字"    ,
                number:"请输入有效数字",
                isMobile : "手机号格式错误"
            },
            myemail:{
                required:'不能为空',
                maxlength:"不能多于50个字符",
                isEmail:"邮箱格式错误",
            }
        }
    });
});