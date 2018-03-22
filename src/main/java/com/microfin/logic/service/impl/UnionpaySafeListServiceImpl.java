package com.microfin.logic.service.impl;

import com.microfin.common.util.Properties;
import com.microfin.common.util.StringUtil;
import com.microfin.logic.dao.BlackListDao;
import com.microfin.logic.dao.UnionpaySafeListDao;
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

@Service("t_unionpay_safe_list")
@Transactional(readOnly = true)
public class UnionpaySafeListServiceImpl implements WatchService {
    @Autowired
    private UnionpaySafeListDao unionpaySafeListDao;

    @Override
    public void queryForData(Map<String, List<Keyword>> queryMap, Map<String, Object> resultMap) {
        List<QueryResult> list = new ArrayList<QueryResult>();
        List<Keyword> queryList = queryMap.get("t_unionpay_safe_list");
        for (int i = 0; i < queryList.size(); i++) {
            Keyword keyword = queryList.get(i);
            // list.add(bankCardDetailDao.query(keyword.getP_id()));
            QueryResult queryResult = new QueryResult();
            // 输入的查询内容
            queryResult.setKey(resultMap.get("key").toString());
            // 类别
            String category = Properties.getValue("language-zh-CN", "t_unionpay_safe_list", "");
            queryResult.setCategory(category);
            // 信息标签
            List<QueryLabel> labelList = new ArrayList<QueryLabel>();
            UnionpaySafeList bean = unionpaySafeListDao.query(keyword.getP_id());
            queryResult.setKeyword(keyword.getKey_word().length()>11?keyword.getKey_word().substring(0,11)+"..":keyword.getKey_word());
            QueryLabel label = new QueryLabel("省份",bean.getProvince(),true,true);
            // 省份
            labelList.add(label);
            // 地区
            label = new QueryLabel("地区",bean.getCity(),true,true);
            labelList.add(label);
            // 批发市场名称
            label = new QueryLabel("批发市场名称",bean.getOrgName(),true,true);
            labelList.add(label);
            // 经营范围
            label = new QueryLabel("经营范围",bean.getBusCategory(),true,true);
            labelList.add(label);
            // 批发市场地址
            label = new QueryLabel("批发市场地址",bean.getAddress(),true,false);
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
        List<UnionpaySafeList> list = unionpaySafeListDao.queryByCategory(key);
        //存储list
        QueryResult resultAll = null;
        String category = Properties.getValue("language-zh-CN", "t_third_payment_company", "第三方支付公司");
        int index = 0;
        if(list!=null&&list.size()>0){
            resultAll = new QueryResult();
            for(UnionpaySafeList bean : list){
                QueryResult result = new QueryResult();
                //类目
                result.setCategory(category);
                //查询关键字
                result.setKey(key);
                //关键字
                result.setKeyword(bean.getOrgName());
                //信息标签
                List<QueryLabel> labelList = new ArrayList<QueryLabel>();
                QueryLabel label = new QueryLabel("省份",bean.getProvince(),true,true);
                // 省份
                labelList.add(label);
                // 地区
                label = new QueryLabel("地区",bean.getCity(),true,true);
                labelList.add(label);
                // 批发市场名称
                label = new QueryLabel("批发市场名称",bean.getOrgName(),true,true);
                labelList.add(label);
                // 经营范围
                label = new QueryLabel("经营范围",bean.getBusCategory(),true,true);
                labelList.add(label);
                // 批发市场地址
                label = new QueryLabel("批发市场地址",bean.getAddress(),true,false);
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
