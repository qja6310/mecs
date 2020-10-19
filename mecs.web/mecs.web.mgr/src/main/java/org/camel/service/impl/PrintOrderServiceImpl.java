package org.camel.service.impl;

import java.util.List;

import org.camel.dao.PrintOrderMapper;
import org.camel.service.PrintOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.User;
import mecs.camel.model.dto.MecRecordDto;

@Service
public class PrintOrderServiceImpl implements PrintOrderService {

	@Autowired
	private PrintOrderMapper printOrderMapper;
	
	@Override
	public JSONObject getMecRecordByCardNum(String cardNum) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		/*
		 * 根据卡号获取用户的基本信息
		 */
		User user = printOrderMapper.getUserInfoByCardNum(cardNum);
		/*
		 * 判断卡号的正确性
		 */
		if(user == null) {
			obj.put("res", "cardNumErr");
			return obj;
		}
		obj.put("res", "cardNumSuc");
		obj.put("user", user);
		
		/*
		 * 根据卡号获取体检记录
		 * 1,根据卡号获取相应的体检记录ID
		 * 2,将体检记录ID循环进入批量查询
		 */
		List<MecRecordDto> mecRecordList = printOrderMapper.getMecRecordByCardNum(cardNum);
		obj.put("mecRecordList", mecRecordList);
		
		return obj;
	}

}
