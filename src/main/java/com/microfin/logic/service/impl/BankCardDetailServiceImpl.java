package com.microfin.logic.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microfin.logic.dao.BankCardDetailDao;
import com.microfin.logic.entity.BankCardDetail;
import com.microfin.logic.service.BankCardDetailService;

@Service
@Transactional(readOnly = true)
public class BankCardDetailServiceImpl implements BankCardDetailService {
    @Autowired
    private BankCardDetailDao bankCardDetailDao;

    /**
     * 根据输入信息查询银行卡信息
     */
    @Override
    public List<BankCardDetail> queryCardInfo(BankCardDetail bankCardDetail) {
        List<BankCardDetail> list = bankCardDetailDao.queryCardInfo(bankCardDetail);
        return list == null ? new ArrayList<BankCardDetail>() : list;
    }

}
