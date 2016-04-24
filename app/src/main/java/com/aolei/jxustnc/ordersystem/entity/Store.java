package com.aolei.jxustnc.ordersystem.entity;

import cn.bmob.v3.BmobObject;

/** 店铺的实体类
 * Created by aolei on 2016/4/17.
 */
public class Store extends BmobObject{

    private String store_pic;
    private String store_des;
    private String store_name;
    private String belong_cateen;

    public void setBelong_cateen(String belong_cateen) {
        this.belong_cateen = belong_cateen;
    }

    public String getBelong_cateen() {
        return belong_cateen;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStore_name() {
        return store_name;
    }

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
