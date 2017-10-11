package com.microfin.logic.entity;

import java.io.Serializable;

/**
 * 地区代码bean
 *
 * @author manxiaolei 2016-8-17 21:58:31
 *
 */
public class RegionCode implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1571821662272039180L;

    private String code;

    private String address;

    private String province;

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

}
