package com.microfin.logic.service.impl;

import com.microfin.common.util.Properties;
import com.microfin.logic.dao.BlackListDao;
import com.microfin.logic.dao.RegionCodeDao;
import com.microfin.logic.entity.*;
import com.microfin.logic.service.WatchService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("t_code_detail")
@Transactional(readOnly = true)
public class RegionCodeServiceImpl implements WatchService {
    @Autowired
    private RegionCodeDao regionCodeDao;

    @Override
    public void queryForData(Map<String, List<Keyword>> queryMap, Map<String, Object> resultMap) {
        List<QueryResult> list = new ArrayList<QueryResult>();
        List<Keyword> queryList = queryMap.get("t_code_detail");
        for (int i = 0; i < queryList.size(); i++) {
            Keyword keyword = queryList.get(i);
            QueryResult queryResult = new QueryResult();
            // 输入的查询内容
            queryResult.setKey(resultMap.get("key").toString());
            // 类别
            String category = Properties.getValue("language-zh-CN", "t_code_detail", "");
            queryResult.setCategory(category);
            // 信息标签
            List<QueryLabel> labelList = new ArrayList<QueryLabel>();
            RegionCode bean = regionCodeDao.query(keyword.getP_id());
            queryResult.setKeyword(keyword.getKey_word());
            QueryLabel label = new QueryLabel("代码",bean.getCode(),true,true);
            // 代码
            labelList.add(label);
            // 地区
            label = new QueryLabel("地区",bean.getAddress(),true,true);
            labelList.add(label);
            // 省市
            label = new QueryLabel("省市",bean.getProvince(),true,false);
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
    @Override
    public Map<String,Object> queryByCategory(String key){
        Map<String,Object> map = new HashMap<String, Object>();
        List<RegionCode> list = regionCodeDao.queryByCategory(key);
        //存储list
        QueryResult resultAll = null;
        //类目
        String category = Properties.getValue("language-zh-CN", "t_code_detail", "地区代码");
        if(list!=null&&list.size()>0){
            int index =0;
            resultAll = new QueryResult();
            for(RegionCode bean : list){
                QueryResult result = new QueryResult();
                //类目
                result.setCategory(category);
                //查询内容
                result.setKey(key);
                //关键字
                result.setKeyword(bean.getCode());
                //信息标签
                List<QueryLabel> labelList = new ArrayList<QueryLabel>();
                QueryLabel label = new QueryLabel("代码",bean.getCode(),true,true);
                // 代码
                labelList.add(label);
                // 地区
                label = new QueryLabel("地区",bean.getAddress(),true,true);
                labelList.add(label);
                // 省市
                label = new QueryLabel("省市",bean.getProvince(),true,false);
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
        return  map;
    }
}