package org.camel.controller;

import org.camel.service.CardQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * 

     * <p>Title : CardQueryController.java</p>

     * <p>Description : </p>

     * <p>DevelopTools : Eclipse_x64_8.5</p>

     * <p>DevelopSystem : macOS Sierra 10.12.1</p>

     * <p>Company : org.camel</p>

     * @author : camel

     * @date : 2019年6月14日  下午4:50:43

     * @version : 0.0.1
 */
@Controller
public class CardQueryController {

	@Autowired
	private CardQueryService cardQueryService;

	@RequestMapping("/queryCard.action")
	@ResponseBody
	public JSONObject queryCard(String nowPage, String cardNumber, String userName, String cardState, String startTime,
			String endTime) {

		JSONObject cardQuery = cardQueryService.cardQuery(nowPage, cardNumber, userName, cardState, startTime,
				endTime);
		return cardQuery;

	}

}
