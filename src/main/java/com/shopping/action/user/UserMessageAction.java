package com.shopping.action.user;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shopping.constant.SysConstant;
import com.shopping.domain.User;
import com.shopping.service.UserMessageService;
import org.apache.commons.lang3.StringUtils;
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
 * @Description:
 * @Date: 10:33 2018/1/15
 */
@Controller
@Scope("prototype")
public class UserMessageAction extends ActionSupport {

    @Autowired
    private UserMessageService userMessageService;

    private Map<String,Object> resultJson;
    public Map<String, Object> getResultJson() {
        return resultJson;
    }
    private String result;
    public String getResult() {
        return result;
    }

    private List<Map<String,Object>>resultList ;
    public List<Map<String, Object>> getResultList() {
        return resultList;
    }

    private static final Logger logger= LoggerFactory.getLogger(UserMessageAction.class);
    public String service(){
        logger.info("UserMessageAction--->this is UserMessageAction");
        User user=(User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.SESSION_USER_INFO);
        if (user == null)
            throw new NullPointerException("用户信息丢失！");
       /* List<Map<String,Object>>list=userMessageService.getMessage(user.getUser_id(),SysConstant.ORDER);
        ActionContext.getContext().put("orderList",list);*/
        return "userMessage";
    }
    /**
     * 获取页面信息
     */
    public String getMessage(){
        HttpServletRequest request=ServletActionContext.getRequest();
        String type=request.getParameter("type");
        if(StringUtils.isBlank(type))
            throw new IllegalArgumentException("我的信息->非法参数！所需参数为type空!");
        User user=(User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.SESSION_USER_INFO);
        List<Map<String,Object>>list=userMessageService.getMessage(user.getUser_id(),type);
        Map<String,Object>resultData=new HashMap<String, Object>();
        resultData.put("data",list);
        resultData.put("total",list.size());
        this.resultJson=resultData;
        return "ajaxJson";
    }

    /**
     * 获取订单详细信息
     * @return
     */
    public String getOrderInfo(){
        HttpServletRequest request=ServletActionContext.getRequest();
        String number=request.getParameter("number");
        if(StringUtils.isBlank(number))
            throw new IllegalArgumentException("我的信息->非法参数！所需参数为type空!");
        User user=(User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.SESSION_USER_INFO);
        List<Map<String,Object>>list=userMessageService.getOrderInfo(number);
        //详情底部信息
        Map<String,Object>bottomMap=userMessageService.getBottomMap(number);
        Map<String,Object>resultData=new HashMap<String, Object>();
        resultData.put("data",list);
        resultData.put("total",list.size());
        resultData.put("bottomMap",bottomMap);
        this.resultJson=resultData;
        return "ajaxJson";
    }

    //删除地址
    public String deleteMyAddress(){
        HttpServletRequest request=ServletActionContext.getRequest();
        String addrId=request.getParameter("addrId");
        User user=(User) ActionContext.getContext().getSession().get(SysConstant.SESSION_USER_INFO);
        int resultRow=userMessageService.deleteMyAddress(user.getUser_id(),addrId);
        this.result="false";
        if(resultRow >0){
            this.result="true";
        }
        return "resultbBoolean";
    }
    //删除订单
    public String delOrder(){
        HttpServletRequest request=ServletActionContext.getRequest();
        String orderNumber=request.getParameter("orderNumber");
        int count=userMessageService.delOrder(orderNumber);
        this.result="false";
        if(count >0){
            this.result="true";
        }
        return "resultbBoolean";
    }
    //取消订单
    public String cancelOrder(){
        HttpServletRequest request=ServletActionContext.getRequest();
        String orderNumber=request.getParameter("orderNumber");
        int count=userMessageService.cancelOrder(orderNumber);
        this.result="false";
        if(count >0){
            this.result="true";
        }
        return "resultbBoolean";
    }

    /**
     * 修改地址类型
     * @return
     */
    public String setOrAndDeAddr(){
        HttpServletRequest request=ServletActionContext.getRequest();
        String addrId=request.getParameter("addrId");
        User user=(User) request.getSession().getAttribute(SysConstant.SESSION_USER_INFO);
        int resultRow=userMessageService.setOrAndDeAddr(user.getUser_id(),addrId);
        this.result="false";
        if(resultRow >0){
            this.result="true";
        }
        return "resultbBoolean";
    }

    /**
     * 修改用户信息
     * @return
     */
    public String updateUser(){
        HttpServletRequest request= ServletActionContext.getRequest();
        String user_name=request.getParameter("user_name");
        String user_phone=request.getParameter("user_phone");
        String user_email=request.getParameter("user_email");
        User user=(User) request.getSession().getAttribute(SysConstant.SESSION_USER_INFO);
        user.setUser_name(user_name);
        user.setUser_phone(user_phone);
        user.setUser_email(user_email);
        int count= userMessageService.update(user);
        this.result="false";
        if(count == 1)
            this.result="true";
        return "resultbBoolean";
    }

    public String getProvinceInfo(){
        HttpServletRequest request=ServletActionContext.getRequest();
        String id = request.getParameter("parentId");
        this.resultList= userMessageService.getProvinceInfo(id);
        return SUCCESS;
    }
    public String saveReceiptAddr(){
        HttpServletRequest request=ServletActionContext.getRequest();
        User user=(User) request.getSession().getAttribute(SysConstant.SESSION_USER_INFO);
        if(user == null){
            throw new NullPointerException();
        }
        String receiptUser=request.getParameter("receiptUser");
        String receiptAddr=request.getParameter("receiptAddr");
        String receiptPhone=request.getParameter("receiptPhone");
        Map<String,Object>map=new HashMap<String, Object>();
        map.put("userId",user.getUser_id());
        map.put("receiptUser",receiptUser);
        map.put("receiptAddr",receiptAddr);
        map.put("receiptPhone",receiptPhone);
        int resultRow= userMessageService.saveReceiptAddr(map);
        this.result="false";
        if(resultRow>0){
            this.result="true";
        }
        return "resultbBoolean";
    }
}
