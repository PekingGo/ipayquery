package com.microfin.logic.service;

import java.util.List;
import java.util.Map;

import com.microfin.logic.entity.Keyword;

/**
 * 观察区分具体关键字后查询具体分类Service
 *
 * @author manxiaolei
 *
 */
public interface WatchService {
    public void queryForData(Map<String, List<Keyword>> queryMap, Map<String, Object> resultMap);
}
