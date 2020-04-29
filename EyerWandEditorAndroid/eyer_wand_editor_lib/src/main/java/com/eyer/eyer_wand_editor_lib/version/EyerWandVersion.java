package com.eyer.eyer_wand_editor_lib.version;

import com.eyer.eyer_wand_editor_lib.EyerWandNative;

public class EyerWandVersion {
    public static String getVersion(){
        return EyerWandNative.get_version();
    }
}
