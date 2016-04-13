package com.aolei.jxustnc.ordersystem.Utils;

import java.util.regex.Pattern;

/**
 * Created by aolei on 2016/4/12.
 */
public class Utils {
    //appID
    public static final String APPID = "a488221de9e0bed51d79724389ca6212";
    //电话号码的正则表达式
    public static final String REGEX_PHONE = "^((13[0-9])|(15[^4,\\\\D])|(18[0,5-9]))\\\\d{8}$";
    //密码验证正则表达式
    public static final String REGEX_PWD = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
    //宿舍号的正则表达式
    public static final String REGEX_DORMITORYNUM = "^([1-9][1-7])(([1-3][0-9])|([0][1-9]))$\n";
    //邮箱验证正则表达式
    public static final String REGEX_EMAIL = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*\n";

    /**
     * 判断参数是否符合是合法的手机号码
     *
     * @param phoneNum
     * @return 如果合法，则返回true，如果不合法，则返回false
     */
    public static boolean isMobileNumber(String phoneNum) {
        return Pattern.matches(REGEX_PHONE, phoneNum);
    }

    /**
     * 判断输入的密码是否合法
     *
     * @param pwd
     * @return 合法返回true，不合法返回false
     */
    public static boolean isPassWord(String pwd) {
        return Pattern.matches(REGEX_PWD, pwd);
    }

    /**
     * 判断输入的宿舍号码是否合法
     *
     * @param dormitorynum
     * @return 合法返回true，不合法返回false
     */
    public static boolean isDormitoryNum(String dormitorynum) {
        return Pattern.matches(REGEX_DORMITORYNUM, dormitorynum);
    }

    /**
     * 判断输入的邮箱是否合法
     * @param email
     * @return 合法返回true，不合法返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL,email);
    }
}
