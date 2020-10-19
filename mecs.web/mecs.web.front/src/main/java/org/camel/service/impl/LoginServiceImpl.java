package org.camel.service.impl;

import org.camel.dao.LoginMapper;
import org.camel.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mecs.camel.model.User;
@Service
public class LoginServiceImpl implements LoginService{
   
	@Autowired
	private LoginMapper mapper;
    @Transactional(rollbackFor = Exception.class)
	public User login(String cardNum) {
		User user = mapper.loginByCardNum(cardNum);
		return user;
	}
}
