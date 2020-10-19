package org.camel.service;

import com.alibaba.fastjson.JSONObject;

public interface ItemAlterService {

	/**
	 * 修改细项
	 * 
	 * @param itemName
	 * @param itemId
	 * @return
	 */
	JSONObject alterItem(String doId,String itemName, String itemId);
}
