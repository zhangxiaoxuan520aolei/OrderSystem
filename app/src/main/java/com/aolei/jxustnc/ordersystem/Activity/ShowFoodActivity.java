package com.aolei.jxustnc.ordersystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.entity.Store;
import com.aolei.jxustnc.ordersystem.util.Utils;

import cn.bmob.v3.Bmob;

public class ShowFoodActivity extends AppCompatActivity {
    private ListView mListView;
    private Toolbar mToolbar;
    private Store store;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bmob.initialize(this, Utils.APPID);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_food);
        initView();
        Intent intent = this.getIntent();
        store = (Store)intent.getSerializableExtra("store_info");
        mToolbar.setTitle(store.getStore_name());
    }
    void initView(){
        mListView = (ListView)findViewById(R.id.store_food);
        mToolbar = (Toolbar)findViewById(R.id.store_title);
    }
}
