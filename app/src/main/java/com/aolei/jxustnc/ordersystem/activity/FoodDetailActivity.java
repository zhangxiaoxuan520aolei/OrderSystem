package com.aolei.jxustnc.ordersystem.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.entity.FoodEntity;
import com.aolei.jxustnc.ordersystem.util.DividerItemDecoration;
import com.aolei.jxustnc.ordersystem.util.RecyclerViewCommonAdapter;
import com.aolei.jxustnc.ordersystem.util.RecyclerViewViewHolder;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class FoodDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsing_layout;
    private RecyclerView food_detail_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsing_layout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsing_layout.setTitle("北京烤鸭");
        setRecyclerView();
    }

    private void setRecyclerView() {
        List<FoodEntity> data_list = new ArrayList<>();
        FoodEntity foodEntity;
        for (int i = 0; i < 20; i++) {
            foodEntity = new FoodEntity();
            foodEntity.setDetail("NewOrin");
            data_list.add(foodEntity);
        }
        food_detail_recyclerview = (RecyclerView) findViewById(R.id.food_detail_recyclerview);
        food_detail_recyclerview.setFocusable(false);
        food_detail_recyclerview.setAdapter(new RecyclerViewCommonAdapter<FoodEntity>(this, R.layout.item_food_comment, data_list) {
            @Override
            public void convert(RecyclerViewViewHolder holder, FoodEntity foodEntity) {
                TextView tv = holder.getView(R.id.tv_comment_name);
                tv.setText(foodEntity.getDetail());
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        food_detail_recyclerview.setLayoutManager(linearLayoutManager);
        food_detail_recyclerview.setNestedScrollingEnabled(false);
        food_detail_recyclerview.setItemAnimator(new DefaultItemAnimator());
        food_detail_recyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }
}
