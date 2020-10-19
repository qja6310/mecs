package org.camel.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.camel.service.ItemQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.Admin;

/**
 * 
 * 
 * 
 * <p>
 * Title : ItemQueryController.java
 * </p>
 * 
 * <p>
 * Description :
 * </p>
 * 
 * <p>
 * DevelopTools : Eclipse_x64_8.5
 * </p>
 * 
 * <p>
 * DevelopSystem : macOS Sierra 10.12.1
 * </p>
 * 
 * <p>
 * Company : org.camel
 * </p>
 * 
 * @author : camel
 * 
 * @date : 2019年6月17日 下午10:55:20
 * 
 * @version : 0.0.1
 */
@Controller
public class ItemQueryController {

	@Autowired
	private ItemQueryService itemQueryService;

	@RequestMapping("/itemQuery.action")
	@ResponseBody
	public JSONObject itemQuery(String nowPage, String itemName) {

		JSONObject item = itemQueryService.getItem(nowPage, itemName);

		return item;
	}

	@RequestMapping("/itemAdd.action")
	@ResponseBody
	public JSONObject itemAdd(String itemName, String itemUnit, HttpServletRequest request) {

		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("sessionAdminKey");
		String adminId = admin.getAdminId();

		JSONObject addItm = itemQueryService.addItm(adminId,itemName, itemUnit);

		return addItm;
	}

	@RequestMapping("/itemDel.action")
	@ResponseBody
	public JSONObject itemDel(String itemId, HttpServletRequest request) {

		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("sessionAdminKey");
		String adminId = admin.getAdminId();

		JSONObject delItem = itemQueryService.delItem(adminId,itemId);

		return delItem;
	}
	
	
	

}
