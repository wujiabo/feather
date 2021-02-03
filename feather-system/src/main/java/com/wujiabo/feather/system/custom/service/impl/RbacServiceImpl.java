package com.wujiabo.feather.system.custom.service.impl;

import com.wujiabo.feather.common.core.domain.entity.SysUser;
import com.wujiabo.feather.common.utils.bean.BeanUtils;
import com.wujiabo.feather.system.custom.mapper.RbacMapper;
import com.wujiabo.feather.system.custom.service.RbacService;
import com.wujiabo.feather.system.standard.domain.TSysUser;
import com.wujiabo.feather.system.standard.domain.TSysUserExample;
import com.wujiabo.feather.system.standard.mapper.TSysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class RbacServiceImpl implements RbacService {

    @Autowired
    private RbacMapper rbacMapper;
    @Autowired
    private TSysUserMapper tSysUserMapper;

    @Override
    public SysUser findUserByUsername(String username) {
        TSysUserExample tSysUserEx = new TSysUserExample();
        tSysUserEx.createCriteria().andUserNameEqualTo(username);
        List<TSysUser> tSysUsers = tSysUserMapper.selectByExample(tSysUserEx);
        if (CollectionUtils.isEmpty(tSysUsers)) {
            return null;
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyBeanProp(tSysUsers, sysUser);
        return sysUser;
    }

    @Override
    public List<String> findRoleKeysByUserId(String userId) {
        return rbacMapper.findRoleKeysByUserId(userId);
    }

    @Override
    public List<String> findPermKeysByUserId(String userId) {
        return rbacMapper.findPermKeysByUserId(userId);
    }
}
