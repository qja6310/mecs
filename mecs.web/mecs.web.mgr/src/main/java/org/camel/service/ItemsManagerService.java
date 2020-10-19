package org.camel.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public interface ItemsManagerService {

	/**
	 * 获取项目信息
	 * 
	 * @param nowPage
	 * @param itemsName
	 * @return
	 */
	JSONObject getItems(String nowPage, String itemsName, String depId);

	/**
	 * 删除项目
	 * 
	 * @param itemsId
	 * @return
	 */
	JSONObject delItems(String doId, String itemsId);

	/**
	 * 添加项目
	 * 
	 * @param itemsName
	 * @param itemsPrice
	 * @return
	 */
	JSONObject addItems(String doId, String depId, String itemsName, String itemsPrice, List<String> itemList);

	/**
	 * 修改项目
	 * 
	 * @param itemsId
	 * @param itemsName
	 * @param itemsPrice
	 * @return
	 */
	JSONObject alterItems(String doId, String itemsId, String itemsName, String itemsPrice, String depId,
			List<String> itemList);

	/**
	 * 初始化新增界面下拉框
	 * 
	 * @return
	 */
	JSONObject showItemsAdd();

	/**
	 * 初始化编辑界面
	 * 
	 * @return
	 */
	JSONObject showItemsUpdate(String itemsId);

	/**
	 * 查看项目
	 * 
	 * @param itemsId
	 * @return
	 */
	JSONObject lookItems(String itemsId);

}
