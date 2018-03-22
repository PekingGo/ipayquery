package com.microfin.logic.dao;

import com.microfin.logic.entity.BankDetail;

import java.util.List;

public interface BankDetailDao {

    BankDetail query(String recid);

    List<BankDetail> queryByCategory(String key);

}
