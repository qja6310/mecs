package org.camel.service;

import com.alibaba.fastjson.JSONObject;

public interface CardQueryService {

	/**
	 * 查询所有卡
	 * 
	 * @param currPage
	 * @param cardNumber
	 * @param userName
	 * @param cardState
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	JSONObject cardQuery(String currPage, String cardNumber, String userName, String cardState, String startTime,
			String endTime);

}
