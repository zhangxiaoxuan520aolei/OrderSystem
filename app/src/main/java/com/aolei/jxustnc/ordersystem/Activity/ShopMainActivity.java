package com.aolei.jxustnc.ordersystem.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.fragment.FoodFragment;
import com.aolei.jxustnc.ordersystem.fragment.MessageFragment;
import com.aolei.jxustnc.ordersystem.fragment.UserinfoFragment;
import com.aolei.jxustnc.ordersystem.view.ColorTrackView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.newim.BmobIM;

public class ShopMainActivity extends AppCompatActivity {


    private List<Fragment> mFragments;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private ViewPager mViewPager;
    private List<ColorTrackView> mTabs = new ArrayList<>();
    private Toolbar show_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_main);
        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.container);
        mFragments = new ArrayList<>();
        mFragments.add(new MessageFragment());
        mFragments.add(new FoodFragment());
        mFragments.add(new UserinfoFragment());
        mTabs.add((ColorTrackView) findViewById(R.id.tab01));
        mTabs.add((ColorTrackView) findViewById(R.id.tab02));
        mTabs.add((ColorTrackView) findViewById(R.id.tab03));
        show_toolbar = (Toolbar) findViewById(R.id.show_toolbar);
        show_toolbar.setTitleTextColor(Color.WHITE);
        show_toolbar.setTitle("商家");
        setSupportActionBar(show_toolbar);
        mViewPager.setOffscreenPageLimit(2);
        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };
        mViewPager.setAdapter(fragmentPagerAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BmobIM.getInstance().disConnect();
    }
}
