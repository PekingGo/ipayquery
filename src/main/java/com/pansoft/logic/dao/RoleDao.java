package com.pansoft.logic.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pansoft.logic.entity.Role;

/**
 * 角色表操作
 *
 * @author lipeng 2015-03-25
 */
public interface RoleDao {

    Role getRoleById(Role role);

    List<Role> getRoleByName(Role role);

    String getRoleIdByIdAndName(@Param("system_id") String system_id, @Param("r_id") String r_id, @Param("r_name") String r_name);

    int insertRole(Role role);

    int updateRoleBaseInfo(Role role);

    int updateRoleFunction(Role role);

    int deleteRoleById(String r_id);

    List<Role> findAll(String system_id);
}
