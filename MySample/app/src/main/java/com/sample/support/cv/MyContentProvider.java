package com.sample.support.cv;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.name.libs.utils.DialogUtil;
import com.sample.support.db.bll.StudentService;
import com.sample.support.db.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者 Aaron Zhao
 * 时间 2020/4/3 13:29
 * 文件 MyContentProvider.java
 * 描述
 */
public class MyContentProvider extends ContentProvider {
    private Context mContext;

    // 设置ContentProvider的唯一标识
    public static final String AUTOHORITY = "com.sample.mycontentprovider";

    public static final int Student_Code = 1;

    private static final UriMatcher mMatcher;

    static {
        // 初始化
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        // 若URI资源路径 = content://com.sample.mycontentprovider/student ，Student_Code
        mMatcher.addURI(AUTOHORITY, "student", Student_Code);
    }

    @Override
    public boolean onCreate() {
        mContext = getContext();

        initStudents();

        return true;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        if (mMatcher.match(uri) != Student_Code) {
            DialogUtil.showToast(mContext, "没有找到对应的信息");
            return null;
        }

        // 新增一条数据
        insertStudent(values);

        // 当该URI的ContentProvider数据发生变化时，通知外界（即访问该ContentProvider数据的访问者）
        mContext.getContentResolver().notifyChange(uri, null);

        return uri;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
        if (mMatcher.match(uri) != Student_Code) {
            DialogUtil.showToast(mContext, "没有找到对应的信息");
            return null;
        }

        return StudentService.getStudents(mContext);
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable Bundle queryArgs, @Nullable CancellationSignal cancellationSignal) {
        return super.query(uri, projection, queryArgs, cancellationSignal);
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs,
                        @Nullable String sortOrder, @Nullable CancellationSignal cancellationSignal) {
        return super.query(uri, projection, selection, selectionArgs, sortOrder, cancellationSignal);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        if (mMatcher.match(uri) != Student_Code) {
            DialogUtil.showToast(mContext, "没有找到对应的信息");
            return 0;
        }

        return StudentService.delete(mContext, null) ? 1 : 0;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Bundle call(@NonNull String method, @Nullable String arg, @Nullable Bundle extras) {
        StudentService.closeDatabase(mContext);
        return super.call(method, arg, extras);
    }

    private void insertStudent(ContentValues contentValues) {
        Student student = new Student();
        student.setName(contentValues.getAsString("name"));
        student.setAge(contentValues.getAsInteger("age"));
        student.setSex(contentValues.getAsInteger("sex"));
        student.setInterest(contentValues.getAsString("interest"));

        StudentService.insert(mContext, student);
    }

    private void initStudents() {
        List<Student> studentList = new ArrayList<>();

        Student student = new Student();
        student.setName("Aaron");
        student.setAge(38);
        student.setSex(0);
        student.setInterest("吃喝玩乐");
        studentList.add(student);

        student = new Student();
        student.setName("Wendy");
        student.setAge(34);
        student.setSex(1);
        student.setInterest("买买买");
        studentList.add(student);

        StudentService.insert(mContext, studentList);
    }
}
