package com.aolei.jxustnc.ordersystem.util;

import android.content.Context;

import com.aolei.jxustnc.ordersystem.entity.Comment;
import com.aolei.jxustnc.ordersystem.entity.Food;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * 访问网络类
 * Created by NewOr on 2016/4/21.
 */
public class HttpUtils {

    /**
     * 查询所有食物方法
     *
     * @param context
     * @param query
     */
    public static void queryFood(Context context, BmobQuery<Food> query, FindListener<Food> foodFindListener) {
        query.order("-sold_count");
        query.include("store");
        query.findObjects(context, foodFindListener);
    }

    /**
     * 查询食物评论
     *
     * @param context
     * @param foodId
     * @param query
     * @param findListener
     */
    public static void queryFoodCommment(Context context, String foodId, FindListener<Comment> findListener) {
        BmobQuery<Comment> query = new BmobQuery<>();
        query.include("user");
        query.include("food");
        query.addWhereEqualTo("food", foodId);
        query.findObjects(context, findListener);
    }
}
