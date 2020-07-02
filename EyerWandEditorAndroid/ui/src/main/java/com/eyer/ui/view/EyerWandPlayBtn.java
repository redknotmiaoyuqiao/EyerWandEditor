package com.eyer.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class EyerWandPlayBtn extends View {

    public EyerWandPlayBtn(Context context) {
        super(context);
        init();
    }

    public EyerWandPlayBtn(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EyerWandPlayBtn(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private int init(){
        return 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        // canvas.drawRect(0.0f, 0.0f, 100.0f, 100.0f, p);
    }
}
