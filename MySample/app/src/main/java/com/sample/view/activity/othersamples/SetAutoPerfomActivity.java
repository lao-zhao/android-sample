package com.sample.view.activity.othersamples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sample.R;
import com.sample.support.service.AutoPerformService;
import com.sample.support.uitl.AccessibilityUtil;

public class SetAutoPerfomActivity extends AppCompatActivity {
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_auto_perfom);

        initView();
        bindData();
    }

    private void initView() {
        btnStart = (Button) findViewById(R.id.btn_start);
    }

    private void bindData() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccessibilityUtil.checkSetting(SetAutoPerfomActivity.this, AutoPerformService.class);
            }
        });
    }
}
