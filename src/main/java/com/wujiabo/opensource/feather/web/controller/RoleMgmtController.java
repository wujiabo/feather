package com.wujiabo.opensource.feather.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wujiabo.opensource.feather.customized.dao.CustomizedDaoImpl.PageBean;
import com.wujiabo.opensource.feather.mybatis.model.TRole;
import com.wujiabo.opensource.feather.service.RoleMgmtService;

@Controller
@RequestMapping("/roleMgmt")
public class RoleMgmtController {

	@Autowired
	private RoleMgmtService roleMgmtService;

	@RequestMapping(value = "/view", method = { RequestMethod.POST, RequestMethod.GET })
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
	public String editForm(@PathVariable("roleId") String roleId, Model model) {
		TRole role = roleMgmtService.getRoleById(Integer.valueOf(roleId));
		model.addAttribute("role", role);
		model.addAttribute("updateType", "edit");
		return "role/edit";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addForm(Model model) {
		model.addAttribute("updateType", "add");
		return "role/edit";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@RequestParam(value = "roleId", defaultValue = "") String roleId,
			@RequestParam(value = "roleCode", defaultValue = "") String roleCode,
			@RequestParam(value = "roleName", defaultValue = "") String roleName,
			@RequestParam(value = "state", defaultValue = "") String state,
			@RequestParam(value = "updateType", defaultValue = "") String updateType,
			RedirectAttributes redirectAttributes) {

		try{

			if ("add".equals(updateType)) {
				roleMgmtService.addRole(roleCode, roleName, state);
			} else if ("edit".equals(updateType)) {
				roleMgmtService.editRole(roleId,  roleName, state);
			}
			redirectAttributes.addFlashAttribute("message", "操作成功");
		}catch(Exception e){
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "操作失败");
		}

		return "redirect:/roleMgmt/view";
	}
}
