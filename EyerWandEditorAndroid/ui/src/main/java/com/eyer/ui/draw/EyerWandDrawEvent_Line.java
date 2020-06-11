package com.eyer.ui.draw;

import com.eyer.eyer_wand_editor_lib.EyerWandNative;
import com.eyer.eyer_wand_editor_lib.base.EyerWandObject;
import com.eyer.eyer_wand_editor_lib.math.Vec2;
import com.eyer.eyer_wand_editor_lib.math.Vec4;

public class EyerWandDrawEvent_Line extends EyerWandObject {

    public EyerWandDrawEvent_Line(){
        nativeId = EyerWandNative.wand_view_draw_event_line_init();
    }

    public Vec2 getStart() {
        float x = EyerWandNative.wand_view_draw_event_line_get_start_x(nativeId);
        float y = EyerWandNative.wand_view_draw_event_line_get_start_y(nativeId);

        Vec2 start = new Vec2(x, y);

        return start;
    }

    public Vec2 getEnd() {
        float x = EyerWandNative.wand_view_draw_event_line_get_end_x(nativeId);
        float y = EyerWandNative.wand_view_draw_event_line_get_end_y(nativeId);

        Vec2 end = new Vec2(x, y);

        return end;
    }

    public Vec4 getColor() {
        float r = EyerWandNative.wand_view_draw_event_line_get_color_r(nativeId);
        float g = EyerWandNative.wand_view_draw_event_line_get_color_g(nativeId);
        float b = EyerWandNative.wand_view_draw_event_line_get_color_b(nativeId);
        float a = EyerWandNative.wand_view_draw_event_line_get_color_a(nativeId);

        Vec4 color = new Vec4(r, g, b, a);

        return color;
    }

    public int getStrokeWidth(){
        return EyerWandNative.wand_view_draw_event_line_get_strokewidth(nativeId);
    }

    @Override
    public int destory() {
        if(nativeId != 0L){
            EyerWandNative.wand_view_draw_event_line_uninit(nativeId);
            nativeId = 0L;
        }
        return 0;
    }
}
