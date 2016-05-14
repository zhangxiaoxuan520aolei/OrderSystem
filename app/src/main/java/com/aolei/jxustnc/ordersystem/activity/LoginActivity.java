package com.aolei.jxustnc.ordersystem.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.entity.User;
import com.aolei.jxustnc.ordersystem.util.Utils;

import cn.bmob.v3.BmobUser;
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
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);
        mSharedPreferences = getSharedPreferences("user_info", Context.MODE_APPEND);
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
                            String tag = (String) BmobUser.getObjectByKey(LoginActivity.this, "tag");
                            SharedPreferences.Editor editor = mSharedPreferences.edit();
                            editor.putString("username", et_username.getText().toString());
                            editor.putString("userpwd", et_password.getText().toString());
                            editor.putBoolean("status", true);
                            editor.commit();
                            if ("0".equals(tag)) {
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            } else if ("1".equals(tag)) {

                                startActivity(new Intent(LoginActivity.this, ShopMainActivity.class));
                            }
                            finish();
                        }

                        @Override
                        public void onFinish() {
                            Utils.closeProgressDialog();
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            Log.d("TAG",s);
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
                intent.setClass(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
            default:
                break;
        }
    }

    private void showSnackBar(String text) {
        Snackbar.make(btn_login, text, Snackbar.LENGTH_LONG).show();
    }

}
