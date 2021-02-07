package com.sample.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sample.R;
import com.sample.mvc.MVCActivity;
import com.sample.mvp.MVPActivity;
import com.sample.mvvm.MvvmActivity;
import com.sample.view.activity.othersamples.EventBusActivity;
import com.sample.view.activity.othersamples.GlideActivity;
import com.sample.view.activity.othersamples.ReadDataByBluetoothActivity;
import com.sample.view.activity.othersamples.RedirectWechatActivity;
import com.sample.view.activity.othersamples.RelatedQueryActivity;
import com.sample.view.activity.othersamples.RxJava2SwitchThreadActivity;
import com.sample.view.activity.othersamples.SetAutoPerfomActivity;
import com.winsth.android.libs.adapters.CommonAdapter;
import com.winsth.android.libs.adapters.ViewHolder;
import com.winsth.android.libs.assists.ValueTextPair;

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
        datas.add(new ValueTextPair("1", "EventBus使用"));
        datas.add(new ValueTextPair("2", "自动执行服务"));
        datas.add(new ValueTextPair("3", "RxJava2线程切换"));
        datas.add(new ValueTextPair("4", "MVC实例"));
        datas.add(new ValueTextPair("5", "MVP实例"));
        datas.add(new ValueTextPair("6", "MVVM实例"));
        datas.add(new ValueTextPair("7", "APP跳转到小程序"));
        datas.add(new ValueTextPair("8", "Retrofit2+RxJava2美孚瓶盖查询"));
        datas.add(new ValueTextPair("9", "Glide的使用"));
        datas.add(new ValueTextPair("10", "热修复实现"));

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
                startActivity(new Intent(this, EventBusActivity.class));
                break;
            case "2":
                startActivity(new Intent(this, SetAutoPerfomActivity.class));
                break;
            case "3":
                startActivity(new Intent(this, RxJava2SwitchThreadActivity.class));
                break;
            case "4":
                startActivity(new Intent(this, MVCActivity.class));
                break;
            case "5":
                startActivity(new Intent(this, MVPActivity.class));
                break;
            case "6":
                startActivity(new Intent(this, MvvmActivity.class));
                break;
            case "7":
                startActivity(new Intent(this, RedirectWechatActivity.class));
                break;
            case "8":
                startActivity(new Intent(this, RelatedQueryActivity.class));
                break;
            case "9":
                startActivity(new Intent(this, GlideActivity.class));
                break;
            case "10":
                startActivity(new Intent(this, GlideActivity.class));
                break;
        }
    }
}
