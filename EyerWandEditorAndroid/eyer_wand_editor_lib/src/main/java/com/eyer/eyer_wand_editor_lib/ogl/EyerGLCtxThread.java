package com.eyer.eyer_wand_editor_lib.ogl;

import android.view.Surface;

import com.eyer.eyer_wand_editor_lib.EyerWandNative;
import com.eyer.eyer_wand_editor_lib.base.EyerWandObject;
import com.eyer.eyer_wand_editor_lib.rendertask.EyerGLRenderTask;

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

    public int addTaskToRenderQueue(EyerGLRenderTask task){
        return EyerWandNative.ogl_add_task_to_render_queue(nativeId, task.nativeId);
    }

    @Override
    public int destory() {
        return 0;
    }
}
