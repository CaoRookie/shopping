package com.shopping.action.shopCar;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shopping.constant.SysConstant;
import com.shopping.domain.Car;
import com.shopping.domain.User;
import com.shopping.service.ShopCarService;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: caoyuan
 * @Email: caoyuan@tydic.com
 * @Description: 购物车
 * @Date: 10:32 2018/1/15
 */
@Controller
@Scope("prototype")
public class ShopCarAction extends ActionSupport {
    @Autowired
    private ShopCarService shopCarService;

    //table返回数据参数注入
    private Map<String,Object> resultJson;
    public Map<String, Object> getResultJson() {
        return resultJson;
    }

    public void setResultJson(Map<String, Object> resultJson) {
        this.resultJson = resultJson;
    }


    private String result;
    public String getResult() {
        return result;
    }

   private static final Logger logger= LoggerFactory.getLogger(ShopCarAction.class);

    public String service(){
        logger.info("ShopCarAction--->this is shopCar");

        return "shopCar";
    }

    /**
     * 获取我的购物车数据
     * @return
     */
    public String getCarReportData(){
        User user=(User) ActionContext.getContext().getSession().get(SysConstant.SESSION_USER_INFO);
        if(user == null){
            throw new NullPointerException("用户信息丢失!");
        }
        List<Map<String,Object>>myProduct=shopCarService.getMyProduct(user.getUser_id());
        Map<String,Object>resultData=new HashMap<String, Object>();
        resultData.put("data",myProduct);
        resultData.put("total",myProduct.size());
        this.resultJson=resultData;
        //ActionContext.getContext().put("myProduct",myProduct);
        return "ajaxJson";
    }

    /**
     * 移除购物车数据和删除所选数据
     * @return
     */
    public String removeCarReportData(){
        HttpServletRequest request=ServletActionContext.getRequest();
        int pid=Integer.valueOf(request.getParameter("pId"));
        User user=(User) ActionContext.getContext().getSession().get(SysConstant.SESSION_USER_INFO);
        if(user == null){
            throw new NullPointerException();
        }
        int resultRow=shopCarService.removeCarReportData(user.getUser_id(),pid);
        this.result="false";
        if(resultRow >0){
            this.result="true";
        }
        return "resultbBoolean";
    }

    //增加商品数量
    public String addCarReportData(){
        HttpServletRequest request=ServletActionContext.getRequest();
        Integer pId=Integer.valueOf(request.getParameter("pId"));
        User user=(User)request.getSession().getAttribute(SysConstant.SESSION_USER_INFO);
        int count=shopCarService.addCarReportData(user.getUser_id(),pId);
        this.result="false";
        if(count >0){
            this.result="true";
        }
        return "resultbBoolean";
    }

    //减少商品数量
    public String reduceCarReportData(){
        HttpServletRequest request=ServletActionContext.getRequest();
        Integer pId=Integer.valueOf(request.getParameter("pId"));
        User user=(User)request.getSession().getAttribute(SysConstant.SESSION_USER_INFO);
        int count=shopCarService.reduceCarReportData(user.getUser_id(),pId);
        this.result="false";
        if(count >0){
            this.result="true";
        }
        return "resultbBoolean";
    }
}
