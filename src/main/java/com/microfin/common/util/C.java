package com.microfin.common.util;

/**
 * 系统配置参数（运行后加载）
 * @author guanxp
 * 2015/3/4
 */
public class C {

    // 系统properties文件名称
    private static final String FILE_COMMON = "common";
    
    /**
     * 系统初始化时执行
     */
    public static void initialize() {
        
        // common.properties
        ENCODE_UTF_8                   =                 Properties.getValue(FILE_COMMON,     "ENCODE_UTF_8",                   ""           );
        ENCODE_ISO_88591               =                 Properties.getValue(FILE_COMMON,     "ENCODE_ISO_88591",               ""           );
        MAX_UPLOAD_SIZE                = Long.valueOf(   Properties.getValue(FILE_COMMON,     "MAX_UPLOAD_SIZE",                "0"         ));
        CONTENT_TYPE_HTML              =                 Properties.getValue(FILE_COMMON,     "CONTENT_TYPE_HTML",              ""           );
        CONTENT_TYPE_JSON              =                 Properties.getValue(FILE_COMMON,     "CONTENT_TYPE_JSON",              ""           );
        CONTENT_TYPE_STREAM            =                 Properties.getValue(FILE_COMMON,     "CONTENT_TYPE_STREAM",            ""           );
        CONTENT_TYPE_URLENCODED        =                 Properties.getValue(FILE_COMMON,     "CONTENT_TYPE_URLENCODED",        ""           );
        CHARSET_UTF_8                  =                 Properties.getValue(FILE_COMMON,     "CHARSET_UTF_8",                  ""           );
        CONTENT_TYPE_HTML_UTF_8        =                 Properties.getValue(FILE_COMMON,     "CONTENT_TYPE_HTML_UTF_8",        ""           );
        CONTENT_TYPE_JSON_UTF_8        =                 Properties.getValue(FILE_COMMON,     "CONTENT_TYPE_JSON_UTF_8",        ""           );
        CONTENT_TYPE_STREAM_UTF_8      =                 Properties.getValue(FILE_COMMON,     "CONTENT_TYPE_STREAM_UTF_8",      ""           );
        CONTENT_TYPE_URLENCODED_UTF_8  =                 Properties.getValue(FILE_COMMON,     "CONTENT_TYPE_URLENCODED_UTF_8",  ""           );
        CONTENT_DISPOSITION            =                 Properties.getValue(FILE_COMMON,     "CONTENT_DISPOSITION",            ""           );
        YYYYMMDDHHMMSSSSS_24           =                 Properties.getValue(FILE_COMMON,     "YYYYMMDDHHMMSSSSS_24",           ""           );
        YYYYMMDDHHMMSSSSS_12           =                 Properties.getValue(FILE_COMMON,     "YYYYMMDDHHMMSSSSS_12",           ""           );
        UPLOAD_PROGRESS_LISTENER_KEY   =                 Properties.getValue(FILE_COMMON,     "UPLOAD_PROGRESS_LISTENER_KEY",   ""           );
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------
// common.properties
//--------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * 编码：UTF-8
     */
    public  static String     ENCODE_UTF_8;
    
    /**
     * 编码：ISO-8859-1
     */
    public  static String     ENCODE_ISO_88591;
    
    /**
     * 单个文件最大上传大小
     */
    public  static long       MAX_UPLOAD_SIZE;
    
    /**
     * 网页内容的类型：text/html
     */
    public  static String     CONTENT_TYPE_HTML;
    
    /**
     * 网页内容的类型：application/json
     */
    public  static String     CONTENT_TYPE_JSON;
    
    /**
     * 网页内容的类型：application/octet-stream
     */
    public  static String     CONTENT_TYPE_STREAM;
    
    /**
     * 网页内容的类型：application/x-www-form-urlencoded
     */
    public  static String     CONTENT_TYPE_URLENCODED;
    
    /**
     * 网页内容的编码方式：UTF-8
     */
    public  static String     CHARSET_UTF_8;
    
    /**
     * 网页内容的类型和编码方式：text/html; charset=UTF-8
     */
    public  static String     CONTENT_TYPE_HTML_UTF_8;
    
    /**
     * 网页内容的类型和编码方式：application/json; charset=UTF-8
     */
    public  static String     CONTENT_TYPE_JSON_UTF_8;
    
    /**
     * 网页内容的类型和编码方式：application/octet-stream; charset=UTF-8
     */
    public  static String     CONTENT_TYPE_STREAM_UTF_8;
    
    /**
     * 网页内容的类型和编码方式：application/x-www-form-urlencoded; charset=UTF-8
     */
    public  static String     CONTENT_TYPE_URLENCODED_UTF_8;
    
    /**
     * MIME协议扩展：设置文件下载时要保存的默认文件名 attachment; filename=%s
     */
    public  static String     CONTENT_DISPOSITION;

    /**
     * 时间戳（24小时制）：yyyyMMddHHmmssSSS
     */
    public static String      YYYYMMDDHHMMSSSSS_24;
    
    /**
     * 时间戳（12小时制）：yyyyMMddhhmmssSSS
     */
    public static String      YYYYMMDDHHMMSSSSS_12;
    
    /**
     * 文件上传进度监听类在session对象中的key值
     */
    public static String      UPLOAD_PROGRESS_LISTENER_KEY;
}
