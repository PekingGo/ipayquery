package com.microfin.logic.dao;

import com.microfin.logic.entity.BankDetail;
import com.microfin.logic.entity.BlackList;

import java.util.List;

/**
 * 手刷品牌黑名单
 * Created by manxiaolei on 2017/10/10.
 */
public interface BlackListDao {
	BlackList query(String id);
	List<BlackList> queryByCategory(String key);
}
