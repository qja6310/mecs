package org.camel.service.impl;

import java.util.List;

import org.camel.dao.PrintMecReportMapper;
import org.camel.service.PrintMecReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.Admin;
import mecs.camel.model.ItemsRecord;
import mecs.camel.model.Log;
import mecs.camel.model.MecRecord;
import mecs.camel.model.User;

@Service
public class PrintMecReportServiceImpl implements PrintMecReportService {

	@Autowired
	private PrintMecReportMapper printMecReporMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject getPrintMecReportInfo(String mrNum,Admin admin) {
		JSONObject obj = new JSONObject();
		/*
		 * 获取用户的信息
		 */
		User user = printMecReporMapper.getUserByMrNum(mrNum);
		obj.put("user", user);
		/*
		 * 获取体检记录Model
		 */
		MecRecord mecRecord = printMecReporMapper.getMecRecordByMrNum(mrNum);
		obj.put("mecRecord", mecRecord);
		/*
		 * 获取相应的项目记录  List
		 */
		List<ItemsRecord> itemsList = printMecReporMapper.itemsList(mrNum);
		obj.put("itemsList", itemsList);
		
		/*
		 * 去修改体检记录状态
		 */
		Integer changeState= printMecReporMapper.changeMecRecordState(mrNum);
		
		/*
		 * 去插入数据库
		 */
		Log mgrLog = new Log(null, admin.getAdminId(), "打印体检报告", "打印,体检号码为[" + mrNum + "]", null, null); 
		Integer addMgrLog = printMecReporMapper.addMgrLog(mgrLog);//  7
		
		if(changeState > 0 && addMgrLog > 0) {
			obj.put("res", "suc");
		}else {
			obj.put("res", "err");
		}
		return obj;
	}

	@Override
	public JSONObject getPrintMecReport(String mrNum) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		/*
		 * 获取用户的信息
		 */
		User user = printMecReporMapper.getUserByMrNum(mrNum);
		obj.put("user", user);
		/*
		 * 获取体检记录Model
		 */
		MecRecord mecRecord = printMecReporMapper.getMecRecordByMrNum(mrNum);
		obj.put("mecRecord", mecRecord);
		/*
		 * 获取相应的项目记录  List
		 */
		List<ItemsRecord> itemsList = printMecReporMapper.itemsList(mrNum);
		obj.put("itemsList", itemsList);
		
		return obj;
	}

}
