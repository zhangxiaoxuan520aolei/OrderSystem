package com.aolei.jxustnc.ordersystem.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.activity.MainActivity;

import cn.bmob.push.PushConstants;

/**
 * 推送消息接受类
 * 作者：aolei on 2016/5/4 23:32
 * 邮箱：807648567@qq.com
 * 解释权：敖磊
 */
public class MyPushMessageReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(PushConstants.ACTION_MESSAGE)){
            Log.d("bmob", "BmobPushDemo收到消息："+intent.getStringExtra(PushConstants.EXTRA_PUSH_MESSAGE_STRING));
            //Toast.makeText(context, "BmobPushDemo收到消息："+intent.getStringExtra(PushConstants.EXTRA_PUSH_MESSAGE_STRING),Toast.LENGTH_SHORT).show();
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Intent intent1 = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,100,intent1,0);
            Notification.Builder builder = new Notification.Builder(context)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setTicker("您有一条未读消息")
                    .setAutoCancel(true)
                    .setContentTitle("通知")
                    .setContentText(intent.getStringExtra(PushConstants.EXTRA_PUSH_MESSAGE_STRING).toString());
                    //.setContentText(intent.getStringExtra(str));
            Notification notification = builder.build();
            manager.notify(110,notification);
        }
    }
}
