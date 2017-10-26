package com.microfin.logic.service.impl;

import com.microfin.common.util.Properties;
import com.microfin.common.util.StringUtil;
import com.microfin.logic.dao.BlackListDao;
import com.microfin.logic.dao.UnionCorpDao;
import com.microfin.logic.entity.*;
import com.microfin.logic.service.WatchService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("t_union_corp")
@Transactional(readOnly = true)
public class UnionCorpServiceImpl implements WatchService {
    @Autowired
    private UnionCorpDao unionCorpDao;

    @Override
    public void queryForData(Map<String, List<Keyword>> queryMap, Map<String, Object> resultMap) {
        List<QueryResult> list = new ArrayList<QueryResult>();
        List<Keyword> queryList = queryMap.get("t_union_corp");
        for (int i = 0; i < queryList.size(); i++) {
            Keyword keyword = queryList.get(i);
            QueryResult queryResult = new QueryResult();
            // 输入的查询内容
            queryResult.setKey(resultMap.get("key").toString());
            // 类别
            String category = Properties.getValue("language-zh-CN", "t_union_corp", "");
            queryResult.setCategory(category);
            // 信息标签
            List<QueryLabel> labelList = new ArrayList<QueryLabel>();
            UnionCorp bean = unionCorpDao.query(keyword.getP_id());
            queryResult.setKeyword(keyword.getKey_word().length()>11?keyword.getKey_word().substring(0,11)+"..":keyword.getKey_word());
            QueryLabel label = new QueryLabel("公司名称",bean.getCorp_name(),true,true);
            // 公司名称
            labelList.add(label);
            // 法人
            label = new QueryLabel("法人",bean.getLegal(),true,true);
            labelList.add(label);
            if(StringUtil.isNotEmpty(bean.getAddress())){
                // 营业场所
                label = new QueryLabel("营业场所",bean.getAddress(),true,true);
                labelList.add(label);
            }
            //服务范围
            if(StringUtil.isNotEmpty(bean.getService_type())){
                label = new QueryLabel("服务范围",bean.getService_type(),true,false);
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
