package com.aolei.jxustnc.ordersystem.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/** 设置瀑布流每个item的间距的类
 * Created by aolei on 2016/4/17.
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration{
    private int space;
    public SpacesItemDecoration(int space){
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space * 2;
        outRect.bottom = space;
        if (parent.getChildAdapterPosition(view) == 0){
            //outRect.top = space;
        }
    }
}
