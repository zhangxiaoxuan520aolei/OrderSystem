package com.aolei.jxustnc.ordersystem.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.adapter.RecyclerViewCommonAdapter;
import com.aolei.jxustnc.ordersystem.adapter.RecyclerViewViewHolder;
import com.aolei.jxustnc.ordersystem.entity.Comment;
import com.aolei.jxustnc.ordersystem.entity.Food;
import com.aolei.jxustnc.ordersystem.util.DividerItemDecoration;
import com.aolei.jxustnc.ordersystem.util.HttpUtils;
import com.bumptech.glide.Glide;

import java.util.List;

import cn.bmob.v3.listener.FindListener;

public class FoodDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsing_layout;
    private RecyclerView food_detail_recyclerview;
    private TextView tv_price_item_food, tv_shop_item_food, tv_canteen_item_food, tv_sold_item_food;
    private ImageView img_food;
    private String foodId;
    private List<Comment> data_list;
    private RecyclerViewCommonAdapter<Comment> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        initView();
        initEvent();
    }

    private void initEvent() {

    }

    private void initView() {
        tv_price_item_food = (TextView) findViewById(R.id.tv_price_item_food);
        tv_shop_item_food = (TextView) findViewById(R.id.tv_shop_item_food);
        tv_canteen_item_food = (TextView) findViewById(R.id.tv_canteen_item_food);
        tv_sold_item_food = (TextView) findViewById(R.id.tv_sold_item_food);
        img_food = (ImageView) findViewById(R.id.img_food);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsing_layout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsing_layout.setTitle("北京烤鸭");
        //setRecyclerView();
        setData();

    }

    /**
     * 设置值
     */
    private void setData() {
        Bundle bundle = getIntent().getExtras();
        Food food = (Food) bundle.getSerializable("list");
        tv_price_item_food.setText(" ￥" + food.getPrice());
        tv_shop_item_food.setText(food.getStore().getStore_name());
        tv_canteen_item_food.setText("江西理工大学" + food.getStore().getBelong_cateen());
        tv_sold_item_food.setText("已售" + food.getSold_count());
        collapsing_layout.setTitle(food.getName());
        foodId = food.getObjectId();
        Glide.with(this).load(food.getImg_url()).error(R.drawable.imgloadfiald).into(img_food);
        getNetData();
    }

    /**
     * 获取网络数据
     */
    private void getNetData() {
        HttpUtils.queryFoodCommment(this, foodId, new FindListener<Comment>() {
            @Override
            public void onSuccess(List<Comment> list) {
                data_list = list;
                setRecyclerView();
            }

            @Override
            public void onError(int i, String s) {
                Toast.makeText(FoodDetailActivity.this, "获取失败:" + s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 设置评论RecyclerView
     */
    private void setRecyclerView() {
        if (data_list.size() != 0 && data_list != null) {
            food_detail_recyclerview = (RecyclerView) findViewById(R.id.food_detail_recyclerview);
            food_detail_recyclerview.setFocusable(false);
            mAdapter = new RecyclerViewCommonAdapter<Comment>(this, R.layout.item_food_comment, data_list) {
                @Override
                public void convert(RecyclerViewViewHolder holder, Comment comment) {
                    holder.setTextView(R.id.tv_comment_name, comment.getUser().getUsername());
                    holder.setTextView(R.id.tv_comment_time, comment.getCreatedAt());
                    holder.setTextView(R.id.tv_comment_content, comment.getContent());
                }
            };
            food_detail_recyclerview.setAdapter(mAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            food_detail_recyclerview.setLayoutManager(linearLayoutManager);
            food_detail_recyclerview.setNestedScrollingEnabled(false);
            food_detail_recyclerview.setItemAnimator(new DefaultItemAnimator());
            food_detail_recyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
            mAdapter.setmOnItemClickListener(new RecyclerViewCommonAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                }
            });
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
