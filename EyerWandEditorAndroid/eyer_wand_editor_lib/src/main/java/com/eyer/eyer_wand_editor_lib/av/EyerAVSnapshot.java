package com.eyer.eyer_wand_editor_lib.av;

import android.graphics.Bitmap;

import com.eyer.eyer_wand_editor_lib.EyerWandNative;
import com.eyer.eyer_wand_editor_lib.base.EyerWandObject;

public class EyerAVSnapshot implements EyerWandObject {

    private long nativeId = 0;

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

    @Override
    public int destory() {
        EyerWandNative.wand_snapshot_uninit(nativeId);
        return 0;
    }
}
