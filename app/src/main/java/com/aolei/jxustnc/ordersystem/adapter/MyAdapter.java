package com.aolei.jxustnc.ordersystem.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.entity.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aolei on 2016/4/15.
 */
public class MyAdapter extends BaseAdapter{
    private List<Food> mList = new ArrayList<>();
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public MyAdapter(Context context,List<Food> list){
        this.mList = list;
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Food getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View view;
        Food food = getItem(position);
        if (convertView == null){
            view = mLayoutInflater.inflate(R.layout.listviewitem,null);
            viewHolder = new ViewHolder();
            viewHolder.foodImageView = (ImageView)view.findViewById(R.id.food_image);
            viewHolder.foodDescribe = (TextView)view.findViewById(R.id.food_describe);
            viewHolder.foodName = (TextView)view.findViewById(R.id.food_name);
            viewHolder.foodCanteen = (TextView)view.findViewById(R.id.food_canteen);
            viewHolder.foodPrice = (TextView)view.findViewById(R.id.food_price);
            viewHolder.foodSaleNum = (TextView)view.findViewById(R.id.food_sale_num);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
            viewHolder.foodImageView.setImageResource(R.drawable.icon_email);
            viewHolder.foodSaleNum.setText(food.getFoodSaleNum());
            viewHolder.foodCanteen.setText(food.getFoodCanteen());
            viewHolder.foodPrice.setText(food.getFoodPrice());
            viewHolder.foodDescribe.setText(food.getFoodDescribe());
            viewHolder.foodName.setText(food.getFoodName());
        }
        return view;
    }
    class ViewHolder{
        ImageView foodImageView;
        TextView foodName;
        TextView foodDescribe;
        TextView foodPrice;
        TextView foodSaleNum;
        TextView foodCanteen;

    }
}
