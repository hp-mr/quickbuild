package com.mr.quickbuild.view;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mr.quickbuild.R;


public class MyTitleBarView extends RelativeLayout {
    private View mView;
    ImageView iv_back;
    TextView tv_title,tv_right;

    public MyTitleBarView(Context context) {
        this(context, null);
        initView();
    }

    public MyTitleBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        initView();
    }

    public MyTitleBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void initView() {
        if (mView == null) {
            mView = View.inflate(getContext(), R.layout.layout_title_bar, this);
            iv_back = mView.findViewById(R.id.iv_back);
            tv_title = mView.findViewById(R.id.tv_title);
            tv_right = mView.findViewById(R.id.tv_right);
            iv_back.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        ((Activity)getContext()).finish();
                    }catch (Exception e){}
                }
            });
        }
    }
    public void setTitle(String text) {
        if (null != tv_title) {
            if (!TextUtils.isEmpty(text)) {
                tv_title.setText(text);
            }
        }
    }


    public void setRightText(String text, final clickRightTextListener clickRightTextListener) {
        if (null != tv_right) {
            if (!TextUtils.isEmpty(text)) {
                tv_right.setText(text);
                tv_right.setVisibility(VISIBLE);
            }
            tv_right.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != clickRightTextListener) {
                        clickRightTextListener.clickRightText();
                    }
                }
            });
        }
    }


    public interface clickRightTextListener {
        void clickRightText();
    }
}
