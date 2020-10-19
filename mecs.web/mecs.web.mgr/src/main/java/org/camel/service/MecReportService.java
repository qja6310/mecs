package org.camel.service;

import com.alibaba.fastjson.JSONObject;

public interface MecReportService {

	/**
	 * 根据卡号或者体检号码获取体检记录
	 * @param cardNum
	 * @param mecCode
	 * @return
	 */
	JSONObject getMecRecord(String cardNum, String mecCode);
	
}
