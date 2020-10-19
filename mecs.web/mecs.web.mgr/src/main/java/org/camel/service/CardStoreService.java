package org.camel.service;

import com.alibaba.fastjson.JSONObject;

public interface CardStoreService {

	/**
	 * ---------------------林--------------------- 根据开始卡号，结束卡号入库，Admin的信息
	 * 
	 * @param stardNum
	 * @param endNum
	 * @param admin
	 * @return
	 */
	JSONObject cardStore(String startNum, String endNum, String cardPrefix, String adminId);
}
