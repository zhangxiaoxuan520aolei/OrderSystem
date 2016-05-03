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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.fragment.CanteenFragment1;
import com.aolei.jxustnc.ordersystem.fragment.CanteenFragment2;
import com.aolei.jxustnc.ordersystem.fragment.CanteenFragment3;
import com.aolei.jxustnc.ordersystem.fragment.HomeFragment;
import com.aolei.jxustnc.ordersystem.fragment.PurchaseFragment;
import com.aolei.jxustnc.ordersystem.util.ToastUtil;

public class MainActivity extends FragmentActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private Fragment currentFragment;
    private NavigationView navigationView;
    private SharedPreferences mSharedPreferences;
    private ImageView nav_imageView;
    private TextView mStatusText;
    public static final int LOGIN_CODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharedPreferences = getSharedPreferences("user_info", Context.MODE_APPEND);
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
        nav_imageView.setOnClickListener(this);
    }

    /**
     * 初始化控件
     */
    private void initView() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("推荐");
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        nav_imageView = (ImageView) headerView.findViewById(R.id.nav_imageView);
        mStatusText = (TextView) headerView.findViewById(R.id.status);//通过heardLayout来获取TextView 控件
        if (mSharedPreferences.getBoolean("status", false) == true) {
            mStatusText.setText(mSharedPreferences.getString("username", ""));
        }
        //显示第一个Fragment
        HomeFragment homeFragment = new HomeFragment();
        currentFragment = homeFragment;
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, homeFragment).commit();

        /**
         * 添加数据，用作测试
         Food food = new Food();
         food.setName("茄子豆角");
         food.setPrice("8");
         food.setSold_count("239");
         Store store = new Store();
         store.setStore_name("第三食堂");
         store.setObjectId("gHqkMMMQ");
         food.setStore(store);
         food.setImg_url("");
         food.save(this, new SaveListener() {
        @Override public void onSuccess() {

        }

        @Override public void onFailure(int i, String s) {

        }
        });*/
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
        } else if (id == R.id.signout) {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString("username", "");
            editor.putString("userpwd", "");
            editor.putBoolean("status", false);
            editor.commit();
            Intent intent = new Intent();
            intent.setClass(this, LoginActivity.class);
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
                transaction.hide(currentFragment).replace(R.id.fragment_container, fragment).commit();
            } else {
                transaction.hide(currentFragment).show(fragment).commit();

            }
        }
        currentFragment = fragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nav_imageView:
                if (!isLogin(mSharedPreferences)) {
                    startActivityForResult(new Intent(this, LoginActivity.class), LOGIN_CODE);
                    Log.d("NewOrin", "没有登录过");
                } else {
                    ToastUtil.showShort(MainActivity.this, "已登录");
                    break;
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_CODE) {
            if (mSharedPreferences.getBoolean("status", false) == true) {
                mStatusText.setText(mSharedPreferences.getString("username", ""));
            }
        }
    }

    /**
     * 判断是否登录过
     *
     * @param sharedPreferences
     * @return
     */

    public boolean isLogin(SharedPreferences sharedPreferences) {
        if ("".equals(sharedPreferences.getString("username", "")) && ("".equals(sharedPreferences.getString("userpwd", "")))) {
            return false;
        } else return true;
    }
}
