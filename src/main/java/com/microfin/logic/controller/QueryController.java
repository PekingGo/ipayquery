package com.microfin.logic.controller;

import java.net.URLDecoder;
import java.util.*;

import javax.servlet.ServletContext;

import com.microfin.logic.entity.QueryResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
            //当输入key为数字并且长度少于6时一定不是查询银行卡bin或者开户联行号
            if(StringUtil.isNumeric(key)&&(("t_bank_detail".equals(keywordResult.getKey())&&key.length()<6)||("t_bank_card_detail".equals(keywordResult.getKey())&&key.length()<6))){
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
        if(returnMap.get("list")==null){
            returnMap.put("list",new JSONArray());
        }
        JSONArray orginList = JSONArray.fromObject(returnMap.get("list"));
        //根据更多结果的大小排序，目前算法是认为搜索结果越少越贴近用户想要搜索的内容
        Collections.sort(orginList, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject o1, JSONObject o2) {
                int o1Size = JSONArray.fromObject(o1.get("moreList")).size();
                int o2Size = JSONArray.fromObject(o2.get("moreList")).size();
                return o1Size -o2Size;
            }
        });
        returnMap.put("list",orginList);
        return new JSONPObject(jsonpCallback, returnMap);
    }

}
