package com.wujiabo.feather.system.custom.service;

import com.wujiabo.feather.common.core.domain.entity.SysUser;

import java.util.List;

public interface RbacService {
    SysUser findUserByUsername(String username);

    List<String> findRoleKeysByUserId(String userId);

    List<String> findPermKeysByUserId(String userId);
}
