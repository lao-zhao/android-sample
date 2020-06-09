package com.sample.mvvm;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sample.R;
import com.sample.databinding.ActivityMvvmBinding;
import com.sample.mvvm.model.LoginBean;
import com.sample.mvvm.model.UserBean;

public class MvvmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMvvmBinding activityMvvmBinding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);

        /* User info */
        UserBean userBean = new UserBean("老赵", "17717871887");
        activityMvvmBinding.setUser(userBean);

        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                userBean.setUserName("依然");
                userBean.setPhoneNumber("17717871987");
                userBean.setImgUrl("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=559988228,2589160992&fm=26&gp=0.jpg");
            }
        }, 5000);

        /* 帐号信息 */
        LoginBean loginBean = new LoginBean();
        loginBean.account.set("17717871887");
        loginBean.password.set("9852367410");
        activityMvvmBinding.setLogin(loginBean);

        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                loginBean.account.set("17717871987");
                loginBean.password.set("888888888");
            }
        }, 5000);
    }
}