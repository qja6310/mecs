package org.camel.service;

import javax.servlet.http.HttpSession;

public interface AdminLoginService {
	/**
	 * 登录的时候，根据返回的字符串，放回ajax
	 * @param name
	 * @param psd
	 * @return
	 */
	String queryUser(String account,String psd,HttpSession session);
	
	/**
	 * 修改密码
	 * @param oldPsd 
	 * @param newPsd
	 * @return
	 */
	String changPsd(String oldPsd,String newPsd,HttpSession session);

	
}
