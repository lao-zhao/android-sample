package com.sample.view.activity.othersamples;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.sample.R;
import com.sample.support.assistant.ParamsBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class EventBusActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);

        findViewById(R.id.btn_confirm).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name = ((EditText) findViewById(R.id.et_name)).getText().toString();
        String age = ((EditText) findViewById(R.id.et_age)).getText().toString();

        List<String> dynastyList = new ArrayList<>();
        dynastyList.add("夏朝");
        dynastyList.add("商朝");
        dynastyList.add("周朝");
        dynastyList.add("秦朝");
        dynastyList.add("汉朝");
        dynastyList.add("唐朝");
        dynastyList.add("宋朝");
        dynastyList.add("元朝");
        dynastyList.add("明朝");
        dynastyList.add("清朝");
        dynastyList.add("中华民国");
        dynastyList.add("中华人民共和国");

        ParamsBean paramsBean = new ParamsBean<String>(name, age, dynastyList);
        /*
        * EventBus.getDefault().post(event):事件的订阅者需要先注册事件，然后事件的发布者再发布，这样事件的订阅者才能收到信息
        * EventBus.getDefault().postSticky(event):事件的发布者先发布事件，事件的订阅者后注册事件，因为事件的发布时间在注册事件之前，
        *                                         所以只有使用这种模式才订阅者才可以接收到发布事件中的事件信息
        * */
        EventBus.getDefault().postSticky(paramsBean);

        startActivity(new Intent(this, EventBus2Activity.class));
    }
}
