package com.aolei.jxustnc.ordersystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.adapter.RecyclerViewCommonAdapter;
import com.aolei.jxustnc.ordersystem.adapter.RecyclerViewViewHolder;
import com.aolei.jxustnc.ordersystem.entity.Food;
import com.aolei.jxustnc.ordersystem.entity.Store;
import com.aolei.jxustnc.ordersystem.util.CheckUtils;
import com.aolei.jxustnc.ordersystem.util.HttpUtils;
import com.aolei.jxustnc.ordersystem.util.NetworkUtil;
import com.bumptech.glide.Glide;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class ShowFoodActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private Toolbar mToolbar;
    private Store store;
    private RecyclerView mRecyclerView;
    private List<Food> mFoodList;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerViewCommonAdapter<Food> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bmob.initialize(this, CheckUtils.APPID);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_food);
        initView();
        Intent intent = this.getIntent();
        store = (Store) intent.getSerializableExtra("store_info");
        mToolbar.setTitle(store.getStore_name());
        getNetDate();


    }

    void initView() {
        mToolbar = (Toolbar) findViewById(R.id.store_title);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.ShowFood);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.showfood_recyclerview);
    }

    @Override
    public void onRefresh() {
        getNetDate();
    }

    /**
     * 获取网络数据
     */
    private void getNetDate() {
        if (NetworkUtil.isConnected(getApplicationContext())) {
            BmobQuery<Food> query = new BmobQuery<>();
            HttpUtils.queryFoodByStoreName(getApplicationContext(), query, new FindListener<Food>() {
                @Override
                public void onSuccess(List<Food> list) {
                    mFoodList = list;
                    setRecyclerViewDate();
                }

                @Override
                public void onError(int i, String s) {
                    Log.d("error", s);
                }
            }, store.getObjectId());
        } else {
            Snackbar.make(mRecyclerView, "网络连接出错", Snackbar.LENGTH_SHORT).show();
            mSwipeRefreshLayout.setRefreshing(false);
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void setRecyclerViewDate() {
        if (!mFoodList.isEmpty()) {
            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            mAdapter = new RecyclerViewCommonAdapter<Food>(this, R.layout.item_home_recyclerview, mFoodList) {
                @Override
                public void convert(RecyclerViewViewHolder holder, Food food) {
                    holder.setTextView(R.id.tv_shop_item_home, food.getStore().getStore_name());
                    holder.setTextView(R.id.tv_canteen_item_home, food.getStore().getBelong_cateen());
                    holder.setTextView(R.id.tv_name_item_home, food.getName());
                    holder.setTextView(R.id.tv_price_item_home, "￥" + food.getPrice());
                    holder.setTextView(R.id.tv_count_item_home, "已售" + food.getSold_count() + "份");
                    ImageView img_item_home = holder.getView(R.id.img_item_home);
                    Glide.with(getApplicationContext()).load(food.getImg_url()).error(R.drawable.imgloadfiald).into(img_item_home);//设置图片
                }
            };
            mRecyclerView.setAdapter(mAdapter);
            /**
             * 每个Item 的点击事件，跳转到FoodDetaili界面
             */
            mAdapter.setmOnItemClickListener(new RecyclerViewCommonAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("list", mFoodList.get(position));
                    Intent intent = new Intent(getApplicationContext(), FoodDetailActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        } else {
            Log.d("error", "没有查询到数据");
        }
    }

}
