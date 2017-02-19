package com.microfin.logic.service;

import java.util.List;

import com.microfin.logic.entity.Function;

/**
 * 菜单表操作
 *
 * @author lipeng 2015-03-25
 */
public interface FunctionService {
    /**
     * 取得所有菜单信息一览
     *
     * @return
     */
    List<Function> listAllFunction();

    /**
     * 取得所有一级菜单信息一览
     *
     * @return
     */
    List<Function> listAllParentFunction(String system_id);

    /**
     * 根据父菜单编号取得子菜单信息一览
     *
     * @param function
     * @return
     */
    List<Function> listSubFunctionByParentId(Function function);

    /**
     * 取得所有子菜单信息一览
     *
     * @param system_id
     * @return
     */
    List<Function> listAllSubFunction(String system_id);
}
