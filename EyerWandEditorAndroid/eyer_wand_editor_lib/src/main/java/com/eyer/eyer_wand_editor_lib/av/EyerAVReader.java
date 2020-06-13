package com.eyer.eyer_wand_editor_lib.av;

import com.eyer.eyer_wand_editor_lib.EyerWandNative;
import com.eyer.eyer_wand_editor_lib.base.EyerWandObject;

public class EyerAVReader extends EyerWandObject {

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
        if(nativeId != 0L){
            EyerWandNative.avreader_uninit(nativeId);
            nativeId = 0L;
        }

        return 0;
    }
}
