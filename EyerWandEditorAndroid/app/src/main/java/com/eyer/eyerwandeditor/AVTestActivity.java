package com.eyer.eyerwandeditor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.eyer.eyer_wand_editor_lib.av.EyerAVReader;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.util.List;

public class AVTestActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CHOOSE = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_av_test);

        /*
        Matisse.from(AVTestActivity.this)
                .choose(MimeType.allOf())//图片类型
                .countable(true)//true:选中后显示数字;false:选中后显示对号
                .maxSelectable(5)//可选的最大数
                .capture(true)//选择照片时，是否显示拍照
                .captureStrategy(new CaptureStrategy(true, "com.example.xx.fileprovider"))//参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                .imageEngine(new GlideEngine())//图片加载引擎
                .forResult(REQUEST_CODE_CHOOSE);//
        */

        File dir = Environment.getExternalStorageDirectory();

        String videoPath = dir.getAbsolutePath() + "/" + "ST/time_clock_1min_720x1280_30fps.mp4";

        EyerAVReader avReader = new EyerAVReader(videoPath);
        avReader.open();
        avReader.printInfo();
        avReader.close();
        avReader.destory();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<Uri> result = Matisse.obtainResult(data);

            Log.e("onActivityResult", result.toString());
        }
    }
}
