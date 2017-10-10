package com.microfin.logic.dao;

import com.microfin.logic.entity.BankCardDetail;

public interface BankCardDetailDao {

    BankCardDetail query(String id);

    int queryDataNum(BankCardDetail bankCardDetail);

}
