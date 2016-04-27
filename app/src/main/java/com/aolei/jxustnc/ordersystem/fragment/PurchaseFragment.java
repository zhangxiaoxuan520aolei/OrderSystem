package com.aolei.jxustnc.ordersystem.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.view.ColorTrackView;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的购买Fragment
 */

public class PurchaseFragment extends Fragment {

    private View view;
    private List<Fragment> mFragments;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private ViewPager mViewPager;
    private List<ColorTrackView> mTabs = new ArrayList<>();


    public PurchaseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return view = inflater.inflate(R.layout.fragment_purchase, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        mFragments = new ArrayList<>();
        mFragments.add(new DoneFragment());
        mFragments.add(new UnDoneFragment());
        mViewPager = (ViewPager) view.findViewById(R.id.purchase_viewpager);
        mTabs.add((ColorTrackView) view.findViewById(R.id.id_tab01));
        mTabs.add((ColorTrackView) view.findViewById(R.id.id_tab02));
        fragmentPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }
        };
        mViewPager.setAdapter(fragmentPagerAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("NewOrin", "position=" + position + ", positionOffset=" + positionOffset + ", positionOffsetPixels=" + positionOffsetPixels);
                if (positionOffset > 0) {
                    ColorTrackView left = mTabs.get(position);
                    ColorTrackView right = mTabs.get(position + 1);

                    left.setDirection(1);
                    right.setDirection(0);
                    left.setProgress(1 - positionOffset);
                    right.setProgress(positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
