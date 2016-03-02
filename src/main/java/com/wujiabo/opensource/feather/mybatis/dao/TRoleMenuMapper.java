package com.wujiabo.opensource.feather.mybatis.dao;

import com.wujiabo.opensource.feather.mybatis.model.TRoleMenuExample;
import com.wujiabo.opensource.feather.mybatis.model.TRoleMenuKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRoleMenuMapper {
    int countByExample(TRoleMenuExample example);

    int deleteByExample(TRoleMenuExample example);

    int deleteByPrimaryKey(TRoleMenuKey key);

    int insert(TRoleMenuKey record);

    int insertSelective(TRoleMenuKey record);

    List<TRoleMenuKey> selectByExample(TRoleMenuExample example);

    int updateByExampleSelective(@Param("record") TRoleMenuKey record, @Param("example") TRoleMenuExample example);

    int updateByExample(@Param("record") TRoleMenuKey record, @Param("example") TRoleMenuExample example);
}