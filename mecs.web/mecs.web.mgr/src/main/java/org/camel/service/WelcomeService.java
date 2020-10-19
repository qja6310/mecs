package org.camel.service;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.Admin;

public interface WelcomeService {

	/**
	 * 获取首页的信息
	 * @return
	 */
	JSONObject getAdminAndData(Admin admin);
	
}
