package com.sample.mvvm.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.android.databinding.library.baseAdapters.BR;
import com.bumptech.glide.Glide;
import com.name.libs.utils.DialogUtil;

/**
 * 作者 Aaron Zhao
 * 时间 2020/6/8 11:49
 * 文件 Student.java
 * 描述
 */
public class Student extends BaseObservable {
    public String name;
    public String sex;
    public String headUrl;

    public Student(String name, String sex, String headUrl) {
        this.name = name;
        this.sex = sex;
        this.headUrl = headUrl;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
        notifyPropertyChanged(BR.sex);
    }

    @Bindable
    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
        notifyPropertyChanged(BR.headUrl);
    }

    @BindingAdapter("bind:headimg")
    public static void setHeadUrl(ImageView imageView, String headUrl) {
        Glide.with(imageView.getContext()).load(headUrl).into(imageView);
    }

    public void click(View v) {
        DialogUtil.showToast(v.getContext(), getName());
    }
}
