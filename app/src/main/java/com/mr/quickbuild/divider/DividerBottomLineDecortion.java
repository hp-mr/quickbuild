package com.mr.quickbuild.divider;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.mr.quickbuild.utils.ScreenUtils;


public class DividerBottomLineDecortion extends RecyclerView.ItemDecoration {
    float dp=3;

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    public DividerBottomLineDecortion(float dp) {
        this.dp = dp;
    }
    public DividerBottomLineDecortion() {
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        outRect.bottom = ScreenUtils.dip2px(dp);
    }
}
