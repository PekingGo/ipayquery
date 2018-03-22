package com.microfin.logic.service.impl;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.util.DecodeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microfin.common.util.Properties;
import com.microfin.logic.dao.BankCardDetailDao;
import com.microfin.logic.entity.BankCardDetail;
import com.microfin.logic.entity.Keyword;
import com.microfin.logic.entity.QueryLabel;
import com.microfin.logic.entity.QueryResult;
import com.microfin.logic.service.WatchService;

import javax.management.Query;

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
        List<BankCardDetail> list = bankCardDetailDao.queryByCategory(key);
        //存储list
        QueryResult resultAll = null;
        String category = Properties.getValue("language-zh-CN", "t_bank_card_detail", "银行卡BIN");
        if(list!=null&&list.size()>0){
            resultAll = new QueryResult();
            int index =0;
            for(BankCardDetail bankCardDetail : list){
                QueryResult result = new QueryResult();
                //查询内容
                result.setKey(key);
                //类别
                result.setCategory(category);
                //关键字
                result.setKeyword(bankCardDetail.getMainValue());
                //信息标签
                List<QueryLabel> queryLabels = new ArrayList<QueryLabel>();
                QueryLabel label = new QueryLabel("发卡行",bankCardDetail.getOrgName(),true,true);
                //发卡行
                queryLabels.add(label);
                //银行卡名称
                label = new QueryLabel("银行卡名",bankCardDetail.getCardName(),true,true);
                queryLabels.add(label);
                //银行卡长度
                label = new QueryLabel("卡号长度",bankCardDetail.getAccountLength(),true,true);
                queryLabels.add(label);
                //银行卡前6位
                label = new QueryLabel("银行卡前6位",bankCardDetail.getMainValue(),true,true);
                queryLabels.add(label);
                //银行卡类型
                label = new QueryLabel("卡种",bankCardDetail.getCardType(),true,true);
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
