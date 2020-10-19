package org.camel.dao;

import org.apache.ibatis.annotations.Param;

import mecs.camel.model.dto.SettleAccountsUserDto;

public interface SettleAccountsUserDtoMapper {
	//先嵌套查询，通过卡号查出卡ID，在通过内连接查询，通过拓展类查询出来
	SettleAccountsUserDto querySettleAccounts(@Param("cardNum")String cardNum);
}
