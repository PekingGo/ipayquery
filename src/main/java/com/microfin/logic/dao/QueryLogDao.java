package com.microfin.logic.dao;

import com.microfin.logic.entity.QueryLog;

import java.util.List;

/**
 * Created by manxiaolei on 2017/10/26.
 */
public interface QueryLogDao {
	void insert(QueryLog queryLog);
	List<String> query();
}
