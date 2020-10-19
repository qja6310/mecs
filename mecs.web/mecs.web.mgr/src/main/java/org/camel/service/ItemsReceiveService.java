package org.camel.service;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.alibaba.fastjson.JSONObject;

public interface ItemsReceiveService {
	/**
	 * 项目接收
	 * @param cardNum
	 * @param admindepId
	 * @param itemsName
	 * @param itemsState
	 * @param rb
	 * @return
	 */
	JSONObject itemsReceive(String currPage,HttpSession session,String cardNum,String admindepId,String itemsName,String itemsState,RowBounds rb );

	/**
	 *项目接收后，改变项目状态
	 * @param docId
	 * @param irId
	 * @return
	 */
	JSONObject changeItemsReceive(String docName,String docId,String irId,String cardNum,String itemsName);
	/**
	 * 录入项目小结
	 * @param irId
	 * @param result
	 * @param docId
	 * @return
	 */
	JSONObject addResult(String docName,String irId,String result,String docId, String cardNum, String itemsName);
}
