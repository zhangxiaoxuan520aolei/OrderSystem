package com.aolei.jxustnc.ordersystem.entity;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * 订单实体类
 * Created by NewOr on 2016/4/24.
 */
public class Order extends BmobObject implements Serializable {

    private Food food;
    private User user;
    private Integer count;//订单食物数量
    private String money;//小计
    private Boolean isDeal;//是否交易完成
    private String dorm;//宿舍号
    private String store_uid;//商家用户id

    public void setMoney(String money) {
        this.money = money;
    }

    public String getMoney() {
        return money;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Boolean getDeal() {
        return isDeal;
    }

    public void setDeal(Boolean deal) {
        isDeal = deal;
    }

    public String getDorm() {
        return dorm;
    }

    public void setDorm(String dorm) {
        this.dorm = dorm;
    }

    public void setStore_uid(String store_uid) {
        this.store_uid = store_uid;
    }

    public String getStore_uid() {
        return store_uid;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
