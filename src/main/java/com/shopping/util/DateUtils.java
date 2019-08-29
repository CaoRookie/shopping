package com.shopping.util;

import com.shopping.constant.SysConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static com.shopping.constant.SysConstant.MILL_DATE_FORMATE;
import static com.shopping.constant.SysConstant.STANDARD_DATE_FORMATE;

/**
 * 日期操作工具类
 * Created by pq on 2017/8/16.
 */
public class DateUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    /**
     * 获取上一月
     * @return String:"201707"
     */
    public static String getLastMonth() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
        String month = sf.format(c.getTime());
        return month;
    }

    /**
     * 获取上上月
     * @return String:"201707"
     */
    public static String getMonthBeforeLast() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -2);
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
        return sf.format(c.getTime());
    }

    /**
     * 获取当前年
     * @return String:"2017"
     */
    public static String getCurrentYear() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy");
        String year = sf.format(c.getTime());
        return year;
    }

    /**
     * 获取精确到秒的当前时间字符串,yyyy-MM-dd HH:mm:ss
     * @return String:"yyyy-MM-dd HH:mm:ss"
     */
    public static String getCurrentDateTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(c.getTime());
    }

    /**
     * 获取精确到毫秒的当前时间字符串,yyyyMMddHHmmssSSS
     * @return String:"HHmmssSSS"
     */
    public static String getCurrFullMilliDateTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sf.format(c.getTime());
    }

    /**
     * 获取当前日期
     * @return String:"20170708"
     */
    public static String getCurrentDay() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        String day = sf.format(c.getTime());
        return day;
    }

    /**
     * 获取当天的最后时刻(晚上23:59:59:999)
     * @return
     */
    public static Date getCurrentDayLastTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * yyyy-MM-dd hh:mm:ss格式的日期字符串转Date
     * @param dateStr
     * @return
     */
    public static Date formateDateStr(String dateStr){
        SimpleDateFormat sf = new SimpleDateFormat(STANDARD_DATE_FORMATE);
        Date date = null;
        try {
            date = sf.parse(dateStr);
        } catch (ParseException e) {
            logger.error("格式化日期字符串:{} 出错！原因: {}，" +
                            "允许的格式:{}",
                   dateStr, e.getMessage(), SysConstant.STANDARD_DATE_FORMATE);
            throw new IllegalArgumentException("日期格式错误！允许的格式:" +
                    SysConstant.STANDARD_DATE_FORMATE);
        }
        return date;
    }

    /**
     * yyyyMMddhhmmssSSS格式的日期字符串转Date
     * @param dateStr
     * @return
     */
    public static Date formateMillDateStr(String dateStr) {
        SimpleDateFormat sf = new SimpleDateFormat(MILL_DATE_FORMATE);
        Date date = null;
        try {
            date = sf.parse(dateStr);
        } catch (ParseException e) {
            logger.error("格式化日期字符串:{} 出错！原因: {}，" +
                            "允许的格式:{}",
                    dateStr, e.getMessage(), SysConstant.MILL_DATE_FORMATE);
            throw new IllegalArgumentException("日期格式错误！允许的格式:" +
                    SysConstant.MILL_DATE_FORMATE);
        }
        return date;
    }

    /**
     * 获取当前月的前N个月
     * @param n
     * @return
     */
    public static List<String> getRecentlyMonthList(int n) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
        List<String> list = new LinkedList<String>();
        if (n < 0) {
            return null;
        }
        if (n == 0) {
            Calendar c = Calendar.getInstance();
            list.add(sf.format(c.getTime()));
            return list;
        }
        for (int i = 0; i < n; i++) {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, -(i+1));
            list.add(sf.format(c.getTime()));
        }
        return list;
    }
}
