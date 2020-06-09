package com.sample.mvp.model;

import android.os.Handler;

import com.sample.mvp.base.BaseModel;

/**
 * 作者 Aaron Zhao
 * 时间 2020/6/7 20:29
 * 文件 UserDataModel.java
 * 描述
 */
public class UserDataModel extends BaseModel<String> {
    @Override
    public void execute(OnResponseListener<String> callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (mParams[0]) {
                    case "normal":
                        callback.onSuccess("根据参数" + mParams[0] + "的请求网络数据成功");
                        break;

                    case "failure":
                        callback.onFailure("请求失败：参数有误");
                        break;

                    case "error":
                        callback.onError();
                        break;
                }
                callback.onComplete();
            }

        }, 2000);
    }
}
