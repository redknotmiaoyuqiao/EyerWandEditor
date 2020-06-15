package com.eyer.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.eyer.eyer_wand_editor_lib.eyerwand.EyerWandContext;
import com.eyer.eyer_wand_editor_lib.math.Vec2;
import com.eyer.eyer_wand_editor_lib.math.Vec4;
import com.eyer.eyer_wand_editor_lib.math.WandVec4;
import com.eyer.ui.R;
import com.eyer.ui.draw.EyerWandDrawEventList;
import com.eyer.ui.draw.EyerWandDrawEventType;
import com.eyer.ui.draw.EyerWandDrawEvent_Bitmap;
import com.eyer.ui.draw.EyerWandDrawEvent_Line;
import com.eyer.ui.draw.EyerWandDrawEvent_Rect;

public class EyerWandTimeLineView extends View {

    private EyerWandTimeLine timeLine = null;

    private Bitmap bitmap = null;

    public EyerWandTimeLineView(Context context) {
        super(context);
        init();
    }

    public EyerWandTimeLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EyerWandTimeLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void destory() {
        if(timeLine != null){
            timeLine.destory();
            timeLine = null;
        }
    }

    public void SetWandContext(EyerWandContext wandContext){
        timeLine.SetWandContext(wandContext);
    }
    public void UnsetWandContext(){
        timeLine.SetWandContext(null);
    }

    private void init() {
        timeLine = new EyerWandTimeLine();
        setOnTouchListener(new MyOnTouchListener());

        bitmap = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.miaowu);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        EyerWandDrawEventList eventList = new EyerWandDrawEventList();

        timeLine.setWH(canvas.getWidth(), canvas.getHeight());

        timeLine.draw(eventList);



        int count = eventList.getCount();
        // Log.e("EyerWandTimeLineView", "Event Count: " + count);

        for(int i=0;i<count;i++){
            int type = eventList.getEventType(i);

            if(type == EyerWandDrawEventType.UNKNOW){
                // Log.e("EyerWandTimeLineView", "Event Type: Unknow");
            }
            if(type == EyerWandDrawEventType.LINE){
                // Log.e("EyerWandTimeLineView", "Event Type: Line");

                EyerWandDrawEvent_Line line = new EyerWandDrawEvent_Line();
                int ret = eventList.getEvent_Line(i, line);

                if(ret == 0) {
                    // Log.e("EyerWandTimeLineView", "Get Line Success");

                    Vec4 color = line.getColor();

                    Paint p = new Paint();
                    int c = Color.argb((int)(color.getW() * 255), (int)(color.getX() * 255), (int)(color.getY() * 255), (int)(color.getZ() * 255));
                    p.setColor(c);
                    p.setStrokeWidth(line.getStrokeWidth());

                    Vec2 start = line.getStart();
                    Vec2 end = line.getEnd();

                    canvas.drawLine(start.getX(), start.getY(), end.getX(), end.getY(), p);
                }

                line.destory();
            }
            if(type == EyerWandDrawEventType.RECT){
                // Log.e("EyerWandTimeLineView", "Event Type: Rect");

                EyerWandDrawEvent_Rect rect = new EyerWandDrawEvent_Rect();
                int ret = eventList.getEvent_Rect(i, rect);

                if(ret == 0){
                    // Log.e("EyerWandTimeLineView", "Get Rect Success");

                    Vec4 color = rect.getColor();

                    Paint p = new Paint();
                    int c = Color.argb((int)(color.getW() * 255), (int)(color.getX() * 255), (int)(color.getY() * 255), (int)(color.getZ() * 255));
                    p.setColor(c);

                    Vec2 start = rect.getStart();
                    Vec2 end = rect.getEnd();

                    canvas.drawRect(start.getX(), start.getY(), end.getX(), end.getY(), p);
                }

                rect.destory();
            }

            if(type == EyerWandDrawEventType.BITMAP){
                // Log.e("EyerWandTimeLineView", "Event Type: BITMAP");
                // canvas.drawBitmap();
                EyerWandDrawEvent_Bitmap bitmapEvent = new EyerWandDrawEvent_Bitmap();
                int ret = eventList.getEvent_Bitmap(i, bitmapEvent);

                if(ret == 0) {
                    // Log.e("EyerWandTimeLineView", "Get Bitmap Success");
                    Paint p = new Paint();

                    WandVec4 distVec4 = new WandVec4();
                    bitmapEvent.getDist(distVec4);

                    Rect dist = new Rect((int)distVec4.x(), (int)distVec4.y(), (int)distVec4.z(), (int)distVec4.w());
                    canvas.drawBitmap(bitmap, null, dist, p);
                }
            }
        }

        eventList.destory();
    }

    private class MyOnTouchListener implements OnTouchListener{
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();

            if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                timeLine.setTouchUp(x, y);
            }
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                timeLine.setTouchDown(x, y);
            }
            if(motionEvent.getAction() == MotionEvent.ACTION_MOVE){
                timeLine.setTouchMove(x, y);
            }

            invalidate();
            return true;
        }
    }
}
