package com.pansoft.logic.consts;

/**
 * 登录处理固定常量
 *
 * @author lipeng 2015/3/18
 */
public class LoginConst {

    /** 页面参数键值(USERNAME) */
    public static final String PARAM_NAME_USERNAME = "USERNAME";

    /** 页面参数键值(USERPWD) */
    public static final String PARAM_NAME_USER_PWD = "USERPWD";

    /** 业务路径：登录处理 */
    public static final String PATH_LOGIN = "/login";

    /** 业务路径：系统首页处理 */
    public static final String PATH_INDEX = "/index";

    /** 业务路径：用户注销处理 */
    public static final String PATH_LOGOUT = "/logout";

    /** 业务路径：获取跳转用登录用户信息处理 */
    public static final String PATH_SWITCH_SYSTEM = "/switchSystem";

    /** 跳转页面：登录页面 */
    public static final String FORWARD_LOGIN = "login";

    /** 跳转页面：默认页面。当用户所授权的所有功能菜单都没有配置菜单路径时，直接跳转到common/default.jsp页面。 */
    public static final String FORWARD_DEFAULT = "common/default";

    /** 跳转页面：自动跳转页面 */
    public static final String FORWARD_INDEX = "index";

    /** 跳转页面：系统切换页面 */
    public static final String FORWARD_AUTHORIZATION = "authorization";

    /** 后台处理回传参数键值(errInfo) */
    public static final String ATTRIBUTE_NAME_ERRINFO = "errInfo";

    /** 后台处理回传参数键值(u_account) */
    public static final String ATTRIBUTE_NAME_U_ACCOUNT = "u_account";

    /** 后台处理回传参数键值(u_password) */
    public static final String ATTRIBUTE_NAME_U_PASSWORD = "u_password";

    /** 后台处理回传参数键值(result) */
    public static final String ATTRIBUTE_NAME_RESULT = "result";

}
