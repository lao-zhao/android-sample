package com.sample.mvp.presenter;

import com.sample.mvp.base.BasePresenter;
import com.sample.mvp.base.DataModel;
import com.sample.mvp.model.OnResponseListener;
import com.sample.mvp.model.UserDataModel;
import com.sample.mvp.view.IMvpView;

/**
 * 作者 Aaron Zhao
 * 时间 2020/6/7 17:29
 * 文件 MvpPresenter.java
 * 描述
 */
public class MvpPresenter extends BasePresenter<IMvpView> {

    /**
     * 获取网络数据
     *
     * @param params 参数
     */
    public void getData(String params) {
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }

        //显示正在加载进度条
        getView().showLoading();

        DataModel.request(UserDataModel.class).params(params)
                .execute(new OnResponseListener<String>() {
            @Override
            public void onSuccess(String data) {
                if (isViewAttached()) {
                    //调用view接口显示数据
                    getView().showData(data);
                }
            }

            @Override
            public void onFailure(String msg) {
                if (isViewAttached()) {
                    //调用view接口提示失败信息
                    getView().showToast(msg);
                }
            }

            @Override
            public void onError() {
                if (isViewAttached()) {
                    //调用view接口提示请求异常
                    getView().showErr();
                }
            }

            @Override
            public void onComplete() {
                if (isViewAttached()) {
                    // 隐藏正在加载进度条
                    getView().hideLoading();
                }
            }
        });
    }
}
