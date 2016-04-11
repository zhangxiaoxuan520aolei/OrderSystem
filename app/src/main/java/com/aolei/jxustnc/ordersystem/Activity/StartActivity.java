package com.aolei.jxustnc.ordersystem.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.aolei.jxustnc.ordersystem.R;

public class StartActivity extends Activity implements View.OnClickListener {
    private TextView mRegist,mSignin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_start);
        super.onCreate(savedInstanceState);
        initView();
    }
     void initView(){
        mRegist = (TextView)findViewById(R.id.regist);
         mRegist.setOnClickListener(this);
         mSignin = (TextView)findViewById(R.id.signin);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.regist:
                intent.setClass(StartActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.signin:
                break;
            default:
                break;
        }
    }
}
