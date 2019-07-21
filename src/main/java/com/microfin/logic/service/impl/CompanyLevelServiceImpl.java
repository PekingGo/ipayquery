package com.microfin.logic.service.impl;

import com.microfin.common.util.Properties;
import com.microfin.logic.dao.CompanyLevelDao;
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

@Service("t_company_level")
@Transactional(readOnly = true)
public class CompanyLevelServiceImpl implements WatchService {
    @Autowired
    private CompanyLevelDao companyLevelDao;

    @Override
    public void queryForData(Map<String, List<Keyword>> queryMap, Map<String, Object> resultMap) {
        List<QueryResult> list = new ArrayList<QueryResult>();
        List<Keyword> queryList = queryMap.get("t_company_level");
        for (int i = 0; i < queryList.size(); i++) {
            Keyword keyword = queryList.get(i);
            QueryResult queryResult = new QueryResult();
            // 输入的查询内容
            queryResult.setKey(resultMap.get("key").toString());
            // 类别
            String category = Properties.getValue("language-zh-CN", "t_company_level", "");
            queryResult.setCategory(category);
            // 信息标签
            List<QueryLabel> labelList = new ArrayList<QueryLabel>();
            CompanyLevel bean = companyLevelDao.query(keyword.getP_id());
            queryResult.setKeyword(bean.getOrgName());
            QueryLabel label = new QueryLabel("公司名",bean.getOrgName(),true,true);
            // 外包服务机构名称
            labelList.add(label);
            // 营业执照注册号
            label = new QueryLabel("营业执照编号",bean.getOrgNo(),true,true);
            labelList.add(label);
            // 2018年度
            label = new QueryLabel("2018年度",bean.getLevel(),true,false);
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

    /**
     * 单类目查询
     * @param key
     * @return
     */
    @Override
    public Map<String,Object> queryByCategory (String key)throws Exception{
        //Map
        Map<String,Object> map = new HashMap<String, Object>();
//        key = URLDecoder.decode(key,"UTF-8");
        List<CompanyLevel> list = companyLevelDao.queryByCategory(key);
        //存储list
        QueryResult resultAll = null;
        String category = Properties.getValue("language-zh-CN", "t_company_level", "服务商评级");
        if(list!=null&&list.size()>0){
            resultAll = new QueryResult();
            int index =0;
            for(CompanyLevel companyLevel : list){
                QueryResult result = new QueryResult();
                //查询内容
                result.setKey(key);
                //类别
                result.setCategory(category);
                //关键字
                result.setKeyword(companyLevel.getOrgName());
                //信息标签
                List<QueryLabel> queryLabels = new ArrayList<QueryLabel>();
                QueryLabel label = new QueryLabel("公司名",companyLevel.getOrgName(),true,true);
                //公司名
                queryLabels.add(label);
                //营业执照编号
                label = new QueryLabel("营业执照编号",companyLevel.getOrgNo(),true,true);
                queryLabels.add(label);
                //2018年度
                label = new QueryLabel("2018年度",companyLevel.getLevel(),true,true);
                queryLabels.add(label);
                result.setInfoArr(queryLabels);
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
