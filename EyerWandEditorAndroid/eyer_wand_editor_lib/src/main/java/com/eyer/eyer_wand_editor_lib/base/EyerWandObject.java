package com.eyer.eyer_wand_editor_lib.base;

public abstract class EyerWandObject {

    protected long nativeId = 0;

    public long getNativeId()
    {
        return nativeId;
    }

    public abstract int destory();


    public static int sleep(long t){
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
