package com.aolei.jxustnc.ordersystem.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.util.Utils;
import com.aolei.jxustnc.ordersystem.entity.User;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends Activity implements View.OnClickListener {
    private Button mLogin;
    private EditText mUname;
    private EditText mUpwd;
    private TextView mRegistView;
    TextInputLayout uName;
    TextInputLayout uPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Bmob.initialize(this, Utils.APPID);
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);
        initView();

    }
    void initView(){
        mLogin = (Button) findViewById(R.id.login_btn);
        mUname = (EditText)findViewById(R.id.uname_input);
        mUpwd = (EditText)findViewById(R.id.pwd_input);
        mRegistView = (TextView)findViewById(R.id.regist_textview);
        mRegistView.setOnClickListener(this);
        mLogin.setOnClickListener(this);
        uName = (TextInputLayout)findViewById(R.id.uname_Wrapper);
        uPwd = (TextInputLayout)findViewById(R.id.upwd_Wrapper);


}

    /**
     * 登陆按钮注册时间
     * @param v
     */
    @Override
    public void onClick(View v) {
        User user = new User();
        switch (v.getId()) {
            case R.id.login_btn:
                user.setUsername(mUname.getText().toString());
                user.setPassword(mUpwd.getText().toString());
                user.login(this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Intent intent = new Intent();
                        intent.setClass(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    @Override
                    public void onFailure(int i, String s) {
                        Snackbar.make(mLogin,"账号或密码不正确",Snackbar.LENGTH_LONG).show();
                    }
                });
                break;
            case R.id.regist_textview:
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this,RegistActivity.class);
                startActivity(intent);
            default:
                break;
        }
    }
}
