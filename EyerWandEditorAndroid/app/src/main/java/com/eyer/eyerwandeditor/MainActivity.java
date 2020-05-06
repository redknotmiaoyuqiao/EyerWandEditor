package com.eyer.eyerwandeditor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.eyer.eyer_wand_editor_lib.version.EyerWandVersion;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button crop_test_btn = null;
    private Button opengl_test_btn = null;
    private Button av_test_btn = null;
    private Button snapshot_btn = null;
    private Button wand_context_btn = null;

    private TextView version_txt = null;

    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };

    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        crop_test_btn = findViewById(R.id.crop_test_btn);
        opengl_test_btn = findViewById(R.id.opengl_test_btn);
        av_test_btn = findViewById(R.id.av_test_btn);
        wand_context_btn = findViewById(R.id.wand_context_btn);
        snapshot_btn = findViewById(R.id.snapshot_btn);

        version_txt = findViewById(R.id.version_txt);

        MyClickListener listener = new MyClickListener();

        crop_test_btn.setOnClickListener(listener);
        opengl_test_btn.setOnClickListener(listener);
        av_test_btn.setOnClickListener(listener);
        wand_context_btn.setOnClickListener(listener);
        snapshot_btn.setOnClickListener(listener);

        version_txt.setText(EyerWandVersion.getVersion());

        verifyStoragePermissions(this);
    }

    private class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if(view == crop_test_btn){
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
            if(view == opengl_test_btn){
                Intent intent = new Intent(MainActivity.this, OpenGLTestActivity.class);
                startActivity(intent);
            }
            if(view == av_test_btn){
                Intent intent = new Intent(MainActivity.this, AVTestActivity.class);
                startActivity(intent);
            }
            if(view == wand_context_btn){
                Intent intent = new Intent(MainActivity.this, EyerWandContextTestActivity.class);
                startActivity(intent);
            }
            if(view == snapshot_btn){
                Intent intent = new Intent(MainActivity.this, SnapshotTestActivity.class);
                startActivity(intent);
            }
        }
    }


    public static void verifyStoragePermissions(Activity activity) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity, "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
