package com.wujiabo.opensource.feather.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wujiabo.opensource.feather.customized.dao.CustomizedDaoImpl.PageBean;
import com.wujiabo.opensource.feather.mybatis.model.TUser;
import com.wujiabo.opensource.feather.service.UserMgmtService;
import com.wujiabo.opensource.feather.util.RequestUtil;

@Controller
@RequestMapping("/userMgmt")
public class UserMgmtController {

	@Autowired
	private UserMgmtService userMgmtService;

	@RequestMapping(value = "/view", method = { RequestMethod.POST, RequestMethod.GET })
	public String view(HttpServletRequest request, Model model) {
		String currentPage = RequestUtil.getParamString(request, "currentPage", "1");
		String userName = RequestUtil.getParamString(request, "userName", "");
		String screenName = RequestUtil.getParamString(request, "screenName", "");
		PageBean pageBean = userMgmtService.getUsers(userName, screenName, Integer.valueOf(currentPage));
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("userName", userName);
		model.addAttribute("screenName", screenName);
		return "user/view";
	}

	@RequestMapping(value = "edit/{userId}", method = RequestMethod.GET)
	public String editForm(@PathVariable("userId") String userId, Model model) {
		TUser user = userMgmtService.getUserById(Integer.valueOf(userId));
		model.addAttribute("user", user);
		model.addAttribute("updateType", "edit");
		return "user/edit";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addForm(Model model) {
		model.addAttribute("updateType", "add");
		return "user/edit";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", "更新用户成功");
		return "redirect:/userMgmt/view";
	}
}
