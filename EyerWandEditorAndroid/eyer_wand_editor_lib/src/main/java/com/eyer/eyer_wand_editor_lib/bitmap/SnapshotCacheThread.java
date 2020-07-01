package com.eyer.eyer_wand_editor_lib.bitmap;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

import com.eyer.eyer_wand_editor_lib.av.EyerAVSnapshot;
import com.eyer.eyer_wand_editor_lib.base.EyerWandObject;
import com.eyer.eyer_wand_editor_lib.math.WandVec4;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SnapshotCacheThread extends Thread {

    private volatile boolean stopFlag = false;
    private LruCache<String, Bitmap> cache = null;
    private List<SnapshotCacheTask> taskList = null;
    private Map<String, EyerAVSnapshot> snapshotCache = null;

    private SnapshotCacheThreadListener listener = null;

    public SnapshotCacheThread(){
        this.cache = new LruCache<String, Bitmap>(30);
        this.taskList = new LinkedList<SnapshotCacheTask>();
        this.snapshotCache = new HashMap<String, EyerAVSnapshot>();
    }

    public int setListener(SnapshotCacheThreadListener listener){
        this.listener = listener;
        return 0;
    }

    @Override
    public void run() {
        super.run();

        Log.e("SnapshotCacheThread", "SnapshotCacheThread Start");

        while(!stopFlag){
            EyerWandObject.sleep(1);

            SnapshotCacheTask task = null;
            synchronized (taskList) {
                if(taskList.size() > 0){
                    task = taskList.get(0);
                    taskList.remove(0);
                }
            }

            if(task != null){
                Bitmap b = this.cache.get(task.getUrl());
                if(b == null){
                    b = task.doTask();
                    this.cache.put(task.getUrl(), b);
                }
                if(listener != null){
                    listener.onLoadSuccess(b);
                }
            }
        }


        for(Map.Entry<String, EyerAVSnapshot> entry : snapshotCache.entrySet()) {
            String mapKey = entry.getKey();
            EyerAVSnapshot snapshot = entry.getValue();
            snapshot.destory();
        }
        snapshotCache.clear();

        Log.e("SnapshotCacheThread", "SnapshotCacheThread Stop");
    }

    public int stopThread(){
        stopFlag = true;
        return 0;
    }

    public Bitmap getCache(String path, double time, WandVec4 distVec){
        String url = path + "?time=" + time;
        Bitmap b = this.cache.get(url);

        if(b == null){
            // 插入任务
            synchronized (taskList) {
                SnapshotCacheTask cacheTask = new SnapshotCacheTask(this.snapshotCache, path, time, distVec);
                taskList.add(cacheTask);
            }
        }

        return b;
    }


    public interface SnapshotCacheThreadListener{
        public void onLoadSuccess(Bitmap bitmap);
    }
}
