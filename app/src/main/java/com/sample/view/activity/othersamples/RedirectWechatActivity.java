package com.sample.view.activity.othersamples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.name.libs.utils.DialogUtil;
import com.sample.R;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * 开发者在微信开放平台帐号下申请移动应用并通过审核后，即可获得移动应用拉起小程序功能权限。
 * */
public class RedirectWechatActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redirect_wechat);

        findViewById(R.id.btn_redirect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String appId = "wxd930ea5d5a258f4f";                                    // 填应用AppId
                IWXAPI api = WXAPIFactory.createWXAPI(RedirectWechatActivity.this, appId);

                if(api.isWXAppInstalled()) {
                    WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
                    req.userName = "gh_6d6cfd1d08ae";                                       // 填小程序原始id
                    req.path = "page/module/pages/judgelocation/judgelocation";             //拉起小程序页面的可带参路径，不填默认拉起小程序首页，对于小游戏，可以只传入 query 部分，来实现传参效果，如：传入 "?foo=bar"。
                    req.miniprogramType = WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE; // 可选打开 开发版，体验版和正式版
                    api.sendReq(req);
                } else {
                    DialogUtil.showToast(RedirectWechatActivity.this, getResources().getString(R.string.toast_pls_install_wechat));
                }
            }
        });
    }
}
