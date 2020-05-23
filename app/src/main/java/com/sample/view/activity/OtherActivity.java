package com.sample.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.name.libs.adapters.CommonAdapter;
import com.name.libs.adapters.ViewHolder;
import com.name.libs.assists.ValueTextPair;
import com.name.libs.utils.DialogUtil;
import com.sample.R;
import com.sample.mvc.MVCActivity;
import com.sample.view.activity.othersamples.ReadDataByBluetoothActivity;
import com.sample.view.activity.othersamples.RedirectWechatActivity;
import com.sample.view.activity.othersamples.SetAutoPerfomActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者 Aaron Zhao
 * 时间 2019/1/10 13:21
 * 文件 OtherActivity.java
 * 描述
 */
public class OtherActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private GridView gvMenu;

    private CommonAdapter<ValueTextPair> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        gvMenu = (GridView) findViewById(R.id.gv_menu);
        gvMenu.setOnItemClickListener(this);

        /* 初始化数据源 */
        List<ValueTextPair> datas = new ArrayList<>();

        datas.add(new ValueTextPair("0", "获取蓝牙数据"));
        datas.add(new ValueTextPair("1", "回调三方接口"));
        datas.add(new ValueTextPair("2", "自动执行服务"));
        datas.add(new ValueTextPair("3", "其他"));
        datas.add(new ValueTextPair("4", "MVC实例"));
        datas.add(new ValueTextPair("5", "MVP实例"));
        datas.add(new ValueTextPair("6", "MVVM实例"));
        datas.add(new ValueTextPair("7", "APP跳转到小程序"));

        /* 初始化适配器 */
        mAdapter = new CommonAdapter<ValueTextPair>(this, datas, R.layout.activity_main_item) {
            @Override
            public void convert(ViewHolder holder, ValueTextPair item) {
                TextView tvMenuItem = (TextView) holder.getView(R.id.tv_menu_item);
                tvMenuItem.setText(item.getText());
            }
        };

        /* 绑定数据 */
        gvMenu.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String value = ((ValueTextPair) parent.getItemAtPosition(position)).getValue();
        switch (value) {
            case "0":
                startActivity(new Intent(this, ReadDataByBluetoothActivity.class));
                break;
            case "1":
                DialogUtil.showToast(this, "暂无");
                break;
            case "2":
                startActivity(new Intent(this, SetAutoPerfomActivity.class));
                break;
            case "3":
                DialogUtil.showToast(this, "暂无");
                break;
            case "4":
                startActivity(new Intent(this, MVCActivity.class));
                break;
            case "5":
                DialogUtil.showToast(this, "暂无");
                break;
            case "6":
                DialogUtil.showToast(this, "暂无");
                break;
            case "7":
                startActivity(new Intent(this, RedirectWechatActivity.class));
                break;
        }
    }

    /*private void init() {
        *//* 回传数据给区块链 *//*
        callBlockChain();

        *//**//*
    }

    private void callBlockChain() {
        DialogUtil.showToast(this, "callBlockChain");

        ExecutorUtil.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                String url = "http://blockchain.southeastasia.cloudapp.azure.com/bloc/v2.2/userc/contract/contract/";
                Map<String, String> params = new HashMap<>();

                String result = OKHttpUtil.getInstance().requestStringByPost(url, null, "UTF-8", "json");
            }
        });
    }*/

}
