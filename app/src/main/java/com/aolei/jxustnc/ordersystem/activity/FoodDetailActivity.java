package com.aolei.jxustnc.ordersystem.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.entity.FoodEntity;
import com.aolei.jxustnc.ordersystem.util.DividerItemDecoration;
import com.aolei.jxustnc.ordersystem.util.ImageAdapter;
import com.aolei.jxustnc.ordersystem.util.RecyclerViewCommonAdapter;
import com.aolei.jxustnc.ordersystem.util.RecyclerViewViewHolder;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class FoodDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsing_layout;
    private ImageHandler handler;
    private ViewPager viewPager;
    private LayoutInflater inflater;
    private ImageView imageView;
    private List<ImageView> imageViewList;
    private LinearLayout layout_image_indicator;
    private int[] images = {R.mipmap.cheese_2, R.mipmap.cheese_3, R.mipmap.cheese_2};
    private ImageView[] image_indicator;
    private RecyclerView food_detail_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        initView();
    }

    private void initView() {
        layout_image_indicator = (LinearLayout) findViewById(R.id.layout_image_indicator);
        viewPager = (ViewPager) findViewById(R.id.image_viewpager);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsing_layout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsing_layout.setTitle("北京烤鸭");
        handler = new ImageHandler(new WeakReference<FoodDetailActivity>(this));
        inflater = LayoutInflater.from(this);
        imageViewList = new ArrayList<>();
        image_indicator = new ImageView[images.length];
        for (int i = 0; i < images.length; i++) {
            imageView = (ImageView) inflater.inflate(R.layout.item_image_viewpager, null);
            imageView.setImageResource(images[i]);
            imageViewList.add(imageView);
            View view = inflater.inflate(R.layout.item_image_indicator, layout_image_indicator, false);
            image_indicator[i] = (ImageView) view.findViewById(R.id.image_indicator);
            layout_image_indicator.addView(image_indicator[i]);
        }
        viewPager.setAdapter(new ImageAdapter(imageViewList));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //配合Adapter的currentItem字段进行设置。
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                handler.sendMessage(Message.obtain(handler, ImageHandler.MSG_PAGE_CHANGED, position, 0));
                setIndicator(position);
            }

            //覆写该方法实现轮播效果的暂停和恢复
            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        handler.sendEmptyMessage(ImageHandler.MSG_KEEP_SILENT);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
                        break;
                    default:
                        break;
                }
            }
        });
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2);//默认在中间，使用户看不到边界
        //开始轮播效果
        handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
        setRecyclerView();
    }

    private void setRecyclerView() {
        List<FoodEntity> data_list = new ArrayList<>();
        FoodEntity foodEntity;
        for (int i = 0; i < 20; i++) {
            foodEntity = new FoodEntity();
            foodEntity.setDetail("NewOrin");
            data_list.add(foodEntity);
        }
        food_detail_recyclerview = (RecyclerView) findViewById(R.id.food_detail_recyclerview);
        food_detail_recyclerview.setAdapter(new RecyclerViewCommonAdapter<FoodEntity>(this, R.layout.item_food_comment, data_list) {
            @Override
            public void convert(RecyclerViewViewHolder holder, FoodEntity foodEntity) {
                TextView tv = holder.getView(R.id.tv_comment_name);
                tv.setText(foodEntity.getDetail());
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        food_detail_recyclerview.setLayoutManager(linearLayoutManager);
        food_detail_recyclerview.setNestedScrollingEnabled(false);
        food_detail_recyclerview.setItemAnimator(new DefaultItemAnimator());
        food_detail_recyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }

    /**
     * 设置指示器
     *
     * @param position
     */
    private void setIndicator(int position) {
        int currentItem = position % image_indicator.length;
        for (int i = 0; i < image_indicator.length; i++) {
            if (i != currentItem) {
                image_indicator[i].setImageResource(R.drawable.ic_circle_indicator_normal);
            } else {
                image_indicator[currentItem].setImageResource(R.drawable.ic_circle_indicator_pressed);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_food_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void doClick(View view) {
        switch (view.getId()) {
            case R.id.food_layout_call:
                //拨打电话
                Uri uri = Uri.parse("tel:18907975986");
                startActivity(new Intent(Intent.ACTION_DIAL, uri));
                break;
            case R.id.food_layout_canteen:
                break;
        }
    }

    private static class ImageHandler extends Handler {
        /**
         * 请求更新显示的View。
         */
        protected static final int MSG_UPDATE_IMAGE = 1;
        /**
         * 请求暂停轮播。
         */
        protected static final int MSG_KEEP_SILENT = 2;
        /**
         * 请求恢复轮播。
         */
        protected static final int MSG_BREAK_SILENT = 3;
        /**
         * 记录最新的页号，当用户手动滑动时需要记录新页号，否则会使轮播的页面出错。
         * 例如当前如果在第一页，本来准备播放的是第二页，而这时候用户滑动到了末页，
         * 则应该播放的是第一页，如果继续按照原来的第二页播放，则逻辑上有问题。
         */
        protected static final int MSG_PAGE_CHANGED = 4;

        //轮播间隔时间
        protected static final long MSG_DELAY = 3000;

        //使用弱引用避免Handler泄露.这里的泛型参数可以不是Activity，也可以是Fragment等
        private WeakReference<FoodDetailActivity> weakReference;
        private int currentItem = 0;

        protected ImageHandler(WeakReference<FoodDetailActivity> wk) {
            weakReference = wk;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            FoodDetailActivity activity = weakReference.get();
            if (activity == null) {
                //Activity已经回收，无需再处理UI了
                return;
            }
            //检查消息队列并移除未发送的消息，这主要是避免在复杂环境下消息出现重复等问题。
            if (activity.handler.hasMessages(MSG_UPDATE_IMAGE)) {
                activity.handler.removeMessages(MSG_UPDATE_IMAGE);
            }
            switch (msg.what) {
                case MSG_UPDATE_IMAGE:
                    currentItem++;
                    activity.viewPager.setCurrentItem(currentItem);
                    //准备下次播放
                    activity.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_KEEP_SILENT:
                    //只要不发送消息就暂停了
                    break;
                case MSG_BREAK_SILENT:
                    activity.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_PAGE_CHANGED:
                    //记录当前的页号，避免播放的时候页面显示不正确。
                    currentItem = msg.arg1;
                    break;
                default:
                    break;
            }
        }
    }
}
