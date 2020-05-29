package com.eyer.eyer_wand_editor_lib.rendertask;

import com.eyer.eyer_wand_editor_lib.EyerWandNative;
import com.eyer.eyer_wand_editor_lib.base.EyerWandObject;

public class EyerGLRenderTask extends EyerWandObject {

    public EyerGLRenderTask(String name){
        nativeId = EyerWandNative.render_task_init(name);
    }

    @Override
    public int destory() {
        if(nativeId != 0){
            int ret = EyerWandNative.render_task_uninit(nativeId);
            nativeId = 0;
        }

        return 0;
    }
}
