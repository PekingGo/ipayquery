package com.pansoft.logic.service;

import java.util.List;

import com.pansoft.logic.entity.Role;

/**
 * 用户角色表操作
 *
 * @author lipeng 2015-03-25
 */
public interface RoleService {

    /**
     * 根据角色ID获取角色信息
     *
     * @param r_name
     * @return
     */
    Role getRoleById(Role role);

    /**
     * 根据角色名获取角色信息
     *
     * @param r_name
     * @return
     */
    List<Role> getRoleByName(Role role);

    /**
     * 判断角色是否存在
     *
     * @param role
     * @return
     */
    String getRoleIdByIdAndName(Role role);

    /**
     * 新增角色信息
     *
     * @param role
     * @return
     */
    boolean insertRole(Role role);

    /**
     * 更新角色信息
     *
     * @param role
     * @return
     */
    String updateRoleBaseInfo(Role role);

    /**
     * 更新角色权限
     *
     * @param role
     * @return
     */
    boolean updateRoleFunction(Role role);

    /**
     * 删除角色信息
     *
     * @param r_id
     * @return
     */
    void deleteRoleById(String r_id);

    /**
     * 获取所有角色
     *
     * @return
     */
    List<Role> findAll(String system_id);
}
