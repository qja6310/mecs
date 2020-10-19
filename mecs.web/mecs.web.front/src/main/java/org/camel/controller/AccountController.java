package org.camel.controller;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.RowBounds;
import org.camel.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import mecs.camel.model.Account;
import mecs.camel.model.Dict;
import mecs.camel.utils.IsNullUtil;
import mecs.camel.utils.LimitUtil;

/**
 * 用户对账
 * 

     * <p>Title : AccountController.java</p>

     * <p>Description : </p>

     * <p>DevelopTools : Eclipse_x64_8.5</p>

     * <p>DevelopSystem : macOS Sierra 10.12.1</p>

     * <p>Company : org.camel</p>

     * @author : camel

     * @date : 2019年6月14日 上午11:17:18

     * @version : 0.0.1
 */
@Controller
public class AccountController {
	@Autowired
	private AccountService as;
	@RequestMapping("/account.action")
	@ResponseBody
	public JSONObject account(String currPage,String beginTime,String overTime,
			String type,HttpServletRequest request) {
		JSONObject jo = new JSONObject();
		
		//将存入session里面的String取出转换成Admin对象
		  String cardNum = (String) request.getSession().getAttribute("cardNum");
		ArrayList<String > list1 = new ArrayList<>();
		list1.add(currPage);
		list1.add(cardNum);
		boolean boo = IsNullUtil.isNull(list1);
		//非法输入，输入卡号为空
		if (!boo) {
			return jo;
		}
		int beginPage = Integer.valueOf(currPage) * LimitUtil.ACCOUNTLIMIT - LimitUtil.ACCOUNTLIMIT ;
		//获取余额
		String balance = null;
		balance = as.getBalanceByCard(cardNum);
		if(balance == null || "".equals(balance)) {
			jo.put("name", "");
			return jo;
		}
		//获取对账总记录数
		int count = as.getAccountCountByCard(cardNum, beginTime, overTime, type);
		//获取对账记录的集合
		ArrayList<Account> list = (ArrayList<Account>) as.getAccountByCard(cardNum, beginTime, overTime, type, new RowBounds(beginPage, LimitUtil.ACCOUNTLIMIT ));
        //获取用户id 通过卡号
		int userId = as.getUserIdByCard(cardNum);
		//存入日志
	    int bo = as.addLogForAccount(userId, "查看对账", "查看了对账记录");
	    jo.put("count", count);
	    jo.put("list", list);
	    jo.put("limit", LimitUtil.ACCOUNTLIMIT);
	    jo.put("balance", balance);
	    jo.put("userId", userId);
		return jo;
	}
	/**
	 * 获取下拉框的值
	 * @param type
	 * @return
	 */
	@RequestMapping("/getCheckInfo.action")
	@ResponseBody
	public JSONObject getCheckInfoByDictType(String type) {
		ArrayList<Dict> list = as.getDeskInfoByDictType(type);
		JSONObject jo = new JSONObject();
		jo.put("list", list);
		return jo;
	}
}
