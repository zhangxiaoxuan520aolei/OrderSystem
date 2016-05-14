package com.aolei.jxustnc.ordersystem.util;

import android.content.Context;

import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.event.OfflineMessageEvent;
import cn.bmob.newim.listener.BmobIMMessageHandler;

/**
 * 自定义消息接收器继承自BmobIMMessageHandler来处理服务器发来的消息和离线消息。
 * Created by NewOr on 2016/5/4.
 */
public class DemoMessageHandler extends BmobIMMessageHandler {

    private Context context;

    public DemoMessageHandler(Context context) {
        this.context = context;
    }

    /**
     * 当接收到服务器发来的消息时，此方法被调用
     *
     * @param messageEvent
     */
    @Override
    public void onMessageReceive(MessageEvent messageEvent) {
        super.onMessageReceive(messageEvent);
        ToastUtil.showShort(context, "消息:" + messageEvent.getMessage().getContent());
    }

    /**
     * 每次调用connect方法时会查询一次离线消息，如果有，此方法会被调用
     *
     * @param offlineMessageEvent
     */
    @Override
    public void onOfflineReceive(OfflineMessageEvent offlineMessageEvent) {
        super.onOfflineReceive(offlineMessageEvent);
        ToastUtil.showShort(context, "共有" + offlineMessageEvent.getTotalNumber() + " 条消息");
    }
}
