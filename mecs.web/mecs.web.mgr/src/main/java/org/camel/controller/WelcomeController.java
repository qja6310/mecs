package org.camel.controller;

import javax.servlet.http.HttpServletRequest;

import org.camel.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.Admin;

/**
 * 欢迎界面数据获取

     * <p>Title : WelcomeController</p>

     * <p>Description : </p>

     * <p>DevelopTools : Eclipse_x64_8.5</p>

     * <p>DevelopSystem : macOS Sierra 10.12.1</p>

     * <p>Company : org.camel</p>

     * @author : camel

     * @date : 2019年6月22日 下午10:53:37

     * @version : 0.0.1   1111111
 */
@Controller
public class WelcomeController {

	@Autowired
	private WelcomeService welcomeService;
	
	@RequestMapping("/getAdminAndData.action")
	@ResponseBody
	public JSONObject getAdminAndData(HttpServletRequest req) {
		JSONObject obj = new JSONObject();
		//将存入session里面的String取出转换成Admin对象
		Admin admin = (Admin) req.getSession().getAttribute("sessionAdminKey");
//		  Admin admin = JSONObject.parseObject(ad, Admin.class);
//		Admin admin = (Admin) req.getSession().getAttribute("sessionAdminKey");
		//判断是否有没有登录
		if(admin != null) {
			obj = welcomeService.getAdminAndData(admin);
		}else {
			obj.put("res", "isNull");
		}
		return obj;
	}
}
