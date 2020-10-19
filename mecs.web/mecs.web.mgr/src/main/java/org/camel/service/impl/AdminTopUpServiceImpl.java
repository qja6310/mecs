package org.camel.service.impl;

import javax.servlet.http.HttpSession;

import org.camel.dao.AddAccountLog;
import org.camel.dao.AdminTopUpMapper;
import org.camel.dao.LogMapper;
import org.camel.service.AdminTopUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mecs.camel.model.Account;
import mecs.camel.model.Admin;

@Service
public class AdminTopUpServiceImpl implements AdminTopUpService{
	
	@Autowired
	private AdminTopUpMapper ma;
	
	//插入后台人员日志
	@Autowired
	private LogMapper log;
	//插入个人对账日志
	@Autowired
	private AddAccountLog accountLog;
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String AdminTopUp(String money,String cardNum,HttpSession session) {
		// TODO Auto-generated method stub
		//把传入的钱，设置到加上原来的钱设置到数据库里，
		//先查原来的钱
		/*返回 suc 充值成功
		 * Err充值失败*/
		
		System.out.println("zoulAdminTopUpServiceImpl");
		
		Integer money1=ma.queryCardBalance(cardNum);
		Integer mo=null;
		if(money!=null) {
			mo=Integer.valueOf(money);	
		}
		
		Integer newMoney=money1+mo;
		Integer back=ma.topUp(newMoney, cardNum);
		if(back==1) {
			Admin admin=(Admin) session.getAttribute("sessionAdminKey");
			if(admin==null) {
				return "notLogin";
			}
			
			//插入后台人员日志
			
			Integer lo=log.addLog(Integer.valueOf(admin.getAdminId()), "充值", "操作账户为："+admin.getAdminAcc());
			System.out.println("lo="+lo);
			//插入个人对账日志
			Account account =new Account();
			account.setCardNum(cardNum);
			account.setAccMoney(String.valueOf(mo));
			account.setAccType("0");
			account.setAccDescribe("操作账号为："+admin.getAdminAcc());
			Integer a=accountLog.insertAccount(account);
			
			System.out.println("a="+a);
			
			return "suc";
		}else {
			return "Err";
		}
		
	}

}
