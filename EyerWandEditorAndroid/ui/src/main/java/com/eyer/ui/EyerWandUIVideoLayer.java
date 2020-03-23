package com.eyer.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class EyerWandUIVideoLayer extends View {

    private int startPos = 0;

    public EyerWandUIVideoLayer(Context context) {
        super(context);
        init();
    }

    public EyerWandUIVideoLayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EyerWandUIVideoLayer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public EyerWandUIVideoLayer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        setOnTouchListener(new MyOnTouchListener());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        // Log.e("EyerWandUIVideoLayer", "Width:" + width + " Height:" + height);

        Paint paint = new Paint();
        paint.setColor(Color.RED);

        float p1x = 0 + startPos;
        float p1y = 0;
        float p2x = width + startPos;
        float p2y = height;

        canvas.drawLine(p1x, p1y, p2x, p2y, paint);
    }

    private class MyOnTouchListener implements OnTouchListener{

        private float lastX = -1.0f;

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                // Log.e("EyerWandUIVideoLayer", "ACTION_DOWN");
                lastX = -1.0f;
            }
            if(event.getAction() == MotionEvent.ACTION_UP){
                // Log.e("EyerWandUIVideoLayer", "ACTION_UP");
                lastX = -1.0f;
            }
            if(event.getAction() == MotionEvent.ACTION_MOVE){
                // Log.e("EyerWandUIVideoLayer", "ACTION_MOVE===x:" + event.getX() + ", y:" + event.getY());
                if(lastX >= 0.0f){
                    float d = event.getX() - lastX;
                    startPos += d;
                }

                lastX = event.getX();
            }

            invalidate();

            return true;
        }
    }
}
