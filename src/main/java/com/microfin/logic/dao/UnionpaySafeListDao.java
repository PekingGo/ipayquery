package com.microfin.logic.dao;

import com.microfin.logic.entity.UnionpaySafeList;

import java.util.List;

/**
 * Created by manxiaolei on 2017/10/11.
 */
public interface UnionpaySafeListDao {
	UnionpaySafeList query(String id);
	List<UnionpaySafeList> queryByCategory(String key);
}
