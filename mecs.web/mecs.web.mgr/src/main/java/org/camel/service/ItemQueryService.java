package org.camel.service;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

@Service
public interface ItemQueryService {

	/**
	 * 查询所有细项
	 * 
	 * @param currPage
	 * @param itemName
	 * @return
	 */
	JSONObject getItem(String nowPage, String itemName);

	/**
	 * 添加细项
	 * 
	 * @param itemName
	 * @param itemUnit
	 * @return
	 */
	JSONObject addItm(String doId,String itemName, String itemUnit);

	/**
	 * 删除细项
	 * 
	 * @param itemId
	 * @return
	 */
	JSONObject delItem(String doId,String itemId);
}
