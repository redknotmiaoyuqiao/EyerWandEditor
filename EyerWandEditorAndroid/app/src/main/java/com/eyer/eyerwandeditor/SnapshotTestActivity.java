package com.eyer.eyerwandeditor;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eyer.eyer_wand_editor_lib.av.EyerAVSnapshot;
import com.eyer.eyerwandeditor.adapter.SnapshotAdapter;
import com.eyer.eyerwandeditor.adapter.SnapshotBean;

import java.util.ArrayList;
import java.util.List;

public class SnapshotTestActivity extends AppCompatActivity {

    private RecyclerView snapshot_recyclerview = null;
    private List<SnapshotBean> snapshotBeanList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snapshot_test);

        snapshot_recyclerview = findViewById(R.id.snapshot_recyclerview);
        snapshotBeanList = new ArrayList<SnapshotBean>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        snapshot_recyclerview.setLayoutManager(layoutManager);
        SnapshotAdapter adapter = new SnapshotAdapter(snapshotBeanList);
        snapshot_recyclerview.setAdapter(adapter);

        double maxTime = 60.0;
        for(double t=0;t<maxTime;t+=0.5){
            SnapshotBean snapshotBean = new SnapshotBean(t);
            snapshotBeanList.add(snapshotBean);
        }

        adapter.notifyDataSetChanged();
    }
}
