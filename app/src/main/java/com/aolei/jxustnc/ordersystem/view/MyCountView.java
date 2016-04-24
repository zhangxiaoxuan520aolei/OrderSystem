package com.aolei.jxustnc.ordersystem.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aolei.jxustnc.ordersystem.R;

/**
 * 自定义订单数量View
 * Created by NewOr on 2016/4/24.
 */
public class MyCountView extends RelativeLayout {

    private String minusText, countText, plusText;
    private int minusTextColor, countTextColor, plusTextColor;
    private float minusTextSize, countTextSize, plusTextSize;
    private TextView tvMinus, tvCount, tvPlus;
    private countClickListener countClickListener;
    //将控件放到ViewGroup中
    private LayoutParams minusParams, countParams, plusParams;

    //点击事件接口
    public interface countClickListener {
        public void minusClick();

        public void plusClick();
    }

    public void setOnCountClickListener(countClickListener listener) {
        this.countClickListener = listener;
    }

    public MyCountView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        /**
         * 获取自定义属性
         */
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyCountView);

        minusText = ta.getString(R.styleable.MyCountView_minusText);
        minusTextColor = ta.getColor(R.styleable.MyCountView_minusTextColor, 0);
        minusTextSize = ta.getDimension(R.styleable.MyCountView_minusTextSize, 10);

        countText = ta.getString(R.styleable.MyCountView_countText);
        countTextColor = ta.getColor(R.styleable.MyCountView_countTextColor, 0);
        countTextSize = ta.getDimension(R.styleable.MyCountView_countTextSize, 10);

        plusText = ta.getString(R.styleable.MyCountView_plusText);
        plusTextColor = ta.getColor(R.styleable.MyCountView_plusTextColor, 0);
        plusTextSize = ta.getDimension(R.styleable.MyCountView_plusTextSize, 10);
        ta.recycle();

        tvMinus = new TextView(context);
        tvCount = new TextView(context);
        tvPlus = new TextView(context);
        /**
         * 将自定义属性赋值给控件
         */
        tvMinus.setText(minusText);
        tvMinus.setTextColor(minusTextColor);
        tvMinus.setTextSize(minusTextSize);

        tvCount.setText(countText);
        tvCount.setTextColor(countTextColor);
        tvCount.setTextSize(countTextSize);
        tvCount.setGravity(Gravity.CENTER);

        tvPlus.setText(plusText);
        tvPlus.setTextColor(plusTextColor);
        tvPlus.setTextSize(plusTextSize);

        /**
         * 定义控件的布局属性
         */
        minusParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        minusParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);//左对齐
        minusParams.addRule(RelativeLayout.CENTER_VERTICAL);//垂直居中
        addView(tvMinus, minusParams);

        countParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        countParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(tvCount, countParams);

        plusParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        plusParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);//右对齐
        plusParams.addRule(RelativeLayout.CENTER_VERTICAL);//垂直居中
        addView(tvPlus, plusParams);

        /**
         * 设置监听事件
         */
        tvMinus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                countClickListener.minusClick();
            }
        });
        tvPlus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                countClickListener.plusClick();
            }
        });
    }
}
