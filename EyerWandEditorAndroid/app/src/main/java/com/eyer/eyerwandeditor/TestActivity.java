package com.eyer.eyerwandeditor;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.eyer.eyer_wand_editor_lib.EyerWandNative;

public class TestActivity extends AppCompatActivity {

    private TextView version_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        version_txt = findViewById(R.id.version_txt);

        String version = EyerWandNative.getVersion();

        version_txt.setText(version);
    }
}
