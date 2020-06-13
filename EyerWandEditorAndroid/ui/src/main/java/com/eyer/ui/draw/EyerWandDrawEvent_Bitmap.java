package com.eyer.ui.draw;

import com.eyer.eyer_wand_editor_lib.EyerWandNative;
import com.eyer.eyer_wand_editor_lib.base.EyerWandObject;
import com.eyer.eyer_wand_editor_lib.math.WandVec4;

public class EyerWandDrawEvent_Bitmap extends EyerWandObject {

    public EyerWandDrawEvent_Bitmap(){
        nativeId = EyerWandNative.wand_view_draw_event_bitmap_init();
    }

    public int getSrc(WandVec4 src)
    {
        return EyerWandNative.wand_view_draw_event_bitmap_get_src(nativeId, src.getNativeId());
    }

    public int getDist(WandVec4 dist)
    {
        return EyerWandNative.wand_view_draw_event_bitmap_get_dist(nativeId, dist.getNativeId());
    }

    @Override
    public int destory() {
        if(nativeId != 0L) {
            EyerWandNative.wand_view_draw_event_bitmap_uninit(nativeId);
            nativeId = 0L;
        }
        return 0;
    }
}
