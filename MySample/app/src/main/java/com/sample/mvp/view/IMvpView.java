package com.sample.mvp.view;

import com.sample.mvp.base.IBaseView;

/**
 * 作者 Aaron Zhao
 * 时间 2020/6/7 17:13
 * 文件 IView.java
 * 描述
 */
public interface IMvpView extends IBaseView {
    /**
     * 当数据请求成功后，调用此接口显示数据
     *
     * @param data 数据源
     */
    void showData(String data);
}
