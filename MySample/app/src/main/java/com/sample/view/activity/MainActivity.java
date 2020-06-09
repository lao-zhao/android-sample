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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private GridView gvMenu;

    private CommonAdapter<ValueTextPair> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gvMenu = (GridView) findViewById(R.id.gv_menu);
        gvMenu.setOnItemClickListener(this);

        /* 初始化数据源 */
        List<ValueTextPair> datas = new ArrayList<>();


        datas.add(new ValueTextPair("0", "系统组件"));
        datas.add(new ValueTextPair("1", "三方组件"));
        datas.add(new ValueTextPair("2", "其他示例"));

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
                startActivity(new Intent(this, WidgetsActivity.class));
                break;
            case "1":
                DialogUtil.showToast(this, "暂无");
                break;
            case "2":
                startActivity(new Intent(this, OtherActivity.class));
                break;
        }
    }
}
