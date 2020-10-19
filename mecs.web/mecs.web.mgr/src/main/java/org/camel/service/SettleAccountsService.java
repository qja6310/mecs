package org.camel.service;

import javax.servlet.http.HttpServletRequest;

import mecs.camel.model.dto.SettleAccountsUserDto;

public interface SettleAccountsService {
	SettleAccountsUserDto querySettleAccountsByService(String cardNum);
}
