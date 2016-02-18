package com.wujiabo.opensource.feather.dao;

import com.wujiabo.opensource.feather.model.TUserGroupExample;
import com.wujiabo.opensource.feather.model.TUserGroupKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserGroupMapper {
    int countByExample(TUserGroupExample example);

    int deleteByExample(TUserGroupExample example);

    int deleteByPrimaryKey(TUserGroupKey key);

    int insert(TUserGroupKey record);

    int insertSelective(TUserGroupKey record);

    List<TUserGroupKey> selectByExample(TUserGroupExample example);

    int updateByExampleSelective(@Param("record") TUserGroupKey record, @Param("example") TUserGroupExample example);

    int updateByExample(@Param("record") TUserGroupKey record, @Param("example") TUserGroupExample example);
}