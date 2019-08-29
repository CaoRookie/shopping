package com.shop.test;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: caoyuan
 * @Email: caoyuan@tydic.com
 * @Description:
 * @Date: 16:08 2018/1/12
 */
public class SessionFactoryTest {
    @Test
    public void testSessionFactory(){
        ApplicationContext context=new ClassPathXmlApplicationContext("WEB-INF/applicationContext.xml");
        SessionFactory sessionFactory=(SessionFactory)context.getBean("sessionFactory");
        System.out.println(sessionFactory);
    }


}
