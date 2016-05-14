package com.aolei.jxustnc.ordersystem.util;

import android.content.Context;
import android.util.Log;

import com.aolei.jxustnc.ordersystem.entity.Comment;
import com.aolei.jxustnc.ordersystem.entity.Food;
import com.aolei.jxustnc.ordersystem.entity.Order;
import com.aolei.jxustnc.ordersystem.entity.User;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.listener.ConnectListener;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 访问网络类
 * Created by NewOr on 2016/4/21.
 */
public class HttpUtils {

    private Context mContext;

    public HttpUtils(Context context) {
        mContext = context;
    }

    /**
     * 查询所有食物方法
     *
     *
     * @param query
     */
    public void queryFood(BmobQuery<Food> query, FindListener<Food> foodFindListener) {
        query.order("-sold_count");
        query.include("store.user");
        query.findObjects(mContext, foodFindListener);
    }

    /**
     * 查询食物评论
     *
     * @param foodId
     * @param findListener
     */
    public void queryFoodCommment(String foodId, FindListener<Comment> findListener) {
        BmobQuery<Comment> query = new BmobQuery<>();
        query.include("user");
        query.include("food");
        query.addWhereEqualTo("food", foodId);
        query.findObjects(mContext, findListener);
    }

    /**
     * 通过店名查找店内食物
     *
     *
     * @param query
     * @param foodFindListener
     * @param store_objectId
     */
    public void queryFoodByStoreName(BmobQuery<Food> query, FindListener<Food> foodFindListener, String store_objectId) {
        Log.d("name", store_objectId);
        query.addWhereEqualTo("store", store_objectId);
        query.order("-updatedAt");
        query.include("store.user");
        query.findObjects(mContext, foodFindListener);
    }

    /**
     * 查询用户订单信息
     *
     * @param listener
     */
    public void queryUserMsg(String store_uid, FindListener<Order> listener) {
        BmobQuery<Order> query = new BmobQuery<>();
        query.include("user");
        query.addWhereEqualTo("store_uid", store_uid);
        query.findObjects(mContext, listener);
    }

    /**
     * 连接Bmob服务器
     */
    public void connectBmobServer() {
        User user = BmobUser.getCurrentUser(mContext, User.class);
        if (user != null) {
            BmobIM.connect(user.getObjectId(), new ConnectListener() {
                @Override
                public void done(String uid, BmobException e) {
                    if (e == null) {
                        Log.d("NewOrin", "Bmob服务器连接成功");
                        ToastUtil.showShort(mContext, "Connected");
                    } else {
                        Log.d("NewOrin", "Bmob服务器连接失败");
                        ToastUtil.showShort(mContext, "Connected Failed");
                    }
                }
            });
        }
    }
}
