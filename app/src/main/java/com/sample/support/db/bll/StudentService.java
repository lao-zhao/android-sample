package com.sample.support.db.bll;

import android.content.Context;
import android.database.Cursor;

import com.sample.support.db.dal.StudentDao;
import com.sample.support.db.model.Student;

import java.util.List;

/**
 * 作者 Aaron Zhao
 * 时间 2020/4/3 10:15
 * 文件 StudentService.java
 * 描述
 */
public class StudentService {
    public static boolean insert(Context context, Student student) {
        return new StudentDao(context).insert(student);
    }

    public static boolean insert(Context context, List<Student> studentList) {
        return new StudentDao(context).insert(studentList);
    }

    public static boolean update(Context context, Student student) {
        return new StudentDao(context).update(student);
    }

    public static boolean delete(Context context, Student student) {
        return new StudentDao(context).delete(student);
    }

    public static List<Student> getStudentList(Context context) {
        return new StudentDao(context).getStudentList();
    }

    public static Cursor getStudents(Context context) {
        return new StudentDao(context).getStudents();
    }

    public static void closeDatabase(Context context) {
        new StudentDao(context).closeDatabase();
    }
}
