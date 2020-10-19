package org.camel.controller;

import javax.servlet.http.HttpSession;

import org.camel.service.AdminTopUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
public class AdminTopUpController {
	
	@Autowired
	private AdminTopUpService ser;
	
	
	/**
	 * 充值
	 * @param money
	 * @param carNum
	 * @return
	 * 返回 suc 充值成功
	 * Err充值失败
	 * notLogin:没登录
	 */
	@RequestMapping("/AdminTopUp")
	@ResponseBody
	public JSONObject topUp(String money,String cardNum,HttpSession session) {
		System.out.println("充值控制");
		JSONObject obj =new JSONObject();
		String st=ser.AdminTopUp(money,cardNum,session);
		System.out.println("返回值："+st);
		obj.put("AdminTopUpKey", st);
		return obj;
	}
}
