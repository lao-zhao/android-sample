package com.sample.support.assistant;

import androidx.annotation.NonNull;

import java.util.List;

/**
 * 作者 Aaron Zhao
 * 时间 2020/6/4 13:02
 * 文件 ParamsBean.java
 * 描述
 */
public class ParamsBean<T> {
    private String name;
    private String age;
    private List<T> tList;

    public ParamsBean(String name, String age, List<T> tList) {
        this.name = name;
        this.age = age;
        this.tList = tList;
    }

    @NonNull
    @Override
    public String toString() {
        return "name:" + this.name + "#age:" + this.age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public List<T> gettList() {
        return tList;
    }

    public void settList(List<T> tList) {
        this.tList = tList;
    }
}
