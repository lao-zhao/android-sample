package com.sample.mvp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sample.R;
import com.sample.mvp.base.BaseActivity;
import com.sample.mvp.base.BasePresenter;
import com.sample.mvp.presenter.MvpPresenter;
import com.sample.mvp.view.IMvpView;

public class MVPActivity extends BaseActivity implements IMvpView {
    private TextView text;
    private MvpPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);

        text = (TextView)findViewById(R.id.text);
    }

    @Override
    protected void initPresenter() {
        presenter = new MvpPresenter();
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    // button 点击事件调用方法
    public void getData(View view){
        presenter.getData("normal");
    }

    // button 点击事件调用方法
    public void getDataForFailure(View view){
        presenter.getData("failure");
    }

    // button 点击事件调用方法
    public void getDataForError(View view){
        presenter.getData("error");
    }

    @Override
    public void showData(String data) {
        text.setText(data);
    }
}
