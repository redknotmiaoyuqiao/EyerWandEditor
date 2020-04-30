package com.eyer.eyerwandeditor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.eyer.eyer_wand_editor_lib.version.EyerWandVersion;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button crop_test_btn = null;
    private Button opengl_test_btn = null;

    private TextView version_txt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        crop_test_btn = findViewById(R.id.crop_test_btn);
        opengl_test_btn = findViewById(R.id.opengl_test_btn);

        version_txt = findViewById(R.id.version_txt);

        MyClickListener listener = new MyClickListener();

        crop_test_btn.setOnClickListener(listener);
        opengl_test_btn.setOnClickListener(listener);

        version_txt.setText(EyerWandVersion.getVersion());
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
        }
    }
}
