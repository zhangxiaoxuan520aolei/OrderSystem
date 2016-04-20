package com.aolei.jxustnc.ordersystem.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.utils.SpacesItemDecoration;
import com.aolei.jxustnc.ordersystem.adapter.MyRecyleViewAdapter;
import com.aolei.jxustnc.ordersystem.entity.Store;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;

/**
 * 第一食堂Fragment
 * Created by NewOr on 2016/4/10.
 */
public class CanteenFragment1 extends Fragment{
    private RecyclerView mRecyleView;
    private MyRecyleViewAdapter myRecyleViewAdapter;
    private List<Store> lists;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view =  inflater.inflate(R.layout.fragment_canteen, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        mRecyleView = (RecyclerView) view.findViewById(R.id.show_list);
        mRecyleView.setItemAnimator(new DefaultItemAnimator());
        //设置RecyleView布局管理器为2列垂直布局
        mRecyleView.setLayoutManager(new GridLayoutManager(getContext(),2));
        myRecyleViewAdapter = new MyRecyleViewAdapter(getContext(),lists);
        mRecyleView.setAdapter(myRecyleViewAdapter);

        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        mRecyleView.addItemDecoration(decoration);
    }
    //初始化数据
    private void initData(){
        String bql = "select * from Store";
        BmobQuery<Store> queryResult = new BmobQuery<Store>();
        queryResult.setSQL(bql);
        queryResult.doSQLQuery(getContext(), new SQLQueryListener<Store>() {

            @Override
            public void done(BmobQueryResult<Store> bmobQueryResult, BmobException e) {
                if (e == null){
                    List<Store> listResult = bmobQueryResult.getResults();
                    if (!listResult.isEmpty() && listResult.size() > 0){
                        for (int i = 0; i < listResult.size(); i++){
                            Store store = new Store();
                            store.setStore_pic(listResult.get(i).getStore_pic());
                            store.setStore_des(listResult.get(i).getStore_des());
                            lists.add(store);
                        }
                    }
                }else{
                    Log.d("Error",e+"");
                }
            }
        });
    }
}
