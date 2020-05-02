package com.eyer.ui;

import android.content.Context;
import android.opengl.EGL14;
import android.opengl.EGLDisplay;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.eyer.eyer_wand_editor_lib.ogl.EyerGLCtxThread;
import com.eyer.eyer_wand_editor_lib.rendertask.EyerGLRenderTask;

public class EyerWandUISurfaceView extends SurfaceView implements SurfaceHolder.Callback{

    private EyerGLCtxThread glCtxThread = null;

    public EyerWandUISurfaceView(Context context) {
        super(context);
        init();
    }

    public EyerWandUISurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EyerWandUISurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        glCtxThread = new EyerGLCtxThread(surfaceHolder.getSurface());
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int w, int h) {
        glCtxThread.setWH(w, h);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        glCtxThread.stop();
        glCtxThread.destory();
    }

    public int addRenderTask(EyerGLRenderTask renderTask){
        return glCtxThread.addTaskToRenderQueue(renderTask);
    }
}
