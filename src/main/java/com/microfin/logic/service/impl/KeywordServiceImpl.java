package com.microfin.logic.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microfin.common.util.StringUtil;
import com.microfin.logic.dao.KeywordDao;
import com.microfin.logic.entity.Keyword;
import com.microfin.logic.service.KeywordService;

@Service
public class KeywordServiceImpl implements KeywordService {

    @Autowired
    private KeywordDao keywordDao;

    @Override
    public Map<String, List<Keyword>> query(Keyword keyword) {
        // List<Keyword> list;
        Map<String, List<Keyword>> resultMap;
        if (StringUtil.isNum(keyword.getKey_word())) {
            resultMap = keywordDao.queryNumber(keyword);
        } else {
            // list = keywordDao.queryCharacter(keyword);
            resultMap = new HashMap<String, List<Keyword>>();
        }
        return resultMap == null ? new HashMap<String, List<Keyword>>() : resultMap;
    }
}
