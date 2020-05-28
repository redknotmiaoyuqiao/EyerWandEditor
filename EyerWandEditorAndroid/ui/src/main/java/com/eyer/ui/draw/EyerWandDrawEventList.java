package com.eyer.ui.draw;

import com.eyer.eyer_wand_editor_lib.EyerWandNative;
import com.eyer.eyer_wand_editor_lib.base.EyerWandObject;

public class EyerWandDrawEventList implements EyerWandObject {

    private long nativeId = 0;

    public EyerWandDrawEventList(){
        nativeId = EyerWandNative.wand_view_draw_event_list_init();
    }

    public int getCount() {
        return EyerWandNative.wand_view_draw_event_list_size(nativeId);
    }

    public int getEventType(int index){
        return EyerWandNative.wand_view_draw_event_list_get_event_type(nativeId, index);
    }

    @Override
    public int destory() {
        if(nativeId != 0){
            EyerWandNative.wand_view_draw_event_list_uninit(nativeId);
            nativeId = 0;
        }
        return 0;
    }

    public long getNativeId(){
        return nativeId;
    }
}
