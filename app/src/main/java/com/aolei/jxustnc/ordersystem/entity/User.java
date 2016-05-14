package com.aolei.jxustnc.ordersystem.entity;

import cn.bmob.v3.BmobUser;

/**
 * Created by aolei on 2016/4/12.
 */
public class User extends BmobUser {

    private String trueName;
    private String dormitoryNumber;
    private String store_name;

    public User(){
        this.setTableName("_User");
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getDormitoryNumber() {
        return dormitoryNumber;
    }

    public void setDormitoryNumber(String dormitoryNumber) {
        this.dormitoryNumber = dormitoryNumber;
    }


}
