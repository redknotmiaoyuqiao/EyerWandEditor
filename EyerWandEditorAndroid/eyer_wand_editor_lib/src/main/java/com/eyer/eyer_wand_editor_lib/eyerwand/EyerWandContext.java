package com.eyer.eyer_wand_editor_lib.eyerwand;

import com.eyer.eyer_wand_editor_lib.EyerWandNative;
import com.eyer.eyer_wand_editor_lib.base.EyerWandObject;
import com.eyer.eyer_wand_editor_lib.ogl.EyerGLCtxThread;

public class EyerWandContext extends EyerWandObject {

    public EyerWandContext(int width, int height, int fps) {
        nativeId = EyerWandNative.wand_context_init(width, height, fps);
    }

    public int setGL(EyerGLCtxThread glCtx){
        return EyerWandNative.wand_context_set_gl_ctx(nativeId, glCtx.getNativeId());
    }

    public int renderFrameByIndex(int frameIndex){
        return EyerWandNative.wand_context_render_frame_index(nativeId, frameIndex);
    }

    @Override
    public int destory() {
        if(nativeId != 0L){
            EyerWandNative.wand_context_uninit(nativeId);
            nativeId = 0L;
        }

        return 0;
    }
}
