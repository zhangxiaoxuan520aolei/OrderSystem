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
    /**
     * 判断参数是否符合是合法的手机号码
     * @param phoneNum
     * @return 如果合法，则返回真，如果不合法，则返回假
     */
    public static boolean isMobileNumber(String phoneNum){
           return Pattern.matches(REGEX_PHONE,phoneNum);
    }
}
