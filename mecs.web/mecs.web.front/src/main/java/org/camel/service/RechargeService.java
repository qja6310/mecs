package org.camel.service;

public interface RechargeService {
	
	/**
	 *  获取卡内余额
	 * @param cardNum
	 * @return
	 */
	String getBalanceByCardNum(String cardNum);

	/**
	 * 获取订单号
	 * @return
	 */
	String getAccountOrderNum();
	
	/**
	 * 插入数据库
	 * @param cardNum
	 * @param aoNum
	 * @param rechrgeMoney
	 * @return
	 */
	String finishRecharge(String cardNum,String aoNum,String rechrgeMoney);
}
