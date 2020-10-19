package org.camel.controller;

import javax.servlet.http.HttpServletRequest;

import org.camel.service.AccountService;
import org.camel.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.User;
/**
 * 登录
 * 

     * <p>Title : LoginController.java</p>

     * <p>Description : </p>

     * <p>DevelopTools : Eclipse_x64_8.5</p>

     * <p>DevelopSystem : macOS Sierra 10.12.1</p>

     * <p>Company : org.camel</p>

     * @author : camel

     * @date : 2019年6月17日 下午12:31:32

     * @version : 0.0.1
 */
@Controller
public class LoginController {
	  //登录
		@Autowired
		private LoginService ls;
		@RequestMapping("/frontlogin.action")
		@ResponseBody
		public JSONObject login(HttpServletRequest request,String cardNum) {
			JSONObject jo = new JSONObject();
			User user = ls.login(cardNum);
			if(user == null ) {//表示卡号有误，登录不成功
				jo.put("sign", "err");
				return jo;
			}else {//表示登录成功，user存入session
				
				// 用户存入session
			   request.getSession().setAttribute("user", user);
			   request.getSession().setAttribute("cardNum", cardNum);
				jo.put("sign", "suc");
				jo.put("user", user);
				return jo;
			}
			
		
		}
		
		//检查是否有登录
		@RequestMapping("/checkLogin.action")
		@ResponseBody
		public JSONObject account(HttpServletRequest request) {
			JSONObject jo = new JSONObject();
			User user = (User) request.getSession().getAttribute("user");
			if (user != null ) {//说明已经登录了
				jo.put("sign", "yes");
				jo.put("name", user.getUserName());
				return jo;
			}else {//说明未登录
				jo.put("sign", "no");
				return jo;
			}
			
		}
		//注销
		@RequestMapping("/logout.action")
		@ResponseBody
		public JSONObject logout(HttpServletRequest request) {
			JSONObject jo = new JSONObject();
			request.getSession().removeAttribute("user");
			request.getSession().removeAttribute("cardNum");
			User user = (User) request.getSession().getAttribute("user");
			String cardNum= (String) request.getSession().getAttribute("cardNum");
			if (user != null &&  cardNum != null ) {//说明未注销成功
				jo.put("sign", "yes");
				jo.put("name", user.getUserName());
				return jo;
			}else {//说明注销成功
				jo.put("sign", "no");
				return jo;
			}
		}


}
