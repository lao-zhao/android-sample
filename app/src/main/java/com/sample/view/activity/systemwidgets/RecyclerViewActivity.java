package com.sample.view.activity.systemwidgets;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.name.libs.utils.DialogUtil;
import com.sample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者 Aaron Zhao
 * 时间 2019/7/15 23:07
 * 文件 RecyclerViewActivity.java
 * 描述
 */
public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView rvData;
    private MyAdapter myAdapter;
    List<String> datas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        initView();
        bindData();
    }

    private void initView() {
        rvData = (RecyclerView) findViewById(R.id.rv_data);
    }

    private void bindData() {
        datas.clear();
        datas.add("赵刚");
        datas.add("董美娟");
        datas.add("赵思依");
        datas.add("赵思然");

        myAdapter = new MyAdapter(this, datas);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvData.setLayoutManager(manager);

        rvData.setAdapter(myAdapter);
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
        Context context;
        List<String> datas;

        public MyAdapter(Context context, List<String> datas) {
            this.context = context;
            this.datas = datas;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.activity_recyclerview_item, parent, false);
            MyHolder myHolder = new MyHolder(view);
            return myHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            final String name = datas.get(position);
            holder.tvMenuItem.setText(name);

            holder.tvMenuItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogUtil.showToast(context, "你点击了" + name);
                }
            });
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        public void update(List<String> datas) {
            this.datas = datas;
            notifyDataSetChanged();
        }

        /**
         * 自定义Holder
         */
        class MyHolder extends RecyclerView.ViewHolder {
            TextView tvMenuItem;

            public MyHolder(View itemView) {
                super(itemView);
                tvMenuItem = itemView.findViewById(R.id.tv_menu_item);
            }
        }
    }
}
