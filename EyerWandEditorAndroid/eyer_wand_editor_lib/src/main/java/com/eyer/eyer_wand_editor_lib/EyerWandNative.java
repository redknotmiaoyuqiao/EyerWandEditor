package com.eyer.eyer_wand_editor_lib;

public class EyerWandNative {

    static {
        System.loadLibrary("EyerWandJni");
    }

    public static native String                 get_version();

    // Crop Native Function
    public static native long                   crop_init                                   (int viewW, int viewH, int imageW, int imageH);
    public static native int                    crop_uninit                                 (long crop);
    public static native int                    crop_get_crop_width                         (long crop);
    public static native int                    crop_get_crop_height                        (long crop);
    public static native int                    crop_crop                                   (long crop, int crop_type);

}
