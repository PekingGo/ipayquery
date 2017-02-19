package com.pansoft.common.util;

import javax.servlet.http.HttpSession;

/**
 * Session工具类
 *
 * @author guanxp 2015-02-18
 */
public class SessionUtil {

    /**
     * 检测Session对象是否有效
     *
     * @param request
     * @return
     */
    public static boolean sessionCheck(HttpSession session) {
        if (session.getAttribute(Const.SESSION_USER) == null) {
            return false;
        }
        return true;
    }

    /**
     * 重置Session对象
     *
     * @param request
     * @return
     */
    public static void sessionReset(HttpSession session) {
        // 删除登录用户信息
        session.removeAttribute(Const.SESSION_USER);
        // 删除登录用户角色权限
        // session.removeAttribute(Const.SESSION_ROLE_RIGHTS);
        // 删除登录用户功能权限
        session.removeAttribute(Const.SESSION_USER_RIGHTS);
        // 删除登录用户菜单一览
        session.removeAttribute(Const.SESSION_MENU_LIST);
        // 销毁session
        session.invalidate();
    }

    /**
     * 设置Session属性值
     *
     * @param request
     * @param key
     * @param value
     */
    public static void setValue(HttpSession session, String key, Object value) {

        session.setAttribute(key, value);
    }

    /**
     * 读取Session属性值
     *
     * @param request
     * @param key
     * @param defValue
     * @return
     */
    public static Object getValue(HttpSession session, String key, Object defValue) {

        return session.getAttribute(key) == null ? defValue : session.getAttribute(key);
    }
}
