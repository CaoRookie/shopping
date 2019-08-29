
$(function () {
    // 用户登录验证
    $('#login_btn').click(function () {
        var username=$("#username").val();
        var password=$("#password").val();
        if(validateMethod.valiName(username)
            && validateMethod.valiPassword(password)
            && validateMethod.remoteVali(username,password))
            $('#login-form').submit();
    });
    $
});

