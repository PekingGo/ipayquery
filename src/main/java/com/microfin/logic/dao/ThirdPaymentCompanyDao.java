package com.microfin.logic.dao;

import com.microfin.logic.entity.ThirdPaymentCompany;

import java.util.List;

/**
 * Created by manxiaolei on 2017/10/11.
 */
public interface ThirdPaymentCompanyDao {
	ThirdPaymentCompany query(String id);
	List<ThirdPaymentCompany> queryByCategory(String key);
}
