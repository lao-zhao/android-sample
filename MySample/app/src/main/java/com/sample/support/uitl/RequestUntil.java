package com.sample.support.uitl;

import com.name.libs.utils.RetrofitUtil;
import com.sample.support.Inter.APIService;
import com.sample.support.config.AppConfig;

/**
 * 作者 Aaron Zhao
 * 时间 2020/5/28 14:56
 * 文件 RequestUntil.java
 * 描述
 */
public class RequestUntil {
    public static APIService getApiService(String baseUrl) {
        APIService apiService = RetrofitUtil.getDefault(baseUrl).create(APIService.class);
        return apiService;
    }
}
