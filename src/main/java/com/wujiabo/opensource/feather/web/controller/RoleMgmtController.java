package com.wujiabo.opensource.feather.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.wujiabo.opensource.feather.customized.dao.CustomizedDaoImpl.PageBean;
import com.wujiabo.opensource.feather.mybatis.model.TRole;
import com.wujiabo.opensource.feather.service.RoleMgmtService;
import com.wujiabo.opensource.feather.service.exception.ServiceException;

@Controller
@RequestMapping("/roleMgmt")
public class RoleMgmtController {

	@Autowired
	private RoleMgmtService roleMgmtService;

	@RequestMapping(value = "/view", method = { RequestMethod.POST, RequestMethod.GET })
	@RequiresPermissions(value = "ROLE_MGMT_VIEW")
	public String view(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "roleCode", defaultValue = "") String roleCode,
			@RequestParam(value = "roleName", defaultValue = "") String roleName, Model model) {
		PageBean pageBean = roleMgmtService.getRoles(roleCode, roleName, currentPage);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("roleCode", roleCode);
		model.addAttribute("roleName", roleName);
		return "role/view";
	}

	@RequestMapping(value = "edit/{roleId}", method = RequestMethod.GET)
	@RequiresPermissions(value = "ROLE_MGMT_UPDATE")
	public String editForm(@PathVariable("roleId") String roleId, Model model) {
		TRole role = roleMgmtService.getRoleById(Integer.valueOf(roleId));
		model.addAttribute("role", role);
		model.addAttribute("updateType", "edit");
		return "role/edit";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	@RequiresPermissions(value = "ROLE_MGMT_UPDATE")
	public String addForm(Model model) {
		model.addAttribute("updateType", "add");
		return "role/edit";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@RequiresPermissions(value = "ROLE_MGMT_UPDATE")
	public String update(@RequestParam(value = "roleId", defaultValue = "") String roleId,
			@RequestParam(value = "roleCode", defaultValue = "") String roleCode,
			@RequestParam(value = "roleName", defaultValue = "") String roleName,
			@RequestParam(value = "state", defaultValue = "") String state,
			@RequestParam(value = "updateType", defaultValue = "") String updateType,
			RedirectAttributes redirectAttributes) {

		try {

			if ("add".equals(updateType)) {
				roleMgmtService.addRole(roleCode, roleName, state);
			} else if ("edit".equals(updateType)) {
				roleMgmtService.editRole(roleId, roleName, state);
			}
			redirectAttributes.addFlashAttribute("message", "操作成功");
		} catch (ServiceException e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			if ("add".equals(updateType)) {
				return "redirect:/roleMgmt/add";
			} else if ("edit".equals(updateType)) {
				return "redirect:/roleMgmt/edit/" + roleId;
			}
		}

		return "redirect:/roleMgmt/view";
	}

	@RequestMapping(value = "permission/{roleId}", method = RequestMethod.GET)
	@RequiresPermissions(value = "ROLE_MGMT_PERMISSION")
	public String permissionForm(@PathVariable("roleId") String roleId, Model model) {
		List<Map<String, Object>> list = roleMgmtService.getPermissionByRoleId(Integer.valueOf(roleId));
		List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("id", map.get("permission_id"));
			jsonMap.put("pId", map.get("permission_pid"));
			jsonMap.put("name", map.get("permission_name"));
			if ("0".equals(map.get("flag").toString())) {
				jsonMap.put("checked", true);
			}
			jsonList.add(jsonMap);
		}
		model.addAttribute("roleId", roleId);
		model.addAttribute("permissionJson", JSON.toJSONString(jsonList));
		return "role/permission";
	}

	@RequestMapping(value = "permission", method = RequestMethod.POST)
	@RequiresPermissions(value = "ROLE_MGMT_PERMISSION")
	public String permission(@RequestParam(value = "roleId", defaultValue = "") String roleId,
			@RequestParam(value = "permissionIds", defaultValue = "") String permissionIds,
			RedirectAttributes redirectAttributes) {
		try {
			roleMgmtService.savePermissions(roleId, permissionIds);
			redirectAttributes.addFlashAttribute("message", "操作成功");
		} catch (ServiceException e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:/roleMgmt/permission/" + roleId;
		}
		return "redirect:/roleMgmt/view";
	}

	@RequestMapping(value = "menu/{roleId}", method = RequestMethod.GET)
	@RequiresPermissions(value = "ROLE_MGMT_MENU")
	public String menuForm(@PathVariable("roleId") String roleId, Model model) {
		List<Map<String, Object>> list = roleMgmtService.getMenuByRoleId(Integer.valueOf(roleId));
		List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("id", map.get("menu_id"));
			jsonMap.put("pId", map.get("menu_pid"));
			jsonMap.put("name", map.get("menu_name"));
			if ("0".equals(map.get("flag").toString())) {
				jsonMap.put("checked", true);
			}
			jsonList.add(jsonMap);
		}
		model.addAttribute("roleId", roleId);
		model.addAttribute("menuJson", JSON.toJSONString(jsonList));
		return "role/menu";
	}

	@RequestMapping(value = "menu", method = RequestMethod.POST)
	@RequiresPermissions(value = "ROLE_MGMT_MENU")
	public String menu(@RequestParam(value = "roleId", defaultValue = "") String roleId,
			@RequestParam(value = "menuIds", defaultValue = "") String menuIds, RedirectAttributes redirectAttributes) {
		try {
			roleMgmtService.saveMenus(roleId, menuIds);
			redirectAttributes.addFlashAttribute("message", "操作成功");
		} catch (ServiceException e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:/roleMgmt/menu/" + roleId;
		}
		return "redirect:/roleMgmt/view";
	}
}
