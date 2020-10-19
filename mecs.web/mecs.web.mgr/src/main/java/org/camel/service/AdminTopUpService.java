package org.camel.service;

import javax.servlet.http.HttpSession;

public interface AdminTopUpService {
	String AdminTopUp(String money,String cardNum,HttpSession session);
}
