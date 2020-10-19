package org.camel.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.camel.service.CardDeleteService;
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
 * Title : CardDeleteController.java
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
 * @date : 2019年6月15日 下午2:53:38
 * 
 * @version : 0.0.1
 */
@Controller
public class CardDeleteController {

	@Autowired
	private CardDeleteService cardDeleteService;

	@RequestMapping("/delCard.action")
	@ResponseBody
	public JSONObject delCard(String cardId, String cardNum, HttpServletRequest request) {

		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("sessionAdminKey");
		String adminId = admin.getAdminId();

		JSONObject delCard = cardDeleteService.delCard(cardId, cardNum, adminId);
		return delCard;
	}

}
