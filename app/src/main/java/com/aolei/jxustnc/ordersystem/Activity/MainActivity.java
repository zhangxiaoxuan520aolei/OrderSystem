package com.aolei.jxustnc.ordersystem.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.fragment.CanteenFragment1;
import com.aolei.jxustnc.ordersystem.fragment.CanteenFragment2;
import com.aolei.jxustnc.ordersystem.fragment.CanteenFragment3;
import com.aolei.jxustnc.ordersystem.fragment.HomeFragment;
import com.aolei.jxustnc.ordersystem.fragment.PurchaseFragment;

public class MainActivity extends FragmentActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private Fragment currentFragment;
    private NavigationView navigationView;
    private LinearLayout layout_login_regist;
    private SharedPreferences mSharedPreferences;
    private TextView mStatusText;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharedPreferences = getSharedPreferences("user_info", Context.MODE_APPEND);
        mNavigationView = (NavigationView)findViewById(R.id.nav_view);
        View view  = mNavigationView.getHeaderView(0);//获取NavigationView的heardLayout
        mStatusText = (TextView)view.findViewById(R.id.status);//通过heardLayout来获取TextView 控件
        if (mSharedPreferences.getBoolean("status",false) == true){
            mStatusText.setText(mSharedPreferences.getString("username",""));
    }
        initView();
        initEvent();
    }

    /**
     * 初始化监听事件
     */
    private void initEvent() {
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        layout_login_regist.setOnClickListener(this);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        View v = LayoutInflater.from(this).inflate(R.layout.nav_header_main, null);
        layout_login_regist = (LinearLayout) v.findViewById(R.id.layout_login_regist);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("推荐");
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        //显示第一个Fragment
        HomeFragment homeFragment = new HomeFragment();
        currentFragment = homeFragment;
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, homeFragment).commit();

    }

    /**
     * 监听返回键
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            toolbar.setTitle("主页");
            switchFragment(new HomeFragment());
        } else if (id == R.id.nav_canteen1) {
            toolbar.setTitle("第一食堂");
            switchFragment(new CanteenFragment1());
            // Handle the camera action
        } else if (id == R.id.nav_canteen2) {
            toolbar.setTitle("第二食堂");
            switchFragment(new CanteenFragment2());

        } else if (id == R.id.nav_canteen3) {
            toolbar.setTitle("第三食堂");
            switchFragment(new CanteenFragment3());

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_purchase) {
            toolbar.setTitle("我的购买");
            switchFragment(new PurchaseFragment());
        }else if (id == R.id.signout){
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString("username","");
            editor.putString("userpwd","");
            editor.commit();
            Intent intent = new Intent();
            intent.setClass(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void switchFragment(Fragment fragment) {
        if (currentFragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (!fragment.isAdded()) {
                transaction.hide(currentFragment).add(R.id.fragment_container, fragment).commit();
            } else {
                transaction.hide(currentFragment).show(fragment).commit();
            }
        }
        currentFragment = fragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_login_regist:
                Toast.makeText(this, "点击", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

}
