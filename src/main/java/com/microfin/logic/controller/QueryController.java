package com.microfin.logic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.microfin.common.util.StringUtil;
import com.microfin.logic.consts.QueryConsts;
import com.microfin.logic.entity.Keyword;
import com.microfin.logic.entity.KeywordTableMap;
import com.microfin.logic.service.KeywordService;

/**
 * 信息查询主处理器
 *
 * @author manxiaolei 2017-3-4 21:12:13
 *
 */
@Controller
public class QueryController {
    @Autowired
    private KeywordService keywordServie;

    @RequestMapping(QueryConsts.QUERY)
    public @ResponseBody List<KeywordTableMap> query(String key) {
        Keyword keyword = new Keyword();
        List<KeywordTableMap> list = null;
        if (StringUtil.isNotEmpty(key)) {
            keyword.setKey_word(key);
            list = keywordServie.query(keyword);
        }
        return list;
    }
}
