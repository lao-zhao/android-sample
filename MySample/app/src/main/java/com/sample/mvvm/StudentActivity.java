package com.sample.mvvm;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.android.databinding.library.baseAdapters.BR;
import com.sample.R;
import com.sample.mvvm.viewmodel.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentActivity extends AppCompatActivity {
    private ListView lvData;
    private List<Student> mStudentList = new ArrayList<>();
    private MyAdapter<Student> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        lvData = findViewById(R.id.lv_data);

        initData();

        mAdapter = new MyAdapter<Student>(this, R.layout.activity_student_item, BR.student, mStudentList);
        lvData.setAdapter(mAdapter);
    }

    private void initData() {
        String url1 = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2690815690,1656161543&fm=26&gp=0.jpg";
        String url2 = "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=559988228,2589160992&fm=26&gp=0.jpg";
        String url3 = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=782344312,767872200&fm=26&gp=0.jpg";
        String url4 = "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2238435263,3800883188&fm=26&gp=0.jpg";
        Student student = new Student("小龙", "男", url1);
        mStudentList.add(student);
        student = new Student("晨曦", "女", url2);
        mStudentList.add(student);
        student = new Student("依依", "女", url3);
        mStudentList.add(student);
        student = new Student("然然", "女", url4);
        mStudentList.add(student);
    }

    public class MyAdapter<Student> extends BaseAdapter {
        private Context context;
        private int layoutId;
        private int varId;
        private List<Student> dataList;

        public MyAdapter(Context context, int layoutId, int varId, List<Student> dataList) {
            this.context = context;
            this.layoutId = layoutId;
            this.varId = varId;
            this.dataList = dataList;
        }

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewDataBinding viewDataBinding;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, null, false);
            } else {
                viewDataBinding = DataBindingUtil.getBinding(convertView);
            }

            viewDataBinding.setVariable(varId, dataList.get(position));

            return viewDataBinding.getRoot().getRootView();
        }
    }
}