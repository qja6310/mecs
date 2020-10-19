package org.camel.controller;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.camel.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.Department;
import mecs.camel.utils.IdUtil;
import mecs.camel.utils.LimitUtil;

@Controller
public class DepartmentController {
	
	@Autowired DepartmentService departmentService;
	
	@RequestMapping("/showDep.action")
	@ResponseBody
	public JSONObject showDep(String beginTime,String endTime,String name ,String nowPage) {
		
		Integer offset = (Integer.valueOf(nowPage)-1)*LimitUtil.DEPLIMIT;
		int limit = LimitUtil.DEPLIMIT;
		
		RowBounds rb = new RowBounds(offset, limit);
		ArrayList<Department> depList = departmentService.getDepartment(beginTime, endTime, name, rb);
		
		Integer count = departmentService.getCount(beginTime, endTime, name);
		JSONObject obj = new JSONObject();
		obj.put("depList", depList);
		obj.put("count", count);
		obj.put("limit", limit);
		return obj;
		
	}
	
	@RequestMapping("/delDep.action")
	@ResponseBody
	public JSONObject delDep(String depId) {
		System.out.println("进入删除科室方法");
		String depName = departmentService.getDepName(depId);
		String newDepName = depName+"(已删除)";
		boolean bo = departmentService.delDep(depId, newDepName);
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
	@RequestMapping("/addDep.action")
	@ResponseBody
	public JSONObject addDep(String depName,String depDescribe) {//新增科室
		//用科室名称复查一遍是否重复
		String newdepName =departmentService.checkdepName(depName);
		String res = null;
		if(newdepName!=null) {
			  res = "科室名重复!";
		}else {
			boolean bo = departmentService.addDep(depName, depDescribe);
			if(bo) {
				res="编辑成功！";
			}else {
				res="err";
			}
			
		}
		JSONObject obj = new JSONObject();
		obj.put("res", res);
		return obj;
		
	}
	
	
	
	@RequestMapping("/showDepartment.action")
	@ResponseBody
	public JSONObject showDepartment(String depId) {//编辑科室时回填消息
		
		//用科室名称复查一遍是否重复
		String newdepName =departmentService.getDepName(depId);
		String newdepDescribe = departmentService.getdepDescribe(depId);
		
		JSONObject obj = new JSONObject();
		obj.put("newdepName", newdepName);
		obj.put("newdepDescribe", newdepDescribe);
		return obj;
	}
	
	@RequestMapping("/editDep.action")
	@ResponseBody
	public JSONObject editDep(String depId,String depName,String depDescribe) {//编辑科室
		//用科室名称复查一遍是否重复
		String newdepName =departmentService.checkEditDepName(depId, depName);
		String res = null;
		if(newdepName!=null) {//排除要修改科室的名字  其他名字不能跟要修改的科室名重复
			res = "rep";
		}else {
			boolean bo = departmentService.update(depId, depName, depDescribe);
			if(bo) {
				res="suc";
			}else {
				res="err";
			}
			
		}
		JSONObject obj = new JSONObject();
		obj.put("res", res);
		return obj;
		
		
	}
	
}
