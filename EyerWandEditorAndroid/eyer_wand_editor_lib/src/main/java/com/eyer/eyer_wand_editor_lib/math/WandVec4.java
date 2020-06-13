package com.eyer.eyer_wand_editor_lib.math;

import com.eyer.eyer_wand_editor_lib.EyerWandNative;
import com.eyer.eyer_wand_editor_lib.base.EyerWandObject;

public class WandVec4 extends EyerWandObject {

    public WandVec4(){
        nativeId = EyerWandNative.wand_vec4_init();
    }

    @Override
    public int destory() {
        EyerWandNative.wand_vec4_uninit(nativeId);
        return 0;
    }

    public float x(){
        return EyerWandNative.wand_vec4_get_x(nativeId);
    }

    public float y(){
        return EyerWandNative.wand_vec4_get_y(nativeId);
    }

    public float z(){
        return EyerWandNative.wand_vec4_get_z(nativeId);
    }

    public float w(){
        return EyerWandNative.wand_vec4_get_w(nativeId);
    }
}
