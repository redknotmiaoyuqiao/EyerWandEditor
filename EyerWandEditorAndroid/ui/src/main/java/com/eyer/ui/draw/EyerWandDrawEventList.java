package com.eyer.ui.draw;

import com.eyer.eyer_wand_editor_lib.EyerWandNative;
import com.eyer.eyer_wand_editor_lib.base.EyerWandObject;

public class EyerWandDrawEventList extends EyerWandObject {

    public EyerWandDrawEventList(){
        nativeId = EyerWandNative.wand_view_draw_event_list_init();
    }

    public int getCount() {
        return EyerWandNative.wand_view_draw_event_list_size(nativeId);
    }

    public int getEventType(int index){
        return EyerWandNative.wand_view_draw_event_list_get_event_type(nativeId, index);
    }

    public EyerWandDrawEvent_Rect getEvent_Rect(int index)
    {
        EyerWandDrawEvent_Rect rect = new EyerWandDrawEvent_Rect();

        EyerWandNative.wand_view_draw_event_list_get_rect_event(nativeId, rect.getNativeId(), index);

        return rect;
    }

    public int getEvent_Rect(int index, EyerWandDrawEvent_Rect rect)
    {
        return EyerWandNative.wand_view_draw_event_list_get_rect_event(nativeId, rect.getNativeId(), index);
    }

    public int getEvent_Line(int index, EyerWandDrawEvent_Line line)
    {
        return EyerWandNative.wand_view_draw_event_list_get_line_event(nativeId, line.getNativeId(), index);
    }

    public int getEvent_Bitmap(int index, EyerWandDrawEvent_Bitmap bitmap)
    {
        return EyerWandNative.wand_view_draw_event_list_get_bitmap_event(nativeId, bitmap.getNativeId(), index);
    }

    @Override
    public int destory() {
        if(nativeId != 0){
            EyerWandNative.wand_view_draw_event_list_uninit(nativeId);
            nativeId = 0;
        }
        return 0;
    }
}
