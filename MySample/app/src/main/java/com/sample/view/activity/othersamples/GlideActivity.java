package com.sample.view.activity.othersamples;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sample.R;
import com.winsth.android.libs.adapters.CommonAdapter;
import com.winsth.android.libs.adapters.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class GlideActivity extends AppCompatActivity {
    private ImageView ivTest;
    private GridView gvPictures;

    private CommonAdapter<String> mCommonAdapter;
    private List<String> mDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);

        init();
    }

    private void init() {
        ivTest = findViewById(R.id.iv_test);
        gvPictures = findViewById(R.id.gv_picture);

        mCommonAdapter = new CommonAdapter<String>(this, mDataList, R.layout.activity_glide_item) {
            @Override
            public void convert(ViewHolder holder, String item) {
                Glide.with(GlideActivity.this)
                        .load(item)
                        .placeholder(R.mipmap.ic_launcher_round)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .into((ImageView) holder.getView(R.id.iv_item));
            }
        };
        gvPictures.setAdapter(mCommonAdapter);

        loadOnePicture();
        loadPictures();
    }

    private void loadOnePicture() {
        Glide.with(this).load("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=559988228,2589160992&fm=26&gp=0.jpg")
                .placeholder(R.mipmap.ic_launcher_round)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(ivTest);
    }

    private void loadPictures() {
        mDataList.clear();
        mDataList.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=559988228,2589160992&fm=26&gp=0.jpg");
        mDataList.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2690815690,1656161543&fm=26&gp=0.jpg");
        mDataList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=782344312,767872200&fm=26&gp=0.jpg");
        mDataList.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2238435263,3800883188&fm=26&gp=0.jpg");
        mDataList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1591469518803&di=cc46f6968a87ecdab30c567ed4272b94&imgtype=0&src=http%3A%2F%2Fimg.08087.cc%2Fuploads%2F20191221%2F22%2F1576938658-uYRVjQIWvA.jpg");
        mDataList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1333939898,3720919752&fm=26&gp=0.jpg");
        mDataList.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=956660071,3420087590&fm=26&gp=0.jpg");
        mDataList.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3693552609,2739990919&fm=26&gp=0.jpg");
        mDataList.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1481660997,3689709559&fm=26&gp=0.jpg");
        mDataList.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1053915016,731987381&fm=26&gp=0.jpg");

        mCommonAdapter.notifyDataSetChanged();
    }
}
