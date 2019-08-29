
$(function () {
    addNavClass();

    $('#userInfo').hover(function(){
        layer.tips("<div><div>用户名称："+user_name +"</div><div>用户电话："+user_phone+"</div></div>",
            '#userInfo', {
                tips: [3, '#6382A1']
            });
    },function () {
        layer.closeAll();
    })
});

//导航条样式
function addNavClass() {
    $('#nav-bottom li a').each(function () {
        var link=$(this).attr('href');
        if(link===URL_PATH)
            $(this).parent('li').addClass("a-nav-focus")
    });
}
var method_ajax={
    updateDataBoolean:function (url,param,resSucc,resError) {
        $.ajax({
            type: "post",
            data: param,
            dataType: 'json',
            url: ROOT_PATH + url,
            success: function (data) {
                if(data === "true")
                    layer.msg(resSucc,{icon:1});
                if(data === "false")
                    layer.msg(resError,{icon:2});
            },
            error:function (data) {
                layer.msg(resError+data,{icon:2});
            }
        });
    },
    /**
     * 复用ajax函数
     * @param url 请求路径
     * @param param 参数
     * @param successCallback 成功回调函数
     * @param errorCallback 失败回调函数
     */
    updateDataCallback:function (url,param,successCallback,errorCallback) {
        $.ajax({
            type: "post",
            data: param,
            //contentType:"application/json",
            dataType: 'json',
            url: ROOT_PATH + url,
            success: function (data) {
                if(typeof successCallback == "function"){
                    successCallback(data);
                }
            },
            error:function (data) {
                if(typeof errorCallback == "function"){
                    errorCallback(data);
                }
            }
        });
    }
};

//所有自定义的验证
var validateMethod={
    expression:{
        //号码
        phone:/^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$/,
        //邮箱
        email:/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
        //汉字
        chinese:/[\u4E00-\u9FA5]/,
        //密码
        password:/^[a-zA-Z]\w{5,17}$/
    },
    //检测登录名格式是否正确
    valiName:function (value) {
        if(!validateMethod.isNull(value)) {
            if(validateMethod.expression.chinese.test(value)
                || validateMethod.expression.email.test(value)
                || validateMethod.expression.phone.test(value))
                return true;
            layer.msg("用户名格式错误!");
            return false;
        }else{
            layer.msg("请输入用户名!");
            return false;
        }
    },
    valiEmail:function (value) {
        if(!validateMethod.isNull(value)){
            if(validateMethod.expression.email.test(value)){
                return true;
            }
            layer.msg("邮箱格式错误!");
            return false;
        }else{
            layer.msg("邮箱不能为空!");
            return false;
        }
    },
    valiChinese:function (value) {
        if(!validateMethod.isNull(value)){
            if(validateMethod.expression.chinese.test(value))
                return true;
            layer.msg("名称格式错误!");
            return false;
        }else{
            layer.msg("名称不能为空!");
            return false;
        }
    },
    valiPhone:function (value) {
        if(!validateMethod.isNull(value)){
            if(validateMethod.expression.phone.test(value))
                return true;
            layer.msg("手机号码格式错误!");
            return false;
        }else{
            layer.msg("手机号码不能为空!");
            return false;
        }

    },
    //验证密码格式是否正确
    valiPassword:function (value) {
        //(以字母开头，长度在6~18之间，只能包含字母、数字和下划线)
        if(!validateMethod.isNull(value)){
            if(!validateMethod.expression.password.test(value)){
                layer.msg("密码格式错误!");
                return false;
            }
            return true;
        }else{
            layer.msg("请输入密码!");
            return false;
        }
    },
    //远程验证用户名是否已经存在
    remoteVali:function (username,password) {
        var resBoolean=true;
        $.ajax({
            type: "post",
            async:false,//必要条件，否则下面ajax会先走retuen再走success
            data: {username:username,password:password},
            url: ROOT_PATH + "/loginAction_validateUser.action",
            success: function (data) {
                if(data === "true"){
                    resBoolean =true;
                    layer.msg("验证成功!");
                }else{
                    resBoolean =false;
                    layer.msg("请注册!")
                }
            },
            error:function () {
                layer.msg("验证失败,请重新登录!");
                resBoolean=false;
            }
        });
        return resBoolean;
    },
    isNull:function (value) {
        if(value === undefined){
            return true;
        }
        var nameLength=value.replace(/(^s*)|(s*$)/g, "").length;
        return value ==="" || value === null || nameLength === 0;
    }
};

jQuery.validator.addMethod("isMobile", function(value, element) {
    var length = value.length;
    return this.optional(element) || (length == 11 && validateMethod.expression.phone.test(value));
}, "请正确填写您的手机号码");

jQuery.validator.addMethod("isEmail", function(value, element) {
    return this.optional(element) ||  validateMethod.expression.email.test(value);
}, "请正确填写您的邮箱");

jQuery.validator.addMethod("isPassword", function(value, element) {
    return this.optional(element) || validateMethod.expression.password.test(value);
}, "请正确填写您的密码");
jQuery.validator.addMethod("isNull", function(value, element) {
    return this.optional(element) || !validateMethod.isNull(value);
}, "不能为空");
//进入注册界面
$(document).ready(function () {
    $('#register').click(function () {
        window.open(ROOT_PATH+'/login/loginAction_registerUser.action');
    });
});