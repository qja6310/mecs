package org.camel.service.impl;

import org.camel.dao.SettleAccountsUserDtoMapper;
import org.camel.service.SettleAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mecs.camel.model.dto.SettleAccountsUserDto;

@Service
public class SettleAccountsServiceImpl implements SettleAccountsService{
	//获得dao方法
	@Autowired
	private SettleAccountsUserDtoMapper mapper;
	
	
	@Override
	public SettleAccountsUserDto querySettleAccountsByService(String cardNum) {
		// TODO Auto-generated method stub
		SettleAccountsUserDto dto=mapper.querySettleAccounts(cardNum);
		return dto;
	}
	
}
