package com.wujiabo.opensource.feather.service;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface WorkflowMgmtService {

	void deployProcessDef(MultipartFile processFile);

	InputStream getProcessViewPicture(String processDefId, String viewType);

	void startProcess(String processDefId, String variables);

	InputStream getProcessInstanceViewPicture(String instanceId);

	void claimTask(String taskId, String userId);

	void completeTask(String taskId, String variables);

}
