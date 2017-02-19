package com.pansoft.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Ajax请求响应类
 * @author guanxp
 * @param <T>
 * 2015/02/28
 */
public class AjaxResponse<T extends Serializable> implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5905364400369736280L;

    // List类型数据
    private List<T> dataList;
    
    // Map类型数据
    private Map<String, T> dataMap;
    
    // 返回消息
    private String message;
    
    // 是否成功
    private boolean success;

    
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public Map<String, T> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, T> dataMap) {
        this.dataMap = dataMap;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
