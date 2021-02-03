package com.wujiabo.feather.system.standard.domain;

import java.io.Serializable;
import java.util.Date;

public class TSysUser implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_user.user_id
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    private String userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_user.user_name
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    private String userName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_user.password
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_user.del_flag
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    private String delFlag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_user.create_by
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    private String createBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_user.create_time
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_user.update_by
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    private String updateBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_user.update_time
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_user.remark
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_sys_user
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_user.user_id
     *
     * @return the value of t_sys_user.user_id
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_user.user_id
     *
     * @param userId the value for t_sys_user.user_id
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_user.user_name
     *
     * @return the value of t_sys_user.user_name
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_user.user_name
     *
     * @param userName the value for t_sys_user.user_name
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_user.password
     *
     * @return the value of t_sys_user.password
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_user.password
     *
     * @param password the value for t_sys_user.password
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_user.del_flag
     *
     * @return the value of t_sys_user.del_flag
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_user.del_flag
     *
     * @param delFlag the value for t_sys_user.del_flag
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_user.create_by
     *
     * @return the value of t_sys_user.create_by
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_user.create_by
     *
     * @param createBy the value for t_sys_user.create_by
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_user.create_time
     *
     * @return the value of t_sys_user.create_time
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_user.create_time
     *
     * @param createTime the value for t_sys_user.create_time
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_user.update_by
     *
     * @return the value of t_sys_user.update_by
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_user.update_by
     *
     * @param updateBy the value for t_sys_user.update_by
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_user.update_time
     *
     * @return the value of t_sys_user.update_time
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_user.update_time
     *
     * @param updateTime the value for t_sys_user.update_time
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_user.remark
     *
     * @return the value of t_sys_user.remark
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_user.remark
     *
     * @param remark the value for t_sys_user.remark
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", password=").append(password);
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