package com.microfin.logic.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microfin.logic.dao.BankCardDetailDao;
import com.microfin.logic.entity.Keyword;
import com.microfin.logic.service.WatchService;

@Service("t_bank_card_detail")
@Transactional(readOnly = true)
public class BankCardDetailServiceImpl implements WatchService {
    @Autowired
    private BankCardDetailDao bankCardDetailDao;

    @Override
    public void queryForData(Map<String, List<Keyword>> queryMap, Map<String, Object> resultMap) {

    }

}
