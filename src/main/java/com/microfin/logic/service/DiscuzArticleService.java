package com.microfin.logic.service;

import com.microfin.logic.entity.DiscuzArticle;

import java.util.List;

/**
 * Created by manxiaolei on 2018-1-4
 */
public interface DiscuzArticleService {
	List<DiscuzArticle> query(String key);
}
