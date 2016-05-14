package com.aolei.jxustnc.ordersystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.util.HttpUtils;

import cn.bmob.v3.BmobUser;

/**
 * APP欢迎界面
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (BmobUser.getCurrentUser(this) == null) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            HttpUtils httpUtils = new HttpUtils(this);
            httpUtils.connectBmobServer();
            String tag = (String) BmobUser.getObjectByKey(SplashActivity.this, "tag");
            if ("0".equals(tag)) {
                startActivity(new Intent(this, MainActivity.class));
            } else if ("1".equals(tag)) {
                startActivity(new Intent(this, ShopMainActivity.class));
            }
        }
        finish();
    }
}
