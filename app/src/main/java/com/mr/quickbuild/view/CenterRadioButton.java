package com.mr.quickbuild.view;

/**
 * @Description: java类作用描述
 * @Author: kirali
 * @CreateDate: 2019/1/11 0011 14:45
 * @UpdateUser: kirali
 * @UpdateDate: 2019/1/11 0011 14:45
 * @UpdateRemark: 更新说明
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.RadioButton;

@SuppressLint("AppCompatCustomView")
public class CenterRadioButton extends RadioButton {
    public CenterRadioButton(Context context) {
        super(context);
    }

    public CenterRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CenterRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Drawable[] drawables = getCompoundDrawables();
        Drawable drawable = drawables[0];
        int gravity = getGravity();
        int left = 0;
        int top = 0;
        if (gravity == Gravity.CENTER) {
            left = ((int) (getWidth() - drawable.getIntrinsicWidth() - getPaint().measureText(getText().toString()))
                    / 2);
        }
        if (gravity == Gravity.TOP) {
            top=0;
        }
        if (gravity == Gravity.BOTTOM) {
            left = ((int) (getHeight() - drawable.getIntrinsicWidth() - getPaint().measureText(getText().toString()))
                    / 2);
        }
        drawable.setBounds(left, top, left + drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    }
}
