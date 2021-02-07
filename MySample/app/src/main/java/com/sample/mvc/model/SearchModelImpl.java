package com.sample.mvc.model;


import com.winsth.android.libs.utils.StringUtil;

/**
 * 作者 Aaron Zhao
 * 时间 2020/5/16 20:09
 * 文件 SearchModelImpl.java
 * 描述
 */
public class SearchModelImpl implements ISearchModel {
    private OnSearchListener mOnSearchListener;
    public SearchModelImpl(OnSearchListener onSearchListener) {
        this.mOnSearchListener = onSearchListener;
    }

    @Override
    public void search(String name) {
        if (StringUtil.isNullOrEmpty(name) || !name.equals("ZhaoGang")) {
            this.mOnSearchListener.onError("没有找到您要搜索的记录");
        } else {
            StringBuffer buffer = new StringBuffer();
            buffer.append("恭喜你找了名字叫" + name + "的记录。");
            this.mOnSearchListener.onSuccess(buffer.toString());
        }
    }
}
