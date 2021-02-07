package com.sample.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sample.R;
import com.sample.view.activity.systemwidgets.RecyclerViewActivity;
import com.sample.view.activity.systemwidgets.cv.ContentProviderActivity;
import com.winsth.android.libs.adapters.CommonAdapter;
import com.winsth.android.libs.adapters.ViewHolder;
import com.winsth.android.libs.utils.DialogUtil;

import java.util.ArrayList;
import java.util.List;

public class WidgetsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView lvMenu;

    private CommonAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widgets);

        lvMenu = (ListView) findViewById(R.id.lv_menu);
        lvMenu.setOnItemClickListener(this);

        /* 初始化数据源 */
        List<String> datas = new ArrayList<>();
        datas.add("ListView");
        datas.add("GridView");
        datas.add("ExpandableListView");
        datas.add("RecyclerView");
        datas.add("ContentProvider");

        /* 初始化适配器 */
        mAdapter = new CommonAdapter<String>(this, datas, R.layout.activity_widgets_item) {
            @Override
            public void convert(ViewHolder holder, String item) {
                TextView tvMenuItem = (TextView) holder.getView(R.id.tv_menu_item);
                tvMenuItem.setText(item);
            }
        };

        /* 绑定数据 */
        lvMenu.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String itemName = (String) parent.getItemAtPosition(position);
        switch (itemName) {
            case "ListView":
                DialogUtil.showToast(this, itemName);
                break;
            case "GridView":
                DialogUtil.showToast(this, itemName);
                break;
            case "ExpandableListView":
                DialogUtil.showToast(this, itemName);
                break;
            case "RecyclerView":
                startActivity(new Intent(this, RecyclerViewActivity.class));
                break;
            case "ContentProvider":
                startActivity(new Intent(this, ContentProviderActivity.class));
                break;
        }
    }
}
