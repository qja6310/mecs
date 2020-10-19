package org.camel.service.impl;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.RowBounds;
import org.camel.dao.BackMoneyMapper;
import org.camel.dao.ItemsReceiveMapper;
import org.camel.dao.LogMapper;
import org.camel.service.ItemsReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.dto.ItemsRecordDto;
import mecs.camel.utils.LimitUtil;
@Service
public class ItemsReceiveServiceImpl implements ItemsReceiveService {
		/**
		 * 项目接收
		 */
	@Autowired
	private ItemsReceiveMapper mapper;
	@Autowired
    private BackMoneyMapper backMapper;
	@Transactional(rollbackFor = Exception.class)
	public JSONObject itemsReceive(String currPage,HttpSession session,String cardNum, String admindepId, String itemsName, String itemsState,
			RowBounds rb) {
		// TODO Auto-generated method stub
		ArrayList<ItemsRecordDto> list = mapper.getItemsRecord(cardNum, admindepId, itemsName, itemsState, rb);
		int count = mapper.getItemSRecordCount(cardNum, admindepId, itemsName, itemsState);
		String balance= backMapper.getBalanceByCard(cardNum);
		String userName= backMapper.getUserNameByCard(cardNum);
		if (userName != null && userName != "") {
			//存session 卡号，userNameNumForItemsForReceive
			session.setAttribute("userNameNumForItemsForReceive", userName);
			session.setAttribute("currPageFor", currPage);
			session.setAttribute("cardNumFor", cardNum);
			session.setAttribute("itemsNameFor", itemsName);
			session.setAttribute("itemsStateFor", itemsState);
			
		}
		JSONObject jo = new JSONObject();
		jo.put("list", list);
		jo.put("count", count);
		jo.put("limit", LimitUtil.ITEMSECORD);
		jo.put("balance", balance);
		jo.put("name", userName);
		return jo;
	}
	/**
	 * 项目接收后修改状态
	 */
	@Autowired
	private ItemsReceiveMapper itemsMapper;
	@Autowired
	private LogMapper logMapper;
	@Transactional(rollbackFor = Exception.class)
	public JSONObject changeItemsReceive(String docName,String docId, String irId,String cardNum,String itemsName) {
		  JSONObject jo = new JSONObject();
		  Integer in = itemsMapper.itemsReceive(docId, irId);
		  //插入日志
		logMapper.addLog(Integer.valueOf(docId),"接收项目", docName+" 接收了卡号为："+cardNum+" 项目名为："+itemsName+" 的项目");

		  if(in > 0 ) {//修改成功
			  jo.put("type", "suc");
			  
		  }else {//修改失败
			  jo.put("type", "err"); 
		  }
		  
		return jo;
	}
	/**
	 * 录入体检小结
	 */
	@Transactional(rollbackFor = Exception.class)
	public JSONObject addResult(String docName,String irId, String result, String docId, String cardNum, String itemsName) {
		 JSONObject jo = new JSONObject();
		Integer in = itemsMapper.addResult(irId, result);
		//插入日志
		logMapper.addLog(Integer.valueOf(docId)," 录入体检小结", docName+" 为卡号："+cardNum+" 项目名为："+itemsName+" 的项目录入了体检小结");
		if(in > 0 ) {//修改成功
			  jo.put("type", "suc");
			  
		  }else {//修改失败
			  jo.put("type", "err"); 
		  }
		return jo;
	}


}
