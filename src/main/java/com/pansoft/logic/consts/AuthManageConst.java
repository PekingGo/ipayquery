package com.pansoft.logic.consts;

/**
 * 权限管理固定常量
 *
 * @author lipeng 2015/3/18
 */
public class AuthManageConst {

    /** 业务路径：空 */
    public static final String PATH_EMPTY = "";

    /** 业务路径：权限管理处理 */
    public static final String PATH_AUTH_MANAGE = "/authManage";

    /** 业务路径：检索角色信息处理 */
    public static final String PATH_QUERY = "/query";

    /** 业务路径：保存角色信息处理 */
    public static final String PATH_SAVE = "/save";

    /** 业务路径：删除角色信息处理 */
    public static final String PATH_DELETE = "/delete";

    /** 业务路径：取得用户权限处理 */
    public static final String PATH_AUTH = "/auth";

    /** 业务路径：保存角色权限处理 */
    public static final String PATH_SAVE_AUTH = "/saveAuth";

    /** 跳转页面：权限管理页面 */
    public static final String FORWARD_AUTH_MANAGE = "authManage";

    /** 后台处理回传参数键值(result) */
    public static final String ATTRIBUTE_NAME_RESULT = "result";

    /** 后台处理回传参数键值(msg) */
    public static final String ATTRIBUTE_NAME_MSG = "msg";

    /** 后台处理回传参数键值(msg) */
    public static final String ATTRIBUTE_NAME_MODEL_CLOSE = "modelClose";

    /** 后台处理回传参数键值(f_no) */
    public static final String ATTRIBUTE_NAME_F_NO = "f_no";

    /** 后台处理回传参数键值(id) */
    public static final String ATTRIBUTE_NAME_ID = "id";

    /** 后台处理回传参数键值(f_name) */
    public static final String ATTRIBUTE_NAME_F_NAME = "f_name";

    /** 后台处理回传参数键值(name) */
    public static final String ATTRIBUTE_NAME_NAME = "name";

    /** 后台处理回传参数键值(subFunction) */
    public static final String ATTRIBUTE_NAME_SUB_FUNCTION = "subFunction";

    /** 后台处理回传参数键值(children) */
    public static final String ATTRIBUTE_NAME_CHILDREN = "children";

    /** 后台处理回传参数键值(hasFunction) */
    public static final String ATTRIBUTE_NAME_HAS_FUNCTION = "hasFunction";

    /** 后台处理回传参数键值(checked) */
    public static final String ATTRIBUTE_NAME_CHECKED = "checked";

    /** 后台处理回传参数键值(zTreeNodes) */
    public static final String ATTRIBUTE_NAME_Z_TREE_NODES = "zTreeNodes";

    /** 后台处理回传参数键值(r_id) */
    public static final String ATTRIBUTE_NAME_R_ID = "r_id";

    /** 更新结果区分(0:成功) */
    public static final String UPDATE_FLAG_SUCCESS_ZERO = "0";

    /** 更新结果区分(1:角色名已存在) */
    public static final String UPDATE_FLAG_FAILURE_ONE = "1";

    /** 更新结果区分(2:角色已被删除) */
    public static final String UPDATE_FLAG_FAILURE_TWO = "2";

}
