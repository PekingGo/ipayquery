package com.microfin.logic.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.microfin.common.JQGridResponse;
import com.microfin.common.util.Const;
import com.microfin.common.util.StringUtil;
import com.microfin.logic.consts.StaffManageConst;
import com.microfin.logic.entity.Role;
import com.microfin.logic.entity.User;
import com.microfin.logic.entity.UserRole;
import com.microfin.logic.service.RoleService;
import com.microfin.logic.service.UserRoleService;
import com.microfin.logic.service.UserService;

/**
 * 员工管理
 *
 * @author zhujiarong 2015/4/14
 *
 */
@Controller
@RequestMapping(StaffManageConst.PATH_STAFF_MANAGE)
public class StaffManageController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;

    // @Autowired
    // private ProjectInfoService projectInfoService;

    /**
     * 显示员工管理画面
     *
     * @return
     */
    @RequestMapping(StaffManageConst.PATH_EMPTY)
    public String setStaffManageController() {

        // 跳转到员工管理画面
        return StaffManageConst.FORWARD_STAFF_MANAGE;
    }

    /**
     * 取得员工信息列表
     *
     * @param staffNumber
     * @param staffName
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = StaffManageConst.PATH_SELECT_STAFF, method = RequestMethod.GET)
    public @ResponseBody JQGridResponse<User> query(@RequestParam(StaffManageConst.ATTRIBUTE_F_ZGBH) String staffNumber,
            @RequestParam(StaffManageConst.ATTRIBUTE_F_NAME) String staffName, @RequestParam(StaffManageConst.PARAM_STATUS) String status)
            throws UnsupportedEncodingException {

        // 初始化返回数据
        JQGridResponse<User> res = new JQGridResponse<User>();

        // 取得转码之前的字符串
        String f_zgbh = StringUtil.getDecodeStr(staffNumber).trim();
        String f_name = StringUtil.getDecodeStr(staffName).trim();
        String f_status = StringUtil.getDecodeStr(status);

        User user = new User();
        // 员工编号
        user.setU_account(f_zgbh);
        // 员工姓名
        user.setU_name(f_name);
        // 维护状态
        user.setU_status(f_status);

        // 取得员工列表
        List<User> list = userService.getStaffList(user);

        res.setRows(list);

        return res;
    }

    // /**
    // * 取得所有项目列表
    // *
    // * @return
    // * @throws UnsupportedEncodingException
    // */
    // @RequestMapping(value = StaffManageConst.PATH_SELECT_ALL_PROJECT, method
    // = RequestMethod.GET)
    // public @ResponseBody JQGridResponse<ProjectInfo> query(String pi_name)
    // throws UnsupportedEncodingException {
    //
    // // 初始化返回数据
    // JQGridResponse<ProjectInfo> res = new JQGridResponse<ProjectInfo>();
    //
    // // 封装查询bean
    // ProjectInfo projectInfo = new ProjectInfo();
    // projectInfo.setPi_name(StringUtil.getDecodeStr(pi_name));
    // // 取得所有项目一览
    // List<ProjectInfo> list =
    // projectInfoService.queryProjectInfo(projectInfo);
    //
    // res.setRows(list);
    //
    // return res;
    // }

    /**
     * 取得所有角色列表
     *
     * @param r_name
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = StaffManageConst.PATH_SELECT_ALL_ROLE, method = RequestMethod.GET)
    public @ResponseBody JQGridResponse<Role> selectAllRole(@RequestParam(StaffManageConst.ATTRIBUTE_R_NAME) String r_name)
            throws UnsupportedEncodingException {

        // 初始化返回数据
        JQGridResponse<Role> res = new JQGridResponse<Role>();

        // 取得转码前的字符串并封装在bean中
        Role role = new Role();
        role.setSystem_id(Const.SYSTEM_DIF_AIGOUMS);
        String roleName = StringUtil.getDecodeStr(r_name);
        role.setR_name(roleName);

        // 取得所有角色一览
        List<Role> list = roleService.getRoleByName(role);

        res.setRows(list);

        return res;
    }

    /**
     * 取得员工对应的角色
     *
     * @param staffNumber
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = StaffManageConst.PATH_SELECT_STAF_ROLE, method = RequestMethod.GET)
    public @ResponseBody JQGridResponse<UserRole> queryStaffRole(@RequestParam String staffNumber) throws UnsupportedEncodingException {

        JQGridResponse<UserRole> res = new JQGridResponse<UserRole>();

        UserRole userRole = new UserRole();
        // 用户编号
        userRole.setU_account(StringUtil.getDecodeStr(staffNumber));
        // 获取用户角色和项目信息一览
        List<UserRole> roleAndProjectInfoList = userRoleService.getRoleInfoList(userRole);

        res.setRows(roleAndProjectInfoList);

        return res;
    }

    /**
     * 新增角色保存操作
     *
     * @param userRole
     * @param session
     * @return
     */
    @RequestMapping(value = StaffManageConst.PATH_SAVE_ROLE, method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> saveRole(@RequestBody UserRole userRole, HttpSession session) {

        Map<String, Object> map = new HashMap<String, Object>();
        // 判断新增角色是否已经存在
        boolean flag = true;
        // 取得员工编号对应的角色id
        List<String> list = userRoleService.selectUserId(userRole);
        for (int i = 0; i < list.size(); i++) {
            String r_id = list.get(i);
            if (userRole.getR_id().equals(r_id)) {
                flag = false;
            }
        }

        if (flag) {

            // 用户角色表主键
            String ur_id = StringUtil.getUuid();
            userRole.setUr_id(ur_id);
            // 创建时间
            userRole.setCreate_time(new Date());
            // 创建人
            User user = (User) session.getAttribute(Const.SESSION_USER);
            String create_user = user.getU_name();
            userRole.setCreate_user(create_user);
            // 系统标志
            userRole.setSystem_id(Const.SYSTEM_DIF_AIGOUMS);

            userRoleService.insertRoleData(userRole);
        } else {
            map.put(StaffManageConst.ATTRIBUTE_NAME_MSG, "此员工已经授权该角色，不得重复添加!");
        }

        // 返回处理结果
        map.put(StaffManageConst.ATTRIBUTE_NAME_RESULT, Boolean.TRUE);

        return map;
    }

    /**
     * 角色删除操作
     *
     * @param userRole
     * @return
     */
    @RequestMapping(value = StaffManageConst.PATH_DELETE_ROLE, method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> deleteUser(@RequestBody UserRole userRole) {

        // 在数据库中删除选中角色
        userRoleService.deleteRole(userRole.getUr_id());

        // 返回处理结果
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(StaffManageConst.ATTRIBUTE_NAME_RESULT, Boolean.TRUE);

        return map;
    }

    /**
     * 保存用户信息
     *
     * @param user
     * @param session
     * @return
     */
    @RequestMapping(value = StaffManageConst.PATH_SAVE_STAFF, method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> saveStaff(@RequestBody User user, HttpSession session) {

        Map<String, Object> map = new HashMap<String, Object>();
        // 判断用户是否为空
        if (user != null) {
            // 判断输入是否为空
            if (StringUtil.isEmpty(user.getU_account())) {
                map.put(StaffManageConst.ATTRIBUTE_NAME_MSG, "用户名不允许为空！");
            } else if (StringUtil.isEmpty(user.getU_name())) {
                map.put(StaffManageConst.ATTRIBUTE_NAME_MSG, "姓名不允许为空！");
            } else {
                // 判断用户编号
                if (user.getU_id() == null || "".endsWith(user.getU_id())) {
                    // 新增用户
                    if (userService.getCountByName(user.getU_account()) > 0) {
                        map.put(StaffManageConst.ATTRIBUTE_NAME_MSG, "新增失败，该用户名已存在！");
                    } else {
                        // 设置初始密码
                        user.setU_password(StaffManageConst.DEFAULT_PASSWORD);
                        user.setU_id(StringUtil.getUuid());
                        userService.insertUser(user);
                    }
                } else {
                    // 判断该用户编号是否为超级管理员
                    if ("1".equals(user.getU_id())) {
                        map.put(StaffManageConst.ATTRIBUTE_NAME_MSG, "超级管理员信息不允许修改！");
                    } else {
                        // 更新用户信息
                        userService.updateUserBaseInfo(user);
                    }
                }
            }
        }

        // 返回处理结果
        map.put(StaffManageConst.ATTRIBUTE_NAME_RESULT, Boolean.TRUE);

        return map;
    }

    /**
     * 删除某个用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = StaffManageConst.PATH_DELETE_STAFF, method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> delete(@RequestBody User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 判断用户编号
        if (user.getU_id() != null || "".equals(user.getU_id())) {
            // 判断该用户编号是否为超级管理员
            if ("1".equals(user.getU_id())) {
                map.put(StaffManageConst.ATTRIBUTE_NAME_MSG, "超级管理员信息不允许删除！");
            } else {
                // 删除用户信息
                userService.deleteUser(user.getU_id());
            }
        }
        // 返回处理结果
        map.put(StaffManageConst.ATTRIBUTE_NAME_RESULT, Boolean.TRUE);
        return map;
    }
}