package com.aolei.jxustnc.ordersystem.entity;

import cn.bmob.v3.BmobObject;

/**
 * 评论实体类
 * Created by NewOr on 2016/4/21.
 */
public class Comment extends BmobObject {
    private String content;//评论内容
    private User user;//评论者
    private Food food;//评论食物

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
