/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.wujiabo.opensource.feather.rest.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.wujiabo.opensource.feather.mybatis.model.TGroup;
import com.wujiabo.opensource.feather.rest.MediaTypes;
import com.wujiabo.opensource.feather.rest.RestException;
import com.wujiabo.opensource.feather.rest.RestValidation;
import com.wujiabo.opensource.feather.service.RbacService;
import com.wujiabo.opensource.feather.service.UserMgmtService;

/**
 * Restful API的Controller.
 * 
 */
@RestController
@RequestMapping(value = "/rbacRest")
public class RbacRestController {

	private static Logger logger = LoggerFactory.getLogger(RbacRestController.class);

	@Autowired
	private Validator validator;

	@Autowired
	private UserMgmtService userMgmtService;

	@Autowired
	private RbacService rbacService;

	@RequestMapping(value = "/group", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public List<TGroup> getAllGroup() {
		return rbacService.getAllGroup();
	}

	@RequestMapping(value = "/group/{userId}", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public List<Map<String, Object>> get(@PathVariable("userId") Integer userId) {
		List<Map<String, Object>> list = userMgmtService.getGroupByUserId(userId);
		if (list == null) {
			String message = "用户不存在(id:" + userId + ")";
			logger.warn(message);
			throw new RestException(HttpStatus.NOT_FOUND, message);
		}
		List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("id", map.get("group_id"));
			jsonMap.put("pId", map.get("group_pid"));
			jsonMap.put("name", map.get("group_name"));
			if ("0".equals(map.get("flag").toString())) {
				jsonMap.put("checked", true);
			}
			jsonList.add(jsonMap);
		}
		return jsonList;
	}

	@RequestMapping(value = "/group/create", method = RequestMethod.POST, consumes = MediaTypes.JSON)
	public ResponseEntity<?> create(@RequestBody TGroup group, UriComponentsBuilder uriBuilder) {
		// 调用JSR303 Bean Validator进行校验, 异常将由RestExceptionHandler统一处理.
		RestValidation.validateWithException(validator, group);

		// // 保存任务
		// taskService.saveTask(group);
		//
		// // 按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		// Long id = task.getId();
		// URI uri = uriBuilder.path("/api/v1/task/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		// headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/group/{id}", method = RequestMethod.PUT, consumes = MediaTypes.JSON)
	// 按Restful风格约定，返回204状态码, 无内容. 也可以返回200状态码.
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@RequestBody TGroup task) {
		// 调用JSR303 Bean Validator进行校验, 异常将由RestExceptionHandler统一处理.
		RestValidation.validateWithException(validator, task);

		// 保存任务
		// taskService.saveTask(task);
	}

	@RequestMapping(value = "/group/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		// taskService.deleteTask(id);
	}
}
