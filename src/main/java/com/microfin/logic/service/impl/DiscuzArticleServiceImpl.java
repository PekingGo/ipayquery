package com.microfin.logic.service.impl;

import com.microfin.logic.discuzDao.DiscuzArticleDao;
import com.microfin.logic.entity.DiscuzArticle;
import com.microfin.logic.service.DiscuzArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manxiaolei on 2018/1/4.
 */
@Service
@Transactional(readOnly = true)
public class DiscuzArticleServiceImpl implements DiscuzArticleService {
	@Autowired
	private DiscuzArticleDao discuzArticleDao;
	@Override
	public List<DiscuzArticle> query(String key) {
		List<DiscuzArticle> list = discuzArticleDao.queryInfo(key);
		return list==null?new ArrayList<DiscuzArticle>():list;
	}
}
