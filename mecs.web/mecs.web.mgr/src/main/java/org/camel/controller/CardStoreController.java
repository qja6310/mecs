package org.camel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.camel.service.CardStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.Admin;
import mecs.camel.utils.IsNullUtil;

/**
 * 
 * 
 * 
 * <p>
 * Title : cardStoreController.java
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
 * @date : 2019年6月14日 上午11:01:35
 * 
 * @version : 0.0.1
 */
@Controller
public class CardStoreController {

	@Autowired
	private CardStoreService cardStoreService;

	@RequestMapping("/cardStore.action")
	@ResponseBody
	public JSONObject cardStore(String startNum, String endNum, String cardPrefix, HttpServletRequest request) {

		HttpSession session = request.getSession();
		//将存入session里面的String取出转换成Admin对象
		Admin admin = (Admin) session.getAttribute("sessionAdminKey");
		String adminId = admin.getAdminId();

		JSONObject obj = new JSONObject();
		List<String> isNullList = new ArrayList<String>();
		isNullList.add(startNum);
		isNullList.add(endNum);
		
		boolean notNull = IsNullUtil.isNull(isNullList);
		if (notNull) {
			System.out.println("你过来啊！！！！");
			// 去service,然后添加日志
			JSONObject cardStore = cardStoreService.cardStore(startNum, endNum, cardPrefix, adminId);
			return cardStore;
		} else {
			System.out.println("你这是啥？？？？");
			obj.put("res", "isNull");
		}
		return obj;
	}

}
