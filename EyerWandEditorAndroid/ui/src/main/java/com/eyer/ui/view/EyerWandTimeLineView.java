package com.eyer.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.eyer.eyer_wand_editor_lib.math.Vec2;
import com.eyer.eyer_wand_editor_lib.math.Vec4;
import com.eyer.ui.draw.EyerWandDrawEventList;
import com.eyer.ui.draw.EyerWandDrawEventType;
import com.eyer.ui.draw.EyerWandDrawEvent_Line;
import com.eyer.ui.draw.EyerWandDrawEvent_Rect;

public class EyerWandTimeLineView extends View {

    private EyerWandTimeLine timeLine = null;

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

    private void init() {
        timeLine = new EyerWandTimeLine();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        EyerWandDrawEventList eventList = new EyerWandDrawEventList();

        timeLine.setWH(canvas.getWidth(), canvas.getHeight());

        timeLine.draw(eventList);



        int count = eventList.getCount();
        Log.e("EyerWandTimeLineView", "Event Count: " + count);

        for(int i=0;i<count;i++){
            int type = eventList.getEventType(i);

            if(type == EyerWandDrawEventType.UNKNOW){
                Log.e("EyerWandTimeLineView", "Event Type: Unknow");
            }
            if(type == EyerWandDrawEventType.LINE){
                Log.e("EyerWandTimeLineView", "Event Type: Line");

                EyerWandDrawEvent_Line line = new EyerWandDrawEvent_Line();
                int ret = eventList.getEvent_Line(i, line);

                if(ret == 0) {
                    Log.e("EyerWandTimeLineView", "Get Line Success");

                    Vec4 color = line.getColor();

                    Paint p = new Paint();
                    int c = Color.argb((int)(color.getW() * 255), (int)(color.getX() * 255), (int)(color.getY() * 255), (int)(color.getZ() * 255));
                    p.setColor(c);

                    Vec2 start = line.getStart();
                    Vec2 end = line.getEnd();

                    canvas.drawLine(start.getX(), start.getY(), end.getX(), end.getY(), p);
                }

                line.destory();
            }
            if(type == EyerWandDrawEventType.RECT){
                Log.e("EyerWandTimeLineView", "Event Type: Rect");

                EyerWandDrawEvent_Rect rect = new EyerWandDrawEvent_Rect();
                int ret = eventList.getEvent_Rect(i, rect);

                if(ret == 0){
                    Log.e("EyerWandTimeLineView", "Get Rect Success");

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
        }

        eventList.destory();
    }
}
