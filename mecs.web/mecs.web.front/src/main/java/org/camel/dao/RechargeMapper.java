package org.camel.dao;

import org.apache.ibatis.annotations.Param;

import mecs.camel.model.Log;

public interface RechargeMapper {

	/**
	 * 获取卡内余额
	 * @param cardNum
	 * @return
	 */
	String getBalanceByCardNum(@Param("cardNum") String cardNum);
	
	/**
	 * 获取订单号
	 * @return
	 */
	String checkAccountOrderNum(@Param("aoNum") String aoNum);
	
	/**
	 * 插入账单表
	 * @param aoNum
	 * @param aoName
	 * @param aoPay
	 * @param aoRemark
	 * @param cardNum
	 * @return
	 */
	Integer addAccountOrder(@Param("aoNum") String aoNum,@Param("aoName") String aoName,@Param("aoPay") String aoPay,@Param("aoRemark") String aoRemark,@Param("cardNum") String cardNum);
	
	/**
	 * 修改余额
	 * @param aoPay
	 * @param cardNum
	 * @return
	 */
	Integer changeCardBalance(@Param("aoPay") String aoPay,@Param("cardNum") String cardNum);
	
	/**
	 * 插入对账表
	 * @param cardNum
	 * @param accRemark
	 * @param accMoney
	 * @return
	 */
	Integer addAccount(@Param("cardNum") String cardNum,@Param("accRemark") String accRemark,@Param("accMoney") String accMoney);
	
	/**
	 * 插入日志表
	 * @param mgrLog
	 * @return
	 */
	Integer addFrontLog(Log frontLog);
	
	/**
	 * 根据卡号获取用户Id
	 * @param cardNum
	 * @return
	 */
	String getUserIdByCrdNum(@Param("cardNum") String cardNum);
}
