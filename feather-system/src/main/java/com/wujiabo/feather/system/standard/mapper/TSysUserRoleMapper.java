package com.wujiabo.feather.system.standard.mapper;

import com.wujiabo.feather.system.standard.domain.TSysUserRole;
import com.wujiabo.feather.system.standard.domain.TSysUserRoleExample;
import com.wujiabo.feather.system.standard.domain.TSysUserRoleKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSysUserRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user_role
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    long countByExample(TSysUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user_role
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    int deleteByExample(TSysUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user_role
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    int deleteByPrimaryKey(TSysUserRoleKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user_role
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    int insert(TSysUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user_role
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    int insertSelective(TSysUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user_role
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    List<TSysUserRole> selectByExample(TSysUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user_role
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    TSysUserRole selectByPrimaryKey(TSysUserRoleKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user_role
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    int updateByExampleSelective(@Param("record") TSysUserRole record, @Param("example") TSysUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user_role
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    int updateByExample(@Param("record") TSysUserRole record, @Param("example") TSysUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user_role
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    int updateByPrimaryKeySelective(TSysUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user_role
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    int updateByPrimaryKey(TSysUserRole record);
}