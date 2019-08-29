package com.shopping.action.sreach;

import com.opensymphony.xwork2.ActionSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

/**
 * @author: caoyuan
 * @Email: caoyuan@tydic.com
 * @Description:
 * @Date: 17:05 2018/1/15
 */
@Controller
public class SreachAction extends ActionSupport {

    private static final  Logger logger= LoggerFactory.getLogger(SreachAction.class);

    public String service(){
        logger.info("SreachAction--->this is sreach");
        return "sreach";
    }
}
