package com.aolei.jxustnc.ordersystem.activity;

import android.app.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.utils.MyTextChangeListener;
import com.aolei.jxustnc.ordersystem.utils.Utils;
import com.aolei.jxustnc.ordersystem.entity.User;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;

public class RegistActivity extends Activity implements View.OnClickListener{
    private TextInputLayout mUnameInputLayout;
    private TextInputLayout mUpwdInputLayout;
    private TextInputLayout mDormitoryInputLayout;
    private TextInputLayout mPhoneNumInputLayout;
    private Button mRegistButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        Bmob.initialize(this,Utils.APPID);
        setContentView(R.layout.activity_regist);
        initView();
        listener();

    }
    void initView(){
        mUnameInputLayout = (TextInputLayout)findViewById(R.id.regist_uname);
        mUpwdInputLayout = (TextInputLayout)findViewById(R.id.regist_pwd);
        mDormitoryInputLayout = (TextInputLayout)findViewById(R.id.regist_dormitory);
        mPhoneNumInputLayout = (TextInputLayout)findViewById(R.id.regist_phone);
        mRegistButton = (Button)findViewById(R.id.regist_btn);
    }
    void listener(){
        mRegistButton.setOnClickListener(this);
        mUpwdInputLayout.getEditText().setOnFocusChangeListener(new MyTextChangeListener(mUpwdInputLayout,RegistActivity.this));
        mDormitoryInputLayout.getEditText().setOnFocusChangeListener(new MyTextChangeListener(mDormitoryInputLayout,RegistActivity.this));
        mPhoneNumInputLayout.getEditText().setOnFocusChangeListener(new MyTextChangeListener(mPhoneNumInputLayout,RegistActivity.this));
    }

    @Override
    public void onClick(View v) {
        if (Utils.isPassWord(mUpwdInputLayout.getEditText().getText().toString()) && Utils.isDormitoryNum(mDormitoryInputLayout.getEditText().getText().toString())
                && Utils.isMobileNumber(mPhoneNumInputLayout.getEditText().getText().toString())){
            User user = new User();
            user.setUsername(mUnameInputLayout.getEditText().getText().toString());
            user.setPassword(mUpwdInputLayout.getEditText().getText().toString());
            user.setDormitoryNumber(mDormitoryInputLayout.getEditText().getText().toString());
            user.setMobilePhoneNumber(mPhoneNumInputLayout.getEditText().getText().toString());
            user.setMobilePhoneNumberVerified(true);
            user.signUp(this, new SaveListener() {
                @Override
                public void onSuccess() {
                    Intent intent = new Intent();
                    intent.setClass(RegistActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(int i, String s) {
                    Log.d("error",s);

                }
            });
        }else{
            Snackbar.make(mRegistButton,"亲,您打开的方式不对哦,再检查一下吧",Snackbar.LENGTH_SHORT).show();
        }
    }
}
