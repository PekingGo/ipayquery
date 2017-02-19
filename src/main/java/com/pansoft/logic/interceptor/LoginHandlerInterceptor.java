package com.pansoft.logic.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.pansoft.common.util.Const;
import com.pansoft.common.util.SessionUtil;

/**
 * 登录用户信息判定
 *
 * @author lipeng 2015-02-28
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {

    /**
     * 验证用户是否存在指定的功能权限
     *
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求信息
        String path = request.getServletPath();
        // 判定请求信息是否合法
        if (path.matches(Const.NO_INTERCEPTOR_PATH)) {
            return true;
        } else {
            // 获取登录用户信息
            if (SessionUtil.sessionCheck(request.getSession())) {
                return true;
            } else {
                response.sendRedirect(request.getContextPath() + "/login.html");
                return false;
            }
        }
    }

}
