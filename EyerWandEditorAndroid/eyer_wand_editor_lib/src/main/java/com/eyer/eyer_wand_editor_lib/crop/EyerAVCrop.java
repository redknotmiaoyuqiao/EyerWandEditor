package com.eyer.eyer_wand_editor_lib.crop;

import com.eyer.eyer_wand_editor_lib.EyerWandNative;
import com.eyer.eyer_wand_editor_lib.base.EyerWandObject;

public class EyerAVCrop implements EyerWandObject {

    private long nativeId = 0;

    public EyerAVCrop(int viewW, int viewH, int imageW, int imageH) {
        nativeId = EyerWandNative.crop_init(viewW, viewH, imageW, imageH);
    }

    @Override
    public int destory() {
        EyerWandNative.crop_uninit(nativeId);
        nativeId = 0;
        return 0;
    }

    public int crop(EyerAVCropType type) {
        return EyerWandNative.crop_crop(nativeId, type.getId());
    }

    public int getCropWidth(){
        return EyerWandNative.crop_get_crop_width(nativeId);
    }

    public int getCropHeight(){
        return EyerWandNative.crop_get_crop_height(nativeId);
    }
}
