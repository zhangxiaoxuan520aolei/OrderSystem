package com.aolei.jxustnc.ordersystem.util;

import android.view.View;

import com.aolei.jxustnc.ordersystem.entity.Store;

/**
 * RecyclerViewItemClickListener 监听接口
 * 作者：aolei on 2016/4/23 16:55
 * 邮箱：807648567@qq.com
 * 解释权：敖磊
 */
public interface OnRecyclerViewItemClickListener {
    void onItemClick(View view, Store store);
}
