package com.pansoft.logic.service;

import java.util.List;

import com.pansoft.logic.entity.UserRole;

/**
 * 用户角色表操作
 *
 * @author lipeng 2015-03-25
 */
public interface UserRoleService {

    /**
     * 获取用户角色信息一览
     *
     * @param userRole
     * @return
     */
    List<UserRole> getUserRoleList(UserRole userRole);

    /**
     * 获取用户角色信息一览
     *
     * @param userRole
     * @return
     */
    List<UserRole> getRoleInfoList(UserRole userRole);

    /**
     * 根据角色ID获取信息件数
     *
     * @param r_id
     * @return
     */
    int getCountByRoleId(String r_id);

    /**
     * 取得员工对应的角色ID
     *
     * @param userRole
     * @return
     */
    List<String> selectUserId(UserRole userRole);

    /**
     * 新增用户角色信息
     *
     * @param userRole
     */
    void insertRoleData(UserRole userRole);

    /**
     * 删除角色
     *
     * @param ur_id
     */
    void deleteRole(String ur_id);

    /**
     * 通过角色id得到用户帐号
     *
     * @param string
     * @return
     */
    String getUAccount(String xzrl);

    /**
     * 是否是系统管理员，系统管理员有权查看所有的流程信息
     *
     * @param userAccount
     * @return
     */
    boolean isAdmin(String userAccount);

    /**
     * 根据用户id获取用户角色
     *
     * @param u_account
     * @return
     */
    List<UserRole> selectUserRoleList(String u_account);

    /**
     * 根据项目编号查询项目是否被分配
     *
     * @param pro_no
     * @return
     */
    boolean isExistRole(String pro_no);

    /**
     * 查询管理员
     *
     * @param u_account
     * @return
     */
    boolean selectAministrtor(String u_account);

}
