package com.wujiabo.feather.system.custom.service.impl;

import com.wujiabo.feather.system.custom.service.LogService;
import com.wujiabo.feather.system.standard.domain.TSysLoginLog;
import com.wujiabo.feather.system.standard.domain.TSysOperLog;
import com.wujiabo.feather.system.standard.mapper.TSysLoginLogMapper;
import com.wujiabo.feather.system.standard.mapper.TSysOperLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private TSysLoginLogMapper tSysLoginLogMapper;
    @Autowired
    private TSysOperLogMapper tSysOperLogMapper;

    @Override
    public void insertLoginLog(TSysLoginLog loginLog) {
        tSysLoginLogMapper.insert(loginLog);
    }

    @Override
    public void insertOperLog(TSysOperLog operLog) {
        tSysOperLogMapper.insert(operLog);
    }
}
