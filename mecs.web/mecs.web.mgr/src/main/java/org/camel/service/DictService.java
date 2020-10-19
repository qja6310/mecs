package org.camel.service;

import com.alibaba.fastjson.JSONObject;

public interface DictService {

	/**
	 * 从数据库获取体检卡的前缀
	 * 
	 * @return
	 */
	JSONObject getCardPrefix();

	/**
	 * 从数据库获取体检卡的位数
	 * 
	 * @return
	 */
	JSONObject getCardFigure();
}
