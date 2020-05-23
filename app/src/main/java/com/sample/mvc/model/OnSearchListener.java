package com.sample.mvc.model;

/**
 * 作者 Aaron Zhao
 * 时间 2020/5/16 20:07
 * 文件 OnSearchListener.java
 * 描述
 */
public interface OnSearchListener {
    void onError(String error);
    void onSuccess(String info);
}
