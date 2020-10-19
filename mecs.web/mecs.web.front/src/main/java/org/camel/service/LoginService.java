package org.camel.service;


import mecs.camel.model.User;

public interface LoginService {
	/**
	 * 用户通过卡号登录
	 * 
	 * @param cardNum
	 * @return
	 */
	User login( String cardNum);
}
