package com.microfin.logic.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.microfin.common.util.StringUtil;
import com.microfin.logic.consts.QueryConsts;
import com.microfin.logic.entity.Keyword;
import com.microfin.logic.entity.KeywordTableMap;
import com.microfin.logic.service.KeywordResultService;
import com.microfin.logic.service.KeywordService;
import com.microfin.logic.service.WatchService;

/**
 * 信息查询主处理器
 *
 * @author manxiaolei 2017-3-4 21:12:13
 *
 */
@Controller
public class QueryController {
    private final ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mvc.xml");
    @Autowired
    private KeywordService keywordServie;
    @Autowired
    private KeywordResultService keywordResultService;

    @RequestMapping(QueryConsts.QUERY)
    public @ResponseBody Map<String, Object> query(String key) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        Keyword keyword = new Keyword();
        List<KeywordTableMap> list = null;
        // key不能为空
        if (StringUtil.isNotEmpty(key)) {
            keyword.setKey_word(key);
            list = keywordServie.query(keyword);
        }
        // 查询参数Map
        Map<String, List<Keyword>> queryMap = new HashMap<String, List<Keyword>>();
        for (KeywordTableMap keywordResult : list) {
            if (keywordResult == null) {
                continue;
            }
            queryMap.put(keywordResult.getKey(), keywordResult.getValue());
            // 此处前提是把表名对应service的名字用@Service("name")配置成和表名一致
            keywordResultService.addWatchServcie((WatchService) ctx.getBean(keywordResult.getKey()));
        }
        keywordResultService.notifyServiceToQuery(queryMap, returnMap);
        returnMap.put(QueryConsts.RESULT, list);
        return returnMap;
    }
}
