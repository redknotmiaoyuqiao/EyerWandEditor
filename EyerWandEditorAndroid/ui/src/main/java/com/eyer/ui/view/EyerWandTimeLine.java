package com.eyer.ui.view;

import com.eyer.eyer_wand_editor_lib.EyerWandNative;
import com.eyer.eyer_wand_editor_lib.base.EyerWandObject;
import com.eyer.eyer_wand_editor_lib.eyerwand.EyerWandContext;
import com.eyer.ui.draw.EyerWandDrawEventList;

public class EyerWandTimeLine extends EyerWandObject {

    public EyerWandTimeLine(){
        nativeId = EyerWandNative.wand_view_timeline_init();
    }

    public int draw(EyerWandDrawEventList eventList){
        return EyerWandNative.wand_view_timeline_draw(nativeId, eventList.getNativeId());
    }

    public int setWH(float w, float h){
        return EyerWandNative.wand_view_timeline_set_wh(nativeId, w, h);
    }

    public int setWH(int w, int h){
        return setWH(w * 1.0f, h * 1.0f);
    }

    public int setTouchUp(float x, float y) {
        return EyerWandNative.wand_view_timeline_on_touch_up(nativeId, x, y);
    }

    public int setTouchDown(float x, float y) {
        return EyerWandNative.wand_view_timeline_on_touch_down(nativeId, x, y);
    }

    public int setTouchMove(float x, float y) {
        return EyerWandNative.wand_view_timeline_on_touch_move(nativeId, x, y);
    }

    public void SetWandContext(EyerWandContext wandContext){
        if(wandContext != null){
            EyerWandNative.wand_view_timeline_set_wand_ctx(nativeId, wandContext.getNativeId());
        }
        else{
            EyerWandNative.wand_view_timeline_set_wand_ctx(nativeId, 0L);
        }
    }

    @Override
    public int destory() {
        if(nativeId != 0L){
            EyerWandNative.wand_view_timeline_uninit(nativeId);
            nativeId = 0L;
        }

        return 0;
    }
}
