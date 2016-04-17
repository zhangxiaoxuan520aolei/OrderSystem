package com.aolei.jxustnc.ordersystem.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aolei.jxustnc.ordersystem.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aolei on 2016/4/17.
 */
public class MyRecyleViewAdapter extends RecyclerView.Adapter<MyRecyleViewAdapter.MyViewHolder>{
    private List<String> lists;
    private Context context;
    private List<Integer> heights;

    public MyRecyleViewAdapter(Context context, List<String> list){
        this.context = context;
        this.lists = list;
        getRandoomHeight(this.lists);
    }
    private void getRandoomHeight(List<String> lists){
        heights = new ArrayList<>();
        for (int i = 0; i < lists.size();i++){
            //heights.add((int)(200 + Math.random() * 400));
            heights.add(200);
        }
    }
    @Override
    public MyRecyleViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.store_show,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(MyRecyleViewAdapter.MyViewHolder holder, int position) {
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();//得到Item的layoutParams布局参数
        params.height = heights.get(position);
        holder.itemView.setLayoutParams(params);
        //绑定数据
        holder.mTv.setText(lists.get(position));

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mTv;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTv = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
