package com.wujiabo.feather.system.custom.service;

import com.wujiabo.feather.system.standard.domain.TSysLoginLog;
import com.wujiabo.feather.system.standard.domain.TSysOperLog;

public interface LogService {
    void insertLoginLog(TSysLoginLog loginLog);

    void insertOperLog(TSysOperLog operLog);
}
