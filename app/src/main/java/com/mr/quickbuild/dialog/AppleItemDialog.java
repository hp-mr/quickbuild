package com.mr.quickbuild.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mr.quickbuild.R;
import com.mr.quickbuild.adapter.AppleItemAdapter;
import java.util.ArrayList;
import java.util.List;

public class AppleItemDialog extends Dialog {

    CallBack callBack;
    TextView tv_title;
    TextView tv_cancel;
    String title="";
    RecyclerView rv;
    AppleItemAdapter adapter;
    List<String>list=new ArrayList<>();
    public CallBack getCallBack() {
        return callBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_common_item);
        tv_title = findViewById(R.id.tv_title);
        rv = findViewById(R.id.rv);
        tv_cancel = findViewById(R.id.tv_cancel);
        initView();
    }

    private void initView() {
        setCancelable(false);
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(layoutParams);
        if(!TextUtils.isEmpty(title)){
            tv_title.setText(title);
        }
        adapter=new AppleItemAdapter(list);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(null!=callBack){
                    callBack.clickItem((String) adapter.getData().get(position));
                    dismiss();
                }
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public AppleItemDialog(@NonNull Context context) {
        super(context, R.style.dialog);
    }

    public AppleItemDialog setTitle(String content) {
        this.title = content;
        if(null!=tv_title){
            tv_title.setText(title);
        }
        return this;
    }
    public AppleItemDialog setData(List<String> list) {
        this.list.addAll(list);
        if(null!=adapter){
            adapter.replaceData(list);
        }
        return this;
    }
    public AppleItemDialog setData(String str) {
        this.list.add(str);
        if(null!=adapter){
            adapter.replaceData(list);
        }
        return this;
    }
    public AppleItemDialog setClickListener(CallBack callBack) {
        this.callBack = callBack;
        return this;
    }

    public interface CallBack {
        void clickItem(String str);
    }
}
