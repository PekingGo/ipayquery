package com.microfin.logic.entity;

import java.util.ArrayList;
import java.util.List;

public class QueryResult {
    // 与关键字匹配的结果
    private String keyword;
    // 标签数组
    private List<QueryLabel> infoArr;
    // 所属类别
    private String category;
    // 查询关键字
    private String key;
    // 更多结果
    private final List<QueryResult> moreList = new ArrayList<QueryResult>();

    /**
     * @return the keyword
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * @param keyword
     *            the keyword to set
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * @return the infoArr
     */
    public List<QueryLabel> getInfoArr() {
        return infoArr;
    }

    /**
     * @param infoArr
     *            the infoArr to set
     */
    public void setInfoArr(List<QueryLabel> infoArr) {
        this.infoArr = infoArr;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category
     *            the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key
     *            the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the moreList
     */
    public List<QueryResult> getMoreList() {
        return moreList;
    }

    /**
     *
     * @param result
     */
    public void setMoreList(QueryResult result) {
        this.moreList.add(result);
    }

}
