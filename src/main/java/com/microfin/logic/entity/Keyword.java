package com.microfin.logic.entity;

import java.io.Serializable;

public class Keyword implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6562915166360355993L;

    private String key_word = "";
    private String p_id;

    /**
     * @return the key_word
     */
    public String getKey_word() {
        return key_word;
    }

    /**
     * @param key_word
     *            the key_word to set
     */
    public void setKey_word(String key_word) {
        this.key_word = key_word;
    }

    /**
     * @return the p_id
     */
    public String getP_id() {
        return p_id;
    }

    /**
     * @param p_id
     *            the p_id to set
     */
    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

}
