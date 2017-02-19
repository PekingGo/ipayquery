package com.microfin.logic.entity;

import java.util.List;

/**
 * 系统功能信息
 *
 * @author lipeng 2015/3/25
 */
public class Function extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 494091779212217607L;

    // 功能ID
    private String f_id;
    // 功能编号
    private Integer f_no;
    // 功能名称
    private String f_name;
    // 父功能编号
    private Integer f_super_no;
    // 功能链接URL
    private String f_url;
    // 功能图片URL
    private String f_pic_url;
    // 备注
    private String f_remarks;
    // 子功能
    private List<Function> subFunction;
    // 是否存在子功能
    private boolean hasFunction = false;

    /**
     * @return the f_id
     */
    public String getF_id() {
        return f_id;
    }

    /**
     * @param f_id
     *            the f_id to set
     */
    public void setF_id(String f_id) {
        this.f_id = f_id;
    }

    /**
     * @return the f_no
     */
    public Integer getF_no() {
        return f_no;
    }

    /**
     * @param f_no
     *            the f_no to set
     */
    public void setF_no(Integer f_no) {
        this.f_no = f_no;
    }

    /**
     * @return the f_name
     */
    public String getF_name() {
        return f_name;
    }

    /**
     * @param f_name
     *            the f_name to set
     */
    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    /**
     * @return the f_super_no
     */
    public Integer getF_super_no() {
        return f_super_no;
    }

    /**
     * @param f_super_no
     *            the f_super_no to set
     */
    public void setF_super_no(Integer f_super_no) {
        this.f_super_no = f_super_no;
    }

    /**
     * @return the f_url
     */
    public String getF_url() {
        return f_url;
    }

    /**
     * @param f_url
     *            the f_url to set
     */
    public void setF_url(String f_url) {
        this.f_url = f_url;
    }

    /**
     * @return the f_pic_url
     */
    public String getF_pic_url() {
        return f_pic_url;
    }

    /**
     * @param f_pic_url
     *            the f_pic_url to set
     */
    public void setF_pic_url(String f_pic_url) {
        this.f_pic_url = f_pic_url;
    }

    /**
     * @return the f_remarks
     */
    public String getF_remarks() {
        return f_remarks;
    }

    /**
     * @param f_remarks
     *            the f_remarks to set
     */
    public void setF_remarks(String f_remarks) {
        this.f_remarks = f_remarks;
    }

    /**
     * @return the subFunction
     */
    public List<Function> getSubFunction() {
        return subFunction;
    }

    /**
     * @param subFunction
     *            the subFunction to set
     */
    public void setSubFunction(List<Function> subFunction) {
        this.subFunction = subFunction;
    }

    /**
     * @return the hasFunction
     */
    public boolean isHasFunction() {
        return hasFunction;
    }

    /**
     * @param hasFunction
     *            the hasFunction to set
     */
    public void setHasFunction(boolean hasFunction) {
        this.hasFunction = hasFunction;
    }

}
