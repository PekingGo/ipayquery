package com.microfin.logic.entity;

/**
 * 用户角色信息
 *
 * @author lipeng 2015/3/25
 */
public class UserRole extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 346191927607065836L;

    // 用户角色ID
    private String ur_id;
    // 用户账号
    private String u_account;
    // 角色ID
    private String r_id;
    // 角色名称
    private String r_name;
    // 项目编号
    private String project_no;
    // 项目名称
    private String pi_name;
    // 角色信息
    private Role role;

    /**
     * @return the ur_id
     */
    public String getUr_id() {
        return ur_id;
    }

    /**
     * @param ur_id
     *            the ur_id to set
     */
    public void setUr_id(String ur_id) {
        this.ur_id = ur_id;
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
     * @return the r_id
     */
    public String getR_id() {
        return r_id;
    }

    /**
     * @param r_id
     *            the r_id to set
     */
    public void setR_id(String r_id) {
        this.r_id = r_id;
    }

    /**
     * @return the r_name
     */
    public String getR_name() {
        return r_name;
    }

    /**
     * @param r_name
     *            the r_name to set
     */
    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    /**
     * @return the project_no
     */
    public String getProject_no() {
        return project_no;
    }

    /**
     * @param project_no
     *            the project_no to set
     */
    public void setProject_no(String project_no) {
        this.project_no = project_no;
    }

    /**
     * @return the pi_name
     */
    public String getPi_name() {
        return pi_name;
    }

    /**
     * @param pi_name
     *            the pi_name to set
     */
    public void setPi_name(String pi_name) {
        this.pi_name = pi_name;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role
     *            the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }

}
