package com.wujiabo.feather.system.standard.mapper;

import com.wujiabo.feather.system.standard.domain.TSysPerms;
import com.wujiabo.feather.system.standard.domain.TSysPermsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSysPermsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_perms
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    long countByExample(TSysPermsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_perms
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    int deleteByExample(TSysPermsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_perms
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    int deleteByPrimaryKey(String permsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_perms
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    int insert(TSysPerms record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_perms
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    int insertSelective(TSysPerms record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_perms
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    List<TSysPerms> selectByExample(TSysPermsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_perms
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    TSysPerms selectByPrimaryKey(String permsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_perms
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    int updateByExampleSelective(@Param("record") TSysPerms record, @Param("example") TSysPermsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_perms
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    int updateByExample(@Param("record") TSysPerms record, @Param("example") TSysPermsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_perms
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    int updateByPrimaryKeySelective(TSysPerms record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_perms
     *
     * @mbg.generated Tue Feb 02 16:08:52 CST 2021
     */
    int updateByPrimaryKey(TSysPerms record);
}