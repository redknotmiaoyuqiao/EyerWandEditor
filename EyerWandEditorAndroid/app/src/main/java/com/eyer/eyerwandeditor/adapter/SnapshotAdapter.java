package com.eyer.eyerwandeditor.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eyer.eyer_wand_editor_lib.av.EyerAVSnapshot;
import com.eyer.eyerwandeditor.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SnapshotAdapter extends RecyclerView.Adapter<SnapshotAdapter.ViewHolder> {



    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView snapshot_item_image;
        TextView snapshot_item_time;

        public ViewHolder (View view)
        {
            super(view);
            snapshot_item_image = (ImageView) view.findViewById(R.id.snapshot_item_image);
            snapshot_item_time = (TextView) view.findViewById(R.id.snapshot_item_time);
        }

    }

    private List<SnapshotBean> snapshotBeanList = null;

    private EyerAVSnapshot myEyerAVSnapshot = null;
    private LruCache<Double, Bitmap> bitmapLruCache = null;
    private Map<Double, Object> ing = null;

    public SnapshotAdapter(List<SnapshotBean> snapshotBeanList){
        this.snapshotBeanList = snapshotBeanList;

        myEyerAVSnapshot = new EyerAVSnapshot("/storage/emulated/0/ST/time_clock_1min_720x1280_30fps.mp4");
        bitmapLruCache = new LruCache<Double, Bitmap>(10);
        ing = new HashMap<Double, Object>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_snapshot,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SnapshotBean snapshotBean = snapshotBeanList.get(i);
        viewHolder.snapshot_item_time.setText(snapshotBean.getTime() + "");

        /*
        Bitmap b = bitmapLruCache.get(snapshotBean.getTime());
        if(b != null){
            viewHolder.snapshot_item_image.setImageBitmap(b);
        }
        else{
            Bitmap bitmap = Bitmap.createBitmap(720, 1280, Bitmap.Config.ARGB_8888);
            bitmap = myEyerAVSnapshot.snapshot(snapshotBean.getTime(), bitmap);

            viewHolder.snapshot_item_image.setImageBitmap(bitmap);

            ing.put(snapshotBean.getTime(), new Object());
            MyHandler handler = new MyHandler();
            GetSnapshotThread thread = new GetSnapshotThread(handler, snapshotBean.getTime());
            thread.start();

        }

        Bitmap bitmap = Bitmap.createBitmap(720, 1280, Bitmap.Config.ARGB_8888);
        bitmap = myEyerAVSnapshot.snapshot(snapshotBean.getTime(), bitmap);
        viewHolder.snapshot_item_image.setImageBitmap(bitmap);
        */


        ing.put(snapshotBean.getTime(), new Object());
        MyHandler handler = new MyHandler(viewHolder.snapshot_item_image);
        GetSnapshotThread thread = new GetSnapshotThread(handler, snapshotBean.getTime());
        thread.start();
    }

    @Override
    public int getItemCount() {
        return snapshotBeanList.size();
    }

    private class BitmapBean
    {
        public double time = 0.0;
        public Bitmap bitmap = null;
    }

    private class MyHandler extends Handler
    {
        private ImageView imageView = null;
        public MyHandler(ImageView imageView){
            this.imageView = imageView;
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            BitmapBean bitmapBean = (BitmapBean)msg.obj;

            ing.remove(bitmapBean.time);

            this.imageView.setImageBitmap(bitmapBean.bitmap);
        }
    }

    private class GetSnapshotThread extends Thread
    {
        private MyHandler handler = null;
        private double time = 0.0;

        public GetSnapshotThread(MyHandler handler, double time){
            this.handler = handler;
            this.time = time;
        }

        @Override
        public void run() {
            super.run();
            synchronized (myEyerAVSnapshot){
                Bitmap bitmap = Bitmap.createBitmap(720, 1280, Bitmap.Config.ARGB_8888);
                bitmap = myEyerAVSnapshot.snapshot(this.time, bitmap);

                Message msg = new Message();

                BitmapBean bitmapBean = new BitmapBean();
                bitmapBean.bitmap = bitmap;
                bitmapBean.time = this.time;

                msg.obj = bitmapBean;

                this.handler.sendMessage(msg);
            }
        }
    }

    /*
    private LayoutInflater mInflater = null;



    private ListView mListView = null;

    public SnapshotAdapter(Context context, List<SnapshotBean> snapshotBeanList){
        this.snapshotBeanList = snapshotBeanList;
        mInflater = LayoutInflater.from(context);

        myEyerAVSnapshot = new EyerAVSnapshot("/storage/emulated/0/ST/time_clock_1min_720x1280_30fps.mp4");
        bitmapLruCache = new LruCache<Double, Bitmap>(10);
        ing = new HashMap<Double, Object>();
    }

    @Override
    public int getCount() {
        return this.snapshotBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.snapshotBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        if(mListView == null){
            mListView = (ListView)parent;
        }


        SnapshotBean snapshotBean = snapshotBeanList.get(i);

        View view = mInflater.inflate(R.layout.adapter_snapshot,null);

        TextView t = view.findViewById(R.id.snapshot_item_time);
        t.setText(snapshotBean.getTime() + "");

        ImageView snapshot_item_image = view.findViewById(R.id.snapshot_item_image);
        snapshot_item_image.setTag(snapshotBean.getTime());

        if(ing.get(snapshotBean.getTime()) == null) {
            ing.put(snapshotBean.getTime(), new Object());
            MyHandler handler = new MyHandler();
            GetSnapshotThread thread = new GetSnapshotThread(handler, snapshotBean.getTime());
            thread.start();
        }

        return view;
    }



     */
}
