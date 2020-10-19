package org.camel.service;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONObject;

public interface ComboManagerService {

	/**
	 * 获取套餐信息
	 * 
	 * @param nowPage
	 * @param comboName
	 * @return
	 */
	JSONObject getCombo(String nowPage, String comboName);

	/**
	 * 获取所有套餐
	 * 
	 * @return
	 */

	/**
	 * 获得所有套餐
	 * 
	 * @return
	 */
	JSONObject getAllCombo();

	/**
	 * 删除套餐信息
	 * 
	 * @param ComboId
	 * @return
	 */
	JSONObject delCombo(String doId,String ComboId);

	/**
	 * 修改套餐的状态
	 * 
	 * @param ComboId
	 * @param ComboState
	 * @return
	 */
	JSONObject alterComboState(String doId,String comboId, String comboState);

	/**
	 * 增加套餐
	 * 
	 * @return
	 */
	JSONObject addCombo(String doId,String comboName, String price, ArrayList<String> list);

	/**
	 * 初始化修改界面
	 * 
	 * @param comboId
	 * @return
	 */
	JSONObject showUpdateComboPanel(String comboId);
	/**
	 * 修改套餐
	 * @param comboId
	 * @param comboName
	 * @param price
	 * @param list
	 * @return
	 */
	JSONObject toUpdateCombo(String doId,String comboId, String comboName, String price, ArrayList<String> list);

	/**
	 * 查看套餐
	 * @param comboId
	 * @return
	 */
	JSONObject lookCombo(String comboId);

}
