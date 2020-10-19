package org.camel.controller;

import java.util.ArrayList;
import java.util.List;

import org.camel.service.FrontLogService;
import org.camel.service.impl.FrontLogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.Log;
import mecs.camel.utils.LimitUtil;

/**
 * 
 * 
 * 

     * <p>Title : FrontLogController.java</p>

     * <p>Description : </p>

     * <p>DevelopTools : Eclipse_x64_8.5</p>

     * <p>DevelopSystem : macOS Sierra 10.12.1</p>

     * <p>Company : org.camel</p>

     * @author : camel

     * @date : 2019年6月13日 下午8:32:00

     * @version : 0.0.1
 */


@Controller
public class FrontLogController {
	
	@Autowired
	private FrontLogService frontLogService;
	
	@RequestMapping("/showFrontLog.action")
	@ResponseBody
	public JSONObject showFrontLog(String beginTime,String endTime,String userName,String nowPage) {
		
//		Integer offset = (Integer.valueOf(nowPage)-1)*LimitUtil.LOGLIMIT;
//		int limit1 = LimitUtil.LOGLIMIT;
//		int nowPage1 = Integer.valueOf(nowPage);
		ArrayList<Log> frontList = frontLogService.getLog(beginTime, endTime, userName);
		Integer count = frontLogService.getAllCount(beginTime, endTime, userName);
		JSONObject obj = new JSONObject();
			obj.put("frontList", frontList);
			obj.put("count", count);
			obj.put("limit", LimitUtil.LOGLIMIT);
			return obj;
		
	}
	
	@RequestMapping("/delFrontLog.action")
	@ResponseBody
	public JSONObject delFrontLog(String logIdJson) {
		
		List<String> logIdList = JSONObject.parseArray(logIdJson, String.class);
		boolean bo = frontLogService.delLog(logIdList);
		String res=null;
		if(bo) {
			res = "suc";
		}else {
			res = "fals";
		}
		JSONObject obj = new JSONObject();
			obj.put("res", res);
			return obj;
	}
	
	
}
