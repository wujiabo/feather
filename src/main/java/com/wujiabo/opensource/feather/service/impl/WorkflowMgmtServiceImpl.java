package com.wujiabo.opensource.feather.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
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
		map.put("owner", "1");
		runtimeService.startProcessInstanceById(processDefId, map);
	}

	@Override
	public InputStream getProcessInstanceViewPicture(String processInstanceId) {
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());

		List<Execution> executionList = runtimeService.createExecutionQuery().processInstanceId(processInstanceId)
				.list();

		List<String> activeActivityIds = new ArrayList<String>();
		for (Execution execution : executionList) {
			activeActivityIds.add(execution.getActivityId());
		}
		// Context.setProcessEngineConfiguration(processEngine.getProcessEngineConfiguration());
		ProcessDiagramGenerator processDiagramGenerator = new DefaultProcessDiagramGenerator();
		InputStream imageStream = processDiagramGenerator.generateDiagram(bpmnModel, "png", activeActivityIds,
				Collections.<String> emptyList(), processEngine.getProcessEngineConfiguration().getActivityFontName(),
				processEngine.getProcessEngineConfiguration().getLabelFontName(), null, 1.0);
		return imageStream;
	}
}
