package org.camel.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.camel.dao.WelcomeMapper;
import org.camel.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.Admin;

@Service
public class WelcomeServiceImpl implements WelcomeService {

	@Autowired
	private WelcomeMapper welcomeMpper;
	
	@Override
	public JSONObject getAdminAndData(Admin admin) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		obj.put("admin", admin);
		/*
		 * 获取售卡数
		 */
		Integer sellCardNum = welcomeMpper.getSellCardNum();
		obj.put("sellCardNum", sellCardNum);
		/*
		 * 获取用户数
		 */
		Integer userNum = welcomeMpper.getUserNum();
		obj.put("userNum", userNum);
		/*
		 * 获取营业额
		 */
		String turnover = welcomeMpper.getTurnover();
		obj.put("turnover", turnover);
		/*
		 * 获取当前可用卡数
		 */
		Integer usableCardNum = welcomeMpper.getUsableCardNum();
		obj.put("usableCardNum", usableCardNum);
		
		/*
		 * 获取当月的卡销售
		 */
		//获取当月的第一天
		// 获取当月第一天和最后一天  
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
        String firstday,currDate; 
        // 获取前月的第一天  
        Calendar cale = null; 
        cale = Calendar.getInstance();  
        cale.add(Calendar.MONTH, 0);  
        cale.set(Calendar.DAY_OF_MONTH, 1);  
        firstday = format.format(cale.getTime());
        
        //获取当前日期
        Calendar c=java.util.Calendar.getInstance();    
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        currDate = f.format(c.getTime());
        Integer sell = welcomeMpper.getSell(firstday, currDate);
        obj.put("sell", sell);
        /*
         * 获取当月的用户
         */
        Integer userM = welcomeMpper.getUser(firstday, currDate);
        obj.put("userM", userM);
        /*
         * 获取当月营业额
         */
        String turnoverM = welcomeMpper.getTurnoverM(firstday, currDate);
        obj.put("turnoverM", turnoverM);
		return obj;
	}

}
