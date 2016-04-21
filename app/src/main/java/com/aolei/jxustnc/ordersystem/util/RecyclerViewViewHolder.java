package com.aolei.jxustnc.ordersystem.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * RecyclerView通用ViewHolder
 * Created by NewOr on 2016/4/19.
 */
public class RecyclerViewViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;
    private View mConvertView;
    private Context mContext;
    private int mPosition;
    private int mLayoutId;

    /**
     * 构造方法
     *
     * @param context
     * @param itemView
     * @param parent
     */
    public RecyclerViewViewHolder(Context context, View itemView, ViewGroup parent) {
        super(itemView);
        mConvertView = itemView;
        mContext = context;
        mViews = new SparseArray<>();
    }

    public static RecyclerViewViewHolder get(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        RecyclerViewViewHolder holder = new RecyclerViewViewHolder(context, itemView, parent);
        return holder;
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId)
    {
        View view = mViews.get(viewId);
        if (view == null)
        {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }
}
