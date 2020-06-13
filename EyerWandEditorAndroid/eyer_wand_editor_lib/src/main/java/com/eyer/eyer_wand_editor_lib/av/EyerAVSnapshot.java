package com.eyer.eyer_wand_editor_lib.av;

import android.graphics.Bitmap;

import com.eyer.eyer_wand_editor_lib.EyerWandNative;
import com.eyer.eyer_wand_editor_lib.base.EyerWandObject;

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

    @Override
    public int destory() {
        if(nativeId != 0L){
            EyerWandNative.wand_snapshot_uninit(nativeId);
            nativeId = 0L;
        }

        return 0;
    }
}
