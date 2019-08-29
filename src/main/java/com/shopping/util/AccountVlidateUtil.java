package com.shopping.util;

import java.util.regex.Pattern;

/**
 * @author: caoyuan
 * @Email: caoyuan@tydic.com
 * @Description:
 * @Date: 16:46 2018/3/13
 */
public class AccountVlidateUtil {
    /**
     * 验证手机号
     */
    public static final String REGEX_PHONE="^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4E00-\u9FA5]{1,}$";
    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";
    /**
     * 正则表达式：验证URL
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    /**
     * 正则表达式：验证IP地址
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

    /**
     * 验证姓名
     * @param name
     * @return
     */
    public static boolean isName(String name){
        return Pattern.matches(REGEX_CHINESE,name);
    }

    /**
     * 验证邮箱
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        return Pattern.matches(REGEX_EMAIL,email);
    }

    /**
     * 验证电话号码
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone){
        return Pattern.matches(REGEX_PHONE,phone);
    }
}
