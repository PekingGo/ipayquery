package com.pansoft.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 返回JQGrid数据序列化
 * @author guanxp
 * @param <T>
 * 2015-02-21
 */
public class JQGridResponse<T extends Serializable> implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3521778098473963085L;

    // 需要显示的数据集  
    private List<T> rows;  
  
    // 每页显示数量  
    private int page;  
      
    // 数据总数  
    private int records;  
      
    // 可显示的页数  
    private int total;  
      
    // 自定义数据  
    private Map<String, Object> userdata;
    

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Map<String, Object> getUserdata() {
        return userdata;
    }

    public void setUserdata(Map<String, Object> userdata) {
        this.userdata = userdata;
    }
}
