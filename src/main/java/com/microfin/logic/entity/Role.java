package com.microfin.logic.entity;

/**
 * 角色信息
 *
 * @author lipeng 2015/3/25
 */
public class Role extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1944909395286057044L;

    // 角色ID
    private String r_id;
    // 角色编号
    private String r_no;
    // 角色名称
    private String r_name;
    // 角色功能
    private String r_function;
    // 备注
    private String r_remarks;
    // 功能编号一览
    private String functionIds;
    // 标签显示内容
    private String label;

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
     * @return the r_no
     */
    public String getR_no() {
        return r_no;
    }

    /**
     * @param r_no
     *            the r_no to set
     */
    public void setR_no(String r_no) {
        this.r_no = r_no;
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
     * @return the r_function
     */
    public String getR_function() {
        return r_function;
    }

    /**
     * @param r_function
     *            the r_function to set
     */
    public void setR_function(String r_function) {
        this.r_function = r_function;
    }

    /**
     * @return the r_remarks
     */
    public String getR_remarks() {
        return r_remarks;
    }

    /**
     * @param r_remarks
     *            the r_remarks to set
     */
    public void setR_remarks(String r_remarks) {
        this.r_remarks = r_remarks;
    }

    /**
     * @return the functionIds
     */
    public String getFunctionIds() {
        return functionIds;
    }

    /**
     * @param functionIds
     *            the functionIds to set
     */
    public void setFunctionIds(String functionIds) {
        this.functionIds = functionIds;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        this.label = this.r_name;
        return label;
    }

    /**
     * @param label
     *            the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }
}
