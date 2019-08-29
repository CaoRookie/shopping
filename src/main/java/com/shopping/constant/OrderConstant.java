package com.shopping.constant;

/**
 * @Name: 订单状态常量
 * @Description: ${todo}
 * @Author cy
 * @Date 2018/5/1615:09
 */
public class OrderConstant {

    /**
     *  全部订单
     */
    public static final String ORDER="order";
    /**
     *  已处理订单
     */
    public static final String ORDERCL="orderCL";
    public static final int RECEIVE = 1;
    /**
     *  待处理订单
     */
    public static final String ORDERDCL="orderDCL";
    public static final int SHIP = 0;
    /**
     *  交易成功订单
     */
    public static final String ORDERCG="orderCG";
    public static final int EVALUATION = 2;
    /**
     *  待支付
     */
    public static final String ORDERDZF="orderDZF";
    public static final int PAYMENT = -1;

    /**
     *  待支付
     */
    public static final String ORDERQX="orderQX";
    public static final int CANCEL = 3;

}
