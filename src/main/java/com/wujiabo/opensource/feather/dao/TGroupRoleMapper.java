package com.wujiabo.opensource.feather.dao;

import com.wujiabo.opensource.feather.model.TGroupRoleExample;
import com.wujiabo.opensource.feather.model.TGroupRoleKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TGroupRoleMapper {
    int countByExample(TGroupRoleExample example);

    int deleteByExample(TGroupRoleExample example);

    int deleteByPrimaryKey(TGroupRoleKey key);

    int insert(TGroupRoleKey record);

    int insertSelective(TGroupRoleKey record);

    List<TGroupRoleKey> selectByExample(TGroupRoleExample example);

    int updateByExampleSelective(@Param("record") TGroupRoleKey record, @Param("example") TGroupRoleExample example);

    int updateByExample(@Param("record") TGroupRoleKey record, @Param("example") TGroupRoleExample example);
}