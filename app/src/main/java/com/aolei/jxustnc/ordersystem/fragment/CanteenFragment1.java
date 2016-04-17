package com.aolei.jxustnc.ordersystem.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.adapter.MyAdapter;
import com.aolei.jxustnc.ordersystem.entity.Food;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 第一食堂Fragment
 * Created by NewOr on 2016/4/10.
 */
public class CanteenFragment1 extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_canteen, container, false);
    }


}
