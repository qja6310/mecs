package org.camel.dao;

import org.apache.ibatis.annotations.Param;

public interface AdminTopUpMapper {
	/**
	 * 查询原来的金额
	 * @param cardNum
	 * @return
	 */
	Integer queryCardBalance(@Param("cardNum") String cardNum);
	
	/**
	 * 把新充值加的金额重新设置到car表里面
	 * @param newMoney
	 * @param cardNum
	 * @return
	 */
	Integer topUp(@Param("newMoney") Integer newMoney,@Param("cardNum") String cardNum);

}
