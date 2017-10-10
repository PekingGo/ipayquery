package com.microfin.logic.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.microfin.logic.entity.Keyword;
import com.microfin.logic.service.KeywordResultService;
import com.microfin.logic.service.WatchService;

@Service
public class KeywordResultServiceImpl implements KeywordResultService {

    private final List<WatchService> serviceList = new ArrayList<WatchService>();

    @Override
    public void addWatchServcie(WatchService queryService) {
        if(!serviceList.contains(queryService)){
            serviceList.add(queryService);
        }
    }

    @Override
    public void delWatchService(WatchService queryService) {
        serviceList.remove(queryService);
    }

    @Override
    public void notifyServiceToQuery(Map<String, List<Keyword>> queryMap, Map<String, Object> returnMap) {
        for (WatchService queryService : serviceList) {
            queryService.queryForData(queryMap, returnMap);
        }
    }

    @Override
    public void removeAllService() {
        serviceList.removeAll(serviceList);
    }

}
