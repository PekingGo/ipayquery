package com.microfin.logic.dao;

import java.util.List;

import com.microfin.logic.entity.BankCardDetail;

public interface BankCardDetailDao {

    List<BankCardDetail> queryCardInfo(BankCardDetail bankCardDetail);

    int queryDataNum(BankCardDetail bankCardDetail);

}
