package org.camel.service;

import com.alibaba.fastjson.JSONObject;

public interface UserMsgService {

	/**
	 * 获取体检人信息
	 * 
	 * @param currPage
	 * @param userName
	 * @param userTel
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	JSONObject userMsgQuery(String currPage, String userName, String number, String userTel, String startTime,
			String endTime);

}
