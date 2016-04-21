package com.aolei.jxustnc.ordersystem.entity;

import cn.bmob.v3.BmobObject;

/** 店铺的实体类
 * Created by aolei on 2016/4/17.
 */
public class Store extends BmobObject{
    private String store_pic;
    private String store_des;

    public String getStore_pic() {
        return store_pic;
    }

    public void setStore_pic(String store_pic) {
        this.store_pic = store_pic;
    }

    public String getStore_des() {
        return store_des;
    }

    public void setStore_des(String store_des) {
        this.store_des = store_des;
    }
}
