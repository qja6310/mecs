package org.camel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.camel.service.SettleAccountsCommitService;
import org.camel.service.SettleAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.dto.SettleAccountsUserDto;
import mecs.camel.utils.IsNullUtil;

@Controller
public class SettleAccountsController {
	
	//通过自动的接口获得相应的实现类
	@Autowired
	private SettleAccountsService service;
	
	
	/**
	 * 读卡按钮
	 * @param cardNum
	 * @return
	 */
	@RequestMapping("/settleAccounts")
	@ResponseBody
	public JSONObject SettleAccounts(String cardNum) {
		JSONObject obj=new JSONObject();
		//非空判断
		List<String> list=new ArrayList<String>();
		list.add(cardNum);
		if(!IsNullUtil.isNull(list)) {
			obj.put("SettleAccountsKey", "inputHaveNull");
			return obj;
		}
		
		
		SettleAccountsUserDto querySettleAccountsByService = service.querySettleAccountsByService(cardNum);
		if(querySettleAccountsByService == null) {
			obj.put("SettleAccountsKey", "cardNumErr");
			return obj;
		}
		//System.out.println("读卡："+querySettleAccountsByService.toString());
		obj.put("SettleAccountsKey", querySettleAccountsByService);
		return obj;
		
	}
	
	
	
	
	@Autowired
	private SettleAccountsCommitService Commit;
	
	/**
	 * 点了结算按钮之后， :				
	 * @param cardNum
	 * @return
	 * 可能返回的值如下
	 * haveSettle:已经被结算了
	 * balanceInsuf:余额不足
	 * updateBalanceErr:修改卡的余额失败
	 * Err1:：请重试，修改体检记录表的状态失败
	 * Err2：请重试，修改项目记录表的状态失败
	 * Err5:请重试，插入日志，失败
	 * notLogIn:账号未登录
	 * Err3:：请重试。插入日志失败
	 * cardNumIsNull：插入对账表的时候，查询卡号失败
	 * Err4：请重试。个人对账表插入数据失败
	 * 
	 * suc:结算成功
	 */
	@RequestMapping("/SettleAccountsCommit")  
	@ResponseBody
	public JSONObject SettleAccountsCommit(String mrId,HttpServletRequest req) {
		JSONObject obj=new JSONObject();
		//非空判断
		List<String> list=new ArrayList<String>();
		list.add(mrId);
		if(!IsNullUtil.isNull(list)) {
			obj.put("SettleAccountsCommitKey", "inputHaveNull");
			return obj;
		}
		
		String st=Commit.commit(mrId,req);
		//System.out.println("提交控制层获得的 st="+st);
		obj.put("SettleAccountsCommitKey", st);

		return obj;
	}
	
	
}
