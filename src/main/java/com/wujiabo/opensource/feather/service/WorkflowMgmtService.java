package com.wujiabo.opensource.feather.service;

import org.springframework.web.multipart.MultipartFile;

public interface WorkflowMgmtService {

	void deployProcessDef(MultipartFile processFile);

}
