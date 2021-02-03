package com.wujiabo.feather.system.standard.domain;

import java.io.Serializable;
import java.util.Date;

public class TSysUserRole extends TSysUserRoleKey implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_user_role.del_flag
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    private String delFlag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_user_role.create_by
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    private String createBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_user_role.create_time
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_user_role.update_by
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    private String updateBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_user_role.update_time
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_user_role.remark
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_sys_user_role
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_user_role.del_flag
     *
     * @return the value of t_sys_user_role.del_flag
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_user_role.del_flag
     *
     * @param delFlag the value for t_sys_user_role.del_flag
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_user_role.create_by
     *
     * @return the value of t_sys_user_role.create_by
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_user_role.create_by
     *
     * @param createBy the value for t_sys_user_role.create_by
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_user_role.create_time
     *
     * @return the value of t_sys_user_role.create_time
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_user_role.create_time
     *
     * @param createTime the value for t_sys_user_role.create_time
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_user_role.update_by
     *
     * @return the value of t_sys_user_role.update_by
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_user_role.update_by
     *
     * @param updateBy the value for t_sys_user_role.update_by
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_user_role.update_time
     *
     * @return the value of t_sys_user_role.update_time
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_user_role.update_time
     *
     * @param updateTime the value for t_sys_user_role.update_time
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_user_role.remark
     *
     * @return the value of t_sys_user_role.remark
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_user_role.remark
     *
     * @param remark the value for t_sys_user_role.remark
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user_role
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", delFlag=").append(delFlag);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}