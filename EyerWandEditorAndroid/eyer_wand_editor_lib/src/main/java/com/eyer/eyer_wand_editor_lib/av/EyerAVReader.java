package com.eyer.eyer_wand_editor_lib.av;

import com.eyer.eyer_wand_editor_lib.EyerWandNative;
import com.eyer.eyer_wand_editor_lib.base.EyerWandObject;

public class EyerAVReader implements EyerWandObject {

    private long nativeId = 0;

    public EyerAVReader(String url){
        nativeId = EyerWandNative.avreader_init(url);
    }

    public int open(){
        return EyerWandNative.avreader_open(nativeId);
    }

    public int close(){
        return EyerWandNative.avreader_close(nativeId);
    }

    public int printInfo(){
        return EyerWandNative.avreader_print_info(nativeId);
    }

    @Override
    public int destory() {
        EyerWandNative.avreader_uninit(nativeId);
        return 0;
    }
}
