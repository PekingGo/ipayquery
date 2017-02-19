package com.microfin.logic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.microfin.logic.dao.UserDao;
import com.microfin.logic.dao.UserRoleDao;
import com.microfin.logic.entity.User;
import com.microfin.logic.service.UserService;

/**
 * 用户表操作
 *
 * @author lipeng 2015-03-25
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;

    /**
     * 根据用户名和密码取得用户信息
     *
     * @param user
     * @return
     */
    /* @Override */
    public User getUserByNameAndPwd(User user) {
        return userDao.getUserInfo(user);
    }

    /**
     * 根据员工编号、员工姓名和维护状态取得员工列表
     */
    /* @Override */
    public List<User> getStaffList(User user) {

        List<User> list = userDao.getStaffList(user);
        return list;
    }

    /**
     * 根据用户帐号得到手机号
     */
    /* @Override */
    public String getUMobile(String uAccout) {
        String uMobile = userDao.getUMobile(uAccout);
        return uMobile;
    }

    /**
     * 同步员工信息
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    /* @Override */
    public int syncStaff() {
        userDao.syncUser();
        return 1;
    }

    /**
     * 判断用户账户是否存在
     */
    /* @Override */
    public int getCountByName(String user_account) {
        return userDao.getCountByName(user_account);
    }

    /**
     * 插入新用户信息
     */
    /* @Override */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    /**
     * 更新用户基础信息
     */
    /* @Override */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public void updateUserBaseInfo(User user) {
        userDao.updateUserBaseInfo(user);
    }

    /**
     * 删除用户信息
     */
    /* @Override */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public void deleteUser(String u_id) {
        // 删除用户的权限
        userRoleDao.deleteRoleByAccount(u_id);
        // 删除用户
        userDao.deleteUser(u_id);
    }

}
