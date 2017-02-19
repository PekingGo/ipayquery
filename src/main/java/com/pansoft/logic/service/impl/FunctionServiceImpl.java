package com.pansoft.logic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pansoft.common.util.Const;
import com.pansoft.logic.dao.FunctionDao;
import com.pansoft.logic.entity.Function;
import com.pansoft.logic.service.FunctionService;

/**
 * 菜单表操作
 *
 * @author lipeng 2015-03-25
 */
@Service
@Transactional(readOnly = true)
public class FunctionServiceImpl implements FunctionService {

    @Autowired
    private FunctionDao functionDao;

    /**
     * 取得所有一级菜单信息一览
     *
     * @return
     */
    /* @Override */
    public List<Function> listAllParentFunction(String system_id) {
        return functionDao.listAllParentFunction(system_id);
    }

    /**
     * 根据父菜单编号取得子菜单信息一览
     *
     * @param function
     * @return
     */
    /* @Override */
    public List<Function> listSubFunctionByParentId(Function function) {
        return functionDao.listSubFunctionByParentId(function);
    }

    /**
     * 取得所有菜单信息一览
     *
     * @return
     */
    /* @Override */
    public List<Function> listAllFunction() {
        List<Function> parentFunctionList = this.listAllParentFunction(Const.SYSTEM_DIF_AIGOUMS);
        for (Function parentFunction : parentFunctionList) {
            List<Function> subFunctionList = this.listSubFunctionByParentId(parentFunction);
            parentFunction.setSubFunction(subFunctionList);
            for (Function subFunction : subFunctionList) {
                List<Function> nextSubFunctionList = this.listSubFunctionByParentId(subFunction);
                subFunction.setSubFunction(nextSubFunctionList);
            }
        }
        return parentFunctionList;
    }

    /**
     * 取得所有子菜单信息一览
     *
     * @param system_id
     * @return
     */
    /* @Override */
    public List<Function> listAllSubFunction(String system_id) {
        return functionDao.listAllSubFunction(system_id);
    }

}
