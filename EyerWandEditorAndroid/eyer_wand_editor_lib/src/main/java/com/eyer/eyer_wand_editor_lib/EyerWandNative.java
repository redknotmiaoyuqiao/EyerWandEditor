package com.eyer.eyer_wand_editor_lib;

public class EyerWandNative {

    static {
        System.loadLibrary("EyerWandJni");
    }

    public static native String getVersion();
}
