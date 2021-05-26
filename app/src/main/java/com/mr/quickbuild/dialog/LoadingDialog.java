package com.mr.quickbuild.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mr.quickbuild.R;


public class LoadingDialog extends Dialog {
    TextView tv_desc;
    String desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        initView();
    }

    private void initView() {
        setCancelable(false);
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        tv_desc=findViewById(R.id.tv_desc);
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(layoutParams);
        if(!TextUtils.isEmpty(desc)){
            tv_desc.setText(desc);
        }
    }

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.dialog_no_fade);
    }
    public LoadingDialog(@NonNull Context context, String desc) {
        super(context, R.style.dialog_no_fade);
        this.desc=desc;
    }
    public void setDesc(String desc){
        if(null!=tv_desc){
            tv_desc.setText(desc);
        }
    }
}
