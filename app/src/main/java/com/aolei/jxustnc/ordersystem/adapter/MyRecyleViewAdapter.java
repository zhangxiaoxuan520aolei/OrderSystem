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
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aolei on 2016/4/17.
 */
public class MyRecyleViewAdapter extends RecyclerView.Adapter<MyRecyleViewAdapter.MyViewHolder>{
    private List<Store> lists;
    private Context context;
    private List<Integer> heights;

    public MyRecyleViewAdapter(Context context, List<Store> list){
        this.context = context;
        this.lists = list;

        getRandoomHeight(this.lists);
    }
    private void getRandoomHeight(List<Store> lists){
        heights = new ArrayList<>();
        //Log.d("sizse",lists.size()+"");
        for (int i = 0; i < lists.size();i++){
            heights.add(400);
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
        //绑定数据 暂时没有数据
        holder.mStore_dec.setText(lists.get(position).getStore_des());
        holder.mStore_name.setText(lists.get(position).getStore_name());
        Glide.with(context).load(lists.get(position).getStore_pic()).into(holder.mStore_pic);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mStore_dec;
        TextView mStore_name;
        ImageView mStore_pic;
        public MyViewHolder(View itemView) {
            super(itemView);
            mStore_dec = (TextView) itemView.findViewById(R.id.store_dec);
            mStore_name = (TextView)itemView.findViewById(R.id.store_name);
            mStore_pic = (ImageView)itemView.findViewById(R.id.store_pic);
        }
    }

}
