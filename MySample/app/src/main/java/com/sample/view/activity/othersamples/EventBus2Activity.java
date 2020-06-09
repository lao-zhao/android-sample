package com.sample.view.activity.othersamples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.sample.R;
import com.sample.support.assistant.ParamsBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBus2Activity extends AppCompatActivity {
    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus2);

        tvInfo = findViewById(R.id.tv_info);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /*
    * 该方法名称自己随便定义，但是一定要加上注解@Subscribe并指定执行的线程模式，并且方法的修饰符必须是public
    * */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onReceiveMsg(ParamsBean paramsBean) {
        if (paramsBean != null) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("国家：" + paramsBean.getName() + "\n");
            buffer.append("年龄：" + paramsBean.getAge() + "\n");
            if (paramsBean.gettList() != null && paramsBean.gettList().size() > 0) {
                for (Object temp : paramsBean.gettList()) {
                    if (temp instanceof String) {
                        buffer.append("朝代：" + temp.toString() + "\n");
                    }
                }
            }

            tvInfo.setText(buffer.toString());
            // 在sticky模式下，事件会存储在缓存中，如果不做显式的移除操作，那么事件将永远留在内存中，这可能导致内存溢出
            EventBus.getDefault().removeStickyEvent(paramsBean);
        }
    }
}
