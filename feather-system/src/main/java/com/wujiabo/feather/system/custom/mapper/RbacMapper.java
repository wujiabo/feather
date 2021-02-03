package com.wujiabo.feather.system.custom.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RbacMapper {
    List<String> findRoleKeysByUserId(@Param("userId") String userId);

    List<String> findPermKeysByUserId(@Param("userId") String userId);
}
