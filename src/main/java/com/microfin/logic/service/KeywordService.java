package com.microfin.logic.service;

import java.util.List;

import com.microfin.logic.entity.Keyword;
import com.microfin.logic.entity.KeywordTableMap;

public interface KeywordService {
    /**
     *
     * @param keyword
     * @return
     */
     List<KeywordTableMap> query(Keyword keyword);
}
