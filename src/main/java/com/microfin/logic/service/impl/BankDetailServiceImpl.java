package com.microfin.logic.service.impl;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
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
        String key = resultMap.get("key").toString();
        long cur = System.currentTimeMillis();
        for (int i = 0; i < queryList.size(); i++) {
            Keyword keyword = queryList.get(i);
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
            queryResult.setKeyword(keyword.getKey_word().length()>13?keyword.getKey_word().substring(0,13)+"..":keyword.getKey_word());
            // 所属银行
            QueryLabel label = new QueryLabel("所属银行",bean.getBankName(),true,true);
            labelList.add(label);
            // 银行全称
            if (StringUtil.isNotEmpty(bean.getBankDetail())) {
                label = new QueryLabel("银行全称",bean.getBankDetail(),true,true);
                labelList.add(label);
            }
            label = new QueryLabel("联行号",bean.getBankNo(),true,true);
            labelList.add(label);
            // 地址
            if (StringUtil.isNotEmpty(bean.getAddress())) {
                label = new QueryLabel("地址",bean.getAddress(),true,true);
                labelList.add(label);
            }
            // 联系电话
            if (StringUtil.isNotEmpty(bean.getPhoneNo())) {
                label = new QueryLabel("联系电话",bean.getPhoneNo(),true,false);
                labelList.add(label);
            }
            queryResult.setInfoArr(labelList);
            if (i == 0) {
                list.add(queryResult);
            } else {
                list.get(0).setMoreList(queryResult);
            }
        }
        long cur1 = System.currentTimeMillis();
        System.out.println("开户行联行号查询用时："+(cur1-cur)/1000+"s");
        if (resultMap.containsKey("list")) {
            JSONArray dataArr = JSONArray.fromObject(resultMap.get("list"));
            dataArr.addAll(JSONArray.fromObject(list));
            resultMap.put("list", dataArr);
        } else {
            resultMap.put("list", JSONArray.fromObject(list));
        }
        long cur2 = System.currentTimeMillis();
        System.out.println("开户行联行号json操作用时："+(cur2-cur1)/1000+"s");
    }

    /**
     * 类目查询
     * @param key
     * @return
     */
    @Override
    public Map<String,Object> queryByCategory(String key) throws Exception{
        //map
        Map<String,Object> map = new HashMap<String, Object>();
//        key = URLDecoder.decode(key,"UTF-8");
        List<BankDetail> list = bankDetailDao.queryByCategory(key);
        //存储list
        QueryResult resultAll = null;
        String category = Properties.getValue("language-zh-CN", "t_bank_detail", "开户联行号");
        if(list!=null&&list.size()>0){
            resultAll = new QueryResult();
            int index =0;
            for(BankDetail bean:list){
                QueryResult result = new QueryResult();
                //查询内容
                result.setKey(key);
                //类别
                result.setCategory(category);
                //关键字
                result.setKeyword(bean.getBankDetail());
                //信息标签
                List<QueryLabel> labelList = new ArrayList<QueryLabel>();
                QueryLabel label = null;
                if(StringUtil.isEmpty(bean.getBankName())){
                    // 所属银行
                    label = new QueryLabel("所属银行",bean.getBankName(),true,true);
                    labelList.add(label);
                }
                // 银行全称
                if (StringUtil.isNotEmpty(bean.getBankDetail())) {
                    label = new QueryLabel("银行全称",bean.getBankDetail(),true,true);
                    labelList.add(label);
                }
                label = new QueryLabel("联行号",bean.getBankNo(),true,true);
                labelList.add(label);
                // 地址
                if (StringUtil.isNotEmpty(bean.getAddress())) {
                    label = new QueryLabel("地址",bean.getAddress(),true,true);
                    labelList.add(label);
                }
                // 联系电话
                if (StringUtil.isNotEmpty(bean.getPhoneNo())) {
                    label = new QueryLabel("联系电话",bean.getPhoneNo(),true,false);
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
