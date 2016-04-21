package com.aolei.jxustnc.ordersystem.util;

import android.content.Context;
import android.util.Log;

import com.aolei.jxustnc.ordersystem.entity.Store;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * 作者：aolei on 2016/4/21 16:48
 * 邮箱：807648567@qq.com
 * 解释权：敖磊
 */
public class HttpUtils {
    private Context mContext;
    private String mCateen;
    List<Store> resultList;
    private List<Store> mStores;
   public HttpUtils(Context context) {
        this.mContext = context;
    }

    /**
     * 查询Store表中所有的数据，
     *
     * @return Stroe表中数据的List集合
     */
    public void querryStore() {
        BmobQuery<Store> query = new BmobQuery<>();

        //按照ObjectId查找
        query.order("objectId");
        query.findObjects(mContext, new FindListener<Store>() {
            //查询成功

            @Override
            public void onSuccess(List<Store> list) {
                for (int i = 0; i < list.size(); i++) {
                    Store store = new Store();
                    store.setStore_pic(list.get(i).getStore_pic());
                    store.setBelong_cateen(list.get(i).getBelong_cateen());
                    store.setStore_des(list.get(i).getStore_des());
                    store.setStore_name(list.get(i).getStore_name());
                    Log.d("TAG", store.getStore_pic() + " " + store.getBelong_cateen() + " " + store.getStore_des() + " "
                            + store.getStore_name());
                    resultList.add(store);
                    Log.d("result",resultList.size()+"");
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                setStores(resultList);

            }

            @Override
            public void onError(int i, String s) {
                Log.d("StoreError", s);
            }
        });
    }

    public List<Store> getStores() {
        return mStores;
    }

    public void setStores(List<Store> stores) {
        mStores = stores;
    }
}
