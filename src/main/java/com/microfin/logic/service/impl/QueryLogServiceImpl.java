package com.microfin.logic.service.impl;

import com.microfin.logic.dao.QueryLogDao;
import com.microfin.logic.entity.QueryLog;
import com.microfin.logic.service.QueryLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manxiaolei on 2017/10/26.
 */
@Service
@Transactional(readOnly = true)
public class QueryLogServiceImpl implements QueryLogService {
	@Autowired
	private QueryLogDao queryLogDao;

	/**
	 * 热词插入表
	 * @param queryLog
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
	public void insert(QueryLog queryLog) {
		queryLogDao.insert(queryLog);
	}

	/**
	 * 热词查询
	 * @return
	 */
	@Override
	public List<String> query() {
		List<String> list = queryLogDao.query();
		return list==null?new ArrayList<String>():list;
	}
}
