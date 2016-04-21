package com.aolei.jxustnc.ordersystem.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.activity.FoodDetailActivity;
import com.aolei.jxustnc.ordersystem.entity.FoodEntity;
import com.aolei.jxustnc.ordersystem.util.ListViewCommonAdapter;
import com.aolei.jxustnc.ordersystem.util.ListViewViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private List<FoodEntity> data_list;
    private View view;
    private ListView listView_home;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return view = inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        listView_home = (ListView) view.findViewById(R.id.listview_home_fg);
        data_list = new ArrayList<>();
        FoodEntity foodEntity;
        for (int i = 0; i < 20; i++) {
            foodEntity = new FoodEntity();
            foodEntity.setDetail("很好吃的北京烤鸭");
            data_list.add(foodEntity);
        }
        listView_home.setAdapter(new ListViewCommonAdapter<FoodEntity>(getActivity(), data_list, R.layout.item_home_listview) {
            @Override
            public void convert(ListViewViewHolder holder, FoodEntity foodEntity) {
                holder.setText(R.id.tv_detail_item_home, foodEntity.getDetail());
            }
        });
        listView_home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), FoodDetailActivity.class));
            }
        });
    }
}
