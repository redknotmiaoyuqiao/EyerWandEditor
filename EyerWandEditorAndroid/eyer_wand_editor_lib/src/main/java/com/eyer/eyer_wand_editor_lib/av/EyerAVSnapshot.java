package com.eyer.eyer_wand_editor_lib.av;

import android.graphics.Bitmap;

import com.eyer.eyer_wand_editor_lib.EyerWandNative;
import com.eyer.eyer_wand_editor_lib.base.EyerWandObject;
import com.eyer.eyer_wand_editor_lib.math.WandVec2;

public class EyerAVSnapshot extends EyerWandObject {

    public EyerAVSnapshot(String url){
        nativeId = EyerWandNative.wand_snapshot_init(url);
    }

    public Bitmap snapshot(double time, Bitmap bitmap){
        int ret = EyerWandNative.wand_snapshot_bitmap(nativeId, time, bitmap);
        if(ret != 0){
            return null;
        }
        return bitmap;
    }

    public int getWH(WandVec2 vec2) {
        return EyerWandNative.wand_snapshot_get_wh(nativeId, vec2.getNativeId());
    }

    @Override
    public int destory() {
        if(nativeId != 0L){
            EyerWandNative.wand_snapshot_uninit(nativeId);
            nativeId = 0L;
        }

        return 0;
    }
}
