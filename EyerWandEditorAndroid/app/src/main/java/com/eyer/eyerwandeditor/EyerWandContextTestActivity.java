package com.eyer.eyerwandeditor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.eyer.eyer_wand_editor_lib.eyerwand.EyerWandContext;
import com.eyer.ui.EyerWandUISurfaceView;
import com.eyer.ui.EyerWandUISurfaceViewListener;
import com.eyer.ui.view.EyerWandTimeLineView;

public class EyerWandContextTestActivity extends AppCompatActivity {

    private EyerWandContext wandContext = null;
    private EyerWandUISurfaceView eyer_wand_ctx_surfaceview = null;

    private EyerWandTimeLineView timeLineView = null;

    private TextView render_frame_txt = null;
    private Button render_frame_btn = null;

    private int frameIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wand_test);

        eyer_wand_ctx_surfaceview = findViewById(R.id.eyer_wand_ctx_surfaceview);
        render_frame_txt = findViewById(R.id.render_frame_txt);
        render_frame_btn = findViewById(R.id.render_frame_btn);
        timeLineView = findViewById(R.id.timeLineView);

        render_frame_btn.setOnClickListener(new MyClickListener());
        eyer_wand_ctx_surfaceview.setListener(new MyEyerWandUISurfaceViewListener());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(timeLineView != null){
            timeLineView.destory();
            timeLineView = null;
        }
    }

    private class MyClickListener implements View.OnClickListener  {
        @Override
        public void onClick(View v) {
            if(wandContext != null){
                frameIndex++;
                render_frame_txt.setText("Frame Index: " + frameIndex);
                wandContext.renderFrameByIndex(frameIndex);
            }
        }
    }

    private class MyEyerWandUISurfaceViewListener implements EyerWandUISurfaceViewListener
    {
        @Override
        public int onCreated(EyerWandUISurfaceView surfaceView) {
            wandContext = new EyerWandContext(800, 1280, 30);
            wandContext.setGL(eyer_wand_ctx_surfaceview.getGlCtx());

            timeLineView.SetWandContext(wandContext);

            return 0;
        }

        @Override
        public int onBeforeDestroy(EyerWandUISurfaceView surfaceView) {
            timeLineView.UnsetWandContext();

            if(wandContext != null){
                wandContext.destory();
                wandContext = null;
            }

            return 0;
        }

        @Override
        public int onDestroyed(EyerWandUISurfaceView surfaceView) {
            return 0;
        }
    }
}
