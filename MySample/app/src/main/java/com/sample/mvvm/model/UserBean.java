package com.sample.mvvm.model;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.name.libs.utils.DialogUtil;
import com.sample.BR;
import com.sample.mvvm.StudentActivity;

/**
 * 作者 Aaron Zhao
 * 时间 2020/6/8 10:10
 * 文件 UserBean.java
 * 描述
 */
public class UserBean extends BaseObservable {
    public String userName;
    public String phoneNumber;
    public String imgUrl;

    public UserBean(String userName, String phoneNumber) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }

    public UserBean(String userName, String phoneNumber, String imgUrl) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.imgUrl = imgUrl;
    }

    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        notifyPropertyChanged(BR.phoneNumber);
    }

    @Bindable
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        notifyPropertyChanged(BR.imgUrl);
    }

    /* 该方法必须是静态方法 */
    @BindingAdapter("bind:img")
    public static void setImgUrl(ImageView imageView, String imgUrl) {
        Glide.with(imageView.getContext()).load(imgUrl).into(imageView);
    }

    public void browseStudent(View v) {
        Context context = v.getContext();
        context.startActivity(new Intent(context, StudentActivity.class));
    }
}
