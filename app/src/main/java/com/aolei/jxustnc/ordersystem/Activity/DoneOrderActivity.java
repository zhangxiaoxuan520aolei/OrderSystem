package com.aolei.jxustnc.ordersystem.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.util.ToastUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMTextMessage;
import cn.bmob.newim.core.BmobIMClient;
import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.listener.MessageListHandler;
import cn.bmob.newim.listener.MessageSendListener;
import cn.bmob.v3.exception.BmobException;

public class DoneOrderActivity extends AppCompatActivity implements MessageListHandler {
    private Toolbar toolbar;
    private EditText et_message = null;
    private String send_msg;

    BmobIMConversation c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done_order);
        initView();
        /**
         * 使用BmobIMConversation.obtain(BmobIMClient client,BmobIMConversation conversation)方法
         * 传入BmobIMClient和BmobIMConversation的各自实例就可以创建一个用于控制消息查询、发送和删除的会话实例。
         */
        //在聊天页面的onCreate方法中，通过如下方法创建新的会话实例,这个obtain方法才是真正创建一个管理消息发送的会话
        c = BmobIMConversation.obtain(BmobIMClient.getInstance(), (BmobIMConversation) getIntent().getExtras().getSerializable("c"));

    }

    private void initView() {
        et_message = (EditText) findViewById(R.id.et_message);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Take it!");
    }

    public void doClick(View view) {
        send_msg = et_message.getText().toString();
        sendMessage();
    }

    private void sendMessage() {
        BmobIMTextMessage msg = new BmobIMTextMessage();
        msg.setContent(send_msg);
        //可随意设置额外信息
        Map<String, Object> map = new HashMap<>();
        map.put("level", 1);
        msg.setExtraMap(map);
        c.sendMessage(msg, new MessageSendListener() {
            @Override
            public void done(BmobIMMessage bmobIMMessage, BmobException e) {
                if (e == null) {
                    Log.d("NewOrin", "消息发送成功");
                } else {
                    Log.d("NewOrin", "消息发送失败" + e.getMessage() + "/" + e.getErrorCode());
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        BmobIM.getInstance().disConnect();//与服务器断开连接
        super.onDestroy();
    }

    @Override
    public void onMessageReceive(List<MessageEvent> list) {
        Log.d("NewOrin", "消息list=" + list.size());
        if (list.size() == 0) {
            ToastUtil.showShort(this, "没有消息");
        }
        for (int i = 0; i < list.size(); i++) {
            ToastUtil.showShort(this, "接受到了" + list.get(i).getFromUserInfo().getName() + "的消息:" + list.get(i).getMessage().getContent());
        }
    }

    /**
     * 在onResume方法中添加页面消息监听器：
     */
    @Override
    protected void onResume() {
        BmobIM.getInstance().addMessageListHandler(this);
        super.onResume();
    }

    /**
     * 在onPause方法中移除页面消息监听器
     */
    @Override
    protected void onPause() {
        BmobIM.getInstance().removeMessageListHandler(this);
        super.onPause();
    }
}
