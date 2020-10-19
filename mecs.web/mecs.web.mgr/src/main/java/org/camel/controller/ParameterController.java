package org.camel.controller;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.camel.service.DepartmentService;
import org.camel.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.Department;
import mecs.camel.model.Dict;
import mecs.camel.utils.IdUtil;
import mecs.camel.utils.LimitUtil;

@Controller
public class ParameterController {
	
	@Autowired ParameterService parameterService;
	
	@RequestMapping("/showDict.action")
	@ResponseBody
	public JSONObject showDict(String beginTime,String endTime,String name ,String nowPage) {
		
		Integer offset = (Integer.valueOf(nowPage)-1)*LimitUtil.DEPLIMIT;
		int limit = LimitUtil.DEPLIMIT;
		
		RowBounds rb = new RowBounds(offset, limit);
		ArrayList<Dict> dictList = parameterService.getDict(beginTime, endTime, name, rb);
		Integer count = parameterService.getCount(beginTime, endTime, name);
		JSONObject obj = new JSONObject();
		obj.put("dictList", dictList);
		obj.put("count", count);
		obj.put("limit", limit);
		return obj;
		
	}
	
	@RequestMapping("/delDict.action")
	@ResponseBody
	public JSONObject delDict(String dictId) {
		boolean bo = parameterService.delDict(dictId);
		String  res = null;
		if(bo) {
			res="删除成功！";
		}else {
			res="err";
		}
		JSONObject obj = new JSONObject();
		obj.put("res", res);
		return obj;
		
	}
	@RequestMapping("/addDict.action")
	@ResponseBody
	public JSONObject addDict(String dictCode,String dictName,String dictType,String dictDescribe) {//新增参数
			String res = null;
			boolean bo = parameterService.addDict(dictCode, dictName, dictType, dictDescribe);
			if(bo) {
				res="新增成功！";
			}else {
				res="新增失败";
			}
			
		JSONObject obj = new JSONObject();
		obj.put("res", res);
		return obj;
		
	}
	
	
	
	@RequestMapping("/showDictMessage.action")
	@ResponseBody
	public JSONObject showDictMessage(String dictId) {//编辑参数时回填消息
		
		String dictName =parameterService.getDictName(dictId);
		String dictDescribe = parameterService.getDictDescribe(dictId);
		
		JSONObject obj = new JSONObject();
		obj.put("dictName", dictName);
		obj.put("dictDescribe", dictDescribe);
		return obj;
	}
	
	@RequestMapping("/editDict.action")
	@ResponseBody
	public JSONObject editDict(String dictName,String dictDescribe,String dictId) {//编辑参数
		String res = null;
		boolean bo = parameterService.updateDict(dictName, dictDescribe, dictId);
		if(bo) {
			res = "操作成功！";
		}else {
			res="操作失败！";
		}
			
		JSONObject obj = new JSONObject();
		obj.put("res", res);
		return obj;
		
		
	}
	
}
