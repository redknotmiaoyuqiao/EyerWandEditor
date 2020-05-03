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

    private EyerWandUISurfaceViewListener listener = null;
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

    public void setListener(EyerWandUISurfaceViewListener listener){
        this.listener = listener;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        glCtxThread = new EyerGLCtxThread(surfaceHolder.getSurface());

        if(this.listener != null){
            this.listener.onCreated(this);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int w, int h) {
        glCtxThread.setWH(w, h);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if(this.listener != null){
            this.listener.onDestroyed(this);
        }

        glCtxThread.stop();
        glCtxThread.destory();
    }

    public int addTaskToRenderQueue(EyerGLRenderTask renderTask){
        return glCtxThread.addTaskToRenderQueue(renderTask);
    }

    public int addTaskToDestoryQueue(EyerGLRenderTask renderTask){
        return glCtxThread.addTaskToDestoryQueue(renderTask);
    }
}
