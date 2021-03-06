package com.aolei.jxustnc.ordersystem.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListViewViewHolder {

	private SparseArray<View> mViews;
	private int mPostion;
	private View mConvertView;

	public ListViewViewHolder(Context context, ViewGroup parent, int layoutId,
							  int position) {
		this.mPostion = position;
		this.mViews = new SparseArray<View>();
		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
				false);
		mConvertView.setTag(this);
	}

	// 入口方法，判断ViewHolder是new出来的，还是getTag出来的
	public static ListViewViewHolder get(Context context, View convertView,
										 ViewGroup parent, int layoutId, int position) {
		if (convertView == null) {
			return new ListViewViewHolder(context, parent, layoutId, position);
		} else {
			ListViewViewHolder holder = (ListViewViewHolder) convertView.getTag();
			holder.mPostion = position;
			return holder;
		}
	}

	// 使用泛型T，返回值是View的子类
	/**
	 * 通过viewId获取控件
	 * 
	 * @param viewId
	 * @return
	 */
	public <T extends View> T getView(int viewId) {
		View view = mViews.get(viewId);
		if (view == null) {
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T) view;
	}
	public View getConvertView() {
		return mConvertView;
	}
/**
 * 设置TextView的值
 * @param viewId
 * @param text
 * @return
 */
	public ListViewViewHolder setText(int viewId, String text) {
		TextView tv = getView(viewId);
		tv.setText(text);
		return this;
	}
}