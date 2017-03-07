package com.microfin.logic.entity;

import java.io.Serializable;

/**
 * 银行卡信息bean
 *
 * @author manxiaolei 2016-1-23 15:50:28
 *
 */
public class BankCardDetail implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3023393711646907837L;
    /* id */
    private String id;
    /* 所属银行 */
    private String orgName;

    private String cardName;

    private String atm;

    private String pos;

    private String accountLength;

    private String mainAccount;

    private String mainValue;

    private String cardType;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
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
     * @return the cardName
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * @param cardName
     *            the cardName to set
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    /**
     * @return the atm
     */
    public String getAtm() {
        return atm;
    }

    /**
     * @param atm
     *            the atm to set
     */
    public void setAtm(String atm) {
        this.atm = atm;
    }

    /**
     * @return the pos
     */
    public String getPos() {
        return pos;
    }

    /**
     * @param pos
     *            the pos to set
     */
    public void setPos(String pos) {
        this.pos = pos;
    }

    /**
     * @return the accountLength
     */
    public String getAccountLength() {
        return accountLength;
    }

    /**
     * @param accountLength
     *            the accountLength to set
     */
    public void setAccountLength(String accountLength) {
        this.accountLength = accountLength;
    }

    /**
     * @return the mainAccount
     */
    public String getMainAccount() {
        return mainAccount;
    }

    /**
     * @param mainAccount
     *            the mainAccount to set
     */
    public void setMainAccount(String mainAccount) {
        this.mainAccount = mainAccount;
    }

    /**
     * @return the mainValue
     */
    public String getMainValue() {
        return mainValue;
    }

    /**
     * @param mainValue
     *            the mainValue to set
     */
    public void setMainValue(String mainValue) {
        this.mainValue = mainValue;
    }

    /**
     * @return the cardType
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * @param cardType
     *            the cardType to set
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

}
