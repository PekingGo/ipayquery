package com.microfin.logic.dao;

import java.util.List;

import com.microfin.logic.entity.User;

/**
 * 用户表操作
 *
 * @author lipeng 2015-03-25
 */
public interface UserDao {

    User getUserInfo(User user);

    List<User> getStaffList(User user);

    String getUMobile(String uAccout);

    // 人员同步
    void syncUser();

    int getCountByName(String user_account);

    // 插入用户
    void insertUser(User user);

    // 更新用户
    void updateUserBaseInfo(User user);

    // 删除用户信息
    void deleteUser(String u_id);

}
