package com.wujiabo.opensource.feather.service;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface WorkflowMgmtService {

	void deployProcess(MultipartFile processFile);

	InputStream getProcessDefDiagram(String processDefId);

	void startProcess(String processDefId, String variables);

	InputStream getProcessInstanceDiagram(String instanceId);

	void claimTask(String taskId, String userId);

	void completeTask(String taskId, String variables);

}
