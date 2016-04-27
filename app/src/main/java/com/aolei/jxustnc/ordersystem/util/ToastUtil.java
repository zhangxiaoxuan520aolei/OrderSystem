package com.aolei.jxustnc.ordersystem.util;

import android.content.Context;
import android.widget.Toast;

/**
 * 显示Toast工具类
 * Created by NewOr on 2016/4/23.
 */
public class ToastUtil {
    private final static boolean isShow = true;

    public static void showShort(Context context, CharSequence text) {
        if (isShow) Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context, CharSequence text) {
        if (isShow) Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
