package com.wujiabo.opensource.feather.web.controller;

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
import com.wujiabo.opensource.feather.mybatis.model.TPermission;
import com.wujiabo.opensource.feather.service.PermissionMgmtService;

@Controller
@RequestMapping("/permissionMgmt")
public class PermissionMgmtController {

	@Autowired
	private PermissionMgmtService permissionMgmtService;

	@RequestMapping(value = "/view", method = { RequestMethod.POST, RequestMethod.GET })
    @RequiresPermissions(value = "PERMISSION_MGMT_VIEW")
	public String view(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "permissionPid", defaultValue = "") String permissionPid,
			@RequestParam(value = "permissionCode", defaultValue = "") String permissionCode,
			@RequestParam(value = "permissionName", defaultValue = "") String permissionName, Model model) {

		PageBean pageBean = permissionMgmtService.getPermissions(permissionPid, permissionCode, permissionName,
				currentPage);
		if (!StringUtils.isBlank(permissionPid)) {
			TPermission parentPermission = permissionMgmtService.getPermissionById(Integer.valueOf(permissionPid));
			model.addAttribute("permissionParentName", parentPermission.getPermissionName());
		}
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("permissionPid", permissionPid);
		model.addAttribute("permissionCode", permissionCode);
		model.addAttribute("permissionName", permissionName);

		String permissionJson = permissionMgmtService.getPermissionJson();
		model.addAttribute("permissionJson", permissionJson);
		return "permission/view";
	}

	@RequestMapping(value = "edit/{permissionId}", method = RequestMethod.GET)
    @RequiresPermissions(value = "PERMISSION_MGMT_UPDATE")
	public String editForm(@PathVariable("permissionId") String permissionId, Model model) {
		TPermission permission = permissionMgmtService.getPermissionById(Integer.valueOf(permissionId));
		model.addAttribute("permission", permission);
		model.addAttribute("updateType", "edit");
		return "permission/edit";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
    @RequiresPermissions(value = "PERMISSION_MGMT_UPDATE")
	public String addForm(Model model) {
		String permissionJson = permissionMgmtService.getPermissionJson();
		model.addAttribute("permissionJson", permissionJson);
		model.addAttribute("updateType", "add");
		return "permission/edit";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
    @RequiresPermissions(value = "PERMISSION_MGMT_UPDATE")
	public String update(@RequestParam(value = "permissionId", defaultValue = "") String permissionId,
			@RequestParam(value = "permissionPid", defaultValue = "") String permissionPid,
			@RequestParam(value = "permissionCode", defaultValue = "") String permissionCode,
			@RequestParam(value = "permissionName", defaultValue = "") String permissionName,
			@RequestParam(value = "state", defaultValue = "") String state,
			@RequestParam(value = "updateType", defaultValue = "") String updateType,
			RedirectAttributes redirectAttributes) {
		try {
			if ("add".equals(updateType)) {
				permissionMgmtService.addPermission(permissionPid, permissionCode, permissionName, state);
			} else if ("edit".equals(updateType)) {
				permissionMgmtService.editPermission(permissionId, permissionCode, permissionName, state);
			}
			redirectAttributes.addFlashAttribute("message", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "操作失败");
		}

		return "redirect:/permissionMgmt/view";
	}
}
