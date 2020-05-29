package com.eyer.eyer_wand_editor_lib.ogl;

import android.view.Surface;

import com.eyer.eyer_wand_editor_lib.EyerWandNative;
import com.eyer.eyer_wand_editor_lib.base.EyerWandObject;
import com.eyer.eyer_wand_editor_lib.rendertask.EyerGLRenderTask;

public class EyerGLCtxThread extends EyerWandObject {

    public EyerGLCtxThread(Surface surface){
        nativeId = EyerWandNative.ogl_create_thread(surface);
    }

    public int stop(){
        int ret = 0;
        if(nativeId != 0L){
            ret = EyerWandNative.ogl_stop_thread(nativeId);
            nativeId = 0L;
        }
        return ret;
    }

    public int setWH(int w, int h){
        return EyerWandNative.ogl_set_wh(nativeId, w, h);
    }

    public int addTaskToRenderQueue(EyerGLRenderTask task){
        return EyerWandNative.ogl_add_task_to_render_queue(nativeId, task.getNativeId());
    }

    public int addTaskToDestoryQueue(EyerGLRenderTask task){
        return EyerWandNative.ogl_add_task_to_destory_queue(nativeId, task.getNativeId());
    }

    @Override
    public int destory() {
        stop();
        return 0;
    }
}
