package com.microfin.logic.service.impl;

import com.microfin.common.util.Properties;
import com.microfin.common.util.StringUtil;
import com.microfin.logic.dao.CmbcNoScoreDao;
import com.microfin.logic.entity.*;
import com.microfin.logic.service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by manxiaolei on 2017/11/16.
 */
@Service("t_cmbc_noscore")
public class CmbcNoScoreServiceImpl implements WatchService {
	@Autowired
	private CmbcNoScoreDao cmbcNoScoreDao;

	@Override
	public void queryForData(Map<String, List<Keyword>> queryMap, Map<String, Object> resultMap) {

	}

	/**
	 * 单类目查询
	 *
	 * @param key
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> queryByCategory(String key) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		//查询List
		List<CmbcNoScore> list = cmbcNoScoreDao.queryByCategory(key);
		//存储list
		QueryResult resultAll = null;
		//类目名
		String category = Properties.getValue("language-zh-CN", "t_cmbc_noscore", "招行无积分商户");
		if(list!=null&&list.size()>0){
			resultAll = new QueryResult();
			int index =0;
			for(CmbcNoScore bean :list){
				QueryResult result = new QueryResult();
				//查询内容
				result.setKey(key);
				//类别
				result.setCategory(category);
				//关键字
				result.setKeyword(bean.getCompanyName());
				//信息标签
				List<QueryLabel> labelList = new ArrayList<QueryLabel>();
				QueryLabel label = new QueryLabel("银联商户号",bean.getCompanyNo(),true,true);
				// 银联商户号
				labelList.add(label);
				// 商户名称
				if(StringUtil.isNotEmpty(bean.getCompanyName())){
					label = new QueryLabel("商户名称",bean.getCompanyName(),true,true);
					labelList.add(label);
				}
				// 招行卡是否有积分
				label = new QueryLabel("招行卡是否有积分",bean.getScore(),true,true);
				labelList.add(label);
				//收单机构号
				label = new QueryLabel("收单机构号",bean.getOrgNo(),true,true);
				labelList.add(label);
				//所属收单机构
				label = new QueryLabel("所属收单机构",bean.getOrgName(),true,true);
				labelList.add(label);
				//数据更新时间
				label = new QueryLabel("数据更新时间",bean.getUpTime(),true,false);
				labelList.add(label);
				result.setInfoArr(labelList);
				if(index ==0){
					resultAll = result;
				}else{
					resultAll.setMoreList(result);
				}
				index++;
			}
		}
        map.put("queryResult",resultAll);
		return map;
	}

}
