package com.sample.support.config;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 作者 Aaron Zhao
 * 时间 2020/4/3 9:50
 * 文件 DTFConfig.java
 * 描述 Database table field config
 */
public class DTFConfig {
    /* 数据库信息：数据库名称、数据库版本号 */
    public static final String DB_NAME = "db_sample";
    public static final int DB_VERSION = 1;

    /* 学生信息表 */
    public final static String T_STUDENT = "t_student";
    public final static String T_STUDENT_NAME = "name";
    public final static String T_STUDENT_AGE = "age";
    public final static String T_STUDENT_SEX = "sex";
    public final static String T_STUDENT_INTEREST = "interest";

    /* 变量信息 */
    private static List<String> mTableList = new LinkedList<>();
    private static List<String> mSQLList = new LinkedList<>();

    /* 不可被实例化 */
    private DTFConfig() {}

    /**
     * 获取需要创建的表名
     * @return 返回需要创建的表名
     */
    public static List<String> getTableList() {
        if (mTableList.size() > 0) {
            mTableList.clear();
        }

        mTableList.add(T_STUDENT);

        return mTableList;
    }

    /**
     * 获取需要创建表的语句
     * @return  返回需要创建表的语句
     */
    public static List<String> getSQLList() {
        if (mSQLList.size() > 0) {
            mSQLList.clear();
        }

        String sqlCreateStudent = String.format("CREATE TABLE %s (%s text, %s INTEGER, %s INTEGER, %s text)"
                , T_STUDENT, T_STUDENT_NAME, T_STUDENT_AGE, T_STUDENT_SEX, T_STUDENT_INTEREST);
        mSQLList.add(sqlCreateStudent);

        return mSQLList;
    }
}
