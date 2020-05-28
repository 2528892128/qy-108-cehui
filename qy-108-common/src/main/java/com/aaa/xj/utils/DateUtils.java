package com.aaa.xj.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/5/27 15:01
 * @Description
 *      日期处理工具类
 **/
public class DateUtils {

    private DateUtils() {

    }

    /**
     * 定义日期格式
     */
    public static final String DATE_TYPE = "yyyy-MM-dd";


    /**
     * @author Seven Lee
     * @description
     *      按照DATE_TYPE格式来进行转换
     * @param []
     * @date 2020/5/27
     * @return java.lang.String
     * @throws
    **/
    public static final String formatDate(Object date) {
        if(null == date) {
            return null;
        } else {
            return formatDate(date, DATE_TYPE);
        }
    }

    /**
     * @author Seven Lee
     * @description
     *      按照指定格式日期来进行转换
     * @param []
     * @date 2020/5/27
     * @return java.lang.String
     * @throws
    **/
    public static final String formatDate(Object date, String formatType) {
        if(null == date) {
            return null;
        } else {
            if(StringUtils.isNotEmpty(formatType)) {
                // 说明最终需要根据客户所定义的格式来进行转换
                SimpleDateFormat format = new SimpleDateFormat(formatType);
                return format.format(date);
            } else {
                // 说明你没有给我转你自己的类型(这里千万不要直接return null--->容易抛出格式转换异常的错误)
                return formatDate(date);
            }
        }
    }

    /**
     * @author Seven Lee
     * @description
     *      将时间转换为字符串
     * @param [millisecond]
     * @date 2020/5/27
     * @return java.lang.String
     * @throws
    **/
    public static String formatDateAgo(long millisecond) {
        StringBuilder stringBuilder = new StringBuilder();
        if(1000 > millisecond) {
            // 说明只是毫秒
            stringBuilder.append(millisecond).append("毫秒");
        } else {
            // 说明传进来的long类型毫秒数大于1000
            Integer ss = 1000;
            Integer mi = ss * 60;
            Integer hh = mi * 60;
            Integer dd = hh * 24;

            Long day = millisecond / dd;
            Long hour = (millisecond - day * dd) / hh;
            Long minute = (millisecond - day * dd - hour * hh) / mi;
            Long second = (millisecond - day * dd - hour *hh - minute * mi) / ss;

            if(day > 365) {
                return formatDate(new Date(millisecond), "yyyy年MM月dd日 HH时mm分ss秒");
            }
            if(day > 0) {
                stringBuilder.append(day).append("天");
            }
            if(hour > 0) {
                stringBuilder.append(hour).append("小时");
            }
            if(minute > 0) {
                stringBuilder.append(minute).append("分钟");
            }
            if(second > 0) {
                stringBuilder.append(second).append("秒");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * @author Seven Lee
     * @description
     *      获取系统当前时间
     * @param []
     * @date 2020/5/27
     * @return java.lang.String
     * @throws
    **/
    public static final String getCurrentDate() {
        return formatDate(new Date());
    }

    /**
     * @author Seven Lee
     * @description
     *      获取当年年度
     * @param []
     * @date 2020/5/27
     * @return java.lang.Integer
     * @throws
    **/
    public static Integer getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

}
