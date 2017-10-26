package com.microfin.logic.interceptor;

import com.microfin.common.util.*;
import net.sf.json.JSONObject;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URLDecoder;

/**
 *  查询内容的过滤
 *
 *  Author flyman
 */
public class QueryKeyHandlerInterceptor extends HandlerInterceptorAdapter {

    /**
     * 对于无效的查询内容统一进行过滤，并对查询内容进行存储
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
        if (path.matches(Const.QUERY_INTERCEPTOR_PATH)) {
            String key = request.getParameter("key");
            String jsonCallBack = request.getParameter("jsonpCallback");
            String invalidKey = Properties.getValue("language-zh-CN","invalid_querykey","");
            if(StringUtil.isNotEmpty(key)){
                key = URLDecoder.decode(key,"UTF-8");
                if(invalidKey.contains(key)){
                    JSONObject result = new JSONObject();
                    result.element("invalid", 1);
                    PrintWriter printWriter = response.getWriter();
                    printWriter.write(jsonCallBack+"("+result+")");
                    printWriter.close();
                    return false;
                }else{

                }
            }else {
                JSONObject result = new JSONObject();
                result.element("invalid", 1);
                PrintWriter printWriter = response.getWriter();
                printWriter.write(jsonCallBack+"("+result+")");
                printWriter.close();
                return false;
            }
        }
        return true;
    }

}
