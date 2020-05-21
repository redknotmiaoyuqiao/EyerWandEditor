package com.eyer.ui.view;

import com.eyer.eyer_wand_editor_lib.EyerWandNative;
import com.eyer.eyer_wand_editor_lib.base.EyerWandObject;

public class EyerWandTimeLine implements EyerWandObject {

    private long nativeId = 0;

    public EyerWandTimeLine(){
        nativeId = EyerWandNative.wand_view_timeline_init();
    }

    public int draw(){
        return EyerWandNative.wand_view_timeline_draw(nativeId);
    }

    @Override
    public int destory() {
        EyerWandNative.wand_view_timeline_uninit(nativeId);
        return 0;
    }
}
