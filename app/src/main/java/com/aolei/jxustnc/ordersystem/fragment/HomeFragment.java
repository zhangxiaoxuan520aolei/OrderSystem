package com.aolei.jxustnc.ordersystem.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.activity.FoodDetailActivity;
import com.aolei.jxustnc.ordersystem.entity.Food;
import com.aolei.jxustnc.ordersystem.util.HttpUtils;
import com.aolei.jxustnc.ordersystem.util.NetworkUtil;
import com.aolei.jxustnc.ordersystem.adapter.RecyclerViewCommonAdapter;
import com.aolei.jxustnc.ordersystem.adapter.RecyclerViewViewHolder;
import com.aolei.jxustnc.ordersystem.util.ToastUtil;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private List<Food> data_list;
    private View view;
    private RecyclerView home_recyclerview;
    private SwipeRefreshLayout home_swipe_layout;
    private RecyclerViewCommonAdapter<Food> mAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return view = inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initEvent();
    }

    private void initEvent() {
        home_swipe_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNetData();
            }
        });
    }

    private void initView() {
        home_recyclerview = (RecyclerView) view.findViewById(R.id.home_recyclerview);
        home_swipe_layout = (SwipeRefreshLayout) view.findViewById(R.id.home_swipe_layout);
        home_swipe_layout.post(new Runnable() {
            @Override
            public void run() {
                home_swipe_layout.setRefreshing(true);
            }
        });
        getNetData();
    }

    /**
     * 获取网络数据
     */
    private void getNetData() {
        if (NetworkUtil.isConnected(getActivity())) {
            data_list = new ArrayList<>();
            BmobQuery<Food> query = new BmobQuery<>();
            HttpUtils.queryFood(getActivity(), query, new FindListener<Food>() {
                @Override
                public void onSuccess(List<Food> list) {
                    data_list = list;
                    setRecyclerView();
                }

                @Override
                public void onError(int i, String s) {
                    Toast.makeText(getActivity(), "获取失败:" + s, Toast.LENGTH_SHORT).show();
                    home_swipe_layout.setRefreshing(false);
                }
            });
        } else {
            ToastUtil.showLong(getActivity(), "网络尚未连接");
            home_swipe_layout.post(new Runnable() {
                @Override
                public void run() {
                    home_swipe_layout.setRefreshing(false);
                }
            });
        }
    }

    /**
     * 设置RecyclerView
     */
    private void setRecyclerView() {
        if (data_list.size() != 0 && data_list != null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            home_recyclerview.setLayoutManager(linearLayoutManager);
            mAdapter = new RecyclerViewCommonAdapter<Food>(getActivity(), R.layout.item_home_recyclerview, data_list) {
                @Override
                public void convert(RecyclerViewViewHolder holder, Food food) {
                    holder.setTextView(R.id.tv_shop_item_home, food.getStore().getStore_name());
                    holder.setTextView(R.id.tv_canteen_item_home, food.getStore().getBelong_cateen());
                    holder.setTextView(R.id.tv_name_item_home, food.getName());
                    holder.setTextView(R.id.tv_price_item_home, "￥" + food.getPrice());
                    holder.setTextView(R.id.tv_count_item_home, "已售" + food.getSold_count() + "份");
                    ImageView img_item_home = holder.getView(R.id.img_item_home);
                    Glide.with(getActivity()).load(food.getImg_url()).error(R.drawable.imgloadfiald).into(img_item_home);//设置图片
                }
            };
            home_recyclerview.setAdapter(mAdapter);
        } else {
            Toast.makeText(getActivity(), "亲，这家店还没有准备好吃的供您享用哦，去别家看看吧", Toast.LENGTH_SHORT).show();
        }
        home_swipe_layout.post(new Runnable() {
            @Override
            public void run() {
                home_swipe_layout.setRefreshing(false);
            }
        });
        mAdapter.setOnItemClickListener(new RecyclerViewCommonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("list", data_list.get(position));
                Intent intent = new Intent(getActivity(), FoodDetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
