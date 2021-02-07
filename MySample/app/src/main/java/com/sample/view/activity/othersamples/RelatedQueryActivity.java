package com.sample.view.activity.othersamples;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sample.R;
import com.sample.support.config.AppConfig;
import com.sample.support.uitl.RequestUntil;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RelatedQueryActivity extends AppCompatActivity {
    private EditText etCode;
    private Button btnConfirm;
    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_related_query);

        etCode = findViewById(R.id.et_code);
        btnConfirm = findViewById(R.id.btn_confirm);
        tvInfo = findViewById(R.id.tv_info);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> params = new HashMap<>();
                params.put("qrCode", etCode.getText().toString());
                params.put("type", "2");  // 已扫条码类型 :1内码 2外码

                RequestUntil.getApiService(AppConfig.BASE_URL).request(params)
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Object o) {
                                tvInfo.setText(o.toString());
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });
    }
}
