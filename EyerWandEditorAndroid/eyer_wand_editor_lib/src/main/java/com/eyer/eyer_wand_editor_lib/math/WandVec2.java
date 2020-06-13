package com.eyer.eyer_wand_editor_lib.math;

import com.eyer.eyer_wand_editor_lib.EyerWandNative;
import com.eyer.eyer_wand_editor_lib.base.EyerWandObject;

public class WandVec2 extends EyerWandObject {

    public WandVec2() {
        nativeId = EyerWandNative.wand_vec2_init();
    }

    @Override
    public int destory() {
        EyerWandNative.wand_vec2_uninit(nativeId);
        return 0;
    }

    public float x(){
        return EyerWandNative.wand_vec2_get_x(nativeId);
    }

    public float y(){
        return EyerWandNative.wand_vec2_get_y(nativeId);
    }
}
