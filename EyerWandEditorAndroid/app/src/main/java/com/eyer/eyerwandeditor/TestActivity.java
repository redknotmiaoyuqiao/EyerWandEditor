package com.eyer.eyerwandeditor;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.eyer.eyer_wand_editor_lib.crop.EyerAVCrop;
import com.eyer.eyer_wand_editor_lib.crop.EyerAVCropType;
import com.eyer.eyer_wand_editor_lib.version.EyerWandVersion;

public class TestActivity extends AppCompatActivity {

    private TextView version_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        version_txt = findViewById(R.id.version_txt);

        String version = EyerWandVersion.getVersion();

        version_txt.setText(version);


        // Crop Test
        EyerAVCrop crop = new EyerAVCrop(100, 100, 1920, 1080);
        crop.crop(EyerAVCropType.FIT_CENTER);
        int cropWidth = crop.getCropWidth();
        int cropHeight = crop.getCropHeight();

        Log.e("EyerWandCrop", "EyerWandCrop Width: " + cropWidth);
        Log.e("EyerWandCrop", "EyerWandCrop Height: " + cropHeight);

        crop.destory();
    }
}
