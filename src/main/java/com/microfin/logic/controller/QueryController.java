package com.microfin.logic.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.microfin.common.util.StringUtil;
import com.microfin.logic.consts.QueryConsts;
import com.microfin.logic.entity.Keyword;
import com.microfin.logic.entity.KeywordTableMap;
import com.microfin.logic.service.KeywordResultService;
import com.microfin.logic.service.KeywordService;
import com.microfin.logic.service.WatchService;

/**
 * 信息查询主处理器 观察者模式
 *
 * @author manxiaolei 2017-3-4 21:12:13
 *
 */
@Controller
public class QueryController {

    @Autowired
    private KeywordService keywordServie;
    @Autowired
    private KeywordResultService keywordResultService;
    @Autowired
    private ServletContext servletContext;

    @RequestMapping(value = QueryConsts.QUERY, method = RequestMethod.GET)
    public @ResponseBody JSONPObject query(String key, String jsonpCallback) throws Exception {
        // 获取ioc容器
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        Map<String, Object> returnMap = new HashMap<String, Object>();
        // JSONObject json = JSONObject.fromObject(key);
        Keyword keyword = new Keyword();
        List<KeywordTableMap> list = null;
        key = URLDecoder.decode(key,"UTF-8");
        // key不能为空
        if (StringUtil.isNotEmpty(key)) {
            keyword.setKey_word(key);
            list = keywordServie.query(keyword);
        }
        // 查询参数Map
        Map<String, List<Keyword>> queryMap = new HashMap<String, List<Keyword>>();
        keywordResultService.removeAllService();
        for (KeywordTableMap keywordResult : list) {
            if (keywordResult == null) {
                continue;
            }
            queryMap.put(keywordResult.getKey(), keywordResult.getValue());
            // 此处前提是把表名对应service的名字用@Service("name")配置成和表名一致
//            keywordResultService.delWatchService((WatchService) ctx.getBean(keywordResult.getKey()));
            keywordResultService.addWatchServcie((WatchService) ctx.getBean(keywordResult.getKey()));
        }
        // 查询内容
        returnMap.put("key", key);
        keywordResultService.notifyServiceToQuery(queryMap, returnMap);
        // returnMap.put(QueryConsts.RESULT,
        // JSONArray.fromObject(list).toString());

        return new JSONPObject(jsonpCallback, returnMap);
    }

}
