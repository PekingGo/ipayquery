package com.microfin.logic.interceptor;

import com.microfin.common.util.*;
import net.sf.json.JSONObject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * 请求次数限制
 *
 * @author manxiaolei 2017-10-25
 */
public class RequestLimitsInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求信息
        String remoteIp = IPUtil.getIpAddr(request);
        //设置时分秒缓存key
        String mm_key = "limit-min:"+remoteIp;
        String hh_key = "limit-hour:"+remoteIp;
        String dd_key = "limit-day:"+remoteIp;
        System.out.println("IP:"+remoteIp);
        //访问次数+1
        long mm_count = JedisUtil.increaseNum(mm_key,1),
        hh_count = JedisUtil.increaseNum(hh_key,1),
        dd_count = JedisUtil.increaseNum(dd_key,1);
        //第一次访问则设置失效时间
        if (mm_count == 1) {
            JedisUtil.expire(mm_key,1, TimeUnit.MINUTES);
        }
        if (dd_count == 1) {
            JedisUtil.expire(hh_key,1, TimeUnit.HOURS);
        }
        if (mm_count == 1) {
            JedisUtil.expire(dd_key,1, TimeUnit.DAYS);
        }
        int mm_limits = Integer.parseInt(Properties.getValue("language-zh-CN","minutes_limits","")),
        hh_limits = Integer.parseInt(Properties.getValue("language-zh-CN","hour_limits","")),
        dd_limits = Integer.parseInt(Properties.getValue("language-zh-CN","day_limits",""));
        String jsonCallBack = request.getParameter("jsonpCallback");
        if(mm_count>mm_limits||hh_count>hh_limits||dd_count>dd_limits){
            PrintWriter printWriter = response.getWriter();
            JSONObject result = new JSONObject();
            result.element("invalid", 4);
            printWriter.write(jsonCallBack+"("+result+")");
            printWriter.close();
        }else{
            return true;
        }
        return false;
    }

}
