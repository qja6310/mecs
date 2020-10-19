package org.camel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.camel.service.PrintOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.Admin;
import mecs.camel.utils.IsNullUtil;

/**
 * 打印导检单和条码

     * <p>Title : PrintOrderController</p>

     * <p>Description : </p>

     * <p>DevelopTools : Eclipse_x64_8.5</p>

     * <p>DevelopSystem : macOS Sierra 10.12.1</p>

     * <p>Company : org.camel</p>

     * @author : camel

     * @date : 2019年6月19日 上午8:57:11

     * @version : 0.0.1
 */
@Controller
public class PrintOrderController {

	@Autowired
	private PrintOrderService printOrderService;
	
	/**
	 * 根据卡号获取体检记录信息和用户信息
	 * @param cardNum
	 * @return
	 */
	@RequestMapping("/getMecRecordByCardNum.action")
	@ResponseBody
	public JSONObject getMecRecordByCardNum(String cardNum,HttpServletRequest req) {
		JSONObject obj = new JSONObject();
		/*
		 * 卡号非空判断
		 */
		List<String> isNullList = new ArrayList<String>();
		isNullList.add(cardNum);
		boolean notNull = IsNullUtil.isNull(isNullList);
		Admin admin = (Admin) req.getSession().getAttribute("sessionAdminKey");
		if(admin == null){//说明没有登录或者失效
			obj.put("res", "toLogin");
		}else if(notNull) {
			obj = printOrderService.getMecRecordByCardNum(cardNum);
		}else {
			obj.put("res", "isNull");
		}
		return obj;
	}
	
}
