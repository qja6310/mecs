package org.camel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.camel.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.Admin;
import mecs.camel.model.Items;
import mecs.camel.model.User;
import mecs.camel.utils.IsNullUtil;


/**
 * 开单

     * <p>Title : BillingController</p>

     * <p>Description : </p>

     * <p>DevelopTools : Eclipse_x64_8.5</p>

     * <p>DevelopSystem : macOS Sierra 10.12.1</p>

     * <p>Company : org.camel</p>

     * @author : camel

     * @date : 2019年6月14日 上午11:05:11

     * @version : 0.0.1
 */
@Controller
public class BillingController {

	@Autowired
	private BillingService billingService;
	/**
	 * 根据卡号去获取体检人信息
	 * @param cardNum
	 * @return
	 */
	@RequestMapping("/getUserInfoByCardNum.action")
	@ResponseBody
	public JSONObject getUserInfoByCardNum(String cardNum) {
		JSONObject obj = new JSONObject();
		//非空判断
		List<String> isNullList = new ArrayList<String>();
		isNullList.add(cardNum);
		boolean notNull = IsNullUtil.isNull(isNullList);
		if(notNull) {
			obj = billingService.getUserInfoByCardNum(cardNum);
		}else {
			obj.put("res", "isNull");
		}
		return obj;
	}
	
	/**
	 * 获取体检套餐
	 * @param cardNum
	 * @return
	 */
	@RequestMapping("/getAllComboInfo.action")
	@ResponseBody
	public JSONObject getAllComboInfo(String comboName,String comboState) {
		JSONObject obj = new JSONObject();
		//获取套餐集合
		obj = billingService.getAllComboInfo(comboName,comboState);
		return obj;
	}
	
	/**
	 * 根据套餐ID获取详情,包括项目,及项目里的细项
	 * @param cardNum
	 * @return
	 */
	@RequestMapping("/getDetailsByComboId.action")
	@ResponseBody
	public JSONObject getDetailsByComboId(String comboId) {
		JSONObject obj = new JSONObject();
		//非空判断
		List<String> isNullList = new ArrayList<String>();
		isNullList.add(comboId);
		boolean notNull = IsNullUtil.isNull(isNullList);
		if(notNull) {
			/*
			 *调用service中的方法
			 */
			obj = billingService.getDetailsByComboId(comboId);
		}else {
			obj.put("res", "isNull");
		}
		return obj;
	}
	
	/**
	 * 确认体检套餐
	 * @param cardNum
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/chooseItems.action")
	@ResponseBody
	public JSONObject chooseItems(String comboJSON,String mrPrice,String cardNum,HttpServletRequest req) throws Exception {
		JSONObject obj = new JSONObject();
		//非空判断
		List<String> isNullList = new ArrayList<String>();
		isNullList.add(cardNum);
		boolean notNull = IsNullUtil.isNull(isNullList);
		//用json封装套餐集合
		List<String> comboList = JSONObject.parseArray(comboJSON, String.class);
		/*
		 *在作用域中获取登陆者 
		 */
		Admin admin = (Admin) req.getSession().getAttribute("sessionAdminKey");
//		Admin admin = new Admin();
//		admin.setAdminId("1");
		if(admin == null) {//说明session失效,需重新登录
			obj.put("res", "toLogin");
		}else if(notNull && comboList.size() > 0) {
			obj = billingService.sureBilling(comboList, mrPrice, cardNum, admin);
		}else {
			obj.put("res", "isNull");
		}
		return obj;
	}
	
	@RequestMapping("/billing.action")
	public ModelAndView getPrintInfo(String mrNum,HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		JSONObject obj = new JSONObject();
		//非空判断
		List<String> isNullList = new ArrayList<String>();
		isNullList.add(mrNum);
		boolean notNull = IsNullUtil.isNull(isNullList);
		/*
		 *在作用域中获取登陆者 
		 */
		Admin admin = (Admin) req.getSession().getAttribute("sessionAdminKey");
		if(admin == null) {
			mv.setViewName("adminlogin");
		}else if(notNull) {
			obj = billingService.getPrintInfo(mrNum);
			User u = (User) obj.get("user");
			mv.addObject("user", u);
			String time = obj.getString("time");
			mv.addObject("time", time);
			List<Items> itemsList = (List<Items>) obj.get("itemsList");
			mv.addObject("itemsList", itemsList);
			mv.addObject("mrNum", mrNum);
			mv.setViewName("billing");
		}else {
			obj.put("res", "isNull");
		}
		return mv;
	}
}
