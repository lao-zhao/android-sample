package com.sample.view.activity.systemwidgets.cv;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.name.libs.adapters.CommonAdapter;
import com.name.libs.adapters.ViewHolder;
import com.sample.R;
import com.sample.support.config.DTFConfig;

import java.util.ArrayList;
import java.util.List;

public class ContentProviderActivity extends AppCompatActivity {
    private ListView lvData;

    private CommonAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        init();
    }

    private void init() {
        // 初始化组件
        initView();

        // 显示数据
        showData();
    }

    private void initView() {
        lvData = findViewById(R.id.lv_data);
    }

    private void showData() {
        // 设置URI
        Uri uriStudent = Uri.parse("content://com.sample.mycontentprovider/student");

        // 获取ContentResolver
        ContentResolver contentResolver = getContentResolver();

        // 删除数据
        contentResolver.delete(uriStudent, null, null);

        // 插入表中数据
        ContentValues values = new ContentValues();
        values.put("name", "CiCi");
        values.put("age", 6);
        values.put("sex", 1);
        values.put("interest", "学习、娱乐");

        // 通过ContentResolver 根据URI 向ContentProvider中插入数据
        contentResolver.insert(uriStudent, values);

        // 通过ContentResolver 向ContentProvider中查询数据
        String[] fields = new String[]{DTFConfig.T_STUDENT_NAME, DTFConfig.T_STUDENT_AGE, DTFConfig.T_STUDENT_SEX, DTFConfig.T_STUDENT_INTEREST};
        Cursor cursor = contentResolver.query(uriStudent, fields, null, null, null);
        List<String[]> dataList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String[] students = new String[4];
            students[0] = cursor.getString(0);
            students[1] = cursor.getInt(1) + "";
            students[2] = cursor.getInt(2) + "";
            students[3] = cursor.getString(3);
            dataList.add(students);
        }
        cursor.close();
        contentResolver.call(uriStudent, "", null, null);

        mAdapter = new CommonAdapter(this, dataList, R.layout.activity_content_provider_item) {
            @Override
            public void convert(ViewHolder holder, Object item) {
                ((TextView) holder.getView(R.id.tv_name)).setText("姓名：" + ((String[]) item)[0]);
                ((TextView) holder.getView(R.id.tv_age)).setText("年龄：" + ((String[]) item)[1]);
                ((TextView) holder.getView(R.id.tv_sex)).setText("性别：" + ((String[]) item)[2]);
                ((TextView) holder.getView(R.id.tv_interest)).setText("兴趣：" + ((String[]) item)[3]);
            }
        };
        lvData.setAdapter(mAdapter);
    }
}
