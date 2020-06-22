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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wand_test);

        eyer_wand_ctx_surfaceview = findViewById(R.id.eyer_wand_ctx_surfaceview);
        timeLineView = findViewById(R.id.timeLineView);

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

    private class MyEyerWandUISurfaceViewListener implements EyerWandUISurfaceViewListener
    {
        @Override
        public int onCreated(EyerWandUISurfaceView surfaceView) {
            wandContext = new EyerWandContext(720, 1280, 30);
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
