package com.eyer.eyerwandeditor;

import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.eyer.eyer_wand_editor_lib.rendertask.EyerGLRenderTask;
import com.eyer.ui.EyerWandUISurfaceView;

public class OpenGLTestActivity extends AppCompatActivity {

    private EyerWandUISurfaceView surfaceview = null;
    private Button btn_add_julia = null;

    private EyerGLRenderTask renderTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opengl_test);

        renderTask = new EyerGLRenderTask("");

        surfaceview = findViewById(R.id.surfaceview);
        btn_add_julia = findViewById(R.id.btn_add_julia);

        btn_add_julia.setOnClickListener(new MyClickListener());
    }

    private class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            surfaceview.addRenderTask(renderTask);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        renderTask.destory();
    }
}
