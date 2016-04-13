package com.aolei.jxustnc.ordersystem.activity;

import android.app.Activity;

import android.os.Bundle;
import android.view.Window;

import com.aolei.jxustnc.ordersystem.R;

public class RegistActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
    }
}
