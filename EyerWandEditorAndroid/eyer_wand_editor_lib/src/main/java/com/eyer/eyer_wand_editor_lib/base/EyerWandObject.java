package com.eyer.eyer_wand_editor_lib.base;

public abstract class EyerWandObject {

    protected long nativeId = 0;

    public long getNativeId()
    {
        return nativeId;
    }

    public abstract int destory();
}
