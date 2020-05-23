package com.name.libs.assists.sslcers;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * Created by Haosheng.zhang on 2018/6/2.
 * SSL证书过滤（忽略）
 */

public class TrustAllHostnameVerifier implements HostnameVerifier {
    @Override
    public boolean verify(String s, SSLSession sslSession) {
        return true;
    }
}
