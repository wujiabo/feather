package com.wujiabo.opensource.feather.service;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface WorkflowMgmtService {

	void deployProcessDef(MultipartFile processFile);

	InputStream getProcessViewPicture(String processDefId, String viewType);

}
