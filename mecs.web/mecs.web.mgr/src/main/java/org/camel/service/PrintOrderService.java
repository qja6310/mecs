package org.camel.service;

import com.alibaba.fastjson.JSONObject;

public interface PrintOrderService {

	/**
	 * 根据卡号获取体检记录信息和用户信息
	 * @param cardNum
	 * @return
	 */
	JSONObject getMecRecordByCardNum(String cardNum);
	
}
