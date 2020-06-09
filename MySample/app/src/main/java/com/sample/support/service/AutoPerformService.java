package com.sample.support.service;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

/**
 * 作者 Aaron Zhao
 * 时间 2020/2/23 11:24
 * 文件 AutoPerformService.java
 * 描述
 */
public class AutoPerformService extends AccessibilityService {
    private final static String TAG = AutoPerformService.class.getSimpleName();

    private final static String PACKAGE_NAME = "kuaishou";

    private Handler mHandler = new Handler();

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event == null || !event.getPackageName().toString().contains(PACKAGE_NAME)) {
            return;
        }

//        Log.e(TAG, "onAccessibilityEvent: " + event.getPackageName().toString());
//        Log.e(TAG, "onAccessibilityEvent: " + event);

        AccessibilityNodeInfo accessibilityNodeInfo = event.getSource();
        Log.e(TAG, "onAccessibilityEvent: " + accessibilityNodeInfo);
        if (accessibilityNodeInfo == null) {
            Log.e(TAG, "onAccessibilityEvent: aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            return;
        }

        Log.e(TAG, "onAccessibilityEvent: bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        // 模拟滑动手势操作
        topGestureClick();
    }

    @Override
    public void onInterrupt() {

    }

    private void topGestureClick() {
        Log.e(TAG, "onAccessibilityEvent: topGestureClick执行");
        GestureDescription.Builder builder = new GestureDescription.Builder();
        Path path = new Path();
        int x = 300, y = 1200;
        path.moveTo(360, y);

        path.lineTo(x += 3, y -= 1000);

        GestureDescription gestureDescription = builder.addStroke(new GestureDescription.StrokeDescription(path, 200L,800L,false)).build();


        dispatchGesture(gestureDescription, new AccessibilityService.GestureResultCallback(){
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        topGestureClick();
                    }
                },3000);
            }

            @Override
            public void onCancelled(GestureDescription gestureDescription) {
                super.onCancelled(gestureDescription);
            }
        }, new Handler(Looper.getMainLooper()));
    }
}
