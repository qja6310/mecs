package org.camel.service.impl;

import java.util.List;

import org.camel.dao.MecReportMapper;
import org.camel.service.MecReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.MecRecord;

@Service
public class MecReportServiceImpl implements MecReportService {
	
	@Autowired
	private MecReportMapper mecReportMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject getMecRecord(String cardNum, String mecCode) {
		JSONObject obj = new JSONObject();
		/*
		 * 数据库获取体检记录
		 */
		List<MecRecord> mecRecordList = mecReportMapper.getMecRecord(cardNum, mecCode);
		obj.put("mecRecordList", mecRecordList);
		obj.put("res", "suc");
		return obj;
	}

}
