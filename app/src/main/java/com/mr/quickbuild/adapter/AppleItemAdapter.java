package com.mr.quickbuild.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mr.quickbuild.R;
import java.util.List;
import io.reactivex.annotations.Nullable;

//下拉选项adapter
public class AppleItemAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public AppleItemAdapter(@Nullable List<String> data) {
        super(R.layout.item_chose, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, String item) {
        if (null != item) {
            helper.setText(R.id.tv_name,item);
        }
    }
}

