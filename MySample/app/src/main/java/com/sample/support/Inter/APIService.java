package com.sample.support.Inter;

import com.sample.support.config.AppConfig;

import java.util.Map;

import io.reactivex.Observable;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

/**
 * 作者 Aaron Zhao
 * 时间 2020/5/27 11:43
 * 文件 APIService.java
 * 描述
 */
public interface APIService {
    /**
     * 将参数信息使用Map进行传值
     * 表单Body：application/x-www-form-urlencoded
     * */
    @FormUrlEncoded
    @POST(AppConfig.URL_RELATED_QUERY)
    Observable<String> request(@FieldMap Map<String, Object> params);

    /**
     * 将参数信息使用Map进行传值
     * 表单Body：application/x-www-form-urlencoded
     * */
    @FormUrlEncoded
    @POST(AppConfig.URL_REQUEST_PICTURES)
    Observable<String> requestPictureList(@FieldMap Map<String, Object> params);

    /**
     * 将参数信息作为自定义Body进行传值
     * */
    @POST(AppConfig.URL_RELATED_QUERY)
    Observable<String> request(@Body RequestBody requestBody);

    /**
     * 将参数信息使用具体的参数进行传值
     * 表单Body：application/x-www-form-urlencoded
     * */
    @FormUrlEncoded
    @POST(AppConfig.URL_RELATED_QUERY)
    Observable<String> request(@Field("code") String code, @Field("userName") String userName);

    /**
     * 上传文件
     * 此Body支持文件上传与下载：multipart/form-data
     * */
    @Multipart
    @POST(AppConfig.URL_UPLOAD_FILE)
    Observable<String> upload(@FieldMap Map<String, Object> params);
}
