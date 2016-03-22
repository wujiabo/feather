package com.wujiabo.opensource.feather.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.util.IoUtil;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wujiabo.opensource.feather.service.WorkflowMgmtService;
import com.wujiabo.opensource.feather.service.exception.ServiceException;

@Controller
@RequestMapping("/workflowMgmt")
public class WorkflowMgmtController {

	@Autowired
	private WorkflowMgmtService workflowMgmtService;

	@Resource
	protected RepositoryService repositoryService;

	@Resource
	protected HistoryService historyService;

	@RequestMapping(value = "/process", method = { RequestMethod.POST, RequestMethod.GET })
	@RequiresPermissions(value = "WORKFLOW_MGMT_PROCESS")
	public String process(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "processDefinitionKey", defaultValue = "") String processDefinitionKey, Model model) {

		long totalCount = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKeyLike("%" + processDefinitionKey + "%").active().count();
		List<ProcessDefinition> processDefList = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKeyLike("%" + processDefinitionKey + "%").orderByDeploymentId().active().asc()
				.listPage((currentPage - 1) * 10, 10);

		Boolean isFirst = false;
		Boolean isLast = false;
		if (currentPage == 1) {
			isFirst = true;
			isLast = false;
		}
		if (totalCount <= currentPage * 10 && totalCount >= (currentPage - 1) * 10) {
			isLast = true;
		}
		int totalPage = (int) totalCount / 10 + (totalCount % 10 > 0 ? 1 : 0);

		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("isFirst", isFirst);
		model.addAttribute("isLast", isLast);
		model.addAttribute("processDefList", processDefList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("processDefinitionKey", processDefinitionKey);
		return "workflow/process";
	}

	@RequestMapping(value = "deploy", method = RequestMethod.GET)
	@RequiresPermissions(value = "WORKFLOW_MGMT_DEPLOY")
	public String deployForm() {
		return "workflow/deploy";
	}

	@RequestMapping(value = "deploy", method = RequestMethod.POST)
	@RequiresPermissions(value = "WORKFLOW_MGMT_DEPLOY")
	public String deploy(@RequestParam(value = "processFile", required = false) MultipartFile processFile,
			RedirectAttributes redirectAttributes) {
		try {
			workflowMgmtService.deployProcessDef(processFile);
			redirectAttributes.addFlashAttribute("message", "操作成功");
		} catch (ServiceException e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:/workflowMgmt/deploy";
		}
		return "redirect:/workflowMgmt/process";
	}

	@RequestMapping(value = "viewPicture/{processDefId}", method = RequestMethod.GET)
	@RequiresPermissions(value = "WORKFLOW_MGMT_DEPLOY")
	public String viewPicture(@PathVariable("processDefId") String processDefId, Model model) {
		model.addAttribute("processDefId", processDefId);
		return "workflow/view";
	}

	@RequestMapping(value = "picture/{processDefId}/{viewType}", method = RequestMethod.GET)
	@RequiresPermissions(value = "WORKFLOW_MGMT_DEPLOY")
	public String picture(@PathVariable("processDefId") String processDefId, @PathVariable("viewType") String viewType,
			HttpServletResponse response) {
		InputStream processDefInputStream = workflowMgmtService.getProcessViewPicture(processDefId, viewType);
		try {
			response.getOutputStream().write(IoUtil.readInputStream(processDefInputStream, "processDefInputStream"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/instance", method = { RequestMethod.POST, RequestMethod.GET })
	@RequiresPermissions(value = "WORKFLOW_MGMT_PROCESS")
	public String instance(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "orderId", defaultValue = "") String orderId, Model model) {

		long totalCount = historyService.createHistoricProcessInstanceQuery().count();
		List<HistoricProcessInstance> processInstanceList = historyService.createHistoricProcessInstanceQuery()
				.orderByProcessInstanceId().asc().listPage((currentPage - 1) * 10, 10);

		Boolean isFirst = false;
		Boolean isLast = false;
		if (currentPage == 1) {
			isFirst = true;
			isLast = false;
		}
		if (totalCount <= currentPage * 10 && totalCount >= (currentPage - 1) * 10) {
			isLast = true;
		}
		int totalPage = (int) totalCount / 10 + (totalCount % 10 > 0 ? 1 : 0);

		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("isFirst", isFirst);
		model.addAttribute("isLast", isLast);
		model.addAttribute("processInstanceList", processInstanceList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("orderId", orderId);
		return "workflow/instance";
	}

	@RequestMapping(value = "start", method = RequestMethod.GET)
	@RequiresPermissions(value = "WORKFLOW_MGMT_DEPLOY")
	public String startForm(Model model) {
		List<ProcessDefinition> processDefList = repositoryService.createProcessDefinitionQuery().latestVersion()
				.active().orderByProcessDefinitionName().asc().list();
		model.addAttribute("processDefList", processDefList);
		return "workflow/start";
	}

	@RequestMapping(value = "start", method = RequestMethod.POST)
	@RequiresPermissions(value = "WORKFLOW_MGMT_DEPLOY")
	public String start(@RequestParam(value = "processDefId", required = false) String processDefId,
			@RequestParam(value = "variables", required = false) String variables,
			RedirectAttributes redirectAttributes) {
		try {
			workflowMgmtService.startProcess(processDefId, variables);
			redirectAttributes.addFlashAttribute("message", "操作成功");
		} catch (ServiceException e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:/workflowMgmt/start";
		}
		return "redirect:/workflowMgmt/instance";
	}

}
