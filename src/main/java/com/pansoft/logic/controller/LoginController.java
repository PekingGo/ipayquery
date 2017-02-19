package com.pansoft.logic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pansoft.common.util.Const;
import com.pansoft.common.util.RightsHelper;
import com.pansoft.common.util.SessionUtil;
import com.pansoft.common.util.StringUtil;
import com.pansoft.logic.consts.LoginConst;
import com.pansoft.logic.entity.Function;
import com.pansoft.logic.entity.Role;
import com.pansoft.logic.entity.User;
import com.pansoft.logic.entity.UserRole;
import com.pansoft.logic.service.FunctionService;
import com.pansoft.logic.service.UserRoleService;
import com.pansoft.logic.service.UserService;

/**
 * 用户登录
 *
 * @author lipeng 2015-03-25
 */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private FunctionService functionService;

    /**
     * 访问登录页
     *
     * @return
     */
    @RequestMapping(value = LoginConst.PATH_LOGIN, method = RequestMethod.GET)
    public String loginGet(HttpSession session) {
        SessionUtil.sessionReset(session);
        return LoginConst.FORWARD_LOGIN;
    }

    /**
     * 请求登录，验证用户
     *
     * @param session
     * @param username
     * @param password
     * @param code
     * @return
     */
    @RequestMapping(value = LoginConst.PATH_LOGIN, method = RequestMethod.POST)
    public ModelAndView loginPost(HttpSession session, HttpServletRequest request, User loginUser) {

        ModelAndView mv = new ModelAndView();
        // 提示信息
        String errInfo = Const.STRING_EMPTY;

        // if (StringUtil.isEmpty(loginUser.getU_account())) {
        // // ocs登录
        // loginUser.setU_account(request.getParameter(LoginConst.PARAM_NAME_USERNAME));
        // loginUser.setU_password(request.getParameter(LoginConst.PARAM_NAME_USER_PWD));
        // }

        //
        // ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓生产环境START↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
        // 用USERNAME\PASSWORD调用oa接口验证用户合法性，如果用户合法，用USERNAME到本地数据库取用户信息
        // String result = OAWebService.oaValidateUser(loginUser.getU_account(),
        // loginUser.getU_password());
        // if (StringUtil.notEmpty(result)) {// 用户名和密码合法
        // String userPwd = loginUser.getU_password();
        // loginUser.setU_password(Const.STRING_EMPTY);
        // User user = userService.getUserByNameAndPwd(loginUser);
        // user.setU_password(userPwd);
        // session.setAttribute(Const.SESSION_USER, user);
        // } else {// 用户名或密码不合法
        // errInfo = "用户名或密码有误！";
        // }
        //
        // ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑生产环境END↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

        //
        // ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓测试环境START↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
        User user = userService.getUserByNameAndPwd(loginUser);
        if (user != null) {
            user.setSystem_id(loginUser.getSystem_id());
            user.setU_password(loginUser.getU_password());
            // 保存登录用户信息
            session.setAttribute(Const.SESSION_USER, user);
        } else {
            // 设置提示信息
            errInfo = "用户名或密码有误！";
        }
        //
        // ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑测试环境END↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

        // 判断登录是否成功
        if (StringUtil.isEmpty(errInfo)) {
            // 跳转到主页面
            mv.setViewName(Const.LOGIC_PATH_REDIRECT + LoginConst.FORWARD_INDEX + Const.LOGIC_PATH_SUFFIX);
        } else {
            // 返回提示信息
            mv.addObject(LoginConst.ATTRIBUTE_NAME_ERRINFO, errInfo);
            // 返回用户输入的用户名
            mv.addObject(LoginConst.ATTRIBUTE_NAME_U_ACCOUNT, loginUser.getU_account());
            // 返回用户输入的密码
            mv.addObject(LoginConst.ATTRIBUTE_NAME_U_PASSWORD, loginUser.getU_password());
            // 跳转到登录页面
            mv.setViewName(LoginConst.FORWARD_LOGIN);
        }
        return mv;
    }

    /**
     * 访问系统首页
     *
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = LoginConst.PATH_INDEX)
    public String index(HttpSession session, Model model) {
        // 从session中取得登录用户信息
        User user = (User) session.getAttribute(Const.SESSION_USER);
        // 用户角色信息
        UserRole userRoleS = new UserRole();
        // 设置用户账号
        userRoleS.setU_account(user.getU_account());
        // 获取登录用户所属角色
        List<UserRole> userRoleList = userRoleService.getUserRoleList(userRoleS);
        List<String> userRights = null;
        if (userRoleList != null && userRoleList.size() > 0) {
            userRights = new ArrayList<String>();
            for (UserRole userRole : userRoleList) {
                Role role = userRole.getRole();
                if (role != null) {
                    String right = role.getR_function();
                    if (StringUtil.notEmpty(right)) {
                        userRights.add(right);
                    }
                }
            }
        }
        // 避免每次拦截用户操作时查询数据库，以下将用户所属角色权限、用户权限限都存入session
        // 将用户权限存入session
        session.setAttribute(Const.SESSION_USER_RIGHTS, userRights);
        // 获取系统功能菜单列表
        List<Function> functionList = functionService.listAllFunction();
        List<Function> sessionFunctionList = new ArrayList<Function>();
        String fUrl = Const.STRING_EMPTY;
        // 判断用户是否有访问权限
        if (userRights != null) {
            // 循环系统功能列表一览，并取得登录用户可以访问的菜单一览
            for (Function function : functionList) {
                function.setHasFunction(RightsHelper.testRightsList(userRights, function.getF_no()));
                if (function.isHasFunction()) {
                    fUrl = getFunctionUrl(fUrl, function.getF_url());
                    Function sessionFunction = function;
                    List<Function> subFunctionList = function.getSubFunction();
                    List<Function> sessionSubFunctionList = new ArrayList<Function>();
                    // 循环二级功能菜单
                    for (Function sub : subFunctionList) {
                        sub.setHasFunction(RightsHelper.testRightsList(userRights, sub.getF_no()));
                        if (sub.isHasFunction()) {
                            fUrl = getFunctionUrl(fUrl, sub.getF_url());
                            Function sessionSub = sub;
                            List<Function> nextSubFunctionList = sub.getSubFunction();
                            List<Function> sessionNextSubFunctionList = new ArrayList<Function>();
                            // 循环三级功能菜单
                            for (Function nextSub : nextSubFunctionList) {
                                nextSub.setHasFunction(RightsHelper.testRightsList(userRights, nextSub.getF_no()));
                                if (nextSub.isHasFunction()) {
                                    fUrl = getFunctionUrl(fUrl, nextSub.getF_url());
                                    sessionNextSubFunctionList.add(nextSub);
                                }
                            }
                            sessionSub.setSubFunction(sessionNextSubFunctionList);
                            sessionSubFunctionList.add(sessionSub);
                        }
                    }
                    sessionFunction.setSubFunction(sessionSubFunctionList);
                    sessionFunctionList.add(sessionFunction);
                }
            }
        } else {
            //
            // ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓返回登录页面START↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
            // 返回用户无权登录信息提示
            model.addAttribute(LoginConst.ATTRIBUTE_NAME_ERRINFO, "该用户无权登录，请与系统管理员联系！");
            model.addAttribute(LoginConst.ATTRIBUTE_NAME_U_ACCOUNT, user.getU_account());
            model.addAttribute(LoginConst.ATTRIBUTE_NAME_U_PASSWORD, user.getU_password());
            return LoginConst.FORWARD_LOGIN;

            // ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑返回登录页面END↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
            //
            // ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓切换到会议系统START↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
            // return LoginConst.FORWARD_AUTHORIZATION;
            //
            // ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑切换到会议系统END↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
        }
        // 保存登录用户可以访问的功能菜单一览
        session.setAttribute(Const.SESSION_MENU_LIST, sessionFunctionList);
        return StringUtil.isEmpty(fUrl) ? LoginConst.FORWARD_DEFAULT : Const.LOGIC_PATH_REDIRECT + fUrl;
    }

    /**
     * 进入首页后的默认页面
     *
     * @return
     */
    // @RequestMapping(value = "/default")
    // public String defaultPage() {
    // return "common/default";
    // }

    /**
     * 用户注销
     *
     * @param session
     * @return
     */
    @RequestMapping(value = LoginConst.PATH_LOGOUT)
    public ModelAndView logout(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        // 从session中取得登录用户信息
        User user = (User) session.getAttribute(Const.SESSION_USER);
        if (user != null) {
            // 返回用户输入的用户名
            mv.addObject(LoginConst.ATTRIBUTE_NAME_U_ACCOUNT, user.getU_account());
            // 返回用户输入的密码
            // mv.addObject(LoginConst.ATTRIBUTE_NAME_U_PASSWORD,
            // user.getU_password());
        }
        SessionUtil.sessionReset(session);
        // 跳转到登录页面
        mv.setViewName(LoginConst.FORWARD_LOGIN);
        return mv;
    }

    /**
     * 获取跳转用登录用户信息
     *
     * @param session
     * @return
     */
    @RequestMapping(value = LoginConst.PATH_SWITCH_SYSTEM, method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> switchSystem(HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();

        // 从session中取得登录用户信息
        User user = (User) session.getAttribute(Const.SESSION_USER);

        if (user != null) {
            // 返回登录用户名
            map.put(LoginConst.ATTRIBUTE_NAME_U_ACCOUNT, StringUtil.nvl(user.getU_account()));

            // 返回登录密码
            map.put(LoginConst.ATTRIBUTE_NAME_U_PASSWORD, StringUtil.nvl(user.getU_password()));
        }

        // 返回处理结果
        map.put(LoginConst.ATTRIBUTE_NAME_RESULT, Boolean.TRUE);
        return map;
    }

    /**
     * 取得业务路径
     *
     * @param str
     * @param param
     * @return
     */
    private String getFunctionUrl(String str, String param) {
        // 判断业务路径是否已经存在
        if (StringUtil.isEmpty(str)) {
            str = StringUtil.nvlTrim(param);
        }
        return str;
    }
}