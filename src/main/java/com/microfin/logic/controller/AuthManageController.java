package com.microfin.logic.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.microfin.common.JQGridResponse;
import com.microfin.common.util.Const;
import com.microfin.common.util.DateUtil;
import com.microfin.common.util.RightsHelper;
import com.microfin.common.util.StringUtil;
import com.microfin.logic.consts.AuthManageConst;
import com.microfin.logic.entity.Function;
import com.microfin.logic.entity.Role;
import com.microfin.logic.entity.User;
import com.microfin.logic.service.FunctionService;
import com.microfin.logic.service.RoleService;
import com.microfin.logic.service.UserRoleService;

/**
 * 权限管理
 *
 * @author lipeng 2015-03-25
 */
@Controller
@RequestMapping(AuthManageConst.PATH_AUTH_MANAGE)
public class AuthManageController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private FunctionService functionService;
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 显示角色列表
     *
     * @return
     */
    @RequestMapping(AuthManageConst.PATH_EMPTY)
    public ModelAndView userManage() {
        ModelAndView mv = new ModelAndView();
        // 跳转到权限管理
        mv.setViewName(AuthManageConst.FORWARD_AUTH_MANAGE);
        return mv;
    }

    /**
     * 取得角色信息一览
     *
     * @param r_name
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = AuthManageConst.PATH_QUERY, method = RequestMethod.GET)
    public @ResponseBody JQGridResponse<Role> query(@RequestParam String r_name) throws UnsupportedEncodingException {

        // 初始化返回数据
        JQGridResponse<Role> res = new JQGridResponse<Role>();
        Role role = new Role();
        // 判断角色名是否为空
        if (StringUtil.notEmpty(r_name)) {
            // 检索信息设置角色名
            role.setR_name(StringUtil.getDecodeStr(r_name));
        }

        // 打开分页功能
        // PageHelper.startPage(pageNumber, rowsAmountLimit);
        // 检索角色信息一览
        List<Role> roleList = roleService.getRoleByName(role);
        // 分页
        // PageInfo<Role> page = new PageInfo<Role>(roleList);
        // 当前页数
        // res.setPage(page.getPageNum());
        // 数据总数
        // res.setRecords((int) page.getTotal());
        // 可显示的页数
        // res.setTotal(page.getPages());
        // 数据集合
        // res.setRows(page.getList());
        res.setRows(roleList);
        // 返回检索数据
        return res;
    }

    /**
     * 保存角色信息
     *
     * @param session
     * @param user
     * @return
     */
    @RequestMapping(value = AuthManageConst.PATH_SAVE, method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> saveRole(HttpSession session, @RequestBody Role role) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 判断角色信息是否为空
        if (role != null) {
            // 判断输入是否为空
            if (StringUtil.isEmpty(role.getR_name())) {
                map.put(AuthManageConst.ATTRIBUTE_NAME_MSG, "角色名称不允许为空！");
                map.put(AuthManageConst.ATTRIBUTE_NAME_MODEL_CLOSE, Boolean.FALSE);
            } else {
                BigInteger rights = null;
                String functionIds = role.getFunctionIds();
                if (StringUtil.notEmpty(functionIds)) {
                    // 将权限序列化
                    rights = RightsHelper.sumRights(StringUtil.str2StrArray(functionIds));
                    role.setR_function(rights.toString());
                }
                // 判断角色编号
                if (StringUtil.isEmpty(role.getR_id())) {
                    // 从session中取得登录用户信息
                    User user = (User) session.getAttribute(Const.SESSION_USER);
                    // 设定创建者
                    role.setCreate_user(user.getU_name());
                    // 设定创建时间
                    role.setCreate_time(DateUtil.getCurDate());
                    // 设置角色ID
                    role.setR_id(StringUtil.getUuid());
                    // 新增角色
                    if (!roleService.insertRole(role)) {
                        map.put(AuthManageConst.ATTRIBUTE_NAME_MSG, "新增失败，该角色名已存在！");
                        map.put(AuthManageConst.ATTRIBUTE_NAME_MODEL_CLOSE, Boolean.FALSE);
                    }
                } else {
                    // 角色信息更新
                    String updFlg = roleService.updateRoleBaseInfo(role);
                    // 更新角色信息
                    if (AuthManageConst.UPDATE_FLAG_FAILURE_ONE.equals(updFlg)) {
                        map.put(AuthManageConst.ATTRIBUTE_NAME_MSG, "更新失败，修改的角色名已存在！");
                        map.put(AuthManageConst.ATTRIBUTE_NAME_MODEL_CLOSE, Boolean.FALSE);
                    } else if (AuthManageConst.UPDATE_FLAG_FAILURE_TWO.equals(updFlg)) {
                        map.put(AuthManageConst.ATTRIBUTE_NAME_MSG, "更新失败，该角色可能已被删除！");
                        map.put(AuthManageConst.ATTRIBUTE_NAME_MODEL_CLOSE, Boolean.TRUE);
                    }
                }
            }
        }
        // 返回处理结果
        map.put(AuthManageConst.ATTRIBUTE_NAME_RESULT, Boolean.TRUE);
        return map;
    }

    /**
     * 删除某个角色
     *
     * @param user
     * @return
     */
    @RequestMapping(value = AuthManageConst.PATH_DELETE, method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> deleteRole(@RequestBody Role role) {
        Map<String, Object> map = new HashMap<String, Object>();
        String roleId = role.getR_id();
        // 判断当前角色是否被使用
        if (userRoleService.getCountByRoleId(roleId) > 0) {
            map.put(AuthManageConst.ATTRIBUTE_NAME_MSG, "当前角色正在使用中，无法删除！");
        } else {
            // 删除角色信息
            roleService.deleteRoleById(roleId);
        }
        // 返回处理结果
        map.put(AuthManageConst.ATTRIBUTE_NAME_RESULT, Boolean.TRUE);
        return map;
    }

    /**
     * 请求权限授权页面
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = AuthManageConst.PATH_AUTH, method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> auth(@RequestParam String r_id) {
        // 取得菜单一览
        List<Function> functionList = functionService.listAllFunction();
        // 取得授权权限权限
        Role roleSearch = new Role();
        roleSearch.setR_id(r_id);
        Role role = roleService.getRoleById(roleSearch);
        if (role != null) {
            String roleRights = role.getR_function();
            // 判断该权限是否存在功能权限
            if (StringUtil.notEmpty(roleRights)) {
                // 循环菜单一览
                for (Function function : functionList) {
                    // 设置权限是否存在该一级菜单的访问权限
                    function.setHasFunction(RightsHelper.testRights(roleRights, function.getF_no()));
                    // 判断是否存在此级菜单访问权限
                    if (function.isHasFunction()) {
                        // 取得二级菜单一览
                        List<Function> subRightsList = function.getSubFunction();
                        // 循环二级菜单一览
                        for (Function sub : subRightsList) {
                            // 设置权限是否存在该二级菜单的访问权限
                            sub.setHasFunction(RightsHelper.testRights(roleRights, sub.getF_no()));
                            // 判断是否存在此级菜单访问权限
                            if (sub.isHasFunction()) {
                                // 取得三级菜单一览
                                List<Function> nextSubRightsList = sub.getSubFunction();
                                // 循环三级菜单一览
                                for (Function nexSub : nextSubRightsList) {
                                    // 设置权限是否存在该三级菜单的访问权限
                                    nexSub.setHasFunction(RightsHelper.testRights(roleRights, nexSub.getF_no()));
                                }
                            }
                        }
                    }
                }
            }
        }
        // 将菜单一览转换为json格式
        JSONArray arr = JSONArray.fromObject(functionList);
        // 将json格式的菜单一览转换为字符串
        String json = arr.toString();
        // 置换为指定的ID
        json = json.replaceAll(AuthManageConst.ATTRIBUTE_NAME_F_NO, AuthManageConst.ATTRIBUTE_NAME_ID)
                .replaceAll(AuthManageConst.ATTRIBUTE_NAME_F_NAME, AuthManageConst.ATTRIBUTE_NAME_NAME)
                .replaceAll(AuthManageConst.ATTRIBUTE_NAME_SUB_FUNCTION, AuthManageConst.ATTRIBUTE_NAME_CHILDREN)
                .replaceAll(AuthManageConst.ATTRIBUTE_NAME_HAS_FUNCTION, AuthManageConst.ATTRIBUTE_NAME_CHECKED);
        Map<String, Object> map = new HashMap<String, Object>();
        // 返回处理结果
        map.put(AuthManageConst.ATTRIBUTE_NAME_RESULT, Boolean.TRUE);
        // 返回菜单一览
        map.put(AuthManageConst.ATTRIBUTE_NAME_Z_TREE_NODES, json);
        // 返回角色ID
        map.put(AuthManageConst.ATTRIBUTE_NAME_R_ID, r_id);
        return map;
    }

    /**
     * 保存角色权限
     *
     * @param userId
     * @param menuIds
     * @return
     */
    @RequestMapping(value = AuthManageConst.PATH_SAVE_AUTH, method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> saveAuth(@RequestParam String r_id, @RequestParam String functionIds) {
        Role roleSearch = new Role();
        roleSearch.setR_id(r_id);
        // 取得角色权限信息
        Role role = roleService.getRoleById(roleSearch);
        Map<String, Object> map = new HashMap<String, Object>();
        if (role != null) {
            if (StringUtil.notEmpty(functionIds)) {
                // 将权限权限序列化
                BigInteger rights = RightsHelper.sumRights(StringUtil.str2StrArray(functionIds));
                // 设置角色权限
                role.setR_function(rights.toString());
            } else {
                // 设置角色权限
                role.setR_function(null);
            }
            // 更新角色权限
            if (!roleService.updateRoleFunction(role)) {
                map.put(AuthManageConst.ATTRIBUTE_NAME_MSG, "授权失败，该角色可能已被删除！");
            }
        } else {
            map.put(AuthManageConst.ATTRIBUTE_NAME_MSG, "授权失败，该角色可能已被删除！");
        }
        // 返回处理结果
        map.put(AuthManageConst.ATTRIBUTE_NAME_RESULT, Boolean.TRUE);
        return map;
    }
}
