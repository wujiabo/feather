package com.wujiabo.opensource.feather.web.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wujiabo.opensource.feather.customized.dao.CustomizedDaoImpl.PageBean;
import com.wujiabo.opensource.feather.mybatis.model.TGroup;
import com.wujiabo.opensource.feather.service.GroupMgmtService;
import com.wujiabo.opensource.feather.service.exception.ServiceException;

@Controller
@RequestMapping("/groupMgmt")
public class GroupMgmtController {

	@Autowired
	private GroupMgmtService groupMgmtService;

	@RequestMapping(value = "/view", method = { RequestMethod.POST, RequestMethod.GET })
	@RequiresPermissions(value = "GROUP_MGMT_VIEW")
	public String view(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "groupPid", defaultValue = "") String groupPid,
			@RequestParam(value = "groupCode", defaultValue = "") String groupCode,
			@RequestParam(value = "groupName", defaultValue = "") String groupName, Model model) {

		PageBean pageBean = groupMgmtService.getGroups(groupPid, groupCode, groupName, currentPage);
		if (!StringUtils.isBlank(groupPid)) {
			TGroup parentGroup = groupMgmtService.getGroupById(Integer.valueOf(groupPid));
			model.addAttribute("groupParentName", parentGroup.getGroupName());
		}
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("groupPid", groupPid);
		model.addAttribute("groupCode", groupCode);
		model.addAttribute("groupName", groupName);

		String groupJson = groupMgmtService.getGroupJson();
		model.addAttribute("groupJson", groupJson);
		return "group/view";
	}

	@RequestMapping(value = "edit/{groupId}", method = RequestMethod.GET)
	@RequiresPermissions(value = "GROUP_MGMT_UPDATE")
	public String editForm(@PathVariable("groupId") String groupId, Model model) {
		TGroup group = groupMgmtService.getGroupById(Integer.valueOf(groupId));
		model.addAttribute("group", group);
		model.addAttribute("updateType", "edit");
		return "group/edit";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	@RequiresPermissions(value = "GROUP_MGMT_UPDATE")
	public String addForm(Model model) {
		String groupJson = groupMgmtService.getGroupJson();
		model.addAttribute("groupJson", groupJson);
		model.addAttribute("updateType", "add");
		return "group/edit";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@RequiresPermissions(value = "GROUP_MGMT_UPDATE")
	public String update(@RequestParam(value = "groupId", defaultValue = "") String groupId,
			@RequestParam(value = "groupPid", defaultValue = "") String groupPid,
			@RequestParam(value = "groupCode", defaultValue = "") String groupCode,
			@RequestParam(value = "groupName", defaultValue = "") String groupName,
			@RequestParam(value = "seq", defaultValue = "0") Integer seq,
			@RequestParam(value = "state", defaultValue = "") String state,
			@RequestParam(value = "updateType", defaultValue = "") String updateType,
			RedirectAttributes redirectAttributes) {
		try {
			if ("add".equals(updateType)) {
				groupMgmtService.addGroup(groupPid, groupCode, groupName, state);
			} else if ("edit".equals(updateType)) {
				groupMgmtService.editGroup(groupId, groupCode, groupName, state);
			}
			redirectAttributes.addFlashAttribute("message", "操作成功");
		} catch (ServiceException e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:/groupMgmt/edit/" + groupId;
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "操作失败");
			return "redirect:/groupMgmt/edit/" + groupId;
		}

		return "redirect:/groupMgmt/view";
	}

	@RequestMapping(value = "role/{groupId}", method = RequestMethod.GET)
	@RequiresPermissions(value = "GROUP_MGMT_ROLE")
	public String roleForm(@PathVariable("groupId") String groupId, Model model) {
		List<Map<String, Object>> roleList = groupMgmtService.getRoleByGroupId(groupId);
		model.addAttribute("roleList", roleList);
		model.addAttribute("groupId", groupId);
		return "group/role";
	}

	@RequestMapping(value = "role", method = RequestMethod.POST)
	@RequiresPermissions(value = "GROUP_MGMT_ROLE")
	public String role(@RequestParam(value = "groupId", defaultValue = "") String groupId,
			@RequestParam(value = "roleIds", defaultValue = "") String roleIds, RedirectAttributes redirectAttributes) {
		try {
			groupMgmtService.saveRoles(groupId, roleIds);
			redirectAttributes.addFlashAttribute("message", "操作成功");
		} catch (ServiceException e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:/groupMgmt/edit/" + groupId;
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "操作失败");
			return "redirect:/groupMgmt/edit/" + groupId;
		}
		return "redirect:/groupMgmt/view";
	}
}
