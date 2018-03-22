package com.microfin.logic.dao;

import com.microfin.logic.entity.OrderAcceptOrg;

import java.util.List;

/**
* 收单机构号
*
 * Created by manxiaolei on 2017/10/11.
 */
public interface OrderAcceptOrgDao {
	OrderAcceptOrg query(String id);

	List<OrderAcceptOrg> queryByCategory(String key);
}
