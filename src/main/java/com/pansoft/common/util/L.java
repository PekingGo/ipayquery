package com.pansoft.common.util;

import net.sf.json.JSONArray;

/**
 * 系统语言参数（运行后加载）
 * 
 * @author guanxp 2015/3/4
 */
public class L {

    // 系统properties文件名称
    private static final String FILE_lang_ZH_CN = "language-zh-CN";

    /**
     * 系统初始化时执行
     */
    public static void initialize() {

        // language-zh-CN.properties
        UPLOAD_FILE_NOT_FOUND = Properties.getValue(FILE_lang_ZH_CN, "UPLOAD_FILE_NOT_FOUND", "");
        UPLOAD_FOLDER_ISNULL = Properties.getValue(FILE_lang_ZH_CN, "UPLOAD_FOLDER_ISNULL", "");
        UPLOAD_FILE_SIZE_ZERO = Properties.getValue(FILE_lang_ZH_CN, "UPLOAD_FILE_SIZE_ZERO", "");
        UPLOAD_FILE_SIZE_EXCEED = Properties.getValue(FILE_lang_ZH_CN, "UPLOAD_FILE_SIZE_EXCEED", "");
        UPLOAD_FILE_TIME_USED = Properties.getValue(FILE_lang_ZH_CN, "UPLOAD_FILE_TIME_USED", "");
        RESPONSE_JSON_DATA = Properties.getValue(FILE_lang_ZH_CN, "RESPONSE_JSON_DATA", "");
        UPLOAD_FILE_SUCCESS = Properties.getValue(FILE_lang_ZH_CN, "UPLOAD_FILE_SUCCESS", "");
        UPLOAD_FILE_EXCEPTION = Properties.getValue(FILE_lang_ZH_CN, "UPLOAD_FILE_EXCEPTION", "");
        SESSION_NOT_FOUND = Properties.getValue(FILE_lang_ZH_CN, "SESSION_NOT_FOUND", "");
        UPLOAD_FILE_LISTENER_ISNULL = Properties.getValue(FILE_lang_ZH_CN, "UPLOAD_FILE_LISTENER_ISNULL", "");
        UPLOAD_FILE_PROGRESS = Properties.getValue(FILE_lang_ZH_CN, "UPLOAD_FILE_PROGRESS", "");
        DOWNLOAD_FILE_NOT_FOUND = Properties.getValue(FILE_lang_ZH_CN, "DOWNLOAD_FILE_NOT_FOUND", "");
        DOWNLOAD_FILE_TIME_USED = Properties.getValue(FILE_lang_ZH_CN, "DOWNLOAD_FILE_TIME_USED", "");
        DOWNLOAD_FILE_PATH_ISNULL = Properties.getValue(FILE_lang_ZH_CN, "DOWNLOAD_FILE_PATH_ISNULL", "");
        RESOLVE_MULTIPART_EXCEPTION = Properties.getValue(FILE_lang_ZH_CN, "RESOLVE_MULTIPART_EXCEPTION", "");
        DELETE_FILE_INFO_NOT_FOUND = Properties.getValue(FILE_lang_ZH_CN, "DELETE_FILE_INFO_NOT_FOUND", "");
        DELETE_FILE_NOT_FOUND = Properties.getValue(FILE_lang_ZH_CN, "DELETE_FILE_NOT_FOUND", "");
        DELETE_FILE_SUCCESS = Properties.getValue(FILE_lang_ZH_CN, "DELETE_FILE_SUCCESS", "");
        DELETE_FILE_FAILURE = Properties.getValue(FILE_lang_ZH_CN, "DELETE_FILE_FAILURE", "");
        DELETE_FILE_TIME_USED = Properties.getValue(FILE_lang_ZH_CN, "DELETE_FILE_TIME_USED", "");
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------
    // language-zh-CN.properties
    // --------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * 上传文件不存在
     */
    public static String UPLOAD_FILE_NOT_FOUND;

    /**
     * 上传文件所属目录名为空
     */
    public static String UPLOAD_FOLDER_ISNULL;

    /**
     * 上传文件【%s】是空文件
     */
    public static String UPLOAD_FILE_SIZE_ZERO;

    /**
     * 上传文件【%s】超过最大限制
     */
    public static String UPLOAD_FILE_SIZE_EXCEED;

    /**
     * 文件【%s】上传花费了 %d 毫秒
     */
    public static String UPLOAD_FILE_TIME_USED;

    /**
     * 向客户端返回的JSON数据：%s
     */
    public static String RESPONSE_JSON_DATA;

    /**
     * 上传文件成功
     */
    public static String UPLOAD_FILE_SUCCESS;

    /**
     * 上传文件过程中出现异常
     */
    public static String UPLOAD_FILE_EXCEPTION;

    /**
     * 找不到session对象
     */
    public static String SESSION_NOT_FOUND;

    /**
     * 文件上传监听器类为空
     */
    public static String UPLOAD_FILE_LISTENER_ISNULL;

    /**
     * 文件的上传进度是 %f %%
     */
    public static String UPLOAD_FILE_PROGRESS;

    /**
     * 下载文件不存在
     */
    public static String DOWNLOAD_FILE_NOT_FOUND;

    /**
     * 文件【%s】下载花费了 %d 毫秒
     */
    public static String DOWNLOAD_FILE_TIME_USED;

    /**
     * 下载文件路径为空
     */
    public static String DOWNLOAD_FILE_PATH_ISNULL;

    /**
     * Multipart解析过程中出现异常
     */
    public static String RESOLVE_MULTIPART_EXCEPTION;

    /**
     * 找不到要删除的文件信息
     */
    public static String DELETE_FILE_INFO_NOT_FOUND;

    /**
     * 找不到要删除的文件【%s】
     */
    public static String DELETE_FILE_NOT_FOUND;

    /**
     * 删除文件成功
     */
    public static String DELETE_FILE_SUCCESS;

    /**
     * 删除文件【%s】失败
     */
    public static String DELETE_FILE_FAILURE;

    /**
     * 文件删除花费了 %d 毫秒
     */
    public static String DELETE_FILE_TIME_USED;

    /**
     * 南开大学学院列表
     */
    public static JSONArray NK_COLLEGES;
}
