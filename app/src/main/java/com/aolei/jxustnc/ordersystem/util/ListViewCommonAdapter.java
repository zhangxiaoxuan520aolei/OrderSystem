package com.aolei.jxustnc.ordersystem.util;

/**
 * ListView万能适配器
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class ListViewCommonAdapter<T> extends BaseAdapter {

    public Context mContext;
    public List<T> mDatas;
    public LayoutInflater mInflater;
    private int mLayoutId;

    public ListViewCommonAdapter(Context context, List<T> datas, int layoutId) {
        this.mContext = context;
        this.mDatas = datas;
        this.mLayoutId = layoutId;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return mDatas.size();
    }

    public T getItem(int arg0) {
        return mDatas.get(arg0);
    }

    public long getItemId(int arg0) {
        return arg0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ListViewViewHolder holder = ListViewViewHolder.get(mContext, convertView, parent,
                mLayoutId, position);
        convert(holder, getItem(position));

        return holder.getConvertView();
    }

    public abstract void convert(ListViewViewHolder holder, T t);
}