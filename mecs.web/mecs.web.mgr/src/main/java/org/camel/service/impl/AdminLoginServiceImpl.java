package org.camel.service.impl;

import javax.servlet.http.HttpSession;

import org.camel.dao.AdminLoginMapper;
import org.camel.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import mecs.camel.model.Admin;
import mecs.camel.model.Log;

@Service
public class AdminLoginServiceImpl implements AdminLoginService {
	
	@Autowired
	private AdminLoginMapper loginMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String changPsd(String oldPsd, String newPsd, HttpSession session) {
		// 获得当前用户的ID
		//将存入session里面的String取出转换成Admin对象
		Admin ad = (Admin) session.getAttribute("sessionAdminKey");
//		Admin ad = JSONObject.parseObject(admin, Admin.class);
//		Admin ad = (Admin) session.getAttribute("sessionAdminKey");
		String adminId = ad.getAdminId();
		// 先查询旧密码是否存在
		Admin ad2 = loginMapper.queryOldPsd(oldPsd, adminId);
		if (ad2 != null) {
			Integer i = loginMapper.changPsd(newPsd, adminId);
			if (i > 0) {
				// 插入修改的日志
				Log log = new Log();
				log.setLogUserId(adminId);
				log.setLogType("修改");
				log.setLogRemark("操作账号为："+ad2.getAdminAcc()+"，名字为："+ad2.getAdminName());
				loginMapper.doLog(log);

				return "suc";
			}
			return "err";
		} else {
			return "oldErr";
		}

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String queryUser(String account, String psd, HttpSession session) {
		Admin admin = new Admin();
		admin.setAdminAcc(account);
		admin.setAdminPawd(psd);
		Admin ad = loginMapper.loginQueryAdmin(admin);
		// 如果查询到用户
		if (ad != null) {
			//System.out.println("查询到的admin" + ad.toString());
			String adId = ad.getAdminId();
			// String转Integer，必须进行非空判断，不然可能会出现空指针异常
			Integer adminId = null;
			if (adId != null) {
				adminId = Integer.valueOf(adId);
			}
			//System.out.println("用来查询的用户ID=" + adminId);
			// 查询改用户的状态是否被禁用
			String queryAdminState = loginMapper.queryAdminState(adminId);

			// 如果等于0，说明被禁用了
			if (queryAdminState.equals("0")) {
				return "forb";
			}
			// 用户存入session
//			String jsonString = JSON.toJSONString(ad);
			session.setAttribute("sessionAdminKey", ad);
			
			// 插入登录的日志
			Log log = new Log();
			log.setLogUserId(adId);
			log.setLogType("登录");
			log.setLogRemark("登录账号为："+account);
			
			loginMapper.doLog(log);
			return "suc";
		} else {
			return "err";
		}

	}

}
