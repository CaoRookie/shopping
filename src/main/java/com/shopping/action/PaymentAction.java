package com.shopping.action;

import com.opensymphony.xwork2.ActionContext;
import com.shopping.util.EncryptUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * @Name: PaymentAction
 * @Description: 支付Controller
 * @Author cy
 * @Date 2018/5/1216:34
 */
@Controller
@Scope("prototype")
public class PaymentAction {

    private static final Logger logger= LoggerFactory.getLogger(PaymentAction.class);
    public String buyProduct(){
        HttpServletRequest request= ServletActionContext.getRequest();
        String code=request.getParameter("code");
        if(StringUtils.isBlank(code)){
            throw new IllegalArgumentException("非法参数！所传参数位空！");
        }
        logger.info("------------------------->订单支付界面");
        try{
            code= EncryptUtil.decodeString(code);
            logger.info("解密后的参数为{}",code);
        }catch (Exception e){
            logger.info("到支付界面参数解密失败！信息：",e.getCause());
            throw new RuntimeException("解密参数时发生异常！请联系管理员处理！");
        }
        String tmp[]=code.split(",");
        ActionContext.getContext().put("orderNumber",tmp[0]);
        ActionContext.getContext().put("totalPrice",tmp[1]);
        return "payment";
    }
}
