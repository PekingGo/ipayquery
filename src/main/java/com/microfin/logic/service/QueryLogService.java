package com.microfin.logic.service;

import com.microfin.logic.entity.QueryLog;

import javax.management.Query;
import java.util.List;

/**
 * Created by manxiaolei on 2017/10/26.
 */
public interface QueryLogService {
	void insert(QueryLog queryLog);
	List<String> query();
}
