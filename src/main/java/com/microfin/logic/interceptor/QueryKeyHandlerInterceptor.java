package com.microfin.logic.interceptor;

import com.microfin.common.util.*;
import com.microfin.logic.entity.QueryLog;
import com.microfin.logic.service.QueryLogService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  查询内容的过滤
 *
 *  Author flyman
 */
public class QueryKeyHandlerInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private QueryLogService queryLogService;
    private ExecutorService exec = Executors.newFixedThreadPool(5);
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
                if(StringUtil.isEmpty(key)||invalidKey.contains(key)){
                    JSONObject result = new JSONObject();
                    result.element("invalid", 1);
                    PrintWriter printWriter = response.getWriter();
                    printWriter.write(jsonCallBack+"("+result+")");
                    printWriter.close();
                    return false;
                }else{
                    String catg = request.getParameter("catg");
                    catg =  URLDecoder.decode(catg,"UTF-8");
                    final QueryLog logInfo = new QueryLog(key,catg,IPUtil.getIpAddr(request),DateUtil.getCurDate());
                    exec.execute(new Runnable() {
                        @Override
                        public void run() {
                            queryLogService.insert(logInfo);
                        }
                    });
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
