package com.sample.support.config;

/**
 * 作者 Aaron Zhao
 * 时间 2019/1/8 15:35
 * 文件 AppConfig.java
 * 描述 项目中使用的常量配置
 */
public final class AppConfig {
    /* 系统产生的异常日志是否保存：true-保存；false-不保存 */
    public final static boolean IS_LOG_SAVE = true;

    // 缓存信息


    /* 美孚关联查询接口 */
    public static final String BASE_URL = "https://em.winsafe.cn/";
    public static final String URL_RELATED_QUERY = "intenralapp/query.do"; //关联信息 查询接口
    public static final String URL_UPLOAD_FILE = ""; //关联信息 查询接口

    /* 神象查询野山参图片接口 */
    public static final String BASE_URL_SX = "http://shenxiang.winsafe.cn/is/";
    public static final String URL_REQUEST_PICTURES = "scanner/productSearchAction.do"; //获取野山参图片信息接口
}
