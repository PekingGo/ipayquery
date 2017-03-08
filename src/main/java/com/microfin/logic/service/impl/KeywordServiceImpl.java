package com.microfin.logic.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microfin.common.util.StringUtil;
import com.microfin.logic.dao.KeywordDao;
import com.microfin.logic.entity.Keyword;
import com.microfin.logic.entity.KeywordTableMap;
import com.microfin.logic.service.KeywordService;

@Service
public class KeywordServiceImpl implements KeywordService {

    @Autowired
    private KeywordDao keywordDao;

    @Override
    public List<KeywordTableMap> query(Keyword keyword) {
        // List<Keyword> list;
        List<KeywordTableMap> list;
        if (StringUtil.isNumeric(keyword.getKey_word())) {
            list = keywordDao.queryNumber(keyword);
        } else {
            // list = keywordDao.queryCharacter(keyword);
            list = null;
        }
        return list == null ? new ArrayList<KeywordTableMap>() : list;
    }
}
