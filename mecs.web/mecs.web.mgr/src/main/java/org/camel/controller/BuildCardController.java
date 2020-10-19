package org.camel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.camel.service.BuildCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.utils.IsNullUtil;

@Controller
public class BuildCardController {
	
	@Autowired
	private BuildCardService ser;
	
	//给界面提示1个已有的卡号的方法
	@RequestMapping("/backCardNumber")
	@ResponseBody
	public JSONObject backCardNumber() {
		JSONObject obj=new JSONObject();
	String st =ser.backCardNumber();
		obj.put("backCardNumber", st);
		return obj;	
	}

	
	//money 是充值的钱，减去开卡的
	//先把办卡人的信息插入，User表，或者User表的ID，然后userID插入 card表，改状态为已经销售，插入自
	//已经登录的收费员的ID为售卡人ID
	/**
	 * /**
	 * 返回值：
	 * withoutCardID没有这个卡号 ，
	 * buildCardPericeErr未设置开卡单价,
	 * MoneyErr充值金额小于开卡的钱，无法开卡
	 * buildUserErr：开卡失败，无法创建用户时，序列号
	 * buildUserErr2：开卡失败，创建用户
	 * notLogIn：收费员未先登录
	 * buildCardErr：建卡错误
	 * AccountErr：请重试插入 个人对账表的时候，错误
	 * logErr：插入日志失败
	 * suc：建卡成功
	 * 
	 * Err：建卡失败：调用service层失败
	 *
	 *InputHaveNull:除了血型，备注的输入框不能为空。
	 * 
	 * 
	 * 
	 * @param req
	 * @param cardNum
	 * @param userName
	 * @param money
	 * @param userSex
	 * @param userAge
	 * @param userBloodType
	 * @param userBirthday
	 * @param userPhone
	 * @param userAddress
	 * @param userMemo
	 * @param userNativePlace
	 * @return
	 */
	@RequestMapping("/buildcard")
	@ResponseBody
	public JSONObject buildCard(HttpServletRequest req,String cardNum,String userName,String money,String userSex,String userAge,String userBloodType,String userBirthday,String userPhone,String userAddress,String userMemo,String userNativePlace) {
		
		JSONObject obj=new JSONObject();
		
		//非空判断，血型和备注可以为空 userBloodType,userMemo,
		List<String> list=new ArrayList<String>();
		list.add(cardNum);
		list.add(userName);
		list.add(money);
		list.add(userSex);
		list.add(userAge);
		list.add(userBirthday);
		list.add(userAddress);
		list.add(userNativePlace);
		boolean bo=IsNullUtil.isNull(list);
		if(bo==false) {
			//System.out.println("除了血型和备注之后的输入框不能为空");
			obj.put("buildcardkey", "InputHaveNull");
			return obj;
		}
		

        String st=ser.buildCard(req, cardNum, money, userName, userSex, userAge, userBloodType, userBirthday, userPhone, userAddress,userMemo, userNativePlace);
      // System.out.println("service 返回的值：st="+st); 
        if(st!=null) {
        	obj.put("buildcardkey", st);
        	return obj;
        }else {
        	obj.put("buildcardkey", "Err");
        	return obj;
        }
	
		
	}
}
