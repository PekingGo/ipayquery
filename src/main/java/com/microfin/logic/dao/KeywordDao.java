package com.microfin.logic.dao;

import java.util.List;
import java.util.Map;

import com.microfin.logic.entity.Keyword;

public interface KeywordDao {
    /**
     *
     * @param keyword
     * @return
     */
    public Map<String, List<Keyword>> queryNumber(Keyword keyword);

    public List<Keyword> queryCharacter(Keyword keyword);
}
