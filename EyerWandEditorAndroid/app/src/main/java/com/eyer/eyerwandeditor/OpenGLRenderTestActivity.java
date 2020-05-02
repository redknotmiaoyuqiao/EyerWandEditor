package com.eyer.eyerwandeditor;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.eyer.ui.EyerWandUISurfaceView;

public class OpenGLRenderTestActivity extends AppCompatActivity {

    private EyerWandUISurfaceView surfaceview = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opengl_test);

        surfaceview = findViewById(R.id.surfaceview);
    }
}
