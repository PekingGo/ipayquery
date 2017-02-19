package com.microfin.logic.entity;

import java.io.Serializable;
import java.util.Date;

import com.microfin.common.util.Const;

/**
 * 基础信息
 *
 * @author lipeng 2015/3/25
 */
public class BaseEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2639912034915151777L;

    // 系统标志
    private String system_id = Const.SYSTEM_DIF_AIGOUMS;

    // 创建者
    private String create_user;

    // 创建时间
    private Date create_time;

    /**
     * @return the system_id
     */
    public String getSystem_id() {
        return system_id;
    }

    /**
     * @param system_id
     *            the system_id to set
     */
    public void setSystem_id(String system_id) {
        this.system_id = system_id;
    }

    /**
     * @return the create_user
     */
    public String getCreate_user() {
        return create_user;
    }

    /**
     * @param create_user
     *            the create_user to set
     */
    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    /**
     * @return the create_time
     */
    public Date getCreate_time() {
        return create_time;
    }

    /**
     * @param create_time
     *            the create_time to set
     */
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

}
