package com.eyer.eyer_wand_editor_lib.eyerwand;

import com.eyer.eyer_wand_editor_lib.EyerWandNative;
import com.eyer.eyer_wand_editor_lib.base.EyerWandObject;
import com.eyer.eyer_wand_editor_lib.ogl.EyerGLCtxThread;

public class EyerWandContext implements EyerWandObject {

    private long nativeId = 0;

    public EyerWandContext(int width, int height, int fps) {
        nativeId = EyerWandNative.wand_context_init(width, height, fps);
    }

    public int setGL(EyerGLCtxThread glCtx){
        return EyerWandNative.wand_context_set_gl_ctx(nativeId, glCtx.nativeId);
    }

    public int renderFrame(double time){
        return EyerWandNative.wand_context_render_frame(nativeId, time);
    }

    public int renderFrameByIndex(int frameIndex){
        return EyerWandNative.wand_context_render_frame_index(nativeId, frameIndex);
    }

    @Override
    public int destory() {
        EyerWandNative.wand_context_uninit(nativeId);
        return 0;
    }
}
