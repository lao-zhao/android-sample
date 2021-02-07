package com.sample.mvp.base;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.winsth.android.libs.utils.DialogUtil;

/**
 * 作者 Aaron Zhao
 * 时间 2020/6/7 18:30
 * 文件 BaseActivity.java
 * 描述
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseView {
    /**
     * 初始化Presenter的实例，子类实现
     */
    protected abstract void initPresenter();

    /**
     * 获取Presenter实例，子类实现
     */
    protected abstract BasePresenter getPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
        if (getPresenter() != null) {
            getPresenter().attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null) {
            getPresenter().detachView();
        }
    }

    @Override
    public void showLoading() {
        DialogUtil.showProgressDialog(this, "", "正在加载数据……");
    }

    @Override
    public void hideLoading() {
        DialogUtil.dismissProgressDialog();
    }

    @Override
    public void showToast(String msg) {
        DialogUtil.showToast(this, msg);
    }

    @Override
    public void showErr() {
        DialogUtil.showToast(this, "网络错误");
    }

    @Override
    public Context getContext() {
        return BaseActivity.this;
    }
}
