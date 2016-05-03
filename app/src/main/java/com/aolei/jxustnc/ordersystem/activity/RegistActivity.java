package com.aolei.jxustnc.ordersystem.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.aolei.jxustnc.ordersystem.R;
import com.aolei.jxustnc.ordersystem.util.CheckUtils;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.VerifySMSCodeListener;

public class RegistActivity extends Activity implements View.OnClickListener {

    private Button btn_register, btn_validate;
    private EditText et_register_username, et_register_pwd, et_register_phone, et_register_code,et_register_dormitory,et_register_suerpwd;
    private String username, password, phone_number, code;

    private TimeCount timeCount;
    private int millisInFuture = 6000;//倒计时秒数
    private int countDownInterval = 1000;//倒计时多少

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        Bmob.initialize(this, CheckUtils.APPID);
        initView();
        initEvent();
    }

    void initView() {
        btn_register = (Button) findViewById(R.id.regist_btn);
        btn_validate = (Button) findViewById(R.id.get_code_btn);
        et_register_username = (EditText) findViewById(R.id.et_register_username);
        et_register_phone = (EditText) findViewById(R.id.et_register_phone);
        et_register_code = (EditText) findViewById(R.id.et_register_code);
        et_register_pwd = (EditText) findViewById(R.id.et_register_pwd);
        et_register_dormitory = (EditText)findViewById(R.id.et_register_dormitoryNum);
        et_register_suerpwd = (EditText)findViewById(R.id.et_register_sure_pwd);
        timeCount = new TimeCount(millisInFuture, countDownInterval);
    }

    void initEvent() {
        btn_register.setOnClickListener(this);
        btn_validate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        phone_number = et_register_phone.getText().toString();
        switch (v.getId()) {
            case R.id.get_code_btn:
                if (!TextUtils.isEmpty(phone_number)) {
                    sendVerificaCode(phone_number);
                    Snackbar.make(btn_register, "验证码已发送到手机", Snackbar.LENGTH_SHORT).show();
                    timeCount.start();
                } else {
                    Snackbar.make(btn_register, "手机号不能为空", Snackbar.LENGTH_SHORT).show();
                }
                break;
            case R.id.regist_btn:
                code = et_register_code.getText().toString();
                if (!TextUtils.isEmpty(phone_number) && !TextUtils.isEmpty(code)) {
                    validateCode(phone_number, code);
                } else {
                    Snackbar.make(btn_register, "手机号或验证码不能为空", Snackbar.LENGTH_SHORT).show();
                }
                break;
        }
    }


    /**
     * 发送验证码
     *
     * @param phone
     */
    private void sendVerificaCode(String phone) {
        BmobSMS.requestSMSCode(RegistActivity.this, phone, "短信模板", new RequestSMSCodeListener() {
            @Override
            public void done(Integer smsId, BmobException e) {
                if (e == null) {//验证码发送成功
                    Log.e("NewOrin", "短信id：" + smsId);//用于查询本次短信发送详情
                }
            }
        });
    }

    /**
     * 校验验证码
     */
    private void validateCode(final String phone, String code) {
        Log.d("NewOrin", phone + "," + code);
        //开始请求后台校验验证码
        BmobSMS.verifySmsCode(RegistActivity.this, phone, code, new VerifySMSCodeListener() {
            @Override
            public void done(BmobException ex) {
                if (ex == null) {//短信验证码已验证成功
                    Log.d("NewOrin", "验证通过");
                    doRegister(btn_register.getText().toString(), phone);
                } else {
                    Snackbar.make(btn_register, "验证失败：code =" + ex.getErrorCode() + ",msg = " + ex.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 注册用户
     *
     * @param password
     * @param phone
     */
    private void doRegister(String password, String phone) {
        username = et_register_username.getText().toString();
        BmobUser user = new BmobUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setMobilePhoneNumber(phone);
        user.signUp(this, new SaveListener() {
            @Override
            public void onSuccess() {
                Snackbar.make(btn_register, "注册成功!请重新登录!", Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                Snackbar.make(btn_register, "注册失败!" + s, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private class TimeCount extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btn_validate.setClickable(false);
            btn_validate.setTextColor(Color.WHITE);
            btn_validate.setText(millisUntilFinished / 1000 + "秒后重新获取");
        }

        @Override
        public void onFinish() {
            btn_validate.setTextColor(Color.BLACK);
            btn_validate.setText("获取验证码");
            btn_validate.setClickable(true);
        }
    }
}
