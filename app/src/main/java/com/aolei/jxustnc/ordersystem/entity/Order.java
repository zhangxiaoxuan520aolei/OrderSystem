package com.aolei.jxustnc.ordersystem.entity;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * 订单实体类
 * Created by NewOr on 2016/4/24.
 */
public class Order extends BmobObject implements Serializable{
    private Food food;
    private Integer count;//订单食物数量
    private String money;//小计
    private boolean isDeal;//是否交易完成

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

    public boolean isDeal() {
        return isDeal;
    }

    public void setDeal(boolean deal) {
        isDeal = deal;
    }
}
