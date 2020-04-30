package com.eyer.eyer_wand_editor_lib.eyerwand;

import com.eyer.eyer_wand_editor_lib.base.EyerWandObject;

public class EyerVideoLayer {

    private int startFrameIndex = 0;
    private int endFrameIndex = 0;

    public EyerVideoLayer(int startFrameIndex, int endFrameIndex){
        setFrame(startFrameIndex, endFrameIndex);
    }

    public int setFrame(int startFrameIndex, int endFrameIndex) {
        this.startFrameIndex = startFrameIndex;
        this.endFrameIndex = endFrameIndex;
        return 0;
    }
}
