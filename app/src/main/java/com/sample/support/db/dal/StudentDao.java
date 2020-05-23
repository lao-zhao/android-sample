package com.sample.support.db.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.name.libs.utils.CommonUtil;
import com.name.libs.utils.LogUtil;
import com.name.libs.utils.singleton.SQLiteUtil;
import com.sample.support.config.AppConfig;
import com.sample.support.config.DTFConfig;
import com.sample.support.db.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者 Aaron Zhao
 * 时间 2020/4/3 9:46
 * 文件 StudentDao.java
 * 描述
 */
public class StudentDao {
    private SQLiteUtil mSQLiteUtil;

    public StudentDao(Context context) {
        mSQLiteUtil = SQLiteUtil.getInstance(context, DTFConfig.DB_NAME, DTFConfig.DB_VERSION);
        mSQLiteUtil.setTableList(DTFConfig.getTableList());
        mSQLiteUtil.setSQLList(DTFConfig.getSQLList());
    }

    public boolean insert(Student student) {
        boolean result = false;

        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DTFConfig.T_STUDENT_NAME, student.getName());
            contentValues.put(DTFConfig.T_STUDENT_AGE, student.getAge());
            contentValues.put(DTFConfig.T_STUDENT_SEX, student.getSex());
            contentValues.put(DTFConfig.T_STUDENT_INTEREST, student.getInterest());

            result = mSQLiteUtil.getDatabase().insert(DTFConfig.T_STUDENT, null, contentValues) > 0;
        } catch (Exception e) {
            LogUtil.exportLog(CommonUtil.getCName(new Exception()), CommonUtil.getMName(new Exception()), e.getMessage(), AppConfig.IS_LOG_SAVE);
        } finally {
            mSQLiteUtil.close();
        }

        return result;
    }

    public boolean insert(List<Student> studentList) {
        boolean result = false;

        try {
            mSQLiteUtil.getDatabase().beginTransaction();
            String insertSQL = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES(?, ?, ?, ?)"
                    , DTFConfig.T_STUDENT, DTFConfig.T_STUDENT_NAME, DTFConfig.T_STUDENT_AGE, DTFConfig.T_STUDENT_SEX, DTFConfig.T_STUDENT_INTEREST);
            for (Student student : studentList) {
                mSQLiteUtil.getDatabase().execSQL(insertSQL, new Object[] {student.getName(), student.getAge(), student.getSex(), student.getInterest()});
            }
            mSQLiteUtil.getDatabase().setTransactionSuccessful();
            mSQLiteUtil.getDatabase().endTransaction();

            result = true;
        } catch (Exception e) {
            LogUtil.exportLog(CommonUtil.getCName(new Exception()), CommonUtil.getMName(new Exception()), e.getMessage(), AppConfig.IS_LOG_SAVE);
        } finally {
            mSQLiteUtil.close();
        }

        return result;
    }

    public boolean update(Student student) {
        return false;
    }

    public boolean delete(Student student) {
        boolean result = false;

        try {
            String deleteSQL = String.format("DELETE FROM %s", DTFConfig.T_STUDENT);
            mSQLiteUtil.getDatabase().execSQL(deleteSQL);

            result = true;
        } catch (Exception e) {
            LogUtil.exportLog(CommonUtil.getCName(new Exception()), CommonUtil.getMName(new Exception()), e.getMessage(), AppConfig.IS_LOG_SAVE);
        } finally {
            mSQLiteUtil.close();
        }

        return result;
    }

    public List<Student> getStudentList() {
        List<Student> studentList = new ArrayList<>();

        try {
            String querySQL = String.format("SELECT * FROM %s", DTFConfig.T_STUDENT);
            Cursor cursor = mSQLiteUtil.getDatabase().rawQuery(querySQL, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    Student student = new Student();
                    student.setName(cursor.getString(0));
                    student.setAge(cursor.getInt(1));
                    student.setSex(cursor.getInt(2));
                    student.setInterest(cursor.getString(3));

                    studentList.add(student);
                }
            }
        } catch (Exception e) {
            LogUtil.exportLog(CommonUtil.getCName(new Exception()), CommonUtil.getMName(new Exception()), e.getMessage(), AppConfig.IS_LOG_SAVE);
        } finally {
            mSQLiteUtil.close();
        }

        return studentList;
    }

    public Cursor getStudents() {
        Cursor cursor = null;

        try {
            String querySQL = String.format("SELECT * FROM %s", DTFConfig.T_STUDENT);
            cursor = mSQLiteUtil.getDatabase().rawQuery(querySQL, null);
        } catch (Exception e) {
            LogUtil.exportLog(CommonUtil.getCName(new Exception()), CommonUtil.getMName(new Exception()), e.getMessage(), AppConfig.IS_LOG_SAVE);
        } finally {
//            mSQLiteUtil.close();
        }

        return cursor;
    }

    public void closeDatabase() {
        mSQLiteUtil.close();
    }
}
