package com.microfin.logic.entity;

/**
 * 用户信息
 *
 * @author lipeng 2015/3/25
 */
public class User extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 4641260768227581179L;

    // 用户ID
    private String u_id;
    // 用户账号
    private String u_account;
    // 密码
    private String u_password;
    // 姓名
    private String u_name;
    // 座机
    private String u_phone;
    // 手机
    private String u_mobile;
    // 邮箱
    private String u_email;
    // 公司编号
    private String u_company;
    // 使用状态
    private String u_status;
    // 备注
    private String u_remarks;
    // 微信号
    private String u_wechat;

    // 标签显示内容
    private String label;

    // 标签选中内容
    private String value;

    /**
     * @return the u_id
     */
    public String getU_id() {
        return u_id;
    }

    /**
     * @param u_id
     *            the u_id to set
     */
    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    /**
     * @return the u_account
     */
    public String getU_account() {
        return u_account;
    }

    /**
     * @param u_account
     *            the u_account to set
     */
    public void setU_account(String u_account) {
        this.u_account = u_account;
    }

    /**
     * @return the u_password
     */
    public String getU_password() {
        return u_password;
    }

    /**
     * @param u_password
     *            the u_password to set
     */
    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    /**
     * @return the u_name
     */
    public String getU_name() {
        return u_name;
    }

    /**
     * @param u_name
     *            the u_name to set
     */
    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    /**
     * @return the u_phone
     */
    public String getU_phone() {
        return u_phone;
    }

    /**
     * @param u_phone
     *            the u_phone to set
     */
    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }

    /**
     * @return the u_mobile
     */
    public String getU_mobile() {
        return u_mobile;
    }

    /**
     * @param u_mobile
     *            the u_mobile to set
     */
    public void setU_mobile(String u_mobile) {
        this.u_mobile = u_mobile;
    }

    /**
     * @return the u_email
     */
    public String getU_email() {
        return u_email;
    }

    /**
     * @param u_email
     *            the u_email to set
     */
    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    /**
     * @return the u_company
     */
    public String getU_company() {
        return u_company;
    }

    /**
     * @param u_company
     *            the u_company to set
     */
    public void setU_company(String u_company) {
        this.u_company = u_company;
    }

    /**
     * @return the u_status
     */
    public String getU_status() {
        return u_status;
    }

    /**
     * @param u_status
     *            the u_status to set
     */
    public void setU_status(String u_status) {
        this.u_status = u_status;
    }

    /**
     * @return the u_remarks
     */
    public String getU_remarks() {
        return u_remarks;
    }

    /**
     * @param u_remarks
     *            the u_remarks to set
     */
    public void setU_remarks(String u_remarks) {
        this.u_remarks = u_remarks;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        StringBuffer sbf = new StringBuffer("");
        sbf.append(u_account);
        sbf.append("--");
        sbf.append(this.u_name);
        this.label = sbf.toString();
        return label;
    }

    /**
     * @param label
     *            the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the value
     */
    public String getValue() {
        value = u_name;
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the u_wechat
     */
    public String getU_wechat() {
        return u_wechat;
    }

    /**
     * @param u_wechat
     *            the u_wechat to set
     */
    public void setU_wechat(String u_wechat) {
        this.u_wechat = u_wechat;
    }

}
