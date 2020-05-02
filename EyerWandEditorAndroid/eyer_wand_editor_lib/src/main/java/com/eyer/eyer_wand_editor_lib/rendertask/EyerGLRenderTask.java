package com.eyer.eyer_wand_editor_lib.rendertask;

import com.eyer.eyer_wand_editor_lib.EyerWandNative;
import com.eyer.eyer_wand_editor_lib.base.EyerWandObject;

public class EyerGLRenderTask implements EyerWandObject {

    public long nativeId = 0;

    public EyerGLRenderTask(String name){
        nativeId = EyerWandNative.render_task_init(name);
    }

    @Override
    public int destory() {
        int ret = EyerWandNative.render_task_uninit(nativeId);
        nativeId = 0;
        return ret;
    }
}
