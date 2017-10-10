package com.microfin.logic.service;

import java.util.List;
import java.util.Map;

import com.microfin.logic.entity.Keyword;

public interface KeywordResultService {

    public void addWatchServcie(WatchService queryService);

    public void delWatchService(WatchService queryService);

    public void notifyServiceToQuery(Map<String, List<Keyword>> queryMap, Map<String, Object> returnMap);

    public void removeAllService();
}
