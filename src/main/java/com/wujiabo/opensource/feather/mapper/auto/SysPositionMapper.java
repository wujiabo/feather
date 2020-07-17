package com.wujiabo.opensource.feather.mapper.auto;

import com.wujiabo.opensource.feather.model.auto.SysPosition;
import com.wujiabo.opensource.feather.model.auto.SysPositionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 岗位表 SysPositionMapper
 * @author fuce_自动生成
 * @email 115889198@qq.com
 * @date 2020-04-16 23:46:24
 */
public interface SysPositionMapper {
      	   	      	      	      	      
    long countByExample(SysPositionExample example);

    int deleteByExample(SysPositionExample example);
		
    int deleteByPrimaryKey(String id);
		
    int insert(SysPosition record);

    int insertSelective(SysPosition record);

    List<SysPosition> selectByExample(SysPositionExample example);
		
    SysPosition selectByPrimaryKey(String id);
		
    int updateByExampleSelective(@Param("record") SysPosition record, @Param("example") SysPositionExample example);

    int updateByExample(@Param("record") SysPosition record, @Param("example") SysPositionExample example); 
		
    int updateByPrimaryKeySelective(SysPosition record);

    int updateByPrimaryKey(SysPosition record);
  	  	
}