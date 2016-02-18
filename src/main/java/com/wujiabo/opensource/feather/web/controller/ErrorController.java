package com.wujiabo.opensource.feather.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {

	/**
	 * 404页
	 */
	@RequestMapping("/404")
	public String error404() {
		return "404";
	}

	/**
	 * 401页
	 */
	@RequestMapping("/401")
	public String error401() {
		return "401";
	}

	/**
	 * 500页
	 */
	@RequestMapping("/500")
	public String error500() {
		return "500";
	}
}
