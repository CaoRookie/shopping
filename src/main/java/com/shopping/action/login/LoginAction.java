package com.shopping.action.login;

import com.opensymphony.xwork2.ActionSupport;
import com.shopping.constant.SysConstant;
import com.shopping.domain.User;
import com.shopping.service.LoginService;
import com.shopping.util.EncryptUtil;
import org.apache.struts2.ServletActionContext;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author: caoyuan
 * @Email: caoyuan@tydic.com
 * @Description: 用户登录Action
 * @Date: 9:05 2018/1/17
 */
@Scope("prototype")
@Controller
public class LoginAction extends ActionSupport {

    @Autowired
    private LoginService loginService;

    private String result;
    public String getResult() {
        return result;
    }

    private static final Logger logger= LoggerFactory.getLogger(LoginAction.class);

    public String service(){
        logger.info("LoginAction-------->this is login");
        return "login";
    }

    /**
     *@auth：caoyuan
     *@Desc： 登录验证,用户登录
     */
    public String validateUser(){
        HttpServletRequest request=ServletActionContext.getRequest();
        String username=request.getParameter("username");
        String password=EncryptUtil.baseStringEncode(request.getParameter("password"));
        User user = loginService.validateUser(username,password);
        request.getSession().setAttribute(SysConstant.SESSION_USER_INFO,user);
        this.result="true";
        if(user == null){
            this.result="false";


        }
        return "resultbBoolean";
    }
    /**
     *@auth：caoyuan
     *@Desc： 用户注册
     */
    public String registerUser(){
        logger.info("------------------->sthis is registerUser");
        return "register";
    }
    public String register(){
        HttpServletRequest request=ServletActionContext.getRequest();
        Map<String,Object>map=new HashMap<String, Object>();
        map.put("username",request.getParameter("username"));
        map.put("sPassword",EncryptUtil.baseStringEncode(request.getParameter("sPassword")));
        map.put("phone",request.getParameter("phone"));
        map.put("myemail", request.getParameter("myemail"));
        loginService.register(map);
        return "resultbBoolean";
    }

    /**
     * 退出登录
     * @return
     */
    public String logout(){
        HttpServletRequest request=ServletActionContext.getRequest();
        request.getSession().removeAttribute(SysConstant.SESSION_USER_INFO);
        request.getSession().invalidate();
        return "login";
    }
}
