package com.aolei.jxustnc.ordersystem.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.activity.LoginActivity;
import com.aolei.jxustnc.ordersystem.entity.User;
import com.aolei.jxustnc.ordersystem.util.CheckUtils;
import com.aolei.jxustnc.ordersystem.util.NetworkUtil;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * 商家信息
 */
public class UserinfoFragment extends Fragment implements View.OnClickListener{

    private View view;
    public UserinfoFragment() {
        // Required empty public constructor
    }
    private TextView mTrueName,mPwd,mPhone,mStoreName;
    private PercentRelativeLayout mLayoutPwd,mLyoutPhone;
    private Button mLoginOut;
    private SharedPreferences mSharedPreferences;
    private List<User> mList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_user_info, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Bmob.initialize(getContext(), CheckUtils.APPID);
        super.onActivityCreated(savedInstanceState);
        mSharedPreferences = getActivity().getSharedPreferences("user_info", Context.MODE_APPEND);
        initView();
        queryInfo();


    }
    protected void initView(){
        mTrueName = (TextView)view.findViewById(R.id.info_truename);
        mPwd = (TextView)view.findViewById(R.id.info_pwd);
        mPhone = (TextView)view.findViewById(R.id.info_phone);
        mStoreName = (TextView)view.findViewById(R.id.info_storename);
        mLayoutPwd = (PercentRelativeLayout)view.findViewById(R.id.layout_pwd);
        mLyoutPhone = (PercentRelativeLayout)view.findViewById(R.id.layout_phone);
        mLoginOut = (Button)view.findViewById(R.id.loginout);
        mLoginOut.setOnClickListener(this);
    }

    /**
     * 查询数据
     */
    protected void queryInfo(){
        if (NetworkUtil.isConnected(getContext())){
            BmobQuery<User>  query = new BmobQuery<>();
            query.addWhereEqualTo("username",mSharedPreferences.getString("username",""));
            query.order("objectId");
            Log.d("username",mSharedPreferences.getString("username",""));
            query.findObjects(getContext(), new FindListener<User>() {
                @Override
                public void onSuccess(List<User> list) {
                    mList = list;
                    setData();
                }

                @Override
                public void onError(int i, String s) {
                    Log.d("Error",s);
                }
            });
        }else{
            Snackbar.make(getView(),"网络未连接",Snackbar.LENGTH_SHORT).show();
        }


    }

    /**
     * 填充数据
     */
    protected void setData(){
        if (!mList.isEmpty() && mList != null){
            mTrueName.setText(mList.get(0).getTrueName());
            mPwd.setText(mSharedPreferences.getString("userpwd",""));
            mPhone.setText(mList.get(0).getMobilePhoneNumber());
            mStoreName.setText(mList.get(0).getStore_name());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginout:
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putString("username","");
                editor.putString("userpwd","");
                editor.commit();
                Intent intent = new Intent();
                intent.setClass(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;
            default:
                break;
        }
    }
}
