package com.sundy.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author sundy
 * @since 1.8
 * 日期: 2018年03月16日 15:45:45
 * 描述：验证工具类
 */
public class VerificationUtil {
    /**
     * 邮箱格式验证
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_.\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /***
     * 密码验证
     *
     */
    public static boolean isPassword(String password) {
        String str = "^([a-zA-Z0-9]{0,50})";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(password);
        return m.matches();
    }

    /***
     * 用户名验证证
     */
    public static boolean isAdminname(String adminname) {
        String str = "^([a-zA-Z]{1}[a-zA-Z0-9_]{5,15})";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(adminname);
        return m.matches();
    }

    /**
     * 判断在image/是否为图片
     * @param ContentType
     * @return
     */
    public static boolean isImage(String ContentType) {
        String str = "^(image/gif|image/jpg|image/png|image/jpeg)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(ContentType);
        return m.matches();
    }

    /**
     * 判断字符串是否为数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 校验是否为电话号码
     *
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone){
        Pattern pattern = Pattern.compile("^1[3|4|5|7|8][0-9]{9}$");
        return pattern.matcher(phone).matches();
    }

    /**
     * 校验身份证是否正确
     *
     * @param idCard
     * @return
     */
    public static boolean isIDCard(String idCard){
        if(idCard.length()!=15 && idCard.length()!=18){
            return false;
        }
        if(idCard.length()==15){
            String regex = "\\d{14}[[0-9],0-9xX]";
            return Pattern.compile(regex).matcher(idCard).matches();
        }else if(idCard.length()==18){
            String regex = "\\d{17}[[0-9],0-9xX]";
            return Pattern.compile(regex).matcher(idCard).matches();
        }else {
            return false;
        }
    }

    /**
     * 校验输入的是否都是汉字
     * @param str
     * @return
     */
    public static boolean isHanzi(String str){
        return Pattern.compile("^[\\u4e00-\\u9fa5]{0,}$").matcher(str).matches();
    }

    public static void main(String[] args) {
        System.out.println(isHanzi(""));
    }
}
