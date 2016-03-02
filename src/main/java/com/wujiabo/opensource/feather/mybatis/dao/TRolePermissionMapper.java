package com.wujiabo.opensource.feather.mybatis.dao;

import com.wujiabo.opensource.feather.mybatis.model.TRolePermissionExample;
import com.wujiabo.opensource.feather.mybatis.model.TRolePermissionKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRolePermissionMapper {
    int countByExample(TRolePermissionExample example);

    int deleteByExample(TRolePermissionExample example);

    int deleteByPrimaryKey(TRolePermissionKey key);

    int insert(TRolePermissionKey record);

    int insertSelective(TRolePermissionKey record);

    List<TRolePermissionKey> selectByExample(TRolePermissionExample example);

    int updateByExampleSelective(@Param("record") TRolePermissionKey record, @Param("example") TRolePermissionExample example);

    int updateByExample(@Param("record") TRolePermissionKey record, @Param("example") TRolePermissionExample example);
}