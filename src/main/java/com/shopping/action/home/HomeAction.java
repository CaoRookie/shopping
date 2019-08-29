package com.shopping.action.home;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shopping.constant.SysConstant;
import com.shopping.domain.Announcement;
import com.shopping.domain.News;
import com.shopping.domain.Product;
import com.shopping.domain.User;
import com.shopping.service.HomeService;
import com.shopping.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author: caoyuan
 * @Email: caoyuan@tydic.com
 * @Description: 主页
 * @Date: 11:50 2018/1/12
 */
@Scope("prototype")
@Controller
public class HomeAction extends ActionSupport implements ModelDriven<Product>{

    //注入模型驱动
    private Product product=new Product();
    public Product getModel() {
        return product;
    }
    //注入service
    @Autowired
    private HomeService homeService;

    @Autowired
    private LoginService loginService;

    private String result;
    public String getResult() {
        return result;
    }

    private static final Logger logger= LoggerFactory.getLogger(HomeAction.class);

    public String home(){
        logger.info("HomeAction-->this is home");
        List<Product> clickList=homeService.getClickRateHight();
        logger.info("点击率前四:{}",clickList);
        List<Product> saleHightProduct=homeService.getSaleProductQuantity();
        logger.info("销量前四:{}",saleHightProduct);
        List<Product> newProduct=homeService.getNewProduct();
        logger.info("新商品{}",newProduct);
        List<Product> promotionsProduct=homeService.getPromotionsProduct();
        logger.info("促销商品：{}",promotionsProduct);
        List<Announcement> announcements=homeService.getAnnouncement();
        logger.info("公告信息：{}",announcements);
        List<News>newsList=homeService.getNews();
        logger.info("新闻信息：{}",newsList);

        ActionContext.getContext().put("clickList",clickList);
        ActionContext.getContext().put("saleHightProduct",saleHightProduct);
        ActionContext.getContext().put("newProduct",newProduct);
        ActionContext.getContext().put("promotionsProduct",promotionsProduct);
        ActionContext.getContext().put("announcements",announcements);
        ActionContext.getContext().put("newsList",newsList);
        return "home";
    }
    //添加产品到购物车
    public String addProductToCar(){
        HttpServletRequest request =ServletActionContext.getRequest();
        Integer pid=Integer.valueOf(request.getParameter("pid"));
        logger.info("获取到的产品id={}",pid);
        User user=(User) ActionContext.getContext().getSession().get(SysConstant.SESSION_USER_INFO);
        int resRows = homeService.addProductToCar(user.getUser_id(),pid);
        this.result = "true";
        if(resRows <= 0){
            this.result="false";
        }
        return "resultbBoolean";
    }
}
