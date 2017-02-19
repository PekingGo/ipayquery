package com.pansoft.logic.service;

import java.util.List;

import com.pansoft.logic.entity.User;

/**
 * 用户表操作
 *
 * @author lipeng 2015-03-25
 */
public interface UserService {

    /**
     * 根据用户名和密码取得用户信息
     *
     * @param user
     * @return
     */
    User getUserByNameAndPwd(User user);

    /**
     * 根据员工编号、员工姓名和维护状态查询员工列表
     *
     * @param user
     * @return
     */
    List<User> getStaffList(User user);

    /**
     * 根据用户帐号找到手机号
     *
     * @param uAccout
     * @return
     */
    String getUMobile(String uAccout);

    /**
     * 同步员工信息
     *
     * @return
     * */
    int syncStaff();

    /**
     * 判断用户名是否存在
     *
     * @param user_account
     * @return
     */
    int getCountByName(String user_account);

    /**
     * 插入新用户信息
     *
     * @param user
     */
    void insertUser(User user);

    /**
     * 更新用户信息
     *
     * @param user
     */
    void updateUserBaseInfo(User user);

    /**
     * 删除用户信息
     *
     * @param user
     */
    void deleteUser(String u_id);
}
