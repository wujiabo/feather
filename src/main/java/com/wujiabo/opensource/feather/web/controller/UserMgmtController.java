package com.wujiabo.opensource.feather.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wujiabo.opensource.feather.customized.dao.CustomizedDaoImpl.PageBean;
import com.wujiabo.opensource.feather.service.UserMgmtService;
import com.wujiabo.opensource.feather.util.RequestUtil;

@Controller
@RequestMapping("/userMgmt")
public class UserMgmtController {

	@Autowired
	private UserMgmtService userMgmtService;

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(HttpServletRequest request, HttpServletResponse response) {
		String userName = RequestUtil.getString(request, "userName", "");
		String currentPage = RequestUtil.getString(request, "currentPage", "1");
		PageBean pageBean = userMgmtService.getUsers(userName, Integer.valueOf(currentPage));
		request.setAttribute("userName", userName);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBean", pageBean);
		return "user/view";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id) {
		return "user/edit";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", "更新用户成功");
		return "redirect:/userMgmt/list";
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", "删除用户成功");
		return "redirect:/userMgmt/list";
	}
}
