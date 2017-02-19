package com.pansoft.logic.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pansoft.logic.entity.Function;

/**
 * 功能表操作
 *
 * @author lipeng 2015-03-25
 */
@Repository
public interface FunctionDao {
    List<Function> listAllParentFunction(String system_id);

    List<Function> listSubFunctionByParentId(Function function);

    List<Function> listAllSubFunction(String system_id);
}
