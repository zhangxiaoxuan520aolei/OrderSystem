package com.aolei.jxustnc.ordersystem.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.adapter.RecyclerViewCommonAdapter;
import com.aolei.jxustnc.ordersystem.adapter.RecyclerViewViewHolder;
import com.aolei.jxustnc.ordersystem.entity.Food;
import com.aolei.jxustnc.ordersystem.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class SubmitOrderActivity extends AppCompatActivity {

    private int count = 1;
    private Toolbar submit_toolbar;
    private List<Order> data_list;
    private RecyclerViewCommonAdapter<Order> mAdapter;
    private RecyclerView submit_recyclerview;
    private int money;
    private Button btn_submit_order;
    private String total_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_order);
        initView();
        initEvent();
    }

    /**
     * 从上一个Activity获取数据
     */
    private void getIntentData() {
        Bundle bundle = getIntent().getExtras();
        Order order = new Order();
        Food food = (Food) bundle.getSerializable("order");
        order.setFood(food);
        order.setMoney(food.getPrice());
        order.setCount(1);
        data_list.add(order);
    }

    private void initEvent() {
        mAdapter.setOnItemClickListener(new RecyclerViewCommonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            }
        });

    }

    private void initView() {
        btn_submit_order = (Button) findViewById(R.id.btn_submit_order);
        submit_toolbar = (Toolbar) findViewById(R.id.submit_toolbar);
        submit_recyclerview = (RecyclerView) findViewById(R.id.submit_recyclerview);
        setSupportActionBar(submit_toolbar);
        submit_toolbar.setTitle("提交订单");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        data_list = new ArrayList<>();
        getIntentData();
        setRecyclerView();
    }

    /**
     * 设置RecyclerView
     */
    private void setRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        if (data_list != null && data_list.size() != 0) {
            mAdapter = new RecyclerViewCommonAdapter<Order>(SubmitOrderActivity.this, R.layout.item_submit_recyclerview, data_list) {
                @Override
                public void convert(RecyclerViewViewHolder holder, final Order order) {
                    /**
                     * 绑定数据
                     */
                    holder.setTextView(R.id.tv_food_count_submit, order.getCount() + "");
                    holder.setTextView(R.id.tv_money_item_submit, order.getMoney());
                    holder.setTextView(R.id.tv_shop_item_submit, order.getFood().getStore().getStore_name());
                    holder.setTextView(R.id.tv_canteen_item_submit, order.getFood().getStore().getBelong_cateen());
                    holder.setTextView(R.id.tv_food_item_submit, order.getFood().getName());
                    /**
                     * 设置点击事件
                     */
                    /**
                     * 减
                     */
                    holder.getView(R.id.tv_minus_item_submit).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count = order.getCount();
                            if (count > 1) {
                                money = Integer.parseInt(order.getMoney());
                                count--;
                                order.setCount(count);
                                money = money - Integer.parseInt(order.getFood().getPrice());
                                order.setMoney(money + "");
                                setTotalPrice(data_list);
                                mAdapter.notifyDataSetChanged();
                            }
                        }
                    });
                    /**
                     * 加
                     */
                    holder.getView(R.id.tv_plus_item_submit).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            money = Integer.parseInt(order.getMoney());
                            count = order.getCount();
                            count++;
                            order.setCount(count);
                            money = money + Integer.parseInt(order.getFood().getPrice());
                            order.setMoney(money + "");
                            setTotalPrice(data_list);
                            mAdapter.notifyDataSetChanged();
                        }
                    });
                }
            };

            submit_recyclerview.setLayoutManager(linearLayoutManager);
            submit_recyclerview.setAdapter(mAdapter);
            //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
            submit_recyclerview.hasFixedSize();
//            submit_recyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        }
        setTotalPrice(data_list);
    }

    /**
     * 设置总价
     *
     * @param list
     */
    private void setTotalPrice(List<Order> list) {
        if (list.size() != 0 && list != null) {
            int price = 0;
            for (int i = 0; i < list.size(); i++) {
                price = price + Integer.parseInt(list.get(i).getMoney());
            }
            total_price = price + "";
            btn_submit_order.setText("提交订单(￥" + total_price + ")");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
