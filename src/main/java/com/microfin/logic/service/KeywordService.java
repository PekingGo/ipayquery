package com.microfin.logic.service;

import java.util.List;
import java.util.Map;

import com.microfin.logic.entity.Keyword;

public interface KeywordService {
    /**
     *
     * @param keyword
     * @return
     */
    public Map<String, List<Keyword>> query(Keyword keyword);
}
