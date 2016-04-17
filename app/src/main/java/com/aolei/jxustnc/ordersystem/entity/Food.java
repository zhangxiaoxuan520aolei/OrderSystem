package com.aolei.jxustnc.ordersystem.entity;

import android.widget.ImageView;

/** 食物的实体类，对食物的各项属性的描述
 * Created by aolei on 2016/4/15.
 */
public class Food {
    private ImageView foodImage;
    private String foodName;
    private String foodDescribe;
    private String foodPrice;
    private String foodSaleNum;
    private String foodCanteen;

    public ImageView getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(ImageView foodImage) {
        this.foodImage = foodImage;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDescribe() {
        return foodDescribe;
    }

    public void setFoodDescribe(String foodDescribe) {
        this.foodDescribe = foodDescribe;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodSaleNum() {
        return foodSaleNum;
    }

    public void setFoodSaleNum(String foodSaleNum) {
        this.foodSaleNum = foodSaleNum;
    }

    public String getFoodCanteen() {
        return foodCanteen;
    }

    public void setFoodCanteen(String foodCanteen) {
        this.foodCanteen = foodCanteen;
    }

    public Food() {
        super();
    }

    public String toString() {
        return "Food{" +
                "foodImage=" + foodImage +
                ", foodName='" + foodName + '\'' +
                ", foodDescribe='" + foodDescribe + '\'' +
                ", foodPrice='" + foodPrice + '\'' +
                ", foodSaleNum='" + foodSaleNum + '\'' +
                ", foodCanteen='" + foodCanteen + '\'' +
                '}';
    }
}
