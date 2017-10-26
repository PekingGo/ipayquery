package com.microfin.logic.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microfin.common.util.Properties;
import com.microfin.logic.dao.BankCardDetailDao;
import com.microfin.logic.entity.BankCardDetail;
import com.microfin.logic.entity.Keyword;
import com.microfin.logic.entity.QueryLabel;
import com.microfin.logic.entity.QueryResult;
import com.microfin.logic.service.WatchService;

@Service("t_bank_card_detail")
@Transactional(readOnly = true)
public class BankCardDetailServiceImpl implements WatchService {
    @Autowired
    private BankCardDetailDao bankCardDetailDao;

    @Override
    public void queryForData(Map<String, List<Keyword>> queryMap, Map<String, Object> resultMap) {
        List<QueryResult> list = new ArrayList<QueryResult>();
        List<Keyword> queryList = queryMap.get("t_bank_card_detail");
        for (int i = 0; i < queryList.size(); i++) {
            Keyword keyword = queryList.get(i);
            QueryResult queryResult = new QueryResult();
            // list.add(bankCardDetailDao.query(keyword.getP_id()));
            // 输入的查询内容
            queryResult.setKey(resultMap.get("key").toString());
            // 类别
            String category = Properties.getValue("language-zh-CN", "t_bank_card_detail", "");
            queryResult.setCategory(category);
            // 信息标签
            List<QueryLabel> labelList = new ArrayList<QueryLabel>();
            BankCardDetail bean = bankCardDetailDao.query(keyword.getP_id());
            queryResult.setKeyword(bean.getMainValue());
            QueryLabel label = new QueryLabel("发卡行",bean.getOrgName(),true,true);
            // 发卡行
            labelList.add(label);
            // 银行卡类型
            label = new QueryLabel("银行卡类型",bean.getCardType(),true,true);
            labelList.add(label);
            // 银行卡名称
            label = new QueryLabel("银行卡名称",bean.getCardName(),true,false);
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
