package com.aolei.jxustnc.ordersystem;

import android.app.Application;

import com.aolei.jxustnc.ordersystem.util.DemoMessageHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import cn.bmob.newim.BmobIM;

/**
 * 注：
 * 初始化方法包含了BmobSDK的初始化步骤，故无需再初始化BmobSDK
 * 在初始化的时候，最好做下判断：只有主进程运行的时候才开始初始化，避免资源浪费。
 * Created by NewOr on 2016/5/4.
 */
public class BmobIMApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //只有主进程运行的时候才需要初始化
        if (getApplicationInfo().packageName.equals(getMyProcessName()))
            BmobIM.init(this);
        //注册消息接收器
        BmobIM.registerDefaultMessageHandler(new DemoMessageHandler(this));
    }

    /**
     * 获取当前运行的进程名
     *
     * @return
     */
    public static String getMyProcessName() {
        try {
            File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
            BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
            String processName = mBufferedReader.readLine().trim();
            mBufferedReader.close();
            return processName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
