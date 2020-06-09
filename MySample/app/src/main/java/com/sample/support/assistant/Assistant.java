package com.sample.support.assistant;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 作者 Aaron Zhao
 * 时间 2020/5/27 12:15
 * 文件 Assistant.java
 * 描述
 */
public class Assistant {
    public static RequestBody getRequestJsonBody(String req) {
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), req);
    }

    public static RequestBody getRequestXmlBody(String req) {
        return RequestBody.create(MediaType.parse("text/xml; charset=utf-8"), req);
    }
}
