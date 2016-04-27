package com.aolei.jxustnc.ordersystem.util;

import android.content.Context;
import android.util.Log;

import com.aolei.jxustnc.ordersystem.entity.Comment;
import com.aolei.jxustnc.ordersystem.entity.Food;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * 作者：aolei on 2016/4/21 16:48
 * 邮箱：807648567@qq.com
 * 解释权：敖磊
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
     * 通过店名查找店内食物
     * @param context
     * @param query
     * @param foodFindListener
     * @param store_objectId
     */
    public static void queryFoodByStoreName(Context context,BmobQuery<Food> query,FindListener<Food> foodFindListener,String store_objectId){
        Log.d("name",store_objectId);
        query.addWhereEqualTo("store", store_objectId);
        query.order("-updatedAt");
        query.include("store");
        query.findObjects(context,foodFindListener);
    }
    /**
     * 查询食物评论
     *
     * @param context
     * @param foodId
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
