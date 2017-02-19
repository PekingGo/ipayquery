package com.pansoft.logic.dao;

import java.util.List;

import com.pansoft.logic.entity.UserRole;

/**
 * 用户角色表操作
 *
 * @author lipeng 2015-03-25
 */
public interface UserRoleDao {
    List<UserRole> getUserRoleList(UserRole userRole);

    List<UserRole> getRoleInfoList(UserRole userRole);

    int getCountByRoleId(String r_id);

    List<String> selectUserId(UserRole userRole);

    void insertRoleData(UserRole userRole);

    void deleteRole(String ur_id);

    String getUAccount(String xzrl);

    int isAdmin(String userAccount);

    List<UserRole> selectUserRoleList(String u_account);

    /**
     * 通过角色名找到对应的人
     *
     * @param roleName
     * @return
     */
    List<UserRole> getUserRolesByRoleName(String roleName);

    boolean isExistRole(String pro_no);

    boolean selectAministrtor(String u_account);

    /**
     * 删除用户时同时删除用户权限表下的权限
     */
    void deleteRoleByAccount(String u_id);

}
