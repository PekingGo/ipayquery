package com.microfin.logic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.microfin.common.util.StringUtil;
import com.microfin.logic.dao.RoleDao;
import com.microfin.logic.entity.Role;
import com.microfin.logic.service.RoleService;

/**
 * 角色表操作
 *
 * @author lipeng 2015-03-25
 */
@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    /**
     * 根据角色ID获取角色信息
     *
     * @param role
     * @return
     */
    /* @Override */
    @Override
    public Role getRoleById(Role role) {
        return roleDao.getRoleById(role);
    }

    /**
     * 根据角色名获取角色信息
     *
     * @param role
     * @return
     */
    /* @Override */
    @Override
    public List<Role> getRoleByName(Role role) {
        return roleDao.getRoleByName(role);
    }

    /**
     * 判断角色是否存在
     *
     * @param role
     * @return
     */
    /* @Override */
    @Override
    public String getRoleIdByIdAndName(Role role) {
        return roleDao.getRoleIdByIdAndName(role.getSystem_id(), null, role.getR_name());
    }

    /**
     * 新增角色信息
     *
     * @param role
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    /* @Override */
    public boolean insertRole(Role role) {
        if (StringUtil.notEmpty(roleDao.getRoleIdByIdAndName(role.getSystem_id(), null, role.getR_name()))) {
            return false;
        } else {
            roleDao.insertRole(role);
            return true;
        }
    }

    /**
     * 更新角色信息
     *
     * @param role
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    /* @Override */
    public String updateRoleBaseInfo(Role role) {

        // 系统标志
        String system_id = role.getSystem_id();
        // 角色ID
        String r_id = role.getR_id();
        // 角色名称
        String r_name = role.getR_name();

        String roleIdById = roleDao.getRoleIdByIdAndName(system_id, r_id, null);
        String roleIdByName = roleDao.getRoleIdByIdAndName(system_id, null, r_name);

        if (StringUtil.isNotEmpty(roleIdById)) {

            if (StringUtil.isEmpty(roleIdByName) || roleIdByName.equals(r_id)) {
                roleDao.updateRoleBaseInfo(role);
                // 更新成功
                return "0";
            } else {
                // 该角色名已存在
                return "1";
            }

        } else {
            // 该角色已被删除
            return "2";
        }
    }

    /**
     * 更新角色权限
     *
     * @param role
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    /* @Override */
    public boolean updateRoleFunction(Role role) {

        if (roleDao.updateRoleFunction(role) > 0) {
            // 更新成功
            return true;
        } else {
            // 该角色已被删除
            return false;
        }

    }

    /**
     * 删除角色信息
     *
     * @param r_id
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    /* @Override */
    public void deleteRoleById(String r_id) {
        roleDao.deleteRoleById(r_id);

    }

    /* @Override */
    @Override
    public List<Role> findAll(String system_id) {
        // TODO Auto-generated method stub
        return roleDao.findAll(system_id);
    }

}
