package org.camel.controller;

import org.camel.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
public class DictController {

	@Autowired
	private DictService dictService;

	@RequestMapping("/dictPrefix.action")
	@ResponseBody
	public JSONObject dictPrefix(String cardId) {

		JSONObject cardPrefix = dictService.getCardPrefix();
		return cardPrefix;
	}

}
