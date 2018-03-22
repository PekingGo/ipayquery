package com.microfin.logic.dao;


import com.microfin.logic.entity.CmbcNoScore;

import java.util.List;

public interface CmbcNoScoreDao {

    List<CmbcNoScore> queryByCategory(String key);

    int queryDataNum(CmbcNoScore cmbcNoScore);

}
