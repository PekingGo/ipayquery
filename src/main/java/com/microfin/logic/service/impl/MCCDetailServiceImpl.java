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

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
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
            Keyword keyword = queryList.get(i);
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
            QueryLabel label = null;
            // MCC码
            if(StringUtil.isNotEmpty(bean.getMccNo())){
                label = new QueryLabel("MCC码",bean.getMccNo(),true,true);
                labelList.add(label);
            }
            // MCC大类
            if(StringUtil.isNotEmpty(bean.getType())){
                label = new QueryLabel("MCC大类",bean.getType(),true,true);
                labelList.add(label);
            }
            // MCC小类
            if(StringUtil.isNotEmpty(bean.getBusType())){
                label = new QueryLabel("MCC小类",bean.getBusType(),true,true);
                labelList.add(label);
            }
            if(StringUtil.isNotEmpty(bean.getTypeDetail())){
                // MCC细类
                label = new QueryLabel("MCC细类",bean.getTypeDetail(),true,true);
                labelList.add(label);
            }
            if(StringUtil.isNotEmpty(bean.getBusDetail())){
                // 经营类别详细描述
                label = new QueryLabel("经营类别详细描述",bean.getBusDetail(),true,true);
                labelList.add(label);
            }
            // 商户类别名
            if(StringUtil.isNotEmpty(bean.getBusNameType())){
                label = new QueryLabel("商户类别名",bean.getBusNameType(),true,true);
                labelList.add(label);
            }
            //银联扣率
            if(StringUtil.isNotEmpty(bean.getPresent())){
                label = new QueryLabel("银联扣率",bean.getPresent(),true,false);
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
    @Override
    public Map<String,Object> queryByCategory(String key) throws Exception{
        //Map
        Map<String,Object> map = new HashMap<String, Object>();
//        key = URLDecoder.decode(key,"UTF-8");
        List<MCCDetail> list = mccDetailDao.queryByCategory(key);
        //存储list
        QueryResult resultAll = null;
        String category = Properties.getValue("language-zh-CN", "mcc_t", "MCC码扣率");
        if(list!=null&&list.size()>0){
            resultAll = new QueryResult();
            int index =0;
            for(MCCDetail bean:list){
                QueryResult result = new QueryResult();
                //查询内容
                result.setKey(key);
                //类别
                result.setCategory(category);
                //关键字
                result.setKeyword(bean.getMccNo());
                //信息标签
                List<QueryLabel> labelList = new ArrayList<QueryLabel>();
                // MCC码
                QueryLabel label = null;
                if(StringUtil.isNotEmpty(bean.getMccNo())){
                    label = new QueryLabel("MCC码",bean.getMccNo(),true,true);
                    labelList.add(label);
                }
                // MCC大类
                if(StringUtil.isNotEmpty(bean.getType())){
                    label = new QueryLabel("MCC大类",bean.getType(),true,true);
                    labelList.add(label);
                }
                // MCC小类
                if(StringUtil.isNotEmpty(bean.getBusType())){
                    label = new QueryLabel("MCC小类",bean.getBusType(),true,true);
                    labelList.add(label);
                }
                if(StringUtil.isNotEmpty(bean.getTypeDetail())){
                    // MCC细类
                    label = new QueryLabel("MCC细类",bean.getTypeDetail(),true,true);
                    labelList.add(label);
                }
                if(StringUtil.isNotEmpty(bean.getBusDetail())){
                    // 经营类别详细描述
                    label = new QueryLabel("经营类别详细描述",bean.getBusDetail(),true,true);
                    labelList.add(label);
                }
                // 商户类别名
                if(StringUtil.isNotEmpty(bean.getBusNameType())){
                    label = new QueryLabel("商户类别名",bean.getBusNameType(),true,true);
                    labelList.add(label);
                }
                //银联扣率
                if(StringUtil.isNotEmpty(bean.getPresent())){
                    label = new QueryLabel("银联扣率",bean.getPresent(),true,false);
                    labelList.add(label);
                }
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
