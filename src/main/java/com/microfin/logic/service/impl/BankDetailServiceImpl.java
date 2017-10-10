package com.microfin.logic.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microfin.common.util.Properties;
import com.microfin.common.util.StringUtil;
import com.microfin.logic.dao.BankDetailDao;
import com.microfin.logic.entity.BankDetail;
import com.microfin.logic.entity.Keyword;
import com.microfin.logic.entity.QueryLabel;
import com.microfin.logic.entity.QueryResult;
import com.microfin.logic.service.WatchService;

@Service("t_bank_detail")
@Transactional(readOnly = true)
public class BankDetailServiceImpl implements WatchService {
    @Autowired
    private BankDetailDao bankDetailDao;

    @Override
    public void queryForData(Map<String, List<Keyword>> queryMap, Map<String, Object> resultMap) {
        List<QueryResult> list = new ArrayList<QueryResult>();
        List<Keyword> queryList = queryMap.get("t_bank_detail");
        for (int i = 0; i < queryList.size(); i++) {
            Keyword keyword = queryList.get(0);
            // list.add(bankCardDetailDao.query(keyword.getP_id()));
            QueryResult queryResult = new QueryResult();
            // 输入的查询内容
            queryResult.setKey(resultMap.get("key").toString());
            // 类别
            String category = Properties.getValue("language-zh-CN", "t_bank_detail", "");
            queryResult.setCategory(category);
            // 信息标签
            List<QueryLabel> labelList = new ArrayList<QueryLabel>();
            BankDetail bean = bankDetailDao.query(keyword.getP_id());
            queryResult.setKeyword(keyword.getKey_word().replace("股份有限公司","..."));
            QueryLabel label = new QueryLabel();
            // 所属银行
            label.setText(bean.getBankName());
            label.setSeen(true);
            labelList.add(label);
            // 银行全称
            if (StringUtil.isNotEmpty(bean.getBankDetail())) {
                label = new QueryLabel();
                label.setText(bean.getBankDetail());
                label.setSeen(true);
                labelList.add(label);
            }
            // 地址
            if (StringUtil.isNotEmpty(bean.getAddress())) {
                label = new QueryLabel();
                label.setText(bean.getAddress());
                label.setSeen(true);
                labelList.add(label);
            }

            // 联系电话
            if (StringUtil.isNotEmpty(bean.getPhoneNo())) {
                label = new QueryLabel();
                label.setText(bean.getPhoneNo());
                label.setSeen(false);
                labelList.add(label);
            }
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
