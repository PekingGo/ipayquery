package com.microfin.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.microfin.common.util.C;
import com.microfin.common.util.L;

/**
 * 系统监听类（服务器启动时执行）
 *
 * @author guanxp 2015/3/4
 */
public class SystemContextListener implements ServletContextListener {

    /* @Override */
    public void contextDestroyed(ServletContextEvent arg0) {
    }

    /* @Override */
    public void contextInitialized(ServletContextEvent arg0) {

        try {
            // common.properties数据加载
            C.initialize();

            // language-zh-CN数据加载
            L.initialize();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
