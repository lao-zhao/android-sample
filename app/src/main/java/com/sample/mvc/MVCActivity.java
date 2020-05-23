package com.sample.mvc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.name.libs.utils.DialogUtil;
import com.sample.R;
import com.sample.mvc.model.ISearchModel;
import com.sample.mvc.model.OnSearchListener;
import com.sample.mvc.model.SearchModelImpl;

public class MVCActivity extends AppCompatActivity implements View.OnClickListener, OnSearchListener {
    private EditText etName;
    private Button btnSearch;
    private TextView tvResult;

    private ISearchModel mSearchModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);

        initView();
        setListener();
    }

    private void initView() {
        etName = findViewById(R.id.et_name);
        btnSearch = findViewById(R.id.btn_search);
        tvResult = findViewById(R.id.tv_result);
    }

    private void setListener() {
        btnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                mSearchModel = new SearchModelImpl(this);
                mSearchModel.search(etName.getText().toString());
                break;
        }
    }

    @Override
    public void onError(String error) {
        DialogUtil.showToast(this, error);
    }

    @Override
    public void onSuccess(String info) {
        tvResult.setText(info);
    }
}
