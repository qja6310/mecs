package org.camel.service.impl;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.RowBounds;
import org.camel.dao.BackMoneyMapper;
import org.camel.dao.LogMapper;
import org.camel.service.BackMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.Admin;
import mecs.camel.model.dto.ItemsRecordDto;
@Service
public class BackMoneyServiceImpl implements BackMoneyService {

	 /**
	  * //根据卡号查询卡余额
	  */
	@Autowired
	private BackMoneyMapper mapper;
	@Autowired
	private LogMapper logMapper;
	@Transactional(rollbackFor = Exception.class)
	public String getBalanceByCard(String cardNum) {
		String s = mapper.getBalanceByCard(cardNum);
		return s;
	}

	@Override
	//根据卡号查询用户名字
	public String getUserNameByCard(String cardNum) {
		String s = mapper.getUserNameByCard(cardNum);
		return s;
	}

	@Override
	//根据卡号 ，项目状态分页查询 表格集合
	public ArrayList<ItemsRecordDto> getItemsByCard(String cardNum, String itemsState, RowBounds rb) {
		ArrayList<ItemsRecordDto> list = mapper.getItemsByCard(cardNum, itemsState, rb);
		return list;
	}

	@Override
    //根据卡号，项目状态查询总记录数
	public Integer getItemsCountByCard(String cardNum, String itemsState) {
		int count = mapper.getItemsCountByCard(cardNum, itemsState);
		return count;
	}

	@Override
	public JSONObject modifyItemsStateAndBalanceById(HttpSession session,Integer cardItemsId,String itemsState, 
			String cardNum, String money,RowBounds rb) {
		 int retState = mapper.modifyItemsStateById(cardItemsId);
		 int retBalance = mapper.modifyBalanceByCard(cardNum, money);
		 int account = mapper.addAccount(cardNum, money);
		 JSONObject jo = new JSONObject();
		 if (retState > 0 && retBalance > 0 && account > 0) {//表明退费成功
			 jo.put("ret", "退费成功");
			 //记录日志表
			 Admin ad=(Admin) session.getAttribute("sessionAdminKey");
			 String adminId=ad.getAdminId();
			 logMapper.addLog(Integer.valueOf(adminId), "退费", "给体检卡,卡号："+cardNum+" 退费，退费金额："+money+"元");
			 //修改退费时间
			 mapper.modifyReturnTime(cardItemsId);
			 //退费成功后修改体检记录表的项目的金额
			 mapper.modifyMecRecordMoney(cardItemsId, money);
		     
		 } else {
			 jo.put("ret", "退费失败");
		 }
		 //查询总记录数
		 int count = mapper.getItemsCountByCard(cardNum, itemsState);
		 //查询集合
		 ArrayList<ItemsRecordDto> list = mapper.getItemsByCard(cardNum, itemsState, rb);
		 //查询余额
		 String s = mapper.getBalanceByCard(cardNum);
		 jo.put("count", count);
		 jo.put("list", list);
		 jo.put("balance", s);
		 
		return jo;
	}

	
}
