package com.eyer.ui.draw;

import com.eyer.eyer_wand_editor_lib.EyerWandNative;
import com.eyer.eyer_wand_editor_lib.base.EyerWandObject;
import com.eyer.eyer_wand_editor_lib.math.Vec2;
import com.eyer.eyer_wand_editor_lib.math.Vec4;

public class EyerWandDrawEvent_Text extends EyerWandObject {

    public EyerWandDrawEvent_Text(){
        nativeId = EyerWandNative.wand_view_draw_event_text_init();
    }

    public Vec2 getStart() {
        float x = EyerWandNative.wand_view_draw_event_text_get_start_x(nativeId);
        float y = EyerWandNative.wand_view_draw_event_text_get_start_y(nativeId);

        Vec2 start = new Vec2(x, y);

        return start;
    }

    public Vec4 getColor() {
        float r = EyerWandNative.wand_view_draw_event_text_get_color_r(nativeId);
        float g = EyerWandNative.wand_view_draw_event_text_get_color_g(nativeId);
        float b = EyerWandNative.wand_view_draw_event_text_get_color_b(nativeId);
        float a = EyerWandNative.wand_view_draw_event_text_get_color_a(nativeId);

        Vec4 color = new Vec4(r, g, b, a);

        return color;
    }

    public String getTextContent(){
        String textContent = EyerWandNative.wand_view_draw_event_text_get_text(nativeId);
        return textContent;
    }

    public float getSize(){
        return EyerWandNative.wand_view_draw_event_text_get_size(nativeId);
    }

    @Override
    public int destory() {
        if(nativeId != 0L) {
            EyerWandNative.wand_view_draw_event_text_uninit(nativeId);
            nativeId = 0L;
        }
        return 0;
    }
}
