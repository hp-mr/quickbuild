package com.mr.quickbuild.divider;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.mr.quickbuild.utils.ScreenUtils;


public class DividerGridDecortion extends RecyclerView.ItemDecoration {
    float divider_width;
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }


    public DividerGridDecortion() {
    }

    public DividerGridDecortion(float divider_width) {
        this.divider_width = divider_width;
    }
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        outRect.right = ScreenUtils.dip2px(divider_width);
        outRect.left = ScreenUtils.dip2px(divider_width);
        outRect.top = ScreenUtils.dip2px(divider_width);
        outRect.bottom = ScreenUtils.dip2px(divider_width);
    }

}
