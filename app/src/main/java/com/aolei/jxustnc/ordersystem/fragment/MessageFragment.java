package com.aolei.jxustnc.ordersystem.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.adapter.RecyclerViewCommonAdapter;
import com.aolei.jxustnc.ordersystem.adapter.RecyclerViewViewHolder;
import com.aolei.jxustnc.ordersystem.entity.Order;
import com.aolei.jxustnc.ordersystem.util.HttpUtils;
import com.aolei.jxustnc.ordersystem.util.ToastUtil;
import com.aolei.jxustnc.ordersystem.util.Utils;

import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 * 商家接收消息界面
 */
public class MessageFragment extends Fragment {

    private View view;
    private RecyclerView recyclerview_msg;
    private List<Order> data_list;
    private RecyclerViewCommonAdapter<Order> mAdapter;
    private SwipeRefreshLayout msg_swipe_layout;

    public MessageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_message, container, false);
        recyclerview_msg = (RecyclerView) view.findViewById(R.id.recyclerview_msg);
        msg_swipe_layout = (SwipeRefreshLayout) view.findViewById(R.id.msg_swipe_layout);
        Utils.showProgressDialog(getActivity());
        getNetData();
        initEvent();
        return view;
    }

    private void initEvent() {
        msg_swipe_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNetData();
            }
        });
    }

    /**
     * 从服务器获取数据
     */
    private void getNetData() {
        HttpUtils httpUtils = new HttpUtils(getActivity());
        httpUtils.queryUserMsg(BmobUser.getCurrentUser(getActivity()).getObjectId(), new FindListener<Order>() {
            @Override
            public void onSuccess(List<Order> list) {
                data_list = list;
                showRecyclerView();
            }

            @Override
            public void onError(int i, String s) {
                ToastUtil.showShort(getActivity(), "查询失败" + s);
                Log.d("MessageFragment", "查询订单失败" + s);
                Utils.closeProgressDialog();
            }
        });
    }

    /**
     * 设置RecyclerView
     */
    private void showRecyclerView() {
        if (data_list != null && data_list.size() != 0) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            recyclerview_msg.setLayoutManager(linearLayoutManager);
            mAdapter = new RecyclerViewCommonAdapter<Order>(getActivity(), R.layout.item_msg_recyclerview, data_list) {
                @Override
                public void convert(RecyclerViewViewHolder holder, Order order) {
                    holder.setTextView(R.id.tv_msg_name, order.getFood().getName());
                    holder.setTextView(R.id.tv_msg_tel, order.getUser().getMobilePhoneNumber());
                    if (order.getDeal()) {
                        holder.setTextView(R.id.tv_msg_status, "已完成");
                    } else {
                        holder.setTextView(R.id.tv_msg_status, "未完成");
                    }
                    holder.setTextView(R.id.tv_msg_dorm, order.getDorm());
                    holder.setTextView(R.id.tv_msg_time, order.getCreatedAt());
                }
            };
            recyclerview_msg.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener(new RecyclerViewCommonAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                }
            });
        }
        msg_swipe_layout.setRefreshing(false);
        Utils.closeProgressDialog();
    }
}
