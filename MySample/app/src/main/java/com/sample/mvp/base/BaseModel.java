package com.sample.mvp.base;

import com.sample.mvp.model.OnResponseListener;

import java.util.Map;

/**
 * 作者 Aaron Zhao
 * 时间 2020/6/7 20:10
 * 文件 BaseModel.java
 * 描述
 */
public abstract class BaseModel<T>  {
    //数据请求参数
    protected String[] mParams;

    /**
     * 设置数据请求参数
     *
     * @param args 参数数组
     */
    public BaseModel params(String... args) {
        mParams = args;
        return this;
    }

    // 添加Callback并执行数据请求
    // 具体的数据请求由子类实现
    public abstract void execute(OnResponseListener<T> callback);

    // 执行Get网络请求，此类看需求由自己选择写与不写
    protected void requestGetAPI(String url, OnResponseListener<T> callback) {
        //这里写具体的网络请求
    }

    // 执行Post网络请求，此类看需求由自己选择写与不写
    protected void requestPostAPI(String url, Map params, OnResponseListener<T> callback) {
        //这里写具体的网络请求
    }
}
