package com.eyer.eyer_wand_editor_lib.math;

import com.eyer.eyer_wand_editor_lib.EyerWandNative;
import com.eyer.eyer_wand_editor_lib.base.EyerWandObject;

public class WandVec3 extends EyerWandObject {

    public WandVec3(){
        nativeId = EyerWandNative.wand_vec3_init();
    }

    @Override
    public int destory() {
        EyerWandNative.wand_vec3_uninit(nativeId);
        return 0;
    }

    public float x(){
        return EyerWandNative.wand_vec3_get_x(nativeId);
    }

    public float y(){
        return EyerWandNative.wand_vec3_get_y(nativeId);
    }

    public float z(){
        return EyerWandNative.wand_vec3_get_z(nativeId);
    }
}
