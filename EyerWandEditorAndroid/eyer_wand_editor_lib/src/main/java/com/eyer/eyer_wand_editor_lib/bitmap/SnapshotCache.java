package com.eyer.eyer_wand_editor_lib.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.LruCache;

import com.eyer.eyer_wand_editor_lib.av.EyerAVSnapshot;
import com.eyer.eyer_wand_editor_lib.math.WandVec2;
import com.eyer.eyer_wand_editor_lib.math.WandVec4;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SnapshotCache {

    private SnapshotCacheListener listener = null;
    private Context context = null;
    private MyHandle handle = null;
    private SnapshotCacheThread cacheThread = null;

    public SnapshotCache(Context context){
        this.context = context;

        this.handle = new MyHandle();

        this.cacheThread = new SnapshotCacheThread();
        this.cacheThread.setListener(new MySnapshotCacheThreadListener(this.handle));
        this.cacheThread.start();
    }

    public void setListener(SnapshotCacheListener listener){
        this.listener = listener;
    }

    public Bitmap getCache(String path, double time, WandVec4 distVec){
        Bitmap b = this.cacheThread.getCache(path, time, distVec);
        return b;
    }

    public int destory(){
        Log.e("SnapshotCache", "SnapshotCache destory()");

        this.cacheThread.stopThread();
        try {
            this.cacheThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private class MyHandle extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(listener != null){
                listener.onLoad();
            }
        }
    }

    private class MySnapshotCacheThreadListener implements SnapshotCacheThread.SnapshotCacheThreadListener {

        private MyHandle handle = null;

        public MySnapshotCacheThreadListener(MyHandle handle){
            this.handle = handle;
        }

        @Override
        public void onLoadSuccess(Bitmap bitmap) {
            Message msg = new Message();
            handle.sendMessage(msg);
        }
    }
}
