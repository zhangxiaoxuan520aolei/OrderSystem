package com.aolei.jxustnc.ordersystem.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;

import com.aolei.jxustnc.ordersystem.R;

/**
 * 注册验证数据是否合法
 * Created by aolei on 2016/4/12.
 */
public class MyTextChangeListener implements View.OnFocusChangeListener {
    private TextInputLayout mTextInputLayout;
    private EditText mEditText;
    private Context context;

    public MyTextChangeListener(EditText editText,Context context) {
        //this.mTextInputLayout = textInputLayout;
        this.mEditText = editText;
        this.context = context;
    }



   /**
     * EditText 失去焦点监听事件
     *
     * @param v
     * @param hasFocus
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.et_register_username:
                if (!hasFocus) {
                }
                break;
            case R.id.et_register_pwd:
                if (!hasFocus) {
                    if (!CheckUtils.isPassWord(mEditText.getText().toString())) {
                        v.clearFocus();
                        Snackbar.make(mEditText, "密码必须是数字和字母的组合且长度必须大于8小于16位", Snackbar.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.et_register_phone:
                if (!hasFocus) {
                    if (!CheckUtils.isMobileNumber(mEditText.getText().toString())) {
                        v.clearFocus();
                        Snackbar.make(mEditText, "您输入的手机号码不符合规范", Snackbar.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.et_register_dormitoryNum:
                if (!hasFocus) {
                    if (!CheckUtils.isDormitoryNum(mEditText.getText().toString())) {

                        Snackbar.make(mEditText, "您输入的宿舍号不存在", Snackbar.LENGTH_SHORT).show();
                    }
                }
                break;
           /* case R.id.et_register_sure_pwd:
                if (!hasFocus){
                    if (!Utils.isEquals())
                }*/
            default:
                break;
        }
    }


}
