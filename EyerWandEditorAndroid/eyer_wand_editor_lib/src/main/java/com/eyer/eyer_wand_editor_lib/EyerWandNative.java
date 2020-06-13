package com.eyer.eyer_wand_editor_lib;

import android.graphics.Bitmap;

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

    // OpenGL
    public static native long                   ogl_create_thread                           (android.view.Surface surface);
    public static native int                    ogl_stop_thread                             (long thread);
    public static native int                    ogl_set_wh                                  (long thread, int w, int h);
    public static native int                    ogl_add_task_to_render_queue                (long thread, long render_task);
    public static native int                    ogl_add_task_to_destory_queue               (long thread, long render_task);

    // Render Task
    public static native long                   render_task_init                            (String name);
    public static native int                    render_task_uninit                          (long render_task);

    // AV Reader
    public static native long                   avreader_init                               (String url);
    public static native int                    avreader_uninit                             (long avreader);
    public static native int                    avreader_open                               (long avreader);
    public static native int                    avreader_close                              (long avreader);
    public static native int                    avreader_print_info                         (long avreader);

    // Eyer Wand Context
    public static native long                   wand_context_init                           (int w, int h, int fps);
    public static native int                    wand_context_uninit                         (long wand_ctx);
    public static native int                    wand_context_set_gl_ctx                     (long wand_ctx, long thread);
    public static native int                    wand_context_update_screen_wh               (long wand_ctx, int screenW, int screenH);
    public static native int                    wand_context_render_frame_index             (long wand_ctx, int frame_index);

    // Snapshot
    public static native long                   wand_snapshot_init                          (String url);
    public static native int                    wand_snapshot_uninit                        (long snapshot);
    public static native int                    wand_snapshot_bitmap                        (long snapshot, double time, Bitmap bitmap);


    // TimeLine
    public static native long                   wand_view_timeline_init                     ();
    public static native int                    wand_view_timeline_uninit                   (long timeline);
    public static native int                    wand_view_timeline_set_wh                   (long timeline, float w, float h);
    public static native int                    wand_view_timeline_draw                     (long timeline, long event_list);
    public static native int                    wand_view_timeline_on_touch_up              (long timeline, float x, float y);
    public static native int                    wand_view_timeline_on_touch_down            (long timeline, float x, float y);
    public static native int                    wand_view_timeline_on_touch_move            (long timeline, float x, float y);


    // TimeLine Draw Event List
    public static native long                   wand_view_draw_event_list_init              ();
    public static native int                    wand_view_draw_event_list_uninit            (long event_list);
    public static native int                    wand_view_draw_event_list_size              (long event_list);
    public static native int                    wand_view_draw_event_list_get_event_type    (long event_list, int index);

    public static native int                    wand_view_draw_event_list_get_rect_event    (long event_list, long rect_event,      int index);
    public static native int                    wand_view_draw_event_list_get_line_event    (long event_list, long line_event,      int index);
    public static native int                    wand_view_draw_event_list_get_text_event    (long event_list, long text_event,      int index);
    public static native int                    wand_view_draw_event_list_get_bitmap_event  (long event_list, long bitmap_event,    int index);


    // Rect Draw Event
    public static native long                   wand_view_draw_event_rect_init              ();
    public static native int                    wand_view_draw_event_rect_uninit            (long rect_event);

    public static native float                  wand_view_draw_event_rect_get_start_x       (long rect_event);
    public static native float                  wand_view_draw_event_rect_get_start_y       (long rect_event);
    public static native float                  wand_view_draw_event_rect_get_end_x         (long rect_event);
    public static native float                  wand_view_draw_event_rect_get_end_y         (long rect_event);

    public static native float                  wand_view_draw_event_rect_get_color_r       (long rect_event);
    public static native float                  wand_view_draw_event_rect_get_color_g       (long rect_event);
    public static native float                  wand_view_draw_event_rect_get_color_b       (long rect_event);
    public static native float                  wand_view_draw_event_rect_get_color_a       (long rect_event);



    // Line Draw Event
    public static native long                   wand_view_draw_event_line_init              ();
    public static native int                    wand_view_draw_event_line_uninit            (long line_event);

    public static native float                  wand_view_draw_event_line_get_start_x       (long line_event);
    public static native float                  wand_view_draw_event_line_get_start_y       (long line_event);
    public static native float                  wand_view_draw_event_line_get_end_x         (long line_event);
    public static native float                  wand_view_draw_event_line_get_end_y         (long line_event);

    public static native float                  wand_view_draw_event_line_get_color_r       (long line_event);
    public static native float                  wand_view_draw_event_line_get_color_g       (long line_event);
    public static native float                  wand_view_draw_event_line_get_color_b       (long line_event);
    public static native float                  wand_view_draw_event_line_get_color_a       (long line_event);

    public static native int                    wand_view_draw_event_line_get_strokewidth   (long line_event);

    // Text Draw Event
    public static native long                   wand_view_draw_event_text_init              ();
    public static native int                    wand_view_draw_event_text_uninit            (long text_event);


    // Bitmap Draw Event
    public static native long                   wand_view_draw_event_bitmap_init              ();
    public static native int                    wand_view_draw_event_bitmap_uninit            (long bitmap_event);

    public static native float                  wand_view_draw_event_bitmap_src_start_x       (long bitmap_event);
    public static native float                  wand_view_draw_event_bitmap_src_start_y       (long bitmap_event);
    public static native float                  wand_view_draw_event_bitmap_src_end_x         (long bitmap_event);
    public static native float                  wand_view_draw_event_bitmap_src_end_y         (long bitmap_event);











    // vec2
    public static native long                   wand_vec2_init                                ();
    public static native int                    wand_vec2_uninit                              (long vec2);
    public static native float                  wand_vec2_get_x                               (long vec2);
    public static native float                  wand_vec2_get_y                               (long vec2);

    // vec3
    public static native long                   wand_vec3_init                                ();
    public static native int                    wand_vec3_uninit                              (long vec3);
    public static native float                  wand_vec3_get_x                               (long vec3);
    public static native float                  wand_vec3_get_y                               (long vec3);
    public static native float                  wand_vec3_get_z                               (long vec3);

    // vec4
    public static native long                   wand_vec4_init                                ();
    public static native int                    wand_vec4_uninit                              (long vec4);
    public static native float                  wand_vec4_get_x                               (long vec4);
    public static native float                  wand_vec4_get_y                               (long vec4);
    public static native float                  wand_vec4_get_z                               (long vec4);
    public static native float                  wand_vec4_get_w                               (long vec4);
}
