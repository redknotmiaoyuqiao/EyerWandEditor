package com.eyer.eyer_wand_editor_lib.ogl;

import android.view.Surface;

import com.eyer.eyer_wand_editor_lib.EyerWandNative;
import com.eyer.eyer_wand_editor_lib.base.EyerWandObject;

public class EyerGLCtxThread implements EyerWandObject {

    private long nativeId = 0;

    public EyerGLCtxThread(Surface surface){
        nativeId = EyerWandNative.ogl_create_thread(surface);
    }

    public int stop(){
        int ret = EyerWandNative.ogl_stop_thread(nativeId);
        nativeId = 0;
        return ret;
    }

    public int setWH(int w, int h){
        return EyerWandNative.ogl_set_wh(nativeId, w, h);
    }

    @Override
    public int destory() {
        return 0;
    }
}
