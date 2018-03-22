package com.microfin.logic.dao;

import com.microfin.logic.entity.MCCDetail;

import java.util.List;

/**
* MCC码扣率
 * Created by manxiaolei on 2017/10/11.
 */
public interface MccDetailDao {
	MCCDetail query(String id);

	List<MCCDetail> queryByCategory(String key);
}
