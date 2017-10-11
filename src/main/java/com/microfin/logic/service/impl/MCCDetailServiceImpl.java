package com.microfin.logic.service.impl;

import com.microfin.common.util.Properties;
import com.microfin.common.util.StringUtil;
import com.microfin.logic.dao.MccDetailDao;
import com.microfin.logic.entity.Keyword;
import com.microfin.logic.entity.MCCDetail;
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

@Service("mcc_t")
@Transactional(readOnly = true)
public class MCCDetailServiceImpl implements WatchService {
    @Autowired
    private MccDetailDao mccDetailDao;

    @Override
    public void queryForData(Map<String, List<Keyword>> queryMap, Map<String, Object> resultMap) {
        List<QueryResult> list = new ArrayList<QueryResult>();
        List<Keyword> queryList = queryMap.get("mcc_t");
        for (int i = 0; i < queryList.size(); i++) {
            Keyword keyword = queryList.get(0);
            // list.add(bankCardDetailDao.query(keyword.getP_id()));
            QueryResult queryResult = new QueryResult();
            // 输入的查询内容
            queryResult.setKey(resultMap.get("key").toString());
            // 类别
            String category = Properties.getValue("language-zh-CN", "mcc_t", "");
            queryResult.setCategory(category);
            // 信息标签
            List<QueryLabel> labelList = new ArrayList<QueryLabel>();
            MCCDetail bean = mccDetailDao.query(keyword.getP_id());
            queryResult.setKeyword(bean.getMccNo()+"|"+bean.getBusType());
            QueryLabel label = new QueryLabel();
            // MCC码
            if(StringUtil.isNotEmpty(bean.getMccNo())){
                label.setText(bean.getMccNo());
                label.setSeen(true);
                labelList.add(label);
            }
            // MCC大类
            if(StringUtil.isNotEmpty(bean.getType())){
                label = new QueryLabel();
                label.setText(bean.getType());
                label.setSeen(true);
                labelList.add(label);
            }
            // MCC小类
            if(StringUtil.isNotEmpty(bean.getBusType())){
                label = new QueryLabel();
                label.setText(bean.getBusType());
                label.setSeen(true);
                labelList.add(label);
            }
            if(StringUtil.isNotEmpty(bean.getTypeDetail())){
                // MCC细类
                label = new QueryLabel();
                label.setText(bean.getTypeDetail());
                label.setSeen(true);
                labelList.add(label);
            }
            if(StringUtil.isNotEmpty(bean.getBusDetail())){
                // 经营类别详细描述
                label = new QueryLabel();
                label.setText(bean.getBusDetail());
                label.setSeen(true);
                labelList.add(label);
            }
            // 商户类别名
            if(StringUtil.isNotEmpty(bean.getBusNameType())){
                label = new QueryLabel();
                label.setText(bean.getBusNameType());
                label.setSeen(true);
                labelList.add(label);
            }
            //银联扣率
            if(StringUtil.isNotEmpty(bean.getPresent())){
                label = new QueryLabel();
                label.setText(bean.getPresent());
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
