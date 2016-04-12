package com.aolei.jxustnc.ordersystem.Utils;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by aolei on 2016/4/12.
 */
public class MyTextChangeListener implements TextWatcher {
    private TextInputLayout mTextInputLayout;
    private String errorInfo;
    public MyTextChangeListener(TextInputLayout textInputLayout,String errorinfo){
        this.mTextInputLayout = textInputLayout;
        this.errorInfo = errorinfo;
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (Utils.isMobileNumber(mTextInputLayout.getEditText().getText().toString())){
            mTextInputLayout.setErrorEnabled(true);//是否设置错误提示信息
            mTextInputLayout.setError(errorInfo);//设置错误提示信息
        }else{
            mTextInputLayout.setErrorEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {


    }
}
