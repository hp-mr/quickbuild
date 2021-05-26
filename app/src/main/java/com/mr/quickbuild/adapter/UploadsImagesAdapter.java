package com.mr.quickbuild.adapter;

import android.text.TextUtils;


import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mr.quickbuild.R;
import com.mr.quickbuild.utils.GlideUtils;

import java.util.List;


public class UploadsImagesAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public UploadsImagesAdapter(@Nullable List<String> data) {
        super(R.layout.item_upload_images, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, String item) {
        if (null != item) {
            if (!TextUtils.isEmpty(item)) {
                GlideUtils.load(item,helper.getView(R.id.iv_img));
                helper.setGone(R.id.iv_del,true);
            }else{
                helper.setImageResource(R.id.iv_del,R.mipmap.ic_add_image);
                helper.setGone(R.id.iv_del,false);
            }
        }
        helper.addOnClickListener(R.id.iv_del);
    }
}

