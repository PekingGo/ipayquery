package com.microfin.logic.dao;

import java.util.List;

import com.microfin.logic.entity.Keyword;
import com.microfin.logic.entity.KeywordTableMap;

public interface KeywordDao {
    /**
     *
     * @param keyword
     * @return
     */
    public List<KeywordTableMap> queryNumber(Keyword keyword);

    public List<Keyword> queryCharacter(Keyword keyword);
}
