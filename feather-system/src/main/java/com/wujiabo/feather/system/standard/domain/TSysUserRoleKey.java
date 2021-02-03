package com.wujiabo.feather.system.standard.domain;

import java.io.Serializable;

public class TSysUserRoleKey implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_user_role.user_id
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    private String userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_user_role.role_id
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    private String roleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_sys_user_role
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_user_role.user_id
     *
     * @return the value of t_sys_user_role.user_id
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_user_role.user_id
     *
     * @param userId the value for t_sys_user_role.user_id
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_user_role.role_id
     *
     * @return the value of t_sys_user_role.role_id
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_user_role.role_id
     *
     * @param roleId the value for t_sys_user_role.role_id
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
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
        sb.append(", userId=").append(userId);
        sb.append(", roleId=").append(roleId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}