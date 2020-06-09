package com.sample.mvp.base;

import android.content.Context;

/**
 * 作者 Aaron Zhao
 * 时间 2020/6/7 18:24
 * 文件 IBaseView.java
 * 描述
 */
public interface IBaseView {
    /**
     * 显示正在加载view
     */
    void showLoading();

    /**
     * 关闭正在加载view
     */
    void hideLoading();

    /**
     * 显示提示
     * @param msg
     */
    void showToast(String msg);

    /**
     * 显示请求错误提示
     */
    void showErr();

    /**
     * 获取上下文
     * @return 上下文
     */
    Context getContext();
}
