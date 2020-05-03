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

    // OpenGL
    public static native long                   ogl_create_thread                           (android.view.Surface surface);
    // public static native long                   ogl_create_thread                           ();
    public static native int                    ogl_stop_thread                             (long thread);
    public static native int                    ogl_set_wh                                  (long thread, int w, int h);
    public static native int                    ogl_add_task_to_render_queue                (long thread, long render_task);
    public static native int                    ogl_add_task_to_destory_queue                (long thread, long render_task);

    // Render Task
    public static native long                   render_task_init                            (String name);
    public static native int                    render_task_uninit                          (long render_task);


    // AV Reader
    public static native long                   avreader_init                               (String url);
    public static native int                    avreader_uninit                             (long avreader);
    public static native int                    avreader_open                               (long avreader);
    public static native int                    avreader_close                              (long avreader);
    public static native int                    avreader_print_info                         (long avreader);
}
