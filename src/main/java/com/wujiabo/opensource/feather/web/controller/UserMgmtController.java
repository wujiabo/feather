package com.wujiabo.opensource.feather.web.controller;

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
import com.wujiabo.opensource.feather.mybatis.model.TUser;
import com.wujiabo.opensource.feather.service.UserMgmtService;

@Controller
@RequestMapping("/userMgmt")
public class UserMgmtController {

	@Autowired
	private UserMgmtService userMgmtService;

	@RequestMapping(value = "/view", method = { RequestMethod.POST, RequestMethod.GET })
    @RequiresPermissions(value = "USER_MGMT_VIEW")
	public String view(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "userName", defaultValue = "") String userName,
			@RequestParam(value = "screenName", defaultValue = "") String screenName, Model model) {
		PageBean pageBean = userMgmtService.getUsers(userName, screenName, currentPage);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("userName", userName);
		model.addAttribute("screenName", screenName);
		return "user/view";
	}

	@RequestMapping(value = "edit/{userId}", method = RequestMethod.GET)
    @RequiresPermissions(value = "USER_MGMT_UPDATE")
	public String editForm(@PathVariable("userId") String userId, Model model) {
		TUser user = userMgmtService.getUserById(Integer.valueOf(userId));
		model.addAttribute("user", user);
		model.addAttribute("updateType", "edit");
		return "user/edit";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
    @RequiresPermissions(value = "USER_MGMT_UPDATE")
	public String addForm(Model model) {
		model.addAttribute("updateType", "add");
		return "user/edit";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
    @RequiresPermissions(value = "USER_MGMT_UPDATE")
	public String update(@RequestParam(value = "userId", defaultValue = "") String userId,
			@RequestParam(value = "userName", defaultValue = "") String userName,
			@RequestParam(value = "screenName", defaultValue = "") String screenName,
			@RequestParam(value = "state", defaultValue = "") String state,
			@RequestParam(value = "updateType", defaultValue = "") String updateType,
			RedirectAttributes redirectAttributes) {

		try{

			if ("add".equals(updateType)) {
				userMgmtService.addUser(userName, screenName, state);
			} else if ("edit".equals(updateType)) {
				userMgmtService.editUser(userId,  screenName, state);
			}
			redirectAttributes.addFlashAttribute("message", "操作成功");
		}catch(Exception e){
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "操作失败");
		}

		return "redirect:/userMgmt/view";
	}
}
