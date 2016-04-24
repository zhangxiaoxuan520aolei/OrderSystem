package com.aolei.jxustnc.ordersystem.entity;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * 商店食物实体类
 * Created by NewOr on 2016/4/11.
 */

public class Food extends BmobObject implements Serializable {

    private String name;//食物名称
    private String price;//售价
    private String sold_count;//销售数量
    private Store store;//所属食堂
    private String img_url;//图片url
    private String food_desc;//食物描述

    public void setFood_desc(String food_desc) {
        this.food_desc = food_desc;
    }

    public String getFood_desc() {
        return food_desc;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSold_count() {
        return sold_count;
    }

    public void setSold_count(String sold_count) {
        this.sold_count = sold_count;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Store getStore() {
        return store;
    }
}
