package com.mr.quickbuild.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @Description: java类作用描述
 * @Author: kirali
 * @CreateDate: 2019/3/18 0018 15:10
 * @UpdateUser: kirali
 * @UpdateDate: 2019/3/18 0018 15:10
 * @UpdateRemark: 更新说明
 */
@SuppressLint("AppCompatCustomView")
public class SquareWidthImageView extends ImageView {

    public SquareWidthImageView(Context context) {
        super(context);
    }

    public SquareWidthImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareWidthImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
