package com.sample.mvp.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.winsth.android.libs.utils.DialogUtil;

/**
 * 作者 Aaron Zhao
 * 时间 2020/6/7 18:44
 * 文件 BaseFragment.java
 * 描述
 */
public abstract class BaseFragment extends Fragment implements IBaseView {
    public abstract int getContentViewId();

    protected abstract void initAllMembersView(Bundle savedInstanceState);

    protected Context mContext;
    protected View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getContentViewId(), container, false);
        this.mContext = getActivity();
        initAllMembersView(savedInstanceState);
        return mRootView;
    }

    @Override
    public void showLoading() {
        checkActivityAttached();
        DialogUtil.showProgressDialog(mContext, "", "正在加载数据……");
    }

    @Override
    public void hideLoading() {
        checkActivityAttached();
        DialogUtil.dismissProgressDialog();
    }

    @Override
    public void showToast(String msg) {
        checkActivityAttached();
        DialogUtil.showToast(mContext, msg);
    }

    @Override
    public void showErr() {
        checkActivityAttached();
        DialogUtil.showToast(mContext, "网络错误");
    }

    protected boolean isAttachedContext(){
        return getActivity() != null;
    }

    /**
     * 检查activity连接情况
     */
    public void checkActivityAttached() {
        if (getActivity() == null) {
            throw new ActivityNotAttachedException();
        }
    }

    public static class ActivityNotAttachedException extends RuntimeException {
        public ActivityNotAttachedException() {
            super("Fragment has disconnected from Activity ! - -.");
        }
    }
}
