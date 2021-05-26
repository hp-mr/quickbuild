package com.mr.quickbuild.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mr.quickbuild.R;


public class AppleInfoDialog extends Dialog {

    CallBack callBack;
    TextView tv_title;
    TextView tv_content;
    TextView tv_cancel;
    TextView tv_ok;
    String title,content,cancelText,okText;
    public CallBack getCallBack() {
        return callBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_common);
        tv_title = findViewById(R.id.tv_title);
        tv_content = findViewById(R.id.tv_content);
        tv_cancel = findViewById(R.id.tv_cancel);
        tv_ok = findViewById(R.id.tv_ok);
        initView();
    }

    private void initView() {
        setCancelable(true);
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(layoutParams);
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != callBack) {
                    callBack.clickOK();
                }
                dismiss();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        if(null!=title&&title.length()>0){
            tv_title.setText(title);
        }
        if(null!=content&&content.length()>0){
            tv_content.setText(content);
        }
        if(null!=cancelText&&cancelText.length()>0){
            tv_cancel.setText(cancelText);
        }
        if(null!=okText&&okText.length()>0){
            tv_ok.setText(okText);
        }
    }

    public AppleInfoDialog(@NonNull Context context) {
        super(context, R.style.dialog);
    }

    public AppleInfoDialog setTitle(String title) {
        this.title=title;
        return this;
    }

    public AppleInfoDialog setContent(String content) {
        this.content=content;
        return this;
    }

    public AppleInfoDialog setCancelText(String cancelText) {
        this.cancelText=cancelText;
        return this;
    }

    public AppleInfoDialog setOkText(String okText) {
        this.okText=okText;
        if(null!=tv_title){
            tv_title.setText(okText);
        }
        return this;
    }

    public AppleInfoDialog setClickListener(CallBack callBack) {
        this.callBack = callBack;
        return this;
    }

    public interface CallBack {
        void clickOK();
    }
}
