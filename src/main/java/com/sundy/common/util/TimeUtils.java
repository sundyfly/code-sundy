package com.sundy.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author sundy
 * @since 1.8
 * 日期: 2018年05月18日 14:16:00
 * 描述：
 */
public class TimeUtils {
    public static final String SDF_DATE = "yyyy-MM-dd";
    public static final String SDF_STANDARD = "yyyy-MM-dd HH:mm:ss";
    /**
     * 格式化时间
     * @return
     */
    public static String showTime(long start,long end){
        long calc = end-start;
        long a = calc%1000;
        calc/=1000;
        long b = calc%60;
        calc/=60;
        long c=calc%60;
        calc/=60;
        long d=calc%60;
        return d+"时"+c+"分"+b+"秒"+a+"毫秒";
    }

    public static Date form(String str){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 把Date时间转换成字符串
     * @param date
     * @param form 格式化的模型 TimeUitls.SDF_DATE
     * @return
     */
    public static String parseToStr(Date date,String form){
        SimpleDateFormat sdf = new SimpleDateFormat(form);
        return sdf.format(date);
    }

    public static String getFrontDay(Date date){
        long time =  date.getTime()-1000*60*60*24;
        date.setTime(time);
        return parseToStr(date, SDF_STANDARD);
    }
}
