package com.aolei.jxustnc.ordersystem.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.Utils.MyTextChangeListener;
import com.aolei.jxustnc.ordersystem.Utils.Utils;
import com.aolei.jxustnc.ordersystem.entity.User;

import org.w3c.dom.Text;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;

public class StartActivity extends Activity implements View.OnClickListener {
    private Button mLogin;
    private EditText mUname;
    private EditText mUpwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Bmob.initialize(this, Utils.APPID);
        setContentView(R.layout.activity_start);
        super.onCreate(savedInstanceState);
        initView();

    }
    void initView(){
        mLogin = (Button) findViewById(R.id.login_btn);
        mUname = (EditText)findViewById(R.id.uname_input);
        mUpwd = (EditText)findViewById(R.id.pwd_input);
        mLogin.setOnClickListener(this);
        final TextInputLayout uname = (TextInputLayout)findViewById(R.id.uname_Wrapper);
        final TextInputLayout upwd = (TextInputLayout)findViewById(R.id.upwd_Wrapper);
        uname.setHint("用户名");
        upwd.setHint("密  码");
        mUname.addTextChangedListener(new MyTextChangeListener(uname,"您输入的手机号码不合法"));
}
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
                        intent.setClass(StartActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    @Override
                    public void onFailure(int i, String s) {

                    }
                });
                break;
            default:
                break;
        }
    }
}
