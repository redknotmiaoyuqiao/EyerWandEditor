package com.eyer.eyerwandeditor;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.eyer.eyer_wand_editor_lib.av.EyerAVSnapshot;

public class SnapshotTestActivity extends AppCompatActivity {

    private ImageView bitmap_imageview = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snapshot_test);

        bitmap_imageview = findViewById(R.id.bitmap_imageview);

        EyerAVSnapshot myEyerAVSnapshot = new EyerAVSnapshot("");
        Bitmap bitmap = Bitmap.createBitmap(1024, 1024, Bitmap.Config.ARGB_8888);

        myEyerAVSnapshot.snapshot(0.0, bitmap);

        bitmap_imageview.setImageBitmap(bitmap);
    }
}
