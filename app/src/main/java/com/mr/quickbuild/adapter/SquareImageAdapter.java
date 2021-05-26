package com.mr.quickbuild.adapter;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mr.quickbuild.R;
import com.mr.quickbuild.utils.GlideUtils;

import java.util.List;



public class SquareImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public SquareImageAdapter(@Nullable List<String> data) {
        super(R.layout.item_image, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, String item) {
        if (null != item) {
            if (!TextUtils.isEmpty(item)) {
                GlideUtils.load(item,helper.getView(R.id.iv));
            }
        }
    }
}

