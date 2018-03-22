package com.microfin.logic.dao;

import com.microfin.logic.entity.BankCardDetail;
import java.util.List;

public interface BankCardDetailDao {

    BankCardDetail query(String id);

    List<BankCardDetail> queryByCategory(String key);

}
