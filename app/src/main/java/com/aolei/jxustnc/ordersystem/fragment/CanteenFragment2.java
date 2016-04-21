package com.aolei.jxustnc.ordersystem.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.adapter.MyRecyleViewAdapter;
import com.aolei.jxustnc.ordersystem.entity.Store;
import com.aolei.jxustnc.ordersystem.util.SpacesItemDecoration;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * 第二食堂Fragment
 * Created by NewOr on 2016/4/10.
 */
public class CanteenFragment2 extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
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
        super.onActivityCreated(savedInstanceState);
        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.CanteenFragment);
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
    private void getNetData(){
        BmobQuery<Store> storeBmobQuery = new BmobQuery<>();
        storeBmobQuery.addWhereEqualTo("belong_cateen", "第二食堂");
        storeBmobQuery.order("objectId");
        storeBmobQuery.findObjects(getContext(), new FindListener<Store>() {
            @Override
            public void onSuccess(List<Store> list) {
                lists = list;
                setNetData();
            }

            @Override
            public void onError(int i, String s) {
                Log.d("Error", "数据不存在");
            }
        });

    }
    /**
     * 将获取到的网络数据设置到Item中
     */
    private void setNetData() {
        myRecyleViewAdapter = new MyRecyleViewAdapter(getContext(), lists);
        mRecyleView.setAdapter(myRecyleViewAdapter);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        getNetData();
    }
}
