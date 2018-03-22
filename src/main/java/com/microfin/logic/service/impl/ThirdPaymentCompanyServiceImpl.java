package com.microfin.logic.service.impl;

import com.microfin.common.util.Properties;
import com.microfin.logic.dao.BlackListDao;
import com.microfin.logic.dao.ThirdPaymentCompanyDao;
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

@Service("t_third_payment_company")
@Transactional(readOnly = true)
public class ThirdPaymentCompanyServiceImpl implements WatchService {
    @Autowired
    private ThirdPaymentCompanyDao thirdPaymentCompanyDao;

    @Override
    public void queryForData(Map<String, List<Keyword>> queryMap, Map<String, Object> resultMap) {
        List<QueryResult> list = new ArrayList<QueryResult>();
        List<Keyword> queryList = queryMap.get("t_third_payment_company");
        for (int i = 0; i < queryList.size(); i++) {
            Keyword keyword = queryList.get(i);
            QueryResult queryResult = new QueryResult();
            // 输入的查询内容
            queryResult.setKey(resultMap.get("key").toString());
            // 类别
            String category = Properties.getValue("language-zh-CN", "t_third_payment_company", "");
            queryResult.setCategory(category);
            // 信息标签
            List<QueryLabel> labelList = new ArrayList<QueryLabel>();
            ThirdPaymentCompany bean = thirdPaymentCompanyDao.query(keyword.getP_id());
            queryResult.setKeyword(keyword.getKey_word().length()>11?keyword.getKey_word().substring(0,11)+"..":keyword.getKey_word());
            QueryLabel label = new QueryLabel("批次",bean.getLot(),true,true);
            // 批次
            labelList.add(label);
            // 许可证编号
            label = new QueryLabel("许可证编号",bean.getLicenseNo(),true,true);
            labelList.add(label);
            // 公司名称
            label = new QueryLabel("公司名称",bean.getCompanyName(),true,true);
            labelList.add(label);
            // 法人
            label = new QueryLabel("法人",bean.getLegalPerson(),true,true);
            labelList.add(label);
            // 业务类型
            label = new QueryLabel("业务类型",bean.getServiceType(),true,false);
            labelList.add(label);
            //有效期
            // 颁发日期-到期日期
            label = new QueryLabel("有效期",bean.getIssuingDate()+"-"+bean.getEndDate(),true,true);
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
        List<ThirdPaymentCompany> list = thirdPaymentCompanyDao.queryByCategory(key);
        //存储list
        QueryResult resultAll = null;
        String category = Properties.getValue("language-zh-CN", "t_third_payment_company", "第三方支付公司");
        int index = 0;
        if(list!=null&&list.size()>0){
            resultAll = new QueryResult();
            for(ThirdPaymentCompany bean : list){
                QueryResult result = new QueryResult();
                //类目
                result.setCategory(category);
                //查询关键字
                result.setKey(key);
                //关键字
                result.setKeyword(bean.getCompanyName());
                //信息标签
                List<QueryLabel> labelList = new ArrayList<QueryLabel>();
                QueryLabel label = new QueryLabel("批次",bean.getLot(),true,true);
                // 批次
                labelList.add(label);
                // 许可证编号
                label = new QueryLabel("许可证编号",bean.getLicenseNo(),true,true);
                labelList.add(label);
                // 公司名称
                label = new QueryLabel("公司名称",bean.getCompanyName(),true,true);
                labelList.add(label);
                // 法人
                label = new QueryLabel("法人",bean.getLegalPerson(),true,true);
                labelList.add(label);
                // 业务类型
                label = new QueryLabel("业务类型",bean.getServiceType(),true,false);
                labelList.add(label);
                //有效期
                // 颁发日期-到期日期
                label = new QueryLabel("有效期",bean.getIssuingDate()+"至"+bean.getEndDate(),true,true);
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
        return map;
    }
}
