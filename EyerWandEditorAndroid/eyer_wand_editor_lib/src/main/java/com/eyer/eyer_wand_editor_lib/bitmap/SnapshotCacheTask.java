package com.eyer.eyer_wand_editor_lib.bitmap;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.eyer.eyer_wand_editor_lib.av.EyerAVSnapshot;
import com.eyer.eyer_wand_editor_lib.math.WandVec2;
import com.eyer.eyer_wand_editor_lib.math.WandVec4;

import java.util.Map;

public class SnapshotCacheTask {

    private String path = null;
    private double time = 0.0;
    private WandVec4 distVec = null;

    private Map<String, EyerAVSnapshot> snapshot = null;

    public SnapshotCacheTask(Map<String, EyerAVSnapshot> snapshot, String path, double time, WandVec4 distVec){
        this.path = path;
        this.time = time;
        this.distVec = distVec;

        this.snapshot = snapshot;
    }

    public String getUrl(){
        String url = path + "?time=" + time;
        return url;
    }

    public Bitmap doTask(){
        EyerAVSnapshot snapshot = this.snapshot.get(path);
        if(snapshot == null){
            snapshot = new EyerAVSnapshot(path);
            this.snapshot.put(path, snapshot);
        }


        WandVec2 wh = new WandVec2();
        snapshot.getWH(wh);

        Bitmap b = Bitmap.createBitmap((int)wh.x(), (int)wh.y(), Bitmap.Config.ARGB_8888);
        snapshot.snapshot(time, b);

        Bitmap bbb = scaleBitmap(b, distVec);

        b.recycle();

        return bbb;
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
