package com.eyer.ui.view;

import com.eyer.eyer_wand_editor_lib.EyerWandNative;
import com.eyer.eyer_wand_editor_lib.base.EyerWandObject;
import com.eyer.ui.draw.EyerWandDrawEventList;

public class EyerWandTimeLine implements EyerWandObject {

    private long nativeId = 0;

    public EyerWandTimeLine(){
        nativeId = EyerWandNative.wand_view_timeline_init();
    }

    public int draw(EyerWandDrawEventList eventList){
        return EyerWandNative.wand_view_timeline_draw(nativeId, eventList.getNativeId());
    }

    @Override
    public int destory() {
        EyerWandNative.wand_view_timeline_uninit(nativeId);
        return 0;
    }
}
