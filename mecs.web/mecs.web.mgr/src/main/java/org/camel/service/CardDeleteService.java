package org.camel.service;

import com.alibaba.fastjson.JSONObject;

public interface CardDeleteService {

	/**
	 * 删除体检卡信息（物理删除）
	 * 
	 * @param cardId
	 * @return
	 */
	JSONObject delCard(String cardId, String cardNum, String adminId);
}
