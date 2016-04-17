package com.aolei.jxustnc.ordersystem.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.Utils.SpacesItemDecoration;
import com.aolei.jxustnc.ordersystem.adapter.MyAdapter;
import com.aolei.jxustnc.ordersystem.adapter.MyRecyleViewAdapter;
import com.aolei.jxustnc.ordersystem.entity.Food;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 第一食堂Fragment
 * Created by NewOr on 2016/4/10.
 */
public class CanteenFragment1 extends Fragment{
    private RecyclerView mRecyleView;
    private MyRecyleViewAdapter myRecyleViewAdapter;
    private List<String> lists;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view =  inflater.inflate(R.layout.fragment_canteen, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //initView();
        initData();
        mRecyleView = (RecyclerView) view.findViewById(R.id.show_list);
        mRecyleView.setItemAnimator(new DefaultItemAnimator());
        //设置RecyleView布局管理器为2列垂直布局
        //mRecyleView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyleView.setLayoutManager(new GridLayoutManager(getContext(),2));
        myRecyleViewAdapter = new MyRecyleViewAdapter(getContext(),lists);
        mRecyleView.setAdapter(myRecyleViewAdapter);

        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        mRecyleView.addItemDecoration(decoration);
    }
    private void initData(){
        lists = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            lists.add(""+i);
        }
    }
}
