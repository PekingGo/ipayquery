package com.microfin.logic.service.impl;

import com.microfin.common.util.Properties;
import com.microfin.logic.dao.BlackListDao;
import com.microfin.logic.entity.BlackList;
import com.microfin.logic.entity.Keyword;
import com.microfin.logic.entity.QueryLabel;
import com.microfin.logic.entity.QueryResult;
import com.microfin.logic.service.WatchService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("t_black_list")
@Transactional(readOnly = true)
public class BlackListServiceImpl implements WatchService {
    @Autowired
    private BlackListDao blackListDao;

    @Override
    public void queryForData(Map<String, List<Keyword>> queryMap, Map<String, Object> resultMap) {
        List<QueryResult> list = new ArrayList<QueryResult>();
        List<Keyword> queryList = queryMap.get("t_black_list");
        for (int i = 0; i < queryList.size(); i++) {
            Keyword keyword = queryList.get(i);
            // list.add(bankCardDetailDao.query(keyword.getP_id()));
            QueryResult queryResult = new QueryResult();
            // 输入的查询内容
            queryResult.setKey(resultMap.get("key").toString());
            // 类别
            String category = Properties.getValue("language-zh-CN", "t_black_list", "");
            queryResult.setCategory(category);
            // 信息标签
            List<QueryLabel> labelList = new ArrayList<QueryLabel>();
            BlackList bean = blackListDao.query(keyword.getP_id());
            queryResult.setKeyword(keyword.getKey_word().length()>11?keyword.getKey_word().substring(0,11)+"..":keyword.getKey_word());
            QueryLabel label = new QueryLabel("公司名称",bean.getCompany(),true,true);
            // 公司名称
            labelList.add(label);
            // 是否在黑名单内
            label = new QueryLabel("是否在黑名单内",bean.getInblacklist(),true,true);
            labelList.add(label);
            // 发布日期
            label = new QueryLabel("发布日期",bean.getIssdate(),true,false);
            labelList.add(label);
            queryResult.setInfoArr(labelList);
            if (i == 0) {
                list.add(queryResult);
            } else {
                list.get(0).setMoreList(queryResult);
            }
        }
        if (resultMap.containsKey("list")) {
            JSONArray dataArr = JSONArray.fromObject(resultMap.get("list"));
            dataArr.addAll(JSONArray.fromObject(list));
            resultMap.put("list", dataArr);
        } else {
            resultMap.put("list", JSONArray.fromObject(list));
        }
    }
}
