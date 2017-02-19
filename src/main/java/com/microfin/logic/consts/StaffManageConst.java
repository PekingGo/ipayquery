package com.microfin.logic.consts;

/**
 * 员工管理常量
 *
 * @author zhujiarong 2015/4/17
 *
 */
public class StaffManageConst {
    /** 业务路径：空 */
    public static final String PATH_EMPTY = "";

    /** 业务路径：员工管理处理 */
    public static final String PATH_STAFF_MANAGE = "/staffManage";

    /** 业务路径：取得所有员工列表 */
    public static final String PATH_SELECT_STAFF = "/selectStaff";

    /** 业务路径：取得所有角色列表 */
    public static final String PATH_SELECT_ALL_ROLE = "/selectAllRole";

    /** 业务路径：取得员工对应的角色 */
    public static final String PATH_SELECT_STAF_ROLE = "/selectStafRole";

    /** 业务路径：同步员工 */
    public static final String PATH_SYNC_STAFF = "/syncStaff";

    /** 业务路径：新增角色保存 */
    public static final String PATH_SAVE_ROLE = "/saveRole";

    /** 业务路径：新增员工信息 */
    public static final String PATH_SAVE_STAFF = "/saveStaff";

    /** 业务路径：删除员工信息 */
    public static final String PATH_DELETE_STAFF = "/deleteStaff";

    /** 业务路径：删除项目操作 */
    public static final String PATH_DELETE_PROJECT = "/deleteProject";

    /** 业务路径：删除角色操作 */
    public static final String PATH_DELETE_ROLE = "/deleteRole";

    /** 跳转页面：员工管理 */
    public static final String FORWARD_STAFF_MANAGE = "staffManage";

    /** 后台处理回传参数键值(result) */
    public static final String ATTRIBUTE_NAME_RESULT = "result";

    /** 后台处理回传参数键值(msg) */
    public static final String ATTRIBUTE_NAME_MSG = "msg";

    /** 后台处理回传参数键值（f_zgbh） */
    public static final String ATTRIBUTE_F_ZGBH = "f_zgbh";

    /** 后台处理回传参数键值（f_name） */
    public static final String ATTRIBUTE_F_NAME = "f_name";

    /** 后台处理回传参数键值（f_xmmc） */
    public static final String ATTRIBUTE_F_XMMC = "f_xmmc";

    /** 后台处理回传参数键值（r_name） */
    public static final String ATTRIBUTE_R_NAME = "r_name";

    /** 后台处理回传参数键值（staffNumber） */
    public static final String ATTRIBUTE_STAFF_NUMBER = "staffNumber";

    /** 项目字典表名（T_PROJECT_INFO） */
    public static final String PARAM_PROJECT_INFO = "T_PROJECT_INFO";

    /** 页面参数（F_XMBH） */
    public static final String PARAM_F_XMBH = "F_XMBH";

    /** 项目字典表项目编号（PI_NO） */
    public static final String PARAM_PI_NO = "PI_NO";

    /** 页面参数（status） */
    public static final String PARAM_STATUS = "status";

    /** 初始密码（888888） */
    public static final String DEFAULT_PASSWORD = "888888";
}
