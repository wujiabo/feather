package com.wujiabo.opensource.feather.mapper.custom;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wujiabo.opensource.feather.model.auto.TsysDatas;

public interface TsysDatasDao {
	
	public List<TsysDatas> selectByPrimaryKeys(@Param("ids") List<String> ids);
	
}