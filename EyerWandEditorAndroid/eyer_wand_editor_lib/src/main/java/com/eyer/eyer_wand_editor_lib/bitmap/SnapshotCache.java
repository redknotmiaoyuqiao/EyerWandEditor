package com.eyer.eyer_wand_editor_lib.bitmap;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;
import android.util.LruCache;

import com.eyer.eyer_wand_editor_lib.av.EyerAVSnapshot;
import com.eyer.eyer_wand_editor_lib.math.WandVec4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SnapshotCache {

    private LruCache<String, Bitmap> cache = null;
    private LruCache<String, EyerAVSnapshot> snapshotLruCache = null;

    private ExecutorService executorService = null;

    public SnapshotCache(){
        cache = new LruCache<String, Bitmap>(30);
        snapshotLruCache = new LruCache<String, EyerAVSnapshot>(3);
        executorService = Executors.newFixedThreadPool(1);
    }

    public Bitmap getCache(String path, double time, WandVec4 srcVec, WandVec4 distVec){
        String url = path + "?time=" + time;

        Bitmap b = cache.get(url);
        if(b == null){
            GetSnapshotThread t = new GetSnapshotThread(path, time, srcVec, distVec);
            // new Thread(t).start();
            executorService.execute(t);
        }

        return b;
    }

    private class GetSnapshotThread implements Runnable {

        private String path = null;
        private double time = 0.0;
        private WandVec4 srcVec = null;
        private WandVec4 distVec = null;

        public GetSnapshotThread(String path, double time, WandVec4 srcVec, WandVec4 distVec){
            this.path = path;
            this.srcVec = srcVec;
            this.distVec = distVec;
            this.time = time;
        }

        @Override
        public void run() {
            String url = path + "?time=" + time;

            Bitmap bb = cache.get(url);
            if(bb != null){
                return;
            }

            EyerAVSnapshot snapshot = snapshotLruCache.get(path);
            if(snapshot == null){
                snapshot = new EyerAVSnapshot(path);
                snapshotLruCache.put(path, snapshot);
            }

            Bitmap b = Bitmap.createBitmap((int)srcVec.z(), (int)srcVec.w(), Bitmap.Config.ARGB_8888);
            snapshot.snapshot(time, b);

            Bitmap bbb = scaleBitmap(b, distVec);

            b.recycle();

            cache.put(url, bbb);
        }
    }

    private Bitmap scaleBitmap(Bitmap origin, WandVec4 distVec) {
        if (origin == null) {
            return null;
        }
        int distWidth = (int)(distVec.z() - distVec.x());
        int distHeight = (int)(distVec.w() - distVec.y());

        int width = origin.getWidth();
        int height = origin.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(distWidth * 1.0f / width, distHeight * 1.0f / height);
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (newBM.equals(origin)) {
            return newBM;
        }
        origin.recycle();
        return newBM;
    }
}
