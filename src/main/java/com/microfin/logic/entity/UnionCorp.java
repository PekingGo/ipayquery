package com.microfin.logic.entity;

import java.io.Serializable;

/**
 * 银联认证服务机构bean
 *
 * @author manxiaolei 2016-8-17 22:01:37
 *
 */
public class UnionCorp implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8651437534408424108L;

    private String id;

    private String corp_name;

    private String funds;

    private String legal;

    private String address;

    private String contacts_person;

    private String contacts;

    private String service_type;

    private String service_range;

    private String reg_date;

    private String site;

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
     * @return the corp_name
     */
    public String getCorp_name() {
        return corp_name;
    }

    /**
     * @param corp_name
     *            the corp_name to set
     */
    public void setCorp_name(String corp_name) {
        this.corp_name = corp_name;
    }

    /**
     * @return the funds
     */
    public String getFunds() {
        return funds;
    }

    /**
     * @param funds
     *            the funds to set
     */
    public void setFunds(String funds) {
        this.funds = funds;
    }

    /**
     * @return the legal
     */
    public String getLegal() {
        return legal;
    }

    /**
     * @param legal
     *            the legal to set
     */
    public void setLegal(String legal) {
        this.legal = legal;
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
     * @return the contacts_person
     */
    public String getContacts_person() {
        return contacts_person;
    }

    /**
     * @param contacts_person
     *            the contacts_person to set
     */
    public void setContacts_person(String contacts_person) {
        this.contacts_person = contacts_person;
    }

    /**
     * @return the contacts
     */
    public String getContacts() {
        return contacts;
    }

    /**
     * @param contacts
     *            the contacts to set
     */
    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    /**
     * @return the service_type
     */
    public String getService_type() {
        return service_type;
    }

    /**
     * @param service_type
     *            the service_type to set
     */
    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    /**
     * @return the service_range
     */
    public String getService_range() {
        return service_range;
    }

    /**
     * @param service_range
     *            the service_range to set
     */
    public void setService_range(String service_range) {
        this.service_range = service_range;
    }

    /**
     * @return the reg_date
     */
    public String getReg_date() {
        return reg_date;
    }

    /**
     * @param reg_date
     *            the reg_date to set
     */
    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    /**
     * @return the site
     */
    public String getSite() {
        return site;
    }

    /**
     * @param site
     *            the site to set
     */
    public void setSite(String site) {
        this.site = site;
    }

}
