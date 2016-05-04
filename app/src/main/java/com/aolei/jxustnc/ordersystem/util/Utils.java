package com.aolei.jxustnc.ordersystem.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import java.util.regex.Pattern;

/**
 * Created by aolei on 2016/4/12.
 */
public class Utils {
    //appID
    public static final String APPID = "a488221de9e0bed51d79724389ca6212";
    //电话号码的正则表达式
    public static final String REGEX_PHONE = "^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$";
    //密码验证正则表达式
    public static final String REGEX_PWD = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
    //宿舍号的正则表达式
    public static final String REGEX_DORMITORYNUM = "^([1-9][1-7])(([1-3][0-9])|([0][1-9]))$";
    //判断是汉字的正则表达式,且长度在1到10之间
    public static final String REGEX_ISCHINESE = "^[\u4E00-\u9FA5]{1,10}$";
    //判断字符串的长度小于10

    private static ProgressDialog mProgressDialog;

    /**
     * 判断参数是否符合是合法的手机号码
     *
     * @param phoneNum
     * @return 如果合法，则返回true，如果不合法，则返回false
     */
    public static boolean isMobileNumber(String phoneNum) {
        boolean result = false;
        if (phoneNum != null){
            result = Pattern.matches(REGEX_PHONE, phoneNum);
        }
        Log.d("1",result+"");
        return result;

    }

    /**
     * 判断输入的密码是否合法
     *
     * @param pwd
     * @return 合法返回true，不合法返回false
     */
    public static boolean isPassWord(String pwd) {
        boolean result = false;
        if (pwd != null){
            result = Pattern.matches(REGEX_PWD, pwd);
        }
        Log.d("1",result+"");
        return result;
    }

    /**
     * 判断输入的宿舍号码是否合法
     *
     * @param dormitorynum
     * @return 合法返回true，不合法返回false
     */
    public static boolean isDormitoryNum(String dormitorynum) {
        boolean result = false;
        if (dormitorynum != null){
            result = Pattern.matches(REGEX_DORMITORYNUM, dormitorynum);
        }
        Log.d("1",result+"");
        return result;
    }

    /**
     * 判断两次密码是否一致
     * @param pwd
     * @param pwd1
     * @return
     */
    public static boolean isEquals(String pwd,String pwd1){
        boolean result = false;
        if (pwd != null && pwd1 != null){
            result = pwd.equals(pwd1);
        }
        Log.d("1",result+"");
            return result;
    }

    /**
     * 判断验证码是否为空
     * @param code
     * @return
     */
    public static boolean isEmpty(String code){
        if (code != null){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 隐藏输入法
     * @param context
     */
    public static void hideInputMethod(Context context){
        InputMethodManager inputMethodManager = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = inputMethodManager.isActive();
        if (isOpen){
            inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    /**
     * 显示加载对话框
     *
     * @param context
     */
    public static void showProgressDialog(Context context) {
        ProgressDialog dialog = ProgressDialog.show(context, "正在加载", "请稍后");
        mProgressDialog = dialog;
    }

    /**
     *关闭ProgressDialog
     */
    public static void closeProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.cancel();
        }
    }
}
