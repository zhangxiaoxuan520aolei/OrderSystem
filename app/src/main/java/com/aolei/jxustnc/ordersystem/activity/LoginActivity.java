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

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.entity.User;
import com.aolei.jxustnc.ordersystem.util.CheckUtils;
import com.aolei.jxustnc.ordersystem.util.Utils;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends Activity implements View.OnClickListener {
    private Button btn_login;
    private EditText et_username;
    private EditText et_password;
    private TextView tv_register;
    private User user;
    private SharedPreferences mSharedPreferences;
    private TextInputLayout input_layout_username;
    private TextInputLayout input_layout_password;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Bmob.initialize(this, CheckUtils.APPID);
        mSharedPreferences = getSharedPreferences("user_info", Context.MODE_APPEND);
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);
        initView();
        initEvent();
    }

    private void initEvent() {
        tv_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    void initView() {
        btn_login = (Button) findViewById(R.id.login_btn);
        et_username = (EditText) findViewById(R.id.et_login_name);
        et_password = (EditText) findViewById(R.id.et_login_pwd);
        tv_register = (TextView) findViewById(R.id.regist_textview);
        input_layout_username = (TextInputLayout) findViewById(R.id.input_layout_name);
        input_layout_password = (TextInputLayout) findViewById(R.id.input_layout_pwd);
    }

    /**
     * 登陆按钮注册事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        user = new User();
        switch (v.getId()) {
            case R.id.login_btn:
                Utils.showProgressDialog(LoginActivity.this);
                username = et_username.getText().toString().trim();
                password = et_password.getText().toString().trim();
                if (!username.equals("") && !password.equals("")) {
                    user.setUsername(username);
                    user.setPassword(password);
                    user.login(this, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            SharedPreferences.Editor editor = mSharedPreferences.edit();
                            editor.putString("username", et_username.getText().toString());
                            editor.putString("userpwd", et_password.getText().toString());
                            editor.putBoolean("status", true);
                            editor.commit();
                            setResult(MainActivity.LOGIN_CODE);
                            Intent intent = new Intent();
                            intent.setClass(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFinish() {
                            Utils.closeProgressDialog();
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            showSnackBar("账号或密码不正确");
                        }
                    });
                } else {
                    showSnackBar("用户名或密码不能为空");
                    Utils.closeProgressDialog();
                }
                break;
            case R.id.regist_textview:
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, com.aolei.jxustnc.ordersystem.activity.RegistActivity.class);
                startActivity(intent);
                finish();
            default:
                break;
        }
    }

    private void showSnackBar(String text) {
        Snackbar.make(btn_login, text, Snackbar.LENGTH_LONG).show();
    }

}
