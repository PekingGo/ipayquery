package com.microfin.common.util;

/**
 * 固定常量
 *
 * @author lipeng 2015/3/16
 */
public class Const {

    /** 用户信息session键值 */
    public static final String SESSION_USER = "sessionUser";

    /** 用户权限信息session键值 */
    public static final String SESSION_USER_RIGHTS = "sessionUserRights";

    /** 角色权限信息session键值 */
    public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";

    /** 菜单一览信息session键值 */
    public static final String SESSION_MENU_LIST = "sessionMenuList";

    /** 不对匹配该值的访问路径拦截（正则） */
    public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(code)).*";
    /** 匹配该值的访问路径拦截（正则）:该路径为查询请求 */
    public static final String QUERY_INTERCEPTOR_PATH = ".*/((q)|(cq))/*";

    /** 标签区分(1:课程) */
    public static final int TAG_KEY_COURSE = 1;

    /** 标签区分(2:班级) */
    public static final int TAG_KEY_CLASS = 2;

    /** 标签区分(3:终身学习) */
    public static final int TAG_KEY_LIFT_LEARN = 3;

    /** 标签区分(4:证书) */
    public static final int TAG_KEY_CERT = 4;

    /** 性别(1:男) */
    public static final int SEX_KEY_MALE = 1;

    /** 性别(2:女) */
    public static final int SEX_KEY_FEMALE = 2;

    /** 性别(1:男) */
    public static final String SEX_VALUE_MALE = "男";

    /** 性别(2:女) */
    public static final String SEX_VALUE_FEMALE = "女";

    /** 字符串(空) */
    public static final String STRING_EMPTY = "";

    /** 字符串(空白) */
    public static final String STRING_BLANK = " ";

    /** 字符串(,) */
    public static final String STRING_COMMA = ",";

    /** 字符串(~) */
    public static final String STRING_WAVY_LINE = "~";

    /** 字符串(-) */
    public static final String STRING_LINE = "-";

    /** 字符串(_) */
    public static final String STRING_UNDER_LINE = "_";

    /** 每日开始时间点(00:00:00) */
    public static final String TIME_START = "00:00:00";

    /** 每日结束时间点(23:59:59) */
    public static final String TIME_END = "23:59:59";

    /** 缺省日期格式(yyyy-MM-dd) */
    public static final String DEFAULT_DATE_FORMAT_ONE = "yyyy-MM-dd";

    /** 缺省日期格式(yyyy/MM/dd) */
    public static final String DEFAULT_DATE_FORMAT_TWO = "yyyy/MM/dd";

    /** 缺省日期格式(yyyyMMdd) */
    public static final String DEFAULT_DATE_FORMAT_THREE = "yyyyMMdd";

    /** 缺省日期格式(yyyy.MM.dd) */
    public static final String DEFAULT_DATE_FORMAT_FOUR = "yyyy.MM.dd";

    /** 缺省时间格式,精确到秒 (HH:mm:ss) */
    public static final String DEFAULT_TIME_FORMAT_ONE = "HH:mm";

    /** 缺省时间格式,精确到秒 (HHmmss) */
    public static final String DEFAULT_TIME_FORMAT_TWO = "HHmm";

    /** 缺省时间格式,精确到秒 (HH:mm:ss) */
    public static final String DEFAULT_TIME_FORMAT_SEC_ONE = "HH:mm:ss";

    /** 缺省时间格式,精确到秒 (HHmmss) */
    public static final String DEFAULT_TIME_FORMAT_SEC_TWO = "HHmmss";

    /** 缺省长日期格式(yyyy/MM/dd HH:mm) */
    public static final String DEFAULT_DATETIME_FORMAT_ONE = "yyyy/MM/dd HH:mm";

    /** 缺省长日期格式(yyyy-MM-dd HH:mm) */
    public static final String DEFAULT_DATETIME_FORMAT_TWO = "yyyy-MM-dd HH:mm";

    /** 缺省长日期格式(yyyyMMddHHmm) */
    public static final String DEFAULT_DATETIME_FORMAT_THREE = "yyyyMMddHHmm";

    /** 缺省长日期格式,精确到秒(yyyy-MM-dd HH:mm:ss) */
    public static final String DEFAULT_DATETIME_FORMAT_SEC_ONE = "yyyy-MM-dd HH:mm:ss";

    /** 缺省长日期格式2,精确到秒(yyyy/MM/dd HH:mm:ss) */
    public static final String DEFAULT_DATETIME_FORMAT_SEC_TWO = "yyyy/MM/dd HH:mm:ss";

    /** 缺省长日期格式2,精确到秒(yyyyMMddHHmmss) */
    public static final String DEFAULT_DATETIME_FORMAT_SEC_THREE = "yyyyMMddHHmmss";

    /** 缺省长日期格式,精确到毫秒(yyyy-MM-dd HH:mm:ss.SSS) */
    public static final String DEFAULT_DATETIME_FORMAT_MS_ONE = "yyyy-MM-dd HH:mm:ss.SSS";

    /** 缺省长日期格式,精确到毫秒(yyyy/MM/dd HH:mm:ss.SSS) */
    public static final String DEFAULT_DATETIME_FORMAT_MS_TWO = "yyyy/MM/dd HH:mm:ss.SSS";

    /** 缺省长日期格式,精确到毫秒(yyyyMMddHHmmssSSS) */
    public static final String DEFAULT_DATETIME_FORMAT_MS_THREE = "yyyyMMddHHmmssSSS";

    /** 编码格式(UTF-8) */
    public static final String CODING_FORMAT_UTF8 = "UTF-8";

    /** 业务处理重定向 */
    public static final String LOGIC_PATH_REDIRECT = "redirect:";

    /** 业务处理文件分隔标志 */
    public static final String LOGIC_PATH_SLASH = "/";

    /** 业务处理文件后缀 */
    public static final String LOGIC_PATH_SUFFIX = ".html";

    /** jqgrid页面参数信息键值(page) */
    public static final String JQGRID_PARAM_NAME_PAGE = "page";

    /** jqgrid页面参数信息键值(rows) */
    public static final String JQGRID_PARAM_NAME_ROWS = "rows";

    /** jqgrid页面参数信息键值(sidx) */
    public static final String JQGRID_PARAM_NAME_SIDX = "sidx";

    /** jqgrid页面参数信息键值(sord) */
    public static final String JQGRID_PARAM_NAME_SORD = "sord";

    /** 文件路径分隔标志 */
    public static final String FILE_PATH_SLASH = "/";

    /** 文件名后缀分隔标志 */
    public static final String FILE_NAME_SUFFIX = ".";

    /** 文件名连接用下划线标志 */
    public static final String FILE_NAME_UNDERLINE = "_";

    /** 系统区分(aigoums) */
    public static final String SYSTEM_DIF_AIGOUMS = "aigoums";

}
