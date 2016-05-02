package com.aolei.jxustnc.ordersystem.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.activity.ShowFoodActivity;
import com.aolei.jxustnc.ordersystem.adapter.MyRecyleViewAdapter;
import com.aolei.jxustnc.ordersystem.entity.Store;
import com.aolei.jxustnc.ordersystem.util.OnRecyclerViewItemClickListener;
import com.aolei.jxustnc.ordersystem.util.SpacesItemDecoration;
import com.aolei.jxustnc.ordersystem.util.*;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * 第一食堂Fragment
 * Created by NewOr on 2016/4/10.
 */
public class CanteenFragment1 extends Fragment implements SwipeRefreshLayout.OnRefreshListener, OnRecyclerViewItemClickListener {
    private RecyclerView mRecyleView;
    private MyRecyleViewAdapter myRecyleViewAdapter;
    private List<Store> lists;
    private SwipeRefreshLayout mSwipeRefreshLayout;
     View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_canteen, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Bmob.initialize(getContext(), CheckUtils.APPID);
        super.onActivityCreated(savedInstanceState);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.CanteenFragment);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyleView = (RecyclerView) view.findViewById(R.id.show_list);
        //设置RecyleView布局管理器为2列垂直布局
        mRecyleView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        //设置每个Item之间的距离
        SpacesItemDecoration decoration = new SpacesItemDecoration(5);
        mRecyleView.addItemDecoration(decoration);
        getNetData();

    }

    /**
     * 请求网络后台数据
     */
    private void getNetData() {
        if (NetworkUtil.isConnected(getContext())){
            BmobQuery<Store> storeBmobQuery = new BmobQuery<>();
            storeBmobQuery.addWhereEqualTo("belong_cateen", "第一食堂");
            storeBmobQuery.order("objectId");
            storeBmobQuery.findObjects(getContext(), new FindListener<Store>() {
                @Override
                public void onSuccess(List<Store> list) {
                    lists = list;
                    setNetData();
                }

                @Override
                public void onError(int i, String s) {
                    Log.d("Error", "数据不存在"+s);
                }
            });
        }else{
            Snackbar.make(getView(),"网络未连接",Snackbar.LENGTH_SHORT).show();
        }


    }

    /**
     * 将获取到的网络数据设置到Item中
     */
    private void setNetData() {
        myRecyleViewAdapter = new MyRecyleViewAdapter(getContext(), lists);
        mRecyleView.setAdapter(myRecyleViewAdapter);
        //Item点击监听事件
        myRecyleViewAdapter.setOnItemClickListener(this);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        getNetData();
    }

    /**
     * Item点击监听事件
     *将获取到的Item的店家信息传入到FoodDetailActivity中
     * @param view
     * @param store
     */
    @Override
    public void onItemClick(View view, Store store) {
        Intent mIntent = new Intent(getContext(), ShowFoodActivity.class);
        mIntent.putExtra("store_info",store);
        startActivity(mIntent);
    }
}