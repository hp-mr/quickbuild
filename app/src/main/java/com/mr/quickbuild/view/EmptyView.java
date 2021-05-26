package com.mr.quickbuild.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mr.quickbuild.R;


public class EmptyView extends LinearLayout {
    private View mView;
    private LayoutInflater mInflater;
    ImageView iv_empty;
    TextView tv_empty;
    LinearLayout ll_parent;

    public EmptyView(Context context) {
        this(context, null);
        initView();
    }

    public EmptyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        initView();
    }

    public EmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void initView() {
        if (mView == null) {
            mInflater = LayoutInflater.from(getContext());
            mView = View.inflate(getContext(), R.layout.empty_view, this);
            iv_empty = mView.findViewById(R.id.iv_empty);
            tv_empty = mView.findViewById(R.id.tv_empty);
            ll_parent = mView.findViewById(R.id.ll_parent);
        }
    }
    public void setEmptyBgColor(int resId) {
        if (null != iv_empty) {
            ll_parent.setBackgroundColor(resId);
        }
    }
    public void setEmptyImg(int resId) {
        if (null != iv_empty) {
            iv_empty.setImageResource(resId);
        }
    }
    public void setEmptyText(String desc) {
        if (null != tv_empty) {
            tv_empty.setText(desc);
        }
    }
}
