package com.shopping.action.classification;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shopping.domain.Product;
import com.shopping.domain.ProductType;
import com.shopping.service.ClassificationService;
import org.apache.struts2.ServletActionContext;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: caoyuan
 * @Email: caoyuan@tydic.com
 * @Description: 商品分类
 * @Date: 10:32 2018/1/15
 */
@Controller
public class ClassificationAction extends ActionSupport {

    @Autowired
    private ClassificationService classificationService;

    private Map<String,Object> resultJson;

    public Map<String, Object> getResultJson() {
        return resultJson;
    }

    public void setResultJson(Map<String, Object> resultJson) {
        this.resultJson = resultJson;
    }

    private static final Logger logger = LoggerFactory.getLogger(ClassificationAction.class);
    public String service(){
        logger.info("ClassificationAction--->this is classification");
        List<ProductType>typeList=classificationService.getProTypeList();
        if(typeList !=null && typeList.size()>0){
            ActionContext.getContext().put("proTypeList",typeList);
        }
        return "classification";
    }
    public String getProductByType(){
        HttpServletRequest request = ServletActionContext.getRequest();
        String productType = request.getParameter("product_type");
        Map<String,Object>map=new HashMap<String, Object>();
        List<Product> productList=classificationService.getProductByType(new Integer(productType));
        if(!productList.isEmpty()) {
            map.put("data", productList);
            map.put("total", productList.size());
        }
        this.resultJson=map;
        return "ajaxJson";
    }
    public String getProductBySreach(){
        HttpServletRequest request = ServletActionContext.getRequest();
        String productType = request.getParameter("product_type");
        Map<String,Object>map=new HashMap<String, Object>();
        List<Map<String,Object>> productList=classificationService.getProductBySreach(productType);
        if(!productList.isEmpty()) {
            map.put("data", productList);
            map.put("total", productList.size());
        }
        this.resultJson=map;
        return "ajaxJson";
    }
}
