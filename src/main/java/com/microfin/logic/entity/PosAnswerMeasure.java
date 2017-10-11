package com.microfin.logic.entity;

import java.io.Serializable;

/**
 * pos应答码bean
 *
 * @author manxiaolei 2016-1-23 20:41:38
 *
 */
public class PosAnswerMeasure implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2612155294398242456L;

    private String code;

    private String meaning;

    private String type;

    private String displaycontent;

    private String measure;

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the meaning
     */
    public String getMeaning() {
        return meaning;
    }

    /**
     * @param meaning
     *            the meaning to set
     */
    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the displaycontent
     */
    public String getDisplaycontent() {
        return displaycontent;
    }

    /**
     * @param displaycontent
     *            the displaycontent to set
     */
    public void setDisplaycontent(String displaycontent) {
        this.displaycontent = displaycontent;
    }

    /**
     * @return the measure
     */
    public String getMeasure() {
        return measure;
    }

    /**
     * @param measure
     *            the measure to set
     */
    public void setMeasure(String measure) {
        this.measure = measure;
    }

}
