package com.microfin.logic.entity;

import java.io.Serializable;
import java.util.List;

public class KeywordTableMap implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3681261979402685494L;
    private String key;
    private List<Keyword> value;

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
     * @return the value
     */
    public List<Keyword> getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(List<Keyword> value) {
        this.value = value;
    }

}