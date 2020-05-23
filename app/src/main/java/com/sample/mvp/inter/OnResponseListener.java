package com.sample.mvp.inter;

/**
 * 作者 Aaron Zhao
 * 时间 2020/5/18 23:58
 * 文件 OnResponseListener.java
 * 描述
 */
public interface OnResponseListener {
    void onSuccess();
    void onFailure(String msg);
}
