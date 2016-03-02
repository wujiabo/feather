package com.wujiabo.opensource.feather.mybatis.dao;

import com.wujiabo.opensource.feather.mybatis.model.TGroup;
import com.wujiabo.opensource.feather.mybatis.model.TGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TGroupMapper {
    int countByExample(TGroupExample example);

    int deleteByExample(TGroupExample example);

    int deleteByPrimaryKey(Integer groupId);

    int insert(TGroup record);

    int insertSelective(TGroup record);

    List<TGroup> selectByExample(TGroupExample example);

    TGroup selectByPrimaryKey(Integer groupId);

    int updateByExampleSelective(@Param("record") TGroup record, @Param("example") TGroupExample example);

    int updateByExample(@Param("record") TGroup record, @Param("example") TGroupExample example);

    int updateByPrimaryKeySelective(TGroup record);

    int updateByPrimaryKey(TGroup record);
}