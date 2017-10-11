package com.microfin.logic.entity;

import java.io.Serializable;

/**
 * 银行卡信息bean
 *
 * @author manxiaolei 2016-1-23 15:50:28
 *
 */
public class MCCDetail implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8646482516426049548L;

    private String mccNo;

    private String busNameType;

    private String busCategory;

    private String busType;

    private String busDetail;

    private String present;

    private String type;

    private String typeDetail;

    /**
     * @return the mccNo
     */
    public String getMccNo() {
        return mccNo;
    }

    /**
     * @param mccNo
     *            the mccNo to set
     */
    public void setMccNo(String mccNo) {
        this.mccNo = mccNo;
    }

    /**
     * @return the busNameType
     */
    public String getBusNameType() {
        return busNameType;
    }

    /**
     * @param busNameType
     *            the busNameType to set
     */
    public void setBusNameType(String busNameType) {
        this.busNameType = busNameType;
    }

    /**
     * @return the busCategory
     */
    public String getBusCategory() {
        return busCategory;
    }

    /**
     * @param busCategory
     *            the busCategory to set
     */
    public void setBusCategory(String busCategory) {
        this.busCategory = busCategory;
    }

    /**
     * @return the busType
     */
    public String getBusType() {
        return busType;
    }

    /**
     * @param busType
     *            the busType to set
     */
    public void setBusType(String busType) {
        this.busType = busType;
    }

    /**
     * @return the busDetail
     */
    public String getBusDetail() {
        return busDetail;
    }

    /**
     * @param busDetail
     *            the busDetail to set
     */
    public void setBusDetail(String busDetail) {
        this.busDetail = busDetail;
    }

    /**
     * @return the present
     */
    public String getPresent() {
        return present;
    }

    /**
     * @param present
     *            the present to set
     */
    public void setPresent(String present) {
        this.present = present;
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
     * @return the typeDetail
     */
    public String getTypeDetail() {
        return typeDetail;
    }

    /**
     * @param typeDetail
     *            the typeDetail to set
     */
    public void setTypeDetail(String typeDetail) {
        this.typeDetail = typeDetail;
    }

}
