package com.pansoft.logic.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pansoft.logic.dao.UserRoleDao;
import com.pansoft.logic.entity.UserRole;
import com.pansoft.logic.service.UserRoleService;

/**
 * 用户角色表操作
 *
 * @author lipeng 2015-03-25
 */
@Service
@Transactional(readOnly = true)
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    /**
     * 获取用户角色信息一览
     *
     * @param userRole
     * @return
     */
    /* @Override */
    public List<UserRole> getUserRoleList(UserRole userRole) {
        return userRoleDao.getUserRoleList(userRole);
    }

    /**
     * 获取用户角色和项目信息一览
     *
     * @param userRole
     * @return
     */
    /* @Override */
    public List<UserRole> getRoleInfoList(UserRole userRole) {
        return userRoleDao.getRoleInfoList(userRole);
    }

    /**
     * 根据角色ID获取信息件数
     *
     * @param r_id
     * @return
     */
    /* @Override */
    public int getCountByRoleId(String r_id) {
        return userRoleDao.getCountByRoleId(r_id);
    }

    /**
     * 根据员工ID获取对应的角色ID
     */
    /* @Override */
    public List<String> selectUserId(UserRole userRole) {

        List<String> list = userRoleDao.selectUserId(userRole);

        return list;
    }

    /**
     * 新增用户角色信息
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    /* @Override */
    public void insertRoleData(UserRole userRole) {

        userRoleDao.insertRoleData(userRole);

    }

    /**
     * 删除角色
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    /* @Override */
    public void deleteRole(String ur_id) {

        userRoleDao.deleteRole(ur_id);

    }

    /**
     * 通过角色id得到用户帐号
     *
     * @return
     *
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    /* @Override */
    public String getUAccount(String xzrl) {
        String uAccount = userRoleDao.getUAccount(xzrl);
        return uAccount;
    }

    /* @Override */
    public boolean isAdmin(String userAccount) {
        return userRoleDao.isAdmin(userAccount) > 0;
    }

    /* @Override */
    public List<UserRole> selectUserRoleList(String u_account) {
        List<UserRole> list = userRoleDao.selectUserRoleList(u_account);
        return list == null ? new ArrayList<UserRole>() : list;
    }

    /* @Override */
    public boolean isExistRole(String pro_no) {
        return userRoleDao.isExistRole(pro_no);
    }

    /* @Override */
    public boolean selectAministrtor(String u_account) {
        return userRoleDao.selectAministrtor(u_account);
    }

}
