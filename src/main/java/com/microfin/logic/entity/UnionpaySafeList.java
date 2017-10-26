package com.microfin.logic.entity;

import java.io.Serializable;

/**
 * 银联白名单信息bean
 *
 * @author manxiaolei 2016-1-24 22:05:32
 *
 */
public class UnionpaySafeList implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5525224016283248765L;

    private String province;

    private String city;

    private String orgName;

    private String busCategory;

    private String address;

    private String manageOrg;

    private String certifyDate;

    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province
     *            the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     *            the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the orgName
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * @param orgName
     *            the orgName to set
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
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
     * @return the manageOrg
     */
    public String getManageOrg() {
        return manageOrg;
    }

    /**
     * @param manageOrg
     *            the manageOrg to set
     */
    public void setManageOrg(String manageOrg) {
        this.manageOrg = manageOrg;
    }

    /**
     * @return the certifyDate
     */
    public String getCertifyDate() {
        return certifyDate;
    }

    /**
     * @param certifyDate
     *            the certifyDate to set
     */
    public void setCertifyDate(String certifyDate) {
        this.certifyDate = certifyDate;
    }

}
