package com.sample.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sample.R;

public class MVPActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etName, etPwd;
    private Button btnConfirm;
    private TextView tvResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);

        initView();
        setListener();
    }

    private void initView() {
        etName = findViewById(R.id.et_name);
        etPwd = findViewById(R.id.et_pwd);
        btnConfirm = findViewById(R.id.btn_confirm);
        tvResult = findViewById(R.id.tv_result);
    }

    private void setListener() {
        btnConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm:

                break;
        }
    }
}
