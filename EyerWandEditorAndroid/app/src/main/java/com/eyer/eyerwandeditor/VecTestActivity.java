package com.eyer.eyerwandeditor;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.eyer.eyer_wand_editor_lib.math.WandVec2;
import com.eyer.eyer_wand_editor_lib.math.WandVec3;
import com.eyer.eyer_wand_editor_lib.math.WandVec4;

public class VecTestActivity extends AppCompatActivity {

    private TextView vec_log = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vec_test);

        vec_log = findViewById(R.id.vec_log);
        String log = "";


        log += "Vector (2)\n";
        WandVec2 vec2 = new WandVec2();
        log += String.format("(x:%f, y:%f)\n", vec2.x(), vec2.y());
        vec2.destory();


        log += "Vector (3)\n";
        WandVec3 vec3 = new WandVec3();
        log += String.format("(x:%f, y:%f, z:%f)\n", vec3.x(), vec3.y(), vec3.z());
        vec3.destory();

        log += "Vector (4)\n";
        WandVec4 vec4 = new WandVec4();
        log += String.format("(x:%f, y:%f, z:%f, w:%f)\n", vec4.x(), vec4.y(), vec4.z(), vec4.w());
        vec4.destory();

        vec_log.setText(log);
    }
}
