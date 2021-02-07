package com.sample.mvvm.model;

import androidx.databinding.ObservableField;

/**
 * 作者 Aaron Zhao
 * 时间 2020/6/8 10:59
 * 文件 LoginBean.java
 * 描述
 */
public class LoginBean {
    public ObservableField<String> account = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();

    public ObservableField<String> getAccount() {
        return account;
    }

    public void setAccount(ObservableField<String> account) {
        this.account = account;
    }

    public ObservableField<String> getPassword() {
        return password;
    }

    public void setPassword(ObservableField<String> password) {
        this.password = password;
    }
}
