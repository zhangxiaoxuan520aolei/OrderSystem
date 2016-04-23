package com.aolei.jxustnc.ordersystem.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.entity.Store;
import com.aolei.jxustnc.ordersystem.util.OnRecyclerViewItemClickListener;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * 店铺展示界面的适配器
 * Created by aolei on 2016/4/17.
 */
public class MyRecyleViewAdapter extends RecyclerView.Adapter<MyRecyleViewAdapter.MyViewHolder> implements View.OnClickListener {
    private List<Store> lists;
    private Context context;
    private List<Integer> heights;
    private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener = null;


    public MyRecyleViewAdapter(Context context, List<Store> list) {
        this.context = context;
        this.lists = list;
        getHeight(this.lists);
    }

    //设置Item的高度
    private void getHeight(List<Store> lists) {
        heights = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            heights.add(400);
        }
    }

    @Override
    public MyRecyleViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.store_show, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyRecyleViewAdapter.MyViewHolder holder, int position) {
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();//得到Item的layoutParams布局参数
        params.height = heights.get(position);
        holder.itemView.setLayoutParams(params);
        //设置Tag，将当前的Item的数据设置到Tag
        holder.itemView.setTag(lists.get(position));
        //绑定数据 暂时没有数据
        holder.mStore_dec.setText(lists.get(position).getStore_des());
        holder.mStore_name.setText(lists.get(position).getStore_name());
        Glide.with(context).load(lists.get(position).getStore_pic()).skipMemoryCache(true).crossFade().centerCrop().into(holder.mStore_pic);

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    //Item的点击监听事件
    @Override
    public void onClick(View v) {
        if (mOnRecyclerViewItemClickListener != null) {
            mOnRecyclerViewItemClickListener.onItemClick(v, ((Store) v.getTag()));
        }
    }
    //Item的点击监听事件
    public void setOnItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.mOnRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mStore_dec;
        TextView mStore_name;
        ImageView mStore_pic;

        public MyViewHolder(View itemView) {
            super(itemView);
            mStore_dec = (TextView) itemView.findViewById(R.id.store_dec);
            mStore_name = (TextView) itemView.findViewById(R.id.store_name);
            mStore_pic = (ImageView) itemView.findViewById(R.id.store_pic);
        }
    }

}
