package com.microfin.logic.entity;

import java.io.Serializable;

/**
 * 银行信息bean
 *
 * @author manxiaolei 2016-4-10 21:39:42
 *
 */
public class BankDetail implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2529260592066929392L;

    private String rec_id;

    private String bankDetail;

    private String bankNo;

    private String address;

    private String phoneNo;

    private String bankName;

    private String bankType;

    private String[] bankDetailArr;

    /**
     * @return the rec_id
     */
    public String getRec_id() {
        return rec_id;
    }

    /**
     * @param rec_id
     *            the rec_id to set
     */
    public void setRec_id(String rec_id) {
        this.rec_id = rec_id;
    }

    /**
     * @return the bankDetail
     */
    public String getBankDetail() {
        return bankDetail;
    }

    /**
     * @param bankDetail
     *            the bankDetail to set
     */
    public void setBankDetail(String bankDetail) {
        this.bankDetail = bankDetail;
    }

    /**
     * @return the bankNo
     */
    public String getBankNo() {
        return bankNo;
    }

    /**
     * @param bankNo
     *            the bankNo to set
     */
    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     *            the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phoneNo
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * @param phoneNo
     *            the phoneNo to set
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * @return the bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName
     *            the bankName to set
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * @return the bankType
     */
    public String getBankType() {
        return bankType;
    }

    /**
     * @param bankType
     *            the bankType to set
     */
    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    /**
     * @return the bankDetailArr
     */
    public String[] getBankDetailArr() {
        return bankDetailArr;
    }

    /**
     * @param bankDetailArr
     *            the bankDetailArr to set
     */
    public void setBankDetailArr(String[] bankDetailArr) {
        this.bankDetailArr = bankDetailArr;
    }

}
