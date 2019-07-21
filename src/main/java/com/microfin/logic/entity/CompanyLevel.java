package com.microfin.logic.entity;

import java.io.Serializable;

public class CompanyLevel implements Serializable {
    private static final long serialVersionUID = 7330698852957002017L;
    private long id;
    private String orgName;
    private String orgNo;
    private String level;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
