package com.sample.mvp.base;

import com.sample.mvp.base.BaseModel;

/**
 * 作者 Aaron Zhao
 * 时间 2020/6/7 20:31
 * 文件 DataModel.java
 * 描述
 */
public class DataModel {
    public static BaseModel request(Class clazz) {
        // 声明一个空的BaseModel
        BaseModel model = null;

        try {
            Object obj = clazz.newInstance();
            //向下转型有风险，使用instanceof
            if (obj instanceof BaseModel) {
                //利用反射机制获得对应Model对象的引用
                model = (BaseModel) clazz.newInstance();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return model;
    }
}
