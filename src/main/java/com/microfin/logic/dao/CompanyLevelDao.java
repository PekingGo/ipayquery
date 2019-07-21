package com.microfin.logic.dao;

import com.microfin.logic.entity.CompanyLevel;

import java.util.List;

public interface CompanyLevelDao {

    CompanyLevel query(String id);

    List<CompanyLevel> queryByCategory(String key);

}
