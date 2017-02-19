package com.microfin.logic.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.microfin.common.util.Const;
import com.microfin.common.util.RightsHelper;
import com.microfin.common.util.StringUtil;
import com.microfin.logic.entity.Function;
import com.microfin.logic.entity.User;
import com.microfin.logic.service.FunctionService;

/**
 * 用户功能权限判定
 *
 * @author lipeng 2015-02-28
 */
public class RightsHandlerInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private FunctionService functionService;

    /**
     * 验证用户是否存在指定的功能权限
     *
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求信息
        String path = request.getServletPath();
        // 取得路径后缀起始位置
        int idx = path.lastIndexOf(Const.FILE_NAME_SUFFIX);
        // 判断该请求信息是否包含后缀,并将后缀统一设置为.html
        if (idx == -1) {
            path = path + Const.LOGIC_PATH_SUFFIX;
        } else {
            path = path.substring(0, path.lastIndexOf(Const.FILE_NAME_SUFFIX)) + Const.LOGIC_PATH_SUFFIX;
        }
        // 判定请求信息是否合法
        if (path.matches(Const.NO_INTERCEPTOR_PATH)) {
            return true;
        }
        // 获取登录用户信息
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Const.SESSION_USER);
        Integer fNo = null;
        // 获取所有菜单信息
        List<Function> functionList = functionService.listAllFunction();
        // 循环遍历一级菜单信息
        loop: for (Function function : functionList) {
            String fUrl = function.getF_url();
            // 验证一级菜单资源路径
            if (isExist(path, fUrl)) {
                fNo = function.getF_no();
                break;
            }
            List<Function> subFunctionList = function.getSubFunction();
            // 循环遍历二级菜单信息
            for (Function subFunction : subFunctionList) {
                String subFunctionUrl = subFunction.getF_url();
                // 验证二级菜单资源路径
                if (isExist(path, subFunctionUrl)) {
                    fNo = subFunction.getF_no();
                    break loop;
                }
                List<Function> nextSubFunctionList = subFunction.getSubFunction();
                // 循环遍历三级菜单信息
                for (Function nextSubFunction : nextSubFunctionList) {
                    String nextSubFunctionUrl = nextSubFunction.getF_url();
                    // 验证三级菜单资源路径
                    if (isExist(path, nextSubFunctionUrl)) {
                        fNo = nextSubFunction.getF_no();
                        break loop;
                    }
                }
            }
        }
        // System.out.println(path+"===="+fNo);
        // 判定用户是否拥有资源菜单访问权限
        if (fNo != null) {
            List<String> userRights = (ArrayList<String>) session.getAttribute(Const.SESSION_USER_RIGHTS);
            // String roleRights = (String)
            // session.getAttribute(Const.SESSION_ROLE_RIGHTS);
            if (RightsHelper.testRightsList(userRights, fNo)) {
                return true;
            } else {
                System.out.println("用户：" + user.getU_account() + "试图访问" + path + "被阻止！");
                ModelAndView mv = new ModelAndView();
                mv.setViewName("common/no_rights");
                throw new ModelAndViewDefiningException(mv);
            }
        }
        return super.preHandle(request, response, handler);
    }

    /**
     * 验证菜单资源路径
     *
     * @param path
     * @param fUrl
     * @return
     */
    private boolean isExist(String path, String fUrl) {
        // 返回值
        boolean isExist = false;
        // 判定资源路径是否为空
        if (StringUtil.notEmpty(fUrl)) {
            // 判定请求中是否包含当前资源路径
            if (path.contains(fUrl)) {
                isExist = true;
            } else {
                String[] arr = fUrl.split("\\.");
                String regex = "";
                if (arr.length == 2) {
                    regex = "/?" + arr[0] + "(/.*)?." + arr[1];

                } else {
                    regex = "/?" + fUrl + "(/.*)?.html";
                }
                if (path.matches(regex)) {
                    isExist = true;
                }
            }
        }
        // 返回判定结果
        return isExist;
    }

}
