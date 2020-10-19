package org.camel.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.camel.service.AdminSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.Admin;
import mecs.camel.model.dto.MenuDto;

/**
 * 登录进入主界面该获取的信息

     * <p>Title : AdminSystemController</p>

     * <p>Description : </p>

     * <p>DevelopTools : Eclipse_x64_8.5</p>

     * <p>DevelopSystem : macOS Sierra 10.12.1</p>

     * <p>Company : org.camel</p>

     * @author : camel

     * @date : 2019年6月22日 下午3:46:48

     * @version : 0.0.1
 */
@Controller
public class AdminSystemController {

	@Autowired
	private AdminSystemService adminSystemService;
	
	@RequestMapping("/getMenuAndAdmin.action")
	@ResponseBody
	public JSONObject getMenuAndAdmin(HttpServletRequest req) {
		System.out.println("fasfdsfdgdffdhgfhgfjghjg");
		JSONObject obj = new JSONObject();
		/*
		 * 获取一个admin
		 */
		//将存入session里面的String取出转换成Admin对象
		Admin admin = (Admin) req.getSession().getAttribute("sessionAdminKey");
//		  Admin admin = JSONObject.parseObject(ad, Admin.class);
//		Admin admin = (Admin) req.getSession().getAttribute("sessionAdminKey");
		if(admin != null) {
			obj.put("admin", admin);
			/*
			 * 去获取菜单
			 */
			Set<MenuDto> menuList = adminSystemService.menuList(admin);
			obj.put("menuList", menuList);
			obj.put("res", "suc");
		}else {
			obj.put("res", "toLogin");
		}
		return obj;
	}
	
}
