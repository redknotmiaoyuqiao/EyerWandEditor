package com.eyer.ui.draw;

import com.eyer.eyer_wand_editor_lib.EyerWandNative;
import com.eyer.eyer_wand_editor_lib.base.EyerWandObject;
import com.eyer.eyer_wand_editor_lib.math.WandVec4;

public class EyerWandDrawEvent_BitmapSnapshot extends EyerWandObject {

    public EyerWandDrawEvent_BitmapSnapshot(){
        nativeId = EyerWandNative.wand_view_draw_event_bitmap_snapshot_init();
    }

    public int getSrc(WandVec4 src)
    {
        return EyerWandNative.wand_view_draw_event_bitmap_snapshot_get_src(nativeId, src.getNativeId());
    }

    public int getDist(WandVec4 dist)
    {
        return EyerWandNative.wand_view_draw_event_bitmap_snapshot_get_dist(nativeId, dist.getNativeId());
    }

    public String getPath(){
        return EyerWandNative.wand_view_draw_event_bitmap_snapshot_get_path(nativeId);
    }

    public double getTime(){
        return EyerWandNative.wand_view_draw_event_bitmap_snapshot_get_time(nativeId);
    }

    @Override
    public int destory() {
        if(nativeId != 0L) {
            EyerWandNative.wand_view_draw_event_bitmap_snapshot_uninit(nativeId);
            nativeId = 0L;
        }
        return 0;
    }
}
