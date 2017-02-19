package com.pansoft.logic.entity;

/**
 * 数据权限表
 *
 * @author zhujiarong 2015/4/15
 *
 */
public class UserData extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -5918860218877034464L;

    // 主键
    private String ud_id;

    // 用户账号
    private String u_account;

    // 数据表名
    private String ud_table_name;

    // 数据表字段
    private String ud_table_column;

    // 数据表字段值
    private String ud_table_column_value;

    // 保留字段
    private String ud_retain;

    // 使用状态
    private String ud_status;

    // 备注
    private String ud_remarks;

    /**
     * @return the ud_id
     */
    public String getUd_id() {
        return ud_id;
    }

    /**
     * @param ud_id
     *            the ud_id to set
     */
    public void setUd_id(String ud_id) {
        this.ud_id = ud_id;
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
     * @return the ud_table_name
     */
    public String getUd_table_name() {
        return ud_table_name;
    }

    /**
     * @param ud_table_name
     *            the ud_table_name to set
     */
    public void setUd_table_name(String ud_table_name) {
        this.ud_table_name = ud_table_name;
    }

    /**
     * @return the ud_table_column
     */
    public String getUd_table_column() {
        return ud_table_column;
    }

    /**
     * @param ud_table_column
     *            the ud_table_column to set
     */
    public void setUd_table_column(String ud_table_column) {
        this.ud_table_column = ud_table_column;
    }

    /**
     * @return the ud_table_column_value
     */
    public String getUd_table_column_value() {
        return ud_table_column_value;
    }

    /**
     * @param ud_table_column_value
     *            the ud_table_column_value to set
     */
    public void setUd_table_column_value(String ud_table_column_value) {
        this.ud_table_column_value = ud_table_column_value;
    }

    /**
     * @return the ud_status
     */
    public String getUd_status() {
        return ud_status;
    }

    /**
     * @param ud_status
     *            the ud_status to set
     */
    public void setUd_status(String ud_status) {
        this.ud_status = ud_status;
    }

    /**
     * @return the ud_remarks
     */
    public String getUd_remarks() {
        return ud_remarks;
    }

    /**
     * @param ud_remarks
     *            the ud_remarks to set
     */
    public void setUd_remarks(String ud_remarks) {
        this.ud_remarks = ud_remarks;
    }

    /**
     * @return the ud_retain
     */
    public String getUd_retain() {
        return ud_retain;
    }

    /**
     * @param ud_retain
     *            the ud_retain to set
     */
    public void setUd_retain(String ud_retain) {
        this.ud_retain = ud_retain;
    }

}
