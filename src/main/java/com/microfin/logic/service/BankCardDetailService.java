package com.microfin.logic.service;

import java.util.List;

import com.microfin.logic.entity.BankCardDetail;

public interface BankCardDetailService {

    List<BankCardDetail> queryCardInfo(BankCardDetail bankCardDetail);

}
