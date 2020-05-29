package com.eyer.ui.view;

import com.eyer.eyer_wand_editor_lib.EyerWandNative;
import com.eyer.eyer_wand_editor_lib.base.EyerWandObject;
import com.eyer.ui.draw.EyerWandDrawEventList;

public class EyerWandTimeLine extends EyerWandObject {

    public EyerWandTimeLine(){
        nativeId = EyerWandNative.wand_view_timeline_init();
    }

    public int draw(EyerWandDrawEventList eventList){
        return EyerWandNative.wand_view_timeline_draw(nativeId, eventList.getNativeId());
    }

    public int setWH(float w, float h){
        return EyerWandNative.wand_view_timeline_set_wh(nativeId, w, h);
    }

    public int setWH(int w, int h){
        return setWH(w * 1.0f, h * 1.0f);
    }

    @Override
    public int destory() {
        if(nativeId != 0L){
            EyerWandNative.wand_view_timeline_uninit(nativeId);
            nativeId = 0L;
        }

        return 0;
    }
}
