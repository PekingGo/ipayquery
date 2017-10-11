package com.microfin.logic.entity;

import java.io.Serializable;

/**
 * 收单机构信息bean
 *
 * @author manxiaolei
 *
 */
public class OrderAcceptOrg implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7330698852957002016L;

    private int orgId;

    private String orgNo;

    private String addrNo;

    private String bankName;

    private String orgType;

    /**
     * @return the orgId
     */
    public int getOrgId() {
        return orgId;
    }

    /**
     * @param orgId
     *            the orgId to set
     */
    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    /**
     * @return the orgNo
     */
    public String getOrgNo() {
        return orgNo;
    }

    /**
     * @param orgNo
     *            the orgNo to set
     */
    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    /**
     * @return the addrNo
     */
    public String getAddrNo() {
        return addrNo;
    }

    /**
     * @param addrNo
     *            the addrNo to set
     */
    public void setAddrNo(String addrNo) {
        this.addrNo = addrNo;
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
     * @return the orgType
     */
    public String getOrgType() {
        return orgType;
    }

    /**
     * @param orgType
     *            the orgType to set
     */
    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

}
