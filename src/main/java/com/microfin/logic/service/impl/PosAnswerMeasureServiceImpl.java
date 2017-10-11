package com.microfin.logic.service.impl;

import com.microfin.common.util.Properties;
import com.microfin.common.util.StringUtil;
import com.microfin.logic.dao.BlackListDao;
import com.microfin.logic.dao.PosAnswerMeasureDao;
import com.microfin.logic.entity.*;
import com.microfin.logic.service.WatchService;
import javafx.geometry.Pos;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("t_pos_answer_measure")
@Transactional(readOnly = true)
public class PosAnswerMeasureServiceImpl implements WatchService {
    @Autowired
    private PosAnswerMeasureDao posAnswerMeasureDao;

    @Override
    public void queryForData(Map<String, List<Keyword>> queryMap, Map<String, Object> resultMap) {
        List<QueryResult> list = new ArrayList<QueryResult>();
        List<Keyword> queryList = queryMap.get("t_pos_answer_measure");
        for (int i = 0; i < queryList.size(); i++) {
            Keyword keyword = queryList.get(0);
            QueryResult queryResult = new QueryResult();
            // 输入的查询内容
            queryResult.setKey(resultMap.get("key").toString());
            // 类别
            String category = Properties.getValue("language-zh-CN", "t_pos_answer_measure", "");
            queryResult.setCategory(category);
            // 信息标签
            List<QueryLabel> labelList = new ArrayList<QueryLabel>();
            PosAnswerMeasure bean = posAnswerMeasureDao.query(keyword.getP_id());
            queryResult.setKeyword(keyword.getKey_word());
            QueryLabel label = new QueryLabel();
            // 应答码
            label.setText(bean.getCode());
            label.setSeen(true);
            labelList.add(label);
            // 应答码含义
            label = new QueryLabel();
            label.setText(bean.getMeaning());
            label.setSeen(true);
            labelList.add(label);
            // 应答码类别
            label = new QueryLabel();
            label.setText(bean.getType());
            label.setSeen(true);
            labelList.add(label);
            //终端显示的内容
            if(StringUtil.isNotEmpty(bean.getDisplaycontent())){
                label = new QueryLabel();
                label.setText(bean.getDisplaycontent());
                label.setSeen(true);
                labelList.add(label);
            }
            if(StringUtil.isNotEmpty(bean.getMeasure())){
                label = new QueryLabel();
                label.setText(bean.getMeasure());
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
