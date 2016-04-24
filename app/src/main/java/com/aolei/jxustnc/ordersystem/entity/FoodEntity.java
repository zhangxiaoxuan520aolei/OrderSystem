package com.aolei.jxustnc.ordersystem.entity;

import cn.bmob.v3.BmobObject;

/**
 * 商店食物实体类
 * Created by NewOr on 2016/4/11.
 */
public class FoodEntity extends BmobObject {
    private String detail;//食物名称
    private String canteen;//食堂
    private String shop_name;//商店名称
    private String price;//售价
    private String sold_count;//销售数量

    public FoodEntity(){
        this.setTableName("Food");
    }
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCanteen() {
        return canteen;
    }

    public void setCanteen(String canteen) {
        this.canteen = canteen;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
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
}
