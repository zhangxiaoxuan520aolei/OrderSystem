package com.aolei.jxustnc.ordersystem.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.entity.User;
import com.aolei.jxustnc.ordersystem.util.CheckUtils;
import com.aolei.jxustnc.ordersystem.util.NetworkUtil;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;


public class LoginActivity extends Activity implements View.OnClickListener {
    private Button mLogin;
    private EditText mUname;
    private EditText mUpwd;
    private TextView mRegistView;
    private User user;
    private SharedPreferences mSharedPreferences;
    TextInputLayout uName;
    TextInputLayout uPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Bmob.initialize(this, CheckUtils.APPID);
        mSharedPreferences = getSharedPreferences("user_info",Context.MODE_APPEND);
        isLogin(mSharedPreferences);
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);
        initView();

    }

    void initView() {
        mLogin = (Button) findViewById(R.id.login_btn);
        mUname = (EditText) findViewById(R.id.uname_input);
        mUpwd = (EditText) findViewById(R.id.pwd_input);
        mRegistView = (TextView) findViewById(R.id.regist_textview);
        mRegistView.setOnClickListener(this);
        mLogin.setOnClickListener(this);
        uName = (TextInputLayout) findViewById(R.id.uname_Wrapper);
        uPwd = (TextInputLayout) findViewById(R.id.upwd_Wrapper);

    }

    /**
     * 登陆按钮注册时间
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        user = new User();
        switch (v.getId()) {
            case R.id.login_btn:
                if (NetworkUtil.isConnected(this)){
                    user.setUsername(mUname.getText().toString());
                    user.setPassword(mUpwd.getText().toString());
                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.putString("username", mUname.getText().toString());
                    editor.putString("userpwd", mUpwd.getText().toString());
                    editor.putBoolean("status",true);
                    editor.commit();
                    user.login(this, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            Intent intent = new Intent();
                            intent.setClass(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            Snackbar.make(mLogin, "账号或密码不正确", Snackbar.LENGTH_LONG).show();
                        }
                    });
                }else{
                    Toast.makeText(this,"网络未连接",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.regist_textview:
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, com.aolei.jxustnc.ordersystem.activity.RegistActivity.class);
                startActivity(intent);
            default:
                break;
        }
    }

    /**
     * 登录以后把账号密码存入到sharedPreferences
     * @param sharedPreferences
     */
    private void isLogin(SharedPreferences sharedPreferences){
        if (!("".equals(sharedPreferences.getString("username",""))) && !("".equals(sharedPreferences.getString("userpwd","")))){
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, com.aolei.jxustnc.ordersystem.activity.MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
