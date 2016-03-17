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
import com.wujiabo.opensource.feather.mybatis.model.TMenu;
import com.wujiabo.opensource.feather.service.MenuMgmtService;
import com.wujiabo.opensource.feather.service.exception.ServiceException;

@Controller
@RequestMapping("/menuMgmt")
public class MenuMgmtController {

	@Autowired
	private MenuMgmtService menuMgmtService;

	@RequestMapping(value = "/view", method = { RequestMethod.POST, RequestMethod.GET })
	@RequiresPermissions(value = "MENU_MGMT_VIEW")
	public String view(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "menuPid", defaultValue = "") String menuPid,
			@RequestParam(value = "menuUrl", defaultValue = "") String menuUrl,
			@RequestParam(value = "menuName", defaultValue = "") String menuName, Model model) {

		PageBean pageBean = menuMgmtService.getMenus(menuPid, menuUrl, menuName, currentPage);
		if (!StringUtils.isBlank(menuPid)) {
			TMenu parentMenu = menuMgmtService.getMenuById(Integer.valueOf(menuPid));
			model.addAttribute("menuParentName", parentMenu.getMenuName());
		}
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("menuPid", menuPid);
		model.addAttribute("menuUrl", menuUrl);
		model.addAttribute("menuName", menuName);

		String menuJson = menuMgmtService.getMenuJson();
		model.addAttribute("menuJson", menuJson);
		return "menu/view";
	}

	@RequestMapping(value = "edit/{menuId}", method = RequestMethod.GET)
	@RequiresPermissions(value = "MENU_MGMT_UPDATE")
	public String editForm(@PathVariable("menuId") String menuId, Model model) {
		TMenu menu = menuMgmtService.getMenuById(Integer.valueOf(menuId));
		model.addAttribute("menu", menu);
		model.addAttribute("updateType", "edit");
		return "menu/edit";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	@RequiresPermissions(value = "MENU_MGMT_UPDATE")
	public String addForm(Model model) {
		String menuJson = menuMgmtService.getMenuJson();
		model.addAttribute("menuJson", menuJson);
		model.addAttribute("updateType", "add");
		return "menu/edit";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@RequiresPermissions(value = "MENU_MGMT_UPDATE")
	public String update(@RequestParam(value = "menuId", defaultValue = "") String menuId,
			@RequestParam(value = "menuPid", defaultValue = "") String menuPid,
			@RequestParam(value = "menuUrl", defaultValue = "") String menuUrl,
			@RequestParam(value = "menuName", defaultValue = "") String menuName,
			@RequestParam(value = "seq", defaultValue = "0") String seq,
			@RequestParam(value = "state", defaultValue = "") String state,
			@RequestParam(value = "updateType", defaultValue = "") String updateType,
			RedirectAttributes redirectAttributes) {
		try {
			Integer sequence = 0;
			if (StringUtils.isNumeric(seq)) {
				sequence = Integer.valueOf(seq);
			}

			if ("add".equals(updateType)) {
				menuMgmtService.addMenu(menuPid, menuUrl, menuName, sequence, state);
			} else if ("edit".equals(updateType)) {
				menuMgmtService.editMenu(menuId, menuUrl, menuName, sequence, state);
			}
			redirectAttributes.addFlashAttribute("message", "操作成功");
		} catch (ServiceException e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			if ("add".equals(updateType)) {
				return "redirect:/menuMgmt/add";
			} else if ("edit".equals(updateType)) {
				return "redirect:/menuMgmt/edit/" + menuId;
			}
		}

		return "redirect:/menuMgmt/view";
	}
}
