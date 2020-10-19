package org.camel.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.camel.service.ItemAlterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
 * Title : ItemAlterController.java
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
 * @date : 2019年6月17日 下午10:55:28
 * 
 * @version : 0.0.1
 */
@Controller
public class ItemAlterController {

	@Autowired
	private ItemAlterService itemAlterService;

	@RequestMapping("/itemAlter.action")
	@ResponseBody
	public JSONObject itemAlter(String itemName, String itemId, HttpServletRequest request) {

		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("sessionAdminKey");
		String adminId = admin.getAdminId();

		JSONObject item = itemAlterService.alterItem(adminId,itemName, itemId);

		return item;
	}

}
