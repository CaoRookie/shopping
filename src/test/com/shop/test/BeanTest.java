package com.shop.test;

import com.shopping.action.home.HomeAction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: caoyuan
 * @Email: caoyuan@tydic.com
 * @Description:
 * @Date: 8:45 2018/1/15
 */
public class BeanTest {
    @Test
    public void testBean(){
        ApplicationContext context=new ClassPathXmlApplicationContext("/WEB-INF/applicationContext.xml");
        HomeAction homeAction=(HomeAction)context.getBean("homeAction");
        homeAction.home();
    }
}
