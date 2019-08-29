package com.shopping.constant;

import java.util.regex.Pattern;

/**
 * @author: caoyuan
 * @Email: caoyuan@tydic.com
 * @Description: 系统常量类
 * @Date: 9:36 2018/3/5
 */
public class SysConstant {

    private SysConstant(){}
    /**
     * session中保存用户信息的key
     */
    public static final String SESSION_USER_INFO="_SESSION_USER_INFO";

    /**
     * 日期格式
     */
    //标准日期格式
    public static final String STANDARD_DATE_FORMATE = "yyyy-MM-dd hh:mm:ss";
    //到毫秒的日期格式
    public static final String MILL_DATE_FORMATE = "yyyyMMddhhmmssSSS";

    /**
     * 字符编码
     */
    public static final String UTF8_ENCODING = "UTF-8";

    /**
     * 订单号位数
     */
    public static final int ORDER_NUM=11;

    /**
     * 管理界面----模块中文名称
     */
    public static final String PRODUCT_FILE_NAME="商品.xlsx";
    public static final String PRODUCTTYPE_FILE_NAME="商品类型.xlsx";

    /**
     * 管理界面--模块标识
     */
    public static final String PRODUCT_TYPE="product";
    public static final String PRODUCTTYPE_TYPE="productType";
    public static final String USER="user";
    public static final String ADDRESS="address";

    /**
     * 页面返回结果编码
     * 000：失败
     * 001：成功
     */
    public static final String SUCCESS="001";
    public static final String ERROR="000";

    /**
     * 批处理每次提交大小
     */
    public static final int BATCH_MAX_ROW=50;


    /**
     * 一键购买商品默认个数
     */
    public static final int DEFAULT_NUM=1;

    /**
     * 图片上传保存路径
     */
    public static final String SAVE_IMAGE_PATH="E:/_shopping/image/";
}
