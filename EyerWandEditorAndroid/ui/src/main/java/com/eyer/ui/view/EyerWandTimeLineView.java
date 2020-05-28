package com.eyer.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.eyer.ui.draw.EyerWandDrawEventList;

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

        timeLine.draw(eventList);


        int count = eventList.getCount();
        Log.e("EyerWandTimeLineView", "Event Count: " + count);

        for(int i=0;i<count;i++){
            int type = eventList.getEventType(i);

            Log.e("EyerWandTimeLineView", "Event Type: " + type);
        }

        eventList.destory();
    }
}
