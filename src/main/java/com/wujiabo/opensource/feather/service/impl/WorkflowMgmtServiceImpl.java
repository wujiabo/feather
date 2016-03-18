package com.wujiabo.opensource.feather.service.impl;

import javax.annotation.Resource;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wujiabo.opensource.feather.service.WorkflowMgmtService;

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

}
