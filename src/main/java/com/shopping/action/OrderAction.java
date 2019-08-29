package com.shopping.action;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shopping.constant.OrderConstant;
import com.shopping.constant.SysConstant;
import com.shopping.domain.Order;
import com.shopping.domain.User;
import com.shopping.domain.UserAddress;
import com.shopping.service.OrderService;
import com.shopping.util.DateUtils;
import com.shopping.util.EncryptUtil;
import com.shopping.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
public class OrderAction extends ActionSupport {

    @Autowired
    private OrderService orderService;
    private static final Logger logger= LoggerFactory.getLogger(OrderAction.class);

    private String result;

    public String getResult() {
        return result;
    }
    private Map<String,Object> resultJson;

    public  Map<String,Object> getUserAddress() {
        return resultJson;
    }

    public void setUserAddress( Map<String,Object> resultJson) {
        this.resultJson = resultJson;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String myOrder(){
//        logger.info("-------------------->this is myOrder");
        HttpServletRequest request= ServletActionContext.getRequest();
        String code=request.getParameter("code");//从购物车提交订单参数
        String param=request.getParameter("param");//从其它地方一键购买提交参数
        User user=(User)request.getSession().getAttribute(SysConstant.SESSION_USER_INFO);
        if(user ==null)
            throw new NullPointerException("用户信息丢失!");
        if(StringUtils.isBlank(code) && StringUtils.isBlank(param))
            throw new IllegalArgumentException("非法参数!所需参数为空!");
        ActionContext context=ActionContext.getContext();
        if(StringUtils.isNotBlank(code)){
            //参数解密 start
            try{
                code=EncryptUtil.decodeString(code);
                logger.info("解密后的参数为{}",code);
            }catch (Exception e){
                logger.info("到订单时,参数解密失败,传入参数为{}",code);
                throw new RuntimeException("解密参数时发生异常！请联系管理员处理！");
            }
            String [] arr=code.split("&");
            for (String key:arr){
                String [] strs=key.split("=");
                if("carIds".equals(strs[0]))
                    context.put("checkedList", getSubmitProduct(strs[1]));
                if("pnum".equals(strs[0]))
                    context.put("pnum", strs[1]);
                if("totalPri".equals(strs[0]))
                    context.put("totalPri", strs[1]);
            }
        }
        if(StringUtils.isNotBlank(param)){
            //参数解密 start
            try{
                param=EncryptUtil.decodeString(param);
                logger.info("解密后的参数为{}",param);
            }catch (Exception e){
                logger.info("到订单时,参数解密失败,传入参数为{}",param);
                throw new RuntimeException("解密参数时发生异常！请联系管理员处理！");
            }
            List<Map<String,Object>>pros=new ArrayList<Map<String, Object>>();
            Map<String,Object> map=orderService.getProductById(Integer.valueOf(param));
            map.put("pnum",SysConstant.DEFAULT_NUM);
            map.put("pid",map.get("id"));
            pros.add(map);
            context.put("checkedList",pros);
            context.put("totalPri",pros.get(0).get("price"));
            context.put("pnum", pros.get(0).get("pnum"));
        }
        List<UserAddress> addressList=orderService.getAddressByUserId(user.getUser_id());
        context.put("addressList",addressList);
        return "myOrder";
    }

    /**
     * 获取购物车选中的商品在购物车对应的记录
     * @param carIds 加密后的，所有的选中的购物车记录id
     * @return
     */
    private List<Map<String,Object>> getSubmitProduct(String carIds){
        List<Map<String,Object>> carList=new ArrayList<Map<String, Object>>();
        String []idArray=carIds.split(",");
        for (String id:idArray){
            carList.add(orderService.getSubmitProductByCarId(Integer.valueOf(id)));
        }
        return carList;
    }

    public String setDefaultAddr(){
        HttpServletRequest request=ServletActionContext.getRequest();
        String addrId=request.getParameter("addrId");
        if(StringUtils.isBlank(addrId))
            throw new IllegalArgumentException("非法参数!所需参数为空!");
        this.result="false";
        int count=orderService.setDefaultAddr(Integer.valueOf(addrId));
        if(count >= 1)
            this.result="true";
        return "resultbBoolean";
    }
    //点击编辑数据回显
    public String getEditAddrd(){
        HttpServletRequest request=ServletActionContext.getRequest();
        String addrId=request.getParameter("addrId");
        if(StringUtils.isBlank(addrId))
            throw new IllegalArgumentException("非法参数!所需参数为空!");
        UserAddress userAddress=orderService.getEditAddrd(Integer.valueOf(addrId));
        this.result=JSON.toJSONString(userAddress);
        return "resultbBoolean";
    }

    /**
     * 模态框点击提交更改
     * @return
     */
    public String updateAddress(){
        HttpServletRequest request=ServletActionContext.getRequest();
        String getProname=request.getParameter("getProname");
        String getProaddr=request.getParameter("getProaddr");
        String getProphone=request.getParameter("getProphone");
        String addrId=request.getParameter("addrId");
        Map<String,Object>map=new HashMap<String, Object>();
        map.put("getProname",getProname);
        map.put("getProaddr",getProaddr);
        map.put("getProphone",getProphone);
        map.put("addrId",addrId);
        if(StringUtils.isBlank(getProname) || StringUtils.isBlank(getProaddr)
                || StringUtils.isBlank(getProphone)|| StringUtils.isBlank(addrId))
            throw new IllegalArgumentException("非法参数!所需参数为空!");
       int count = orderService.updateAddress(map);
        this.result="false";
       if(count >= 1){
           this.result="true";
       }
        return "resultbBoolean";
    }
    public String submitOrder(){
        HttpServletRequest request=ServletActionContext.getRequest();
        User user=(User) request.getSession().getAttribute(SysConstant.SESSION_USER_INFO);
        String pids=request.getParameter("pIds");
        Map<String,Object>map=new HashMap<String, Object>();
        map.put("address",request.getParameter("addrid"));
        map.put("pay",request.getParameter("pay"));
        map.put("post",request.getParameter("post"));
        map.put("totalPrice",request.getParameter("totalPri"));
        map.put("user_id",user.getUser_id());
        map.put("date",DateUtils.getCurrentDateTime());
        map.put("state", OrderConstant.PAYMENT);
        map.put("orderNumber", RandomUtil.getOrderNumber(String.valueOf(user.getUser_id())));
        Order order=JSON.parseObject(JSON.toJSONString(map),Order.class);
        int count=orderService.addOrderToDB(order,pids);
        this.result="false";
        if(count >= 1){
            this.result=map.get("orderNumber").toString()+","+map.get("totalPrice").toString();
        }
        return "resultbBoolean";
    }
}
