package com.eyer.eyer_wand_editor_lib.crop;

public enum EyerAVCropType {
    FIT_CENTER (0),
    FIT_XY (1);

    private int type = 0;
    private EyerAVCropType(int type) {
        this.type = type;
    }

    public int getId(){
        return type;
    }
}
