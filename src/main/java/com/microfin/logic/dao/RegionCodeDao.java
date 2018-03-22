package com.microfin.logic.dao;

import com.microfin.logic.entity.RegionCode;

import java.util.List;

/**
* 地区代码查询dao
 * Created by manxiaolei on 2017/10/11.
 */
public interface RegionCodeDao {
	RegionCode query(String code);

	List<RegionCode> queryByCategory(String key);
}
