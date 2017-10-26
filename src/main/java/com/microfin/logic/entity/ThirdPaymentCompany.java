package com.microfin.logic.entity;

import java.io.Serializable;

/**
 * 第三方支付公司bean
 *
 * @author manxiaolei 2016-1-23 21:35:01
 *
 */
public class ThirdPaymentCompany implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8043464983190491461L;

    private String cid;

    private String licenseNo;

    private String companyName;

    private String legalPerson;

    private String companyAddress;

    private String province;

    private String serviceType;

    private String cardOrder;

    private String serviceRange;

    private String issuingDate;

    private String endDate;

    private String website;

    private String lot;

    private String noticesite;

    private String renewalInfo;

    /**
     * @return the licenseNo
     */
    public String getLicenseNo() {
        return licenseNo;
    }

    /**
     * @param licenseNo
     *            the licenseNo to set
     */
    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName
     *            the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the legalPerson
     */
    public String getLegalPerson() {
        return legalPerson;
    }

    /**
     * @param legalPerson
     *            the legalPerson to set
     */
    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    /**
     * @return the companyAddress
     */
    public String getCompanyAddress() {
        return companyAddress;
    }

    /**
     * @param companyAddress
     *            the companyAddress to set
     */
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

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
     * @return the serviceType
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * @param serviceType
     *            the serviceType to set
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * @return the serviceRange
     */
    public String getServiceRange() {
        return serviceRange;
    }

    /**
     * @param serviceRange
     *            the serviceRange to set
     */
    public void setServiceRange(String serviceRange) {
        this.serviceRange = serviceRange;
    }

    /**
     * @return the issuingDate
     */
    public String getIssuingDate() {
        return issuingDate;
    }

    /**
     * @param issuingDate
     *            the issuingDate to set
     */
    public void setIssuingDate(String issuingDate) {
        this.issuingDate = issuingDate;
    }

    /**
     * @return the website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * @param website
     *            the website to set
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * @return the cid
     */
    public String getCid() {
        return cid;
    }

    /**
     * @param cid
     *            the cid to set
     */
    public void setCid(String cid) {
        this.cid = cid;
    }

    /**
     * @return the cardOrder
     */
    public String getCardOrder() {
        return cardOrder;
    }

    /**
     * @param cardOrder
     *            the cardOrder to set
     */
    public void setCardOrder(String cardOrder) {
        this.cardOrder = cardOrder;
    }

    /**
     * @return the lot
     */
    public String getLot() {
        return lot;
    }

    /**
     * @param lot
     *            the lot to set
     */
    public void setLot(String lot) {
        this.lot = lot;
    }

    /**
     * @return the noticesite
     */
    public String getNoticesite() {
        return noticesite;
    }

    /**
     * @param noticesite
     *            the noticesite to set
     */
    public void setNoticesite(String noticesite) {
        this.noticesite = noticesite;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRenewalInfo() {
        return renewalInfo;
    }

    public void setRenewalInfo(String renewalInfo) {
        this.renewalInfo = renewalInfo;
    }
}
