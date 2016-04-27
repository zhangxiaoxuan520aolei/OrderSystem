package com.aolei.jxustnc.ordersystem.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aolei.jxustnc.ordersystem.R;

/**
 * 已完成订单Fragment
 * Created by NewOr on 2016/4/25.
 */
public class DoneFragment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view = inflater.inflate(R.layout.fragment_order, container, false);
    }
}
