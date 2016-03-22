package com.wujiabo.opensource.feather.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wujiabo.opensource.feather.service.WorkflowMgmtService;
import com.wujiabo.opensource.feather.service.exception.ServiceException;

@Service
public class WorkflowMgmtServiceImpl implements WorkflowMgmtService {

	@Resource
	private IdentityService identityService;

	@Resource
	protected RepositoryService repositoryService;

	@Resource
	protected TaskService taskService;

	@Resource
	protected FormService formService;

	@Resource
	private RuntimeService runtimeService;

	@Resource
	protected HistoryService historyService;

	@Autowired
	protected ProcessEngineFactoryBean processEngine;

	@Override
	public void deployProcessDef(MultipartFile processFile) {
		try {
			repositoryService.createDeployment()
					.addInputStream(processFile.getOriginalFilename(), processFile.getInputStream()).deploy();
		} catch (IOException e) {
			throw new ServiceException("发布异常");
		}
	}

	@Override
	public InputStream getProcessViewPicture(String processDefId, String viewType) {

		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(processDefId).singleResult();

		InputStream inputStream = null;
		if ("xml".equals(viewType)) {
			inputStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
					processDefinition.getResourceName());
		} else if ("picture".equals(viewType)) {
			inputStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
					processDefinition.getDiagramResourceName());
		}
		return inputStream;

	}

	@Override
	public void startProcess(String processDefId, String variables) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("owner", "admin");
		runtimeService.startProcessInstanceById(processDefId, map);
	}
}
