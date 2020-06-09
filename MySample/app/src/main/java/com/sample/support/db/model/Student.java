package com.sample.support.db.model;

/**
 * 作者 Aaron Zhao
 * 时间 2020/4/3 9:41
 * 文件 Student.java
 * 描述
 */
public class Student {
    private String name;     // 姓名
    private int age;         // 年龄
    private int sex;         // 性别
    private String interest; // 兴趣爱好

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }
}
